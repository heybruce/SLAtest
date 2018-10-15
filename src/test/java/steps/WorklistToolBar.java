package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import pageobjects.worklistgrid.WorkListGridClosedPO;
import pageobjects.worklistgrid.WorkListGridInboxPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class WorklistToolBar extends TestBase {
    private WorkListGridOpenPO workListGridOpenPO;
    private WorkListGridInboxPO workListGridInboxPO;
    private WorkListGridClosedPO workListGridClosedPO;

    public WorklistToolBar(){
        workListGridOpenPO.setWebDriver(getDriver());
        workListGridInboxPO.setWebDriver(getDriver());
        workListGridClosedPO.setWebDriver(getDriver());
    }

    public void sendTaskWithLastCalculationAllAttachments(String claimNumber, String receiver){
        workListGridOpenPO.clickClaimCheckbox(claimNumber);
        workListGridOpenPO.clickSendTask();
        workListGridOpenPO.sendTaskAdvancedSearch(receiver);
        workListGridOpenPO.clickRadioBtnOnlyCurrentCalculation();
        workListGridOpenPO.clickRadioBtnAllAttachments();
        workListGridOpenPO.clickBtnSendTaskInDialog();
    }

    public void sendTaskWithSelectedCalculationSelectedAttachments(String claimNumber, String receiver){
        workListGridOpenPO.clickClaimCheckbox(claimNumber);
        workListGridOpenPO.clickSendTask();
        workListGridOpenPO.sendTaskAdvancedSearch(receiver);
        workListGridOpenPO.clickRadioBtnOnlySelectedCalculations();
        workListGridOpenPO.clickRadioBtnOnlySelectedAttachments();
        workListGridOpenPO.clickBtnSendTaskInDialog();
    }

    public void changeOwnerTo(String claimNumber, String newOwner){
        workListGridOpenPO.clickClaimCheckbox(claimNumber);
        workListGridOpenPO.clickChangeOwner();
        workListGridOpenPO.changeOwnerAdvancedSearch(newOwner);
        workListGridOpenPO.clickBtnChangeOwnerInDialog();
    }

    public void closeTheTask(String claimNumber){
        workListGridOpenPO.clickClaimCheckbox(claimNumber);
        workListGridOpenPO.clickCloseTask();
        workListGridOpenPO.enterCloseTaskComment("Automation test - close task");
        workListGridOpenPO.clickBtnCloseInDialog();
    }

    public void mergeTheTask(String claimNumber){
        workListGridInboxPO.clickClaimCheckbox(claimNumber);
        workListGridInboxPO.clickMergeTask();
        workListGridInboxPO.enterMergeComment("Automation test - merge task");
        workListGridInboxPO.clickMergeBtnInDialog();
    }

    public void reopenTheTask(String claimNumber){
        workListGridClosedPO.clickClaimCheckbox(claimNumber);
        workListGridClosedPO.clickBtnReopenTasks();
        workListGridClosedPO.enterReopenComment("Automation test - reopen task");
        workListGridClosedPO.clickBtnReopenInDialog();
    }
}
