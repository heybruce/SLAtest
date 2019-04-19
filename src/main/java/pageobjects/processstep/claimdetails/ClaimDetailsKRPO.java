package pageobjects.processstep.claimdetails;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClaimDetailsKRPO extends ClaimDetailsPO {

    @FindBy(id = "root.task.IBOSSocket.searchButton")
    private WebElement ibosSearch;
    @FindBy(id = ID_PLATE_NUMBER)
    private WebElement plateNumber;
    @FindBy(id = "root.task.basicClaimData.claimDescriptionVehicle.damageCause")
    private WebElement claimType;
    @FindBy(id = "react-select-root.task.basicClaimData.policyData.insuranceType--value")
    private WebElement policyCoverageType;

    //Insurer Details
    @FindBy(id = "root.task.basicClaimData.claimDescriptionVehicle.liabilityLimitInPercent")
    private WebElement liabilityLimitInPercentInsurer;
    @FindBy(id = "root.task.basicClaimData.policyData.insuranceCompany.companyName")
    private WebElement companyNameInsurer;
    @FindBy(id = "root.task.basicClaimData.policyData.insuranceCompany.organizationName")
    private WebElement insuranceCompanyNameInsurer;
    @FindBy(id = "root.task.caseMemberByRole.claimAssistant.communicationDetailList.phone1")
    private WebElement phoneInsurer;
    @FindBy(id = "root.task.basicClaimData.policyData.insuranceCompany.address.street")
    private WebElement addressInsurer;

    //Repairer Details
    @FindBy(id = "root.task.basicClaimData.repairDetail.repairer.companyName")
    private WebElement companyNameRepairer;
    @FindBy(id = "root.task.basicClaimData.repairDetail.repairer.organizationName")
    private WebElement insuranceCompanyNameRepairer;
    @FindBy(id = "root.task.caseMemberByRole.nonPartnerRepairer.firstName")
    private WebElement firstNameRepairer;
    @FindBy(id = "root.task.caseMemberByRole.nonPartnerRepairer.communicationDetailList.phone1")
    private WebElement phoneRepairer;
    @FindBy(id = "root.task.basicClaimData.repairDetail.repairer.address.street")
    private WebElement addressRepairer;
    @FindBy(id = ID_TAX_NUMBER)
    private WebElement taxNumber;

    //IBOS Case Search Dialog
    @FindBy(id = "CaseSearchPopup")
    private WebElement caseSearchDialog;

    public ClaimDetailsKRPO() {
        super();
    }

    public ClaimDetailsKRPO(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickSearchIBOSCase() {
        this.click(ibosSearch);
        // Waiting for IBOS search dialog visible
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.attributeToBe(this.caseSearchDialog, "aria-hidden", "false"));
    }

}