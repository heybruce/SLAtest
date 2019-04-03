package cases.SG;

import cases.JP.WorkListOpenBoxJPTest;
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
import pageobjects.processstep.claimdetails.ClaimDetailsPO;
import pageobjects.processstep.claimdetails.ClaimDetailsSGPO;
import pageobjects.processstep.processstep.ProcessStepSGPO;
import pageobjects.standalone.DashboardPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class WorkListOpenBoxSGTest extends TestBase {
    private final static Logger logger = Logger.getLogger(WorkListOpenBoxSGTest.class);

    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsSGPO claimDetailsSGPO = new ClaimDetailsSGPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepSGPO processStepSGPO = new ProcessStepSGPO();
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
        claimDetailsSGPO.setWebDriver(getDriver());
        workListGridOpenPO.setWebDriver(getDriver());
        processStepSGPO.setWebDriver(getDriver());
        taskIdKey = testResult.getEnv() + "_" + testResult.getCountry() + "_taskId";
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

    @Test
    public void openExistingCase() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        testResult.setTimeStarted(Instant.now());
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE", "GeneralDetailsSG"));
        testResult.setTimeFinished(Instant.now());

        Assert.assertNotNull(claimDetailsSGPO.getClaimNumber());
    }

    @Test
    public void createNewCaseFromHeader(){
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        //Work List grid Open
        workListGridOpenPO.clickCustomOpenTab();
        workListGridOpenPO.clickNewClaimButton();
        testResult.setTimeStarted(Instant.now());

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.selectCompany("0");
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.enterVehicleRegistrationNumberTextbox(testData.getString("plate_number"));
        preIntakePO.clickCreateNewCaseButton();
        fluentWait(By.id(ClaimDetailsPO.ID_CLAIM_NUMBER));
        testResult.setTimeFinished(Instant.now());

        //set taskId once case is created
        String claimDetailUrl = getDriver().getCurrentUrl();
        String taskId = UtilitiesManager.getTaskIdFromUrl(claimDetailUrl);
        RedisManager.setValue(taskIdKey, taskId);
        logger.debug("taskIdKey: " + taskIdKey);
        logger.debug("taskId: " + taskId);

        //Check claim is in Open box
        processStepSGPO.clickClaimManager();
        workListGridOpenPO.clickCustomOpenTab();
        workListGridOpenPO.sortCreationDate();
        Assert.assertTrue(workListGridOpenPO.isClaimNumberExist(claimNumber));
    }

}
