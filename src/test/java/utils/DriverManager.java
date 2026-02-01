package utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {

    private static WebDriver driver;

    public static WebDriver getDriver(){

        if (driver == null){
            String browserType = ConfigReader.getProperty("browser");

            switch (browserType.toLowerCase()){
                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;

                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new RuntimeException(
                            "Unsupported browser type" + browserType
                    );

            }
            driver.manage().window().maximize();
        }

        return driver;
    }
    

    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
