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

		XSSFWorkbook workbook = new XSSFWorkbook(inputstream); // access the file

		XSSFSheet sheet = workbook.getSheet("Sheet1"); // select the first sheet with the help of sheet name

		int rows = sheet.getLastRowNum(); // get the number of row
		int cols = sheet.getRow(1).getLastCellNum(); // get the number of column

		for (int r = 0; r <= rows; r++) // First for loop representing the row
		{
			XSSFRow row = sheet.getRow(r);
			for (int c = 0; c < cols; c++) // Second for loop representing the column
			{
				XSSFCell cell = row.getCell(c);
				XSSFCell gender = row.getCell(3);
				String g = gender.getStringCellValue();
				if (g.toLowerCase().equals("f") || r == 0) {
					switch (cell.getCellType()) {
					case STRING: {
						System.out.print(cell.getStringCellValue() + " | ");
						if (cell.getStringCellValue().toLowerCase().equals("f")
								|| cell.getStringCellValue().toLowerCase().equals("gender"))
							System.out.println();
						break;
					}
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + " | ");
						break;
					}
				}
			}

		}
	}

}
