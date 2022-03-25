import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {

    public static WebDriver driver;

    @BeforeTest
    public  WebDriver initializeSetup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ndhurandher\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Web driver initialized");
        return driver;
    }
    @AfterTest
    public  void closeBrowser(){
        System.out.println("Web driver closed");
        driver.close();
    }

    @Test
    public  void startTest(){
        MainClass mainClass = new MainClass();
        try {
        mainClass.launch(driver);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
