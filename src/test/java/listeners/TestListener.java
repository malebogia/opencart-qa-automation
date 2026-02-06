package listeners;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.AllureUtils;

/**
 * TestNG listener for capturing failures and attaching screenshots to Allure.
 */
public class TestListener implements ITestListener {
        private static final Logger logger =
            LogManager.getLogger(TestListener.class);
    

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error("Test FAILED: {}", result.getName());

        Object instance = result.getInstance();

         if (instance instanceof BaseTest) {
            WebDriver driver = ((BaseTest) instance).driver;
             

        if (driver != null) {
                logger.info("Attaching screenshot to Allure report");
                AllureUtils.attachScreenshot(driver);
            } else {
                logger.warn("WebDriver is null. Screenshot not captured");
            }
        } else {
            logger.warn("Test class does not extend BaseTest");
        }
    }
}
