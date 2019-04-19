package pageobjects.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.PageObject;

public class CalculationOptionsJPPO extends PageObject {

    private static final By LOADING_CIRCLE = By.cssSelector(".loading-component");

    @FindBy(id="react-select-root.task.calculationOptions.manufTimePrintCode--value-item")
    private WebElement manufacturerTimePrintCode;
    @FindBy(id="react-select-root.task.calculationOptions.labourRates--value")
    private WebElement labourRates;
    @FindBy(id="react-select-root.task.calculationOptions.language--value")
    private WebElement language;
    @FindBy(id="react-select-root.task.calculationOptions.wuPriceCode--value")
    private WebElement wuPriceCode;
    @FindBy(id="react-select-root.task.calculationOptions.partPriceOption--value")
    private WebElement partPriceOption;
    @FindBy(id="react-select-root.task.calculationOptions.withVat--value")
    private WebElement vat;

    @FindBy(id="root.task.calculationOptions.calculate")
    private WebElement btnCalculate;

    public CalculationOptionsJPPO() {
        super();
    }

    public CalculationOptionsJPPO(WebDriver webDriver) {
        super(webDriver);
    }


    public void clickCalculate(){
        this.click(btnCalculate);
        waitForElementInvisible(LOADING_CIRCLE);
    }
}
