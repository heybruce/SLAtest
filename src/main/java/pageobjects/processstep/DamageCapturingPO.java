package pageobjects.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.util.List;

public class DamageCapturingPO extends PageObject {
    //Loading Circle
    public static final By PAGE_LOADING_CIRCLE = By.cssSelector(".loader-big .content .loader-circle");
    public static final By INSIDE_LOADING_CIRCLE = By.cssSelector("#content .loader-big .content .loader-circle");
    public static final By QAPTER_CONTAINER = By.id("qapter-container");
    public static final String CSS_REPAIR_COST = "#repair-cost-value .number";

    @FindBy(id = "root.task.damageCapture.webpadImage")
    private WebElement qapterIcon;

    //iframe
    @FindBy(id="iframe_root.task.damageCapture.inlineWebPad")
    private WebElement iframeQapter;
    @FindBy(tagName = "iframe")
    private  WebElement iframe;

    //toolbar
    @FindBy(id="navigation-vehicle")
    private WebElement navigationVehicle;
    @FindBy(id="navigation-photo")
    private WebElement navigationPhoto;
    @FindBy(id="navigation-search")
    private WebElement navigationSearch;
    @FindBy(id="navigation-adjustment")
    private WebElement navigationAdjustment;
    @FindBy(id="navigation-checklist")
    private WebElement navigationChecklist;
    @FindBy(id="navigation-settings")
    private WebElement navigationSettings;
    @FindBy(id="navigation-info")
    private WebElement navigationInfo;
    @FindBy(id="navigation-receipt")
    private WebElement navigationCalcPreview;
    @FindBy(css=CSS_REPAIR_COST)
    private WebElement repairCostNumber;
    @FindBy(css="#repair-cost-value .currency-symbol")
    private WebElement repairCostCurrency;
    @FindBy(id="new_position")
    private WebElement addNewPosition;

    //breadcrumb
    @FindBy(id="breadcrumb-title")
    private WebElement breadcrumbTitle;

    //Add new position - standard part
    @FindBy(id="insert-damage-position-type-standard")
    private WebElement standardPostionTab;
    //Add new position - non-standard part
    @FindBy(id="insert-damage-position-type-non-standard")
    private WebElement nonStandardPostionTab;
    @FindBy(id="nsp-repair-method-S")
    private WebElement nspSupplementary;
    @FindBy(id="nsp-repair-method-E")
    private WebElement nspreplaceWithOEMPart;
    @FindBy(id="nsp-repair-method-N")
    private WebElement nspRemovalRefitting;
    @FindBy(id="nsp-repair-method-I")
    private WebElement nspRepair;
    @FindBy(id="nsp-repair-method-V")
    private WebElement nspOpticalAlignment;
    @FindBy(id="nsp-repair-method-H")
    private WebElement nspHollowCavitySealing;
    @FindBy(id="nsp-repair-method-LE")
    private WebElement nspNewPartPainting;
    @FindBy(id="nsp-repair-method-LI")
    private WebElement nspRepairPainting;
    @FindBy(id="nsp-repair-method-L")
    private WebElement nspSurfacePainting;
    @FindBy(id="nsp-mandatory-description-item")
    private WebElement nspDescription;
    @FindBy(id="nsp-mandatory-spare-part-item")
    private WebElement nspSparePart;
    @FindBy(id="nsp-optional-labour-item")
    private WebElement nspWorkUnits;
    @FindBy(id="nsp-optional-amount-item")
    private WebElement nspAmount;
    @FindBy(id="nsp-add-nsp-footer-item")
    private WebElement nspAddPosition;
    @FindBy(id="nsp-adding-nsp-footer-item")
    private WebElement nspKeepAdding;

    //Select side
    private static final By ID_SELECT_SIDE = By.id("select-side");
    @FindBy(id="left-side")
    private WebElement leftSide;
    @FindBy(id="right-side")
    private WebElement rightSide;

    //Repair Panel
    @FindBy(id="repair-panel")
    private WebElement repairPanel;
    @FindBy(id="repair-panel-main-section")
    private WebElement repairPanelMainSection;
    @FindBy(id="repair-method-selector-E")
    private WebElement btnReplaceWithOEMPart;
    @FindBy(id="repair-method-selector-N")
    private WebElement btnRemovalAndRefitting;
    @FindBy(id="repair-method-selector-I")
    private WebElement btnRepair;
    @FindBy(id="repair-method-selector-H")
    private WebElement btnHollowCavitySealing;
    @FindBy(id="repair-operations-group-header-paint")
    private WebElement btnPaint;
    @FindBy(id="repair-method-selector-L")
    private WebElement btnSurfacePainting;
    @FindBy(id="repair-method-selector-LE")
    private WebElement btnNewPartPainting;
    @FindBy(id="repair-method-selector-LI")
    private WebElement btnRepairPainting;
    @FindBy(id="part-description")
    private WebElement rptextPartDescription;
    @FindBy(css="#part-price-value .price-amount")
    private WebElement rptextPartPriceAmount;
    @FindBy(id="oem-part-number")
    private WebElement rptextPartNumber;
    @FindBy(id="rp-guidenumber-value")
    private WebElement rptextGuideNumber;

    //Mutation
    @FindBy(id="mutations-input-text-field-wu")
    private  WebElement textFieldMutations;
    @FindBy(id="repair-parameters-continue-btn")
    private WebElement btnContinue;

    //New pictograms
    @FindBy(id="pictogram-view-label")
    private WebElement btnMoreView;
    @FindBy(id="pictogram-108")
    private WebElement newPictogramBodyPainting;
    @FindBy(id="pictogram-874")
    private WebElement newPictogramOuterBody;
    @FindBy(id="pictogram-816")
    private  WebElement newPictogramRoofFrame;
    @FindBy(id="pictogram-808")
    private WebElement newPictogramEngineCompartment;
    @FindBy(id="pictogram-812")
    private WebElement newPictogramBodyShell;

    //Checklist
    @FindBy(id=ID_CHECKLIST_TABLE)
    private WebElement checklistTable;
    private static final String ID_CHECKLIST_TABLE = "checklist-table-body-section";
    @FindBy(css=".part-description")
    private List<WebElement> checklistPartDescription;
    @FindBy(css=".guide-number")
    private List<WebElement> checklistGuideNumber;
    @FindBy(css=".part-number")
    private List<WebElement> checklistPartNumber;
    @FindBy(css=".price")
    private List<WebElement> checklistPrice;

    //Calculation Preview
    @FindBy(id="calc-preview-details-preformatted-text")
    private WebElement calcPreview;

    //Settings
    @FindBy(id = "box")
    private WebElement threeDView;


    public DamageCapturingPO() {
        super();
    }

    public DamageCapturingPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void clickQapterIcon() {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(qapterIcon));
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.attributeToBe(qapterIcon, "role", "presentation"));
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.elementToBeClickable(qapterIcon));
        this.click(qapterIcon);
        switchToQapterIframe();
    }


    public void switchToQapterIframe(){
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.presenceOfElementLocated(QAPTER_CONTAINER));
        webDriver.switchTo().frame(0);
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(DamageCapturingPO.PAGE_LOADING_CIRCLE));
        waitForElementInvisible(DamageCapturingPO.INSIDE_LOADING_CIRCLE);
    }


    public void navigationVehicle(){
        this.click(navigationVehicle);
        waitForElementInvisible(INSIDE_LOADING_CIRCLE);
    }


    public void navigationPhoto(){ this.click(navigationPhoto); }


    public void navigationSearch(){ this.click(navigationSearch); }


    public void navigationAdjustment(){ this.click(navigationAdjustment); }


    public void navigationChecklist() {
        this.click(navigationChecklist);
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id(ID_CHECKLIST_TABLE)));
    }


    public void navigationSettings(){ this.click(navigationSettings); }


    public void navigationInfo(){ this.click(navigationInfo); }


    public void navigationCalcPreview(){ this.click(navigationCalcPreview); }


    public void clickZone(String webElementId) {
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id(webElementId))));
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id(webElementId))).click().perform();
        waitForElementInvisible(INSIDE_LOADING_CIRCLE);
    }

    public void clickPosition(String webElementId)  {
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id(webElementId))));
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id(webElementId))).click().perform();
    }


    public void clickRPReplaceWithOEMPart(){ this.click(btnReplaceWithOEMPart); }


    public void clickRPRemovalAndRefitting(){ this.click(btnRemovalAndRefitting); }


    public void clickRPRepair(){ this.click(btnRepair); }


    public void clickRPPaint() { this.click(btnPaint); }


    public void clickRPSurfPainting(){ this.click(btnSurfacePainting); }


    public void clickRPNewPartPainting(){ this.click(btnNewPartPainting); }


    public void clickRPRepairPainting(){ this.click(btnRepairPainting); }


    public void clickRPHollowCavitySealing() {
        this.click(btnHollowCavitySealing);
    }


    public void inputMutations(String mutations){ this.sendKeys(textFieldMutations, mutations); }


    public void clickContinue(){ this.click(btnContinue); }


    public String getIncreaseRepairCost(String originalCost){
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(CSS_REPAIR_COST), originalCost));
        return this.getText(repairCostNumber);
    }


    public void clickAddNewPosition() {
        this.click(addNewPosition);
    }


    public void clickNonStandardTab(){ this.click(nonStandardPostionTab); }


    public void clickNSPSupplementary(){ this.click(nspSupplementary); }


    public void clickNSPReplacewithOEM(){ this.click(nspreplaceWithOEMPart); }


    public void clickNSPRemoveAndRefitting(){ this.click(nspRemovalRefitting); }


    public void clickNSPRepair(){ this.click(nspRepair); }


    public void clickNSPOpticalAligment(){ this.click(nspOpticalAlignment); }


    public void clickNSPHollowCavitySealing(){ this.click(nspHollowCavitySealing); }


    public void clickNSPNewPartPainting(){ this.click(nspNewPartPainting); }


    public void clickNSPRepairPainting(){ this.click(nspRepairPainting); }


    public void clickNSPSurfacePainting(){ this.click(nspSurfacePainting); }


    public void inputNSPDescription(String description){ this.sendKeys(nspDescription, description); }


    public void inputNSPSparePart(String sparePart){ this.sendKeys(nspSparePart, sparePart); }


    public void inputNSPWorkUnits(String workUnits){ this.sendKeys(nspWorkUnits,workUnits); }


    public void inputNSPAmount(String amount){ this.sendKeys(nspAmount,amount); }


    public void clickNSPAddPosition(){ this.click(nspAddPosition); }


    public void clickNSPKeepAdding(){ this.click(nspKeepAdding); }


    public void clickMoreView(){
        this.click(btnMoreView);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.attributeContains(By.id("pictogram-view-label"),"class", "active"));
    }


    public void clickPictogramOuterBody(){ this.click(newPictogramOuterBody); }


    public void clickNewPictogramBodyPainting(){ this.click(newPictogramBodyPainting); }


    public void clickNewPictogramBodyShell(){ this.click(newPictogramBodyShell); }


    public void clickPictogramEngineCompartment(){ this.click(newPictogramEngineCompartment); }


    public void clickNewPictogramRoofFrame(){ this.click(newPictogramRoofFrame); }


    public int getChecklistNumber(){
        return checklistTable.findElements(By.xpath("//div[contains(@class, 'row-wrapper')]")).size();
    }


    public String getChecklistPartDescription(int row){ return checklistPartDescription.get(row).getText(); }


    public String getChecklistGuideNumber(int row){ return checklistGuideNumber.get(row).getText(); }


    public String getChecklistPartNumber(int row){ return checklistPartNumber.get(row).getText(); }


    public String getChecklistPrice(int row){ return checklistPrice.get(row).getText(); }


    public String getRPPartDescription(){ return this.getText(rptextPartDescription); }


    public String getRPPartPriceAmount(){ return this.getText(rptextPartPriceAmount); }


    public String getRPPartNumber(){ return this.getText(rptextPartNumber); }


    public String getRPGuideNumber(){ return this.getText(rptextGuideNumber); }


    public String getCalcPreviewContent() { return this.getText(calcPreview); }


    public boolean isSelectSidePopUp(){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(repairPanelMainSection));
        return !webDriver.findElements(ID_SELECT_SIDE).isEmpty();
    }


    public void clickLeftSide(){
        this.click(leftSide);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.attributeContains(leftSide,"class", "side-selector-active"));
    }


    public void clickRightSide(){ this.click(rightSide); }

    public void click3dView() {
        this.click(threeDView);
    }

    public WebElement getThreeDView() {
        return threeDView;
    }
}
