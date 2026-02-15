package pages.base;

import org.openqa.selenium.WebDriver;
import pages.frontend.DashBoardPage;
import pages.frontend.LoginPage;
import pages.frontend.MainPage;
import pages.frontend.RegistrationPage;

/**
 * Centralized access to all page objects.
 * Implements lazy initialization of pages.
 */
public class WebApp {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private DashBoardPage dashBoardPage;

    public WebApp(WebDriver driver) {
        this.driver = driver;
    }


    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public MainPage mainPage() {
        if (mainPage == null) {
            mainPage = new MainPage(driver);
        }
        return mainPage;
    }

    public RegistrationPage registrationPage() {
        if (registrationPage == null) {
            registrationPage = new RegistrationPage(driver);
        }
        return registrationPage;
    }

    public DashBoardPage dashBoardPage(){
        if (dashBoardPage == null){
            dashBoardPage = new DashBoardPage(driver);
        }
        return dashBoardPage;
    }

}

