package utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Utility class for attaching screenshots to Allure reports.
 */
public class AllureUtils {

    /**
     * Attaches a screenshot to Allure report.
     *
     * @param driver WebDriver instance
     * @return screenshot as byte array
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
