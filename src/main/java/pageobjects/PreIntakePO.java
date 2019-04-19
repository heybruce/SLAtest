package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreIntakePO extends PageObject {
    protected static final By LOADING_CIRCLE = By.cssSelector(".loading-component");

    @FindBy(id = "root.task.claimNumber")
    private WebElement claimNumber;
    @FindBy(id = "root.task.basicClaimData.vehicle.vehicleAdmin.plateNumber-plateNumber")
    private WebElement plateNumberKR;
    @FindBy(id = "root.task.basicClaimData.vehicle.vehicleAdmin.plateNumber")
    private WebElement vehicleRegistrationNumber;
    @FindBy(id = "submitButton")
    private WebElement createNewCase;
    @FindBy(id = "root.task.displayName")
    private WebElement repairerReferenceNumber;
    @FindBy(id = "react-select-root.task.caseMemberByRole.workProvider.companyName--value")
    private WebElement company;


    public PreIntakePO() {
        super();
    }

    public PreIntakePO(WebDriver webDriver) {
        super(webDriver);
    }

     
    public void enterClaimNumberTextbox(String textClaimNumber) {
        this.sendKeys(claimNumber, textClaimNumber);
    }

     
    public boolean isRepairerReferenceNumberEnable(){ return repairerReferenceNumber.isEnabled(); }

     
    public void enterRepairerReferenceNumberTextbox(String textClaimNumber) {
        this.sendKeys(repairerReferenceNumber, textClaimNumber);
    }

     
    public void enterPlateNumberKRTextbox(String textPlateNumber) {
        this.sendKeys(plateNumberKR, textPlateNumber);
    }

     
    public void enterPlateNumberSGTextbox(String textPlateNumber) {
        this.sendKeys(vehicleRegistrationNumber, textPlateNumber);
    }

    public void enterVehicleRegistrationNumberTextbox(String textPlateNumber) { this.sendKeys(vehicleRegistrationNumber, textPlateNumber); }

    public void clickCreateNewCaseButton() {
        this.click(createNewCase);
        waitForElementInvisible(LOADING_CIRCLE);
    }

     
    public void selectCompany(String value){
        if(!company.findElements(By.cssSelector(".Select-placeholder")).isEmpty()) {
            this.click(company);
            webDriver.findElement(By.cssSelector("[id*=\"-option-"+value+"\"]")).click();
        }
    }

}
