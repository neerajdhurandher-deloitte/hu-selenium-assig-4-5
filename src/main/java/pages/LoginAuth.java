package pages;
import Utills.FindElement;
import dataFile.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginAuth {

    WebDriver driver;
    User user;

    public LoginAuth(WebDriver driver, User user){

        this.driver = driver;
        this.user = user;

        WebElement userNameInput = driver.findElement(By.xpath("//input[@id= 'user-name']"));
        WebElement passwordInput = driver.findElement(By.xpath("//input[@id= 'password']"));
        WebElement logInBtn = driver.findElement(By.xpath("//input[@id= 'login-button']"));


        System.out.println("userame : "+user.getUserName());
        System.out.println("password : "+user.getPassword());

        userNameInput.sendKeys(user.getUserName());
        passwordInput.sendKeys(user.getPassword());
        logInBtn.click();

    }

    public void fillDetail(){

        driver.findElement(By.xpath("//input[@id= 'first-name']")).sendKeys(this.user.getFirstName());
        driver.findElement(By.xpath("//input[@id= 'last-name']")).sendKeys(this.user.getLastName());
        driver.findElement(By.xpath("//input[@id= 'postal-code']")).sendKeys(this.user.getPinCode());
    }
}
