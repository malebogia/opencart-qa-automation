package pages.frontend;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

//======================
// Elements and Locators
//=======================
    @FindBy(id = "input-email")
   private WebElement emailInput;

    @FindBy(id = "input-password")
    private   WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private   WebElement submitButton;

    @FindBy(xpath = "//div[@class = 'col mb-3']//a[@class = 'btn btn-primary']")
    private  WebElement newCustomerRegButton;

    @FindBy (id = "alert")
    private  WebElement invalidCredentialAlert;


//======================
// Basic Actions
//=======================

    private void typeEmail(String email) {
        typeText(emailInput, email);
    }


    private void typePassword(String password) {
        typeText(passwordInput, password);
    }

    private void clickLoginButton() {
        click(submitButton);
    }

    private void clickRegNewCustomerButton() {
        click(newCustomerRegButton);
    }

    @Step("Check if the error alert is displayed.")
    public boolean isErrorElementDisplayed(){
        logger.info("verifying if the error alert is displayed.");
        return isElementDisplayed(invalidCredentialAlert);
    }

    @Step("Check if the text of error alert is correct.")
    public String getAlertText(){
        logger.info("Verifying non-empty alert and correct error message.");
       return getText(invalidCredentialAlert);
    }





    // =========================
    // Business action
    // =========================
    @Step("Login with valid credentials")
    public void login(String email, String password) {
        logger.info("Typing valid email: {}" , email);
        typeEmail(email);

        logger.info("Typing valid password");
        typePassword(password);

        logger.info("Clicking the login button");
        clickLoginButton();
    }







}
