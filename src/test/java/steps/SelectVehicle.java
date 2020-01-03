package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.processstep.claimdetails.ClaimDetailsPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class SelectVehicle extends TestBase {
    private ClaimDetailsPO claimDetails = new ClaimDetailsPO();
    private WebDriverWait wait;

    public SelectVehicle(){
        claimDetails.setWebDriver(getDriver());
        wait = new WebDriverWait(getDriver(), 20);
    }

    public void SearchBySearchTree(String manufacturer, String model, String subModel) {
        claimDetails.selectManufacturerByText(manufacturer);
        claimDetails.selectModelByText(model);
        claimDetails.selectSubmodelByText(subModel);

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MANUFACTURER_AXCODE), testData.getString("searchTree_manufacturer_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MODEL_AXCODE), testData.getString("searchTree_model_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_SUB_MODEL_AXCODE), testData.getString("searchTree_submodel_code")));
    }

    public void SearchBySearchTree(String vehicle) {
        claimDetails.selectManufacturerBySearching(testData.getString(vehicle+"_manufacturer_code"));
        claimDetails.selectModelBySearching(testData.getString(vehicle+"_model_code"));
        claimDetails.selectSubmodelBySearching(testData.getString(vehicle+"_submodel_code"));

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MANUFACTURER_AXCODE), testData.getString(vehicle+"_manufacturer_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MODEL_AXCODE), testData.getString(vehicle+"_model_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_SUB_MODEL_AXCODE), testData.getString(vehicle+"_submodel_code")));
    }


    public void SearchByVIN(String vin){
        claimDetails.enterVin(vin);

        claimDetails.clickVinQuery();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(ClaimDetailsPO.ID_ERROR_NOTIFICATION));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MANUFACTURER_AXCODE), testData.getString("benzE_manufacturer_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MODEL_AXCODE), testData.getString("benzE_model_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_SUB_MODEL_AXCODE), testData.getString("benzE_submodel_code")));
    }
}
