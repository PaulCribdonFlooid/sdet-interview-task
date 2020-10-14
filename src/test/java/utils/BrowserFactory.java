package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void identifyBrowser(String browser) {
        switch (browser) {
            case "chrome":
                getChromeDriver();
                break;
            case "firefox":
                getFirefoxDriver();
                break;
        }
    }

    public static void getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/java/utils/driver/win32/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public static void getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/src/test/java/utils/driver/geckodriver.exe");
        driver = new FirefoxDriver();
    }

}
