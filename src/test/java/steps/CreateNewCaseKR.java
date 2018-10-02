package steps;

import cases.TestBase;
import org.openqa.selenium.By;
import pageobjects.PreIntakePO;
import pageobjects.processstep.AttachmentsRepairerPO;
import pageobjects.processstep.processstep.ProcessStepKRPO;
import pageobjects.processstep.ReportsPO;
import pageobjects.processstep.claimdetails.ClaimDetailsKRPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import utils.UtilitiesManager;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class CreateNewCaseKR extends TestBase{
    private WorkListGridOpenPO workListGridOpenPO;
    private PreIntakePO preIntakePO;
    private ProcessStepKRPO processStepKRPO;
    private AttachmentsRepairerPO attachmentPO;
    private ReportsPO reportsPO;

    public CreateNewCaseKR() {
        workListGridOpenPO.setWebDriver(getDriver());
        preIntakePO.setWebDriver(getDriver());
        processStepKRPO.setWebDriver(getDriver());
        attachmentPO.setWebDriver(getDriver());
        reportsPO.setWebDriver(getDriver());
    }

    public void createNewCase(String plateNumber) {
        //Create a new claim
        //Work List grid Open
        workListGridOpenPO.clickNewClaimButton();

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.enterPlateNumberKRTextbox(plateNumber);
        preIntakePO.clickCreateNewCaseButton();
        fluentWait(By.id(ClaimDetailsKRPO.ID_CLAIM_NUMBER));
    }
}
