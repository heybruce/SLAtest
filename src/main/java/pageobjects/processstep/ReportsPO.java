package pageobjects.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

import java.util.List;

public class ReportsPO extends PageObject {
    //Loading circle
    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");
    private static final String CALCULATION_CHECKEDBOX_CSS_LOCATOR = "label[for=\"checked_checkIdN\"]";

    public static final String ID_CALCULATION_ALTERNATIVE = "root.task.calculationOptions.calculateAlternative";
    @FindBy(id=ID_CALCULATION_ALTERNATIVE)
    private WebElement btnCalculateAlternative;

    @FindBy(css=".claim-identification-item-value")
    private WebElement textClaimNumber;

    //Calculation list
    @FindBy(name = "calculationTitle")
    private List<WebElement> calculationTitle;
    @FindBy(name = "calculationDateTime")
    private List<WebElement> calculationDateTime;
    @FindBy(name = "userId")
    private List<WebElement> userId;
    @FindBy(name = "grandTotalWTaxCombined")
    private List<WebElement> grandTotalWTaxCombined;
    @FindBy(name = "FCRepTot")
    private List<WebElement> repairTotal;
    @FindBy(name = "FCPartTot")
    private List<WebElement> partsTotal;
    @FindBy(name = "FCLaborTot")
    private List<WebElement> labourTotal;
    @FindBy(name = "FCPaintTotOverAll")
    private List<WebElement> paintTotal;
    @FindBy(name = "FCAdditionalCostTot")
    private List<WebElement> additionalCostTotal;
    @FindBy(id="printCalculationButton")
    private List<WebElement> btnPrintCalculation;
    @FindBy(name = "grandTotalWithTax")
    private List<WebElement> grandTotalWithTax;

    // Calculation Output
    @FindBy(id="root.task.calculationList.calculationOutput")
    private WebElement calculationOutput;

    //Customize
    @FindBy(css=".table-customize-header-container")
    private WebElement btnCustomizeInCalculations;
    @FindBy(css=".table-customize-component-row")
    private List<WebElement> listOfCustomizeCheckedbox;
    @FindBy(css=".table-customize-component-save")
    private WebElement btnApplyInCalculation;

    public ReportsPO() {
        super();
    }

    public ReportsPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void clickCalculateAlternative() {
        this.click(btnCalculateAlternative);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickCalculationCheckedbox(int row){
        this.click(webDriver.findElement(By.cssSelector(CALCULATION_CHECKEDBOX_CSS_LOCATOR.replace("N", String.valueOf(row)))));
    }


    public int getCalculationNumber(){ return calculationDateTime.size(); }


    public String getCalculationTitle(int row){ return this.getText(calculationTitle.get(row)); }


    public String getCalculationDateTime(int row){ return this.getText(calculationDateTime.get(row)); }


    public String getUserId(int row){ return this.getText(userId.get(row)); }


    public String getGrandTotalWTaxCombined(int row){ return this.getText(grandTotalWTaxCombined.get(row)); }


    public String getRepairTotal(int row){ return this.getText(repairTotal.get(row)); }


    public String getPartsTotal(int row){ return this.getText(partsTotal.get(row)); }


    public String getLabourTotal(int row){ return this.getText(labourTotal.get(row)); }


    public String getPaintTotal(int row){ return this.getText(paintTotal.get(row)); }


    public String getAdditionalCost(int row){ return this.getText(additionalCostTotal.get(row)); }


    public String getGrandTotalWithTax(int row){ return this.getText(grandTotalWithTax.get(row)); }


    public void clickPrintCalculation(int row){ this.click(btnPrintCalculation.get(row)); }


    public String getCalculationOutput() { return this.getText(calculationOutput); }


    public String getClaimNumber(){ return this.getText(textClaimNumber); }


    public void clickCustomizeBtn(){ this.click(btnCustomizeInCalculations); }


    public void checkAllCustomizeCheckedbox(){
        for(WebElement calcilationCustomize: listOfCustomizeCheckedbox) {
            if(!calcilationCustomize.getAttribute("class").contains("z-selected")){
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", calcilationCustomize);
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", calcilationCustomize);
            }
        }
        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0, 0)", "");
    }


    public void clickBtnApply(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", btnApplyInCalculation);
    }
}
