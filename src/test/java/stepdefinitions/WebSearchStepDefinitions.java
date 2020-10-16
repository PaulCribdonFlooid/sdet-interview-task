package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;
import utils.SearchResultsHelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebSearchStepDefinitions
{
  private static final String GOOGLE_SEARCH_URL =  "https://www.google.com/search?q=";

  private static final Logger LOGGER = Logger.getLogger(WebSearchStepDefinitions.class.getName());

  private WebDriver driver;

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


  @Then("results contain {string}")
  public void assertGoogleResultsContain(String expectedResult)
  {
    int numberOfResults = SearchResultsHelper.numberOfMatchingResults(
            SearchResultsHelper.getResults(driver),
            expectedResult);
    Assert.assertTrue(numberOfResults > 0);
  }

  @Then("the top {int} web pages all contain {string}")
  public void assertTopWebsitesContain(int numberOfWebsites, String expectedResult)
  {
    List<String> websiteList = SearchResultsHelper.getTopWebsites(numberOfWebsites, driver);

    Assert.assertEquals("Expected " + numberOfWebsites + " websites, but found " + websiteList.size(),
            websiteList.size(),
            numberOfWebsites);

    for (String website : websiteList)
    {
      driver.navigate().to(website);

      String pageSource = driver.getPageSource();

      Assert.assertTrue("Page " + website + " doesn't contain " + expectedResult,
              pageSource.contains(expectedResult));
    }

    StringBuilder outputListOfWebsites = new StringBuilder();
    outputListOfWebsites.append("The following ").append(websiteList.size())
            .append(" websites all contain ").append(expectedResult).append(":\n");
    websiteList.forEach(website -> outputListOfWebsites.append("\t").append(website).append("\n"));
    LOGGER.log(Level.INFO, outputListOfWebsites.toString());
  }
}
