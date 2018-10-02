package pageobjects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public abstract class PageObject {
    protected WebDriver webDriver;
    private static final int TIME_OUT = 15;

    private WebElement lastElem = null;
    private String lastBorder = null;

    private String scriptGetElementBorder;
    private String scriptUnhighlightElement;

    public PageObject() {
        try {
            scriptGetElementBorder = FileUtils.readFileToString(new File(this.getClass().getClassLoader()
                    .getResource("SCRIPT_GET_ELEMENT_BORDER.js").toURI()), "UTF-8");
            scriptUnhighlightElement = FileUtils.readFileToString(new File(this.getClass().getClassLoader()
                    .getResource("SCRIPT_UNHIGHLIGHT_ELEMENT.js").toURI()), "UTF-8");
            lastElem = null;
        } catch (IOException | URISyntaxException e) {
        }
    }

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        lastElem = null;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        lastElem = null;
    }

    protected void waitForElementInvisible(By locator) {
        try{
            // Wait for the element visible
            WebDriverWait wait = new WebDriverWait(webDriver, 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            // Wait for the element invisible
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }catch(Exception e){
        }
    }

    public void click(WebElement element) {
        new WebDriverWait(webDriver, TIME_OUT).until(ExpectedConditions.elementToBeClickable(element));
        highlightElement(element);
        element.click();
    }

    public void sendKeys(WebElement element, String text){
        new WebDriverWait(webDriver, TIME_OUT).until(ExpectedConditions.visibilityOf(element));
        highlightElement(element);
        element.clear();
        element.sendKeys(text);
    }

    public String getText(WebElement element){
        new WebDriverWait(webDriver, TIME_OUT).until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    public String getAttributeValue(By locator, String attributeName){
        new WebDriverWait(webDriver, TIME_OUT).until(ExpectedConditions.presenceOfElementLocated(locator));
        return  webDriver.findElement(locator).getAttribute(attributeName);
    }

    public void sendKeyboards(WebElement element, Keys keyboard){
        new WebDriverWait(webDriver, TIME_OUT).until(ExpectedConditions.visibilityOf(element));
        highlightElement(element);
        element.sendKeys(keyboard);
    }

    public void waitForElementPresent(By locator) {
        new WebDriverWait(webDriver, TIME_OUT).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    
    void highlightElement(WebElement elem) {
        unhighlightLast();

        // remember the new element
        lastElem = elem;
        lastBorder = (String)(((JavascriptExecutor) webDriver).executeScript(scriptGetElementBorder, elem));
    }

    void unhighlightLast() {
        if (lastElem != null) {
            try {
                // if there already is a highlighted element, unhighlight it
                ((JavascriptExecutor) webDriver).executeScript(scriptUnhighlightElement, lastElem, lastBorder);
            }
            catch (Exception e) {
            }
            finally {
                // element either restored or wasn't valid, nullify in both cases
                lastElem = null;
            }
        }
    }
}
