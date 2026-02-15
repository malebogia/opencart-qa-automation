package dataproviders;

import org.testng.annotations.DataProvider;
import utils.CSVDataProvider;

public class RegistrationDataProvider {
    @DataProvider(name = "registrationNegative")
    public static Object[][] registrationNegative() throws Exception {
        return CSVDataProvider.readCsv(
                "invalid_users.csv"
        );
    }
}
