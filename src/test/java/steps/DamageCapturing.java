package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import pageobjects.processstep.DamageCapturingPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class DamageCapturing extends TestBase {
    private DamageCapturingPO damageCapturingPO;

    public DamageCapturing() {
        damageCapturingPO.setWebDriver(getDriver());
    }

    public void launchQapter(){
        damageCapturingPO.clickQapterIcon();
    }

    public void addStandardPartToRepair(String bodyWorkElementId, String damagePositionElementId) {
        damageCapturingPO.clickZone(bodyWorkElementId);
        damageCapturingPO.clickPosition(damagePositionElementId);
        if(damageCapturingPO.isSelectSidePopUp())
            damageCapturingPO.clickLeftSide();
        damageCapturingPO.clickRPRepair();
        damageCapturingPO.inputMutations("30");
        damageCapturingPO.clickContinue();
        goToVehicleView();
    }

    public void addStandardPartToReplaceWithOem(String bodyWorkElementId, String damagePositionElementId) {
        damageCapturingPO.clickZone(bodyWorkElementId);
        damageCapturingPO.clickPosition(damagePositionElementId);
        if(damageCapturingPO.isSelectSidePopUp())
            damageCapturingPO.clickLeftSide();
        damageCapturingPO.clickRPReplaceWithOEMPart();
        goToVehicleView();
    }

    public void addStandardPartToHollowCavitySealing(String bodyWorkElementId, String damagePositionElementId) {
        damageCapturingPO.clickZone(bodyWorkElementId);
        damageCapturingPO.clickPosition(damagePositionElementId);
        if(damageCapturingPO.isSelectSidePopUp())
            damageCapturingPO.clickLeftSide();
        damageCapturingPO.clickRPHollowCavitySealing();
        goToVehicleView();
    }

    public void addNonStandardPartReplaceWithOem() {
        damageCapturingPO.clickAddNewPosition();
        damageCapturingPO.clickNonStandardTab();
        damageCapturingPO.clickNSPReplacewithOEM();
        damageCapturingPO.inputNSPDescription("Automation Test");
        damageCapturingPO.inputNSPSparePart("NSP test");
        damageCapturingPO.inputNSPWorkUnits("20");
        damageCapturingPO.inputNSPAmount("1000");
        damageCapturingPO.clickNSPAddPosition();
        goToVehicleView();
    }

    public void addOldPictogramToReplaceWithOem(String function, String position) {
        damageCapturingPO.clickZone(function);
        damageCapturingPO.clickPosition(position);
        damageCapturingPO.clickRPReplaceWithOEMPart();
        goToVehicleView();
    }

    public void addPartsFromNewPictogramToSurfacePainting(String function, String position){
        damageCapturingPO.clickMoreViewToOpenPictogram();
        damageCapturingPO.clickZone(function);
        damageCapturingPO.clickPosition(position);
        damageCapturingPO.clickRPSurfPainting();
        goToVehicleView();
    }

    public void addPartsFromNewPictogramToNewPartPainting(String function, String position){
        damageCapturingPO.clickMoreViewToOpenPictogram();
        damageCapturingPO.clickZone(function);
        damageCapturingPO.clickPosition(position);
        damageCapturingPO.clickRPNewPartPainting();
        goToVehicleView();
    }

    public void goToVehicleView(){ damageCapturingPO.navigationVehicle(); }

    public void waitForCalcPreviewGenerated(){
        damageCapturingPO.navigationCalcPreview();
        String editedOutput = damageCapturingPO.getCalcPreviewContent();
        Assert.assertTrue(editedOutput.contains("AUDATEX SYSTEM"));
    }

    public boolean isChecklistNumberMatched(int expectedNumber) {
        damageCapturingPO.navigationChecklist();
        int items = damageCapturingPO.getChecklistNumber();
        if(expectedNumber==items)
            return true;
        else
            return false;
    }
}
