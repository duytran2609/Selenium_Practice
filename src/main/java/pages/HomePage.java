package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    private MoviePage moviePage;

    By btnLogout = By.cssSelector(".logout-btn");
    By btnLogin = By.cssSelector("a[data-discover='true']");
    By crdMovie = By.cssSelector("a[href='/movies/tt0462538']");
    By innerMovie = By.linkText("Phim");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        try {
            click(btnLogout);
        } catch (Exception e) {
            log.error("Logout fail because: ", e);
        }
    }

    public MoviePage navigateMoviePage() {
        try {
            click(innerMovie);
        } catch (Exception e) {
            log.error("Navigate to movie page fail because: ", e);
        }
        return new MoviePage(driver);
    }

    public boolean isLogoutSuccess() {
        return isDisplayed(btnLogin);
    }

    public boolean isLogoutEnable() {
        return isEnabled(btnLogin);
    }

}
