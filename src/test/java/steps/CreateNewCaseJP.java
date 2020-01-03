package steps;

import cases.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PreIntakePO;
import pageobjects.processstep.AttachmentsRepairerPO;
import pageobjects.processstep.DamageCapturingPO;
import pageobjects.processstep.ReportsPO;
import pageobjects.processstep.claimdetails.ClaimDetailsJPPO;
import pageobjects.processstep.claimdetails.ClaimDetailsPO;
import pageobjects.processstep.processstep.ProcessStepJPPO;
import pageobjects.worklistgrid.WorkListGridOpenPO;
import utils.UtilitiesManager;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class CreateNewCaseJP extends TestBase {
    private WorkListGridOpenPO workListGridOpenPO;
    private PreIntakePO preIntakePO;
    private ProcessStepJPPO processstepJPPO;
    private DamageCapturingPO damageCapturingPO;
    private AttachmentsRepairerPO attachmentPO;
    private ClaimDetailsPO claimDetails;
    private ReportsPO reportsPO;
    private WebDriverWait wait;

    public CreateNewCaseJP() {
        workListGridOpenPO.setWebDriver(getDriver());
        preIntakePO.setWebDriver(getDriver());
        processstepJPPO.setWebDriver(getDriver());
        damageCapturingPO.setWebDriver(getDriver());
        attachmentPO.setWebDriver(getDriver());
        claimDetails.setWebDriver(getDriver());
        reportsPO.setWebDriver(getDriver());
        wait = new WebDriverWait(getDriver(), 10);
    }

    public void createNewCase(){
        //Work List grid Open
        workListGridOpenPO.clickOpenTab();
        workListGridOpenPO.clickNewClaimButton();

        //Pre Intake page
        String claimNumber = Long.toString(UtilitiesManager.getCurrentUnixTime());
        preIntakePO.enterClaimNumberTextbox(claimNumber);
        preIntakePO.clickCreateNewCaseButton();
        fluentWait(By.id(ClaimDetailsJPPO.ID_CLAIM_NUMBER));
    }

    public void createNewCaseWithVehicleIdentificationBySearchTree(String vehicle) {
        //Create a new case
        createNewCase();

        SelectVehicle selectVehicle = new SelectVehicle();
        selectVehicle.SearchBySearchTree(vehicle);
    }

    public void createNewCaseWithCalculation(String vehicle){
        //Create a new case
        createNewCaseWithVehicleIdentificationBySearchTree(vehicle);

        //Switch to Labour Rate page
        processstepJPPO.clickLabourRatesTab();

        //Select labour rate
        SetLaborRate setLaborRate = new SetLaborRate();
        setLaborRate.SelectPartnership();

        //Switch to Damage Capturing page
        processstepJPPO.clickDamageCaptureTab();
        damageCapturingPO.switchToQapterIframe();
        //Damage Capturing - add standard part
        DamageCapturing damageCapturing = new DamageCapturing();
        damageCapturing.addStandardPartToReplaceWithOem(
                vehicleElementData.getString("benzE_zone_frontOuter"), vehicleElementData.getString("benzE_position_0471_Bonnet"));
        damageCapturing.waitForCalcPreviewGenerated();

        //Switch to Reports page
        getDriver().switchTo().defaultContent();
        processstepJPPO.clickCalculationsTab();
        //Do calculation
        reportsPO.clickCalculateAlternative();
        //Display all columns for calculation list
        CalculationList calculationList = new CalculationList();
        calculationList.displayAllContentInReportCalculationList();
    }
}
