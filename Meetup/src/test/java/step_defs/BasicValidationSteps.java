package step_defs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import utilities.Driver;

public class BasicValidationSteps {

    WebDriver driver = Driver.getDriver();

    @When("^the user navigates to google$")
    public void the_user_navigates_to_google() throws Throwable {
        driver.navigate().to("https://www.google.com/");
        //Driver.getDriver().navigate().to();
    }

    @When("^the user searches for \"([^\"]*)\"$")
    public void the_user_searches_for(String searchCriteria) throws Throwable {
        driver.findElement(By.name("q")).sendKeys(searchCriteria+ Keys.ENTER);
    }

    @Then("^verify \"([^\"]*)\" is in the title of the page$")
    public void verify_is_in_the_title_of_the_page(String searchCriteria) throws Throwable {
        Assert.assertTrue(driver.getTitle().toLowerCase().contains(searchCriteria));
    }

}
