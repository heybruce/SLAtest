package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import pageobjects.processstep.ToolBarPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class ToolBar extends TestBase{
    private ToolBarPO toolBarPO;

    public ToolBar(){
        toolBarPO.setWebDriver(getDriver());
    }

    public void printPdfReport(int templateNum, String textFileName, boolean isStoreReport){
        toolBarPO.clickToolBar();
        toolBarPO.clickPrintPDF();
        toolBarPO.selectPrintFormatByDropDownMenu(templateNum-1);
        if(isStoreReport==true) {
            toolBarPO.selectStoreReport(1);
        }
        toolBarPO.setFileName(textFileName);
        toolBarPO.clickGeneratePDF();
    }

    public void printPdfReportOverwriteDefaultFileName(int templateNum, String textFileName, boolean isStoreReport){
        toolBarPO.clickToolBar();
        toolBarPO.clickPrintPDF();
        toolBarPO.selectPrintFormatByDropDownMenu(templateNum-1);
        toolBarPO.setFileName(textFileName);
        if(isStoreReport==true) {
            toolBarPO.selectStoreReport(1);
        }
        toolBarPO.clickGeneratePDF();
    }

    public void sendTaskTo(String receiver){
        toolBarPO.clickToolBar();
        toolBarPO.clickSend();
        toolBarPO.sendTaskAdvancedSearch(receiver);
        toolBarPO.clickSendTaskBtn();
    }

    public void changeOwnerTo(String newOwner){
        toolBarPO.clickToolBar();
        toolBarPO.clickChangeOwner();
        toolBarPO.changeOwnerAdvancedSearch(newOwner);
        toolBarPO.clickChangeOwnerBtn();
    }

    public void closeTheTask(){
        toolBarPO.clickToolBar();
        toolBarPO.clickClose();
        toolBarPO.enterCloseTaskComment("Automation test - Close Task");
        toolBarPO.clickCloseTask();
    }

    public void mergeTheTask(){
        toolBarPO.clickToolBar();
        toolBarPO.clickMergeTask();
        toolBarPO.enterMergeComment("Automation test - Merge Task");
        toolBarPO.clickMergeBtnInDialog();
    }

    public void reopenTheTask(){
        toolBarPO.clickToolBar();
        toolBarPO.clickReopen();
        toolBarPO.enterReopenComment("Automation test - Reopen Task");
        toolBarPO.clickReopenBtnInDialog();
    }

    public void approveTheTask(){
        toolBarPO.clickToolBar();
        toolBarPO.clickApprove();
        toolBarPO.clickApproveBtnInDialog();
    }

    public void rejectTheTask(){
        toolBarPO.clickToolBar();
        toolBarPO.clickReject();
        toolBarPO.clickRejectBtnInDialog();
    }

    public void addComment(){
        toolBarPO.clickToolBar();
        toolBarPO.clickNewComment();
        toolBarPO.enterNewComment("Automation test - Add comment");
        toolBarPO.clickSaveInNewCommentDialog();
    }

    public void sendEstimate(){
        toolBarPO.clickToolBar();
        toolBarPO.clickSendEstimate();
        toolBarPO.clickSendEstimateBtnInDialog();
    }

    public void assignTask(String receiver){
        toolBarPO.clickToolBar();
        toolBarPO.clickAssign();
        toolBarPO.sendTaskAdvancedSearch(receiver);
        toolBarPO.clickAssignTaskBtn();
    }
}