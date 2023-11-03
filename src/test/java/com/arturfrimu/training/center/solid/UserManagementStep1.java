package com.arturfrimu.training.center.solid;

/**
 * The UserManagementStep1 class demonstrates a violation of the Single Responsibility Principle (SRP)
 * by combining user management, error logging, and email notification functionalities within a single class.
 * This conflation of responsibilities means that changes in one area (e.g., user database logic, error logging mechanisms,
 * or email communication protocols) may necessitate modifications to this class, thereby increasing the risk of introducing
 * bugs in unrelated areas. Additionally, this design violates the following principles and best practices:<br/><br/>
 *
 * 1. Separation of Concerns (SoC): This principle is about separating a computer program into distinct sections,
 * such that each section addresses a separate concern. The current design mixes data persistence, error handling,
 * and communication concerns in one class.:<br/><br/>
 *
 * 2. Single Level of Abstraction (SLA): Each method should operate at a single level of abstraction to make the class more readable
 * and maintainable. This class handles high-level operations (such as adding or removing users) and lower-level operations
 * (like logging and sending emails), which mixes levels of abstraction.:<br/><br/>
 *
 * 3. Open/Closed Principle: Ideally, classes should be open for extension but closed for modification. This class would need to be
 * modified for changes in any of its areas of responsibility, rather than being extendable in a way that limits the need for modification.:<br/><br/>
 *
 * 4. Interface Segregation Principle: Clients should not be forced to depend on interfaces they do not use. In this case, a class
 * that only needs to manage user entities is also saddled with error logging and email sending capabilities.:<br/><br/>
 *
 * 5. Dependency Inversion Principle: High-level modules should not depend on low-level modules but should depend on abstractions.
 * Here, direct dependencies are created rather than through abstractions.:<br/><br/>
 *
 * Refactoring this class to adhere to these principles would involve separating these concerns into distinct classes or interfaces,
 * thus promoting a more robust, maintainable, and scalable design.:<br/><br/>
 */
public class UserManagementStep1 {

    public void addUser(String username, String password) {
        System.out.println("Adding user to the database: " + username);
    }

    public void deleteUser(String username) {
        System.out.println("Deleting user from the database: " + username);
    }

    public void log(String error) {
        System.out.println("Error logged: " + error);
    }

    public void sendEmail(String emailAddress, String message) {
        System.out.println("Sending email to: " + emailAddress + " Message: " + message);
    }
}

