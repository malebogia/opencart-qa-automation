package dataproviders;

import data.models.User;
import org.testng.annotations.DataProvider;
import utils.CsvUtils;

import java.util.List;

public class RegistrationDataProvider {

    @DataProvider(name = "invalidRegistrationUsers")
    public static Object[][] invalidRegistrationUsers() {

        List<String[]> rows =
                CsvUtils.readCsv("testdata/registration_negative.csv");

        Object[][] data = new Object[rows.size()][2];

        for (int i = 0; i < rows.size(); i++) {
            String[] row = rows.get(i);

            Object user = new User(
                    row[0].replace("\"", ""),
                    row[1].replace("\"", ""),
                    row[2],
                    row[3]
            );

            String reason = row[4];

            data[i][0] = user;
            data[i][1] = reason;
        }

        return data;
    }
}
