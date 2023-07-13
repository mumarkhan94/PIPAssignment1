package Read_and_Write_file;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.*;

import com.opencsv.CSVWriter;

public class Write_data_to_csv {
	
	public static void Enterdata(Object[][] empdata) throws IOException {

		File myFile = new File(".\\DataFile\\Employe1.xlsx");
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

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Take the input.
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();  // Take number of row.
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt(); // Take number of cloumn.
        Object[][] arr = new Object[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Enter the value for row " + (i + 1) + ", column " + (j + 1) + ": ");
                arr[i][j] = scanner.next(); // Enter the data and storing the data in object of array.
            }
        }
        // Print the data enter by user.
        System.out.println("The data entered is:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        Enterdata(arr); // Calling function and passing object of array.
        
    }

	
	



}