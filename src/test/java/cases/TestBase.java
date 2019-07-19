package cases;

import datamodel.TestResult;
import org.apache.commons.configuration2.Configuration;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.RestManager;
import utils.UtilitiesManager;

import utils.webdrivers.WebDriverFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static utils.webdrivers.WebDriverFactory.getDriver;

@ComponentScan(basePackages = { "utils", "b2b" })
@ContextConfiguration(classes = TestBase.class)
public class TestBase extends AbstractTestNGSpringContextTests {
    private final static Logger logger = Logger.getLogger(TestBase.class);

    public static ThreadLocal<TestResult> testResult = new ThreadLocal<>();
    public static Configuration testData;
    public static Configuration vehicleElementData;
    public static final boolean RUN_ON_GRID = Boolean.valueOf(System.getProperty("runOnGrid"));

    @BeforeSuite
    public void beforeSuite() throws Exception {
    }

    @BeforeTest
    public void beforeTest() throws Exception {
        TestResult result = new TestResult();
        testResult.set(result);
    }

    @BeforeClass
    public synchronized void beforeClass() {
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method, ITestContext context) {
        logger.debug("Ready to start test method - " + method.getName());
        WebDriverFactory.setDriver(context);

        //Selenium grid
        if(RUN_ON_GRID) {
            //Enable Remote Web Driver to upload files from local machine
            ((RemoteWebDriver) getDriver()).setFileDetector(new LocalFileDetector());
        }

        TestResult result = new TestResult();
        result.setTestName(method.getName());
        result.setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
        result.setCountry(context.getCurrentXmlTest().getLocalParameters().get("country"));
        result.setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));
        testResult.set(result);
    }

    @AfterMethod
    public synchronized void afterMethod(Method method, ITestResult result) {
        logger.debug("Finish up test method - " + method.getName());
        testResult.get().setTimeElapsed(Duration.between(testResult.get().getTimeStarted(), testResult.get().getTimeFinished()).toMillis());
        testResult.get().setSuccess(result.isSuccess());

        WebDriverFactory.getDriver().manage().deleteAllCookies();

        UtilitiesManager.createJsonFile(method.getName(), testResult.get());
        //Send test result to Kibana server
        RestManager.sendTestResult(testResult.get());

        WebDriverFactory.getDriver().quit();
    }

    @AfterTest
    public void afterTest() { }

    @AfterSuite
    public void afterSuite() { }

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

    public Callable<Boolean> isFileExisted(File downloadFile) {
        return new Callable<Boolean>() {
            public Boolean call() throws Exception {
                return downloadFile.exists() && downloadFile.length()>0;
            }
        };
    }
}