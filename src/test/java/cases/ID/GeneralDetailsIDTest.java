package cases.ID;

import cases.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.claimdetails.ClaimDetailsPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import steps.Login;
import utils.UtilitiesManager;

import java.time.Instant;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class GeneralDetailsIDTest extends TestBase {
    private WorkListGridOpenPO workListGridOpenPO = new WorkListGridOpenPO();
    private ClaimDetailsPO claimDetailsPO = new ClaimDetailsPO();

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup(){
        workListGridOpenPO.setWebDriver(getDriver());
        claimDetailsPO.setWebDriver((getDriver()));
    }

    @Test(description = "Search a vehicle by vin")
    public void searchVehicleByVin() {
        getDriver().get(testData.getString("test_url"));

        Login login = new Login();
        login.LoginBRE(testData.getString("rep_username"), testData.getString("password"));

        getDriver().get(testData.getString("url_to_GeneralDetailsID"));

        claimDetailsPO.enterVin(testData.getString("benzE_vin"));
        testResult.setTimeStarted(Instant.now());
        claimDetailsPO.clickVinQuery();

        //Vin details
        String vinManufacturerCodeExpected, vinModelCodeExpected, vinSubmodelCodeExpected;
        vinManufacturerCodeExpected = testData.getString("benzE_manufacturer_code");
        vinModelCodeExpected = testData.getString("benzE_model_code");
        vinSubmodelCodeExpected = testData.getString("benzE_submodel_code");

        //ClaimDetails - Get the VIN query information
        String vinManufacturerCodeActual, vinModelCodeActual, vinSubmodelCodeActual;
        vinManufacturerCodeActual = claimDetailsPO.getManufacturerCode();
        vinModelCodeActual = claimDetailsPO.getModelCode();
        vinSubmodelCodeActual = claimDetailsPO.getSubModelCode();

        Assert.assertEquals(vinManufacturerCodeActual, vinManufacturerCodeExpected);
        Assert.assertEquals(vinModelCodeActual, vinModelCodeExpected);
        Assert.assertEquals(vinSubmodelCodeActual, vinSubmodelCodeExpected);
        testResult.setTimeFinished(Instant.now());
    }
}
