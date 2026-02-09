package frontend;

import base.BaseTest;
import data.factories.UserFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.DBUtils;

@Epic("Authentication")
@Feature("Registration")
public class RegistrationTest extends BaseTest {


    @Test(description = "Verify that user can register with valid credentials.")
    @Description("Ënsure that a user is able to register successfully and appears in database.")
    public void registerNewUser() {

        data.models.User user = UserFactory.validUserToni();

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
