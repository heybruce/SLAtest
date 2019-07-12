package cases.ID;

import cases.TestBase;
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
import pageobjects.processstep.processstep.ProcessStepIDPO;
import pageobjects.standalone.DashboardPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class WorkListOpenBoxIDTest extends TestBase {
    private final static Logger logger = Logger.getLogger(WorkListOpenBoxIDTest.class);

    private LoginPO loginPO = new LoginPO();
    private DashboardPO dashboardPO = new DashboardPO();
    private PreIntakePO preIntakePO = new PreIntakePO();
    private ClaimDetailsPO claimDetailsPO = new ClaimDetailsPO();
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ProcessStepIDPO processStepIDPO = new ProcessStepIDPO();
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
        claimDetailsPO.setWebDriver(getDriver());
        workListGridOpenPO.setWebDriver(getDriver());
        processStepIDPO.setWebDriver(getDriver());
        taskIdKey = testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskId";
    }

    @Test
    public void loginBRE() {
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();

        testResult.get().setTimeStarted(Instant.now());

        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        testResult.get().setTimeFinished(Instant.now());

        //Dashboard page
        Assert.assertEquals(testData.getString("rep_username").toUpperCase(), workListGridOpenPO.getLoggedUsername());
    }

    @Test
    public void openExistingCase() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        testResult.get().setTimeStarted(Instant.now());
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE", "GeneralDetailsID"));
        testResult.get().setTimeFinished(Instant.now());

        Assert.assertNotNull(claimDetailsPO.getClaimNumber());
    }

    @Test
    public void createNewCaseFromHeader() {

        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        //Work List grid Open
        workListGridOpenPO.clickCustomOpenTab();
        workListGridOpenPO.clickNewClaimButton();
        testResult.get().setTimeStarted(Instant.now());

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.selectCompany("0");
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.enterRepairerReferenceNumberTextbox(claimNumber);
        preIntakePO.enterVehicleRegistrationNumberTextbox(testData.getString("plate_number"));
        preIntakePO.clickCreateNewCaseButton();
        fluentWait(By.id(ClaimDetailsPO.ID_CLAIM_NUMBER));
        testResult.get().setTimeFinished(Instant.now());

        //set taskId once case is created
        String claimDetailUrl = getDriver().getCurrentUrl();
        String taskId = UtilitiesManager.getTaskIdFromUrl(claimDetailUrl);
        RedisManager.setValue(taskIdKey, taskId);
        logger.debug("taskIdKey: " + taskIdKey);
        logger.debug("taskId: " + taskId);

        //Check claim is in Open box
        processStepIDPO.clickClaimManager();
        workListGridOpenPO.clickCustomOpenTab();
        workListGridOpenPO.sortCreationDate();
        Assert.assertTrue(workListGridOpenPO.isClaimNumberExist(claimNumber));
    }

}
