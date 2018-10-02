package pageobjects.processstep.claimdetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class ClaimDetailsPO extends PageObject {

    //Claim Details
    public static final String ID_CLAIM_NUMBER = "root.task.claimNumber";
    public static final String ID_PLATE_NUMBER = "root.task.basicClaimData.vehicle.vehicleAdmin.plateNumber-plateNumber";
    public static final String ID_TAX_NUMBER = "root.task.basicClaimData.repairDetail.repairer.taxNumber";
    public static final String NAME_MANUFACTURER_AXCODE = "root.task.basicClaimData.vehicle.vehicleIdentification.manufacturerCodeAX";
    public static final String NAME_MODEL_AXCODE = "root.task.basicClaimData.vehicle.vehicleIdentification.modelCodeAX";
    public static final String NAME_SUB_MODEL_AXCODE = "root.task.basicClaimData.vehicle.vehicleIdentification.subModelCodeAX";
    public static final String ID_MANUFACTURER = "react-select-root.task.basicClaimData.vehicle.vehicleIdentification.manufacturerCodeAX--value";
    public static final String ID_MODEL = "react-select-root.task.basicClaimData.vehicle.vehicleIdentification.modelCodeAX--value";
    public static final String ID_SUB_MODEL = "react-select-root.task.basicClaimData.vehicle.vehicleIdentification.subModelCodeAX--value";
    public static final String ID_VIN = "root.task.basicClaimData.vehicle.vehicleIdentification.VINQuery-VIN";
    
    @FindBy(id = ID_CLAIM_NUMBER)
    private WebElement claimNumber;
    @FindBy(id = "root.task.basicClaimData.accidentData.accidentDateTime")
    private WebElement accidentDate;
    public static final String GET_ATTRIBUTE_VALUE = "value";

    //Vehicle Details
    @FindBy(id = ID_VIN)
    private WebElement vin;
    @FindBy(id = "root.task.basicClaimData.vehicle.vehicleIdentification.VINQuery-VINQueryButton")
    private WebElement vinQuery;
    @FindBy(id = ID_MANUFACTURER)
    private WebElement manufacturer;
    @FindBy(id = ID_MODEL)
    private WebElement model;
    @FindBy(id = ID_SUB_MODEL)
    private WebElement subModel;
    @FindBy(name = NAME_MANUFACTURER_AXCODE)
    private WebElement manufacturerCode;
    @FindBy(name = NAME_MODEL_AXCODE)
    private WebElement modelCode;
    @FindBy(name = NAME_SUB_MODEL_AXCODE)
    private WebElement subModelCode;


    public ClaimDetailsPO() {
        super();
    }

    public ClaimDetailsPO(WebDriver webDriver) {
        super(webDriver);
    }


    
    public String getClaimNumber(){ return this.getAttributeValue(By.id(ID_CLAIM_NUMBER), GET_ATTRIBUTE_VALUE);}

    
    public String getPlateNumber(){ return this.getAttributeValue(By.id(ID_PLATE_NUMBER), GET_ATTRIBUTE_VALUE);}

    
    public String getTaxNumber(){ return this.getAttributeValue(By.id(ID_TAX_NUMBER), GET_ATTRIBUTE_VALUE);}

    
    public String getManufacturer() { return this.getText(manufacturer); }

    
    public String getModel() {
        return this.getText(model);
    }

    
    public String getSubModel() {
        return this.getText(subModel);
    }

    
    public String getManufacturerCode() { return this.getAttributeValue(By.name(NAME_MANUFACTURER_AXCODE), GET_ATTRIBUTE_VALUE); }

    
    public String getModelCode() { return this.getAttributeValue(By.name(NAME_MODEL_AXCODE), GET_ATTRIBUTE_VALUE); }

    
    public String getSubModelCode() { return this.getAttributeValue(By.name(NAME_SUB_MODEL_AXCODE), GET_ATTRIBUTE_VALUE); }

    
    public String getVinCode(){ return  this.getAttributeValue(By.id(ID_VIN), GET_ATTRIBUTE_VALUE); }

    
    public void enterClaimNumberTextbox(String textClaimNumber) { this.sendKeys(claimNumber, textClaimNumber); }

    
    public void enterAccidentDate(String testAccidentDate) { this.sendKeys(accidentDate, testAccidentDate); }

    
    public void enterVin(String vinNumber) { this.sendKeys(vin, vinNumber); }

    
    public void selectManufacturerByValue(String value) {
        this.click(manufacturer);
        webDriver.findElement(getCssOfSelectionByValue(value)).click();
    }

    
    public void selectManufacturerByText(String text) {
        this.click(manufacturer);
        webDriver.findElement(getCssOfSelectionByText(text)).click();
    }

    
    public void clickVinQuery() {
        this.click(vinQuery);
    }

    
    public void selectModelByValue(String value) {
        waitForElementPresent(By.name(NAME_MODEL_AXCODE));
        this.click(model);
        webDriver.findElement(getCssOfSelectionByValue(value)).click();
    }

    
    public void selectModelByText(String text) {
        waitForElementPresent(By.name(NAME_MODEL_AXCODE));
        this.click(model);
        webDriver.findElement(getCssOfSelectionByText(text)).click();
    }

    
    public void selectSubmodelByValue(String value) {
        waitForElementPresent(By.name(NAME_SUB_MODEL_AXCODE));
        this.click(subModel);
        webDriver.findElement(getCssOfSelectionByValue(value)).click();
    }

    
    public void selectSubmodelByText(String text) {
        waitForElementPresent(By.name(NAME_SUB_MODEL_AXCODE));
        this.click(subModel);
        webDriver.findElement(getCssOfSelectionByText(text)).click();
    }

    private By getCssOfSelectionByText(String selection){
        return By.cssSelector("[aria-label*=\""+selection+"\"]");
    }

    private By getCssOfSelectionByValue(String selection){
        return By.cssSelector(".Select-option[id*=\"-option-"+selection+"\"]");
    }

    
    public boolean isVinQueryEnable(){ return vin.isEnabled(); }

    
    public boolean isClaimNumberEnable(){ return claimNumber.isEnabled(); }

}
