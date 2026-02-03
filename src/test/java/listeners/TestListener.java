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

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).driver;

        if (driver != null) {
            AllureUtils.attachScreenshot(driver); // Attach screenshot on failure
        }
    }
}
