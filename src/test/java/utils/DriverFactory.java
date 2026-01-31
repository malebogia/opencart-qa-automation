package utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

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

            }
        }

        return driver;
    }



}
