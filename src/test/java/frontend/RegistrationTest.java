package frontend;

import ENUMS.FieldNames;
import base.BaseTest;
import data.factories.UserFactory;
import dataproviders.RegistrationDataProvider;
import io.qameta.allure.*;
import jdk.jfr.Description;
import models.User;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DBUtils;

import java.util.ArrayList;
import java.util.List;


@Epic("Authentication")
@Feature("Registration")
public class RegistrationTest extends BaseTest {


    @Story("Successful user registration")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Verify that user can register with valid credentials.")
    @Description("Ënsure that a user is able to register successfully and appears in database.")
    public void registerNewUser() {

        User user = UserFactory.validUserToni();

        Allure.step("Navigate to Registration Page", () -> {
            webApp.mainPage().navigateToRegistrationPage();
        });

        Allure.step("Register new user: " + user.getEmail(), () -> {
            webApp.registrationPage().register(user);
        });

        Allure.step("Verify user exists in the database", () -> {
            boolean userExistInDB = DBUtils.isUserExistsByEmail(user.getEmail());
            Assert.assertTrue(userExistInDB, "User should exist in DB after registration: " + user.getEmail());
        });

    }


    @Story("Negative registration validation")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "registrationNegative",
            dataProviderClass = RegistrationDataProvider.class,
            description ="Negative registration validation")
    @Description("""
Verify that a user cannot register with invalid credentials.
Ensure that the system displays correct validation error messages
for each invalid input field.
""")
    public void tryToRegisterInvalidCredentials(String firstName,
                                                String lastName,
                                                String email,
                                                String password,
                                                String fieldName,
                                                String expectedMessage) {

        Allure.step("Navigate to Registration Page", () ->
        webApp.mainPage().navigateToRegistrationPage());


        Allure.step("Fill registration form with invalid data: " + email, () ->
                webApp.registrationPage()
                        .TryToRegisterInvalidCredentials(firstName, lastName, email, password)
        );


        Allure.step("Submit registration form", () -> {
            webApp.registrationPage().selectAgreePrivacyPolicyCheckbox();
            webApp.registrationPage().clickConfirmRegistrationButton();
        });

        FieldNames targetField = FieldNames.valueOf(fieldName);


        Allure.step("Verify error message for field: " + fieldName, () -> {
            String actualMsg = webApp.registrationPage().getErrorMessage(targetField);
            Assert.assertEquals(actualMsg, expectedMessage,
                    String.format("Error message for %s was incorrect!", fieldName));
        });
    }













    /*@Test
@Test
public void verifyUserInDatabase() throws SQLException {
    // 1. Run the query
    ResultSet result = DBUtils.executeQuery("SELECT email FROM users WHERE id=1");

    // 2. Move to the first row of data
    if (result.next()) {
        String actualEmail = result.getString("email");
        Assert.assertEquals(actualEmail, "test@example.com");
    }

    // 3. Clean up
    DBUtils.destroy();
}*/


    /*🧪 4️⃣ The negative test (clean & expressive)
@Test(
    dataProvider = "invalidRegistrationUsers",
    dataProviderClass = RegistrationDataProvider.class
)
public void shouldNotRegisterWithInvalidData(User user, String reason) {

    Allure.step("Negative scenario: " + reason);

    webApp.mainPage()
          .openMainPage()
          .navigateToRegistrationPage()
          .register(user);

    // assertion example
    assertTrue(
        webApp.registrationPage().isErrorMessageDisplayed(),
        "Expected validation error for scenario: " + reason
    );
}


📌 The test:

Does NOT know about CSV

Does NOT parse data

Reads like a specification*/

}
