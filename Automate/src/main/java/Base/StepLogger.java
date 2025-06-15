package Base;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class StepLogger {
   private PDFReportGenerator pdfreport;
   private WebDriver d;
   public static String screenshotpath;
public StepLogger(WebDriver d, PDFReportGenerator pdfreport){
	this.d=d;
	this.pdfreport=pdfreport;
}
public String capturestep(String objective, String expected, String actual, String status) throws IOException
{
	 File screenshotfile=((TakesScreenshot)d).getScreenshotAs(OutputType.FILE);
	  screenshotpath="screenshots/" + System.currentTimeMillis()+".png";
		 String relativepath="test-output/"+screenshotpath;
	 FileUtils.copyFile(screenshotfile, new  File(screenshotpath));
	 FileUtils.copyFile(screenshotfile, new  File(relativepath));
	 pdfreport.addStep(objective, expected, actual, status, screenshotpath);
	 return screenshotpath;
}
}
   