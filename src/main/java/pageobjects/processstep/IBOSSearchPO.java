package pageobjects.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageobjects.PageObject;

import java.util.HashMap;
import java.util.Map;

public class IBOSSearchPO extends PageObject {

    @FindBy(id = "react-select-root.task.IBOSSocket.companyList--value")
    private WebElement insuranceCompany;

    @FindBy(id = "root.task.IBOSSocket.plateNumber")
    private WebElement plateNumber;

    @FindBy(id = "root.task.IBOSSocket.taxId")
    private WebElement taxId;

    @FindBy(xpath = "id(\"CaseSearchPopup\")/div/div/div[3]/button[2]")
    private WebElement submitButton;

    @FindBy(xpath = "id(\"caseIbos-row0\")/td[1]")
    private WebElement firstSearchResult;

    public IBOSSearchPO() {
        super();
    }

    public IBOSSearchPO(WebDriver driver) {
        super(driver);
    }


    public void selectInsuranceCompany(String insuranceCompanyName){
        this.click(insuranceCompany);
        webDriver.findElement(By.cssSelector("[aria-label*=\""+insuranceCompanyName+"\"]")).click();
    }


    public void enterPlateNumber(String textPlateNumber){ this.sendKeys(plateNumber, textPlateNumber); }


    public void enterTaxId(String textTaxId){ this.sendKeys(taxId, textTaxId); }


    public void clickSearchButton(){ this.click(submitButton); }


    public Map<String, String> getTheFirstSearchResult(){
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(firstSearchResult));
        Map<String, String> result = new HashMap<>();
        result.put("id", this.firstSearchResult.findElement(By.xpath("id(\"caseIbos-row0\")/td[1]")).getText());
        result.put("WIP", this.firstSearchResult.findElement(By.xpath("id(\"caseIbos-row0\")/td[2]")).getText());
        result.put("contactMobile", this.firstSearchResult.findElement(By.xpath("id(\"caseIbos-row0\")/td[3]")).getText());
        result.put("contactPerson", this.firstSearchResult.findElement(By.xpath("id(\"caseIbos-row0\")/td[4]")).getText());
        result.put("coverageTypeCode", this.firstSearchResult.findElement(By.xpath("id(\"caseIbos-row0\")/td[5]")).getText());
        result.put("damagedObjectSerialNo", this.firstSearchResult.findElement(By.xpath("id(\"caseIbos-row0\")/td[6]")).getText());

        return result;
    }


    public void chooseTheFirstSearchResult(){
        this.click(firstSearchResult);
        this.click(submitButton);
    }
}
