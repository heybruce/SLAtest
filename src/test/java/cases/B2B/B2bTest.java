package cases.B2B;

import com.audatex.axn.classchecker.b2b.exceptions.B2bException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.*;
import b2b.B2bClient;
import utils.UtilitiesManager;
import java.time.Instant;

public class B2bTest extends B2bTestBase {

    private static final Logger logger = LoggerFactory.getLogger(B2bTest.class);

    @Autowired
    B2bClient b2bClient;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
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
        finally {
            testResult.setTimeFinished(Instant.now());
        }
    }

    @Test
    public void doCalculation() {

        try {
            testResult.setTimeStarted(Instant.now());
            String response = b2bClient.getCalculation(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , testData.getString("xrecord"), testData.getString("b2b_url"));
            testResult.setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResult.setSuccess(false);
        }
        finally {
            testResult.setTimeFinished(Instant.now());
        }
    }

    @Test
    public void vinQuery() {
        try {
            testResult.setTimeStarted(Instant.now());
            String response = b2bClient.vinQuery(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , testData.getString("b2b_vin"), testData.getString("b2b_callingApplication"), testData.getString("b2b_url"));
            testResult.setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResult.setSuccess(false);
        }
        finally {
            testResult.setTimeFinished(Instant.now());
        }
    }


}
