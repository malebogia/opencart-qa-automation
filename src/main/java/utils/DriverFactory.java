package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

    private static Logger logger = LogManager.getLogger(DriverFactory.class);
    private static WebDriver driver;

    /**
     * Returns a singleton WebDriver instance based on the browser type in config.
     *
     * @return WebDriver instance
     */

    public static WebDriver getDriver() {

        if (driver == null) {
            String browserType = ConfigReader.getProperty("browser").toLowerCase();
            logger.info("Initializing WebDriver for browser: {}", browserType);

            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    logger.info("ChromeDriver started successfully");
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    logger.info("FirefoxDriver started successfully");
                    break;

                default:
                    logger.error("Unsupported browser type: {}", browserType);
                    throw new RuntimeException("Unsupported browser type: " + browserType);
            }

            driver.manage().window().maximize();
            logger.info("Browser window maximized");
        }
        return driver;
    }

    /**
     * Quits the driver and resets the instance.
     */
    public static void quitDriver() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.quit();
            driver = null;
            logger.info("WebDriver closed successfully");
        } else {
            logger.warn("Driver.quit() called but WebDriver instance was already null");
        }
    }

}
