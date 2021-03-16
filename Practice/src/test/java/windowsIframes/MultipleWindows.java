package windowsIframes;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MultipleWindows {


    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();//--> will close all open windows and quit the browser
    }

    @Test
    public  void amazonTest(){
        driver.get("https://www.amazon.com/");
        //I want ot get the unique window handle assigned to amazon page
        //.getWindowHandle() - retrieves the unique id of currently open window
        String windowHandle = driver.getWindowHandle();

        System.out.println(windowHandle);
        //CDwindow-EB03B69FFEACE22CBDDE148168B18527
        //CDwindow-A92AB1CF65333A91C7685A4064A561EF
    }

    @Test
    public void testMultipleWindows(){
        driver.get("http://www.practiceselenium.com/");
        driver.findElement(By.xpath("//span[contains(text(), '25 years')]/a")).click();
        //after I clicked on the link - new window opened up
        //in order to interact with webelements on the new window
        // I need to tell my driver to switch to the new window
        //1. Get windowHandle of main window
        //2. Get window handles of all open windows
        //3. using a loop compare and switch to the new window

        //1
        String mainPage = driver.getWindowHandle();

        //2
        Set<String> allOpenWindows = driver.getWindowHandles();
        //--> this set will contain 2 values because we have 2 windows open
        //one of the values will be id of our main window
        //3
        for (String windowHandle: allOpenWindows){
            if(!windowHandle.equals(mainPage)){
                driver.switchTo().window(windowHandle);
            }
        }

        //click on the link on the new window

        driver.findElement(By.xpath("//a[text()='Choosing an Automation Solution']")).click();

        WebElement agendaHeading = driver.findElement(By.xpath("//strong[text()='Agenda:']"));

        Assert.assertTrue("Agenda is not displayed.Switch to the new window",agendaHeading.isDisplayed());

        //I need to switch back to the main window
        driver.switchTo().window(mainPage);

        WebElement checkOutLink = driver.findElement(By.xpath("//a[text()='Check Out']"));

        Assert.assertTrue("Failed to switch back to the home page",checkOutLink.isDisplayed());

    }

    @Test
    public void practiceThreeWindows(){
        driver.get("https://demoqa.com/links");

        driver.findElement(By.id("simpleLink")).click();
        //after the click new window has opened
        //in order to click on Join Now button I first need to switch to the new window

        String mainPage = driver.getWindowHandle();

        Set<String> allOpenWindows = driver.getWindowHandles();//only 2 values

        for(String windowHandle: allOpenWindows){
            if(!windowHandle.equals(mainPage)){
                driver.switchTo().window(windowHandle);
            }
        }
        //now we are on the new window and we can interact with its elements

        WebElement joinNowLink = driver.findElement(By.xpath("//a[@href='https://www.toolsqa.com/selenium-training/']"));

        joinNowLink.click();
        //now I have 3 windows open

        String secondPage = driver.getWindowHandle();

        allOpenWindows = driver.getWindowHandles();//now it will have 3 values

        for(String windowHandle: allOpenWindows){
            if(!windowHandle.equals(mainPage) && !windowHandle.equals(secondPage)){
                driver.switchTo().window(windowHandle);
                //now I am on the third page
            }
        }

        Assert.assertTrue(driver.getTitle().contains("Online Selenium Training"));
        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Selenium Training']")).isDisplayed());




    }
}
