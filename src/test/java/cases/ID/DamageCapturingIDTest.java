package cases.ID;

import cases.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepIDPO;
import steps.Login;
import utils.UtilitiesManager;

import java.time.Instant;

import static pageobjects.processstep.DamageCapturingPO.INSIDE_LOADING_CIRCLE;
import static utils.webdrivers.WebDriverFactory.getDriver;

public class DamageCapturingIDTest extends TestBase{
    private ProcessStepIDPO processStepIDPO = new ProcessStepIDPO();
    private DamageCapturingPO damageCapturingPO = new DamageCapturingPO();

    @BeforeClass
    @Parameters(value = {"dataFile","vehicleElement"})
    public void setup(String dataFile, String vehicleElement) {
        vehicleElementData = UtilitiesManager.setPropertiesFile(vehicleElement);
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        processStepIDPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
    }

    @Test
    public void activateQapter(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));


        testResult.setTimeStarted(Instant.now());
        getDriver().get(testData.getString("url_to_DamageCaptureID"));

        damageCapturingPO.waitForElementInvisible(INSIDE_LOADING_CIRCLE);
        testResult.setTimeFinished(Instant.now());
    }
}