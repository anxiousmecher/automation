package Testcases;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import Base.Sheet;
import Base.Usertype;
import Base.basetest;
import Base.readexceldata;
import Steps.commonsteps;
public class TC02_Add_Products extends basetest {
@Test(dataProvider="getexceldata", dataProviderClass=readexceldata.class)
@Sheet("Sauce")
@Usertype("Standard User")
public void TC02_Add_Products (String username, String password) throws Exception
{	commonsteps c=new commonsteps(d,pdfReport);
	pdfReport.addCoverPage("TC02: Verify Add Products", "This test case verifies that Valid user can add new product to cart & products are added to the cart");
	commonsteps.verifyloginpage();
Thread.sleep(500);
	commonsteps.verifylogin(username,password);
	Thread.sleep(500);
	commonsteps.verify_user_can_add_to_cart();
	extent_report.info("Starting test: verify_ADD_Products");
    if (commonsteps.b) {pdfReport.addstatustocoverpage("Pass");
        extent_report.pass("Product add successful");
    } else {pdfReport.addstatustocoverpage("Fail");
    	extent_report.fail("Failed").addScreenCaptureFromPath(commonsteps.screenshotpath.get(0));
    }
	Assert.assertTrue(commonsteps.b);
}

}
