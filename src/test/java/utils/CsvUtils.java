package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static List<String[]> readCsv(String filePath) {
        List<String[]> data = new ArrayList<>();

        try (InputStream is = CsvUtils.class
                .getClassLoader()
                .getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            boolean skipHeader = true;

            while ((line = reader.readLine()) != null) {
                if (skipHeader) {
                    skipHeader = false;
                    continue;
                }
                data.add(line.split(","));
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV: " + filePath, e);
        }

        return data;
    }
}
