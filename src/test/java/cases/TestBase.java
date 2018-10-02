package cases;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.UtilitiesManager;

import utils.webdrivers.WebDriverFactory;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestBase {

    protected static Configuration testData;

    @BeforeSuite
    public void beforeSuite() throws Exception {
    }

    @BeforeTest
    @Parameters(value = {"browser"})
    public void beforeTest(ITestContext context, String browser) throws Exception {
    }

    @BeforeClass
    @Parameters(value = {"browser"})
    public synchronized void beforeClass(ITestContext context, String browser) throws Exception {
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method, ITestContext context) {
        WebDriverFactory.setDriver(context);
    }

    @AfterMethod
    @Parameters(value = {"browser"})
    public synchronized void afterMethod(Method method, String browser, ITestResult result) {
        WebDriverFactory.getDriver().manage().deleteAllCookies();
        WebDriverFactory.getDriver().quit();
    }

    @AfterTest
    @Parameters(value = {"browser"})
    public void afterTest(String browser) { }

    @AfterSuite
    public void afterSuite(ITestContext context) throws Exception { }

    public boolean isElementPresent(By by) {
        try {
            WebDriverFactory.getDriver().findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            WebDriverFactory.getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isTextPresent(String text) {
        try {
            boolean isPresent = WebDriverFactory.getDriver().getPageSource().contains(text);
            return isPresent;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<>(WebDriverFactory.getDriver())
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);

        WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return WebDriverFactory.getDriver().findElement(locator);
            }
        });
        return webElement;
    }

    public Map<String, String> getTestInfo(ITestContext context) {
        Map<String, String> testInfo = new HashMap<>();
        testInfo.put("browser", context.getCurrentXmlTest().getLocalParameters().get("browser"));
        testInfo.put("country", context.getSuite().getName());
        testInfo.put("url", UtilitiesManager.setPropertiesFile(
                context.getCurrentXmlTest().getLocalParameters().get("dataFile")).getString("test_url"));
        return testInfo;
    }

    public Callable<Boolean> isFileExisted(File downloadFile) {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return downloadFile.exists() && downloadFile.length()>0;
            }
        };
    }

}