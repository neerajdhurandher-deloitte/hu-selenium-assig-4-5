package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public void goToCart(){
        try {
            driver.findElement(By.xpath("//div[@id = 'shopping_cart_container']")).click();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
