package pageobjects.processstep;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

public class LaborRatesPO extends PageObject {
    //Loading circle
    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");

    //Labour rates
    public static final String ID_LABOR_RATE1 = "root.task.classInput.rate1.value";
    public static final String ID_LABOR_RATE2 = "root.task.classInput.rate2.value";
    public static final String ID_LABOR_RATE3 = "root.task.classInput.rate3.value";
    public static final String ID_PAINT_RATE = "root.task.classInput.ratePaint1.value";
    private static final String CONFIRM_LABEL = "confirmLabel";

    @FindBy(id = "react-select-root.task.classInput.partnershipId--value")
    private WebElement partnership;
    @FindBy(id = ID_LABOR_RATE1)
    private WebElement laborRate1;
    @FindBy(id = ID_LABOR_RATE2)
    private WebElement laborRate2;
    @FindBy(id = ID_LABOR_RATE3)
    private WebElement laborRate3;
    @FindBy(id = "root.task.classInput.category")
    private WebElement paintMethod;
    @FindBy(id = ID_PAINT_RATE)
    private WebElement paintRate;

    @FindBy(id = "confirm")
    private WebElement confirmDialog;

    //ID Block Code(IDBC)
    public static final String IDBC08LABEL = "root.task.classInput.IDBlockCodes.IDBlockCode08.iDBlockCode";
    public static final String IDBC67LABEL = "root.task.classInput.IDBlockCodes.IDBlockCode67.iDBlockCode";
    public static final String IDBC20LABEL = "root.task.classInput.IDBlockCodes.IDBlockCode20.iDBlockCode";
    public static final String IDBC74LABEL = "root.task.classInput.IDBlockCodes.IDBlockCode74.iDBlockCode";
    public static final String IDBC64LABEL = "root.task.classInput.IDBlockCodes.IDBlockCode64.iDBlockCode";
    private static final String GET_ATTRIBUTE_VALUE = "value";
    @FindBy(id = "root.task.classInput.idbcAddButton")
    private WebElement addIdbcButton;
    @FindBy(id = "root.task.classInput.idbcDeleteButton")
    private WebElement delete;

    //ID Block Code(IDBC) Dialog
    public static final String ADD_IDBC_BTN = "AddIDBlockCodes";
    @FindBy(id = "AddIDBlockCodes")
    private WebElement addIdbcDialog;
    @FindBy(id = "idbc-08-amount")
    private WebElement idbc08Amount;
    @FindBy(id = "idbc-67-amount")
    private WebElement idbc67Amount;
    @FindBy(xpath = "//*[@id=\"AddIDBlockCodes\"]/div/div/div[3]/button[2]")
    private WebElement idbcPopUpSubmitButton;

    //Calculation options
    @FindBy(id = "react-select-root.task.calculationOptions.calculationTitle--value-item")
    private WebElement calculationTitle;

    public LaborRatesPO() {
        super();
    }

    public LaborRatesPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void selectPartnerShipByValueDropdown(String value) {
        this.click(partnership);
        webDriver.findElement(By.cssSelector("[id*=\"-option-"+value+"\"]")).click();
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickLaborRate1Textbox(){ new Actions(webDriver).moveToElement(laborRate1).click().perform(); }


    public void clickLaborRate2Textbox(){ new Actions(webDriver).moveToElement(laborRate2).click().perform(); }


    public void clickPaintRateTextbox(){ new Actions(webDriver).moveToElement(paintRate).click().perform(); }


    public void enterLaborRate1Textbox(String textLaborRate1) { this.sendKeys(laborRate1, textLaborRate1); }


    public void enterLaborRate2Textbox(String textLaborRate2) { this.sendKeys(laborRate2, textLaborRate2); }


    public void enterLaborRate3Textbox(String textLaborRate3) { this.sendKeys(laborRate3, textLaborRate3); }


    public void confirmChangeRate(){
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.id(CONFIRM_LABEL)));
        webDriver.findElement(By.cssSelector(".btn-solera")).click();
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOf(confirmDialog));
    }


    public void clearPaintRateTextbox(){
        //use delete key event instead of clear() since newUI seems not able to detect clear() in Chrome
        paintRate.click();
        paintRate.sendKeys(Keys.CONTROL + "a");
        paintRate.sendKeys(Keys.DELETE);
     }


    public void clearLaborRate1Textbox(){
        //use delete key event instead of clear() since newUI seems not able to detect clear() in Chrome
        laborRate1.click();
        laborRate1.sendKeys(Keys.CONTROL + "a");
        laborRate1.sendKeys(Keys.DELETE);
    }


    public void enterPaintRateTextbox(String textPaintRate) {
        this.sendKeys(paintRate, textPaintRate);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.attributeContains(paintRate,"value", textPaintRate));
    }


    public void clickAddIdbcListButton() {
        this.click(addIdbcButton);
        // Waiting for IDBC list dialog visible
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+ADD_IDBC_BTN+"'][contains(@style, 'display: block')]")));
    }


    public void enterIdbcAmount(String idbc, String amount) {
        //Using send delete key event instead of clear() since clear() is not working in Firefox 60 if the field has default value
        WebElement element = getIdbcAmountElement(idbc);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(amount);
    }


    public String getIdbcValue(String idbc) {
        return this.getAttributeValue(getIDBCValueId(idbc), GET_ATTRIBUTE_VALUE);
    }


    public void clickAddIdbcButton() { this.click(idbcPopUpSubmitButton); }


    public void clickIDBCCheckbox(String idbc) {
        this.click(getIDBCCheckboxElement(idbc));
    }


    public void clickDeleteButton(){ this.click(delete); }


    public String getLabourRate1(){ return this.getAttributeValue(By.id(ID_LABOR_RATE1), GET_ATTRIBUTE_VALUE); }

    public void test(){laborRate2.clear();}


    public String getLabourRate2(){
        return this.getAttributeValue(By.id(ID_LABOR_RATE2), GET_ATTRIBUTE_VALUE);
    }


    public String getLabourRate3(){
        return this.getAttributeValue(By.id(ID_LABOR_RATE3), GET_ATTRIBUTE_VALUE);
    }


    public String getPaintRate(){
        return this.getAttributeValue(By.id(ID_PAINT_RATE), GET_ATTRIBUTE_VALUE);
    }

    private WebElement getIdbcAmountElement(String idbc) {
        return new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id("idbc-" + idbc + "-amount")));
    }

    private By getIDBCValueId(String idbc) {
        return By.id("root.task.classInput.IDBlockCodes.IDBlockCode" + idbc + ".entry.value");
    }

    private WebElement getIDBCCheckboxElement(String idbc) {
        return new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label[for=\"root.task.classInput.IDBlockCodes.IDBlockCode" + idbc + ".iDBlockCode\"]")));
    }
}