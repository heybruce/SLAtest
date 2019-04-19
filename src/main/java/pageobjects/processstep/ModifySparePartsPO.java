package pageobjects.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

public class ModifySparePartsPO extends PageObject{
    private static final String GET_ATTRIBUTE_VALUE = "value";
    private static final String PART_SUPPLIER_PREFIX = "#altPartSupplier_";
    private static final String PART_NUMBER_PREFIX = "#altPartNumber_";
    private static final String PART_PRICE_PREFIX = "#altPartPrice_";

    //Loading circle
    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");

    //Parts Information
    @FindBy(css = "input[id=\"root.task.manualEditedParts.newPartNumber\"]")
    private WebElement newPartNumber;
    @FindBy(css = "input[id=\"root.task.manualEditedParts.newPartDescription\"]")
    private WebElement newDescription;
    @FindBy(css = "input[id=\"root.task.manualEditedParts.newPartPrice\"]")
    private WebElement newPrice;
    @FindBy(css = "input[id=\"root.task.manualEditedParts.newPartSupplier\"]")
    private WebElement newSupplier;
    @FindBy(id = "root.task.manualEditedParts.newPartType")
    private WebElement newPartType;
    @FindBy(id="react-select-root.task.manualEditedParts.newPartType--option-0")
    private WebElement selectionOEMPart;
    @FindBy(id="react-select-root.task.manualEditedParts.newPartType--option-1")
    private WebElement selectionNonOEMPart;
    @FindBy(id="react-select-root.task.manualEditedParts.newPartType--option-2")
    private WebElement selectionUsedPart;
    @FindBy(id="root.task.manualEditedParts.addPartButton")
    private WebElement btnAddPart;

    //Upload by template
    public static final String ID_BTN_UPLOAD_CSV = "root.task.manualEditedParts.uploadPartsCsvButton_upload";
    @FindBy(id="root.task.manualEditedParts.downloadPartsTemplateCsvButton")
    private WebElement btnDownloadCsvTemplate;
    @FindBy(id="root.task.manualEditedParts.downloadPartsTemplateXlsButton")
    private WebElement btnDownloadXlsTemplate;
    @FindBy(id=ID_BTN_UPLOAD_CSV)
    private WebElement btnUploadCsv;
    @FindBy(id="root.task.manualEditedParts.uploadPartsXlsButton_upload")
    private WebElement btnUploadXls;

    //Added parts table
    @FindBy(id="root.task.manualEditedParts.parts")
    private WebElement addedPartsTable;
    @FindBy(css="label[for=\"total_saving\"]")
    private WebElement totalSaving;
    //row0
    public static final String PART_NUM_0 = "oemPartNumber_0";
    @FindBy(xpath="id(\"override_0\")/div/label")
    private WebElement partCheckbox0;
    @FindBy(id="oemGuideNumber_0")
    private WebElement guideNumber0;
    @FindBy(id="oemPartDescription_0")
    private WebElement partDescription0;
    @FindBy(id= PART_NUM_0)
    private WebElement partNumber0;
    @FindBy(id="oemPartPrice_0")
    private WebElement partPrice0;
    @FindBy(css="#altPartType_0 #react-select-altPartType--value-item")
    private WebElement partType0;
    @FindBy(css="#altPartSupplier_0 input")
    private WebElement partSupplier0;
    @FindBy(css="#altPartNumber_0 input")
    private WebElement enteredPartNumber0;
    @FindBy(css="#altPartPrice_0 input")
    private WebElement enteredPrice0;
    //row1
    public static final String PART_NUM_1 = "oemPartNumber_1";
    @FindBy(id="oemGuideNumber_1")
    private WebElement guideNumber1;
    @FindBy(id="oemPartDescription_1")
    private WebElement partDescription1;
    @FindBy(id=PART_NUM_1)
    private WebElement partNumber1;
    @FindBy(id="oemPartPrice_1")
    private WebElement partPrice1;
    @FindBy(css="#altPartType_1 #react-select-altPartType--value-item")
    private WebElement partType1;
    @FindBy(css= "#altPartSupplier_1 input")
    private WebElement partSupplier1;
    @FindBy(css="#altPartNumber_1 input")
    private WebElement enteredPartNumber1;
    @FindBy(css="#altPartPrice_1 input")
    private WebElement enteredPrice1;
    //row2
    public static final String PART_NUM_2 = "oemPartNumber_2";
    @FindBy(id="oemGuideNumber_2")
    private WebElement guideNumber2;
    @FindBy(id="oemPartDescription_2")
    private WebElement partDescription2;
    @FindBy(id=PART_NUM_2)
    private WebElement partNumber2;
    @FindBy(id="oemPartPrice_2")
    private WebElement partPrice2;
    @FindBy(css="#altPartType_2 #react-select-altPartType--value-item")
    private WebElement partType2;
    @FindBy(css="#altPartSupplier_2 input")
    private WebElement partSupplier2;
    @FindBy(css="#altPartNumber_2 input")
    private WebElement enteredPartNumber2;
    @FindBy(css="#altPartPrice_2 input")
    private WebElement enteredPrice2;

    @FindBy(id="delete")
    private WebElement btnDelete;


    public ModifySparePartsPO() {
        super();
    }

    public ModifySparePartsPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void inputPartNumber(String partNum) { this.sendKeys(newPartNumber, partNum); }


    public void inputPartDescription(String description) { this.sendKeys(newDescription, description); }


    public void inputPartPrice(String price) { this.sendKeys(newPrice, price); }


    public void inputPartSupplier(String supplier) { this.sendKeys(newSupplier, supplier); }


    public void selectPartType(String partType){
        this.click(newPartType);
        switch (partType){
            case "OEM parts":
                this.click(selectionOEMPart);
                break;
            case "Non-OEM parts":
                this.click(selectionNonOEMPart);
                break;
            case "Used Parts":
                this.click(selectionUsedPart);
                break;
            default:
                this.click(selectionOEMPart);
                break;
        }
    }


    public void clickAddPart(){ this.click(btnAddPart); }


    public int getAddedPartsNum(){
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(addedPartsTable));
        int num;
        num = addedPartsTable.findElements(By.cssSelector("tbody tr")).size();
        return num;
    }


    public By getDeletePartRow(int num){ return By.id("root.task.manualEditedParts.parts_"+num); }


    public String getGuideNumber0(){ return this.getText(guideNumber0); }

    public String getGuideNumber1(){ return this.getText(guideNumber1); }

    public String getGuideNumber2(){ return this.getText(guideNumber2); }


    public String getPartDescription0(){ return this.getText(partDescription0); }

    public String getPartDescription1(){ return this.getText(partDescription1); }

    public String getPartDescription2(){ return this.getText(partDescription2); }


    public String getPartNumber0(){ return this.getText(partNumber0); }

    public String getPartNumber1(){ return this.getText(partNumber1); }

    public String getPartNumber2(){ return this.getText(partNumber2); }


    public String getPartPrice0(){ return this.getText(partPrice0); }

    public String getPartPrice1(){ return this.getText(partPrice1); }

    public String getPartPrice2(){ return this.getText(partPrice2); }


    public String getPartType0(){ return this.getText(partType0); }

    public String getPartType1(){ return this.getText(partType1); }

    public String getPartType2(){ return this.getText(partType2); }



    public String getPartSupplier(int row){
        return this.getAttributeValue(getCssOfAltPartListItem(PART_SUPPLIER_PREFIX, row), GET_ATTRIBUTE_VALUE);
    }


    public String getEnteredPartNumber(int row){
        return this.getAttributeValue(getCssOfAltPartListItem(PART_NUMBER_PREFIX, row), GET_ATTRIBUTE_VALUE);
    }


    public String getEnteredPrice(int row){
        return this.getAttributeValue(getCssOfAltPartListItem(PART_PRICE_PREFIX, row), GET_ATTRIBUTE_VALUE);
    }


    public void clickDelete(){ this.click(btnDelete); }


    public void clickPartCheckbox0(){ this.click(partCheckbox0); }


    public void setEnteredPrice0(String newPrice){ this.sendKeys(enteredPrice0, newPrice); }


    public String getTotalSavingAmount(String currency){
        String wholeTextOfTotalSaving = this.getText(totalSaving);
        String[] splitText = wholeTextOfTotalSaving.split(":");

        return splitText[1].replaceAll(currency, "").trim();
    }


    public void uploadCsvToAddParts(String filePath) {
        // remove the hidden attribute for uploading files
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].removeAttribute('class');", btnUploadCsv);
        // wait for the upload button clickable
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.elementToBeClickable(btnUploadCsv));
        btnUploadCsv.sendKeys(filePath);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void uploadXlsToAddParts(String filePath){ this.sendKeys(btnUploadXls, filePath); }


    public void clickDownloadCsvTemplate(){ this.click(btnDownloadCsvTemplate); }


    public void clickDownloadXlsTemplate(){ this.click(btnDownloadXlsTemplate); }

    private By getCssOfAltPartListItem(String prefix, int row) { return By.cssSelector(prefix + row + " input"); }

}
