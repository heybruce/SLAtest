package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.processstep.claimdetails.ClaimDetailsPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class SelectVehicle extends TestBase {
    private ClaimDetailsPO claimDetails = new ClaimDetailsPO();
    private WebDriverWait wait;

    public SelectVehicle(){
        claimDetails.setWebDriver(getDriver());
        wait = new WebDriverWait(getDriver(), 10);
    }

    public void SearchBySearchTree(String manufacturer, String model, String subModel) {
        claimDetails.selectManufacturerByText(manufacturer);
        claimDetails.selectModelByText(model);
        claimDetails.selectSubmodelByText(subModel);

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MANUFACTURER_AXCODE), testData.getString("searchTree_manufacturer_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MODEL_AXCODE), testData.getString("searchTree_model_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_SUB_MODEL_AXCODE), testData.getString("searchTree_submodel_code")));
    }

    public void SearchByVIN(String vin){
        claimDetails.enterVin(vin);

        claimDetails.clickVinQuery();

        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MANUFACTURER_AXCODE), testData.getString("vin_manufacturer_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_MODEL_AXCODE), testData.getString("vin_model_code")));
        wait.until(ExpectedConditions.textToBePresentInElementValue(By.name(ClaimDetailsPO.NAME_SUB_MODEL_AXCODE), testData.getString("vin_submodel_code")));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ClaimDetailsPO.ID_MANUFACTURER)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ClaimDetailsPO.ID_MODEL)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(ClaimDetailsPO.ID_SUB_MODEL)));
    }
}
