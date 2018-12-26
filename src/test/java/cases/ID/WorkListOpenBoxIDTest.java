package cases.ID;

import cases.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.LoginPO;
import pageobjects.PreIntakePO;
import pageobjects.processstep.claimdetails.ClaimDetailsPO;
import pageobjects.processstep.processstep.ProcessStepIDPO;
import pageobjects.standalone.DashboardPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class WorkListOpenBoxIDTest extends TestBase {
    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsPO claimDetailsPO = new ClaimDetailsPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepIDPO processStepIDPO = new ProcessStepIDPO();

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
        claimDetailsPO.setWebDriver(getDriver());
        workListGridOpenPO.setWebDriver(getDriver());
        processStepIDPO.setWebDriver(getDriver());
    }

    @Test
    public void loginBRE() {
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();

        testResult.setTimeStarted(Instant.now());

        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        testResult.setTimeFinished(Instant.now());

        //Dashboard page
        Assert.assertEquals(testData.getString("rep_username").toUpperCase(), workListGridOpenPO.getLoggedUsername());
    }

    @Test
    public void openExistingCase() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        testResult.setTimeStarted(Instant.now());
        getDriver().get(testData.getString("url_to_GeneralDetailsID"));
        testResult.setTimeFinished(Instant.now());

        Assert.assertNotNull(claimDetailsPO.getClaimNumber());
    }

}
