package framework;

import framework.browser.BrowserManager;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public abstract class BaseEntity {

    static final String propFile = "selenium.properties";
    public static String resourceBundlePath = "localization/loc";
    public static PropReader propReader;
    protected static BrowserManager browser = BrowserManager.getInstance();
    protected static ResourceBundle resourceBundle;
    protected static Logger logger = Logger.getInstance();
    protected ITestContext context;

    protected static String getLocale(final String key) {
        return resourceBundle.getString(key);
    }

    protected abstract String formatLogMsg(String message);

    protected void info(final String message) {
        logger.info(formatLogMsg(message));
    }


    @BeforeClass
    public void before(ITestContext context) {
        propReader = new PropReader(propFile);
        this.context = context;
        browser = BrowserManager.getInstance();
        browser.getDriver().manage().window().maximize();
        browser.getDriver().manage().deleteAllCookies();
        browser.getDriver().manage().timeouts().implicitlyWait(Long.parseLong(propReader.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        browser.getDriver().manage().timeouts().pageLoadTimeout(Long.parseLong(propReader.getProperty("defaultPageLoadTimeout")), TimeUnit.SECONDS);
        browser.navigate(propReader.getProperty("url"));
    }

    @AfterClass
    public void after() {

        browser.exit();

    }


}