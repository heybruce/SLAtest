package cases.JP.Qapter;

import cases.TestBase;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.processstep.ProcessStepJPPO;
import steps.Login;
import steps.Qapter.Checklist;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class ChecklistTest extends TestBase {
    private WebDriverWait wait;
    private ProcessStepJPPO processstepJPPO;
    private DamageCapturingPO damageCapturingPO;
    private String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile", "vehicleElement"})
    public void setup(String dataFile, String vehicleElement) {
        vehicleElementData = UtilitiesManager.setPropertiesFile(vehicleElement);
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        wait = new WebDriverWait(getDriver(), 10);
        processstepJPPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
        taskIdKey = testResult.getEnv() + "_" + testResult.getCountry() + "_taskId";
    }

//    @Test(description = "Layout test for model option in checklist")
    public void modelOptionTabInChecklist(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey),"BRE", "DamageCapturing"));
        damageCapturingPO.clickQapterIcon();

        //Qapter checklist
        Checklist checklist = new Checklist();
        checklist.modelOptionTabInChecklist();

        Assert.assertTrue(damageCapturingPO.getModelOptionNumberInChecklist()>0);
    }

//    @Test(description = "Check added parts are consistent after Qapter re-launched")
    public void consistentAfterQapterResumed(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey),"BRE", "DamageCapturing"));
        damageCapturingPO.clickQapterIcon();

        Checklist checklist = new Checklist();
        int addedPartsNumber = checklist.consistentAfterQapterResumed(
                vehicleElementData.getString("benzE_zone_frontOuter"), vehicleElementData.getString("benzE_position_0471_Bonnet"),
                vehicleElementData.getString("benzE_zone_rearDoors"),vehicleElementData.getString("benzE_position_1781And1782Door"));
        Assert.assertEquals(addedPartsNumber, 2);

        getDriver().switchTo().defaultContent();
        processstepJPPO.clickCalculationOptionsTab();
        //Switch to Damage Capturing page
        processstepJPPO.clickDamageCaptureTab();
        damageCapturingPO.switchToQapterIframe();
        damageCapturingPO.navigationChecklist();

        Assert.assertEquals(damageCapturingPO.getChecklistNumber(), addedPartsNumber);
    }

//    @Test(description = "Add standard and non-standard position from checklist")
    public void addPositionInChecklist(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey),"BRE", "DamageCapturing"));
        damageCapturingPO.clickQapterIcon();

        //Checklist
        Checklist checklist = new Checklist();
        checklist.addPositionInChecklist(vehicleElementData.getString("bmw320_frontRoof_guideNo"));

        //Verification
        Assert.assertEquals(damageCapturingPO.getChecklistNumber(), 2);
        Assert.assertEquals(damageCapturingPO.getChecklistPartDescription(1), vehicleElementData.getString("bmw320_2385_part_description"));
        Assert.assertEquals(damageCapturingPO.getChecklistGuideNumber(1), vehicleElementData.getString("bmw320_frontRoof_guideNo"));
        Assert.assertEquals(damageCapturingPO.getChecklistRepairMethod(1), vehicleElementData.getString("replace_with_oem"));
        Assert.assertEquals(damageCapturingPO.getChecklistPartDescription(2), "Automation Test");
        Assert.assertEquals(damageCapturingPO.getChecklistGuideNumber(2), "1000");
        Assert.assertEquals(damageCapturingPO.getChecklistRepairMethod(2), vehicleElementData.getString("repair"));
    }

//    @Test(description = "Edit added parts in checklist")
    public void editPositionInChecklist(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey),"BRE", "DamageCapturing"));
        damageCapturingPO.clickQapterIcon();

        //Checklist
        Checklist checklist = new Checklist();
        checklist.editPositionInChecklist(
                vehicleElementData.getString("benzE_zone_frontOuter"), vehicleElementData.getString("benzE_position_0471_Bonnet"));

        //Verification
        Assert.assertEquals(damageCapturingPO.getChecklistPartDescription(1), vehicleElementData.getString("benzE_0471_part_description"));
        Assert.assertTrue(damageCapturingPO.getChecklistRepairMethod(1).contains(vehicleElementData.getString("replace_with_oem")));
    }

//    @Test(description = "Delete added part in checklist")
    public void deletePositionInChecklist(){
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey),"BRE", "DamageCapturing"));
        damageCapturingPO.clickQapterIcon();

        //Checklist
        Checklist checklist = new Checklist();
        checklist.deletePositionInChecklist(
                vehicleElementData.getString("benzE_zone_frontOuter"), vehicleElementData.getString("benzE_position_0471_Bonnet"));

        //Verification
        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertEquals(checklistNumber, 0);
        damageCapturingPO.navigationVehicle();
        Assert.assertFalse(damageCapturingPO.isPartSelected(vehicleElementData.getString("benzE_zone_frontOuter")));
        damageCapturingPO.clickZone(vehicleElementData.getString("benzE_zone_frontOuter"));
        Assert.assertFalse(damageCapturingPO.isPartSelected(vehicleElementData.getString("benzE_position_0471_Bonnet")));
    }

    @Test
    public void loadChecklist() {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic", "DamageCaptureJP"));
        damageCapturingPO.clickQapterIcon();

        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.navigationChecklist();
        testResult.setTimeFinished(Instant.now());

        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertTrue(checklistNumber > 0);
    }

    @Test
    public void loadChecklistWithOver100Parts() {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(testData.getString("url_checklist_over_100"));
        damageCapturingPO.clickQapterIcon();

        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.navigationChecklist();
        testResult.setTimeFinished(Instant.now());

        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertTrue(checklistNumber > 0);
    }

    @Test
    public void loadChecklistWithOver200Parts() {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(testData.getString("url_checklist_over_200"));
        damageCapturingPO.clickQapterIcon();

        testResult.setTimeStarted(Instant.now());
        damageCapturingPO.navigationChecklist();
        testResult.setTimeFinished(Instant.now());

        int checklistNumber = damageCapturingPO.getChecklistNumber();
        Assert.assertTrue(checklistNumber > 0);
    }
}
