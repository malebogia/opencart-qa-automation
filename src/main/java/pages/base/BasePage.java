package pages.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    }


    //===============
    // BASIC ELEMENT CHECKS
    //===============


    protected boolean isElementSelected(WebElement webElement){
        waitUntilVisible(webElement);
        boolean selected = webElement.isSelected();
        logger.info("Element {} selected: {}",webElement,selected);
        return selected;
    }

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

    public boolean isElementPresent(By locator) {
        waitUntilPresence(locator);
        boolean present = !driver.findElements(locator).isEmpty();
        logger.info("Element located by {} present: {}",locator,present);
        return present;
    }

    /**
     * Checks that element does NOT exist in the DOM
     * Useful    for delete validations
     */
    public boolean isElementNotPresent(By locator) {
        boolean notPresent = driver.findElements(locator).isEmpty();
        logger.info("Element located by {} not present: {}", locator, notPresent);
        return notPresent;
    }


    //==================
    // Dropdown Helpers
    //==================

    protected void selectByVisibleText(WebElement dropdown, String visibleText){
        waitUntilElementClickable(dropdown);
        new Select(dropdown).selectByVisibleText(visibleText);
        logger.info("Selected '{}' in dropdown {}", visibleText, dropdown);
    }

    protected void selectByValue(WebElement dropdown, String value) {
        waitUntilElementClickable(dropdown);
        new Select(dropdown).selectByValue(value);
        logger.info("Selected value '{}' in dropdown {}", value, dropdown);
    }

    protected void selectByIndex(WebElement dropdown, int index) {
        waitUntilElementClickable(dropdown);
        new Select(dropdown).selectByIndex(index);
        logger.info("Selected index '{}' in dropdown {}", index, dropdown);
    }


    //===================
    // COMMON ELEMENT INTERACTIONS
    //===================


    protected void click(WebElement element) {
        waitUntilElementClickable(element);
        element.click();
        logger.info("Clicked element: {}", element);
    }

    protected void typeText(WebElement element, String text) {
        waitUntilVisible(element).sendKeys(text);
        logger.info("Typed text '{}' into element: {}", text, element);
    }


    protected void clearText(WebElement element) {
        waitUntilVisible(element).clear();
        logger.info("Cleared text from element: {}", element);
    }

    protected String getText(WebElement element) {
        String text = waitUntilVisible(element).getText();
        logger.info("Got text '{}' from element: {}", text, element);
        return text;
    }

    protected List<WebElement> getElements(By locator) {
        waitUntilPresence(locator);
        List<WebElement> elements = driver.findElements(locator);
        logger.info("Found {} elements using locator: {}", elements.size(), locator);
        return elements;
    }


    //===================
    // HELPER METHODS
    //===================

    protected WebElement waitUntilElementClickable(WebElement webElement){
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitUntilPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }





}
