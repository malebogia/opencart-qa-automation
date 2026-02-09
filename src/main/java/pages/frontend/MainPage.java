package pages.frontend;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;
import utils.ConfigReader;


public class MainPage extends BasePage {

    private static final Logger logger = LogManager.getLogger(MainPage.class);


    public MainPage(WebDriver driver) {
        super(driver);
    }

    //======================
    // Elements and Locators
    //=======================

    @FindBy(css = "nav#top div.nav.float-end div.dropdown")
    WebElement myAccountButton;

    @FindBy(css = "nav#top .float-end .dropdown-menu a[href*='register']")
    WebElement registerButton;

    @FindBy(css = "nav#top .float-end .dropdown-menu a[href*='login']")
    WebElement loginButton;


    //===================
    // Basic Actions
    // ==================

    @Step("Open main page")
    public void openMainPage() {
        String baseUrl = ConfigReader.getProperty("base.url");
        logger.info("Opening the page: {}", baseUrl);
        driver.get(baseUrl);

    }

    private void clickMyAccountButton() {
        click(myAccountButton);
    }

    private void clickRegisterButton() {
        click(registerButton);
    }

    private void clickLoginButton() {
        click(loginButton);
    }


    // =========================
    // Business action
    // =========================


    @Step("Navigate to Registration page")
    public void navigateToRegistrationPage() {
        openMainPage();
        logger.info("Transition from mainPage to RegistrationPage");
        clickMyAccountButton();
        clickRegisterButton();
    }


}
