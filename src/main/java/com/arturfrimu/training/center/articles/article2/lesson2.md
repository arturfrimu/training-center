# Spring @Transactional mistakes everyone did

Probably one of the most used Spring annotations is @Transactional. Despite its popularity, it is sometimes misused, resulting in something that is not what the software engineer intended.

In this article, I have collected the problems that I have personally encountered in projects. I hope this list will help you better understand transactions and help fix a couple of your issues.

## 1. Invocations within the same class

@Transactional is rarely covered by enough tests, and this leads to the fact that some problems are not visible at first glance. As a result, you can come across the following code:


```java
public void registerAccount(Account acc) {
    createAccount(acc);

    notificationSrvc.sendVerificationEmail(acc);
}

@Transactional
public void createAccount(Account acc) {
    accRepo.save(acc);
    teamRepo.createPersonalTeam(acc);
}
```

In this case, when calling registerAccount(), saving the user and creating a team will not be performed in a common transaction. @Transactional is powered by Aspect-Oriented Programming. Therefore, processing occurs when a bean is called from another bean. In the example above, the method is called from the same class so that no proxies can be applied. The same is true for other annotations such as @Cacheable.

The problem can be solved in three basic ways:

1. Self-inject
2. Create another layer of abstraction
3. Use TransactionTemplate in the registerAccount() method by wrapping createAccount() call

The first method seems less obvious, but this way, we avoid duplication of logic if @Transactional contains parameters.

```java
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accRepo;
    private final TeamRepository teamRepo;
    private final NotificationService notificationSrvc;
    @Lazy private final AccountService self;

    public void registerAccount(Account acc) {
        self.createAccount(acc);

        notificationSrvc.sendVerificationEmail(acc);
    }

    @Transactional
    public void createAccount(Account acc) {
        accRepo.save(acc);
        teamRepo.createPersonalTeam(acc);
    }
}
```

`If you use Lombok, don’t forget to add @Lazy to your lombok.config.`

## 2. Handling not all exceptions

By default, a rollback occurs only on RuntimeException and Error. At the same time, the code may contain checked exceptions, in which it is also necessary to roll back the transaction.

```java
@Transactional(rollbackFor = StripeException.class)
public void createBillingAccount(Account acc) throws StripeException {
    accSrvc.createAccount(acc);

    stripeHelper.createFreeTrial(acc);
}
```

## 3. Transaction isolation levels and propagation

Often, developers add annotations without really thinking about what kind of behavior they want to achieve. The default isolation level READ_COMMITED is almost always used.

Understanding isolation levels is essential to avoid mistakes that are very difficult to debug later.

For example, if you generate reports, you may select different data at the default isolation level by executing the same query several times during a transaction. It happens when a parallel transaction commits something at this time. Using REPEATABLE_READ will help avoid such scenarios and save a lot of time for troubleshooting.

Different propagations help to bound transactions in our business logic. For example, if you need to run some code in another transaction, not in the outer one, you can use REQUIRES_NEW propagation that suspends the outer transaction, creates a new one, and then resumes the outer transaction.

## 4. Transactions do not lock data

```java
@Transactional
public List<Message> getAndUpdateStatuses(Status oldStatus, Status newStatus, int batchSize) {
    List<Message> messages = messageRepo.findAllByStatus(oldStatus, PageRequest.of(0, batchSize));
    
    messages.forEach(msg -> msg.setStatus(newStatus));

    return messageRepo.saveAll(messages);
}
```

Sometimes there is a construction when we select something in the database, then update it, and think that since all this is done in a transaction and transactions have atomicity property, this code is executed as a single request.

The problem is that nothing prevents another application instance from calling findAllByStatus simultaneously as the first instance. As a result, the method will return the same data in both instances, and the data will be processed 2 times.

There are 2 ways to avoid this problem.

### Select for update (Pessimistic locking)

```sql
UPDATE message
SET status = :newStatus
WHERE id in (
   SELECT m.id FROM message m WHERE m.status = :oldStatus LIMIT :limit
   FOR UPDATE SKIP LOCKED)
RETURNING *
```

In the example above, when a select is performed, the lines are blocked until the end of the update. The query returns all changed rows.

### Versioning of entities (Optimistic locking)

This way helps to avoid blocking. The idea is to add a column version to our entities. Thus, we can select the data and then update it only if the version of the entities in the database matches the version in the application. In the case of using JPA, you can use @Version annotation.

## 5. Two different data sources

For example, we have created a new version of the datastore but still have to maintain the old one for a while.

```java
@Transactional
public void saveAccount(Account acc) {
        dataSource1Repo.save(acc);
        dataSource2Repo.save(acc);
}
```

Of course, in this case, only one save will be processed transactionally, namely, in that TransactionalManager that is considered as default.

Spring provides two options here.

## ChainedTransactionManager

```text
1st TX Platform: begin
  2nd TX Platform: begin
    3rd Tx Platform: begin
    3rd Tx Platform: commit
  2nd TX Platform: commit <-- fail
  2nd TX Platform: rollback  
1st TX Platform: rollback
```

ChainedTransactionManager is a way of declaring multiple data sources, in which, in the case of exception, rollbacks will occur in the reverse order. Thus, with three data sources, if an error occurred during a commit on the second, only the first two will try to roll back. The third has already committed the changes.

## JtaTransactionManager

This manager allows using fully supported distributed transactions based on a 2-phase commit. However, it delegates management to a backend JTA provider. It may be Java EE servers or standalone solutions (Atomikos, Bitrionix, etc.).

# Conclusion

Transactions are a tricky topic, and there can often be problems in knowledge. Most of the time, they are not fully covered by tests, so that most mistakes can be noticed only in the code review. And if incidents happen in production, finding a root cause is always a challenge.