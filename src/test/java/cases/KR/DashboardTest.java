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
    public void loginBRE(Method method, ITestContext context) {
        getDriver().get(testData.getString("test_url"));

        TestResult result = new TestResult();
        result.setTestName(method.getName());
        result.setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
        result.setCountry(context.getSuite().getName());
        result.setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));

        //Login
        Login login = new Login();

        result.setTimeStarted(Instant.now());
//        testResult.get().setTimeStarted(Instant.now());

        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        //Dashboard page
        Assert.assertEquals(testData.getString("ins_username").toUpperCase(), workListGridOpenPO.getLoggedUsername());


        result.setTimeFinished(Instant.now());
//        testResult.get().setTimeFinished(Instant.now());

        result.setTimeElapsed(Duration.between(result.getTimeStarted(), result.getTimeFinished()).toMillis());
//        testResult.get().setTimeElapsed(Duration.between(testResult.get().getTimeStarted(), testResult.get().getTimeFinished()).toMillis());

        UtilitiesManager.convertToJson(result);
        RestManager.sendTestResult(result);
    }

    @Test(description = "Create a new case from dashboard")
    public void createNewCaseFromDashboard(Method method, ITestContext context) {
        getDriver().get(testData.getString("test_url"));
        TestResult result = new TestResult();
        result.setTestName(method.getName());
        result.setBrowser(context.getCurrentXmlTest().getLocalParameters().get("browser"));
        result.setCountry(context.getSuite().getName());
        result.setEnv(context.getCurrentXmlTest().getLocalParameters().get("env"));

        //Login page
        loginPO.enterUserName(testData.getString("ins_username"));
        loginPO.enterPassword(testData.getString("password"));
        loginPO.clickSubmit();

        //Work List grid Open
        dashboardPO.clickCreateCase();
        result.setTimeStarted(Instant.now());

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.enterPlateNumberKRTextbox(testData.getString("plate_number"));
        preIntakePO.clickCreateNewCaseButton();
        Assert.assertFalse(isAlertPresent());

        //Claim Details process step
        fluentWait(By.id(ClaimDetailsKRPO.ID_CLAIM_NUMBER));

        result.setTimeFinished(Instant.now());
        result.setTimeElapsed(Duration.between(result.getTimeStarted(), result.getTimeFinished()).toMillis());

        //Check claim is in Open box
        processStepKRPO.clickClaimManagerIcon();
        workListGridOpenPO.clickOpenTab();
        workListGridOpenPO.sortCreationDate();
        Assert.assertTrue(workListGridOpenPO.isClaimNumberExist(claimNumber));


        UtilitiesManager.convertToJson(result);
        RestManager.sendTestResult(result);

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
