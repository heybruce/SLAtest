package cases.ID;

import cases.TestBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.claimdetails.ClaimDetailsPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class GeneralDetailsIDTest extends TestBase {
    private final static Logger logger = Logger.getLogger(GeneralDetailsIDTest.class);

    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ClaimDetailsPO claimDetailsPO = new ClaimDetailsPO();
    private String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup(){
        workListGridOpenPO.setWebDriver(getDriver());
        claimDetailsPO.setWebDriver((getDriver()));
        taskIdKey = testResult.get().getEnv() + "_" + testResult.get().getCountry() + "_taskId";
    }

    @Test(description = "Search a vehicle by vin")
    public void searchVehicleByVin() {
        getDriver().get(testData.getString("test_url"));

        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE", "GeneralDetailsID"));

        claimDetailsPO.enterVin(testData.getString("benzS_vin"));
        testResult.get().setTimeStarted(Instant.now());
        claimDetailsPO.clickVinQuery();

        //Vin details
        String vinManufacturerCodeExpected, vinModelCodeExpected, vinSubmodelCodeExpected;
        vinManufacturerCodeExpected = testData.getString("benzS_manufacturer_code");
        vinModelCodeExpected = testData.getString("benzS_model_code");
        vinSubmodelCodeExpected = testData.getString("benzS_submodel_code");

        //ClaimDetails - Get the VIN query information
        String vinManufacturerCodeActual, vinModelCodeActual, vinSubmodelCodeActual;
        vinManufacturerCodeActual = claimDetailsPO.getManufacturerCode();
        vinModelCodeActual = claimDetailsPO.getModelCode();
        vinSubmodelCodeActual = claimDetailsPO.getSubModelCode();

        Assert.assertEquals(vinManufacturerCodeActual, vinManufacturerCodeExpected);
        Assert.assertEquals(vinModelCodeActual, vinModelCodeExpected);
        Assert.assertEquals(vinSubmodelCodeActual, vinSubmodelCodeExpected);
        testResult.get().setTimeFinished(Instant.now());
    }
}
