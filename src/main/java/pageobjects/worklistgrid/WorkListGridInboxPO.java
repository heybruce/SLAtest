package pageobjects.worklistgrid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WorkListGridInboxPO extends WorkListGridPO {

    @FindBy(id = "MergeTask")
    private WebElement mergeTask;
    @FindBy(id = "ExportToExcel")
    private WebElement exportToExcel;

    //Merge Dialog
    @FindBy(id="MergeTaskPopup")
    private WebElement mergeTaskDialog;
    @FindBy(id="root.task.workflow.mergeTask.dialog-comment")
    private WebElement textMergeComment;
    @FindBy(css="#MergeTaskPopup div div div.modal-footer button:nth-of-type(3)")
    private WebElement btnMergeInDialog;

    //Action Component in each row
    @FindBy(id="workflowactionInlineContainer_MergeTask")
    private WebElement btnMergeTasksInRow;

    public WorkListGridInboxPO() {
        super();
    }
    public WorkListGridInboxPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void clickMergeTask(){
        this.click(mergeTask);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(mergeTaskDialog, "class", "in"));
    }

    public void clickExportTask(){ this.click(exportToExcel);}

    //Merge task actions

    public void enterMergeComment(String text){ this.sendKeys(textMergeComment, text); }

    public void clickMergeBtnInDialog(){
        this.click(btnMergeInDialog);
        waitForElementInvisible(LOADING_CIRCLE);
        waitForElementInvisible(NOTIFICATION_POPUP);
    }


    public void clickMergeTasksInRow(){
        this.click(btnMergeTasksInRow);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(mergeTaskDialog, "class", "in"));
    }
}
