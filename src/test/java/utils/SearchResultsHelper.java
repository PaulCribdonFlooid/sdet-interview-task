package utils;

import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsHelper
{
  public int numberOfMatchingResults(List<WebElement> searchResults, String expectedResult)
  {
    int matches = 0;
    for (WebElement result : searchResults)
    {
      if (textContainsResult(result.getText(), expectedResult)) {matches++;}
    }
    return matches;
  }

  private boolean textContainsResult(String text, String expectedResult)
  {
    return text.toLowerCase().contains(expectedResult.toLowerCase());
  }

}
