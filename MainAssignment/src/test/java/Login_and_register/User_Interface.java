package Login_and_register;

import Read_write_file.Login_users;
import Read_write_file.Register_users;
import Utility_package_for_comman_use.LibraryManagementSystem;

import java.io.IOException;
import java.util.Scanner;

public class User_Interface {
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        // This method is use to enter the data into file.

        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        libraryManagementSystem.displayWelcomeMessage();
        Register_users register_users = new Register_users();

        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            switch (libraryManagementSystem.displayLoginMenu()) {
                case 1: {
                    Login_users users = new Login_users();
                    System.out.print("Enter your username: ");
                    Comman_variable.Username = scanner.next();
                    System.out.print("Enter your password: ");
                    String password = scanner.next();
                    switch (users.Login(Comman_variable.Username, password)) {
                        case 1: {
                            System.out.println("Login successful! User role is Student");
                            isLoggedIn = true;
                            while (isLoggedIn)
                                libraryManagementSystem.displayMainMenu_for_student();
                            break;
                        }
                        case 2: {
                            System.out.println("Login successful! User role is Staff");
                            isLoggedIn = true;
                            while (isLoggedIn)
                                libraryManagementSystem.displayMainMenu_for_staff();
                            break;
                        }
                        default: {
                            System.out.println("Login Unsuccessful!.");
                        }
                    }
                    break;
                }
                case 2: {
                    register_users.registerUser();
                    System.out.println("Registration successful!");
                    break;
                }
                case 3: {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Invalid option. Please try again.");
                }
            }

        }
    }
}