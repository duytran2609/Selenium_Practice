package tests.movie;

import api.MovieAPI;
import base.BaseTest;
import components.HeaderComponent;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MoviePage;

import java.util.List;

public class GetMovieByCategory extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;
    private HeaderComponent headerComponent;

    @BeforeMethod
    public void setUpGetMovieList() {
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(driver);
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        moviePage = homePage.headerComponent.navigateToMoviePage();
    }

    @DataProvider(name = "dropdownData")
    public Object[][] categoryData() {
        return new Object[][] {
                {null, "Tất cả loại"},
                {"movie", "Phim lẻ"},
                {"series", "Phim bộ"}
        };
    }

    @Test
    public void testDisplayMovieCategoryDropdown() {
        moviePage = new MoviePage(driver);
        Assert.assertTrue(moviePage.isMovieTypeDropdownActive(), "Movie category dropdown is not active");
    }

    @Test
    public void testSelectedDropdownText(String category) {
        String optionSelected = moviePage.getTypeDropdownOption(category);
        Assert.assertEquals(optionSelected, category, "Not true");
    }

    @Test(dataProvider = "dropdownData")
    public void testFilterMovieCategory(String apiType, String uiType) {
        MovieAPI movieAPI = new MovieAPI();
        Response response = movieAPI.getMoviesByType(apiType);
        List<String> getApiTitles = response.then()
                .statusCode(200)
                .extract()
                .path("movies.title");
        List<String> apiTitles = getApiTitles.stream()
                .map(t -> t.trim().toLowerCase())
                .toList();
        moviePage.selectType(uiType);
        moviePage.waitForMoviesReload();
        List<String> uiTitles = moviePage.getAllMovieTitles();
        Assert.assertTrue(uiTitles.containsAll(apiTitles));
    }

}

