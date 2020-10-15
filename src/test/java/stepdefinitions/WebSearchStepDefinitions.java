package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.SearchResultsHelper;

import java.util.List;

public class WebSearchStepDefinitions
{
  private static final String GOOGLE_SEARCH_URL =  "https://www.google.com/search?q=";

  private SearchResultsHelper searchResultsHelper = new SearchResultsHelper();
  private WebDriver driver;

  @Given("a web browser is loaded")
  public void webBrowserIsLoaded()
  {
    driver = BrowserFactory.driver;
  }

  @When("a Google search is performed for the exact string {string}")
  public void googleSearchWithText(String searchString)
  {
    String searchURL = GOOGLE_SEARCH_URL + "\"" + searchString + "\"";
    driver.navigate().to(searchURL);
  }

  @Then("results contain {string}")
  public void assertGoogleResults(String expectedResult)
  {
    int numberOfResults = 0;
    By searchResultsLocator = new By.ByClassName("g");
    List<WebElement> searchResults = driver.findElements(searchResultsLocator);
    numberOfResults = searchResultsHelper.numberOfMatchingResults(searchResults, expectedResult);
    Assert.assertTrue(numberOfResults > 0);
  }
}
