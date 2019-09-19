package cases.B2B;

import datamodel.TestResult;
import org.apache.commons.configuration2.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import utils.RestManager;
import utils.UtilitiesManager;

import java.lang.reflect.Method;
import java.time.Duration;

@ComponentScan(basePackages = { "utils", "b2b" })
@ContextConfiguration(classes = B2bTestBase.class)
public class B2bTestBase extends AbstractTestNGSpringContextTests {

    public static ThreadLocal<TestResult> testResultB2b = new ThreadLocal<>();
    protected static Configuration testData;

    @BeforeTest
    public void beforeTest() throws Exception {
        TestResult result = new TestResult();
        testResultB2b.set(result);
    }

    @BeforeMethod
    public void beforeMethod(Method method, ITestContext context) {
        TestResult result = new TestResult();
        result.setTestName(method.getName());
        result.setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
        result.setCountry(context.getCurrentXmlTest().getLocalParameters().get("country"));
        result.setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));
        testResultB2b.set(result);
    }

    @AfterMethod
    public synchronized void afterMethod(Method method) {
        testResultB2b.get().setTimeElapsed(Duration.between(testResultB2b.get().getTimeStarted(), testResultB2b.get().getTimeFinished()).toMillis());
 //       UtilitiesManager.createJsonFile(method.getName(), testResultB2b);

        // Send test result to Kibana server
          RestManager.sendTestResult(testResultB2b.get());
    }

}
