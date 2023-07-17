package Read_write_file;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.*;

import com.opencsv.CSVWriter;
public class Register_users {

    public static void Enterdata(Object[][] empdata) throws IOException {

        File myFile = new File(".\\DataFile\\Login_details.xlsx");
        FileInputStream inputStream = new FileInputStream(myFile);  // Select the file.
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        System.out.println(" wb: " + workbook);
        XSSFSheet sheet = workbook.getSheet("Sheet1");  // Select the sheet name.

        int rows = empdata.length;  // Get the number of rows present in sheet.
        int cols = empdata[0].length; // Get the number of column present in first row.
        int sheetrows = sheet.getLastRowNum(); // Get the number of row already present

        for (int r = 0; r < rows; r++) {  // This for loop is used for row iteration.
            XSSFRow row = sheet.createRow(r + sheetrows + 1);
            for (int c = 0; c < cols; c++) {  // This for loop is used for column iteration.
                XSSFCell cell = row.createCell(c);  // Create the new cell.
                Object value = empdata[r][c];

                if (value instanceof String)  //  If the value is String then this if condition will execute.
                    cell.setCellValue((String) value);
                if (value instanceof Integer) // If the value is integer then this if condition will execute.
                    cell.setCellValue((Integer) value);
            }

        }
        FileOutputStream os = new FileOutputStream(myFile);
        workbook.write(os);  // Write the data in sheet.
        inputStream.close(); // close the sheet.
        System.out.println("Data inserted");
    }

    public void fill_data() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String[] askusers = {"Username", "Password", "Select your role"};
        Object[][] arr = new Object[1][3]; //Object of array variable.
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                if(j==2){
                    System.out.println(askusers[j]);
                    System.out.println("1. Library staff");
                    System.out.println("2. Student");
                    boolean check_role_selection = false;
                    while (!check_role_selection) {
                        switch (scanner.nextInt()) {
                            case 1: {
                                arr[i][j] = "Library staff"; // Enter the data and storing the data in object of array.
                                check_role_selection = true;
                                break;
                            }
                            case 2: {
                                arr[i][j] = "Student"; // Enter the data and storing the data in object of array.
                                check_role_selection = true;
                                break;
                            }
                            default: {
                                System.out.println("Invalid option. Please try again.");
                            }
                        }
                    }
                 }else {
                    System.out.print("Enter the " + askusers[j]);
                    arr[i][j] = scanner.next(); // Enter the data and storing the data in object of array.
                }
            }
        }
        Enterdata(arr); // Calling function and passing object of array.
    }

}


