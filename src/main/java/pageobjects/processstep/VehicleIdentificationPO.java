package pageobjects.processstep;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pageobjects.PageObject;

public class VehicleIdentificationPO extends PageObject {

    @FindBy(id = "root.task.basicClaimData.vehicle.vehicleIdentification.VINQuery-VIN")
    private WebElement vin;

    @FindBy(id = "root.task.basicClaimData.vehicle.vehicleIdentification.VINQuery-VINQueryButton")
    private WebElement vinQuery;

    @FindBy(id = "root.task.basicClaimData.vehicle.vehicleIdentification.manufacturerCodeAX")
    private WebElement manufacturerCodeAX;

    @FindBy(id = "root.task.basicClaimData.vehicle.vehicleIdentification.modelCodeAX")
    private WebElement modelCodeAX;

    public VehicleIdentificationPO() {
        super();
    }

    public VehicleIdentificationPO(WebDriver driver) {
        super(driver);
    }


    public void enterVin(String vin) {
        this.vin.clear();
        this.vin.sendKeys(vin);
    }


    public void selectManufacturerCodeAXByValue(String value) {
        Select dropdown = new Select(manufacturerCodeAX);
        dropdown.selectByValue(value);
    }


    public void selectManufacturerCodeAXByText(String text) {
        Select dropdown = new Select(manufacturerCodeAX);
        dropdown.selectByVisibleText(text);
    }


    public void clickVinQuery() {
        vinQuery.submit();
    }


    public void selectModelCodeAXByValue(String value) {
        Select dropdown = new Select(modelCodeAX);
        dropdown.selectByValue(value);
    }


    public void selectModelCodeAXByText(String text) {
        Select dropdown = new Select(modelCodeAX);
        dropdown.selectByVisibleText(text);
    }
}
