package projectlab.pages;

import framework.BasePage;
import framework.elements.Button;
import framework.elements.TextBox;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final TextBox txbUsername = new TextBox(By.id("username"), "Username TextBox");
    private final TextBox txbPassword = new TextBox(By.id("password"), "Password TextBox");
    private final Button btnLogIn = new Button(By.id("Login"), "Log In Button");


    public LoginPage() {
        super(By.id("main"), "Login Page");
    }

    public void logIn(String username, String password) {
        txbUsername.sendKeys(getLocale(username));
        txbPassword.sendKeys(getLocale(password));
        btnLogIn.clickAndWait();
    }
}
