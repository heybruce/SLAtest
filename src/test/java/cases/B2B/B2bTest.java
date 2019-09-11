package cases.B2B;

import com.audatex.axn.classchecker.b2b.exceptions.B2bException;
import com.audatex.tw.gateway.service.Message;
import com.audatex.tw.gateway.service.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import b2b.B2bClient;
import org.xml.sax.SAXException;
import utils.RedisManager;
import utils.UtilitiesManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Instant;

public class B2bTest extends B2bTestBase {

    private static final Logger logger = LoggerFactory.getLogger(B2bTest.class);

    String taskIdKey;

    @Autowired
    B2bClient b2bClient;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void beforeMethod() {
        taskIdKey = testResultB2b.get().getEnv() + "_" + testResultB2b.get().getCountry() + "_taskId";
    }

    @Test
    public void getTask() {
        String taskId = RedisManager.getValue(taskIdKey);

        try {
            testResultB2b.get().setTimeStarted(Instant.now());
            String response = b2bClient.getTask(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , taskId,testData.getString("b2b_responseStylesheet") ,testData.getString("b2b_url"));
            testResultB2b.get().setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResultB2b.get().setSuccess(false);
        }
        finally {
            testResultB2b.get().setTimeFinished(Instant.now());
        }
    }

    @Test
    public void doCalculation(ITestContext context) {
        String country = context.getCurrentXmlTest().getLocalParameters().get("country");
        try {
            String newXrecord = UtilitiesManager.addTrailingSpaceToXrecord(testData.getString("b2b_xrecord"));
            testResultB2b.get().setTimeStarted(Instant.now());
            String response = b2bClient.getCalculation(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , newXrecord, country, testData.getString("b2b_url"));
            testResultB2b.get().setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResultB2b.get().setSuccess(false);
        }
        finally {
            testResultB2b.get().setTimeFinished(Instant.now());
        }
    }

    @Test
    public void vinQuery() {
        try {
            testResultB2b.get().setTimeStarted(Instant.now());
            String response = b2bClient.vinQuery(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , testData.getString("b2b_vin"), testData.getString("b2b_callingApplication"), testData.getString("b2b_url"));
            testResultB2b.get().setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResultB2b.get().setSuccess(false);
        }
        finally {
            testResultB2b.get().setTimeFinished(Instant.now());
        }
    }

    @Test
    public void createTaskWith200Part() throws SAXException, IOException, ParserConfigurationException {
        try {
            testResultB2b.get().setTimeStarted(Instant.now());
            String response = b2bClient.createTask(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , testData.getString("b2b_taskXml_200part"), testData.getString("b2b_url"));
            testResultB2b.get().setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResultB2b.get().setSuccess(false);
        }
        finally {
            testResultB2b.get().setTimeFinished(Instant.now());
        }
    }

    @Test
    public void createTaskWith100Part() throws SAXException, IOException, ParserConfigurationException {
        try {
            testResultB2b.get().setTimeStarted(Instant.now());
            String response = b2bClient.createTask(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                    , testData.getString("b2b_taskXml_100part"), testData.getString("b2b_url"));
            testResultB2b.get().setSuccess(true);
        }
        catch(B2bException e) {
            logger.error("Error executing B2B request", e);
            testResultB2b.get().setSuccess(false);
        }
        finally {
            testResultB2b.get().setTimeFinished(Instant.now());
        }
    }

    @Test
    public void getTaskTwVolvoClaimClosedFolderTest() {
        Task response = b2bClient.getTaskTwVolvo(testData.getString("qw_b2b_loginId"),
                testData.getString("qw_b2b_claimNumber_claimInClosedFolder"), testData.getString("qw_b2b_url"));
        Assert.assertEquals(response.getResultCode(), 0);
        Assert.assertEquals(response.getResultMsg(), "下载定损单成功");
    }

    @Test
    public void uploadTaskTwVolvoWithClaimNumberTest() {
        Message response = b2bClient.uploadTaskTwVolvo(testData.getString("qw_b2b_loginId")
                , testData.getString("qw_b2b_claimNumber_uploadTask")
                , testData.getString("qw_b2b_vin")
                , testData.getString("qw_b2b_plateNumber")
                , testData.getString("qw_b2b_insuranceName")
                , testData.getString("qw_b2b_url"));
        Assert.assertEquals(response.getResultCode(), 1);
        Assert.assertEquals(response.getResultMsg(), "上传成功");
    }
}