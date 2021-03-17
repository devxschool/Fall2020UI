package pageNavigation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.concurrent.TimeUnit;

public class PracticeNavigation {

    WebDriver driver;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
    }

    @After
    public void tearDown(){
        Driver.closeDriver();
    }


    @Test
    public void practicePageNavigation(){

        //same as driver.get()
        driver.navigate().to("http://www.practiceselenium.com/");

        WebElement teaLink = driver.findElement(By.xpath("//a[contains(@data-title, 'Talk Tea')]"));

        teaLink.click();

        //here we would expect to get StaleElementException

        teaLink = driver.findElement(By.xpath("//a[contains(@data-title, 'Talk Tea')]"));
        WebElement parentOfTeaLink = teaLink.findElement(By.xpath("./.."));

        Assert.assertTrue(parentOfTeaLink.getAttribute("class").equals("active"));

        //to navigate back
        driver.navigate().back();

        //verify welcome is selcted by default

        WebElement welcomeLink = driver.findElement(By.xpath("//a[@data-title='Welcome']/.."));

        Assert.assertTrue(welcomeLink.getAttribute("class").equals("active"));

        //navigate forward

        driver.navigate().forward();
        //this line will fail because parentOfTeaLink needs to be located again
        //Assert.assertTrue(parentOfTeaLink.getAttribute("class").equals("active"));

        WebElement nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        nameInput.sendKeys("DevX");

        driver.navigate().refresh();
        nameInput = driver.findElement(By.xpath("//input[@name='name']"));
        nameInput.sendKeys(" School");


    }


}
