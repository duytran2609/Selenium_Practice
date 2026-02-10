package tests.movie;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MovieDetailsPage;
import pages.MoviePage;
import tests.driver.DriverManager;

public class ViewMovieListTest extends BaseTest {

    public LoginPage loginPage;
    public HomePage homePage;
    public MoviePage moviePage;
    public MovieDetailsPage movieDetailsPage;

    @BeforeMethod
    public void setUpGetMovieListTest() {
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
    }

    // ===== NAVIGATION =====
    @Test
    public void shouldNavigateToMoviePageSuccessfullyViaHomePageHeader() {
        if (homePage.headerComponent.isMovieActive()) {
            moviePage = homePage.headerComponent.navigateToMoviePage();
        }
        Assert.assertTrue(homePage.headerComponent.isNavigateToMoviePageSuccess(), "Cannot navigate to movie page");
    }

//    @Test
//    public void testNavigateViaMovieDetailsPageHeader() {
//        homePage.navigateToMovieDetailsPage();
//        movieDetailsPage = new MovieDetailsPage(DriverManager.getDriver());
//        if (movieDetailsPage.headerComponent.isMovieActive()) {
//           moviePage = movieDetailsPage.headerComponent.navigateToMoviePage();
//        }
//        Assert.assertTrue(movieDetailsPage.headerComponent.isNavigateToMoviePageSuccess(), "Cannot navigate to movie page");
//    }

    @Test
    public void shouldNavigateToMoviePageSuccessfullyViaExploreButton() {
        if (homePage.isExploreTextActive()) {
            moviePage = homePage.navigateToMoviePageByExploreText();
        }
        Assert.assertTrue(homePage.isNavigateToMoviePage(), "Cannot navigate to movie page");
    }

    @Test
    public void shouldNavigateToMoviePageSuccessfullyViaViewAllText() {
        if (homePage.isViewAllActive()) {
            moviePage = homePage.navigateToMoviePageByViewAllText();
        }
        Assert.assertTrue(homePage.isNavigateToMoviePage(), "Cannot navigate to movie page");
    }


    // ===== DISPLAY =====


    // ===== CLICK =====


    // ===== FILTER =====


    // ===== PAGINATION =====



}
