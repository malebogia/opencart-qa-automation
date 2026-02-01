package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

import java.sql.DriverManager;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        ScreenshotUtils.takeScreenshot(DriverManager.getDriver());
    }
}
