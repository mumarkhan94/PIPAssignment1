package Utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadDataFile {

    public static Object[][] read_data()throws IOException {
        // TODO Auto-generated method stub
        String excelFilePath = ".\\DataFile\\Login_data.xlsx";
        FileInputStream inputstream = new FileInputStream(excelFilePath);



        XSSFWorkbook workbook = new XSSFWorkbook(inputstream); // Access the file

        XSSFSheet sheet = workbook.getSheet("Sheet1"); // Select the first sheet with the help of sheet name

        int rows = sheet.getLastRowNum(); // Get the number of row
        int cols = sheet.getRow(2).getLastCellNum(); // Get the number of column.
        Object[][] loginData = new Object[rows-1][cols];
        for (int r = 1; r <rows; r++) // First for loop representing the row.
        {
            XSSFRow row = sheet.getRow(r); // Accessing row one by one.
            for (int c = 0; c < cols; c++) // Second for loop representing the column.
            {
                XSSFCell cell = row.getCell(c); // Accessing the column one by one.
                XSSFCell gender = row.getCell(c); // This line is used for accessing the gender column.
                String g = gender.getStringCellValue(); // Assign the gender value in local variable so we can compare.
                loginData[r-1][c] = g;
            }

        }
        return loginData;
    }

}
