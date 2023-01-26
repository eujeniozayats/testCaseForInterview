package projectlab.pages;

import framework.BasePage;
import framework.elements.Button;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    private final Button btnAccounts = new Button(By.xpath("//one-app-nav-bar-item-root[3]/a"), "Accounts Button");

    public MainPage() {
        super(By.xpath("//one-app-nav-bar-item-root[3]/a"), "Main Page");
    }

    public void openAccounts() {
        btnAccounts.clickAndWait();
    }
}
