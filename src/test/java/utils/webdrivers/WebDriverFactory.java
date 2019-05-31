package utils.webdrivers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;

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
        OptionManager optionManager = new OptionManager();

        String browser = context.getCurrentXmlTest().getLocalParameters().get("browser");
        String os = context.getCurrentXmlTest().getLocalParameters().get("os");
        String langLocale = context.getCurrentXmlTest().getLocalParameters().get("locale");
        String language = langLocale.substring(0,2);
        String locale = langLocale.substring(3,5);

        if (browser.equalsIgnoreCase("firefox")) {
//            switch (UtilitiesManager.getSystem()) {
//                case "Windows":
//                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverWin"));
//                    break;
//                case "Mac":
//                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverMac"));
//                    break;
//                case "Linux":
//                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverLinux"));
//                    break;
//            }
//            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
//            try {
//                optionManager.setFirefoxOptions(context);
//                webDriverThreadLocal.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
//                webDriverThreadLocal.get().manage().window().maximize();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

            //Selenium Grid
            try {
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
                optionManager.setFirefoxOptions(context);
                FirefoxOptions firefoxOptions = optionManager.getFirefoxOptions();

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
                //hub
                webDriverThreadLocal.set(new RemoteWebDriver(new URL("http://10.29.25.100:4444/wd/hub"), firefoxOptions));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (browser.equalsIgnoreCase("chrome")) {

//            switch (UtilitiesManager.getSystem()) {
//                case "Windows":
//                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverWin"));
//                    break;
//                case "Mac":
//                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverMac"));
//                    break;
//                case "Linux":
//                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverLinux"));
//                    break;
//            }
//            try {
//                optionManager.setChromeOptions(context);
//                webDriverThreadLocal.set(new ChromeDriver(optionManager.getChromeOptions()));
//                webDriverThreadLocal.get().manage().window().maximize();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

            //Selenium Grid
            try {
//                ChromeOptions chromeOptions = new ChromeOptions();
                optionManager.setChromeOptions(context);
                ChromeOptions chromeOptions = optionManager.getChromeOptions();

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
                //hub
                webDriverThreadLocal.set(new RemoteWebDriver(new URL("http://10.29.25.100:4444/wd/hub"), chromeOptions));
                webDriverThreadLocal.get().manage().window().maximize();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
