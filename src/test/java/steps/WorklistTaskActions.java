package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import pageobjects.worklistgrid.WorkListGridClosedPO;
import pageobjects.worklistgrid.WorkListGridInboxPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class WorklistTaskActions extends TestBase {
    private WorkListGridOpenPO workListGridOpenPO;
    private WorkListGridInboxPO workListGridInboxPO;
    private WorkListGridClosedPO workListGridClosedPO;

    public WorklistTaskActions(){
        workListGridOpenPO.setWebDriver(getDriver());
        workListGridInboxPO.setWebDriver(getDriver());
        workListGridClosedPO.setWebDriver(getDriver());
    }

    public void changeOwnerTo(String displayName, String newOwner){
        workListGridOpenPO.clickActionBtnInRow(displayName);
        workListGridOpenPO.clickChangeOwnerInRow();
        workListGridOpenPO.changeOwnerAdvancedSearch(newOwner);
        workListGridOpenPO.clickBtnChangeOwnerInDialog();
    }

    public void closeTheTask(String displayName){
        workListGridOpenPO.clickActionBtnInRow(displayName);
        workListGridOpenPO.clickCloseClaimInRow();
        workListGridOpenPO.enterCloseTaskComment("Automation test - close task");
        workListGridOpenPO.clickBtnCloseInDialog();
    }

    public void reopenTheTask(String displayName){
        workListGridClosedPO.clickActionBtnInRow(displayName);
        workListGridClosedPO.clickReopenTasksInRow();
        workListGridClosedPO.enterReopenComment("Automation test - reopen task");
        workListGridClosedPO.clickBtnReopenInDialog();
    }

    public void mergeTheTask(String claimNumber){
        workListGridInboxPO.clickActionBtnInRow(claimNumber);
        workListGridInboxPO.clickMergeTasksInRow();
        workListGridInboxPO.enterMergeComment("Automation test - merge task");
        workListGridInboxPO.clickMergeBtnInDialog();
    }

    public void sendTaskWithLastCalculationAllAttachments(String claimNumber, String receiver){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickSendTaskInRow();
        workListGridOpenPO.sendTaskAdvancedSearch(receiver);
        workListGridOpenPO.clickRadioBtnOnlyCurrentCalculation();
        workListGridOpenPO.clickRadioBtnAllAttachments();
        workListGridOpenPO.clickBtnSendTaskInDialog();
    }

    public void sendTaskWithSelectedCalculationSelectedAttachments(String claimNumber, String receiver){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickSendTask();
        workListGridOpenPO.sendTaskAdvancedSearch(receiver);
        workListGridOpenPO.clickRadioBtnOnlySelectedCalculations();
        workListGridOpenPO.clickRadioBtnOnlySelectedAttachments();
        workListGridOpenPO.clickBtnSendTaskInDialog();
    }

    public void sendTask(String claimNumber, String receiver){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickSendTask();
        workListGridOpenPO.sendTaskAdvancedSearch(receiver);
        workListGridOpenPO.clickBtnSendTaskInDialog();
    }

    public void submitTaskWithAllAttachments(String claimNumber, String receiver){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickSubmitTaskInRow();
        workListGridOpenPO.sendTaskAdvancedSearch(receiver);
        workListGridOpenPO.clickRadioBtnAllAttachments();
        workListGridOpenPO.clickBtnSubmitTaskInDialog();
    }

    public void assignTask(String claimNumber, String receiver){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickAssignTaskInRow();
        workListGridOpenPO.sendTaskAdvancedSearch(receiver);
        workListGridOpenPO.clickBtnAssignTaskInDialog();
    }

    public void sendEstimate(String claimNumber){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickSendEstimateInRow();
        workListGridOpenPO.clickBtnSendEstimateInDialog();
    }

    public void rejectEstimate(String claimNumber){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickRejectEstimateInRow();
        workListGridOpenPO.clickBtnRejectEstimateInDialog();
    }

    public void acceptEstimate(String claimNumber){
        workListGridOpenPO.clickActionBtnInRow(claimNumber);
        workListGridOpenPO.clickAcceptEstimateInRow();
        workListGridOpenPO.clickBtnApproveEstimateInDialog();
    }
}
