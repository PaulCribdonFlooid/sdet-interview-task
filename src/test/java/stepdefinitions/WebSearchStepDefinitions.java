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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebSearchStepDefinitions
{
  private static final String GOOGLE_SEARCH_URL =  "https://www.google.com/search?q=";

  private static final Logger LOGGER = Logger.getLogger(WebSearchStepDefinitions.class.getName());

  private WebDriver driver;

  private List<String> websiteList;

  @Given("a web browser is loaded")
  public void webBrowserIsLoaded()
  {
    driver = BrowserFactory.driver;
  }

  @When("a Google search is performed for {string}")
  public void googleSearchWithText(String searchString)
  {
    String searchURL = GOOGLE_SEARCH_URL + "\"" + searchString + "\"";
    driver.navigate().to(searchURL);
    SearchResultsHelper.consentToForm(driver);
  }

  @When("the top 10 web pages are returned")
  public void findTop10WebsiteDetails()
  {
    websiteList = SearchResultsHelper.getLinkFromResults(SearchResultsHelper.getResults(driver));
    if(websiteList.size() < 10)
    {
      SearchResultsHelper.loadNextPageOfResults(driver);
      websiteList.addAll(SearchResultsHelper.getLinkFromResults(SearchResultsHelper.getResults(driver)));
    }
    websiteList = websiteList.subList(0,10);
  }

  @Then("results contain {string}")
  public void assertGoogleResultsContain(String expectedResult)
  {
    int numberOfResults = 0;
    numberOfResults = SearchResultsHelper.numberOfMatchingResults(
            SearchResultsHelper.getResults(driver),
            expectedResult);
    Assert.assertTrue(numberOfResults > 0);
  }

  @Then("the external web pages contain {string}")
  public void assertPagesExternalToFlooidContain(String expectedResult) throws Exception
  {
    if (websiteList == null)
    {
      throw new Exception("website list not yet built!");
    }

    WebElement phoneNumber;
    for (String website : websiteList)
    {
      if (website.contains("flooid.com"))
      {
        break;
      }

      driver.navigate().to(website);
      LOGGER.log(Level.INFO, "URL: " + website + " | Title: " + driver.getTitle());

      String pageSource = driver.getPageSource();
      Assert.assertTrue(pageSource.contains(expectedResult));

      int expectedResultIndex = pageSource.indexOf(expectedResult);
      LOGGER.log(Level.INFO, "URL: " + website +
              " | Flooid Details: " +
              pageSource.substring(expectedResultIndex-50, expectedResultIndex+50));
    }

  }
}
