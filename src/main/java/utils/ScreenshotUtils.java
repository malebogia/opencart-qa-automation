package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Utility class for taking screenshots for Allure reports.
 */
public class ScreenshotUtils {

    /**
     * Captures a screenshot and attaches it to Allure report.
     *
     * @param driver WebDriver instance
     * @return screenshot as byte array
     */
    @Attachment(value = "Failure screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
