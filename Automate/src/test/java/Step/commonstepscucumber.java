package Step;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import pagelements.homepage;
import pagelements.loginpage;
import utility.commonaction;
import Base.PDFReportGenerator;
import Base.StepLogger;
import Base.basecucumber;
import Base.basetest;
import static org.junit.Assert.*;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;

public class commonstepscucumber{

    WebDriver driver=basecucumber.d;
    StepLogger stepLogger;
    PDFReportGenerator pdfReport =basecucumber.pdfReport;
    public static ArrayList<String> screenshotpath = new ArrayList<>();

    @Given("the user is on the login page")
    public void verifyLoginPage() throws Exception {
        screenshotpath.clear();
        stepLogger = new StepLogger(driver, pdfReport);
        try {	basecucumber.extent_report.info("Starting test: verifyValidLogin");
            WebElement homepageHeader = driver.findElement(loginpage.loginpage);
            if (homepageHeader.isDisplayed()) {
                stepLogger.capturestep("Verify login page is displayed", "login page should be displayed", "Login Page is displayed", "PASS");
                pdfReport.addstatustocoverpage("Pass");
                basecucumber.extent_report.pass("Login successful");
            assertTrue(homepageHeader.isDisplayed());} else {
                screenshotpath.add(stepLogger.capturestep("Verify login page is displayed", "login page should be displayed", "Login Page is not displayed", "FAIL"));
            fail("Failed");
            }
        } catch (Exception e) {
            stepLogger.capturestep("Verify login page is displayed", "Login page should be displayed", "Login Page is not displayed", "FAIL");
        fail();
        }
    }

    @Then("the user should be redirected to the Homepage after giving valid login credentials {string} and {string}")
    public void verifyLogin(String username, String password) throws Exception {
        stepLogger = new StepLogger(driver, pdfReport);
        screenshotpath.clear();
        try {
            WebElement userField = driver.findElement(loginpage.username);
            WebElement passField = driver.findElement(loginpage.password);
            WebElement loginBtn = driver.findElement(loginpage.loginbox);

            commonaction.insertdata(userField,username);
            commonaction.insertdata(passField,password);
            commonaction.clickelement(loginBtn);

            if (commonaction.isalertpresent(driver)) {
                commonaction.accept_alert(driver);
            }

            WebElement homepageHeader = driver.findElement(loginpage.Homepage);
            if (homepageHeader.isDisplayed()) {
                stepLogger.capturestep("Verify user is able to login", "User should be able to login", "User is able to login", "Status: PASS");
            
            assertTrue(homepageHeader.isDisplayed());} else {
                stepLogger.capturestep("Verify user is able to login", "User should be able to login", "User is not able to login", "Status: Fail");
           fail(); }

        } catch (Exception e) {
            screenshotpath.add(stepLogger.capturestep("Verify user is able to login", "User should be able to login", "User is not able to login", "Status: Fail"));
       fail(); }
    }

}
