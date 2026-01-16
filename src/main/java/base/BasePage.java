package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final int TIMEOUT = 5;

    protected static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Action group
    protected void click(By locator) {
        log.debug("Click element with locator: {}", locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        log.debug("Type [{}] into locator {}", text, locator);
        find(locator).sendKeys(text);
    }

    protected void clear(By locator, String text, boolean clearBefore) {
        log.debug("Clear [{}] in locator {}", text, locator);
        WebElement element = find(locator);
        if (!clearBefore) {
            element.clear();
        }
        element.sendKeys(text);
    }

    // Get group
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected String getAttribute(By locator, String attribute) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attribute);
    }

    protected String getCssValue(By locator, String value) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getCssValue(value);
    }

    protected String getTagName(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getTagName();
    }

    // Check group
    protected boolean isDisplayed(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    protected boolean isEnabled(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isEnabled();
    }

    protected boolean isSelected(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isSelected();
    }
}
