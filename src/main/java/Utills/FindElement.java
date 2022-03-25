package Utills;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindElement {

    private WebElement element = null;
    WebDriver driver = null;

    public FindElement(WebDriver driver){
        this.driver = driver;
    }

    public WebElement spanXpathClass(String className){
        element =  driver.findElement(By.xpath("(//button[contains(@class,')"+className+"')]"));
        return element;
    }
    public WebElement spanCssClass(String className){
        element = driver.findElement(By.cssSelector("span[class*="+className+"]"));
        return element;
    }
    public WebElement inputXpathTag(String tag ,String id){
        element = driver.findElement(By.xpath("//"+tag+"[@id= "+id+"]"));
        return element;
    }

}
