package tests.movie;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MoviePage;
import tests.driver.DriverManager;

import java.util.List;

public class SearchMovieTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;

    @BeforeMethod
    public void setUpSearchMovieTest() {
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        moviePage = homePage.headerComponent.navigateToMoviePage();
    }

    @Test
    public void shouldSearchMovieSuccessfullyWhenKeywordIsValid() {
        String keyword = "lego";
        moviePage.searchMovie(keyword);
        List<String> titles = moviePage.getAllMovieTitles();
        Assert.assertTrue(titles.stream().anyMatch(t -> t.contains(keyword))
                , "No movie includes this keyword"
        );
    }

    @Test
    public void shouldFailToSearchMovieWhenKeywordIsInvalid() {
        String keyword = "dsbdbjsajdkjdbsj";
        moviePage.searchMovie(keyword);
        Assert.assertEquals(moviePage.getNoMovieFoundMessage(), "Movie not found"
        );
    }

    @Test
    public void shouldSearchMovieWhenKeywordHasLeadingWhiteSpace() {
        String keyword = " leg";
        moviePage.searchMovie(keyword);
        Assert.assertTrue(moviePage.getAllMovieTitles().size() > 0
                , "Search with whitt space did not find any movies"
        );
    }


}
