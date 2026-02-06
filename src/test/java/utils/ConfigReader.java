package utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Utility class to read configuration properties.
 */
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;

    static {
        try {
            String path = "src/test/resources/config.properties";
            logger.info("Loading configuration file from path: {}", path);
            
            FileInputStream input = new FileInputStream(path);
            properties.load(input);
            input.close();
            
            logger.info("Configuration file loaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not load config.properties file!", e);
            logger.fatal("Failed to load config.properties file", e);
        }
    }

    /**
     * Gets the value of a property by key.
     *
     * @param key property key
     * @return property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        
        if (value == null) {
            logger.warn("Requested property '{}' was not found in config.properties", key);
        }

        return value;
    }
}
