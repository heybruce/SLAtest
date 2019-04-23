package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import jdk.nashorn.internal.runtime.logging.Loggable;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import pageobjects.processstep.LaborRatesPO;
import utils.UtilitiesManager;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class SetLaborRate extends TestBase {
    private LaborRatesPO laborRatesPO;

    public SetLaborRate() {
        laborRatesPO.setWebDriver(getDriver());
    }

    public void SelectPartnership(){
        laborRatesPO.selectPartnerShipByInputValue("1");
    }

    public void addIDBC(String idbc, String value) {
        laborRatesPO.clickAddIdbcListButton();
        laborRatesPO.enterIdbcAmount(idbc, value);
        laborRatesPO.clickAddIdbcButton();
        try {
            laborRatesPO.confirmChangeRate();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
