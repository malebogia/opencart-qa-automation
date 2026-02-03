package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Factory class for managing WebDriver instances.
 * Implements lazy initialization and ensures a single driver instance per test.
 */
public class DriverFactory {

    protected Logger logger = LogManager.getLogger(DriverFactory.class);
    private static WebDriver driver;

    /**
     * Returns a singleton WebDriver instance based on the browser type in config.
     *
     * @return WebDriver instance
     */

    public static WebDriver getDriver() {

        if (driver == null) {
            String browserType = ConfigReader.getProperty("browser").toLowerCase();

            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new RuntimeException("Unsupported browser type: " + browserType);
            }

            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Quits the driver and resets the instance.
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
