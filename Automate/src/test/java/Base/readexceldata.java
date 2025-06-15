package Base;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

import Base.Sheet;
import Base.Usertype;
public class readexceldata{
	@DataProvider(name="getexceldata")
	public static Object[][] getexceldata(Method testmethod) throws IOException{
		String sheetname;
		ArrayList<Object[]> list=new ArrayList<>();
		String Testcasename=testmethod.getName();
	Sheet  annotationsheet=testmethod.getAnnotation(Sheet.class);	
	if(annotationsheet!=null)
	{
		 sheetname=annotationsheet.value();
	}
	else {
		sheetname="default";
	}
	String[] usertype;
	Usertype annotationusertype=testmethod.getAnnotation(Usertype.class);
	if(annotationusertype!=null)
	{
		usertype=annotationusertype.value();
	}
	else
	{
		usertype=new String[0];
	}
	try(FileInputStream fis=new FileInputStream("input//inputdata.xlsx");
			XSSFWorkbook workbook=new XSSFWorkbook(fis)) {
				org.apache.poi.ss.usermodel.Sheet sheet=workbook.getSheet(sheetname);
				Row headerrow=sheet.getRow(0);
				int tcnamecol=-1, usernamecol=-1,userpasswordcol=-1,usertypecol=-1;
				for(Cell tcell:headerrow)
				{
					if(tcell.getStringCellValue().equalsIgnoreCase("TestCaseName"))
					{
						tcnamecol=tcell.getColumnIndex();					}
				else if(tcell.getStringCellValue().equalsIgnoreCase("Username"))
				{
					usernamecol=tcell.getColumnIndex();					}
	else if(tcell.getStringCellValue().equalsIgnoreCase("Password"))
	{
		userpasswordcol=tcell.getColumnIndex();					}
	else if(tcell.getStringCellValue().equalsIgnoreCase("Usertype"))
	{
		usertypecol=tcell.getColumnIndex();					}
}
				if(tcnamecol==-1||usernamecol==-1||userpasswordcol==-1||usertypecol==-1)
				{
					throw new RuntimeException("No such header column");
				}
				for(int i=1;i<=sheet.getLastRowNum();i++)
				{
					Row row=sheet.getRow(i);
					if(row==null) continue;
					if((row.getCell(tcnamecol)).getStringCellValue().equalsIgnoreCase(Testcasename))
					{
						for(int j=0;j<usertype.length;j++)
						{
							if((row.getCell(usertypecol)).getStringCellValue().equalsIgnoreCase(usertype[j]))
									{
								String username=row.getCell(usernamecol).getStringCellValue();
								String password=row.getCell(userpasswordcol).getStringCellValue();			
									
								list.add(new Object[] {username,password});
									}
								
									}
						}
					}
				}
	catch (Exception e){
		e.printStackTrace();
			} 
	return (list.toArray(new Object[0][0]));
	}	
}