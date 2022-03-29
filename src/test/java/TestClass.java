import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestClass {

    public WebDriver driver;
    public Logger log;


    @BeforeTest
    public  WebDriver initializeSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ndhurandher\\Downloads\\chromedriver.exe");
        log = org.apache.log4j.Logger.getLogger(TestClass.class);
        log.info("Test initialized");
        driver = new ChromeDriver();
        return driver;
    }
//    @AfterTest
    public  void closeBrowser(){
        log.info("Web driver closed");
        driver.close();
    }

    @Test
    public  void startTest(){
        MainClass mainClass = new MainClass();
        try {
        mainClass.launch();
        Thread.sleep(2000);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        try {
            mainClass.logIn();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.highestItemAdd();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.selectLowestPrice();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.cartItems();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.checkoutProcess();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        driver.close();
    }

    @Test
    public  void startTest2(){
        MainClass mainClass = new MainClass();
        try {
            mainClass.launch();
            Thread.sleep(2000);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        try {
            mainClass.logIn();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.selectLowestPrice();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.highestItemAdd();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.cartItems();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            mainClass.checkoutProcess();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        driver.close();
    }
}
