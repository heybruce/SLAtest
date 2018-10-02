package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import pageobjects.worklistgrid.WorkListGridInboxPO;

public class TopNavigationBarPO extends PageObject{

    @FindBy(how = How.ID, using = "tabcontainer_worklistgrid_copied")
    private WebElement inbox;

    @FindBy(how = How.ID, using = "tabcontainer_worklistgrid_open")
    private WebElement open;

    @FindBy(how = How.ID, using = "tabcontainer_worklistgrid_closed")
    private WebElement closed;

    @FindBy(how = How.ID, using = "tabcontainer_messagelistgrid.all")
    private WebElement messages;

    public TopNavigationBarPO() {
        super();
    }

    public TopNavigationBarPO(WebDriver webDriver) {
        super(webDriver);
    }

    public WorkListGridInboxPO clickInboxTab() {
        inbox.click();
        return new WorkListGridInboxPO(webDriver);
    }

}
