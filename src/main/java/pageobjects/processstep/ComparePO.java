package pageobjects.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.util.List;

public class ComparePO extends PageObject {
    private String checkBoxPrefix = "grid-row-";
    private static final String ID_MODIFICATION_ASIDE = "ModificationAside";

    //List
    @FindBy(name = "grandTotalWithTax")
    private List<WebElement> grandTotalWVat;
    @FindBy(name = "FCRepTot")
    private List<WebElement> repairTotal;
    @FindBy(name = "FCPartTot")
    private List<WebElement> partsTotal;
    @FindBy(name = "FCLaborTot")
    private List<WebElement> labourTotal;
    @FindBy(name = "FCPaintTotOverAll")
    private List<WebElement> paintTotal;

    @FindBy(id="root.task.newModifications.comparison")
    private WebElement btnCompare;

    @FindBy(xpath = "id(\"newmodifications-legend\")/a[1]")
    private WebElement showResultLink;
    @FindBy(css=".calculations-to-compare")
    private WebElement calculationToCompare;

    // Compare result
    @FindBy(id="ConfigurableTabs-tab-Summary Changes")
    private WebElement summaryTab;
    @FindBy(xpath = ".//td[@class=\"numberColumn differsRecord\"]")
    private List<WebElement> differsRecord;

    //Customize
    @FindBy(id="table-customize-header-container-BRE-Compare-root-task-newModifications-calculationList")
    private WebElement btnCustomizeInCompare;
    @FindBy(css=".table-customize-component-row")
    private List<WebElement> listOfCustomizeCheckedbox;
    @FindBy(css=".table-customize-component-save")
    private WebElement btnApplyInCompare;

    public ComparePO() {
        super();
    }

    public ComparePO(WebDriver webDriver) {
        super(webDriver);
    }


    public void chooseCalculation(int row) {
        WebElement checkbox = new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label[for=\"" + checkBoxPrefix + row + "\"]")));
        this.click(checkbox);
    }


    public void clickCompareButton(){
        this.click(btnCompare);
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(showResultLink));
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(calculationToCompare));
    }


    public String getGrandTotalWithVat(int row) {
        return grandTotalWVat.get(row).findElement(By.className("text")).getText();
    }


    public String getRepTotal(int row) {
        return repairTotal.get(row).findElement(By.className("text")).getText();
    }


    public String getPartsTotal(int row) {
        return partsTotal.get(row).findElement(By.className("text")).getText();
    }


    public String getLaborTotal(int row) {
        return labourTotal.get(row).findElement(By.className("text")).getText();
    }


    public String getPaintTotal(int row) {
        return paintTotal.get(row).findElement(By.className("text")).getText();
    }


    public void showCompareResult(){
        this.click(showResultLink);
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='"+ID_MODIFICATION_ASIDE+"'][contains(@style, 'display: block')]")));
    }


    public boolean isValueExistingInDiffersRecord(String value) {
        for (WebElement e : differsRecord) {
            if (e.getText().equals(value)) {
                return true;
            }
        }
        return false;
    }


    public void clickCustomizeBtn(){ this.click(btnCustomizeInCompare); }


    public void checkAllCustomizeCheckedbox(){
        for(WebElement compareCustomize: listOfCustomizeCheckedbox) {
            if(!compareCustomize.getAttribute("class").contains("z-selected")){
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", compareCustomize);
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", compareCustomize);
            }
        }
    }


    public void clickBtnApply(){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", btnApplyInCompare);
    }
}
