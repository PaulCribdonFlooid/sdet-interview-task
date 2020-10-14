package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.BrowserFactory;

public class ApplicationHooks{


    @Before()
    public void before()
    {
        BrowserFactory.identifyBrowser("chrome");
        BrowserFactory.driver.manage().window().maximize();
    }

    @After()
    public void after()
    {
        BrowserFactory.driver.quit();
    }
}
