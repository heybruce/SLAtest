package cases.SG;

import cases.TestBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.claimdetails.ClaimDetailsPO;
import pageobjects.processstep.claimdetails.ClaimDetailsSGPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class GeneralDetailsSGTest extends TestBase {
    private final static Logger logger = Logger.getLogger(GeneralDetailsSGTest.class);

    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ClaimDetailsSGPO claimDetailsSGPO = new ClaimDetailsSGPO();
    private String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup(){
        workListGridOpenPO.setWebDriver(getDriver());
        claimDetailsSGPO.setWebDriver((getDriver()));
        taskIdKey = testResult.getEnv() + "_" + testResult.getCountry() + "_taskId";
    }

    @Test(description = "Search a vehicle by vin")
    public void searchVehicleByVin() {
        getDriver().get(testData.getString("test_url"));

        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE", "GeneralDetailsSG"));

        claimDetailsSGPO.enterVin(testData.getString("vin"));
        testResult.setTimeStarted(Instant.now());
        claimDetailsSGPO.clickVinQuery();

        //Vin details
        String vinManufacturerCodeExpected, vinModelCodeExpected, vinSubmodelCodeExpected;
        vinManufacturerCodeExpected = testData.getString("bmw320_manufacturer_code");
        vinModelCodeExpected = testData.getString("bmw320_model_code");
        vinSubmodelCodeExpected = testData.getString("bmw320_submodel_code");

        //ClaimDetails - Get the VIN query information
        String vinManufacturerCodeActual, vinModelCodeActual, vinSubmodelCodeActual;
        vinManufacturerCodeActual = claimDetailsSGPO.getManufacturerCode();
        vinModelCodeActual = claimDetailsSGPO.getModelCode();
        vinSubmodelCodeActual = claimDetailsSGPO.getSubModelCode();

        Assert.assertEquals(vinManufacturerCodeActual, vinManufacturerCodeExpected);
        Assert.assertEquals(vinModelCodeActual, vinModelCodeExpected);
        Assert.assertEquals(vinSubmodelCodeActual, vinSubmodelCodeExpected);
        testResult.setTimeFinished(Instant.now());
    }
}
