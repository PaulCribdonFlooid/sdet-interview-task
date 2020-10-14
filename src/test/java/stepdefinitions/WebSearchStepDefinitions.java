package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;

public class WebSearchStepDefinitions
{

  WebDriver driver = BrowserFactory.driver;

  @Given("a web browser is on the Google page")
  public void a_web_browser_is_on_the_Google_page()
  {
    driver.navigate().to("http://www.google.com");
  }

  @When("a search is performed with text {string}")
  public void a_search_is_performed_with_text(String string)
  {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }

  @Then("results for {string} are shown")
  public void results_for_are_shown(String string)
  {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
}
