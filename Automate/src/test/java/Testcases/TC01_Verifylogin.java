package Testcases;
import Base.Sheet;
import Base.Usertype;
import Base.PDFReportGenerator;
import Base.StepLogger;
import Base.readexceldata;
import Steps.commonsteps;
import java.lang.reflect.Method;
import java.io.IOException;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;

public class TC01_Verifylogin extends Base.basetest{
	@Test(dataProvider = "getexceldata", dataProviderClass = readexceldata.class)
	@Sheet("Sauce")
	@Usertype({"Standard user","locked user"})
	public void TC01_Verifylogin(String username, String password) throws Exception
	{
		pdfReport.addCoverPage("TC01: Verify User Login", "This test case verifies that login functionality works correctly using valid and invalid credentials.");
		 commonsteps c=new commonsteps(d,pdfReport);
		commonsteps.verifyloginpage();  
	commonsteps.verifylogin(username,password);

	extent_report.info("Starting test: verifyValidLogin");
    if (commonsteps.b) {
    	pdfReport.addstatustocoverpage("Pass");
        extent_report.pass("Login successful");
    } else {  pdfReport.addstatustocoverpage("Fail");
    	extent_report.fail("Login failed").addScreenCaptureFromPath(commonsteps.screenshotpath.get(0));
    }Assert.assertTrue(commonsteps.b);
	}
}
