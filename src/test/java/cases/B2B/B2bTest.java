package cases.B2B;

import com.audatex.axn.classchecker.b2b.exceptions.B2bException;
import datamodel.TestResult;
import org.apache.commons.configuration2.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.B2bClient;
import utils.UtilitiesManager;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

public class B2bTest extends B2BTestBase {

    private static final Logger logger = LoggerFactory.getLogger(B2bTest.class);

    public static TestResult testResult = new TestResult();
    protected static Configuration testData;

    @Autowired
    B2bClient b2bClient;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup(Method method, ITestContext context) {
        testResult.setTestName(method.getName());
        testResult.setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
        testResult.setCountry(context.getCurrentXmlTest().getLocalParameters().get("country"));
        testResult.setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));
    }


    @Test
    public void getTask() {

        try {
            testResult.setTimeStarted(Instant.now());
            String response = b2bClient.getTask(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , testData.getString("b2b_taskId"),testData.getString("b2b_responseStylesheet") ,testData.getString("b2b_url"));
            testResult.setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResult.setSuccess(false);
        }
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void doCalculation() {
        B2bClient b2bClient = new B2bClient();

        try {
            testResult.setTimeStarted(Instant.now());
            String response = b2bClient.getCalculation(testData.getString("ins_username"), testData.getString("password")
                    , testData.getString("xrecord"), testData.getString("b2b_url"));
            testResult.setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResult.setSuccess(false);
        }
        testResult.setTimeFinished(Instant.now());
    }

    @AfterMethod
    public synchronized void afterMethod(Method method) {
        testResult.setTimeElapsed(Duration.between(testResult.getTimeStarted(), testResult.getTimeFinished()).toMillis());

        UtilitiesManager.convertToJson(testResult);

        // Send test result to Kibana server
        //  RestManager.sendTestResult(testResult);
    }
}
