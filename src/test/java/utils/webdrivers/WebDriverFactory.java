package utils.webdrivers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import utils.UtilitiesManager;

import java.net.URL;

import static cases.TestBase.RUN_ON_GRID;

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();
    private static OptionManager optionManager = new OptionManager();
    public static final boolean SELENIUM_GRID_HUB_URL = Boolean.valueOf(System.getProperty("seleniumGridHub"));

    public static synchronized WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, 20);
    }

    public static synchronized WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static synchronized void setDriver(ITestContext context) {

        String browser = context.getCurrentXmlTest().getLocalParameters().get("browser");
        String os = context.getCurrentXmlTest().getLocalParameters().get("os");

        if (browser.equalsIgnoreCase("firefox") && !RUN_ON_GRID) {
            switch (UtilitiesManager.getSystem()) {
                case "Windows":
                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverWin"));
                    break;
                case "Mac":
                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverMac"));
                    break;
                case "Linux":
                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverLinux"));
                    break;
            }
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            try {
                webDriverThreadLocal.set(new FirefoxDriver(optionManager.setFirefoxOptions(context)));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (browser.equalsIgnoreCase("firefox") && RUN_ON_GRID) {
            //Selenium Grid
            try {
                FirefoxOptions firefoxOptions = optionManager.setFirefoxOptions(context);

                //default set to ANY
                firefoxOptions.setCapability("platform", Platform.ANY);

                if (os.equalsIgnoreCase("mac")) {
                    firefoxOptions.setCapability("platform", Platform.MAC);
                }
                if (os.equalsIgnoreCase("windows")) {
                    firefoxOptions.setCapability("platform", Platform.WINDOWS);
                }
                if (os.equalsIgnoreCase("any")) {
                    firefoxOptions.setCapability("platform", Platform.ANY);
                }
                //hub
                webDriverThreadLocal.set(new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL + "/wd/hub"), firefoxOptions));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (browser.equalsIgnoreCase("chrome") && !RUN_ON_GRID) {
            switch (UtilitiesManager.getSystem()) {
                case "Windows":
                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverWin"));
                    break;
                case "Mac":
                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverMac"));
                    break;
                case "Linux":
                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverLinux"));
                    break;
            }
            try {
                webDriverThreadLocal.set(new ChromeDriver(optionManager.setChromeOptions(context)));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (browser.equalsIgnoreCase("chrome") && RUN_ON_GRID){

            //Selenium Grid
            try {
                ChromeOptions chromeOptions = optionManager.setChromeOptions(context);

                //default set to ANY
                chromeOptions.setCapability("platform", Platform.ANY);

                if (os.equalsIgnoreCase("mac")) {
                    chromeOptions.setCapability("platform", Platform.MAC);
                }
                if (os.equalsIgnoreCase("windows")) {
                    chromeOptions.setCapability("platform", Platform.WINDOWS);
                }
                if (os.equalsIgnoreCase("any")) {
                    chromeOptions.setCapability("platform", Platform.ANY);
                }
                //hub
                webDriverThreadLocal.set(new RemoteWebDriver(new URL(SELENIUM_GRID_HUB_URL + "/wd/hub"), chromeOptions));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
