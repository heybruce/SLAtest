package cases;

import datamodel.TestResult;
import org.apache.commons.configuration2.Configuration;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.RestManager;
import utils.UtilitiesManager;

import utils.webdrivers.WebDriverFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestBase {
//    private final static Logger logger = Logger.getLogger(TestBase.class);

    public static ThreadLocal<TestResult> testResult = new ThreadLocal<>();
//    public static TestResult testResult = new TestResult();
    public static Configuration testData;
    public static Configuration vehicleElementData;

    @BeforeSuite
    public void beforeSuite() throws Exception {
    }

    @BeforeTest
    @Parameters(value = {"browser"})
    public void beforeTest(ITestContext context, String browser) throws Exception {
        TestResult result = new TestResult();
        testResult.set(result);
    }

    @BeforeClass
    @Parameters(value = {"browser"})
    public synchronized void beforeClass(ITestContext context, String browser) throws Exception {
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method, ITestContext context) {
        WebDriverFactory.setDriver(context);

        TestResult result = new TestResult();
        result.setTestName(method.getName());
        result.setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
        result.setCountry(context.getCurrentXmlTest().getLocalParameters().get("country"));
        result.setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));
        testResult.set(result);

//        testResult.get().setTestName(method.getName());
//        testResult.get().setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
//        testResult.get().setCountry(context.getCurrentXmlTest().getLocalParameters().get("country"));
//        testResult.get().setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));
    }

    @AfterMethod
    @Parameters(value = {"browser"})
    public synchronized void afterMethod(Method method, String browser, ITestResult result) {
        testResult.get().setTimeElapsed(Duration.between(testResult.get().getTimeStarted(), testResult.get().getTimeFinished()).toMillis());
        testResult.get().setSuccess(result.isSuccess());

        WebDriverFactory.getDriver().manage().deleteAllCookies();
        WebDriverFactory.getDriver().quit();

        UtilitiesManager.createJsonFile(method.getName(), testResult.get());
        //Send test result to Kibana server
        RestManager.sendTestResult(testResult.get());
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
                .withTimeout(60, TimeUnit.SECONDS)
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
        testInfo.put("country", context.getCurrentXmlTest().getLocalParameters().get("country"));
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