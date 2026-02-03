package utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Utility class to read configuration properties.
 */
public class ConfigReader {

    private static Properties properties;

    static {
        try {
            String path = "src/test/resources/config.properties";
            FileInputStream input = new FileInputStream(path);

            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties file!", e);
        }
    }

    /**
     * Gets the value of a property by key.
     *
     * @param key property key
     * @return property value
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
