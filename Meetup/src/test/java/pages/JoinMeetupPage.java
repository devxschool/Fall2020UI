package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class JoinMeetupPage {

    public JoinMeetupPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//form/a[@id='facebookAuthButton']")
    public WebElement continueWithFacebookButton;

    //here you will have to add the rest of the elements from this page
    // that you use in your scenario
}
