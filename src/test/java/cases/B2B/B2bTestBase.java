package cases.B2B;

import datamodel.TestResult;
import org.apache.commons.configuration2.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.RestManager;
import utils.UtilitiesManager;

import java.lang.reflect.Method;
import java.time.Duration;

@ComponentScan(basePackages = { "utils", "b2b" })
@ContextConfiguration(classes = B2bTestBase.class)
public class B2bTestBase extends AbstractTestNGSpringContextTests {

    public static TestResult testResult = new TestResult();
    protected static Configuration testData;

    @BeforeMethod
    public void methodSetup(Method method, ITestContext context) {
        testResult.setTestName(method.getName());
        testResult.setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
        testResult.setCountry(context.getCurrentXmlTest().getLocalParameters().get("country"));
        testResult.setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));
    }

    @AfterMethod
    public synchronized void afterMethod(Method method) {
        testResult.setTimeElapsed(Duration.between(testResult.getTimeStarted(), testResult.getTimeFinished()).toMillis());
        UtilitiesManager.createJsonFile(method.getName(), testResult);

        // Send test result to Kibana server
          RestManager.sendTestResult(testResult);
    }

}
