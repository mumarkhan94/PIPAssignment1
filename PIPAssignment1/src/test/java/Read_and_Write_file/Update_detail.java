package Read_and_Write_file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Update_detail {
	
	public static void Update(String nameToFind,String empId,String newDesignation) {
		 String filePath = ".\\DataFile\\Employe1.xlsx";
		 try {	
	    		FileInputStream inputstream = new FileInputStream(filePath);
	    		XSSFWorkbook workbook = new XSSFWorkbook(inputstream); // access the file
	    		XSSFSheet sheet = workbook.getSheet("Sheet1");

	            for (Row row : sheet) {
	                Cell nameCell = row.getCell(0);
	                Cell empidcell = row.getCell(1);
	                Cell designationCell = row.getCell(2);
	               
	                if (nameCell != null && nameCell.getStringCellValue().equals(nameToFind) && empidcell.getStringCellValue().equals(empId)) {
	                    designationCell.setCellValue(newDesignation);
	                    break;
	                }
	            }

	            inputstream.close();

	            FileOutputStream outFile = new FileOutputStream(filePath);
	            workbook.write(outFile);
	            outFile.close();

	            System.out.println("Designation updated successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}
	
	
	

	    public static void main(String[] args)throws IOException {
	    	
	       
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Enter the name to find: ");
	        String nameToFind = scanner.nextLine();
	        System.out.print("Enter the employee ID: ");
	        String empId = scanner.nextLine();
	        System.out.print("Enter the new designation: ");
	        String newDesignation = scanner.nextLine();
	        Update(nameToFind,empId,newDesignation);
	        


	       
	    }
	

	
	
	
}
