package cases.SG.Qapter;

import cases.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepSGPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class QapterTest extends TestBase {

    private ProcessStepSGPO processStepSGPO = new ProcessStepSGPO();
    private DamageCapturingPO damageCapturingPO = new DamageCapturingPO();
    String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile","vehicleElement"})
    public void setup(String dataFile, String vehicleElement) {
        vehicleElementData = UtilitiesManager.setPropertiesFile(vehicleElement);
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        processStepSGPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
        taskIdKey = testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskId";

        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

//        //Verify and switch to non-3D mode
//        getDriver().get(testData.getString("url_to_damage_capturing"));
//        damageCapturingPO.clickQapterIcon();
//        damageCapturingPO.navigationSettings();
//        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
//        if (is3dView) {
//            damageCapturingPO.waitForElementPresent(By.id("box"));
//            damageCapturingPO.click3dViewSwitch();
//            damageCapturingPO.navigationVehicle();
//            damageCapturingPO.navigationSettings();
//        }
    }

    @Test
    public void switchTo3D(){

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationSettings();

        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
        }

        testResult.get().setTimeStarted(Instant.now());
        //Switch to 3D view
        damageCapturingPO.click3dViewSwitch();
        damageCapturingPO.navigationVehicle();

        testResult.get().setTimeFinished(Instant.now());
    }

    @Test
    public void selectPart(){

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
        }

        damageCapturingPO.navigationVehicle();
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("bmw320_zone_frontOuter"))));
        fluentWait(By.id(vehicleElementData.getString("bmw320_position_0471_Bonnet")));

        //Select a part
        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("bmw320_position_0471_Bonnet"))));
        fluentWait(By.id("menu-items-wrapper"));
        Assert.assertTrue(isElementPresent(By.id("menu-items-wrapper")));

        testResult.get().setTimeFinished(Instant.now());
    }

    @Test
    public void navigate3dModelFromZoneToZone() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
        }

        is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
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

        testResult.get().setTimeStarted(Instant.now());
        //Moving to another zone by clicking right arrow
        damageCapturingPO.click(getDriver().findElement(By.id("an-arrow-right")));
        damageCapturingPO.waitForQapterLoading();
        testResult.get().setTimeFinished(Instant.now());
    }

    @Test
    public void loadZoneIn3dView() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
        }

        is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
                .equalsIgnoreCase("checkbox_slide  active ");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dViewSwitch();
        }

        damageCapturingPO.navigationVehicle();
        damageCapturingPO.clickZoneListDropdown();
        //Select the first item in the dropdown list to enter zone view
        fluentWait(By.id("tree-navigation-description-container-46"));

        testResult.get().setTimeStarted(Instant.now());
        //Select a zone to load the zone view
        damageCapturingPO.click(getDriver().findElement(By.id("tree-navigation-description-container-46")));
        //fluentWait(By.id("an-arrow-right"));
        damageCapturingPO.waitForQapterLoading();
        testResult.get().setTimeFinished(Instant.now());
    }

    @Test
    public void activateQapterIn3dView() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
        }

        is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
                .equalsIgnoreCase("checkbox_slide  active ");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dViewSwitch();
        }

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));
        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.waitForQapterLoading();
        testResult.get().setTimeFinished(Instant.now());
    }

    @Test
    public void switchToVehicleTabIn3dView() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE","DamageCaptureSG"));
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
        }

        is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class")
                .equalsIgnoreCase("checkbox_slide  active ");
        if (!is3dView) {     //if not in 3D view, switch to 3D view
            damageCapturingPO.click3dViewSwitch();
        }

        testResult.get().setTimeStarted(Instant.now());
        damageCapturingPO.navigationVehicle();
        damageCapturingPO.waitForQapterLoading();
        testResult.get().setTimeFinished(Instant.now());
    }
}
