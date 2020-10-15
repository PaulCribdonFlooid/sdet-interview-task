package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchResultsHelper
{
  private static final Logger LOGGER = Logger.getLogger(SearchResultsHelper.class.getName());

  private static final String GOOGLE_RESULTS_CLASS = "g";
  private static final String GOOGLE_CONSENT_FORM_ID = "cnsw";
  private static final String GOOGLE_CONSENT_AGREE_ID = "introAgreeButton";
  private static final String GOOGLE_NEXT_PAGE_ID = "pnnext";

  public static void consentToForm(WebDriver driver)
  {
    WebElement consentForm = driver.findElement(By.id(GOOGLE_CONSENT_FORM_ID));
    if (consentForm != null)
    {
      WebElement consentIframe = consentForm.findElement(By.tagName("iframe"));
      driver.switchTo().frame(consentIframe);
      WebElement consentAgreeButton = driver.findElement(By.id(GOOGLE_CONSENT_AGREE_ID));
      consentAgreeButton.click();
      driver.switchTo().parentFrame();
    }
  }

  public static void loadNextPageOfResults(WebDriver driver)
  {
    WebElement nextPageElement = driver.findElement(By.id(GOOGLE_NEXT_PAGE_ID));
    String nextPageURL = nextPageElement.getAttribute("href");
    driver.navigate().to(nextPageURL);
  }

  public static List<WebElement> getResults(WebDriver driver)
  {
    return driver.findElements(By.className(GOOGLE_RESULTS_CLASS));
  }

  public static List<String> getLinkFromResults(List<WebElement> searchResults)
  {
    List<String> links = new ArrayList<>();
    for (WebElement result : searchResults)
    {
      links.add(result.findElement(By.tagName("a")).getAttribute("href"));
    }
    return links;
  }

  public static int numberOfMatchingResults(List<WebElement> searchResults, String expectedResult)
  {
    int matches = 0;
    String resultText;
    for (WebElement result : searchResults)
    {
      resultText = result.getText();
      if (textContainsResult(resultText, expectedResult))
      {
        LOGGER.log(Level.INFO, "\"" + resultText + "\" contains \"" + expectedResult + "\"");
        matches++;
      }
    }
    return matches;
  }

  private static boolean textContainsResult(String text, String expectedResult)
  {
    return text.toLowerCase().contains(expectedResult.toLowerCase());
  }

}
