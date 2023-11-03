package com.arturfrimu.training.center.solid;

import java.util.ArrayList;
import java.util.List;

/**
 * The UserManagementStep3 class represents a further refined implementation of user management functionality,
 * adhering closely to the Single Responsibility Principle (SRP) and the Dependency Inversion Principle (DIP),
 * two of the SOLID principles.<br/><br/>
 * <p>
 * By using interfaces for UserRepo, Logger, and EmailSender, this class demonstrates dependency inversion,
 * allowing for flexible and interchangeable implementations. The dependencies are injected into the class,
 * further promoting decoupling and making the class more testable and maintainable..<br/><br/>
 * <p>
 * Improvements over previous iterations include:.<br/><br/>
 * <p>
 * - The use of interfaces to define contracts for user repository operations, error logging, and email sending,
 * which enables the possibility of substituting different implementations without changing the client code..<br/><br/>
 * <p>
 * - Constructor-based dependency injection allows for better control over the instantiation of the dependencies
 * and facilitates unit testing by making it easy to inject mocks or stubs..<br/><br/>
 * <p>
 * - Clear separation of concerns is achieved as each interface and its implementation handle a single piece of functionality,
 * following SRP..<br/><br/>
 * <p>
 * This design makes the UserManagementStep3 class a robust and flexible foundation for user management operations..<br/><br/>
 */
public class UserManagementStep3 {

    private final UserRepo userRepo;
    private final Logger logger;
    private final NotificationSender notificationSender;

    public UserManagementStep3(UserRepo userRepo, Logger logger, NotificationSender notificationSender) {
        this.userRepo = userRepo;
        this.logger = logger;
        this.notificationSender = notificationSender;
    }

    public void addUser(String username, String password) {
        try {
            userRepo.addUser(username, password);
        } catch (Exception e) {
            logger.log("Error adding user: " + e.getMessage());
        }
    }

    public void deleteUser(String username) {
        try {
            userRepo.deleteUser(username);
        } catch (Exception e) {
            logger.log("Error deleting user: " + e.getMessage());
        }
    }

    public void notifyAllUsers(String emailAddress, String message) {
        try {
            notificationSender.send(emailAddress, message);
        } catch (Exception e) {
            logger.log("Error sending email: " + e.getMessage());
        }
    }

    interface UserRepo {
        void addUser(String username, String password);

        void deleteUser(String username);
    }

    static class UserRepoImpl implements UserRepo {
        @Override
        public void addUser(String username, String password) {
            if (username.isBlank()) {
                throw new IllegalArgumentException("Username can't be blank");
            }
            if (password.isBlank()) {
                throw new IllegalArgumentException("Password can't be blank");
            }

            System.out.println("Adding user to the database: " + username);
        }

        @Override
        public void deleteUser(String username) {
            if (username.isBlank()) {
                throw new IllegalArgumentException("Username can't be blank");
            }
            System.out.println("Deleting user from the database: " + username);
        }
    }

    interface Logger {
        void log(String error);
    }

    static class ConsoleLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("ConsoleLogger logged: " + message);
        }
    }

    static class FileLogger implements Logger {
        @Override
        public void log(String message) {
            System.out.println("FileLogger logged: " + message);
        }
    }

    static class CompositeLogger implements Logger {
        private final List<Logger> loggers = new ArrayList<>();

        public void addLogger(Logger logger) {
            loggers.add(logger);
        }

        @Override
        public void log(String error) {
            loggers.forEach(logger -> logger.log(error));
        }
    }

    interface NotificationSender {
        void send(String to, String message);
    }

    interface EmailSender extends NotificationSender {
    }

    interface SmsSender extends NotificationSender {
    }

    static class EmailSenderImpl implements EmailSender {

        @Override
        public void send(String to, String message) {
            if (to.isBlank()) {
                throw new IllegalArgumentException("EmailAddress can't be blank");
            }
            if (message.isBlank()) {
                throw new IllegalArgumentException("Message can't be blank");
            }
            System.out.println("Sending email to: " + to + " Message: " + message);
        }
    }

    static class SmsSenderImpl implements SmsSender {

        @Override
        public void send(String to, String message) {
            if (to.isBlank()) {
                throw new IllegalArgumentException("SmsAddress can't be blank");
            }
            if (message.isBlank()) {
                throw new IllegalArgumentException("Message can't be blank");
            }
            System.out.println("Sending sms to: " + to + " Message: " + message);
        }
    }

    static class CompositeNotificationSender implements NotificationSender {
        private final List<NotificationSender> notificationSenders = new ArrayList<>();

        public void add(NotificationSender notificationSender) {
            notificationSenders.add(notificationSender);
        }

        @Override
        public void send(String to, String message) {
            notificationSenders.forEach(notificationSender -> notificationSender.send(to, message));
        }
    }
}

