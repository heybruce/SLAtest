package cases.KR.Qapter;

import b2b.B2bClient;
import cases.TestBase;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepKRPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class ChecklistTest extends TestBase {
    private ProcessStepKRPO processStepKRPO = new ProcessStepKRPO();
    private DamageCapturingPO damageCapturingPO = new DamageCapturingPO();
    private WebDriverWait wait;
    private String taskIdKey;

    @Autowired
    B2bClient b2bClient;

    @BeforeClass
    @Parameters(value = {"dataFile", "vehicleElement"})
    public void setup(String dataFile, String vehicleElement) {
        vehicleElementData = UtilitiesManager.setPropertiesFile(vehicleElement);
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        processStepKRPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
        wait = new WebDriverWait(getDriver(), 10);
        taskIdKey = testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskId";
    }

    @Test
    public void loadChecklist() {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE", "DamageCapturing"));

        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.navigationChecklist();
        testResult.get().setTimeFinished(Instant.now());

        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertTrue(checklistNumber > 0);
    }

    @Test
    public void loadChecklistWithOver100Parts() throws SAXException, IOException, ParserConfigurationException {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        //use b2b to create a claim with 100 parts
        String taskId = b2bClient.createTask(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                , testData.getString("b2b_taskXml_100part"), testData.getString("b2b_url"));
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), taskId, "BRE", "DamageCapturing"));

        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.navigationVehicle();
        damageCapturingPO.navigationChecklist();
        testResult.get().setTimeFinished(Instant.now());

        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertTrue(checklistNumber > 0);
    }

    @Test
    public void loadChecklistWithOver200Parts() throws SAXException, IOException, ParserConfigurationException {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        //use b2b to create a claim with 200 parts
        String taskId = b2bClient.createTask(testData.getString("b2b_loginId"), testData.getString("b2b_password")
                , testData.getString("b2b_taskXml_200part"), testData.getString("b2b_url"));
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), taskId, "BRE", "DamageCapturing"));

        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.navigationChecklist();
        testResult.get().setTimeFinished(Instant.now());

        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertTrue(checklistNumber > 0);
    }
}
