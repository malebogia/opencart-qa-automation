package frontend;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.base.WebApp;

public class RegistrationTest extends BaseTest {

    @Test
    public void openPage(){
        webApp.mainPage().openPage();
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

    
}
