package utils;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVDataProvider {
    public static Object[][] readCsv(String filename) throws Exception {

        InputStream inputStream =
                CSVDataProvider.class
                        .getClassLoader()
                        .getResourceAsStream(filename);

        if (inputStream == null) {
            throw new RuntimeException(
                    "CSV file '" + filename + "' not found in resources."
            );
        }

        try (CSVReader reader =
                     new CSVReader(new InputStreamReader(inputStream))) {

            List<Object[]> data = new ArrayList<>();
            String[] line;

            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {
                data.add(line);
            }

            return data.toArray(new Object[0][]);
        }
    }


}
