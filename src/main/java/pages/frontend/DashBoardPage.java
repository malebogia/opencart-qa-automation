package pages.frontend;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class DashBoardPage extends BasePage {
    public DashBoardPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LogManager.getLogger(DashBoardPage.class);

//=======================
// Elements and Locators
//=======================

    @FindBy (xpath = "//nav[@id='top']//span[contains(text(),'Checkout')]")
    WebElement checkOutButton;

    @FindBy (css = "div#account-account")
    WebElement myAccountDiv;

 //======================
// Basic Actions
//=======================

    @Step("Check if the MyAccount menu is displayed")
    public boolean isMyAccountDivDisplayed(){
        logger.info("Verifying if the MyAccount div is displayed");
       return isElementDisplayed(myAccountDiv);
    }

}
