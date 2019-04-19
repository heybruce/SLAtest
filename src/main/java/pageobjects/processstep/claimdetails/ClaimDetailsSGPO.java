package pageobjects.processstep.claimdetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClaimDetailsSGPO extends ClaimDetailsPO{

    private static final String ID_DISPLAY_NAME = "root.task.displayName";

    @FindBy(id=ID_DISPLAY_NAME)
    private WebElement repairReferenceNumber;

    public String getDisplayName(){ return this.getAttributeValue(By.id(ID_DISPLAY_NAME), GET_ATTRIBUTE_VALUE);}

    public void enterDisplayName(String claimNumber){ this.sendKeys(repairReferenceNumber, claimNumber); }
}
