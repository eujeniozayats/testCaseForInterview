package framework.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import javax.naming.NamingException;
import java.io.File;
import java.util.UUID;

public abstract class BrowserFactory {
    public static File folder;
    public static WebDriver driver;


    public static WebDriver initialize(final BrowserTypes type) {

        folder = new File(UUID.randomUUID().toString());
        folder.mkdir();
        switch (type) {

            case firefox:
                FirefoxOptions firefoxOptions = DriverOptionsManager.getFirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case chrome:
                ChromeOptions chromeOptions = DriverOptionsManager.getChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                break;

        }
        return driver;
    }

    public static WebDriver initialize(final String type) throws NamingException {
        for (BrowserTypes t : BrowserTypes.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return initialize(t);
            }
        }
        throw new NamingException();
    }
}