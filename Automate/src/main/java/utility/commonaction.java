package utility;
import javax.lang.model.element.Element;

import org.openqa.selenium.*;
public class commonaction {
 public static void clickelement(WebElement element)
 {
	 element.click();
 }
 public static void insertdata(WebElement element,String A)
 {
	 element.sendKeys(A);
 }
 public static void accept_alert(WebDriver d)
 {
	 (d.switchTo().alert()).accept();
 }
 public static void dismiss_alert(WebDriver d)
 {
	 d.switchTo().alert().dismiss();
 }
 public static void gotoalert(WebDriver d)
 {
	 d.switchTo().alert();
 }
 public static boolean isalertpresent(WebDriver d) {
 try {d.switchTo().alert();
	return true; 
 }
 catch (NoAlertPresentException e)
 {
	 return false;
 }}
}
