package base;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    protected Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp(){
        logger.info("Starting test execution");
    }

    @AfterMethod
    public void tearDown(){
        logger.info("Test execution finished");
    }
}
