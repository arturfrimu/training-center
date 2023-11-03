package com.arturfrimu.training.center.solid;

import com.arturfrimu.training.center.solid.UserManagementStep3.*;

public class ClientCodeStep3 {

    public static void main(String[] args) {
        UserRepo userRepo = new UserRepoImpl();

        CompositeLogger compositeLogger = new CompositeLogger();
//        compositeLogger.addLogger(new ConsoleLogger());
//        compositeLogger.addLogger(new FileLogger());

        CompositeNotificationSender compositeNotificationSender = new CompositeNotificationSender();
        compositeNotificationSender.add(new EmailSenderImpl());
        compositeNotificationSender.add(new SmsSenderImpl());

        UserManagementStep3 userManagement = new UserManagementStep3(userRepo, compositeLogger, compositeNotificationSender);

        userManagement.addUser("John", "Doe");
        userManagement.deleteUser("John");
        userManagement.notifyAllUsers("john.doe@mail.com", "Your account was blocked !");

        userManagement.addUser("", "");
        userManagement.deleteUser("");
        userManagement.notifyAllUsers("", "");
    }
}
