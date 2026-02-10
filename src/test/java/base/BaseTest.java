package base;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.base.WebApp;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ScreenshotUtils;

/**
 * Base test class for all test cases.
 * Handles setup and teardown for WebDriver.
 */
@Listeners(listeners.TestListener.class)
public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebApp webApp;

    public WebDriver getDriver() {
        return driver;
    }


    @BeforeMethod
    public void setUp() {
        Allure.step("Starting test setup");
        try {
            logger.info("========== STARTING TEST EXECUTION ==========");
            driver = DriverFactory.getDriver(); 
            webApp = new WebApp(driver);       
            logger.info("WebDriver and WebApp initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize test environment", e);
            throw new RuntimeException("Setup failed", e);
        }
    }


    @AfterMethod
public void tearDown(ITestResult result) {

    try {
        // Take screenshot only if test failed
        if (result.getStatus() == ITestResult.FAILURE && driver != null) {
            
            Allure.step("Test failed. Capturing screenshot");
            
            logger.error("Test '{}' failed. Capturing screenshot...", result.getName());
            
            ScreenshotUtils.takeScreenshot(driver);
        }

        boolean closeBrowser =
                Boolean.parseBoolean(ConfigReader.getProperty("close.browser"));

        if (closeBrowser) {
            try {
                DriverFactory.quitDriver();
                logger.info("Driver closed successfully");
            } catch (NoSuchSessionException e) {
                logger.warn("Browser was already closed manually. Session already invalid.");
            }
        } else {
            logger.warn("Browser left open for debugging purposes");
            logger.warn("Set close.browser=true to enable automatic browser closing");
            Allure.step("Browser left open for debugging");
        }

        DBUtils.destroy();

        logger.info("========== TEST EXECUTION FINISHED ==========");

    } catch (Exception e) {
        logger.error("Error during teardown", e);
    }
}

    }


