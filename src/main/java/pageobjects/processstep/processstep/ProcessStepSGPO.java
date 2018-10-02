package pageobjects.processstep.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

public class ProcessStepSGPO extends PageObject {
    //Loading circle
    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");

    //Icon menu
    @FindBy(id="worklist")
    private WebElement claimManager;
    @FindBy(id = "messagelist")
    private WebElement messages;
    @FindBy(id = "support")
    private WebElement support;
    @FindBy(id = "logout")
    private WebElement logout;

    //Navigation menu on the left
    @FindBy(id = "toDoListItem_GeneralDetailsSG")
    private WebElement generalDetailsSG;
    @FindBy(id = "toDoListItem_DamageDetailsSG")
    private WebElement damageDetailsSG;
    @FindBy(id = "toDoListItem_AttachmentsSG")
    private WebElement attachmentsSG;
    @FindBy(id = "toDoListItem_LaborRateSG")
    private WebElement laborRateSG;
    @FindBy(id = "toDoListItem_DamageCaptureSG")
    private WebElement damageCaptureSG;
    @FindBy(id = "toDoListItem_ModifySparePartsSG")
    private WebElement modifySparePartsSG;
    @FindBy(id = "toDoListItem_ReportsSG")
    private WebElement reportsSG;
    @FindBy(id = "toDoListItem_CompareSG")
    private WebElement compareSG;
    @FindBy(id = "toDoListItem_CaseHistorySG")
    private WebElement caseHistorySG;

    private static final By QAPTER_CONTENT = By.id("process-step-content");

    public ProcessStepSGPO() {
        super();
    }

    public ProcessStepSGPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void clickClaimManager(){
        this.click(claimManager);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickMessages(){
        this.click(messages);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickLogout(){
        this.click(logout);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickGeneralDetailsSG(){
        this.click(generalDetailsSG);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickDamageDetailsSG(){
        this.click(damageDetailsSG); }


    public void clickAttachmentsSG(){
        this.click(attachmentsSG);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickLaborRateSG(){
        this.click(laborRateSG);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickDamageCaptureSG(){
        this.click(damageCaptureSG);
        waitForElementInvisible(LOADING_CIRCLE);
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.attributeContains(QAPTER_CONTENT,"class", "integrated-mode"));
    }


    public void clickModifySparePartsSG(){
        this.click(modifySparePartsSG);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickReportsSG(){
        this.click(reportsSG);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickCompareSG(){
        this.click(compareSG);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public void clickCaseHistorySG(){
        this.click(caseHistorySG);
        waitForElementInvisible(LOADING_CIRCLE);
    }
}
