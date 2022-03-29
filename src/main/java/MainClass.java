import Utills.Utils;
import dataFile.User;
import dataFile.XlsxReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.CartPage;
import pages.LoginAuth;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass {

    WebDriver driver = null;
    Utils utils;
    Logger log;
    LoginAuth loginAuth;
    CartPage cartPage;


    public void launch() {

        driver = new ChromeDriver();
        log = Logger.getLogger(MainClass.class);

        driver.get("https://www.saucedemo.com/");

        utils = new Utils();

        cartPage = new CartPage(driver);


        log.info("web browser initialized");

    }

    public void logIn() throws InterruptedException, IOException {
        XlsxReader xlsxReader = new XlsxReader("src/main/java/dataFile/UsersDetails.xlsx");
        ArrayList<User> list = xlsxReader.readFile();

        loginAuth = new LoginAuth(driver, list.get(0));

        Thread.sleep(2000);
    }

    public void highestItemAdd() throws InterruptedException {
        selectHighestPrice();

        Thread.sleep(2000);

        clickAddToCart("btn btn_primary btn_small btn_inventory","btn btn_secondary btn_small btn_inventory");

        Thread.sleep(2000);

        cartPage.goToCart();

        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[@id = 'continue-shopping']")).click();
        Thread.sleep(2000);
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

    public void cartItems() throws InterruptedException {
        int a = cartItemCount();
        log.info("items in home page "+a);

        cartPage.goToCart();
        Thread.sleep(2000);

        int b = cartItemCount();
        log.info("items in cart page "+ b);

        takeScreenShort("Shopping","Shopping Recipient");
    }

    private void clickAddToCart(String firstBtnClass, String secondBtnClass) throws InterruptedException {
        try {
            driver.findElement(By.xpath("(//button[contains(@class,'"+firstBtnClass+"')]) [" + 1 + "]")).click();
        }catch (ElementNotVisibleException e){
            log.error("element "+e.getMessage());
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[contains(@class,'"+secondBtnClass+"')]) [" + 1 + "]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//button[contains(@class,'"+firstBtnClass+"')]) [" + 1 + "]")).click();

        }catch (Exception e){
            log.error("e " + e.getMessage());
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

    public void takeScreenShort(String folder, String fileName){
        try {
            utils.takeScreenshot("C:\\Users\\ndhurandher\\Pictures\\Screenshorts\\"+folder,fileName);
            log.info("Take Screenshot");
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    public void checkoutProcess() throws InterruptedException {
        driver.findElement(By.xpath("//button[@id = 'checkout']")).click();

        Thread.sleep(2000);

        loginAuth.fillDetail();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//input[@id = 'continue']")).click();

        Thread.sleep(3000);

        driver.findElement(By.xpath("//button[@id = 'finish']")).click();

        Thread.sleep(2000);

        takeScreenShort("Shopping","Shopping Recipient");
    }
}

