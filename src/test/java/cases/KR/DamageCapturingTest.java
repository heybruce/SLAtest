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

import static pageobjects.processstep.DamageCapturingPO.INSIDE_LOADING_CIRCLE;
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

    @Test
    public void activateQapter(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        testResult.setTimeStarted(Instant.now());

        getDriver().get(testData.getString("url_to_damage_capturing"));
        damageCapturingPO.clickQapterIcon();
        damageCapturingPO.navigationVehicle();

        testResult.setTimeFinished(Instant.now());
    }
    @Test
    public void switchTo3D(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(testData.getString("url_to_damage_capturing"));
        damageCapturingPO.clickQapterIcon();
        damageCapturingPO.navigationSettings();

        Boolean is3dView = !(damageCapturingPO.getThreeDView().getAttribute("class").equalsIgnoreCase("left"));
        if (is3dView) {     //if already in 3D view, switch to non-3D view
            damageCapturingPO.click3dView();
        }

        testResult.setTimeStarted(Instant.now());
        //Switch to 3D view
        damageCapturingPO.click3dView();
        damageCapturingPO.navigationVehicle();

        testResult.setTimeFinished(Instant.now());

        //Switch back to non-3D view
        damageCapturingPO.navigationSettings();
        damageCapturingPO.click3dView();

        is3dView = !(damageCapturingPO.getThreeDView().getAttribute("class").equalsIgnoreCase("left"));
        if (is3dView) {     //if already in 3D view, switch to non-3D view
            damageCapturingPO.click3dView();
            damageCapturingPO.navigationVehicle();
        }

        //Workaround to save 3D view setting
        getDriver().get(testData.getString("url_to_claim_details"));
    }
}