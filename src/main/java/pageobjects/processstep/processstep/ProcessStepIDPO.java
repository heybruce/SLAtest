package pageobjects.processstep.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

public class ProcessStepIDPO extends PageObject {

    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");

    //Navigation menu on the left
    @FindBy(id = "toDoListItem_GeneralDetailsID")
    private WebElement generalDetailsID;
    @FindBy(id = "toDoListItem_DamageDetailsID")
    private WebElement damageDetailsID;
    @FindBy(id = "toDoListItem_AttachmentsID")
    private WebElement attachmentsID;
    @FindBy(id = "toDoListItem_LaborRateID")
    private WebElement laborRateID;
    @FindBy(id = "toDoListItem_DamageCaptureID")
    private WebElement damageCaptureID;
    @FindBy(id = "toDoListItem_ModifySparePartsID")
    private WebElement modifySparePartsID;
    @FindBy(id = "toDoListItem_ReportsID")
    private WebElement reportsID;
    @FindBy(id = "toDoListItem_CompareID")
    private WebElement compareID;
    @FindBy(id = "toDoListItem_CaseHistoryID")
    private WebElement caseHistoryID;

    private static final By QAPTER_CONTENT = By.id("process-step-content");

    public ProcessStepIDPO() {
        super();
    }

    public ProcessStepIDPO(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickDamageDetailsID(){
        this.click(damageDetailsID); }

    public void clickAttachmentsID(){
        this.click(attachmentsID);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickLaborRateID(){
        this.click(laborRateID);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickDamageCaptureID(){
        this.click(damageCaptureID);
        waitForElementInvisible(LOADING_CIRCLE);
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.attributeContains(QAPTER_CONTENT,"class", "integrated-mode"));
    }

    public void clickModifySparePartsID(){
        this.click(modifySparePartsID);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickReportsID(){
        this.click(reportsID);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickCompareID(){
        this.click(compareID);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickCaseHistoryID(){
        this.click(caseHistoryID);
        waitForElementInvisible(LOADING_CIRCLE);
    }
}
