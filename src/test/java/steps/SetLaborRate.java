package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import jdk.nashorn.internal.runtime.logging.Loggable;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.processstep.LaborRatesPO;
import utils.UtilitiesManager;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class SetLaborRate extends TestBase {
    private LaborRatesPO laborRatesPO;

    public SetLaborRate() {
        laborRatesPO.setWebDriver(getDriver());
    }

//    public void SelectPartnership(){
//        laborRatesPO.selectPartnerShipByValueDropdown("1");
//    }
    public void SelectPartnership(){
        laborRatesPO.selectPartnerShipByInputValue("Manufacturer");
        new WebDriverWait(getDriver(), 10).until(
                ExpectedConditions.attributeToBeNotEmpty(getDriver().findElement(By.id(laborRatesPO.ID_LABOR_RATE1)),laborRatesPO.GET_ATTRIBUTE_VALUE));
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
