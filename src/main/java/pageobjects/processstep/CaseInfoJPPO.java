package pageobjects.processstep;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.PageObject;

import java.util.List;

public class CaseInfoJPPO extends PageObject {

    //CaseMessage
    @FindBy(id="CaseMessage")
    private WebElement caseMessage;

    //CaseComment
    @FindBy(id="CaseComment")
    private WebElement caseComment;
    @FindBy(name = "author")
    private List<WebElement> commentAuthor;
    @FindBy(name = "date")
    private List<WebElement> commentDate;
    @FindBy(name = "text")
    private List<WebElement> commentText;

    //CaseHistory
    @FindBy(id="CaseHistory")
    private WebElement caseHistory;
    @FindBy(name="eventNote")
    private List<WebElement> eventNote;
    @FindBy(name="eventStatus")
    private List<WebElement> evenStatus;
    @FindBy(name="taskStatus")
    private List<WebElement> taskStatus;
    @FindBy(name="eventLogUserId")
    private List<WebElement> eventLogUserId;
    @FindBy(name="eventUserFirstLastName")
    private List<WebElement> eventUserFirstLastName;
    @FindBy(name="eventLogDate")
    private List<WebElement> eventLogDate;
    @FindBy(name="taskRole")
    private List<WebElement> taskRole;


    //CaseHistory

    public int getEventNumber(){
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(caseHistory));
        return eventNote.size();
    }

    //CaseComment

    public int getCommentNumber(){ return commentText.size(); }

    public String getCommentAuthor(int row){ return this.getText(commentAuthor.get(row)); }

    public String getCommentDate(int row){ return this.getText(commentDate.get(row)); }

    public String getCommentText(int row){ return this.getText(commentText.get(row)); }
}
