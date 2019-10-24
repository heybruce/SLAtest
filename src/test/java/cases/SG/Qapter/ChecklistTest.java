package cases.SG.Qapter;

import b2b.B2bClient;
import cases.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepSGPO;
import steps.Login;
import steps.Qapter.Checklist;
import utils.RedisManager;
import utils.UtilitiesManager;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class ChecklistTest extends TestBase {
    private WebDriverWait wait;
    private ProcessStepSGPO processStepSGPO = new ProcessStepSGPO();
    private DamageCapturingPO damageCapturingPO = new DamageCapturingPO();
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
        wait = new WebDriverWait(getDriver(), 60);
        processStepSGPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
        taskIdKey = testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskId";
    }

    @Test
    public void loadChecklist() {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));

        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationChecklist();
        testResult.get().setTimeFinished(Instant.now());
    }

    @Test
    public void loadChecklistWithOver100Parts() throws SAXException, IOException, ParserConfigurationException {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        String taskId = RedisManager.getValue(testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskIdWith100Part");
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), taskId, "BRE", "DamageCaptureSG"));

        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.switchToQapterIframe();
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
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        String taskId = RedisManager.getValue(testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskIdWith200Part");
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), taskId, "BRE", "DamageCaptureSG"));

        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationChecklist();
        testResult.get().setTimeFinished(Instant.now());

        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertTrue(checklistNumber > 0);
    }
}
