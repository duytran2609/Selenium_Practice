package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseTest {

    protected WebDriver driver;

    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeMethod
    public void setUp() {
        log.info("Start browser");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        log.info("Quit browser");
        if (driver != null) {
            try {
                Thread.sleep(4500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
