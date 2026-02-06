package pages.frontend;

import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class MainPage extends BasePage {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    private String URL = "http://localhost:8181/";


    public void openPage(){
        driver.get(URL);
    }
}
