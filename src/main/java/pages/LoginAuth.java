package pages;
import Utills.FindElement;
import dataFile.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginAuth {

    public LoginAuth(WebDriver driver, User user){

        FindElement element = new FindElement(driver);

        WebElement userNameInput = driver.findElement(By.xpath("//input[@id= 'user-name']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id= 'password']"));
        WebElement logInBtn = driver.findElement(By.xpath("//input[@id= 'login-button']"));


        System.out.println("userame : "+user.getUserName());
        System.out.println("password : "+user.getPassword());

        userNameInput.sendKeys(user.getUserName());
        passwordInput.sendKeys(user.getPassword());
        logInBtn.click();

    }
}
