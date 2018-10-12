package cases.KR;

import cases.TestBase;
import datamodel.TestResult;
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
import utils.RestManager;
import utils.UtilitiesManager;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class DashboardTest extends TestBase {
    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsKRPO claimDetailsKRPO = new ClaimDetailsKRPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepKRPO processStepKRPO = new ProcessStepKRPO();

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
        claimDetailsKRPO.setWebDriver(getDriver());
        workListGridOpenPO.setWebDriver(getDriver());
        processStepKRPO.setWebDriver(getDriver());
    }

    @Test
    public void loginBRE() {
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();

        testResult.setTimeStarted(Instant.now());

        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        //Dashboard page
        Assert.assertEquals(testData.getString("ins_username").toUpperCase(), workListGridOpenPO.getLoggedUsername());

        testResult.setTimeFinished(Instant.now());
        testResult.setTimeElapsed(Duration.between(testResult.getTimeStarted(), testResult.getTimeFinished()).toMillis());
    }

    @Test(description = "Create a new case from dashboard")
    public void createNewCaseFromDashboard() {
        getDriver().get(testData.getString("test_url"));

        //Login page
        loginPO.enterUserName(testData.getString("ins_username"));
        loginPO.enterPassword(testData.getString("password"));
        loginPO.clickSubmit();

        //Work List grid Open
        dashboardPO.clickCreateCase();
        testResult.setTimeStarted(Instant.now());

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.enterPlateNumberKRTextbox(testData.getString("plate_number"));
        preIntakePO.clickCreateNewCaseButton();
        Assert.assertFalse(isAlertPresent());

        //Claim Details process step
        fluentWait(By.id(ClaimDetailsKRPO.ID_CLAIM_NUMBER));

        testResult.setTimeFinished(Instant.now());
        testResult.setTimeElapsed(Duration.between(testResult.getTimeStarted(), testResult.getTimeFinished()).toMillis());

        //Check claim is in Open box
        processStepKRPO.clickClaimManagerIcon();
        workListGridOpenPO.clickOpenTab();
        workListGridOpenPO.sortCreationDate();
        Assert.assertTrue(workListGridOpenPO.isClaimNumberExist(claimNumber));

        //Logout
        if (isElementPresent(By.id("logout"))) {
            workListGridOpenPO.clickLogout();
        } else {
            processStepKRPO.clickNavigationActions();
            workListGridOpenPO.clickLogout();
        }

        Assert.assertFalse(isAlertPresent());
    }
}
