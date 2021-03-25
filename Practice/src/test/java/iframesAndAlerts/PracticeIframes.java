package iframesAndAlerts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class PracticeIframes {

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
    public void test1(){

        driver.navigate().to("https://demoqa.com/frames");
        //before checking if text1 is displayed we need to switch to the iframe that contains it


        //1 Option - sith to iframe by webelement
        WebElement iframe1 = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(iframe1);
        WebElement text1 = driver.findElement(By.id("sampleHeading"));

        Assert.assertTrue(text1.isDisplayed());

        driver.switchTo().defaultContent();

        //2 Option - switch to iframe by name or id
        //driver.switchTo().frame("frame2");

        //3 Option - switch to iframe by index
        driver.switchTo().frame(1);
        WebElement text2 = driver.findElement(By.id("sampleHeading"));

        Assert.assertTrue(text2.isDisplayed());

    }

    @Test
    public void test2(){
        driver.navigate().to("https://leetcode.com/");
        Actions actions = new Actions(driver);

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='playground-iframe']"))).perform();


        WebElement iframe = driver.findElement(By.xpath("//iframe[@height='400']"));

        driver.switchTo().frame(iframe);

        WebElement runButton = driver.findElement(By.xpath("//button[contains(@class,'btn-success')]"));

        runButton.click();

        //WebDriverWait wait = new WebDriverWait()
        WebElement runResultMessage = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='success result']")));

        String text = runResultMessage.getText();//Finished in 4 ms  --> String[] arr = {Finished, in, 4, ms}

        for (String word: text.split(" ")) {
            if(Character.isDigit(word.charAt(0))){
                int ms = Integer.parseInt(word);
                Assert.assertTrue(ms < 5);
            }
        }
    }


}
