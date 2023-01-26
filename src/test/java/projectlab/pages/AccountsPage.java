package projectlab.pages;

import framework.BasePage;
import framework.browser.BrowserManager;
import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AccountsPage extends BasePage {

    private final String accName = "//div/lightning-formatted-text[text()='%s']";

    private final Button btnNew = new Button(By.cssSelector("a[title='New']"), "New Button ");

    public AccountsPage() {
        super(By.cssSelector("a[title='New']"), "Accounts Page");
    }

    public void clickNew() {
        btnNew.clickAndWait();
    }

    public void clickEdit() {
        info("Click Edit");
        Button btn = new Button(By.xpath("//button[@name='Edit']"), "Edit Button ");
        btn.waitForIsElementPresent();
        btn.clickAndWait();
    }

    public void provideAccountName(String accountName) {
        info("Type account name");
        WebElement modalContainer = BrowserManager.getInstance().getDriver().findElement(By.cssSelector("div[data-aura-class='oneRecordActionWrapper']"));
        WebElement modalContent = modalContainer.findElement(By.cssSelector("input[name='Name']"));
        modalContent.clear();
        modalContent.sendKeys(accountName);

        WebDriverWait wait = new WebDriverWait(BrowserManager.getInstance().getDriver(), Long.parseLong(propReader.getProperty("defaultPageLoadTimeout")));

        try {
            wait.until((ExpectedCondition<Boolean>) d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                return result instanceof Boolean && (Boolean) result;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickSave() {
        info("Click Save Button");
        WebElement modalContainer = BrowserManager.getInstance().getDriver().findElement(By.cssSelector("div[data-aura-class='oneRecordActionWrapper']"));
        WebElement modalContent = modalContainer.findElement(By.cssSelector("button[name='SaveEdit']"));
        modalContent.click();

        WebDriverWait wait = new WebDriverWait(BrowserManager.getInstance().getDriver(), Long.parseLong(propReader.getProperty("defaultPageLoadTimeout")));

        try {
            wait.until((ExpectedCondition<Boolean>) d -> {
                if (!(d instanceof JavascriptExecutor)) {
                    return true;
                }
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");
                return result instanceof Boolean && (Boolean) result;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void validateAccountHasCreated(String accountName) {
        info("Validating account name is displayed");
        TextBox txbAccName = new TextBox(By.xpath(String.format(accName, accountName)), accountName);
        txbAccName.waitForIsElementPresent();
        Boolean isAccNameDisplayed = txbAccName.isDisplayed();
        Assert.assertTrue(isAccNameDisplayed);
    }
}
