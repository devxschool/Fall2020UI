package saucelabs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SaucelabsParallel {

    private static final String USERNAME = ConfigReader.getProperty("saucelabsUsername");
    private static final String ACCESS_KEY = ConfigReader.getProperty("saucelabsAccessKey");
    private static final String URL = "https://"+USERNAME+":"+ACCESS_KEY+"@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    private String browser;
    private String version;
    private String os;

    public SaucelabsParallel(String browser, String version, String os){
        this.browser = browser;
        this.version = version;
        this.os = os;
    }



    //when we are trying to run tests in parallel we need to make sure we set up our driver to be thread local
    //Thread - line of execution

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    //when we are running in parallel we need to keep track of sessions
    private ThreadLocal<String> sessionId = new ThreadLocal<>();


    private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("platformName", os);

        driver.set(new RemoteWebDriver(new URL(URL), capabilities));
        //I need to get session id and store in sessionId collection
        String id = ((RemoteWebDriver)driver.get()).getSessionId().toString();
        sessionId.set(id);

        return driver.get();
    }

    //now we need to store all the configurations for the browser that we will be using

    @Parameterized.Parameters
    public static Collection saucelabsDataProvider(){
        return Arrays.asList(new Object[][]{
                {"chrome", "latest", "macOs 10.14"},
                {"firefox", "latest", "Windows 10"},
                //{"edge", "18.17763", "Windows 10"}
        });
    }


    @Test
    public void testSimpleAlert()throws InterruptedException, MalformedURLException{

        WebDriver driver = createDriver(browser,version,os);
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
    public void testPromptAlert() throws MalformedURLException{
        WebDriver driver = createDriver(browser,version,os);
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

    @After
    public void tearDown(){
        driver.get().close();
    }





}
