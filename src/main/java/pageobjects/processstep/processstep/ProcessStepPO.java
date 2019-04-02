package pageobjects.processstep.processstep;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;
import pageobjects.worklistgrid.WorkListGridPO;

public class ProcessStepPO extends PageObject {
    public static final String GET_ATTRIBUTE_CLASS = "class";
    //Icon menu
    @FindBy(id="worklist")
    private WebElement claimManager;
    @FindBy(id = "messagelist")
    private WebElement messages;
    @FindBy(id = "support")
    private WebElement support;
    @FindBy(id = "logout")
    private WebElement logout;

    //Menu
    @FindBy(css = ".collapsed-menu button")
    private WebElement collapsedMenu;

    @FindBy(xpath = "/html/body/div[1]/div/div[2]/div[2]/div[1]/nav/div/div[1]/div[2]")
    private WebElement currentStep;
    @FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div[1]/nav/div/div[1]/div[1]/button")
    private WebElement navigationActions;

    //Loading circle
    public static final By LOADING_CIRCLE = By.cssSelector(".loading-component ");
    public static final By LOADING_INLINE = By.cssSelector(".loading-inline");
    public static final By QAPTER_CONTENT = By.id("process-step-content");

    public ProcessStepPO() {
        super();
    }

    public ProcessStepPO(WebDriver webDriver) {
        super(webDriver);
    }

    public void clickClaimManager(){
        openCollapsedMenu();
        try {
            this.click(claimManager);
            new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.id(WorkListGridPO.ID_CLOSED_TAB)));
        }catch (TimeoutException e) {
            //Retry click
            this.click(claimManager);
        }
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickMessages(){
        openCollapsedMenu();
        this.click(messages);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public void clickLogout(){
        openCollapsedMenu();
        this.click(logout);
        waitForElementInvisible(LOADING_CIRCLE);
    }

    public String getCurrentStepText() { return getText(currentStep); }

    public void clickNavigationActions() { this.click(navigationActions);}

    public boolean isCollapsedMenuHidden(){
        boolean isHidden = false;
        if(collapsedMenu.getAttribute(GET_ATTRIBUTE_CLASS).contains("menu-hidden"))
            isHidden = true;
        return isHidden;
    }

    public void openCollapsedMenu(){
        if(isCollapsedMenuHidden()) {
            try {
                this.click(collapsedMenu);
                new WebDriverWait(webDriver, 5).until(ExpectedConditions.attributeContains(collapsedMenu, GET_ATTRIBUTE_CLASS, "menu-open"));
            } catch (TimeoutException e) {
                //Retry click
                this.click(collapsedMenu);
            }
        }
    }

}
