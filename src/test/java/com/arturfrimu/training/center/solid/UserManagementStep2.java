package com.arturfrimu.training.center.solid;

/**
 * The UserManagementStep2 class demonstrates an attempt to adhere to the Single Responsibility Principle (SRP)
 * by delegating distinct functionalities to specialized classes. It uses composition to incorporate user management,
 * error logging, and email notification functionalities via separate classes. While this is a step towards a cleaner design,
 * it shows improvement over the previous iteration by separating concerns, yet there might still be room for enhancement:<br/><br/>
 *
 * 1. Dependency Management: The dependencies (UserRepo, UserLogger, UserEmailSender) are instantiated directly within
 * the class, which still couples the UserManagementStep2 class tightly to these implementations. Utilizing dependency
 * injection would allow these dependencies to be passed in, typically through a constructor, promoting greater flexibility
 * and easier testing.:<br/><br/>
 *
 * 2. Testing and Maintainability: Direct instantiation makes mocking the dependencies difficult during unit testing.
 * By using dependency injection, we could easily swap out these dependencies for mocks or stubs.:<br/><br/>
 *
 * 3. Scalability: As the application grows, we may want to further separate concerns, for instance, by having different
 * types of loggers or user repositories. Dependency injection would facilitate this process by not hard-coding the
 * dependency instantiation.:<br/><br/>
 *
 * By further refactoring to inject dependencies, we can make the UserManagementStep2 class adhere even more closely to
 * SOLID principles, thereby enhancing its maintainability, testability, and scalability.:<br/><br/>
 */
public class UserManagementStep2 {

    private UserRepo userRepo = new UserRepo();
    private UserLogger userLogger = new UserLogger();
    private UserEmailSender userEmailSender = new UserEmailSender();

    public void addUser(String username, String password) {
        userRepo.addUser(username, password);
    }

    public void deleteUser(String username) {
        userRepo.deleteUser(username);
    }

    public void log(String error) {
        userLogger.logError(error);
    }

    public void sendEmail(String emailAddress, String message) {
        userEmailSender.sendEmail(emailAddress, message);
    }

    static class UserRepo {
        public void addUser(String username, String password) {
            System.out.println("Adding user to the database: " + username);
        }

        public void deleteUser(String username) {
            System.out.println("Deleting user from the database: " + username);
        }
    }

    static class UserLogger {
        public void logError(String error) {
            System.out.println("Error logged: " + error);
        }
    }

    static class UserEmailSender {
        public void sendEmail(String emailAddress, String message) {
            System.out.println("Sending email to: " + emailAddress + " Message: " + message);
        }
    }
}

