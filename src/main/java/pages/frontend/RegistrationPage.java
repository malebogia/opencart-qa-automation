package pages.frontend;

import ENUMS.FieldNames;
import io.qameta.allure.Step;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LogManager.getLogger(RegistrationPage.class);


//======================
// Elements and Locators
//=======================

    @FindBy(id = "input-firstname")
    private WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    private WebElement lastNameInput;

    @FindBy(id = "input-email")
    private WebElement emailInput;

    @FindBy(id = "input-password")
    private WebElement passwordInput;

    @FindBy(id = "input-newsletter")
    private WebElement subscribeCheckBox;

    @FindBy(css = "div.text-end input[type='checkbox']")
    private WebElement agreePrivacyPolicyCheckbox;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    @FindBy(id = "alert")
    private WebElement alert;

    @FindBy(id = "error-firstname")
    private WebElement firstNameError;

    @FindBy(id = "error-lastname")
    private WebElement lastNameError;

    @FindBy(id = "error-email")
    private WebElement emailError;

    @FindBy(id = "error-password")
    private WebElement passwordError;


//===================
// Basic Actions
// ==================

    private void fillFirstName(String firstName) {
        typeText(firstNameInput, firstName);
    }


    private void fillLastName(String lastName) {
        typeText(lastNameInput, lastName);
    }

    private void fillEmail(String email) {
        typeText(emailInput, email);
    }

    private void fillPassword(String password) {
        typeText(passwordInput, password);
    }


    //This basic action is public and market with @Step and log, because I use in negative DDT test.
    @Step("Select 'Agree to Privacy Policy' checkbox")
    public void selectAgreePrivacyPolicyCheckbox() {
        logger.info("Selecting 'Agree to Privacy Policy' checkbox");
        selectCheckbox(agreePrivacyPolicyCheckbox);
    }

    //This basic action is public and market with @Step and log, because I use it in negative DDT test.
    @Step("Click 'Confirm Registration' button")
    public void clickConfirmRegistrationButton() {
        logger.info("Clicking Confirm Registration button");
        click(submitButton);
    }


    // =========================
    // Business action
    // =========================

    @Step("Register user with email: {user.email}")
    public void register(User user) {

        logger.info("Registering user with email: {}", user.getEmail());
        typeText(firstNameInput, user.getFirstName());
        typeText(lastNameInput, user.getLastName());
        typeText(emailInput, user.getEmail());
        typeText(passwordInput, user.getPassword());

        selectCheckbox(agreePrivacyPolicyCheckbox);
        click(submitButton);
    }

    @Step("Get error message for field: {fieldName}")
    public String getErrorMessage(FieldNames fieldName) {

        logger.info("Retrieving error message for field: {}", fieldName);
        WebElement element = switch (fieldName) {

            case firstName -> firstNameError;
            case lastName -> lastNameError;
            case email -> emailError;
            case password -> passwordError;
        };

        return element.getText().trim();
    }

    @Step("Attempt registration with FirstName: {firstName}, LastName: {lastName}, Email: {email}")
    public void TryToRegisterInvalidCredentials(String firstName,
                                                String lastName,
                                                String email,
                                                String password) {

        logger.info("Typing registration data for email: {}", email);
        typeText(firstNameInput, firstName);
        typeText(lastNameInput, lastName);
        typeText(emailInput, email);
        typeText(passwordInput, password);

    }


}