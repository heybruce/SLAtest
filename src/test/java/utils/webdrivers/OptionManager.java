package utils.webdrivers;

import org.apache.commons.configuration2.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestContext;
import utils.UtilitiesManager;

import java.util.Hashtable;
import java.util.Map;

class OptionManager {

    static Configuration configProp = UtilitiesManager.setPropertiesFile("driver.properties");
    private ChromeOptions chromeOptions = new ChromeOptions();
    private FirefoxOptions firefoxOptions = new FirefoxOptions();

    ChromeOptions getChromeOptions() {
        return chromeOptions;
    }

    void setChromeOptions(ITestContext context) {
        Map<String, Object> preferences = new Hashtable<>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", "false");
        preferences.put("download.default_directory", System.getProperty("user.dir") + "/target");
        preferences.put("plugins.plugins_disabled", new String[]{
                "Adobe Flash Player", "Chrome PDF Viewer"});
        preferences.put("plugins.always_open_pdf_externally",true);

        chromeOptions.addArguments("--window-size=1440,900");
        chromeOptions.addArguments("start-maximized");
        if (Boolean.valueOf(configProp.getString("is_headless")))
            chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--lang=" + context.getCurrentXmlTest().getLocalParameters().get("locale"));
        chromeOptions.setExperimentalOption("prefs", preferences);
    }

    FirefoxOptions getFirefoxOptions() {
        return firefoxOptions;
    }

    void setFirefoxOptions(ITestContext context) {
        firefoxOptions.setHeadless(Boolean.valueOf(configProp.getString("is_headless")));
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("intl.accept_languages", context.getCurrentXmlTest().getLocalParameters().get("locale"));
        firefoxProfile.setPreference("browser.download.dir", System.getProperty("user.dir") + "/target");
        firefoxProfile.setPreference("browser.download.folderList",2);
        firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk"
                ,"application/csv,charset=utf-16,text/csv,application/vnd.ms-excel,application/pdf,application/json,images/jpg,application/zip");
        firefoxProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
        firefoxProfile.setPreference("pdfjs.disabled", true);
        firefoxProfile.setPreference("plugin.disable_full_page_plugin_for_types", "application/pdf,application/vnd.adobe.xfdf,application/vnd.fdf,application/vnd.adobe.xdp+xml");
        firefoxProfile.setPreference("pdfjs.enabledCache.state", false);
        // Disable firefox update
        firefoxProfile.setPreference("app.update.enabled", false);
        firefoxProfile.setPreference("app.update.service.enabled", false);
        firefoxProfile.setPreference("app.update.auto", false);
        firefoxProfile.setPreference("app.update.staging.enabled", false);
        firefoxProfile.setPreference("app.update.silent", false);

        firefoxProfile.setPreference("media.gmp-provider.enabled", false);

        firefoxProfile.setPreference("extensions.update.autoUpdate", false);
        firefoxProfile.setPreference("extensions.update.autoUpdateEnabled", false);
        firefoxProfile.setPreference("extensions.update.enabled", false);
        firefoxProfile.setPreference("extensions.update.autoUpdateDefault", false);

        firefoxProfile.setPreference("lightweightThemes.update.enabled", false);

        // SetProfile for FirefoxOptions
        firefoxOptions.setProfile(firefoxProfile);
    }

    InternetExplorerOptions getIeOptions() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        return ieOptions;
    }
}
