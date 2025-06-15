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
	public class basetest {
		   protected ExtentReports extent;
		    protected ExtentTest extent_report;
    public static WebDriver d;
    protected PDFReportGenerator pdfReport;
    @BeforeMethod

    public void setUp(Method method) throws IOException {
    	extent = ExtentManager.getInstance();
    	System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
    	ChromeOptions option =new ChromeOptions();
    	extent_report = extent.createTest(method.getName());
    	option.addArguments("--incognito");
    			option.addArguments("--disable-notifications");
    	 d=new ChromeDriver(option);
        d.manage().window().maximize();
        d.get("https://www.saucedemo.com");
        pdfReport = new PDFReportGenerator();
    
	
}
    @AfterMethod
    public void tearDown(Method method) throws IOException {
        if (pdfReport != null) { 
        	String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        	  java.io.File directory = new java.io.File("reports/" + method.getName()+"_"+timestamp);
              if (!directory.exists()) {
                  directory.mkdirs(); // Create directory (and parent directories if needed)
              }

        	            String pdfFilename ="reports/"+method.getName()+"_"+timestamp+"/"+ method.getName() + "_" + timestamp + ".pdf";
        	            pdfReport.save(pdfFilename);
        }
        if (d != null) {
            d.quit();
        }
        
    }
    @AfterSuite
    public void tearDownExtentReport() {
        extent.flush();
    }
}
