package utils.webdrivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import utils.UtilitiesManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static synchronized WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, 20);
    }

    public static synchronized WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }

    public static synchronized void setDriver(ITestContext context) {
        String browser = context.getCurrentXmlTest().getLocalParameters().get("browser");
        String os = context.getCurrentXmlTest().getLocalParameters().get("os");
        String locale = context.getCurrentXmlTest().getLocalParameters().get("locale");

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
            try {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //default set to WINDOWS
                firefoxOptions.setCapability("platform", Platform.WINDOWS);

                if (os.equalsIgnoreCase("mac")) {
                    firefoxOptions.setCapability("platform", Platform.MAC);
                }
                if (os.equalsIgnoreCase("windows")) {
                    firefoxOptions.setCapability("platform", Platform.WINDOWS);
                }
                if (os.equalsIgnoreCase("any")) {
                    firefoxOptions.setCapability("platform", Platform.ANY);
                }
                webDriverThreadLocal.set(new RemoteWebDriver(new URL("http://10.29.25.100:4444/wd/hub"), firefoxOptions));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (browser.equalsIgnoreCase("chrome")) {
            try {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--lang=" + locale);
                //default set to WINDOWS
                chromeOptions.setCapability("platform", Platform.WINDOWS);

                if (os.equalsIgnoreCase("mac")) {
                    chromeOptions.setCapability("platform", Platform.MAC);
                }
                if (os.equalsIgnoreCase("windows")) {
                    chromeOptions.setCapability("platform", Platform.WINDOWS);
                }
                if (os.equalsIgnoreCase("any")) {
                    chromeOptions.setCapability("platform", Platform.ANY);
                }
                webDriverThreadLocal.set(new RemoteWebDriver(new URL("http://10.29.25.100:4444/wd/hub"), chromeOptions));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
