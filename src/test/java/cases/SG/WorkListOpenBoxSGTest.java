package cases.SG;

import cases.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LoginPO;
import pageobjects.PreIntakePO;
import pageobjects.processstep.claimdetails.ClaimDetailsSGPO;
import pageobjects.processstep.processstep.ProcessStepSGPO;
import pageobjects.standalone.DashboardPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class WorkListOpenBoxSGTest extends TestBase {
    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsSGPO claimDetailsSGPO = new ClaimDetailsSGPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepSGPO processStepSGPO = new ProcessStepSGPO();

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
        claimDetailsSGPO.setWebDriver(getDriver());
        workListGridOpenPO.setWebDriver(getDriver());
        processStepSGPO.setWebDriver(getDriver());
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
