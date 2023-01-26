package framework.browser;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class DriverOptionsManager {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options;

        File myFile = null;

        URL myTestURL = ClassLoader.getSystemResource("chromedriver.exe");
        try {
            myFile = new File(myTestURL.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert myFile != null;
        System.setProperty("webdriver.chrome.driver", myFile.getAbsolutePath());

        Map<String, Object> prefs;
        prefs = new HashMap<>();
        prefs.put("download.default_directory", BrowserFactory.folder.getAbsolutePath());
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("download.extensions_to_open", "application/xml");
        prefs.put("safebrowsing.enabled", true);

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--safebrowsing-disable-download-protection");
        options.addArguments("safebrowsing-disable-extension-blacklist");

        return options;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options;
        File myFile = null;

        URL myTestURL = ClassLoader.getSystemResource("geckodriver.exe");
        try {
            myFile = new File(myTestURL.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        assert myFile != null;
        System.setProperty("webdriver.gecko.driver", myFile.getAbsolutePath());

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", BrowserFactory.folder.getAbsolutePath());
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        profile.setPreference("browser.download.manager.useWindow", false);
        profile.setPreference("browser.download.manager.focusWhenStarting", false);
        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
        profile.setPreference("browser.download.manager.closeWhenDone", true);
        options = new FirefoxOptions();
        options.setProfile(profile);

        return options;
    }

}
