package steps;

import cases.TestBase;
import pageobjects.LoginPO;
import utils.webdrivers.WebDriverFactory;

import static utils.webdrivers.WebDriverFactory.getDriver;

public class Login extends TestBase {
    public void LoginBRE(String username, String password){
        //login page
        LoginPO loginPO = new LoginPO();
        loginPO.setWebDriver(getDriver());
        loginPO.enterUserName(username);
        loginPO.enterPassword(password);
        loginPO.clickSubmit();
    }

}
