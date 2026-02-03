package pages.base;

import org.openqa.selenium.WebDriver;
import pages.frontend.LoginPage;

/**
 * Centralized access to all page objects.
 * Implements lazy initialization of pages.
 */
public class WebApp {

    private WebDriver driver;
    private LoginPage loginPage;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }
}
