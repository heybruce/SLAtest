package utils.webdrivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import utils.UtilitiesManager;

public class WebDriverFactory {

    private static OptionManager optionManager = new OptionManager();
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    public static synchronized void setDriver(ITestContext context) {
        String browser = context.getCurrentXmlTest().getLocalParameters().get("browser");

        if (browser.equalsIgnoreCase("firefox")) {
            switch (UtilitiesManager.getSystem()) {
                case "Windows" :
                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverWin"));
                    break;
                case "Mac" :
                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverMac"));
                    break;
                case "Linux" :
                    System.setProperty("webdriver.gecko.driver", OptionManager.configProp.getString("geckodriverLinux"));
                    break;
            }
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

            optionManager.setFirefoxOptions(context);
            webDriverThreadLocal.set(new FirefoxDriver(optionManager.getFirefoxOptions()));
            webDriverThreadLocal.get().manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            switch (UtilitiesManager.getSystem()) {
                case "Windows" :
                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverWin"));
                    break;
                case "Mac" :
                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverMac"));
                    break;
                case "Linux" :
                    System.setProperty("webdriver.chrome.driver", OptionManager.configProp.getString("chromedriverLinux"));
                    break;
            }
            optionManager.setChromeOptions(context);
            webDriverThreadLocal.set(new ChromeDriver(optionManager.getChromeOptions()));
            webDriverThreadLocal.get().manage().window().maximize();
        } else if (browser.equalsIgnoreCase("ie") && UtilitiesManager.getSystem().equalsIgnoreCase("Windows")) {
            System.setProperty("webdriver.ie.driver", OptionManager.configProp.getString("iedriverWin"));
            webDriverThreadLocal.set(new InternetExplorerDriver(optionManager.getIeOptions()));
            webDriverThreadLocal.get().manage().window().maximize();
        }
    }

    public static synchronized WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, 20);
    }

    public static synchronized WebDriver getDriver() {
        return webDriverThreadLocal.get();
    }
    
}
