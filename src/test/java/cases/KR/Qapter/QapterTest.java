package cases.KR.Qapter;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepKRPO;
import steps.CreateNewCaseKR;
import steps.Login;
import steps.Qapter.ZoneAndLayout;
import utils.UtilitiesManager;

import java.lang.reflect.Method;
import java.time.Instant;

import static pageobjects.processstep.DamageCapturingPO.INSIDE_LOADING_CIRCLE;
import static utils.webdrivers.WebDriverFactory.getDriver;

public class QapterTest extends TestBase{
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

        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        //Verify and switch to non-3D mode
        getDriver().get(testData.getString("url_to_damage_capturing"));
        damageCapturingPO.clickQapterIcon();
        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
     //       is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        }
    }

    @Test
    public void switchTo3D(){

        testResult.setTimeStarted(Instant.now());
        //Switch to 3D view
        damageCapturingPO.click3dViewSwitch();
        damageCapturingPO.navigationVehicle();

        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void selectPart(){

        damageCapturingPO.navigationVehicle();
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("bmw320_zone_frontOuter"))));
        fluentWait(By.id(vehicleElementData.getString("bmw320_position_0471_Bonnet")));

        //Select a part
        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("bmw320_position_0471_Bonnet"))));
        fluentWait(By.id("menu-items-wrapper"));
        Assert.assertTrue(isElementPresent(By.id("menu-items-wrapper")));

        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void navigate3dModelFromZoneToZone() {

        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
                .equalsIgnoreCase("checkbox_slide  active ");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dViewSwitch();
        }

        damageCapturingPO.navigationVehicle();
        damageCapturingPO.clickZoneListDropdown();
        //Select the first item in the dropdown list to enter zone view
        fluentWait(By.id("tree-navigation-description-container-47"));
        damageCapturingPO.click(getDriver().findElement(By.id("tree-navigation-description-container-47")));
        //Make dropdown list disappear
        damageCapturingPO.clickZoneListDropdownCollapse();
        fluentWait(By.id("an-arrow-right"));

        testResult.setTimeStarted(Instant.now());
        //Moving to another zone by clicking right arrow
        damageCapturingPO.click(getDriver().findElement(By.id("an-arrow-right")));
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void loadZoneIn3dView() {

        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
                .equalsIgnoreCase("checkbox_slide  active ");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dViewSwitch();
        }

        damageCapturingPO.navigationVehicle();
        damageCapturingPO.clickZoneListDropdown();
        //Select the first item in the dropdown list to enter zone view
        fluentWait(By.id("tree-navigation-description-container-46"));

        testResult.setTimeStarted(Instant.now());
        //Select a zone to load the zone view
        damageCapturingPO.click(getDriver().findElement(By.id("tree-navigation-description-container-46")));
        //fluentWait(By.id("an-arrow-right"));
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void activateQapterIn3dView() {

        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
                .equalsIgnoreCase("checkbox_slide  active ");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dViewSwitch();
        }

        getDriver().get(testData.getString("url_to_damage_capturing"));
        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.clickQapterIcon();
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void switchToVehicleTabIn3dView() {

        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
                .equalsIgnoreCase("checkbox_slide  active ");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dViewSwitch();
        }

        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.navigationVehicle();
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }



}