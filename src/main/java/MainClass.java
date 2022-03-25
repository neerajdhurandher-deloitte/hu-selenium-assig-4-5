import dataFile.User;
import dataFile.XlsxReader;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import pages.LoginAuth;

import java.io.IOException;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\ndhurandher\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.saucedemo.com");

        XlsxReader xlsxReader = new XlsxReader("src/main/java/dataFile/UsersDetails.xlsx");
        ArrayList<User> list = xlsxReader.readFile();

        LoginAuth loginAuth = new LoginAuth(driver, list.get(0));

        Thread.sleep(2000);

        selectHighestPrice(driver);


    }

    public static void selectHighestPrice(WebDriver driver) throws InterruptedException {

        filterItem(driver,"hilo");
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

        clickAddToCart(driver,"btn btn_primary btn_small btn_inventory","btn btn_secondary btn_small btn_inventory");

        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id = 'shopping_cart_container']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@id = 'continue-shopping']")).click();
        Thread.sleep(2000);

        filterItem(driver,"lohi");
        Thread.sleep(2000);

        clickAddToCart(driver,"btn btn_primary btn_small btn_inventory","btn btn_secondary btn_small btn_inventory");






    }

    private static void clickAddToCart(WebDriver driver, String firstBtnClass, String secondBtnClass) throws InterruptedException {
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

    private static void filterItem(WebDriver driver ,String filter) {
        WebElement selectOption = driver.findElement(By.cssSelector("span[class*='select_container']"));
        selectOption.click();

        Select selectObj = new Select(driver.findElement(By.cssSelector("select[class*='product_sort_container']")));
        selectObj.selectByValue(filter);
    }
}

