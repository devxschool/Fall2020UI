package javascript;

import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;


import static utilities.HelperMethods.highlightElement;

public class PracticeJavascript {

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
    public void etsyTest() throws InterruptedException{
        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("window.location='https://www.etsy.com/'");

        WebElement signInButton = driver.findElement(By.cssSelector(".select-signin"));

        highlightElement(signInButton);
        //js.executeScript("arguments[0].setAttribute('style', arguments[1]);", signInButton, "border: 2px solid green");

        js.executeScript("arguments[0].click();", signInButton);
        Thread.sleep(3000);

        highlightElement("join_neu_email_field");
        //js.executeScript("document.getElementById('join_neu_email_field').setAttribute('style', arguments[0]);","border: 2px solid blue");



        Faker faker = new Faker();
        String fakeEmail = faker.internet().emailAddress();
        String fakePassword = faker.internet().password();

        js.executeScript("document.getElementById('join_neu_email_field').value=arguments[0];", fakeEmail);

        //join_neu_password_field

        highlightElement("join_neu_password_field");
        //js.executeScript("document.getElementById('join_neu_password_field').setAttribute('style', arguments[0]);","border: 2px solid yellow");


        js.executeScript("document.getElementById('join_neu_password_field').value=arguments[0];", fakePassword);

        WebElement submitButton = driver.findElement(By.xpath("//button[@name='submit_attempt']"));

        highlightElement(submitButton);
        //js.executeScript("arguments[0].setAttribute('style', arguments[1]);",submitButton, "border: 2px solid fuxia");
        js.executeScript("arguments[0].click();", submitButton);

        Thread.sleep(3000);



    }



}
