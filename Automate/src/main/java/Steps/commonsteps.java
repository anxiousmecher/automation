package Steps;
import utility.commonaction;
import io.cucumber.java.en.*;
import pagelements.homepage;
import pagelements.loginpage;
import io.cucumber.java.en.*;
import java.util.ArrayList;

import org.openqa.selenium.*;

import Base.PDFReportGenerator;
import Base.StepLogger;
public class commonsteps {
	 //static PDFReportGenerator pdfReport;
	private static PDFReportGenerator pdfReport;
	private static WebDriver d;
	public  commonsteps(WebDriver d,PDFReportGenerator pdfReport)
	{
		this.pdfReport=pdfReport;
		this.d=d;
	}
	    static StepLogger stepLogger;
	public static boolean b;
	public static ArrayList<String> screenshotpath=new ArrayList<>();
	public static void verifyloginpage() throws Exception{
	screenshotpath.clear();
		    try {
				stepLogger = new StepLogger(d, pdfReport);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    b = false;
		    try {	        WebElement homepageHeader = d.findElement(loginpage.loginpage);
		    if (homepageHeader.isDisplayed()) {
	            stepLogger.capturestep("Verify login page is displayed", "login page should be displayed", "Login Page is displayed", "PASS");
	            b = true;
	        } else {
	            screenshotpath.add(stepLogger.capturestep("Verify login page is displayed", "login page should be displayed", "Login Page is not displayed", "FAIL"));
	        
	        }
		    } catch (NoSuchElementException e) {
		        stepLogger.capturestep("Verify login page is displayed", "Login page should be displayed","Login Page is not displayed", "FAIL");
		    } catch (Exception e) {
		        stepLogger.capturestep("Verify login page is displayed", "Login page should be displayed","Login Page is not displayed", "FAIL");
		    }
	}
	public static void verifylogin(String usernames, String passwords) throws Exception {
	    //pdfReport = new PDFReportGenerator();
	    stepLogger = new StepLogger(d, pdfReport);
	    b = false;
	    screenshotpath.clear();
	    try {
	        WebElement userField = d.findElement(loginpage.username);
	        WebElement passField = d.findElement(loginpage.password);
	        WebElement loginBtn = d.findElement(loginpage.loginbox);

	        commonaction.insertdata(userField, usernames);
	        commonaction.insertdata(passField, passwords);
	        commonaction.clickelement(loginBtn);
	        if(commonaction.isalertpresent(d))
	        {System.out.println("Alert was present");
	       commonaction.accept_alert(d);
	        }
	        WebElement homepageHeader = d.findElement(loginpage.Homepage);
	        if (homepageHeader.isDisplayed()) {
	            stepLogger.capturestep("Verify user is able to login", "User should be able to login", "User is able to login", "Status: PASS");
	            b = true;
	        } else {
	            System.out.println((stepLogger.capturestep("Verify user is able to login", "User should be able to login", "User is not able to login", "Status: Fail")));
	        }

	    } catch (NoSuchElementException e) {
	    	 screenshotpath.add((stepLogger.capturestep("Verify user is able to login", "User should be able to login", "User is not able to login", "Status: Fail")));
	    } catch (Exception e) {
	        stepLogger.capturestep("Verify user is able to login", "User should be able to login", "User is not able to login", "Status: Fail");
	    } 
	}
	public static void verify_user_can_add_to_cart() throws Exception
	{screenshotpath.clear();
		stepLogger = new StepLogger(d, pdfReport);
		b=false;
		try {commonaction.clickelement(d.findElement(homepage.product1_Addtocart));
		int product_count=Integer.parseInt(d.findElement(homepage.Addtocartcount).getText());
		
			if(product_count==1)
			{
				stepLogger.capturestep("Verify user is able to add  product to cart", "User should be able to add product to cart", "User is able to add product to cart", "Status: PASS");
	            b = true;
	        } else {
	            stepLogger.capturestep("Verify user is able to add product to cart", "User should be able to add product to cart", "User is not able to add product to cart", "Status: Fail");
	        }
		}
				
				catch (NoSuchElementException e)  {
					screenshotpath.add(stepLogger.capturestep("Verify user is able to add product to cart", "User should be able to add product to cart", "User is not able to add product to cart", "Status: Fail"));
			    } catch (Exception e) {
			    	stepLogger.capturestep("Verify user is able to add product to cart", "User should be able to add product to cart", "User is not able to add product to cart", "Status: Fail");
			    } 
		}
	}
