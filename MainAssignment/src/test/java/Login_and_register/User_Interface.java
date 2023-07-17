package Login_and_register;

import Read_write_file.Register_users;
import Utility_package_for_comman_use.LibraryManagementSystem;

import java.io.IOException;

public class User_Interface {

    public static void main(String[] args) throws IOException {
        // This method is use to enter the data into file.
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        libraryManagementSystem.displayWelcomeMessage();
        libraryManagementSystem.displayLoginMenu();

        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            switch (libraryManagementSystem.displayLoginMenu()) {
                case 1:{
//                isLoggedIn = login();
                    System.out.println("Login successful!");
                    break;}
                case 2:{
                    Register_users register_users = new Register_users();
                    register_users.fill_data();
//                boolean isRegistered = registerUser();
//                if (isRegistered) {
                    System.out.println("Registration successful!");
//                }
                    break;}
                case 3:{
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;}
                default:{
                    System.out.println("Invalid option. Please try again.");}
            }

        }
    }
}