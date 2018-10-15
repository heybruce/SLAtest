package steps;

import cases.TestBase;
import com.aventstack.extentreports.Status;
import pageobjects.processstep.ComparePO;
import pageobjects.processstep.ReportsPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class CalculationList extends TestBase {
    private ReportsPO reportsPO;
    private ComparePO comparePO;

    public CalculationList(){
        reportsPO.setWebDriver(getDriver());
        comparePO.setWebDriver(getDriver());
    }

    public void displayAllContentInReportCalculationList(){
        reportsPO.clickCustomizeBtn();
        reportsPO.checkAllCustomizeCheckedbox();
        reportsPO.clickBtnApply();
    }

    public void displayAllContentInCompareCalculationList(){
        comparePO.clickCustomizeBtn();
        comparePO.checkAllCustomizeCheckedbox();
        comparePO.clickBtnApply();
    }
}
