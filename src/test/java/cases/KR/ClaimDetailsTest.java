package cases.KR;

import cases.TestBase;
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
import utils.RedisManager;
import utils.UtilitiesManager;

import java.time.Instant;
import java.util.Map;
import static utils.webdrivers.WebDriverFactory.getDriver;

public class ClaimDetailsTest extends TestBase {
    private ClaimDetailsKRPO claimDetailsKRPO = new ClaimDetailsKRPO();
    private IBOSSearchPO IBOSSearchPO = new IBOSSearchPO();
    String taskIdKey;

    @BeforeClass
    @Parameters(value = {"dataFile"})
    public void setup(String dataFile) {
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        claimDetailsKRPO.setWebDriver(getDriver());
        IBOSSearchPO.setWebDriver(getDriver());
        taskIdKey = testResult.getEnv() + "_" + testResult.getCountry() + "_taskId";
    }

    @Test(description = "Search vehicle by VIN query")
    public void searchVehicleByVin() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE", "Claim+Details"));

        claimDetailsKRPO.enterVin(testData.getString("vin"));
        testResult.setTimeStarted(Instant.now());
        claimDetailsKRPO.clickVinQuery();

        //Vin details
        String vinManufacturerCodeExpected, vinModelCodeExpected, vinSubmodelCodeExpected;
        vinManufacturerCodeExpected = testData.getString("vin_manufacturer_code");
        vinModelCodeExpected = testData.getString("vin_model_code");
        vinSubmodelCodeExpected = testData.getString("vin_submodel_code");

        Assert.assertFalse(isTextPresent(testData.getString("error_msg")));
        //ClaimDetails - Get the VIN query information
        String vinManufacturerCodeActual, vinModelCodeActual, vinSubmodelCodeActual;
        vinManufacturerCodeActual = claimDetailsKRPO.getManufacturerCode();
        vinModelCodeActual = claimDetailsKRPO.getModelCode();
        vinSubmodelCodeActual = claimDetailsKRPO.getSubModelCode();

        Assert.assertEquals(vinManufacturerCodeActual, vinManufacturerCodeExpected);
        Assert.assertEquals(vinModelCodeActual, vinModelCodeExpected);
        Assert.assertEquals(vinSubmodelCodeActual, vinSubmodelCodeExpected);
        testResult.setTimeFinished(Instant.now());
    }

    @Test
    public void openExistingCase() {
        getDriver().get(testData.getString("test_url"));

        //Claim Details
        Login login = new Login();
        login.LoginBRE(testData.getString("ins_username"), testData.getString("password"));

        testResult.setTimeStarted(Instant.now());
        getDriver().get(UtilitiesManager.constructBreUrl(
                testData.getString("test_url"), RedisManager.getValue(taskIdKey), "BRE", "Claim+Details"));
        testResult.setTimeFinished(Instant.now());

        Assert.assertNotNull(claimDetailsKRPO.getClaimNumber());
    }

//    @Test(description = "Search a vehicle by search tree")
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

        Assert.assertEquals(claimDetailsKRPO.getManufacturerCode(), ManufacturerCodeExpected);
        Assert.assertEquals(claimDetailsKRPO.getModelCode(), ModelCodeExpected);
        Assert.assertEquals(claimDetailsKRPO.getSubModelCode(), SubModelCodeExpected);
    }

//    @Test(description = "Query case via IBOS case search")
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
        claimDetailsKRPO.clickSearchIBOSCase();

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
        Assert.assertEquals(claimDetailsKRPO.getClaimNumber(), firstResult.get("WIP") + " " + firstResult.get("coverageTypeCode") + firstResult.get("damagedObjectSerialNo"));
        Assert.assertEquals(claimDetailsKRPO.getPlateNumber(), testData.getString("IBOS_plate_number"));
        Assert.assertEquals(claimDetailsKRPO.getTaxNumber(), testData.getString("IBOS_tax_id"));

    }
}
