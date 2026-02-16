package tests.movie;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import driver.DriverManager;

public class ViewHomeMovieTest extends BaseTest {
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setUpGetHomeMovieTest() {
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage();
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
    }

    @Test()
    public void shouldDisplayMovieCardSuccessfully() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(homePage.isMovieCardDisplay(), "Movie card does not display");
        softAssert.assertTrue(homePage.isMoviePosterDisplay(), "Movie poster does not display");
        softAssert.assertTrue(homePage.isMovieTitleDisplay(),  "Movie title does not display");
        softAssert.assertTrue(homePage.isMovieYearDisplay(), "Movie year does not display");
        softAssert.assertAll();
    }

}
