package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class MainPage {
    //in this class we will store all the elements from the main page of Meetup

    //don't add all of the elements right away
    //instead, create your scenarios first and then add webelements to the page as you ned them
    public MainPage(){
        //this line will initialize all the webelements on this page
        PageFactory.initElements(Driver.getDriver(), this);
    }


    //here we will add all the elements that belong to main page
    //before every single webelemnt we need to tell how to find it
    //we use @FindBy annotation (xpath="your xpath here")

    @FindBy(xpath="//a[text()='Log in' and contains(@href,'secure')]")
    public WebElement loginButton;

    @FindBy(xpath = "//a[text()='Sign up' and contains(@href,'secure')]")
    public WebElement signupButton;

    @FindBy(xpath = "//a[text()='Join Meetup' and contains(@href,'secure')]")
    public WebElement joinMeetupButton;

    @FindBy(id = "tracking--searchComponentInput")
    public WebElement searchInputField;
    //here you can add more elements that you are using in your tests

    @FindBy(id = "tracking--searchComponentButton")
    public WebElement searchButton;

    @FindBy(xpath = "//input[@aria-label='Search for location by city or zip code']")
    public WebElement locationInputField;


    @FindBys({
            @FindBy(xpath = "//a[@role='navigation']"),
            @FindBy(xpath = "//a[@aria-label='Online']")

    })
    public WebElement onlineLink;

    @FindAll({
            @FindBy(xpath = "//a[@role='navigation']"),
            @FindBy(xpath = "//a[@aria-label='Online']")
    })
    public List<WebElement> someElement;

    //in the page class you can add basic methods that you perform on the elements of this page

    public void clickJoinMeetup(){
        //you will need explicit waits before performing an action
        joinMeetupButton.click();
    }

    public void search(String searchCriteria, String location){
        searchInputField.sendKeys(searchCriteria);
       if(location!=null){
           //locationInputField.clear();
           locationInputField.sendKeys(Keys.CONTROL+"a"+Keys.DELETE);
           locationInputField.sendKeys(location);
       }
       searchButton.click();

    }


}
