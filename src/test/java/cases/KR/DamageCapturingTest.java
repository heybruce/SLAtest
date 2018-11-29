package cases.KR;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepKRPO;
import steps.CreateNewCaseKR;
import steps.Login;
import steps.Qapter.ZoneAndLayout;
import utils.UtilitiesManager;

import java.time.Instant;

import static pageobjects.processstep.DamageCapturingPO.INSIDE_LOADING_CIRCLE;
import static utils.webdrivers.WebDriverFactory.getDriver;

public class DamageCapturingTest extends TestBase{
    private ProcessStepKRPO processStepKRPO = new ProcessStepKRPO();
    private DamageCapturingPO damageCapturingPO = new DamageCapturingPO();

    @BeforeClass
    @Parameters(value = {"dataFile","vehicleElement"})
    public void setup(String dataFile, String vehicleElement) {
        vehicleElementData = UtilitiesManager.setPropertiesFile(vehicleElement);
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

        getDriver().get(testData.getString("url_to_damage_capturing"));
        damageCapturingPO.clickQapterIcon();

        testResult.setTimeStarted(Instant.now());
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

        Boolean is3dView = damageCapturingPO.getThreeDView().getAttribute("class").equalsIgnoreCase("checkbox_slide active");
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

        is3dView = damageCapturingPO.getThreeDView().getAttribute("class").equalsIgnoreCase("checkbox_slide active");
        if (is3dView) {     //if already in 3D view, switch to non-3D view
            damageCapturingPO.click3dView();
            damageCapturingPO.navigationVehicle();
        }

        //Workaround to save 3D view setting
        getDriver().get(testData.getString("url_to_claim_details"));
    }

    @Test
    public void selectPart(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(testData.getString("url_to_damage_capturing"));
        damageCapturingPO.clickQapterIcon();
        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDView().getAttribute("class").equalsIgnoreCase("checkbox_slide active");
        if (is3dView) {     //if already in 3D view, switch to non-3D view
            damageCapturingPO.click3dView();
        }

        damageCapturingPO.navigationVehicle();
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("bmw320_zone_frontOuter"))));
        fluentWait(By.id(vehicleElementData.getString("bmw320_position_0471_Bonnet")));
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("bmw320_position_0471_Bonnet"))));

        testResult.setTimeStarted(Instant.now());
        fluentWait(By.id("menu-items-wrapper"));
        Assert.assertTrue(isElementPresent(By.id("menu-items-wrapper")));

        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void navigate3dModelFromZoneToZone() {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(testData.getString("url_to_damage_capturing"));
        damageCapturingPO.clickQapterIcon();
        damageCapturingPO.navigationSettings();

        Boolean is3dView = damageCapturingPO.getThreeDView().getAttribute("class").equalsIgnoreCase("checkbox_slide active");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dView();
        }

        damageCapturingPO.navigationVehicle();
        damageCapturingPO.clickZoneListDropdown();
        //Select the first item in the dropdown list to enter zone view
        fluentWait(By.id("tree-navigation-description-container-32"));
        damageCapturingPO.click(getDriver().findElement(By.id("tree-navigation-description-container-32")));
        //Make dropdown list disappear
        damageCapturingPO.clickZoneListDropdownCollapse();
        fluentWait(By.id("an-arrow-right"));

        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.click(getDriver().findElement(By.id("an-arrow-right")));
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());

        damageCapturingPO.navigationSettings();
        is3dView = damageCapturingPO.getThreeDView().getAttribute("class").equalsIgnoreCase("checkbox_slide active");
        if (is3dView) {     //if in 3D view, switch to non-3D view
            damageCapturingPO.click3dView();
            damageCapturingPO.navigationVehicle();
        }
        //Workaround to save 3D view setting
        getDriver().get(testData.getString("url_to_claim_details"));
    }
}