package pageobjects.processstep;

import org.openqa.selenium.*;
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
    public static final By ID_QAPTER_GRAPHICS_NOT_AVAILABLE = By.id("graphics-not-available");
    public static final By ID_POPUP_MODEL_OPTION = By.id("popup-modal");
    public static final By ID_MODEL_OPTION_VIEW = By.id("dynamic-display-model-options-view");
    public static final By ID_ADD_POSITION_MAIN_SECTION = By.id("insert-damage-position-main-section");
    public static final By ID_NSP_MAIN_SECTION = By.id("insert-non-standard-position-main-section");
    public static final By ID_REPAIR_PANEL_MAIN_SECTION = By.id("repair-panel-main-section");
    public static final String GET_ATTRIBUTE_CLASS = "class";
    public static final String GET_ATTRIBUTE_VALUE = "value";
    public static final By ID_PICTOGRAM_SHEET = By.id("pictograms-grid");
    private static final By ID_PICTOGRAM_IMAGE = By.id("Capa_1");
    public static final By MODEL_OPTION_LOADING = By.cssSelector("#dynamic-display-model-options-view .loader-big .content .loader-circle");
    public static final String ID_REPAIR_METHOD_REPAIR = "repair-method-selector-I";
    public static final String ID_REPAIR_METHOD_SURFACE_PAINTING = "repair-method-selector-L";
    public static final String ID_REPAIR_METHOD_REPAIR_PAINTING = "repair-method-selector-LI";
    public static final String REPAIR_METHOD_SELECTED = "repair-method-selected";
    public static final String SECTION_HIDDEN = "section-hidden";
    private static final String ID_MUTATIONS_INPUT_FIELD_WU = "mutations-input-text-field-wu";
    private static final String ID_MUTATIONS_INPUT_FIELD_GM = "mutations-input-text-field-GM";
    public static final By KR_REPAIR_ERROR_DIALOG = By.cssSelector(".korean-repair-error-dialog-content");
    public static final By KR_REPAIR_ERROR_DIALOG_BLANKET = By.id("modal-blanket");
    public static final String ID_BTN_CONTINUE_IN_REPAIR_PARAMETERS = "repair-parameters-continue-btn";

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
    @FindBy(id="subhead-alert-area")
    private WebElement subheadAlert;
    @FindBy(css="#subhead-alert-area p")
    private WebElement warningMessage;

    //breadcrumb
    @FindBy(id="breadcrumb-title")
    private WebElement breadcrumbTitle;

    //Add new position - standard part
    @FindBy(id="insert-damage-position-type-standard")
    private WebElement standardPostionTab;
    @FindBy(id="standard-position-guide-number-input-section")
    private WebElement inputSPGuideNumber;
    @FindBy(id="standard-position-select-repair-method")
    private WebElement spSelectRepairMethod;
    @FindBy(id="standard-position-repair-method-E")
    private WebElement spReplaceWithOEMPart;
    @FindBy(id="insert-standard-position-action")
    private WebElement spAddPosition;
    @FindBy(id="insert-plus-standard-position-action")
    private WebElement spKeepAdding;
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
    @FindBy(id="nsp-add-as-predefined-item-label")
    private WebElement checkboxAddAsPNSP;
    //Add new position - predefined NSP
    @FindBy(id="nsp-open-predefined-nsp-item")
    private WebElement selectPredefinedNsp;

    //Predefined NSP List
    private static final By ID_PNSP_LIST_HEADER = By.id("nsp-modal-header");
    @FindBy(id="nsp-table-body")
    private WebElement pnspListBody;
    @FindBy(css=".nsp-table-body-entry")
    private List<WebElement> pnspListItems;
    @FindBy(css=".nsp-table-body-entry-group-icon")
    private List<WebElement> pnspListAddIcon;
    @FindBy(css=".nsp-table-body-entry-description-text")
    private List<WebElement> pnspListPartDescription;
    @FindBy(css=".nspTableBodyEntryMethodText")
    private List<WebElement> pnspListRepairMethod;
    @FindBy(css=".nsp-table-body-entry-description-guide-number")
    private List<WebElement> pnspListGuideNumber;
    @FindBy(css=".nsp-table-body-entry-text")
    private List<WebElement> pnspListPartNumber;
    @FindBy(css=".nsp-table-body-entry-info-work-units-value")
    private List<WebElement> pnspListWorkUnits;
    @FindBy(css=".nsp-table-body-entry-info-amount-value")
    private List<WebElement> pnspListPartPrice;
    @FindBy(id="nsp-table-footer-button-done")
    private WebElement btnPNSPListDone;
    @FindBy(id="nsp-table-footer-button-cancel")
    private WebElement btnPNSPListCancel;

    //Select side
    private static final By ID_SELECT_SIDE = By.id("select-side");
    @FindBy(id="left-side")
    private WebElement leftSide;
    @FindBy(id="right-side")
    private WebElement rightSide;

    //Repair Panel
    @FindBy(id="repair-panel-main-section")
    private WebElement repairPanelMainSection;
    @FindBy(id="insert-damage-position-main-section")
    private WebElement insertDamageRepairPanelMainSection;
    @FindBy(id="repair-method-selector-E")
    private WebElement btnReplaceWithOEMPart;
    @FindBy(id="repair-method-selector-N")
    private WebElement btnRemovalAndRefitting;
    @FindBy(id=ID_REPAIR_METHOD_REPAIR)
    private WebElement btnRepair;
    @FindBy(id="repair-method-selector-H")
    private WebElement btnHollowCavitySealing;
    @FindBy(id="repair-operations-group-header-paint")
    private WebElement btnPaint;
    @FindBy(id=ID_REPAIR_METHOD_SURFACE_PAINTING)
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
    @FindBy(id="close-repair-panel-icon")
    private WebElement closeRepairPanel;
    @FindBy(id="insert-damage-position-close-icon")
    private WebElement closeInsertPositionRepairPanel;
    //mutations
    @FindBy(id="repair-panel-mutations-section")
    private WebElement repairPanelMutationsSection;
    @FindBy(id="rm-mutations-L")
    private WebElement surfacePaintingMutations;
    //Repair parameters
    @FindBy(id="repair-panel-repair-parameters-section")
    private WebElement repairParametersSection;
    @FindBy(css=".korean-repair-severity-item")
    private List<WebElement> krSeverityTypeSelector;
    @FindBy(id=ID_BTN_CONTINUE_IN_REPAIR_PARAMETERS)
    private WebElement btnContinueInRepairParameters;
    @FindBy(id="korean-repair-severity-section")
    private WebElement repairSeveritySection;
    @FindBy(id="korean-repair-damage-section")
    private WebElement damageTypeSection;
    @FindBy(css=".korean-repair-damage-item")
    private List<WebElement> krDamageItemSelector;
    @FindBy(id="part-price-input-section-field")
    private WebElement oemPartPriceInRepairParameter;
    @FindBy(id="korean-total-working-units")
    private WebElement totalWorkingUnit;
    @FindBy(css=".btn-confirm")
    private WebElement btnConfirm;

    //Mutation
    @FindBy(id=ID_MUTATIONS_INPUT_FIELD_WU)
    private  WebElement textFieldMutations;
    @FindBy(className = "proceed-btn")
    private WebElement btnContinue;

    //New pictograms
    @FindBy(id="pictogram-view-label")
    private WebElement btnMoreView;
    @FindBy(css=".more-views-item")
    private List<WebElement> moreViewItems;
    @FindBy(css=".more-parts-actions-pictogram")
    private List<WebElement> morePartsPictogram;

    //Checklist
    @FindBy(id=ID_CHECKLIST_TABLE)
    private WebElement checklistTable;
    private static final String ID_CHECKLIST_TABLE = "checklist-table-body-section";
    @FindBy(css=".checklist-body-checkbox")
    private List<WebElement> checklistCheckbox;
    @FindBy(css=".part-description")
    private List<WebElement> checklistPartDescription;
    @FindBy(css=".guide-number")
    private List<WebElement> checklistGuideNumber;
    @FindBy(css=".part-number")
    private List<WebElement> checklistPartNumber;
    @FindBy(css=".price")
    private List<WebElement> checklistPrice;
    @FindBy(css=".repair-method")
    private List<WebElement> checklistRepairMethod;
    @FindBy(css=".labour")
    private List<WebElement> checklistLabour;
    @FindBy(css=".checklist-trash-item")
    private List<WebElement> checklistDeleteBtn;
    @FindBy(id="check-list-subhead-section-positions")
    private WebElement checklistPosition;
    @FindBy(id="check-list-subhead-section-modeloptions")
    private WebElement checklistModelOptions;
    @FindBy(id="check-list-results-modeloptions")
    private WebElement checklistModelOptionTable;
    @FindBy(id="check-list-new-position")
    private WebElement btnChecklistNewPosition;
    @FindBy(id="check-list-delete-positions")
    private WebElement btnDeletePositionInChecklist;
    @FindBy(css=".clmo-row")
    private List<WebElement> checklistModelOptionRow;

    //Calculation Preview
    @FindBy(id="calc-preview-details-preformatted-text")
    private WebElement calcPreview;

    //Zone
    @FindBy(id="dynamic-display-model-options")
    private WebElement btnModelOptionInZone;
    @FindBy(id="multiple_parts")
    private WebElement btnFastCapturing;

    //Fast Capturing
    @FindBy(id="fast-capturing-repair-method-desription")
    private WebElement selectRepairMethod;
    @FindBy(xpath="//span[@data-value='left']")
    private WebElement fastCapturingLeftSide;
    @FindBy(xpath="//span[@data-value='right']")
    private WebElement fastCapturingRightSide;
    @FindBy(xpath="//span[@data-value='center']")
    private WebElement fastCapturingNoSide;
    @FindBy(id="fast-capturing-done")
    private WebElement fastCapturingDone;

    //Select repair method
    @FindBy(id="fast-capturing-repair-method-E")
    private WebElement fcReplaceWithOemPart;
    @FindBy(id="fast-capturing-repair-method-E-All")
    private WebElement fcReplaceWithAllPart;
    @FindBy(id="fast-capturing-repair-method-N")
    private WebElement fcRemovalAndRefitting;
    @FindBy(id="fast-capturing-repair-method-N-All")
    private WebElement fcRefitAllParts;
    @FindBy(id="fast-capturing-repair-method-I")
    private WebElement fcRepair;
    @FindBy(id="fast-capturing-repair-method-L")
    private WebElement fcSurfacePainting;
    @FindBy(id="fast-capturing-repair-method-L-All")
    private WebElement fcPaintAllParts;
    @FindBy(id="fast-capturing-repair-method-LE")
    private WebElement fcNewPartPainting;
    @FindBy(id="fast-capturing-repair-method-LI")
    private WebElement fcRepairPainting;
    @FindBy(id="fast-capturing-repair-method-LI1")
    private WebElement fcRepairPaintingOver50;

    //multipart panel
    @FindBy(id="multiple-parts-section")
    private WebElement multipartsSection;

    //model option
    @FindBy(css=".model-option-description")
    private List<WebElement> modelOptionGroup;
    @FindBy(id="continue-button")
    private WebElement btnMoContinue;
    @FindBy(id="model-option-description-mW3")
    private WebElement w3Door;
    @FindBy(id="model-option-description-mW5")
    private WebElement w5Door;
    @FindBy(id="dynamic-display-zone-relevant-options-close-icon")
    private WebElement btnCloseModelOptionView;

    //Search
    private static final String ID_SEARCH_RESULT_TABLE = "search-results-table";
    @FindBy(id="inputSearch")
    private WebElement inputSearch;
    @FindBy(id="search-button")
    private WebElement searchBtn;
    @FindBy(id="part-search-description")
    private WebElement searchResultPartDescription;
    @FindBy(id="sheet-search-description")
    private WebElement searchResultSheetDescription;
    @FindBy(css=".modelOptionsMultiline")
    private WebElement searchResultModeloptions;
    @FindBy(css=".part-preview")
    private WebElement isolatedPartPreview;
    @FindBy(xpath="id(\"" + ID_SEARCH_RESULT_TABLE + "\")/tbody/tr/td[3]")
    private WebElement searchResultGuideNo;
    @FindBy(xpath="id(\"" + ID_SEARCH_RESULT_TABLE + "\")/tbody/tr/td[4]")
    private WebElement oemPartSearchResultOemCode;
    @FindBy(xpath="id(\"" + ID_SEARCH_RESULT_TABLE + "\")/tbody/tr/td[6]")
    private WebElement oemPartSearchResultModelAndSubModel;
    @FindBy(css=".search-result-part")
    private List<WebElement> searchResultPart;
    @FindBy(id="search-results")
    private WebElement searchResults;

    //Settings
    @FindBy(id="user-settings-vehicle-value-input")
    private WebElement inputVehicleValue;
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

     
    public void waitForQapterLoading(){ waitForElementInvisible(INSIDE_LOADING_CIRCLE); }

     
    public void waitForModelOptionLoading(){ waitForElementInvisible(MODEL_OPTION_LOADING); }

     
    public void switchToQapterIframe(){
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.presenceOfElementLocated(QAPTER_CONTAINER));
        webDriver.switchTo().frame(0);
        new WebDriverWait(webDriver, 30).until(ExpectedConditions.visibilityOfElementLocated(DamageCapturingPO.PAGE_LOADING_CIRCLE));
        waitForQapterLoading();
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
     //   waitForQapterLoading();
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id(ID_CHECKLIST_TABLE)));
    }

     
    public void navigationSettings(){ this.click(navigationSettings); }

     
    public void navigationInfo(){ this.click(navigationInfo); }

     
    public void navigationCalcPreview(){ this.click(navigationCalcPreview); }

     
    public void clickZone(String webElementId) {
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(webElementId)));
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id(webElementId))).click().perform();
        waitForElementInvisible(INSIDE_LOADING_CIRCLE);
    }
     
    public void clickPosition(String webElementId)  {
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(webElementId)));
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

     
    public String getMutationsWU(){ return this.getAttributeValue(By.id(ID_MUTATIONS_INPUT_FIELD_WU), GET_ATTRIBUTE_VALUE); }

     
    public String getMutationsGM(){ return this.getAttributeValue(By.id(ID_MUTATIONS_INPUT_FIELD_GM), GET_ATTRIBUTE_VALUE); }

     
    public void clickContinue(){ this.click(btnContinue); }

     
    public void clickCloseRepairPanel(){ this.click(closeRepairPanel); }

     
    public void clickCloseInsertPositionRepairPanel(){ this.click(closeInsertPositionRepairPanel); }

     
    public String getIncreaseRepairCost(String originalCost){
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(CSS_REPAIR_COST), originalCost));
        return this.getText(repairCostNumber);
    }

     
    public void clickAddNewPosition() {
        this.click(addNewPosition);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOfElementLocated(ID_ADD_POSITION_MAIN_SECTION));
    }

     
    public void clickNonStandardTab(){
        new WebDriverWait(webDriver, 10)
                .until(ExpectedConditions.visibilityOf(nonStandardPostionTab));
        new Actions(webDriver).moveToElement(nonStandardPostionTab).click().perform();
        waitForQapterLoading();
    }

     
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

     
    public void clickNSPAddPosition(){
        this.click(nspAddPosition);
        waitForQapterLoading();
    }

     
    public void clickNSPKeepAdding(){ this.click(nspKeepAdding); }

     
    public void clickAddAsPNSPCheckbox(){ this.click(checkboxAddAsPNSP); }

     
    public void clickSelectPredefinedNSP(){ this.click(selectPredefinedNsp); }

     
    public int getSizeOfPNSPList(){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(pnspListBody));
        return pnspListItems.size(); }

     
    public void clickPNSPAddIcon(int index){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(pnspListBody));
        this.click(pnspListAddIcon.get(index));
    }

     
    public void clickPNSPListDoneButton(){
        this.click(btnPNSPListDone);
        this.waitForElementInvisible(ID_PNSP_LIST_HEADER);
    }

     
    public void clickPNSPListCancelButton(){ this.click(btnPNSPListCancel);}

     
    public String getPNSPListPartDescription(int index){ return pnspListPartDescription.get(index).getText(); }

     
    public String getPNSPListGuideNumber(int index){ return pnspListGuideNumber.get(index).getText(); }

     
    public String getPNSPListPartNumber(int index){ return pnspListPartNumber.get(index).getText(); }

     
    public String getPNSPListPartPrice(int index){ return pnspListPartPrice.get(index).getText(); }

     
    public String getPNSPListRepairMethod(int index){ return pnspListRepairMethod.get(index).getText(); }

     
    public String getPNSPListWorkUnits(int index){ return pnspListWorkUnits.get(index).getText(); }

     
    public void clickMoreViewToOpenPictogram(){
        this.click(btnMoreView);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.attributeContains(By.id("pictogram-view-label"),GET_ATTRIBUTE_CLASS, "active"));
    }

     
    public void clickMoreViewToClosePictogram(){
        this.click(btnMoreView);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.attributeContains(By.id("pictogram-view-label"), GET_ATTRIBUTE_CLASS, ""));
    }

     
    public int getChecklistNumber(){
        return checklistTable.findElements(By.xpath("//div[contains(@class, 'row-wrapper')]")).size();
    }

     
    public void clickChecklistCheckbox(int row){ this.click(checklistCheckbox.get(row)); }

     
    public String getChecklistPartDescription(int row){ return checklistPartDescription.get(row).getText(); }

     
    public String getChecklistGuideNumber(int row){ return checklistGuideNumber.get(row).getText(); }

     
    public String getChecklistPartNumber(int row){ return checklistPartNumber.get(row).getText(); }

     
    public String getChecklistPrice(int row){ return checklistPrice.get(row).getText(); }

     
    public String getChecklistRepairMethod(int row){ return checklistRepairMethod.get(row).getText(); }

     
    public String getChecklistLabour(int row){
        return checklistLabour.get(row).findElement(By.xpath("div/input")).getAttribute(GET_ATTRIBUTE_VALUE);
    }

     
    public void deleteChecklistItem(int row){ this.click(checklistDeleteBtn.get(row)); }

     
    public void clickChecklistPositionTab(){ this.click(checklistPosition); }

     
    public void clickChecklistModelOptions(){
        this.click(checklistModelOptions);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(checklistModelOptionTable));
    }

     
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
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.attributeContains(leftSide,GET_ATTRIBUTE_CLASS, "side-selector-active"));
    }

     
    public void clickRightSide(){ this.click(rightSide); }

     
    public void clickModelOptionInZone(){ this.click(btnModelOptionInZone); }

     
    public void clickFastCapturing(){ this.click(btnFastCapturing); }

     
    public void clickSelectRepairMethod(){ this.click(selectRepairMethod); }

     
    public void clickFastCaptureLeftSide(){ this.click(fastCapturingLeftSide); }

     
    public void clickFastCaptureRightSide(){ this.click(fastCapturingRightSide); }

     
    public void clickFastCaptureNoSide(){ this.click(fastCapturingNoSide); }

     
    public void clickFcReplaceWithOem(){ this.click(fcReplaceWithOemPart); }

     
    public void clickFcReplaceAllParts(){ this.click(fcReplaceWithAllPart); }

     
    public void clickFcRemoveAndRefitting(){ this.click(fcRemovalAndRefitting); }

     
    public void clickFcRefitAllParts(){ this.click(fcRefitAllParts); }

     
    public void clickFcRepair(){ this.click(fcRepair); }

     
    public void clickFcSurfacePainting(){ this.click(fcSurfacePainting); }

     
    public void clickFcPaintAllParts(){ this.click(fcPaintAllParts); }

     
    public void clickFcNewPartPainting(){ this.click(fcNewPartPainting); }

     
    public void clickFcRepairPainting(){ this.click(fcRepairPainting); }

     
    public void clickFcRepairPaintingOver50(){ this.click(fcRepairPaintingOver50); }

     
    public void clickDoneInFastCapturing(){ this.click(fastCapturingDone); }

     
    public void clickMultipartsPosition(String damagePosition){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(multipartsSection));
        this.click(webDriver.findElement(By.xpath("//span[@class='multiple-part-selector-text' and contains(text(), '"+ damagePosition +"')]")));
    }

     
    public void inputSearch(String query){ this.sendKeys(inputSearch, query);}

     
    public void clickSearchBtn(){
        //Send space key event to trigger click since there is an element covered the search button
        searchBtn.sendKeys(Keys.SPACE);
    }

     
    public String getSearchResultPartDescription(){ return this.getText(searchResultPartDescription);}

     
    public String getSearchResultSheetDescription(){ return this.getText(searchResultSheetDescription);}

     
    public String getSearchResultGuideNo(){ return this.getText(searchResultGuideNo);}

     
    public String getSearchResultModelOptions(){ return this.getText(searchResultModeloptions);}

     
    public String getOemPartSearchResultOemCode(){ return this.getText(oemPartSearchResultOemCode);}

     
    public String getOemPartSearchResultModelAndSubModel(){ return this.getText(oemPartSearchResultModelAndSubModel);}

     
    public void clickSearchResultPartDescription(){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(searchResults));
        this.click(searchResultPartDescription);
    }

     
    public void clickSearchResult(){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(searchResults));
        for(WebElement result: searchResultPart){
            if(!result.getAttribute(GET_ATTRIBUTE_CLASS).contains(SECTION_HIDDEN))
            {
                this.click(result.findElement(By.xpath("td[2]")));
                break;
            }
        }
    }

     
    public boolean isIsolatedPartPreviewDisplayed() { return isolatedPartPreview.isDisplayed();}

     
    public String selectSubModel(int row){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.presenceOfElementLocated(ID_POPUP_MODEL_OPTION));
        this.click(modelOptionGroup.get(row));
        return modelOptionGroup.get(row).getText();
    }

     
    public void clickModelOptionContinue(){ this.click(btnMoContinue); }

     
    public void clickW5Door() {
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOfElementLocated(ID_MODEL_OPTION_VIEW));
        this.click(w5Door);
    }

     
    public void clickCloseModelOptionView(){ this.click(btnCloseModelOptionView); }

     
    public void clickChecklistNewPosition(){
        this.click(btnChecklistNewPosition);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOfElementLocated(ID_ADD_POSITION_MAIN_SECTION));
    }

     
    public void inputSPGuideNumber(String guideNo){ this.sendKeys(inputSPGuideNumber, guideNo); }

     
    public void clickSPSelectRepairMethod(){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.not(
                ExpectedConditions.attributeContains(spSelectRepairMethod, GET_ATTRIBUTE_CLASS, "disabled")));
        this.click(spSelectRepairMethod);
    }

     
    public void clickSPReplaceWithOem(){ this.click(spReplaceWithOEMPart); }

     
    public void clickSPKeepAdding(){
        this.click(spKeepAdding);
        waitForElementInvisible(INSIDE_LOADING_CIRCLE);
    }

     
    public void clickSPAddPosition(){ this.click(spAddPosition); }

     
    public void clickChecklistItem(int row){
        this.click(checklistGuideNumber.get(row));
        new WebDriverWait(webDriver, 3).until(
                ExpectedConditions.or(
                        ExpectedConditions.visibilityOf(repairPanelMainSection),
                        ExpectedConditions.visibilityOf(insertDamageRepairPanelMainSection)
                ));
    }

     
    public void clickDeletePositionInChecklist(){
        this.click(btnDeletePositionInChecklist);
        waitForQapterLoading();
    }

     
    public boolean isPartSelected(String webElementId){
        boolean isSelected = false;
        WebElement part = webDriver.findElement(By.id(webElementId));
        if (!part.findElements(By.cssSelector(".selected")).isEmpty())
            isSelected = true;
        return isSelected;
    }

     
    public void clickOutOfRepairPanel(){ this.click(webDriver.findElement(By.cssSelector(".subhead-zone-area-options-navigation"))); }

     
    public void inputVehicleValue(String value){ this.sendKeys(inputVehicleValue, value); }

     
    public boolean isRepairCostAlert(){
        boolean isAlert = false;
        if (subheadAlert.getAttribute(GET_ATTRIBUTE_CLASS).contains("state-high"))
            isAlert = true;
        return isAlert;
    }

     
    public String getWarningMessage(){ return this.getText(warningMessage); }

     
    public int getPictogramNumber(){ return moreViewItems.size(); }

     
    public int getMorePartsPictogramNumber(){
        int pictogramDisplayNum = 0;
        for(WebElement morePartsEle: morePartsPictogram) {
            WebElement parentNode = morePartsEle.findElement(By.xpath(".."));
            if (!parentNode.getAttribute("style").contains("display: none"))
                pictogramDisplayNum++;
        }
        return pictogramDisplayNum;
    }

     
    public List<WebElement> getMorePartsPictogram(){ return morePartsPictogram; }

     
    public int getModelOptionNumberInChecklist(){ return checklistModelOptionRow.size(); }

     
    public boolean isPictogramWithImage(){
        boolean isWithImage = true;
        for(WebElement pictogram: moreViewItems) {
            WebElement pictogramDiv = pictogram.findElement(By.xpath("div"));
            if(!pictogramDiv.getAttribute(GET_ATTRIBUTE_CLASS).contains("disable") &&
                    pictogramDiv.findElements(ID_PICTOGRAM_IMAGE).isEmpty()){
                isWithImage = false;
            }
        }
        return isWithImage;
    }

     
    public void selectKRSeverityType(int type){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(repairSeveritySection));
        this.click(krSeverityTypeSelector.get(type));
    }

     
    public void selectKRDamageItem(int type){
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.visibilityOf(damageTypeSection));
        this.click(krDamageItemSelector.get(type));
    }

     
    public int getDamageTypeItemNumber(){ return krDamageItemSelector.size(); }

     
    public String getDamageTypeName(int item){ return krDamageItemSelector.get(item).getText(); }

     
    public void inputOemPartPrice(double price){ this.sendKeys(oemPartPriceInRepairParameter, String.valueOf(price)); }

     
    public String getTotalWorkingUnits(){ return totalWorkingUnit.getText(); }

     
    public void clickContinueInRepairParameters(){ this.click(btnContinueInRepairParameters); }

     
    public void clickSurfacePaintingMutations(){
        this.click(surfacePaintingMutations);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.not(
                ExpectedConditions.attributeContains(repairPanelMutationsSection, GET_ATTRIBUTE_CLASS, SECTION_HIDDEN)));
    }

     
    public String getRepairErrorMessage(){ return webDriver.findElement(KR_REPAIR_ERROR_DIALOG).getText(); }

     
    public void clickBtnCloseInRepairErrorDialog(){
        this.click(btnConfirm);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.invisibilityOfElementLocated(KR_REPAIR_ERROR_DIALOG_BLANKET));
    }

    public void click3dView() {
        this.click(threeDView);
    }

    public WebElement getThreeDView() {
        return threeDView;
    }
}
