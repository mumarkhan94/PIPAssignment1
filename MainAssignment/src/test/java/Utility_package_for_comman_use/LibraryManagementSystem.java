package Utility_package_for_comman_use;


import java.util.Scanner;

public class LibraryManagementSystem {
    private static Scanner scanner = new Scanner(System.in);

    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Library Management System!");
    }

    public static int displayLoginMenu() {
        System.out.println("\nLogin Menu:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static boolean registerUser() {
        System.out.println("Registration:");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // TODO: Perform registration logic (e.g., store user credentials in a database/file)

        System.out.println("Registration successful!");
        return true;
    }

    private static void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Search Books");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // TODO: Implement add book functionality
                break;
            case 2:
                // TODO: Implement borrow book functionality
                break;
            case 3:
                // TODO: Implement return book functionality
                break;
            case 4:
                // TODO: Implement search books functionality
                break;
            case 5:
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMainMenu();
        }
    }
}

