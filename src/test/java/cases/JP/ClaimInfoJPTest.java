package cases.JP;

import cases.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.claimdetails.ClaimDetailsJPPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import steps.SelectVehicle;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class ClaimInfoJPTest extends TestBase {
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ClaimDetailsJPPO claimDetailsJPPO = new ClaimDetailsJPPO();
    private String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup(){
        workListGridOpenPO.setWebDriver(getDriver());
        claimDetailsJPPO.setWebDriver(getDriver());
        taskIdKey = testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskId";
    }

    @Test(description = "Search a vehicle by vin")
    public void searchVehicleByVin() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "Generic", "ClaimInfoJP"));

        claimDetailsJPPO.enterVin(testData.getString("vin"));
        testResult.get().setTimeStarted(Instant.now());
//        claimDetailsJPPO.clickVinQuery();

        //Vin details
        String vinManufacturerCodeExpected, vinModelCodeExpected, vinSubmodelCodeExpected;
        vinManufacturerCodeExpected = testData.getString("benzE_manufacturer_code");
        vinModelCodeExpected = testData.getString("benzE_model_code");
        vinSubmodelCodeExpected = testData.getString("benzE_submodel_code");

        //ClaimDetails - Get the VIN query information
        String vinManufacturerCodeActual, vinModelCodeActual, vinSubmodelCodeActual;
        vinManufacturerCodeActual = claimDetailsJPPO.getManufacturerCode();
        vinModelCodeActual = claimDetailsJPPO.getModelCode();
        vinSubmodelCodeActual = claimDetailsJPPO.getSubModelCode();

        Assert.assertEquals(vinManufacturerCodeActual, vinManufacturerCodeExpected);
        Assert.assertEquals(vinModelCodeActual, vinModelCodeExpected);
        Assert.assertEquals(vinSubmodelCodeActual, vinSubmodelCodeExpected);
        testResult.get().setTimeFinished(Instant.now());
    }

}
