package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MoviePage extends BasePage {

    By title = By.cssSelector(".page-title");

    public MoviePage(WebDriver driver) {
        super(driver);
    }

    public boolean isMoviePageDisplay() {
        return isDisplayed(title);
    }

}
