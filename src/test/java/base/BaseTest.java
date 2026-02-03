package base;

import listeners.TestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.base.WebApp;
import utils.DriverFactory;
import utils.ScreenshotUtils;

/**
 * Base test class for all test cases.
 * Handles setup and teardown for WebDriver.
 */
@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebApp webApp;

    @BeforeMethod
    public void setUp() {
        try {
            logger.info("========== STARTING TEST EXECUTION ==========");
            driver = DriverFactory.getDriver(); // Get driver from factory
            webApp = new WebApp(driver);       // Initialize WebApp for page objects
            logger.info("WebDriver and WebApp initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize test environment", e);
            throw new RuntimeException("Setup failed", e);
        }
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        try {

            if (result.getStatus() == ITestResult.FAILURE && driver != null) {
                logger.error("Test {} failed. Capturing screenshot...", result.getName());
                ScreenshotUtils.takeScreenshot(driver);
            }

            DriverFactory.quitDriver();
            logger.info("WebDriver quit successfully");

            logger.info("========== TEST EXECUTION FINISHED ==========");
        } catch (Exception e) {
            logger.error("Error during teardown", e);
        }
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Test execution finished");
        DriverFactory.quitDriver(); // Quit driver after test
    }
}
