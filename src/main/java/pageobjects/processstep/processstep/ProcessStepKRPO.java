package pageobjects.processstep.processstep;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;
import pageobjects.processstep.AttachmentsRepairerPO;
import pageobjects.processstep.LaborRatesPO;
import pageobjects.worklistgrid.WorkListGridPO;

public class ProcessStepKRPO extends ProcessStepPO {

    //Loading circle
    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");
    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/nav/div/div[1]/div[2]")
    private WebElement currentStep;

    //Icon menu
    @FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div[1]/nav/div/div[1]/div[1]/button")
    private WebElement navigationActions;
    @FindBy(id = "home")
    private WebElement home;
    @FindBy(id = "claimManager")
    private WebElement claimManager;
    @FindBy(id = "messagelist")
    private WebElement messages;
    @FindBy(id = "settings")
    private WebElement settings;
    @FindBy(id = "support")
    private WebElement support;
    @FindBy(id = "logout")
    private WebElement logout;

    //Navigation menu on the left
    @FindBy(id = "toDoListItem_Claim_Details")
    private WebElement claimDetails;
    @FindBy(id = "toDoListItem_Attachments_Repairer")
    private WebElement attachmentsRepairer;
    @FindBy(id = "toDoListItem_DamageCapturing")
    private WebElement damageCapturing;
    @FindBy(id = "toDoListItem_Modify_spare_parts")
    private WebElement modifyParts;
    @FindBy(id = "toDoListItem_Calculations_RC")
    private WebElement reports;
    @FindBy(id = "toDoListItem_Compare")
    private WebElement compare;
    @FindBy(id = "toDoListItem_Labor_rates_KR")
    private WebElement labourRatesKR;
    @FindBy(id = "toDoListItem_Case_History_KR")
    private WebElement caseHistoryKR;


    public ProcessStepKRPO() {
        super();
    }

    public ProcessStepKRPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void clickAttachmentsRepairerTab() {
        this.click(attachmentsRepairer);
        waitForElementPresent(By.id(AttachmentsRepairerPO.CLAIMS_DOCUMENT_BLOCK));
    }


    public void clickClaimDetailsTab() {
        this.click(claimDetails);
    }



    public void clickLabourRatesKRTab() {
        this.click(labourRatesKR);
        this.waitForElementPresent(By.id(LaborRatesPO.ADD_IDBC_BTN));
    }


    public void clickDamageCapturingTab(){ this.click(damageCapturing); }


    public void clickModifySparePartsTab(){ this.click(modifyParts); }


    public void clickReportsTab(){
        this.click(reports);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickCompareTab(){ this.click(compare); }


    public void clickCaseHistoryKRTab(){
        this.click(caseHistoryKR);
        waitForElementInvisible(LOADING_CIRCLE);}


    public String getCurrentStepText() { return getText(currentStep); }


    public void clickClaimManagerIcon() {
        try {
            this.click(claimManager);
            new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id(WorkListGridPO.NEW_CASE_BTN_ID)));
        }catch (TimeoutException e) {
            //Retry click
            this.click(claimManager);
        }
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickMessagesIcon() { this.click(messages); }


    public void clickSettingsIcon() { this.click(settings); }


    public void clickSupportIcon() { this.click(support); }


    public void clickLogoutIcon() { this.click(logout); }


    public void clickNavigationActions() { this.click(navigationActions);}

}
