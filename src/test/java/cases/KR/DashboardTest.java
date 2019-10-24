package cases.KR;

import cases.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LoginPO;
import pageobjects.PreIntakePO;
import pageobjects.processstep.processstep.ProcessStepKRPO;
import pageobjects.processstep.claimdetails.ClaimDetailsKRPO;
import pageobjects.standalone.DashboardPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class DashboardTest extends TestBase {
    private final static Logger logger = Logger.getLogger(DashboardTest.class);

    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsKRPO claimDetailsKRPO = new ClaimDetailsKRPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepKRPO processStepKRPO = new ProcessStepKRPO();
    private String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup(ITestContext context) {
        loginPO.setWebDriver(getDriver());
        dashboardPO.setWebDriver(getDriver());
        preIntakePO.setWebDriver(getDriver());
        claimDetailsKRPO.setWebDriver(getDriver());
        workListGridOpenPO.setWebDriver(getDriver());
        processStepKRPO.setWebDriver(getDriver());
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

    @Test(description = "Create a new case from dashboard")
    public void createNewCaseFromDashboard() {
        getDriver().get(testData.getString("test_url"));

        //Login page
        loginPO.enterUserName(testData.getString("ins_username"));
        loginPO.enterPassword(testData.getString("password"));
        loginPO.clickSubmit();

        //Work List grid Open
        //dashboardPO.clickCreateCase();
        dashboardPO.clickNewClaimButton();
        testResult.get().setTimeStarted(Instant.now());

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.enterPlateNumberKRTextbox(testData.getString("plate_number"));
        preIntakePO.clickCreateNewCaseButton();
        Assert.assertFalse(isAlertPresent());

        //Claim Details process step
        fluentWait(By.id(ClaimDetailsKRPO.ID_CLAIM_NUMBER));

        testResult.get().setTimeFinished(Instant.now());

        String claimDetailUrl = getDriver().getCurrentUrl();
        String taskId = UtilitiesManager.getTaskIdFromUrl(claimDetailUrl);
        if (taskId != null && taskId.length() > 0) {
            RedisManager.setValue(taskIdKey, taskId);
        }
        else {
            Assert.fail("Task ID is empty");
        }
        Assert.assertNotNull(taskId);

        RedisManager.setValue(taskIdKey, taskId);
        logger.debug("taskIdKey: " + taskIdKey);
        logger.debug("taskId: " + taskId);

//        //Check claim is in Open box
//        processStepKRPO.clickClaimManagerIcon();
//        workListGridOpenPO.clickOpenTab();
//        workListGridOpenPO.sortCreationDate();
//        Assert.assertTrue(workListGridOpenPO.isClaimNumberExist(claimNumber));
//
//        //Logout
//        if (isElementPresent(By.id("logout"))) {
//            workListGridOpenPO.clickLogout();
//        } else {
//            processStepKRPO.clickNavigationActions();
//            workListGridOpenPO.clickLogout();
//        }
//
//        Assert.assertFalse(isAlertPresent());
    }
}
