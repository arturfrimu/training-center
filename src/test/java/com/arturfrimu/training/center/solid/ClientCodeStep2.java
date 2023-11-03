package com.arturfrimu.training.center.solid;

public class ClientCodeStep2 {

    public static void main(String[] args) {
        UserManagementStep2 userManagement = new UserManagementStep2();

        userManagement.addUser("John", "Doe");
        userManagement.deleteUser("John");
        userManagement.log("John was deleted successfully !");
        userManagement.sendEmail("john.doe@mail.com", "Your account was blocked !");
    }
}
