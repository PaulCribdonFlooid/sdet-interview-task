package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsHelper
{
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

  public static List<WebElement> getResults(WebDriver driver)
  {
    return driver.findElements(By.className(GOOGLE_RESULTS_CLASS));
  }

  public static List<String> getTopWebsites(int numberOfSites, WebDriver driver)
  {
    List<String> websiteList = new ArrayList<>();
    List<WebElement> results = null;

    while (websiteList.size() < numberOfSites)
    {
      if (results != null)
      {
        SearchResultsHelper.loadNextPageOfResults(driver);
      }

      results = SearchResultsHelper.getResults(driver);
      for (WebElement searchResult : results)
      {
        websiteList.add(getLinkFromResult(searchResult));
      }
    }

    return websiteList.subList(0,numberOfSites);
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
        matches++;
      }
    }
    return matches;
  }

  private static void loadNextPageOfResults(WebDriver driver)
  {
    WebElement nextPageElement = driver.findElement(By.id(GOOGLE_NEXT_PAGE_ID));
    String nextPageURL = nextPageElement.getAttribute("href");
    driver.navigate().to(nextPageURL);
  }

  private static String getLinkFromResult (WebElement searchResult)
  {
    return searchResult.findElement(By.tagName("a")).getAttribute("href");
  }

  private static boolean textContainsResult(String text, String expectedResult)
  {
    return text.toLowerCase().contains(expectedResult.toLowerCase());
  }

}
