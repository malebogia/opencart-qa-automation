package pages.base;

import org.openqa.selenium.WebDriver;
import pages.frontend.LoginPage;

public class WebApp {
    private WebDriver driver;
    private LoginPage loginPage;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage loginPage() {
        if (loginPage == null){
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }


}
