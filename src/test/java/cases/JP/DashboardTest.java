package cases.JP;

import cases.TestBase;
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
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class DashboardTest extends TestBase {
    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsJPPO claimDetailsJPPO = new ClaimDetailsJPPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepJPPO processStepJPPO = new ProcessStepJPPO();

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
    }

    @Test
    public void loginBRE() {
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();

        testResult.setTimeStarted(Instant.now());

        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        testResult.setTimeFinished(Instant.now());

        //Dashboard page
        Assert.assertEquals(testData.getString("ins_username").toUpperCase(), workListGridOpenPO.getLoggedUsername());
    }

}
