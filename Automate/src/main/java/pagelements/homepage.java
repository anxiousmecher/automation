package pagelements;
import org.openqa.selenium.By;
public class homepage {
public static final By product1 = (By.xpath("(//div[(@class='inventory_item_name ')])[1]"));
public static final By product1_Addtocart = (By.xpath("(//button[(@class='btn btn_primary btn_small btn_inventory ')])[1]"));
public static final By Addtocart=By.xpath("//div[@class='shopping_cart_container']//a");
public static final By Addtocart_product=By.xpath("//div[@class='inventory_item_name']");
public static final By Addtocartcount=By.xpath("//span[@class='shopping_cart_badge']");
}
