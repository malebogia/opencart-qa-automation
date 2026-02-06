package pages.base;

import org.openqa.selenium.WebDriver;
import pages.frontend.LoginPage;
import pages.frontend.MainPage;
import pages.frontend.RegistrationPage;

public class WebApp {
    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;

    public WebApp(WebDriver driver){this.driver = driver; }

    public MainPage mainPage(){
        if (mainPage == null){
            mainPage = new MainPage(driver);
        }
        return mainPage;
    }

    public RegistrationPage registrationPage(){
        if (registrationPage == null){
            registrationPage = new RegistrationPage(driver);
        }
        return registrationPage;
    }

    public LoginPage loginPage(){
        if (loginPage == null){
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }


}
