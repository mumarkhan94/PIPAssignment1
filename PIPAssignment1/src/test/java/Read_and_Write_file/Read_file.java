package Read_and_Write_file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.*;

public class Read_file {

	private static final int STRING = 1;
	private static final int NUMERIC = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String excelFilePath = ".\\DataFile\\Employe1.xlsx";
		FileInputStream inputstream = new FileInputStream(excelFilePath);

		XSSFWorkbook workbook = new XSSFWorkbook(inputstream); // Access the file

		XSSFSheet sheet = workbook.getSheet("Sheet1"); // Select the first sheet with the help of sheet name

		int rows = sheet.getLastRowNum(); // Get the number of row
		int cols = sheet.getRow(1).getLastCellNum(); // Get the number of column.

		for (int r = 0; r <= rows; r++) // First for loop representing the row.
		{
			XSSFRow row = sheet.getRow(r); // Accessing row one by one.
			for (int c = 0; c < cols; c++) // Second for loop representing the column.
			{
				XSSFCell cell = row.getCell(c); // Accessing the column one by one.
				XSSFCell gender = row.getCell(3); // This line is used for accessing the gender column.
				String g = gender.getStringCellValue(); // Assign the gender value in local variable so we can compare.
				if (g.toLowerCase().equals("f") || r == 0) {  // r == 0 is used for to print the first line from csv.
					switch (cell.getCellType()) {
					case STRING: { // This switch case will execute when the cell data is string.
						System.out.print(cell.getStringCellValue() + " | ");
						if (cell.getStringCellValue().toLowerCase().equals("f")  // Comparing gender.
								|| cell.getStringCellValue().toLowerCase().equals("gender"))
							System.out.println();
						break;
					}
					case NUMERIC: // This switch case will execute when the cell data is integer.
						System.out.print(cell.getNumericCellValue() + " | ");
						break;
					}
				}
			}

		}
	}

}
