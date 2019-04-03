package pageobjects.worklistgrid;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;
import pageobjects.processstep.claimdetails.ClaimDetailsKRPO;

import java.util.List;

public class WorkListGridPO extends PageObject{

    protected static final By LOADING_CIRCLE = By.cssSelector(".loading-component");
    public static final String NEW_CASE_BTN_ID = "newCaseBtn";
    private static final String ATTRIBUTE_ASC = "z-asc";
    private static final String ATTRIBUTE_DESC = "z-desc";
    public static final String ID_OPEN_TAB = "view-link-worklistgrid_open";
    public static final String ID_CLOSED_TAB = "view-link-worklistgrid_closed";

    @FindBy(id = NEW_CASE_BTN_ID)
    private WebElement newClaim;
    @FindBy(className = "logged-user-name")
    private WebElement loggedUsername;

    //Tabs
    @FindBy(id = "view-link-worklistgrid_copied")
    private WebElement copiedTab;
    @FindBy(id = "view-link-worklistgrid_open")
    private WebElement openTab;
    @FindBy(id = "view-link-worklistgrid_closed")
    private WebElement closedTab;
    @FindBy(id = "view-link-worklistgrid_custom_open")
    private WebElement customOpenTab;
    @FindBy(id = "view-link-worklistgrid_custom_sent")
    private WebElement sentTabSG;

    //Menu items on left
    @FindBy(id = "claimManager")
    private WebElement claimManager;
    @FindBy(id = "messagelist")
    private WebElement messages;
    @FindBy(id = "settings")
    private WebElement settings;
    @FindBy(id = "changepassword")
    private WebElement changePassword;
    @FindBy(id = "support")
    private WebElement support;
    @FindBy(id = "logout")
    private WebElement logout;
    @FindBy(id="worklist")
    private WebElement worklist;

    //Table header
    @FindBy(css= ".worklist-grid-component")
    private WebElement workListTable;
    @FindBy(id = "header-title-containercreationDate")
    private WebElement headerCreationDate;
    @FindBy(id = "#header-title-containerplateNumber")
    private WebElement headerPlateNumber;
    @FindBy(id = "#header-title-containerclaimNumber")
    private WebElement headerClaimNumber;
    @FindBy(id = "#header-title-containerlastEditedDateTime")
    private WebElement headerLastEditedDateTime;
    @FindBy(id = "#header-title-containerbusinessStatusKind")
    private WebElement headerBusinessStatusKind;
    @FindBy(id = "#header-title-containermanufacturerName")
    private WebElement headerManufacturer;
    @FindBy(id = "#header-title-containermodelName")
    private WebElement headerModel;
    @FindBy(id = "#header-title-containerresponsibleUserLoginId")
    private WebElement headerResponsibleUserLoginId;

    //Table content
    @FindBy(name = "plateNumber")
    private List<WebElement> plateNumber;
    @FindBy(name = "claimNumber")
    private List<WebElement> claimNumber;
    @FindBy(name = "creationDate")
    private List<WebElement> creationDate;
    @FindBy(name = "lastEditedDateTime")
    private List<WebElement> lastEditedDateTime;
    @FindBy(name = "businessStatusKind")
    private List<WebElement> businessStatusKind;
    @FindBy(name = "manufacturerName")
    private List<WebElement> manufacturerName;
    @FindBy(name = "modelName")
    private List<WebElement> modelName;
    @FindBy(name = "responsibleUserLoginId")
    private List<WebElement> responsibleUserLoginId;
    @FindBy(name = "displayName")
    private List<WebElement> displayName;

    //Action Component in each row
    @FindBy(css=".more-row-icon-wrapper")
    private List<WebElement> btnAction;

    protected static final By NOTIFICATION_POPUP = By.cssSelector(".notification-component");


    public WorkListGridPO() { super(); }

    public WorkListGridPO(WebDriver webDriver) {
        super(webDriver);
    }

    
    public String getLoggedUsername() { return this.getText(loggedUsername); }

    
    public void clickNewClaimButton() {
        this.click(newClaim);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickCustomOpenTab() {
        this.click(customOpenTab);
        waitForElementInvisible(LOADING_CIRCLE);
    }
    
    public void clickClaimManager() {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(claimManager));
        this.click(claimManager); }

    
    public void clickMessages() { this.click(messages); }

    
    public void clickSettings() { this.click(settings); }

    
    public void clickChangePassword() { this.click(changePassword); }

    
    public void clickSupport() { this.click(support); }

    
    public void clickLogout() { this.click(logout); }

    
    public void clickWorklist(){ this.click(worklist); }

    
    public void clickCopiedTab() {
        this.click(copiedTab);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    
    public void clickOpenTab() {
        this.click(openTab);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    
    public void clickOpenTabSG() {
        this.click(customOpenTab);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    
    public void clickSentTabSG() {
        this.click(sentTabSG);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    
    public void clickClosedTab() {
        this.click(closedTab);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    
    public void sortCreationDate(){
        //Get the class attribute of grand parent element of header-creationDate
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(headerCreationDate));
        String grandParentAttribute = headerCreationDate.findElement(By.xpath("../..")).getAttribute("class");
        //Check if the header-creationDate has never been sorted, click twice to sort by desc
        if (!grandParentAttribute.contains("z-asc") && !grandParentAttribute.contains("z-desc")) {
            this.click(headerCreationDate);
            waitForElementInvisible(LOADING_CIRCLE);
            this.click(headerCreationDate);
            waitForElementInvisible(LOADING_CIRCLE);
        } else if (grandParentAttribute.contains("z-asc")) {
            //Check if the header-creationDate has been sorted and is order by asc, click header to sort by desc
            this.click(headerCreationDate);
            waitForElementInvisible(LOADING_CIRCLE);
        }
    }

    
    public boolean isClaimNumberExist(String findClaimNumber){
        for(WebElement ele: claimNumber) {
            String getClaimNumber = ele.getText();
            if(getClaimNumber.trim().equals(findClaimNumber))
                return true;
        }
        return false;
    }

    
    public boolean isDisplayNameExist(String findDiaplayName){
        for(WebElement ele: displayName) {
            String getClaimNumber = ele.getText();
            if(getClaimNumber.trim().equals(findDiaplayName))
                return true;
        }
        return false;
    }

    
    public void openClaimByClaimNumber(String findClaimNumber){
        for(WebElement ele: claimNumber) {
            String getClaimNumber = ele.getText();
            if(getClaimNumber.trim().equals(findClaimNumber)) {
                new Actions(webDriver).doubleClick(ele).perform();
                this.waitForElementPresent(By.id(ClaimDetailsKRPO.ID_CLAIM_NUMBER));
                return;
            }
        }
    }

    
    public void openClaimByRow(int row){
        new Actions(webDriver).doubleClick(claimNumber.get(row)).perform();
        this.waitForElementPresent(By.id(ClaimDetailsKRPO.ID_CLAIM_NUMBER));
    }

    
    public int findRowOfTheClaimByClaimNumber(String findClaimNumber){
        int row = 0;
        for(WebElement ele: claimNumber) {
            String getClaimNumber = ele.getText();
            if(getClaimNumber.trim().contains(findClaimNumber)){
                return row;
            }else{
                row++;
            }
        }
        return row;
    }

    
    public int findRowOfTheClaimByBizStatus(String bizStatus){
        int row = 0;
        for(WebElement ele: businessStatusKind) {
            String getClaimNumber = ele.getText();
            if(getClaimNumber.trim().contains(bizStatus)){
                return row;
            }else{
                row++;
            }
        }
        if(row==businessStatusKind.size())
            row=-1;
        return row;
    }

    
    public int findRowOfTheClaimByDisplayName(String findDisplayName){
        int row = 0;
        for(WebElement ele: displayName) {
            String getClaimNumber = ele.getText();
            if(getClaimNumber.trim().contains(findDisplayName)){
                return row;
            }else{
                row++;
            }
        }
        return row;
    }

    private WebElement getCssOfClaimCheckbox(int row){ return webDriver.findElement(By.cssSelector("label[for=\"grid-row-"+row+"\"]")); }

    
    public void clickClaimCheckbox(String claimNumber){ this.click(getCssOfClaimCheckbox(findRowOfTheClaimByClaimNumber(claimNumber)));}

    
    public String getClaimBizStatus(String findClaimNumber){
        int row = 0;
        for(WebElement ele: claimNumber) {
            String getClaimNumber = ele.getText();
            if(getClaimNumber.trim().contains(findClaimNumber)){
                return businessStatusKind.get(row).getText();
            }else{
                row++;
            }
        }
        return null;
    }

    //Action Component
    
    public void clickActionBtnInRow(String claimNumber){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", btnAction.get(findRowOfTheClaimByClaimNumber(claimNumber)));
        this.click(btnAction.get(findRowOfTheClaimByClaimNumber(claimNumber)));
    }

    
    public String getClaimNumberByRow(int row){ return claimNumber.get(row).getText(); }
}
