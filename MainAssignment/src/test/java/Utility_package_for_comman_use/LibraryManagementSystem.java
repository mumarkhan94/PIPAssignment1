package Utility_package_for_comman_use;


import Login_and_register.Comman_variable;
import Read_write_file.Barrow_and_return;
import Read_write_file.Library_Book_list;
import Read_write_file.Register_users;


import java.io.IOException;
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



    public static void displayMainMenu() throws IOException {
        Register_users register_users = new Register_users();
        Barrow_and_return barrow_and_return = new Barrow_and_return();
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Search Books");
        System.out.println("5. Book List");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // TODO: Implement add book functionality
                register_users.Enter_book_detail();
                break;
            case 2: {// TODO: Implement borrow book functionality
                barrow_and_return.Barrow_and_Returns(-1);
                barrow_and_return.Add_Barrow_Book_details(Comman_variable.Username,Comman_variable.Book_name,Comman_variable.Isbn);

            }
                break;
            case 3:
                // TODO: Implement return book functionality
                break;
            case 4:
                // TODO: Implement search books functionality
                Search_Book_By();
                break;
            case 5:
            {
                Library_Book_list library_book_list=new Library_Book_list();
                library_book_list.Book_list();
                break;
            }
            case 6:
                System.out.println("Exiting the program. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                displayMainMenu();
        }
    }


    public static void Search_Book_By() throws IOException {
        Library_Book_list library_book_list=new Library_Book_list();
        System.out.println("\nSearch_Book_By:");
        System.out.println("1. Book title:");
        System.out.println("2. Author:");
        System.out.println("3. ISBN:");
        System.out.println("4. Genre:");
        System.out.println("5. Exit:");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Enter Book title:");
                library_book_list.Search_Book(0,scanner.next());
                break;
            case 2:
                System.out.println("Enter Author Name:");
                library_book_list.Search_Book(1,scanner.next());
                break;
            case 3:
                System.out.println("Enter ISBN:");
                library_book_list.Search_Book(2,scanner.next());
                break;
            case 4:
                System.out.println("Enter Genre:");
                library_book_list.Search_Book(3,scanner.next());
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

