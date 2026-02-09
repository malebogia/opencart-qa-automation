package pages.frontend;

import com.sun.jna.platform.win32.Netapi32Util;
import io.qameta.allure.Step;
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
    WebElement firstNameInput;

    @FindBy(id = "input-lastname")
    WebElement lastNameInput;

    @FindBy(id = "input-email")
    WebElement emailInput;

    @FindBy(id = "input-password")
    WebElement passwordInput;

    @FindBy(id ="input-newsletter")
    WebElement subscribeCheckBox;

    @FindBy(css = "div.text-end input[type='checkbox']")
    WebElement agreePrivacyPolicyCheckbox;

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;


//===================
// Basic Actions
// ==================

    private void fillFirstName(String firstName) {
        typeText(firstNameInput, firstName);
    }


    private void fillLastName(String lastName){
        typeText(lastNameInput, lastName);
    }

    private void fillEmail(String email){
        typeText(emailInput,email);
    }

    private void fillPassword(String password){
        typeText(passwordInput,password);
    }

    private void selectAgreePrivacyPolicyCheckbox(){
        selectCheckbox(agreePrivacyPolicyCheckbox);
    }

    private void clickConfirmRegistrationButton(){
        click(submitButton);
    }


    // =========================
    // Business action
    // =========================

    @Step("Register user with email: {user.email}")
    public void register(data.models.User user ){
        logger.info("Registering user with email: {}", user.getEmail());
        typeText(firstNameInput,user.getFirstName());
        typeText(lastNameInput, user.getLastName());
        typeText(emailInput,user.getEmail());
        typeText(passwordInput, user.getPassword());

        selectCheckbox(agreePrivacyPolicyCheckbox);
        selectCheckbox(submitButton);
    }



}