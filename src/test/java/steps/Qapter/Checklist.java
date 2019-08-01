package steps.Qapter;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobjects.processstep.DamageCapturingPO;
import steps.DamageCapturing;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class Checklist extends TestBase {
    private DamageCapturingPO damageCapturingPO = new DamageCapturingPO();

    public Checklist() {
        damageCapturingPO.setWebDriver(getDriver());
    }

    public void modelOptionTabInChecklist(){
        damageCapturingPO.navigationChecklist();
        damageCapturingPO.clickChecklistModelOptions();
    }

    public int consistentAfterQapterResumed(String zone1, String position1, String zone2, String position2){
        DamageCapturing damageCapturing = new DamageCapturing();
        damageCapturing.addStandardPartToRepair(zone1, position1);
        damageCapturing.addStandardPartToHollowCavitySealing(zone2, position2);
        damageCapturingPO.navigationChecklist();
        int checklistPartsNumber = damageCapturingPO.getChecklistNumber();
        return checklistPartsNumber;
    }

    public void addPositionInChecklist(String SPGuideNumber){
        damageCapturingPO.navigationChecklist();
        damageCapturingPO.clickChecklistNewPosition();
        damageCapturingPO.inputSPGuideNumber(SPGuideNumber);
        damageCapturingPO.clickRPReplaceWithOEMPart();
        damageCapturingPO.waitForQapterLoading();
        damageCapturingPO.clickSPAddAnotherPosition();
        Assert.assertTrue(isElementPresent(damageCapturingPO.ID_ADD_POSITION_MAIN_SECTION));
        damageCapturingPO.clickNonStandardTab();
        damageCapturingPO.clickNSPRepair();
        damageCapturingPO.inputNSPDescription("Automation Test");
        damageCapturingPO.inputNSPSparePart("NSP test");
        damageCapturingPO.inputNSPWorkUnits("20");
        damageCapturingPO.clickNSPAddPosition();
    }

    public void editPositionInChecklist(String zone, String position){
        DamageCapturing damageCapturing = new DamageCapturing();
        damageCapturing.addStandardPartToRepair(zone, position);
        damageCapturingPO.navigationChecklist();
        damageCapturingPO.clickChecklistItem(1);
        damageCapturingPO.clickRPRepair();
        new WebDriverWait(getDriver(), 5).until(
                ExpectedConditions.textToBe(By.cssSelector(damageCapturingPO.CSS_REPAIR_COST), "0"));
        damageCapturingPO.clickRPReplaceWithOEMPart();
        damageCapturingPO.waitForQapterLoading();
    }

    public void deletePositionInChecklist(String zone, String position){
        DamageCapturing damageCapturing = new DamageCapturing();
        damageCapturing.addStandardPartToRepair(zone, position);
        damageCapturingPO.navigationChecklist();
        damageCapturingPO.clickChecklistCheckbox(0);
        damageCapturingPO.clickDeletePositionInChecklist();
    }
}
