package Read_write_file;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import Login_and_register.Comman_variable;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Barrow_and_return {

    private static final int STRING = 1;
    private static final int NUMERIC = 0;
    public static void Update_book(String Bookname, String Isbn,int inc_or_dec) {
        String filePath = ".\\DataFile\\Book_details.xlsx";
        try {
            FileInputStream inputstream = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(inputstream); // Access the file
            XSSFSheet sheet = workbook.getSheet("Sheet1"); // Select the first sheet.

            for (Row row : sheet) {
                Cell nameCell = row.getCell(0);  // Get the name.
                Cell empidcell = row.getCell(2); // Get Empid.
                Cell Availability_status = row.getCell(4); // Get the designation.

                switch (Availability_status.getCellType()) {
                    case STRING: { // This switch case will execute when the cell data is string.
                        if (nameCell != null && nameCell.getStringCellValue().equals(Bookname) && empidcell.getStringCellValue().equals(Isbn)) {
                            int Availabil = Integer.parseInt(Availability_status.getStringCellValue());
                            if(inc_or_dec == -1)
                            Availability_status.setCellValue(Availabil-1);
                            else
                                Availability_status.setCellValue(Availabil+1);
                            }
                        break;
                    }
                    case NUMERIC: // This switch case will execute when the cell data is integer.
                    {
                        if (nameCell != null && nameCell.getStringCellValue().equals(Bookname) && empidcell.getStringCellValue().equals(Isbn)) {
                            int Availabil = (int)Availability_status.getNumericCellValue();
                            if(inc_or_dec == -1)
                                Availability_status.setCellValue(Availabil-1);
                            else
                                Availability_status.setCellValue(Availabil+1);
                        }
                    }
                        break;
                }
                // Compare if the name and empid is match then update the designation.
            }

            inputstream.close();

            FileOutputStream outFile = new FileOutputStream(filePath);
            workbook.write(outFile);  // Write the data in sheet.
            outFile.close(); // Close the sheet.

            System.out.println("Books Borrow.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void Add_Barrow_Book_details(String Username,String Bookname, String Isbn) throws IOException {

        File myFile = new File(".\\DataFile\\Barrow_book_details.xlsx");
        String[] askusers = {"Username", "Password", "Select your role"};
        Object[][] arr = new Object[1][3]; //Object of array variable.
        arr[0][0]= Username;
        arr[0][1]= Bookname;
        arr[0][2]= Isbn;
        Register_users register_users = new Register_users();
        register_users.Enterdata(arr,myFile);
    }

    public static void Barrow_and_Returns(int inc_or_dec)throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Book name to find: ");
        Comman_variable.Book_name  = scanner.nextLine();
        System.out.print("Enter the Book ISBN: ");
        Comman_variable.Isbn = scanner.nextLine();
        Update_book(Comman_variable.Book_name,Comman_variable.Isbn,inc_or_dec);

    }

}
