package pages.base;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Base class for all Page Objects.
 * Handles WebDriver and WebDriverWait initialization.
 */
public class BasePage {

    private static final Logger logger = LogManager.getLogger(BasePage.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        logger.info("Initialized page: {}", this.getClass().getSimpleName());
        Allure.step("Initialized page: " + this.getClass().getSimpleName());
    }


    //===============
    // BASIC ELEMENT CHECKS
    //===============

    @Step("Check if element is selected")
    protected boolean isElementSelected(WebElement webElement) {
        waitUntilVisible(webElement);
        boolean selected = webElement.isSelected();
        logger.info("Element {} selected: {}", webElement, selected);
        return selected;
    }

    @Step("Check if element is displayed")
    public boolean isElementDisplayed(WebElement element) {
        try {
            waitUntilVisible(element);
            boolean displayed = element.isDisplayed();
            logger.info("Element {} displayed: {}", element, displayed);
            return displayed;
        } catch (TimeoutException e) {
            logger.warn("Element {} not displayed after waiting", element);
            return false;
        }
    }

    @Step("Check if element is present")
    public boolean isElementPresent(By locator) {
        waitUntilPresence(locator);
        boolean present = !driver.findElements(locator).isEmpty();
        logger.info("Element located by {} present: {}", locator, present);
        return present;
    }

    /**
     * Checks that element does NOT exist in the DOM
     * Useful    for delete validations
     */
    @Step("Check if element is NOT present")
    public boolean isElementNotPresent(By locator) {
        boolean notPresent = driver.findElements(locator).isEmpty();
        logger.info("Element located by {} not present: {}", locator, notPresent);
        return notPresent;
    }


    //==================
    // Dropdown Helpers
    //==================
    @Step("Select '{visibleText}' from dropdown")
    protected void selectByVisibleText(WebElement dropdown, String visibleText) {
        waitUntilElementClickable(dropdown);
        new Select(dropdown).selectByVisibleText(visibleText);
        logger.info("Selected '{}' in dropdown {}", visibleText, dropdown);
    }

    @Step("Select '{value}' from dropdown")
    protected void selectByValue(WebElement dropdown, String value) {
        waitUntilElementClickable(dropdown);
        new Select(dropdown).selectByValue(value);
        logger.info("Selected value '{}' in dropdown {}", value, dropdown);
    }

    @Step("Select '{index}' from dropdown")
    protected void selectByIndex(WebElement dropdown, int index) {
        waitUntilElementClickable(dropdown);
        new Select(dropdown).selectByIndex(index);
        logger.info("Selected index '{}' in dropdown {}", index, dropdown);
    }


    //===================
    // COMMON ELEMENT INTERACTIONS
    //===================

    @Step("Click on element")
    protected void click(WebElement element) {
        waitUntilElementClickable(element);
        scrollIntoView(element);
        element.click();
        logger.info("Clicked element");
    }

    @Step("Click on element located by: {locator}")
    protected void click(By locator) {
        waitUntilPresence(locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        scrollIntoView(element);
        element.click();
        logger.info("Clicked element [{}]", locator);
    }

    @Step("Type text '{text}' into element")
    protected void typeText(WebElement element, String text) {
        WebElement visibleElement = waitUntilVisible(element);
        visibleElement.clear();
        visibleElement.sendKeys(text);
        logger.info("Typed text '{}'", text);

    }

    @Step("Clear text from element")
    protected void clearText(WebElement element) {
        waitUntilVisible(element).clear();
        logger.info("Cleared text from element: {}", element);
    }

    @Step("Get text from element")
    protected String getText(WebElement element) {
        String text = waitUntilVisible(element).getText();
        logger.info("Got text '{}'", text);
        return text;
    }

    @Step("Find elements using locator {locator}")
    protected List<WebElement> getElements(By locator) {
        waitUntilPresence(locator);
        List<WebElement> elements = driver.findElements(locator);
        logger.info("Found {} elements using locator: {}", elements.size(), locator);
        return elements;
    }

    @Step("Ensure checkbox is selected")
    protected void selectCheckbox(WebElement element) {
        scrollIntoView(element);
        waitUntilElementClickable(element);
        
        if (!element.isSelected()) {
            click(element);
            logger.info("Checkbox is selected");
        }
    }


    //===================
    // WAIT METHODS
    //===================

    protected WebElement waitUntilElementClickable(WebElement webElement) {
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    protected void waitUntilInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        logger.info("Element {} is now invisible", locator);
    }


    // =================
    // SCROLL HELPERS
    // =================

    protected void scrollIntoView(WebElement element) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
            logger.debug("Scrolled element into view");
        } catch (Exception e) {
            logger.error("Scroll into view failed", e);
        }
    }


}
