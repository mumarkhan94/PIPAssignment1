package Read_write_file;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class Login_users {

    public static int Login(String Username, String Password) throws IOException {
        // Its return zero when user is unauthorized.
        // If user is student then its return one.
        // If user is staff then it will return two.
        int user_status = 0;
        String excelFilePath = ".\\DataFile\\Login_details.xlsx";
        FileInputStream inputstream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputstream); // Access the file
        XSSFSheet sheet = workbook.getSheet("Sheet1"); // Select the first sheet with the help of sheet name
        int rows = sheet.getLastRowNum(); // Get the number of row
        for (int r = 1; r <= rows; r++) // First for loop representing the row.
        {
            XSSFRow row = sheet.getRow(r); // Accessing row one by one.
            XSSFCell username_form_cell1 = row.getCell(0); // This line is used for accessing the username column.
            String user = username_form_cell1.getStringCellValue(); // Assign the username value in local variable so we can compare.

            XSSFCell password_form_cell1 = row.getCell(1); // This line is used for accessing the password column.
            String pass = password_form_cell1.getStringCellValue(); // Assign the password value in local variable so we can compare.

            if (user.equals(Username) && pass.equals(Password)) {  // r == 0 is used for to print the first line from csv.
                XSSFCell roll_form_cell1 = row.getCell(2); // This line is used for accessing the role column.
                String roll = roll_form_cell1.getStringCellValue(); // Assign the role value in local variable so we can compare.
                if (roll.equals("Library staff"))
                    user_status = 2;
                else
                    user_status = 1;
                break;
            }
        }
        return user_status;
    }
}
