package cases.JP.Qapter;

import cases.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepJPPO;
import steps.DamageCapturing;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class QapterTest extends TestBase {

    private ProcessStepJPPO processStepJPPO = new ProcessStepJPPO();
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
        processStepJPPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
        taskIdKey = testResult.getEnv() + "_" + testResult.getCountry() + "_taskId";

        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

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
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic","DamageCaptureJP"));
        damageCapturingPO.switchToQapterIframe();

        damageCapturingPO.navigationSettings();
        Boolean is3dView = damageCapturingPO.getThreeDViewIndicator().getAttribute("class").equalsIgnoreCase("checkbox_slide  active ");
        if (is3dView) {
            damageCapturingPO.waitForElementPresent(By.id("box"));
            damageCapturingPO.click3dViewSwitch();
            damageCapturingPO.navigationVehicle();
            damageCapturingPO.navigationSettings();
        }

        testResult.setTimeStarted(Instant.now());
        //Switch to 3D view
        damageCapturingPO.click3dViewSwitch();
        damageCapturingPO.navigationVehicle();

        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void selectPart(){

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic","DamageCaptureJP"));
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
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("benzE_zone_frontOuter"))));
        damageCapturingPO.click(getDriver().findElement(By.id("navigation-vehichle-tree-navigation-arrow")));
        fluentWait(By.id("tree-navigation-zone-icon-32"));
        damageCapturingPO.click(getDriver().findElement(By.id("tree-navigation-zone-icon-32")));
        fluentWait(By.id(vehicleElementData.getString("benzE_position_0257_frontNumberPlate")));

        //Select a part
        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.click(getDriver().findElement(By.id(vehicleElementData.getString("benzE_position_0257_frontNumberPlate"))));
//        fluentWait(By.id("menu-items-wrapper"));
//        Assert.assertTrue(isElementPresent(By.id("menu-items-wrapper")));

        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void navigate3dModelFromZoneToZone() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic","DamageCaptureJP"));
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

        testResult.setTimeStarted(Instant.now());
        //Moving to another zone by clicking right arrow
        damageCapturingPO.click(getDriver().findElement(By.id("an-arrow-right")));
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void loadZoneIn3dView() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic","DamageCaptureJP"));
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

        testResult.setTimeStarted(Instant.now());
        //Select a zone to load the zone view
        damageCapturingPO.click(getDriver().findElement(By.id("tree-navigation-description-container-46")));
        //fluentWait(By.id("an-arrow-right"));
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void activateQapterIn3dView() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic","DamageCaptureJP"));
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
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic","DamageCaptureJP"));
        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void switchToVehicleTabIn3dView() {

        //Verify and switch to non-3D mode
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic","DamageCaptureJP"));
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

        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.navigationVehicle();
        damageCapturingPO.waitForQapterLoading();
        testResult.setTimeFinished(Instant.now());
    }
}
