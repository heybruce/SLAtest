package cases.KR;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobjects.processstep.ReportsPO;
import pageobjects.processstep.processstep.ProcessStepKRPO;
import steps.*;
import utils.UtilitiesManager;

import java.io.File;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static utils.webdrivers.WebDriverFactory.getDriver;

public class ReportsTest extends TestBase {
    private ProcessStepKRPO processStepKRPO;
    private ReportsPO reportsPO;

    @BeforeClass
    @Parameters(value = {"dataFile", "vehicleElement"})
    public void setup(String dataFile, String vehicleElement) {
        vehicleElementData = UtilitiesManager.setPropertiesFile(vehicleElement);
        testData = UtilitiesManager.setPropertiesFile(dataFile);
    }

    @BeforeMethod
    public void methodSetup() {
        processStepKRPO.setWebDriver(getDriver());
        reportsPO.setWebDriver(getDriver());
    }

    @Test(description = "Calculation output")
    public void calculationOutput() {

    }
}