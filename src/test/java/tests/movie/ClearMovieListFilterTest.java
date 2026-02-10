package tests.movie;

import base.BaseTest;
import components.HeaderComponent;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.MoviePage;
import tests.driver.DriverManager;

public class ClearMovieListFilterTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;
    private HeaderComponent headerComponent;

    @BeforeMethod
    public void setUpClearMovieListFilterTest() {
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        moviePage = homePage.headerComponent.navigateToMoviePage();
    }

    @Test
    public void shouldClearFilterAfterSearchingMovie() {
        int defaultSize = moviePage.getNumberOfMovies();
        System.out.println("Number of movies at the beginning: " + defaultSize);
        String keyword = "lego";
        moviePage.searchMovie(keyword);
        int sizeAfterSearch = moviePage.getNumberOfMovies();
        System.out.println("Number of movies after search: " + sizeAfterSearch);
        moviePage.clickClearFilterButton();
        moviePage.waitForMoviesReload();
        int sizeAfterClearFilter = moviePage.getNumberOfMovies();
        System.out.println("Number of movies after clear filter: " + sizeAfterClearFilter);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(moviePage.getInputText().isEmpty(), "Input still had keyword");
        softAssert.assertEquals(sizeAfterClearFilter, defaultSize, "Two lists were not equal in size");
        softAssert.assertAll();
    }

    @Test
    public void shouldClearFilterAfterSelectingMovieType() {
        int defaultSize = moviePage.getNumberOfMovies();
        String defaultOption = moviePage.getFirstType();
        System.out.println("Number of movies at the beginning: " + defaultSize);
        moviePage.selectType("Phim bộ");
        int sizeAfterApplyType = moviePage.getNumberOfMovies();
        System.out.println("Number of movies after choose movie type: " + sizeAfterApplyType);
        moviePage.clickClearFilterButton();
        moviePage.waitForMoviesReload();
        int sizeAfterClearFilter = moviePage.getNumberOfMovies();
        System.out.println("Number of movies after clear filter: " + sizeAfterClearFilter);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(defaultOption, "Tất cả loại", "The dropdown did not display default option");
        softAssert.assertEquals(sizeAfterClearFilter, defaultSize, "Two lists were not equal in size");
        softAssert.assertAll();
    }

    @Test
    public void shouldClearFilterSuccessfullyAfterSearchingMovieAndSelectingMovieType() {
        int defaultSize = moviePage.getNumberOfMovies();
        String defaultOption = moviePage.getFirstType();
        System.out.println("Number of movies at the beginning: " + defaultSize);
        String keyword = "lego";
        moviePage.searchMovie(keyword);
        moviePage.selectType("Phim bộ");
        moviePage.clickClearFilterButton();
        moviePage.waitForMoviesReload();
        int sizeAfterClearFilter = moviePage.getNumberOfMovies();
        System.out.println("Number of movies after clear filter: " + sizeAfterClearFilter);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(moviePage.getInputText().isEmpty(), "Input still had keyword");
        softAssert.assertEquals(defaultOption, "Tất cả loại", "The dropdown did not display default option");
        softAssert.assertEquals(sizeAfterClearFilter, defaultSize, "Two lists were not equal in size");
        softAssert.assertAll();
    }

    @Test
    public void shouldClearFilterWhenNoKeywordOrMovieTypeSelected() {
        int defaultSize = moviePage.getNumberOfMovies();
        System.out.println("Number of movies at the beginning: " + defaultSize);
        moviePage.clickClearFilterButton();
        moviePage.waitForMoviesReload();
        int sizeAfterClearFilter = moviePage.getNumberOfMovies();
        System.out.println("Number of movies after clear filter: " + sizeAfterClearFilter);
        Assert.assertEquals(sizeAfterClearFilter, defaultSize, "Two lists were not equal in size");
    }
}
