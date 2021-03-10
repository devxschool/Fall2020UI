package browserSynchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PracticeWaits {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //implicit wait
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @After
    public void tearDown(){
        //driver.close();
    }

    @Test
    public void amazonTest(){
        driver.get("https://www.amazon.com/");
    }


    @Test
    public void testEtsy(){
        driver.get("https://www.etsy.com/"); //--> the only case when you don't need to wait to continue
        //click sign in button
        driver.findElement(By.xpath("//button[contains(@class,'select-signin')]")).click();

        //locate register button
        //here we will add explicit wait to wait for our register button to be displayed on the page
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement registerButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(@class,'select-register')]")));

        //WebElement registerButton = driver.findElement(By.xpath("//button[contains(@class,'select-register')]"));
        registerButton.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[starts-with(text(), 'Registration')]")));
        WebElement registrationForm = driver.findElement(By.xpath("//p[starts-with(text(), 'Registration')]"));

        Assert.assertTrue("The registration form is not displayed", registrationForm.isDisplayed());
    }

    //https://demoqa.com/dynamic-properties
    //using explicit wait wait for the button to be visible on the page
    //verify the button is displayed

    @Test
    public void test1(){
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement visibilityButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='visibleAfter']")));

        Assert.assertTrue(visibilityButton.isDisplayed());
    }

    @Test
    public void test2(){
        driver.get("https://demoqa.com/dynamic-properties");
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.attributeContains(By.cssSelector("#colorChange"), "class", "text-danger"));
        WebElement colorChangeButton = driver.findElement(By.id("colorChange"));

        Assert.assertTrue(colorChangeButton.getAttribute("class").contains("text-danger"));
    }

    @Test
    public void testFileUpload() throws InterruptedException{
        driver.get("https://demoqa.com/upload-download");
        //I need to locate the input element that represents file upload
        //then I need to sendKeys() with the path to my file to the input field
        WebElement chooseFileInput = driver.findElement(By.xpath("//input[@id='uploadFile']"));

        chooseFileInput.sendKeys("/Users/rudenka93/Desktop/devxLogo.png");
        //verify that my file path is displayed under th choose file button
        WebElement result = chooseFileInput.findElement(By.xpath("./../../following-sibling::p"));

        Assert.assertTrue(result.getText().contains("devxLogo"));

    }

    @Test
    public void testImageUpload() throws InterruptedException{
        driver.get("https://images.google.com/");

        driver.findElement(By.xpath("//div[@aria-label='Search by image']")).click();

        driver.findElement(By.xpath("//a[text()='Upload an image']")).click();

        driver.findElement(By.xpath("//input[@id='awyMjb']")).sendKeys("/Users/rudenka93/Desktop/image.png");


    }





}
