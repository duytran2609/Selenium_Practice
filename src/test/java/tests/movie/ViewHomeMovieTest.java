package tests.movie;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import tests.driver.DriverManager;

public class ViewHomeMovieTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUpGetHomeMovieTest() {
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
    }

    @Test
    public void shouldGetMovieNumber() {
        int totalMovie = homePage.getNumberOfMovies();
        Assert.assertEquals(totalMovie, 1, "Số movie không đủ");
    }

    @Test()
    public void shouldDisplayMovieCardSuccessfully() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isMovieCardDisplay(), "Movie card did not display");
        softAssert.assertTrue(homePage.isMoviePosterDisplay(), "Movie poster did not display");
        softAssert.assertTrue(homePage.isMovieTitleDisplay(),  "Movie title did not display");
        softAssert.assertTrue(homePage.isMovieYearDisplay(), "Movie year did not display");
        softAssert.assertAll();
    }

}
