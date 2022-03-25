import Utills.Utils;
import dataFile.User;
import dataFile.XlsxReader;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.CartPage;
import pages.LoginAuth;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {

    WebDriver driver = null;

    public void launch (WebDriver driver) throws IOException, InterruptedException, AWTException {

        this.driver = driver;

        driver.get("https://www.saucedemo.com/");

        Utils utils = new Utils();

        XlsxReader xlsxReader = new XlsxReader("src/main/java/dataFile/UsersDetails.xlsx");
        ArrayList<User> list = xlsxReader.readFile();

        LoginAuth loginAuth = new LoginAuth(driver, list.get(0));

        Thread.sleep(2000);

        selectHighestPrice();

        Thread.sleep(2000);

        clickAddToCart("btn btn_primary btn_small btn_inventory","btn btn_secondary btn_small btn_inventory");

        Thread.sleep(2000);

        CartPage cartPage = new CartPage(driver);

        cartPage.goToCart();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@id = 'continue-shopping']")).click();
        Thread.sleep(2000);

        selectLowestPrice();

        Thread.sleep(2000);

        int a = cartItemCount();
        System.out.println("items in home page "+a);

        cartPage.goToCart();
        Thread.sleep(2000);

        int b = cartItemCount();
        System.out.println("items in cart page "+ b);


        try {
            utils.takeScreenshot("C:\\Users\\ndhurandher\\Pictures\\Screenshorts\\","Shopping Cart");
            System.out.println("Take Screenshot");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        driver.findElement(By.xpath("//button[@id = 'checkout']")).click();

        Thread.sleep(2000);

        loginAuth.fillDetail();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@id = 'continue']")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@id = 'finish']")).click();

        Thread.sleep(2000);

        try {
            utils.takeScreenshot("C:\\Users\\ndhurandher\\Pictures\\Screenshorts\\","Shopping Recipient");
            System.out.println("Take Screenshot");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("End of program");



    }


    public void selectLowestPrice() throws InterruptedException {

        filterItem("lohi");
        Thread.sleep(2000);

        clickAddToCart("btn btn_primary btn_small btn_inventory","btn btn_secondary btn_small btn_inventory");

    }

    public void selectHighestPrice() throws InterruptedException {

        filterItem("hilo");
        Thread.sleep(2000);


        String priceStr = driver.findElement(By.xpath("//div[contains(@class,'inventory_item_price')]")).getText();
        String a = priceStr.replace("$","");
        String b = a.replace(".","");
        b = b.substring(0,b.length()-2);

        int price  = Integer.parseInt(b);
        System.out.println(price);

        if(price>100){
            driver.quit();
        }


    }

    private void clickAddToCart(String firstBtnClass, String secondBtnClass) throws InterruptedException {
        try {
            driver.findElement(By.xpath("(//button[contains(@class,'"+firstBtnClass+"')]) [" + 1 + "]")).click();
        }catch (ElementNotVisibleException e){
            System.out.println("element "+e.getMessage());
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[contains(@class,'"+secondBtnClass+"')]) [" + 1 + "]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[contains(@class,'"+firstBtnClass+"')]) [" + 1 + "]")).click();

        }catch (Exception e){
            System.out.println("e " + e.getMessage());
        }
    }

    public int cartItemCount(){
        String itemCountStr = driver.findElement(By.cssSelector("span[class*='shopping_cart_badge']")).getText();
        return Integer.parseInt(itemCountStr);

    }

    private void filterItem(String filter) {
        WebElement selectOption = driver.findElement(By.cssSelector("span[class*='select_container']"));
        selectOption.click();

        Select selectObj = new Select(driver.findElement(By.cssSelector("select[class*='product_sort_container']")));
        selectObj.selectByValue(filter);
    }
}

