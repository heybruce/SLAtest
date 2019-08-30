package cases.JP;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LoginPO;
import pageobjects.PreIntakePO;
import pageobjects.processstep.claimdetails.ClaimDetailsJPPO;
import pageobjects.processstep.claimdetails.ClaimDetailsKRPO;
import pageobjects.processstep.processstep.ProcessStepJPPO;
import pageobjects.standalone.DashboardPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class WorkListOpenBoxJPTest extends TestBase {
    private final static Logger logger = Logger.getLogger(WorkListOpenBoxJPTest.class);

    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsJPPO claimDetailsJPPO = new ClaimDetailsJPPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepJPPO processStepJPPO = new ProcessStepJPPO();
    private String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        loginPO.setWebDriver(getDriver());
        dashboardPO.setWebDriver(getDriver());
        preIntakePO.setWebDriver(getDriver());
        claimDetailsJPPO.setWebDriver(getDriver());
        workListGridOpenPO.setWebDriver(getDriver());
        processStepJPPO.setWebDriver(getDriver());
        taskIdKey = testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskId";
    }

    @Test
    public void loginBRE() {
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();

        testResult.get().setTimeStarted(Instant.now());
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));
        testResult.get().setTimeFinished(Instant.now());

        //Dashboard page
        Assert.assertEquals(testData.getString("ins_username").toUpperCase(), workListGridOpenPO.getLoggedUsername());
    }

    @Test
    public void openExistingCase() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        testResult.get().setTimeStarted(Instant.now());
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic", "ClaimInfoJP"));
        testResult.get().setTimeFinished(Instant.now());

        Assert.assertNotNull(claimDetailsJPPO.getClaimNumber());
    }

    @Test(description = "Create case from header right top button")
    public void createNewCaseFromHeader(){
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        //Work List grid Open
        workListGridOpenPO.clickOpenTab();
        workListGridOpenPO.clickNewClaimButton();
        testResult.get().setTimeStarted(Instant.now());

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.clickCreateNewCaseButton();
        fluentWait(By.id(ClaimDetailsJPPO.ID_CLAIM_NUMBER));
        testResult.get().setTimeFinished(Instant.now());

        String claimDetailUrl = getDriver().getCurrentUrl();
        String taskId = UtilitiesManager.getTaskIdFromUrl(claimDetailUrl);
        Assert.assertNotNull(taskId);

        RedisManager.setValue(taskIdKey, taskId);
        logger.debug("taskIdKey: " + taskIdKey);
        logger.debug("taskId: " + taskId);

//        //Check claim is in Open box
//        processStepJPPO.clickClaimManager();
//        workListGridOpenPO.clickOpenTab();
//        workListGridOpenPO.sortCreationDate();
//        Assert.assertTrue(workListGridOpenPO.isClaimNumberExist(claimNumber));
//
//        //Logout
//        processStepJPPO.openCollapsedMenu();
//        if (isElementPresent(By.id("logout"))) {
//            workListGridOpenPO.clickLogout();
//        } else {
//            processStepJPPO.clickNavigationActions();
//            workListGridOpenPO.clickLogout();
//        }
//
//        Assert.assertFalse(isAlertPresent());
    }

}
