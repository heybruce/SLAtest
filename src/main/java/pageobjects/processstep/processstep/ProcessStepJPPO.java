package pageobjects.processstep.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;
import pageobjects.processstep.LaborRatesPO;
import pageobjects.worklistgrid.WorkListGridPO;

public class ProcessStepJPPO extends ProcessStepPO {

    //Loading circle
    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");
    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/nav/div/div[1]/div[2]")
    private WebElement currentStep;

    //Icon menu
    @FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div[1]/nav/div/div[1]/div[1]/button")
    private WebElement navigationActions;
    @FindBy(id = "worklist")
    private WebElement workList;
    @FindBy(id = "support")
    private WebElement support;
    @FindBy(id = "logout")
    private WebElement logout;

    //Navigation menu on the left
    @FindBy(id = "toDoListItem_ClaimInfoJP")
    private WebElement claimInfo;
    @FindBy(id = "toDoListItem_LaborRateJP")
    private WebElement labourRates;
    @FindBy(id = "toDoListItem_AttachmentsJP")
    private WebElement attachments;
    @FindBy(id = "toDoListItem_DamageCaptureJP")
    private WebElement damageCapture;
    @FindBy(id = "toDoListItem_CalculationOptionsJP")
    private WebElement calculationOptions;
    @FindBy(id = "toDoListItem_CalculationsJP")
    private WebElement calculations;
    @FindBy(id = "toDoListItem_CaseInfoJP")
    private WebElement caseInfo;

    private static final By QAPTER_CONTENT = By.id("process-step-content");


    public ProcessStepJPPO() {
        super();
    }

    public ProcessStepJPPO(WebDriver webDriver) {
        super(webDriver);
    }

      
    public void clickClaimInfoTab() {
        this.click(claimInfo);
    }

      
    public void clickLabourRatesTab() {
        this.click(labourRates);
        this.waitForElementPresent(By.id(LaborRatesPO.ADD_IDBC_BTN));
    }

      
    public void clickAttachmentsTab() {
        this.click(attachments);
        waitForElementInvisible(LOADING_CIRCLE);
        waitForElementInvisible(LOADING_CIRCLE);
    }

      
    public void clickDamageCaptureTab(){
        this.click(damageCapture);
        waitForElementInvisible(LOADING_CIRCLE);
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.attributeContains(QAPTER_CONTENT,"class", "integrated-mode"));
    }

      
    public void clickCalculationOptionsTab(){
        this.click(calculationOptions);
        waitForElementInvisible(LOADING_CIRCLE);
    }

      
    public void clickCalculationsTab(){
        this.click(calculations);
        waitForElementInvisible(LOADING_CIRCLE);
    }

      
    public void clickCaseInfoTab(){
        this.click(caseInfo);
        waitForElementInvisible(LOADING_CIRCLE);}

      
    public String getCurrentStepText() { return getText(currentStep); }


      
    public void clickWorkListIcon() {
        try {
            this.click(workList);
            new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id(WorkListGridPO.NEW_CASE_BTN_ID)));
        }catch (TimeoutException e) {
            //Retry click
            this.click(workList);
        }
        waitForElementInvisible(LOADING_CIRCLE);
    }

      
    public void clickSupportIcon() { this.click(support); }

      
    public void clickLogoutIcon() { this.click(logout); }

      
    public void clickNavigationActions() { this.click(navigationActions);}

}
