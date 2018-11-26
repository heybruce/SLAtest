package steps.Qapter;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.processstep.DamageCapturingPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class ZoneAndLayout extends TestBase {
    private DamageCapturingPO damageCapturingPO;
    private WebDriverWait wait;

    public ZoneAndLayout() {
        damageCapturingPO.setWebDriver(getDriver());
        wait = new WebDriverWait(getDriver(), 3);
    }

    public void openZone(String bodyWorkElementId){
        damageCapturingPO.clickZone(bodyWorkElementId);
    }

    public void fastCapturing(String zone, String damagePositionElementId){
        openZone(zone);
        damageCapturingPO.clickFastCapturing();
        damageCapturingPO.clickSelectRepairMethod();
        damageCapturingPO.clickFcReplaceWithOem();
        damageCapturingPO.clickFastCaptureNoSide();
        damageCapturingPO.clickPosition(damagePositionElementId);
        damageCapturingPO.clickDoneInFastCapturing();
        damageCapturingPO.navigationChecklist();
    }

    public void multipartSelection(String zone, String damagePositionElementId, String damagePosition){
        openZone(zone);
        damageCapturingPO.clickPosition(damagePositionElementId);
        damageCapturingPO.clickMultipartsPosition(damagePosition);
        damageCapturingPO.clickRPReplaceWithOEMPart();
        damageCapturingPO.navigationChecklist();
    }

    public void pictogramViews(String pictogramId){
        damageCapturingPO.clickMoreViewToOpenPictogram();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(pictogramId)));
    }

    public void pictogramViewsInZone(String zone){
        openZone(zone);
        damageCapturingPO.clickMoreViewToOpenPictogram();
        wait.until(ExpectedConditions.elementToBeClickable(damageCapturingPO.getMorePartsPictogram().get(0)));
    }

    public void openPictogramSheet(String function, String Position){
        damageCapturingPO.clickMoreViewToOpenPictogram();
        damageCapturingPO.clickZone(function);
        wait.until(ExpectedConditions.visibilityOfElementLocated(damageCapturingPO.ID_PICTOGRAM_SHEET));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Position)));
    }

    public void openZoneByPictogram(String function, String Position){
        damageCapturingPO.clickMoreViewToOpenPictogram();
        damageCapturingPO.clickZone(function);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(Position)));
    }

    public void addPartByPictogram(String function, String Position){
        damageCapturingPO.clickMoreViewToOpenPictogram();
        damageCapturingPO.clickZone(function);
        damageCapturingPO.clickPosition(Position);
        if(damageCapturingPO.isSelectSidePopUp())
            damageCapturingPO.clickLeftSide();
        damageCapturingPO.clickRPReplaceWithOEMPart();
        damageCapturingPO.navigationChecklist();
    }

}
