package projectlab.test;

import framework.BaseTest;
import org.testng.annotations.BeforeClass;
import projectlab.pages.AccountsPage;
import projectlab.pages.LoginPage;
import projectlab.pages.MainPage;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestCase extends BaseTest {

    private final String accountName = "TestAccount";
    private final String accountNameEdited = "TestAccount123";


    @BeforeClass
    public void before() {
        resourceBundle = ResourceBundle.getBundle(resourceBundlePath, Locale.forLanguageTag(System.getProperty("language")));
    }

    public void runTest() {
        logger.step(1);
        LoginPage loginPage = new LoginPage();
        loginPage.logIn("loc.username", "loc.password");

        logger.step(2);
        MainPage mainPage = new MainPage();
        mainPage.openAccounts();

        logger.step(3);
        AccountsPage accountsPage = new AccountsPage();
        accountsPage.clickNew();
        accountsPage.provideAccountName(accountName);
        accountsPage.clickSave();
        accountsPage.validateAccountHasCreated(accountName);

        logger.step(4);
        accountsPage.clickEdit();
        accountsPage.provideAccountName(accountNameEdited);
        accountsPage.clickSave();
        accountsPage.validateAccountHasCreated(accountNameEdited);
    }
}
