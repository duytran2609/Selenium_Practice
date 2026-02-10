package tests.movie;

import api.MovieAPI;
import base.BaseTest;
import components.HeaderComponent;
import io.restassured.response.Response;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.LoginPage;
import pages.MoviePage;
import tests.driver.DriverManager;

import java.time.Duration;
import java.util.List;

public class MovieListPaginationTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private MoviePage moviePage;
    private HeaderComponent headerComponent;

    @BeforeMethod
    public void setUpClearMovieListFilterTest() {
        WebDriver driver = DriverManager.getDriver();
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        homePage = loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        moviePage = homePage.headerComponent.navigateToMoviePage();
    }

    @DataProvider(name = "pageNumberData", parallel = false)
    public Object[][] pageNumberData() {
        return new Object[][] {
                {"2"},
                {"3"}
        };
    }

    @DataProvider(name = "invalidPageInputData", parallel = false)
    public Object[][] invalidPageInputData() {
        return new Object[][] {
                {"abc"},
                {"@#$"},
                {" "},
                {""}
        };
    }

    @Test()
    public void shouldDisplayFirstPageAtFirst() {
        System.out.println("Trang đang được chọn: " + moviePage.getPageSelected());
        if (moviePage.getPageSelected().equals("1"))
        {
            Assert.assertTrue(!moviePage.isPreviousPageButtonActive(), "Still can naviagte to previous page");
        }
    }

    @Test(dataProvider = "pageNumberData")
    public void shouldNavigateBetweenPagesSuccessfully(String page) {
        MovieAPI movieAPI = new MovieAPI();
        Response response = movieAPI.getMoviesByPage(page);
        int pageSelectedIndex = response.jsonPath().getInt("currentPage");
        String currentPage = String.valueOf(pageSelectedIndex);
        moviePage.navigateToAnotherPage(page);
        Assert.assertEquals(moviePage.getPageSelected(), currentPage, "Current page is not equal");
    }

    @Test(dataProvider = "pageNumberData")
    public void shouldNavigateToPreviousPageSuccessfully(String page) {
        moviePage.navigateToAnotherPage(page);
        String uiCurrentPage = moviePage.getPageSelected();
        moviePage.navigateToPreviousPage();
        String uiNewPage = moviePage.getPageSelected();
        MovieAPI movieAPI = new MovieAPI();
        Response response = movieAPI.getMoviesByPage(uiNewPage);
        int pageSelectedIndex = response.jsonPath().getInt("currentPage");
        String apiNewPage = String.valueOf(pageSelectedIndex);
        Assert.assertTrue(Integer.parseInt(uiNewPage) < Integer.parseInt(uiCurrentPage), "Error");
        Assert.assertEquals(uiNewPage, apiNewPage, "Current page is not equal");
    }

    @Test(dataProvider = "pageNumberData")
    public void shouldNavigateToNextPageSuccessfully(String page) {
        moviePage.navigateToAnotherPage(page);
        String uiCurrentPage = moviePage.getPageSelected();
        moviePage.navigateToNextPage();
        String uiNewPage = moviePage.getPageSelected();
        MovieAPI movieAPI = new MovieAPI();
        Response response = movieAPI.getMoviesByPage(uiNewPage);
        int pageSelectedIndex = response.jsonPath().getInt("currentPage");
        String apiNewPage = String.valueOf(pageSelectedIndex);
        Assert.assertTrue(Integer.parseInt(uiNewPage) > Integer.parseInt(uiCurrentPage), "Error");
        Assert.assertEquals(uiNewPage, apiNewPage, "Current page is not equal");
    }

    @Test()
    public void shouldNavigateToLastPageSuccessfully() {
        List<String> pageNumberList = moviePage.pageNumberList().stream()
                                                                .map(t -> t.getText().trim())
                                                                .toList();
        String uiLastPage = pageNumberList.getLast();
        moviePage.navigateToLastPage();
        MovieAPI movieAPI = new MovieAPI();
        Response response = movieAPI.getMoviesByPage(uiLastPage);
        int totalPages = response.jsonPath().getInt("totalPages");
        String apiLastPage = String.valueOf(totalPages);
        Assert.assertTrue(!moviePage.isNextPagePageButtonActive(), "Still can navigate to next page");
        Assert.assertEquals(uiLastPage, moviePage.getPageSelected(), "Current page is not equal");
        Assert.assertEquals(uiLastPage, apiLastPage, "Current page is not equal");
    }

    @Test
    public void shouldNavigateToAnotherPageSuccessfullyWhenInputIsValid() {
        List<String> pageNumberList = moviePage.pageNumberList().stream()
                .map(t -> t.getText().trim())
                .toList();
        int firstPage = Integer.parseInt(pageNumberList.getFirst());
        int lastPage = Integer.parseInt(pageNumberList.getLast());
        int middlePage = lastPage / 2;
        int[] pages = {firstPage, middlePage, lastPage};
        for (int p : pages) {
            moviePage.inputPageToNavigate(String.valueOf(p));
            Assert.assertEquals(Integer.parseInt(moviePage.getPageSelected()), p, "Current page is not equal");
        }
    }

    @Test
    public void shouldFailToNavigateToAnotherPageWhenInputIsOutOfRange() {
        List<String> pageNumberList = moviePage.pageNumberList().stream()
                .map(t -> t.getText().trim())
                .toList();
        int firstPage = 0;
        int lastPage = Integer.parseInt(pageNumberList.getLast()) + 1;
        int totalPage = Integer.parseInt(pageNumberList.getLast());
        int[] pages = {firstPage, lastPage};
        for (int p : pages) {
            MovieAPI movieAPI = new MovieAPI();
            Response response = movieAPI.getMoviesByPage(String.valueOf(p));
            int pageSelectedIndex = response.jsonPath().getInt("currentPage");
            moviePage.inputPageToNavigate(String.valueOf(p));

            if (p == firstPage) {
                Alert alert = new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(5)
                ).until(ExpectedConditions.alertIsPresent());
                String alertText = alert.getText();
                Assert.assertEquals(alertText, "Số trang phải lớn hơn 0!", "Current alert text is not equal");
                alert.accept();
                Assert.assertEquals(Integer.parseInt(moviePage.getPageSelected()), pageSelectedIndex, "Currentv b  page is not equal");

            }
            else if (p == lastPage) {
                Alert alert = new WebDriverWait(
                        DriverManager.getDriver(),
                        Duration.ofSeconds(5)
                ).until(ExpectedConditions.alertIsPresent());
                String alertText = alert.getText();
                String expectedText = String.format("Số trang không được vượt quá %d!", totalPage);
                Assert.assertEquals(alertText, expectedText, "Current alert text is not equal");
                alert.accept();
                Assert.assertEquals(Integer.parseInt(moviePage.getPageSelected()), pageSelectedIndex, "Current page is not equal");
            }
        }
    }

    @Test(dataProvider = "invalidPageInputData")
    public void shouldFailToNavigateToAnotherPageWhenInputIsInvalid(String page) {
        List<String> pageNumberList = moviePage.pageNumberList().stream()
                .map(t -> t.getText().trim())
                .toList();
        MovieAPI movieAPI = new MovieAPI();
        Response response = movieAPI.getMoviesByPage(page);
        if (page.equals("abc") || page.equals("@#$") || page.equals(" ")) {
            Assert.assertTrue(moviePage.getInputPageText().isEmpty(), "Still can input character");
        }
        else if (page.equals("")) {
            moviePage.inputPageToNavigate(page);
            Alert alert = DriverManager.getDriver().switchTo().alert();
            String alertText = alert.getText();
            Assert.assertEquals(alertText, "Vui lòng nhập số trang!", "Current alert text is not equal");
        }
    }

    @Test
    public void shouldDisplayMovieListSuccessfullyAfterNavigatingToAnotherPage() {
        List<String> firstMovieTitleList = moviePage.getAllMovieTitles();
        String firstMovieTitle = firstMovieTitleList.getFirst();
        System.out.println("Danh sách tiêu đề phim ban đầu: " + firstMovieTitleList);
        System.out.println("Têu đề phim ban đầu: " + firstMovieTitle);
        moviePage.navigateToAnotherPage("2");
        List<String> nextMovieTitleList = moviePage.getAllMovieTitles();
        String nextMovieTitle = nextMovieTitleList.getFirst();
        System.out.println("Danh sách tiêu đề phim sau đó: " + nextMovieTitleList);
        System.out.println("Têu đề phim sau đó: " + nextMovieTitle);
        Assert.assertNotEquals(firstMovieTitle, nextMovieTitle);
    }
}
