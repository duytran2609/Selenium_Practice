package tests.movie;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MovieDetailsPage;
import pages.MoviePage;
import tests.driver.DriverManager;

public class WatchMovieTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;
    private MovieDetailsPage movieDetailsPage;
    private HeaderComponent headerComponent;

    @BeforeMethod
    public void setUpSeeMovieDetailsTest() {
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
    }

    // ===== NAVIGATION =====
    @Test
    public void testNavigateToMovieDetailsPageViaHomePage() {
        movieDetailsPage = homePage.navigateToMovieDetailsPage();
        Assert.assertTrue(homePage.isNavigateToMovieDetailsPage(), "Cannot navigate to movie details page");
    }

    @Test
    public void testNavigateToMovieDetailsPageViaMoviePage() {
        moviePage = homePage.headerComponent.navigateToMoviePage();
        movieDetailsPage = moviePage.navigateToMovieDetailsPage();
        Assert.assertTrue(moviePage.isNavigateToMovieDetailsPage(), "Cannot navigate to movie details page");
    }

    // ===== CHECKING =====
    @Test
    public void testMovieInformationEquals() {
        moviePage = homePage.headerComponent.navigateToMoviePage();
        movieDetailsPage = moviePage.navigateToMovieDetailsPage();
        Assert.assertTrue(moviePage.isNavigateToMovieDetailsPage(), "Cannot navigate to movie details page");
    }


}
