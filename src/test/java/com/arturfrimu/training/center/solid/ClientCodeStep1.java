package com.arturfrimu.training.center.solid;

public class ClientCodeStep1 {

    public static void main(String[] args) {
        UserManagementStep1 userManagement = new UserManagementStep1();

        userManagement.addUser("John", "Doe");
        userManagement.deleteUser("John");
        userManagement.log("John was deleted successfully !");
        userManagement.sendEmail("john.doe@mail.com", "Your account was blocked !");
    }
}
