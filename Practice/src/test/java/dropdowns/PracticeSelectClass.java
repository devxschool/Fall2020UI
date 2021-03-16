package dropdowns;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class PracticeSelectClass {

    WebDriver driver;

    @Before
    public void setUp(){

        if(ConfigReader.getProperty("browser").equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(ConfigReader.getProperty("browser").equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    @Test
    public void practiceEnrollmentForm(){
        driver.get("https://www.devxschool.com/enrollment/");
        //I want to see all the options available
        //I want to see the option selected by default

        //Step 1
       // Locate your select webelement and store it in webelement object
        WebElement selectElement = driver.findElement(By.cssSelector("#form-field-ads"));

        //Step 2
        //Create object of Select class
        Select dropdown = new Select(selectElement);

        //To see all the available options

        List<WebElement> availableOptions = dropdown.getOptions();

        Assert.assertEquals(5, availableOptions.size());

        for(WebElement option: availableOptions){
            System.out.println(option.getText());
        }

        //make sure From a friend is selected by default
        //1. Get all selected options

        List<WebElement> selectedOptions = dropdown.getAllSelectedOptions();

        Assert.assertTrue(selectedOptions.size()==1 &&
                selectedOptions.get(0).getText().equals("From a friend"));


        //lets select Instagram
        //to select by visible text
        //dropdown.selectByVisibleText("Instagram");
        //to select Instagram by index
        //dropdown.selectByIndex(2);
        //select by value
        dropdown.selectByValue("Instagram");

        selectedOptions = dropdown.getAllSelectedOptions();
        Assert.assertTrue(selectedOptions.size()==1 &&
                selectedOptions.get(0).getText().equals("Instagram"));

    }

    @Test
    public void practiceMultiSelect(){
        driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");

        WebElement selectElement = driver.findElement(By.id("option-droup-demo"));

        Select dropdown = new Select(selectElement);
        //lets verify it is a multi select
        Assert.assertTrue(dropdown.isMultiple());

        //what options are selected
        List<WebElement> selectedOptions = dropdown.getAllSelectedOptions();

        System.out.println("Currently options that are selected: ");

        for (WebElement selected: selectedOptions){
            System.out.println(selected.getText());
        }

        //I want to select Java, Python, C#

        //first, I deselect HTML and CSS

        dropdown.deselectAll();

        //If i wanted to deselect only HTML

        //dropdown.deselectByVisibleText("HTML");


        //select Java by index
        dropdown.selectByIndex(5);
        //select C# by value
        dropdown.selectByValue("csharp");
        //select Python by visible text
        dropdown.selectByVisibleText("Python");


        selectedOptions = dropdown.getAllSelectedOptions();

        System.out.println("*************************\nNew options selected");
        for (WebElement selected: selectedOptions){
            System.out.println(selected.getText());
        }

        Assert.assertEquals(3,selectedOptions.size());



    }


    @Test
    public void expediaTest(){

        driver.get("https://www.expedia.com/");

        try{
            driver.findElement(By.xpath("//a[@aria-controls='wizard-cruise-pwa']")).click();
        }catch(ElementClickInterceptedException e){
            //we will wait for that little modal to show up--> then we will close it
            WebDriverWait wait = new WebDriverWait(driver,5);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[starts-with(text(),'You could be')]")));

            driver.findElement(By.xpath("//div[contains(text(),'Sign in')]/..")).click();

            driver.findElement(By.xpath("//a[@aria-controls='wizard-cruise-pwa']")).click();
        }

        driver.findElement(By.xpath("//a[@aria-controls='wizard-cruise-pwa']")).click();
        //exception happen

        WebElement selectElemnt = driver.findElement(By.id("cruise-destination"));

        Select dropdown = new Select(selectElemnt);
        //select Alaska by value
        dropdown.selectByValue("alaska");
        //verify Alaska is selected
        Assert.assertTrue(dropdown.getAllSelectedOptions().get(0).getText().equals("Alaska"));
        //dropdown.getFirstSelectedOption() - will return a first option that is selected

        //select Africa by visible text
        dropdown.selectByVisibleText("Africa");
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Africa"));

        //select Mexico by index
        dropdown.selectByIndex(3);
        Assert.assertTrue(dropdown.getFirstSelectedOption().getText().equals("Mexico"));

        for(WebElement option: dropdown.getOptions()){
            System.out.println(option.getText());
        }





    }


}
