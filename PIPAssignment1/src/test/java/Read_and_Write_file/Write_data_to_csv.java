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
		FileInputStream inputStream = new FileInputStream(myFile);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		System.out.println(" wb: " + workbook);
		XSSFSheet sheet = workbook.getSheet("Sheet1");

		// its contain all type of data
//		Object empdata[][] = { { "salamna", "09654", "QA", "m" }, { "sofiya", "342524", "SDET2", "f" } };
		int rows = empdata.length;
		int cols = empdata[0].length;
		int sheetrows = sheet.getLastRowNum(); // Get the number of row already present

		for (int r = 0; r < rows; r++) {
			XSSFRow row = sheet.createRow(r + sheetrows + 1);
			for (int c = 0; c < cols; c++) {
				XSSFCell cell = row.createCell(c);
				Object value = empdata[r][c];

				if (value instanceof String)
					cell.setCellValue((String) value);
				if (value instanceof Integer)
					cell.setCellValue((Integer) value);
			}

		}

		FileOutputStream os = new FileOutputStream(myFile);
		workbook.write(os);
		inputStream.close();
		System.out.println("Data inserted");
	
	
	
	
}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();
        Object[][] arr = new Object[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print("Enter the value for row " + (i + 1) + ", column " + (j + 1) + ": ");
                arr[i][j] = scanner.next();
            }
        }
        
        System.out.println("The data entered is:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        Enterdata(arr);
        
    }

	
	



}