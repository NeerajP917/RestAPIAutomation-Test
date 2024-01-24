package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility 
{
	
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet worksheet;
	public XSSFCell cell;
	public XSSFRow row;
	public CellStyle style;
	String Path;
	
	public XLUtility(String Path) 
	{
		this.Path=Path;
	}

	public int getRowCount(String SheetName) throws IOException
	{
		fis= new FileInputStream(Path);
		workbook=new XSSFWorkbook(fis);
		worksheet=workbook.getSheet(SheetName);
		int rowcount=worksheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcount;
	}
	
	public int getCellCount(String SheetName, int rownumber) throws IOException
	{
		fis= new FileInputStream(Path);
		workbook=new XSSFWorkbook(fis);
		worksheet=workbook.getSheet(SheetName);
		row=worksheet.getRow(rownumber);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcount;
	}
	
	public String getCellData(String SheetName, int rownumber, int ColumnNumber) throws IOException 
	{
		fis= new FileInputStream(Path);
		workbook=new XSSFWorkbook(fis);
		worksheet=workbook.getSheet(SheetName);
		
		row=worksheet.getRow(rownumber);
		cell=row.getCell(ColumnNumber);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try
		{
			data=formatter.formatCellValue(cell); //Return the data value in string.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fis.close();
		return data;
		
	}
	
	public String setCellData(String SheetName, int rownumber, int ColumnNumber, String data) throws IOException 
	{
		
		File XFile=new File(Path);
		if(!XFile.exists()); //Create new file if does not exist
          {
        	   workbook=new XSSFWorkbook();
        	   fos = new FileOutputStream(Path);
        	   workbook.write(fos);
          }
          
          fis= new FileInputStream(Path);
  		  workbook=new XSSFWorkbook(fis);
  		  
  		  if(workbook.getSheetIndex(worksheet)==1) //If sheet does not exist then create new sheet
  		  {
  			  workbook.createSheet(SheetName);
  			  worksheet=workbook.getSheet(SheetName);
  		  }
  		
  		  if(worksheet.getRow(rownumber)==null) //If row does not exist then create new row
  		  {
  			worksheet.createRow(rownumber);
  			row=worksheet.getRow(rownumber);
  			
  			cell=row.createCell(ColumnNumber);
  			cell.setCellValue(data);
  			fos = new FileOutputStream(Path);
  			workbook.write(fos);
  			workbook.close();
  			fis.close();
  			fos.close();
  			
  		  }
  		
		return data;
	
		
		
	}

	
	
	
}
