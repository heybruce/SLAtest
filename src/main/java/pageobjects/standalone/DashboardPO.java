package pageobjects.standalone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.processstep.processstep.ProcessStepKRPO;

public class DashboardPO extends ProcessStepKRPO {

    @FindBy(xpath = "id(\"DashBoard\")/div[2]/div/div/div[1]")
    private WebElement createCase;
    @FindBy(xpath = "id(\"DashBoard\")/div[2]/div/div/div[2]")
    private WebElement audanet;
    @FindBy(xpath = "id(\"DashBoard\")/div[2]/div/div/div[3]")
    private WebElement partsInfoChecker;
    @FindBy(xpath = "id(\"DashBoard\")/div[2]/div/div/div[4]")
    private WebElement csa;
    @FindBy(id = "newCaseBtn")
    private WebElement newClaimButton;

    public DashboardPO() {
        super();
    }

    public DashboardPO(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickCreateCase() { this.click(createCase); }

    public void clickNewClaimButton() {
        this.click(newClaimButton);
    }
}
