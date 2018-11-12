package cases.KR;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.IBOSSearchPO;
import pageobjects.processstep.claimdetails.ClaimDetailsKRPO;
import steps.CreateNewCaseKR;
import steps.Login;
import steps.SelectVehicle;
import utils.UtilitiesManager;

import java.time.Instant;
import java.util.Map;
import static utils.webdrivers.WebDriverFactory.getDriver;

public class ClaimDetailsTest extends TestBase {
    private ClaimDetailsKRPO claimDetails = new ClaimDetailsKRPO();
    private IBOSSearchPO IBOSSearchPO = new IBOSSearchPO();

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        claimDetails.setWebDriver(getDriver());
        IBOSSearchPO.setWebDriver(getDriver());
    }

    @Test(description = "Search vehicle by VIN query")
    public void searchVehicleByVin() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(testData.getString("url_to_claim_details"));

        SelectVehicle selectVehicle = new SelectVehicle();

        testResult.setTimeStarted(Instant.now());
        selectVehicle.SearchByVIN(testData.getString("vin"));
        testResult.setTimeFinished(Instant.now());

        //Vin details
        String vinManufacturerCodeExpected, vinModelCodeExpected, vinSubmodelCodeExpected;
        vinManufacturerCodeExpected = testData.getString("vin_manufacturer_code");
        vinModelCodeExpected = testData.getString("vin_model_code");
        vinSubmodelCodeExpected = testData.getString("vin_submodel_code");

        //ClaimDetails - Get the VIN query information
        String vinManufacturerCodeActual, vinModelCodeActual, vinSubmodelCodeActual;
        vinManufacturerCodeActual = claimDetails.getManufacturerCode();
        vinModelCodeActual = claimDetails.getModelCode();
        vinSubmodelCodeActual = claimDetails.getSubModelCode();

        Assert.assertEquals(vinManufacturerCodeActual, vinManufacturerCodeExpected);
        Assert.assertEquals(vinModelCodeActual, vinModelCodeExpected);
        Assert.assertEquals(vinSubmodelCodeActual, vinSubmodelCodeExpected);
    }

    @Test(description = "Search a vehicle by search tree")
    public void searchVehicleBySearchTree(){
        getDriver().get(testData.getString("test_url"));

        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        CreateNewCaseKR createNewCase = new CreateNewCaseKR();
        createNewCase.createNewCase(testData.getString("plate_number"));

        SelectVehicle selectVehicle = new SelectVehicle();
        selectVehicle.SearchBySearchTree(testData.getString("Manufacturer"), testData.getString("Model"), testData.getString("SubModel"));

        String ManufacturerCodeExpected, ModelCodeExpected, SubModelCodeExpected;
        ManufacturerCodeExpected = testData.getString("searchTree_manufacturer_code");
        ModelCodeExpected = testData.getString("searchTree_model_code");
        SubModelCodeExpected = testData.getString("searchTree_submodel_code");

        Assert.assertEquals(claimDetails.getManufacturerCode(), ManufacturerCodeExpected);
        Assert.assertEquals(claimDetails.getModelCode(), ModelCodeExpected);
        Assert.assertEquals(claimDetails.getSubModelCode(), SubModelCodeExpected);
    }

    @Test(description = "Query case via IBOS case search")
    public void queryCaseByIBOS() {
        //Launch browser
        getDriver().get(testData.getString("test_url"));

        //Login
        Login login = new Login();
        login.LoginBRE(testData.getString("IBOS_username"), testData.getString("password"));

        //Create a new case
        CreateNewCaseKR createNewCase = new CreateNewCaseKR();
        createNewCase.createNewCase(testData.getString("plate_number"));

        //Search for IBOS case
        claimDetails.clickSearchIBOSCase();

        IBOSSearchPO.enterPlateNumber(testData.getString("IBOS_plate_number"));
        IBOSSearchPO.enterTaxId(testData.getString("IBOS_tax_id"));
        IBOSSearchPO.selectInsuranceCompany(testData.getString("IBOS_insurance_company"));
        IBOSSearchPO.clickSearchButton();

        Assert.assertFalse(isAlertPresent());

        Map<String, String> firstResult = IBOSSearchPO.getTheFirstSearchResult();
        Assert.assertNotNull(firstResult.get("id"));
        Assert.assertNotNull(firstResult.get("WIP"));
        Assert.assertNotNull(firstResult.get("contactMobile"));
        Assert.assertNotNull(firstResult.get("contactPerson"));
        Assert.assertNotNull(firstResult.get("coverageTypeCode"));
        Assert.assertNotNull(firstResult.get("damagedObjectSerialNo"));

        //Choose the search result
        IBOSSearchPO.chooseTheFirstSearchResult();
        Assert.assertEquals(claimDetails.getClaimNumber(), firstResult.get("WIP") + " " + firstResult.get("coverageTypeCode") + firstResult.get("damagedObjectSerialNo"));
        Assert.assertEquals(claimDetails.getPlateNumber(), testData.getString("IBOS_plate_number"));
        Assert.assertEquals(claimDetails.getTaxNumber(), testData.getString("IBOS_tax_id"));

    }

    @Test
    public void openExistingCase() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        testResult.setTimeStarted(Instant.now());
        getDriver().get(testData.getString("url_to_claim_details"));
        testResult.setTimeFinished(Instant.now());

        Assert.assertNotNull(claimDetails.getClaimNumber());
    }
}
