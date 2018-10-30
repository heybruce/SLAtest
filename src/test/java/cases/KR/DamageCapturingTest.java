package cases.KR;

import cases.TestBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepKRPO;
import steps.Login;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class DamageCapturingTest extends TestBase{
    private ProcessStepKRPO processStepKRPO = new ProcessStepKRPO();
    private DamageCapturingPO damageCapturingPO = new DamageCapturingPO();

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {

        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        processStepKRPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
    }

    @Test(description = "Damage capturing in Qapter")
    public void activateQapter(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        //Switch to Damage Capturing page
        testResult.setTimeStarted(Instant.now());

        getDriver().get(testData.getString("url_to_damage_capturing"));
        damageCapturingPO.clickQapterIcon();
        damageCapturingPO.navigationVehicle();

        testResult.setTimeFinished(Instant.now());
    }
}