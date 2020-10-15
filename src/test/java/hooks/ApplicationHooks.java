package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.BrowserFactory;

public class ApplicationHooks
{
  @After()
  public void after()
  {
    BrowserFactory.driver.quit();
  }

  @Before()
  public void before()
  {
    BrowserFactory.identifyBrowser("chrome");
    BrowserFactory.driver.manage().window().maximize();
  }
}
