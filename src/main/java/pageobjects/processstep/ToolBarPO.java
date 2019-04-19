package pageobjects.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

public class ToolBarPO extends PageObject {
    public ToolBarPO() {
        super();
    }
    public ToolBarPO(WebDriver webDriver) {
        super(webDriver);
    }

    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");
    private static final String GET_ATTRIBUTE_VALUE = "value";

    protected static final By NOTIFICATION_POPUP = By.cssSelector(".notification-component");
    private String classString = "class";

    public static final String ID_SEND_ESTIMATE_BTN = "root.task.workflow.changeBusinessStatus.value23.update";
    
    //Tool bar menu
    @FindBy(id="more-row-icon-toolbar")
    private WebElement btnToolBar;
    @FindBy(id="root.actionButtons.printPDFButton")
    private WebElement btnPrintPDF;
    @FindBy(id="root.task.workflow.sendTask.send")
    private WebElement btnSend;
    @FindBy(id="root.task.workflow.closeTask.close")
    private WebElement btnClose;
    @FindBy(id="root.task.workflow.changeBusinessStatus.value5.update")
    private WebElement btnApprove;
    @FindBy(id="root.task.workflow.changeBusinessStatus.value6.update")
    private WebElement btnReject;
    @FindBy(id="root.task.genericCaseMessages.sendGenericCaseMessage.sendmessage")
    private WebElement btnSendMessage;
    @FindBy(id="root.task.workflow.changeOwner.change")
    private WebElement btnChangeOwner;
    @FindBy(id="root.copyIntoNewCaseComponent.copyButton")
    private WebElement btnCopy;
    @FindBy(id="root.task.workflow.mergeTask.merge")
    private WebElement btnMergeTask;
    @FindBy(id="root.task.workflow.reopenTask.reopen")
    private WebElement btnReopen;
    @FindBy(id="root.actionButtons.commentsButton")
    private WebElement btnNewComment;
    @FindBy(id=ID_SEND_ESTIMATE_BTN)
    private WebElement btnSendEstimate;
    @FindBy(id="root.task.workflow.sendTask.assign")
    private WebElement btnAssign;

    //Print PDF dialog
    @FindBy(id="PrintPDFPopup")
    private WebElement printPdfPopup;
    @FindBy(id="react-select-root.printPdf.printFormat--value")
    private WebElement selectPrintFormat;
    @FindBy(css="input[id=\"root.printPdf.calculation\"]")
    private WebElement selectCalculation;
    @FindBy(id = "react-select-root.printPdf.storereport--value")
    private WebElement selectStoreReport;
    @FindBy(id=ID_PDF_FILE_NAME)
    private WebElement textboxFileName;
    @FindBy(css="#PrintPDFPopup div div div.modal-footer button:nth-of-type(3)")
    private WebElement btnGeneratePDF;
    private static final String ID_PDF_FILE_NAME="root.printPdf.filename";

    //Close Dialog
    @FindBy(id="com-audatex-breservices-ui-components-workflow-CloseTaskWorkflowActionComponent")
    private WebElement closeTaskDialog;
    @FindBy(id="root.task.workflow.closeTask.dialog-comment")
    private WebElement textCloseTaskComment;
    @FindBy(css="#com-audatex-breservices-ui-components-workflow-CloseTaskWorkflowActionComponent div div div.modal-footer button:nth-of-type(3)")
    private WebElement btnCloseTask;

    //Send Dialog
    @FindBy(id="com-audatex-breservices-ui-components-workflow-SendTaskWorkflowActionComponent")
    private WebElement sendTaskDialog;
    @FindBy(id="root.task.workflow.sendTask.dialog-membersearchfield-advancedSearchButton")
    private WebElement sendTaskAdvancedSearchBtn;
    @FindBy(id="root.task.workflow.sendTask.dialog-membersearchfield-advancedSearchWindow-name_or_loginId")
    private WebElement receiverNameField;
    @FindBy(xpath = "id(\"root-task-workflow-sendTaskSendTaskWorkflowActionMemberSearchPopup\")/div/div/div[3]/button[3]")
    private WebElement sendTaskMemberSearchBtn;
    @FindBy(name="loginId")
    private WebElement searchResultLoginId;
    @FindBy(xpath="id(\"com-audatex-breservices-ui-components-workflow-SendTaskWorkflowActionComponent\")/div/div/div[3]/button[3]")
    private WebElement sendTaskBtn;
    @FindBy(id="root.task.workflow.sendTask.dialog-login")
    private WebElement receiverNameSearchResult;
    @FindBy(id="AssignToolbarPopup")
    private WebElement assignPopup;
    @FindBy(xpath="id(\"AssignToolbarPopup\")/div/div/div[3]/button[3]")
    private WebElement assignTaskBtn;

    //Change Owner Dialog
    @FindBy(id="root.task.workflow.changeOwner.dialog-login")
    private WebElement textNewOwner;
    @FindBy(id="com-audatex-breservices-ui-components-workflow-ChangeTaskOwnerWorkflowActionComponent")
    private WebElement changeOwnerDialog;
    @FindBy(id="root.task.workflow.changeOwner.dialog-membersearchfield-advancedSearchButton")
    private WebElement changeOwnerAdvancedSearchBtn;
    @FindBy(id="root.task.workflow.changeOwner.dialog-membersearchfield-advancedSearchWindow-name_or_loginId")
    private WebElement newOwnerNameField;
    @FindBy(xpath = "id(\"ChangeTaskOwnerWorkflowActionPopup\")/div/div/div[3]/button[3]")
    private WebElement changeOwnerMembersearchBtn;
    @FindBy(xpath="id(\"com-audatex-breservices-ui-components-workflow-ChangeTaskOwnerWorkflowActionComponent\")/div/div/div[3]/button[3]")
    private WebElement changeOwnerBtn;

    //Merge Dialog
    @FindBy(id="com-audatex-breservices-ui-components-workflow-MergeTaskWorkflowActionComponent")
    private WebElement mergeTaskDialog;
    @FindBy(id="root.task.workflow.mergeTask.dialog-comment")
    private WebElement textMergeComment;
    @FindBy(css="#com-audatex-breservices-ui-components-workflow-MergeTaskWorkflowActionComponent div div div.modal-footer button:nth-of-type(3)")
    private WebElement btnMergeInDialog;

    //Reopen Dialog
    @FindBy(id="com-audatex-breservices-ui-components-workflow-ReopenTaskWorkflowActionComponent")
    private WebElement reopenTaskDialog;
    @FindBy(id="root.task.workflow.reopenTask.dialog-comment")
    private WebElement textReopenComment;
    @FindBy(css="#com-audatex-breservices-ui-components-workflow-ReopenTaskWorkflowActionComponent div div div.modal-footer button:nth-of-type(3)")
    private WebElement btnReopenConfirm;

    //Copy Dialog
    @FindBy(id="root.copyIntoNewCaseComponent.dialog-claimNumber")
    private WebElement textFieldClaimNumber;

    private static final By ID_INCLUDED_ADMINISTRATIVE_DATA = By.id("root.copyIntoNewCaseComponent.dialog-includeAdministrativeData");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includeAdministrativeData\"]")
    private WebElement checkboxIncludeAdministartiveData;

    private static final By ID_INCLUDED_DAMAGE_DESCRIPTION = By.id("root.copyIntoNewCaseComponent.dialog-includeDamageDescription");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includeDamageDescription\"]")
    private WebElement checkboxIncludeDamageDescription;

    private static final By ID_INCLUDED_POLICY_DATA = By.id("root.copyIntoNewCaseComponent.dialog-includePolicyData");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includePolicyData\"]")
    private WebElement checkboxIncludePolicyData;

    private static final By ID_INCLUDED_VEHICLE_DATA = By.id("root.copyIntoNewCaseComponent.dialog-includeVehicleData");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includeVehicleData\"]")
    private WebElement checkboxIncludeVehicleData;

    private static final By ID_INCLUDED_DAMAGE_CAPTURE = By.id("root.copyIntoNewCaseComponent.dialog-includeDamageCapture");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includeDamageCapture\"]")
    private WebElement checkboxIncludeDamageCapture;

    private static final By ID_INCLUDED_ALL_CALCULATIONS = By.id("root.copyIntoNewCaseComponent.dialog-includeAllCalculations");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includeAllCalculations\"]")
    private WebElement checkboxIncludeAllCalculations;

    private static final By ID_INCLUDED_ATTACHMENTS = By.id("root.copyIntoNewCaseComponent.dialog-includeAttachments");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includeAttachments\"]")
    private WebElement checkboxIncludeAttachments;

    private static final By ID_INCLUDED_LAST_CALCULATIONS = By.id("root.copyIntoNewCaseComponent.dialog-includeLastCalculation");
    @FindBy(css="label[for=\"root.copyIntoNewCaseComponent.dialog-includeLastCalculation\"]")
    private WebElement checkboxIncludeLastCalculation;

    @FindBy(id="com-audatex-breservices-ui-components-workflow-CopyIntoNewCaseComponent")
    private WebElement copyCaseDialog;
    @FindBy(css="#com-audatex-breservices-ui-components-workflow-CopyIntoNewCaseComponent div div div.modal-footer button:nth-of-type(3)")
    private WebElement btnConfirmCopy;
    private static final String GET_ATTRIBUTE_DATA_CHECKED = "data-checked";

    @FindBy(id="com-audatex-breservices-ui-components-workflow-ChangeBusinessStatusWorkflowActionComponent-value5")
    private WebElement approveDialog;
    @FindBy(css="#com-audatex-breservices-ui-components-workflow-ChangeBusinessStatusWorkflowActionComponent-value5 div div div.modal-footer button:nth-of-type(2)")
    private WebElement btnApproveConfirm;
    @FindBy(css="#com-audatex-breservices-ui-components-workflow-ChangeBusinessStatusWorkflowActionComponent-value6 div div div.modal-footer button:nth-of-type(2)")
    private WebElement btnRejectConfirm;

    @FindBy(css="#com-audatex-breservices-ui-components-workflow-ChangeBusinessStatusWorkflowActionComponent-value23 div div div.modal-footer button:nth-of-type(2)")
    private WebElement btnSendEstimateConfirm;

    //New Comment
    @FindBy(id="NewCommentPopup")
    private WebElement newCommentDialog;
    @FindBy(id="root.caseComments.newCommentTextarea")
    private WebElement textNewComment;
    @FindBy(css="#NewCommentPopup div div div.modal-footer button:nth-of-type(3)")
    private WebElement btnNewCommentConfirm;




    public void enterCopyClaimNumber(String textNewClaimNumber){ this.sendKeys(textFieldClaimNumber, textNewClaimNumber); }

    public void clickCheckboxIncludeAdministartiveData(){ this.click(checkboxIncludeAdministartiveData); }

    public void clickCheckboxIncludeDamageDescription(){ this.click(checkboxIncludeDamageDescription); }

    public void clickCheckboxIncludePolicyData(){ this.click(checkboxIncludePolicyData); }

    public void clickCheckboxIncludeVehicleData(){ this.click(checkboxIncludeVehicleData); }

    public void clickCheckboxIncludeDamageCapture(){ this.click(checkboxIncludeDamageCapture); }

    public void clickCheckboxIncludeAllCalculations(){ this.click(checkboxIncludeAllCalculations); }

    public void clickCheckboxIncludeAttachments(){ this.click(checkboxIncludeAttachments); }

    public void clickCheckboxIncludeLastCalculation(){ this.click(checkboxIncludeLastCalculation); }


    //Toolbar actions

    public void clickToolBar(){ this.click(btnToolBar); }

    public void clickPrintPDF(){
        this.click(btnPrintPDF);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(printPdfPopup, classString, "in"));
    }

    public void clickSend(){
        this.click(btnSend);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(sendTaskDialog, classString, "in"));
    }

    public void clickClose(){
        this.click(btnClose);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(closeTaskDialog, classString, "in"));
    }

    public void clickApprove(){
        this.click(btnApprove);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(approveDialog, classString, "in"));
    }

    public void clickReject(){ this.click(btnReject); }

    public void clickSendMessage(){ this.click(btnSendMessage); }

    public void clickChangeOwner(){
        this.click(btnChangeOwner);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(changeOwnerDialog, classString, "in"));
    }

    public void clickCopy(){
        this.click(btnCopy);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(copyCaseDialog, classString, "in"));
    }

    public void clickMergeTask(){
        this.click(btnMergeTask);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(mergeTaskDialog, classString, "in"));
    }

    public void clickReopen(){
        this.click(btnReopen);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(reopenTaskDialog, classString, "in"));
    }

    public void clickNewComment(){
        this.click(btnNewComment);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(newCommentDialog, classString, "in"));
    }

    public void clickSendEstimate(){ this.click(btnSendEstimate); }

    public void clickSendEstimateBtnInDialog(){
        this.click(btnSendEstimateConfirm);
        waitForElementInvisible(LOADING_CIRCLE);
        waitForElementInvisible(NOTIFICATION_POPUP);
    }

    public void clickAssign(){
        this.click(btnAssign);
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.attributeContains(assignPopup, classString, "in"));
    }

    //Approve action
    public void clickApproveBtnInDialog(){
        this.click(btnApproveConfirm);
        waitForElementInvisible(LOADING_CIRCLE);
    }
    //Reject action
    public void clickRejectBtnInDialog(){
        this.click(btnRejectConfirm);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    //Print PDF actions

    public void selectPrintFormatByDropDownMenu(int value) {
        this.click(selectPrintFormat);
        webDriver.findElement(By.cssSelector("[id*=\"react-select-root.printPdf.printFormat--option-"+value+"\"]")).click();
    }

    public String getFileName(){
        return this.getAttributeValue(By.id(ID_PDF_FILE_NAME), GET_ATTRIBUTE_VALUE);
    }

    public void setFileName(String fileName){
        this.sendKeys(textboxFileName, fileName);
    }

    public void clickGeneratePDF(){ this.click(btnGeneratePDF); }

    public void selectStoreReport(int value){
        this.click(selectStoreReport);
        webDriver.findElement(By.cssSelector("[id*=\"react-select-root.printPdf.storereport--option-"+value+"\"]")).click();
    }


    //Close task actions

    public void enterCloseTaskComment(String text){ this.sendKeys(textCloseTaskComment, text);}

    public void clickCloseTask(){
        this.click(btnCloseTask);
        waitForElementInvisible(NOTIFICATION_POPUP);
    }


    //Send task actions

    public void sendTaskAdvancedSearch(String receiver) {
        // Open advanced search dialog
        this.click(sendTaskAdvancedSearchBtn);
        // input receiver in search field
        this.sendKeys(receiverNameField, receiver);
        // search
        this.click(sendTaskMemberSearchBtn);
        // scroll to search result
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(searchResultLoginId));
        // note: Actions.MoveToElement is not working in Firefox so using javascript as workaround
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", searchResultLoginId);
        // choose the receiver in search result
        this.click(searchResultLoginId);
        new WebDriverWait(webDriver, 3).until(ExpectedConditions.attributeContains(receiverNameSearchResult, GET_ATTRIBUTE_VALUE, receiver));
    }

    public void clickSendTaskBtn() {
        // send task via send task dialog
        this.click(sendTaskBtn);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickAssignTaskBtn() {
        // send task via send task dialog
        this.click(assignTaskBtn);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    //Change owner actions

    public void enterNewOwner(String newOwner){ this.sendKeys(textNewOwner, newOwner);}

    public void changeOwnerAdvancedSearch(String newOwner) {
        // Open advanced search dialog
        this.click(changeOwnerAdvancedSearchBtn);
        // input new owner in search field
        this.sendKeys(newOwnerNameField, newOwner);
        // search
        this.click(changeOwnerMembersearchBtn);
        // scroll to search result
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(searchResultLoginId));
        // note: Actions.MoveToElement is not working in Firefox so using javascript as workaround
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", searchResultLoginId);
        // choose the receiver in search result
        this.click(searchResultLoginId);
        // Waiting for the new owner is inputted
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.attributeToBe(textNewOwner, GET_ATTRIBUTE_VALUE , newOwner));
    }


    public void clickChangeOwnerBtn() {
        // change owner via change owner dialog
        this.click(changeOwnerBtn);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    //Merge task actions

    public void enterMergeComment(String text){ this.sendKeys(textMergeComment, text); }

    public void clickMergeBtnInDialog(){
        this.click(btnMergeInDialog);
        waitForElementInvisible(NOTIFICATION_POPUP);
    }

    //Reopen task dialog

    public void enterReopenComment(String text){ this.sendKeys(textReopenComment, text);}

    public void clickReopenBtnInDialog(){
        this.click(btnReopenConfirm);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    //Copy task actions

    public void clickBtnConfirmCopy(){
        this.click(btnConfirmCopy);
        waitForElementInvisible(LOADING_CIRCLE);
    }


    public boolean isCheckedIncludeAdministartiveData(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_ADMINISTRATIVE_DATA, GET_ATTRIBUTE_DATA_CHECKED));
    }

    public boolean isCheckedIncludeDamageDescription(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_DAMAGE_DESCRIPTION, GET_ATTRIBUTE_DATA_CHECKED));
    }

    public boolean isCheckedIncludePolicyData(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_POLICY_DATA, GET_ATTRIBUTE_DATA_CHECKED));
    }

    public boolean isCheckedIncludeVehicleData(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_VEHICLE_DATA, GET_ATTRIBUTE_DATA_CHECKED));
    }

    public boolean isCheckedIncludeDamageCapture(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_DAMAGE_CAPTURE, GET_ATTRIBUTE_DATA_CHECKED));
    }

    public boolean isCheckedIncludeAllCalculations(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_ALL_CALCULATIONS, GET_ATTRIBUTE_DATA_CHECKED));
    }

    public boolean isCheckedIncludeAttachments(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_ATTACHMENTS, GET_ATTRIBUTE_DATA_CHECKED));
    }

    public boolean isCheckedIncludeLastCalculation(){
        return Boolean.valueOf(this.getAttributeValue(ID_INCLUDED_LAST_CALCULATIONS, GET_ATTRIBUTE_DATA_CHECKED));
    }

    //New Comment actions

    public void enterNewComment(String text){ this.sendKeys(textNewComment, text);}

    public void clickSaveInNewCommentDialog(){ this.click(btnNewCommentConfirm); }

}
