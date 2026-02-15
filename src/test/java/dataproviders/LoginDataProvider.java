package dataproviders;

import org.testng.annotations.DataProvider;
import utils.CSVDataProvider;

public class LoginDataProvider {
    @DataProvider(name = "loginNegative")
    public static Object[][] loginNegative() throws Exception {
        return CSVDataProvider.readCsv(
                "invalid_credentials.csv"
        );
    }
}
