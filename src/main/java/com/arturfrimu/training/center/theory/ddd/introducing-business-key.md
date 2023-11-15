### Introducing business key
Sometimes passing IDs manually is nearly impossible. 
Maybe it requires too much refactoring that is unbearable. 
Or maybe your application works with MySQL which doesn’t support sequences but only auto increment columns.

Though you can emulate sequences in MySQL by creating a regular table, 
this approach is not well performant.

In this case, you can introduce business key. 
That is a separate value that can identify an entity uniquely. 
Though it doesn’t mean that the business key must be globally unique. 
For example, if you point to Tamagotchi by name and it’s only unique within a Pocket, 
then you can identify Tamagotchi by a combination of (pocket_business_key, tamagothic_name).

### Nevertheless, each business
Nevertheless, each business key should be unmodifiable. 
Otherwise, you can compromise the identity of your entities. 
So, pay good attention to this point.

Also, a good example of a business key is a slug. Look at the URL of this article. 
Do you see that it contains its name and some hash value? 
That is the slug. 
It assigns only once when the article is created but never changes (even if I change the article’s name). 
So, if your entities don’t have an obvious candidate for business key, introducing a slug might be an option.