package Base;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.chrome.ChromeOptions;
import utility.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
	public class basecucumber {
		public static  ExtentReports extent;
		   public static ExtentTest extent_report;
    public static WebDriver d;
    public static PDFReportGenerator pdfReport;
    @Before

    public void before_() throws IOException {
    	extent = ExtentManager.getInstance();
    	System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
    	ChromeOptions option =new ChromeOptions();
    	extent_report = extent.createTest("Tccucumber");
    	option.addArguments("--incognito");
    			option.addArguments("--disable-notifications");
    	 d=new ChromeDriver(option);
        d.manage().window().maximize();
        d.get("https://www.saucedemo.com");
        pdfReport = new PDFReportGenerator();
    
	
}
    @After
    public void _after( ) throws IOException {
        if (pdfReport != null) { 
        	String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        	  java.io.File directory = new java.io.File("reports/" +"Cucu"+"_"+timestamp);
              if (!directory.exists()) {
                  directory.mkdirs(); // Create directory (and parent directories if needed)
              }

        	            String pdfFilename ="reports/"+"Cucu"+"_"+timestamp+"/"+ "cucu" + "_" + timestamp + ".pdf";
        	            pdfReport.save(pdfFilename);
        }
        if (d != null) {
            d.quit();
            extent.flush();
        }
        
    }

}
