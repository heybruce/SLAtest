package pageobjects.processstep;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

public class AttachmentsRepairerPO extends PageObject {

    public static final String CLAIMS_DOCUMENT_BLOCK = "ClaimsDocument";
    public static final String VEHICLE_BEFORE_REPAIR_BLOCK = "VehicleBeforeRepair";
    public static final String VEHICLE_AFTER_REPAIR_BLOCK = "VehicleAfterRepair";
    public static final String OTHER_BLOCK = "OTHER";
    private static final String CLAIMS_DOCUMENT_UPLOAD_LOCATOR = "ClaimsDocument_upload";
    private static final String VEHICLE_BEFORE_REPAIR_UPLOAD_LOCATOR = "VehicleBeforeRepair_upload";
    private static final String VEHICLE_AFTER_REPAIR_UPLOAD_LOCATOR = "VehicleAfterRepair_upload";
    private static final String OTHER_UPLOAD_LOCATOR = "OTHER_upload";
    public static final String REMOVE_ATTRUBUTE_CLASS_SCRIPT = "arguments[0].removeAttribute('class');";
    private static final String SELECT_ALL_CLAIM_DOCUMENT = "select-ClaimsDocument";
    private static final String SELECT_ALL_VEHICLE_BEFORE_REPAIR = "select-VehicleBeforeRepair";
    private static final String SELECT_ALL_VEHICLE_AFTER_REPAIR = "select-VehicleAfterRepair";
    private static final String SELECT_ALL_OTHER = "select-OTHER";
    private static final String SELECT_ALL = "select-all-link";
    private static final String DOWNLOAD_BUTTON = "batchDownload";

    @FindBy(id = CLAIMS_DOCUMENT_UPLOAD_LOCATOR)
    private WebElement uploadClaimDocument;
    @FindBy(id = VEHICLE_BEFORE_REPAIR_UPLOAD_LOCATOR)
    private WebElement uploadVehicleBeforeRepair;
    @FindBy(id = VEHICLE_AFTER_REPAIR_UPLOAD_LOCATOR)
    private WebElement uploadVehicleAfterRepair;
    @FindBy(id = OTHER_UPLOAD_LOCATOR)
    private WebElement uploadOther;
    @FindBy(id = SELECT_ALL_CLAIM_DOCUMENT)
    private WebElement selectAllInClaimDocument;
    @FindBy(id = SELECT_ALL_VEHICLE_BEFORE_REPAIR)
    private WebElement selectAllVehicleBeforeRepair;
    @FindBy(id = SELECT_ALL_VEHICLE_AFTER_REPAIR)
    private WebElement selectAllVehicleAfterRepair;
    @FindBy(id = SELECT_ALL_OTHER)
    private WebElement selectAllOther;
    @FindBy(id = SELECT_ALL)
    private WebElement selectAll;
    @FindBy(id = DOWNLOAD_BUTTON)
    private WebElement downloadButton;
    @FindBy(id = CLAIMS_DOCUMENT_BLOCK)
    private WebElement claimDocumentBlock;
    @FindBy(id = VEHICLE_BEFORE_REPAIR_BLOCK)
    private WebElement vehicleBeforeRepairBlock;
    @FindBy(id = VEHICLE_AFTER_REPAIR_BLOCK)
    private WebElement vehicleAfterRepairBlock;
    @FindBy(id = OTHER_BLOCK)
    private WebElement otherBlock;

    private static final String JS_HIDE_UPLOAD_DIALOG = "HTMLInputElement.prototype.click = function() {if(this.type !== 'file') HTMLElement.prototype.click.call(this);};";

    public AttachmentsRepairerPO() { super(); }

    public AttachmentsRepairerPO(WebDriver webDriver) { super(webDriver); }


    public void addFilesIntoClaimsDocument(String filePath){
        this.waitForElementPresent(By.id(CLAIMS_DOCUMENT_UPLOAD_LOCATOR));
        // remove the hidden attribute for uploading files
        ((JavascriptExecutor) webDriver).executeScript(REMOVE_ATTRUBUTE_CLASS_SCRIPT, uploadClaimDocument);
        ((JavascriptExecutor) webDriver).executeScript(JS_HIDE_UPLOAD_DIALOG);
        // wait for the upload button clickable
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(uploadClaimDocument));
        this.uploadClaimDocument.sendKeys(filePath);
    }


    public void addFilesIntoVehicleBeforeRepair(String filePath) {
        this.waitForElementPresent(By.id(VEHICLE_BEFORE_REPAIR_UPLOAD_LOCATOR));
        // remove the hidden attribute for uploading files
        ((JavascriptExecutor) webDriver).executeScript(REMOVE_ATTRUBUTE_CLASS_SCRIPT, uploadVehicleBeforeRepair);
        ((JavascriptExecutor) webDriver).executeScript(JS_HIDE_UPLOAD_DIALOG);
        // wait for the upload button clickable
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(uploadVehicleBeforeRepair));
        this.uploadVehicleBeforeRepair.sendKeys(filePath);
    }


    public void addFilesIntoVehicleAfterRepair(String filePath) {
        this.waitForElementPresent(By.id(VEHICLE_AFTER_REPAIR_UPLOAD_LOCATOR));
        // remove the hidden attribute for uploading files
        ((JavascriptExecutor) webDriver).executeScript(REMOVE_ATTRUBUTE_CLASS_SCRIPT, uploadVehicleAfterRepair);
        ((JavascriptExecutor) webDriver).executeScript(JS_HIDE_UPLOAD_DIALOG);
        // wait for the upload button clickable
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(uploadVehicleAfterRepair));
        this.uploadVehicleAfterRepair.sendKeys(filePath);
    }


    public void addFilesIntoOther(String filePath) {
        this.waitForElementPresent(By.id(OTHER_UPLOAD_LOCATOR));
        // remove the hidden attribute for uploading files
        ((JavascriptExecutor) webDriver).executeScript(REMOVE_ATTRUBUTE_CLASS_SCRIPT, uploadOther);
        ((JavascriptExecutor) webDriver).executeScript(JS_HIDE_UPLOAD_DIALOG);
        // wait for the upload button clickable
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(uploadOther));
        this.uploadOther.sendKeys(filePath);
    }


    public String getClaimsDocumentAttributeValue(int index, String attribute){ return this.getAttributeValue(getXpathOfUploadedImg(index, CLAIMS_DOCUMENT_BLOCK), attribute); }


    public String getVehicleBeforeRepairAttributeValue(int index, String attribute){ return this.getAttributeValue(getXpathOfUploadedImg(index, VEHICLE_BEFORE_REPAIR_BLOCK), attribute); }


    public String getVehicleAfterRepairAttributeValue(int index, String attribute){ return this.getAttributeValue(getXpathOfUploadedImg(index, VEHICLE_AFTER_REPAIR_BLOCK), attribute); }


    public String getOtherAttributeValue(int index, String attribute){ return this.getAttributeValue(getXpathOfUploadedImg(index, OTHER_BLOCK), attribute); }

    private String idEqual = "//*[@id=\"";
    private String divBlock = "\"]/div[2]/div";
    public By getXpathOfUploadedImg(int index, String block) { return By.xpath(idEqual+block+divBlock+"["+index+"]/div[1]/a/div[1]"); }

    public By getXpathOfUploadedImgCheckbox(int index, String block) { return By.xpath(idEqual+block+divBlock+"["+index+"]/div[1]/div/label/span"); }

    public void selectAttachment(int index, String block) {
        //hover on the image
        new Actions(webDriver).moveToElement(webDriver.findElement(getXpathOfUploadedImg(index, block))).perform();
        this.click(webDriver.findElement(getXpathOfUploadedImgCheckbox(index, block)));
    }

    public WebElement getElementOfUploadFileName(int index, String block){ return webDriver.findElement(By.xpath("//*[@id=\""+block+divBlock+"["+index+"]/div[2]/div[1]/span")); }


    public String getClaimsDocumentAttachmentFileName(int index){ return getElementOfUploadFileName(index, CLAIMS_DOCUMENT_BLOCK).getText(); }

    public String getVehicleBeforeRepairAttachmentFileName(int index){ return getElementOfUploadFileName(index, VEHICLE_BEFORE_REPAIR_BLOCK).getText(); }

    public String getVehicleAfterRepairAttachmentFileName(int index){ return getElementOfUploadFileName(index, VEHICLE_AFTER_REPAIR_BLOCK).getText(); }

    public String getOtherAttachmentFileName(int index){ return getElementOfUploadFileName(index, OTHER_BLOCK).getText(); }



    public int getClaimDocumentAttachmentFileNumber(){ return webDriver.findElements(By.xpath(idEqual+CLAIMS_DOCUMENT_BLOCK+divBlock)).size()-1; }

    public int getVehicleBeforeRepairAttachmentFileNumber(){ return webDriver.findElements(By.xpath(idEqual+VEHICLE_BEFORE_REPAIR_BLOCK+divBlock)).size()-1; }

    public int getVehicleAfterRepairAttachmentFileNumber(){ return webDriver.findElements(By.xpath(idEqual+VEHICLE_AFTER_REPAIR_BLOCK+divBlock)).size()-1; }

    public int getOtherAttachmentFileNumber(){ return webDriver.findElements(By.xpath(idEqual+OTHER_BLOCK+"\"]/div[2]/div")).size()-1; }


    public void clickSelectAllInClaimDocument() {
        selectAllInClaimDocument.click();
    }


    public void clickSelectAllInVehicleBeforeRepair() {
        selectAllVehicleBeforeRepair.click();
    }


    public void clickSelectAllInVehicleAfterRepair() {
        selectAllVehicleAfterRepair.click();
    }


    public void clickSelectAllInOther() {
        selectAllOther.click();
    }


    public void clickSelectAllAttachments() {
        selectAll.click();
    }


    public void clickDownloadButton() {
        downloadButton.click();
    }


    public String getClaimsDocumentBlockTitle() {
        return claimDocumentBlock.findElement(By.className("group-title")).getText();
    }


    public String getVehicleBeforeRepairBlockTitle() {
        return vehicleBeforeRepairBlock.findElement(By.className("group-title")).getText();
    }


    public String getVehicleAfterRepairBlockTitle() {
        return vehicleAfterRepairBlock.findElement(By.className("group-title")).getText();
    }


    public String getOtherBlockTitle() {
        return otherBlock.findElement(By.className("group-title")).getText();
    }


    public String getCaseNumber() {
        return webDriver.findElement(By.className("claim-identification-item-value")).getText();
    }

}
