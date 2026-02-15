package frontend;

import base.BaseTest;
import dataproviders.LoginDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigReader;


/*  Allure.step("", () -> {

        });   */

@Epic("Authentication")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Story("Successful user login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Verify that user can login with valid credentials.")
    @Description("Ensure that registered user can login successfully and redirected to dashboard page.")
    public void login() {
        String email = ConfigReader.getProperty("base.email");
        String password = ConfigReader.getProperty("default.password");

        Allure.step("Navigate to Login page", () -> {

            webApp.mainPage().navigateToLoginPage();
        });

        Allure.step("Login valid user ", () -> {
            webApp.loginPage().login(email, password);
        });

        Allure.step("Check if the redirection to dashboard is successful", () -> {
            Assert.assertTrue(webApp.dashBoardPage().isMyAccountDivDisplayed(),
                    "Checkout button not displayed — login redirection might have failed.");
        });

    }


    @Story("Negative login validation")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "loginNegative",
            dataProviderClass = LoginDataProvider.class,
            description = "Negative login validation.")
    @Description("""
            Verify that a user cannot login with invalid credentials.
            Ensure that the system displays correct validation error messages
            for each invalid input field.
            """)
    public void tryToLoginWithInvalidCredentials(String email, String password, String expectedMessage) {

        Allure.step("Navigate to Login Page", () ->
                webApp.mainPage().navigateToLoginPage()
        );

        Allure.step("Attempt login with invalid credentials", () ->
                webApp.loginPage().login(email, password)
        );

        Allure.step("Verify error message text", () ->
                Assert.assertTrue(webApp.loginPage().isErrorElementDisplayed())
        );

        Allure.step("Check if the alert's message is correct", () ->
                Assert.assertEquals(webApp.loginPage().getAlertText(), expectedMessage,
                        "Invalid error message!")
        );


    }


}