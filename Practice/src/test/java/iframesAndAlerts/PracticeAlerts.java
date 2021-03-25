package iframesAndAlerts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class PracticeAlerts {
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
    public void testSimpleAlert()throws InterruptedException{
        driver.navigate().to("https://demoqa.com/alerts");

        //trigger the first alert
        driver.findElement(By.id("alertButton")).click();
        //now alert is present on the screen
        Thread.sleep(3000);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals("You clicked a button", alertText);

        //with simple alert - we can only click OK
        alert.accept();

        driver.findElement(By.id("timerAlertButton")).click();
        //this line will fail because the alert is not present on the screeen right away
        //It takes 5 seconds to appear
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert();

        alert.accept();

    }

    @Test
    public void testPromptAlert(){
        driver.navigate().to("https://demoqa.com/alerts");

        driver.findElement(By.id("promtButton")).click();

        Alert alert = driver.switchTo().alert();

        //since it is a prompt - I have to type some info in it
        String name = "Sofia";
        alert.sendKeys(name);
        alert.accept();

        WebElement result = driver.findElement(By.id("promptResult"));
        Assert.assertTrue(result.getText().contains(name));

    }

    @Test
    public void testConfirmationAlert(){
        driver.navigate().to("https://demoqa.com/alerts");

        driver.findElement(By.id("confirmButton")).click();
        Alert alert = driver.switchTo().alert();

        //dismiss - CANCEL
        alert.dismiss();

        WebElement result = driver.findElement(By.id("confirmResult"));

        Assert.assertTrue(result.getText().endsWith("Cancel"));

    }

}
