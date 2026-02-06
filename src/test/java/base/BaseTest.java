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
        }

        logger.info("========== TEST EXECUTION FINISHED ==========");

    } catch (Exception e) {
        logger.error("Error during teardown", e);
    }
}

    }


