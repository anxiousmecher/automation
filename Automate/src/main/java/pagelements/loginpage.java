package pagelements;
import org.openqa.selenium.WebElement;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class loginpage {
public static final By username=(By.xpath("//div[contains(@class,'form_group')]//input[contains(@placeholder,'Username')]"));
public static final By password=(By.xpath("//div[contains(@class,'form_group')]//input[contains(@placeholder,'Password')]"));
public static final By loginbox=(By.xpath("//div[contains(@class,'login-box')]//input[contains(@id,'login-button')]"));
public static final By Homepage=(By.xpath("//div[contains(@class,'app_logo')]"));
public static final By loginpage=(By.xpath("//div[(@class='login_logo')]"));
}
