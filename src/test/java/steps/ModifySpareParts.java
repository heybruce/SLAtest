package steps;

import cases.TestBase;
import pageobjects.processstep.ModifySparePartsPO;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class ModifySpareParts extends TestBase {

    private ModifySparePartsPO modifySparePartsPO;

    public ModifySpareParts() {
        modifySparePartsPO.setWebDriver(getDriver());
    }

    public void addPart(String partNumber, String partDescription, String partPrice, String partSupplier, String partType){
        modifySparePartsPO.inputPartNumber(partNumber);
        modifySparePartsPO.inputPartDescription(partDescription);
        modifySparePartsPO.inputPartPrice(partPrice);
        modifySparePartsPO.inputPartSupplier(partSupplier);
        modifySparePartsPO.selectPartType(partType);
        modifySparePartsPO.clickAddPart();
    }
}
