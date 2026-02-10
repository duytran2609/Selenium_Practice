package pages;

import base.BasePage;
import components.HeaderComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MoviePage extends BasePage {

    public HeaderComponent headerComponent;
    public HomePage homePage;
    public MovieDetailsPage movieDetailsPage;

    private By txtTitleMoviePage = By.tagName("h1");
    private By iptMovie = By.xpath("//*[@id=\"root\"]/div/div/div[1]/input");

    private By crdMovies = By.className("movie-card");
    private By imgMoviePoster = By.cssSelector(".movie-poster");
    private By txtMovieTitle = By.cssSelector(".movie-title");
    private By txtMovieYear = By.cssSelector(".movie-meta");

    private By dropdownMovieType = By.cssSelector(".filter-select");

    private By btnClearFilter = By.cssSelector(".filter-reset");

    private By txtNoMovieFound = By.xpath("//*[@id=\"root\"]/div/div/div[2]/h2");

    private By btnPreviousPage = By.cssSelector(".pagination-prev");
    private By btnPageNumber = By.cssSelector(".pagination-number ");
    private By btnPageSelected = By.cssSelector(".pagination-number.active");
    private By btnNextPage = By.cssSelector(".pagination-next");
    private By iptPage = By.cssSelector(".pagination-goto-input");
    private By btnGo = By.cssSelector(".pagination-goto-btn");

    public MoviePage(WebDriver driver) {
        super(driver);
        headerComponent = new HeaderComponent(driver);
    }

    public boolean isMoviePageTitleDisplay() {
        return isDisplayed(txtTitleMoviePage);
    }

    private List<WebElement> getMovies() {
        wait.until(ExpectedConditions.presenceOfElementLocated(crdMovies));
        return driver.findElements(crdMovies);
    }

    public int getNumberOfMovies() {
        return getMovies().size();
    }

    private WebElement getFirstMovie() {
        return getMovies().get(0);
    }

    public boolean isMovieCardDisplay() {
        return getFirstMovie().isDisplayed();
    }

    public boolean isMoviePosterDisplay() {
        return getFirstMovie().findElement(imgMoviePoster).isDisplayed();
    }

    public boolean isMovieTitleDisplay() {
        return getFirstMovie().findElement(txtMovieTitle).isDisplayed();
    }

    public boolean isMovieYearDisplay() {
        return getFirstMovie().findElement(txtMovieYear).isDisplayed();
    }

    // ===== SEARCH MOVIE =====
    public void waitForMoviesReload() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(crdMovies),
                ExpectedConditions.presenceOfElementLocated(txtNoMovieFound)
        ));
    }

    public void searchMovie(String keyword) {
        type(iptMovie, keyword);
    }

    public String getInputText() {
        return find(iptMovie).getText();
    }

    public List<String> getAllMovieTitles() {
        waitForMoviesReload();
        if (driver.findElements(txtNoMovieFound).size() > 0) {
            return List.of();
        }
        return driver.findElements(crdMovies).stream()
                .map(movie -> movie.findElement(txtMovieTitle)
                        .getText()
                        .trim()
                        .toLowerCase())
                .toList();
    }

    public String getNoMovieFoundMessage() {
        return isDisplayed(txtNoMovieFound) ? find(txtNoMovieFound).getText() : "";
    }

    public MovieDetailsPage navigateToMovieDetailsPage() {
        click(crdMovies);
        return new MovieDetailsPage(driver);
    }

    public boolean isNavigateToMovieDetailsPage() {
        String hrefPart = driver.findElement(crdMovies).getAttribute("href");
        return wait.until(ExpectedConditions.urlContains(hrefPart));
    }

    // ===== MOVIE TYPE DROPDOWN =====
    public boolean isMovieTypeDropdownActive() {
        return isDisplayed(dropdownMovieType) && isEnabled(dropdownMovieType);
    }

    public String getFirstType() {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        return dropdown.getFirstSelectedOption().getText();
    }

    public List<String> getAllTypes() {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        List<WebElement> options = dropdown.getOptions();
        return options.stream()
                    .map(e -> e.getText().trim())
                    .toList();
    }

    public String getTypeDropdownOption(String type) {
        String optionSelected = "";
        click(dropdownMovieType);
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        List<WebElement> options = dropdown.getOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equals(type)) {
                option.click();
                optionSelected = option.getText();
            }
        }
        return optionSelected;
    }

    public void selectType(String type) {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        dropdown.selectByContainsVisibleText(type);
    }

    // ===== CLEAR MOVIE LIST FILTER =====
    public boolean isClearFilterButtonActive() {
        return isDisplayed(btnClearFilter) && isEnabled(btnClearFilter);
    }

    public void clickClearFilterButton() {
        click(btnClearFilter);
    }

    public String getMovieInput() {
        return find(iptMovie).getText();
    }

    public String getMovieTypeSelected() {
        Select dropdown = new Select(driver.findElement(dropdownMovieType));
        return dropdown.toString();
    }

    // ===== PAGINATION =====
    public boolean isPreviousPageButtonDisplay() {
        return isDisplayed(btnPreviousPage);
    }

    public boolean isPreviousPageButtonActive() {
        return isEnabled(btnPreviousPage);
    }

    public List<WebElement> pageNumberList() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(btnPageNumber));
    }

    public WebElement getFirstPage() {
        return pageNumberList().getFirst();
    }

    public String getPageSelected() {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElement(btnPageSelected).getText());
    }

    public void navigateToAnotherPage(String page) {
        By pageBtn = By.xpath("//button[contains(@class,'pagination-number') and text()='" + page + "']");
        WebElement oldPage = wait.until(ExpectedConditions.elementToBeClickable(pageBtn));
        oldPage.click();
        wait.until(ExpectedConditions.stalenessOf(oldPage));
        wait.until(ExpectedConditions.attributeContains(pageBtn, "class", "active"));
    }

    public void navigateToLastPage() {
        By pages = By.cssSelector(".pagination-number ");
        List<WebElement> allPages = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(pages));
        WebElement oldActive = driver.findElement(By.cssSelector(".pagination-number.active"));
        allPages.get(allPages.size()-1).click();
        wait.until(ExpectedConditions.stalenessOf(oldActive));
    }

    public void waitForMovieReload() {
        wait.until(ExpectedConditions.stalenessOf(
                driver.findElement(By.cssSelector(".movie-card"))
        ));
    }

    public void navigateToPreviousPage() {
        By activePage = By.cssSelector(".pagination-number.active");
        WebElement oldActive = driver.findElement(activePage);
        wait.until(ExpectedConditions.elementToBeClickable(btnPreviousPage)).click();
        wait.until(ExpectedConditions.stalenessOf(oldActive));
        wait.until(ExpectedConditions.presenceOfElementLocated(activePage));
    }

    public void navigateToNextPage() {
        By activePage = By.cssSelector(".pagination-number.active");
        WebElement oldActive = driver.findElement(activePage);
        wait.until(ExpectedConditions.elementToBeClickable(btnNextPage)).click();
        wait.until(ExpectedConditions.stalenessOf(oldActive));
        wait.until(ExpectedConditions.presenceOfElementLocated(activePage));
    }


    public boolean isNextPageButtonDisplay() {
        return isDisplayed(btnNextPage);
    }

    public boolean isNextPagePageButtonActive() {
        return isEnabled(btnNextPage);
    }

    public void inputPageToNavigate(String page) {
//        List<String> pageNumberList = pageNumberList().stream()
//                .map(t -> t.getText().trim())
//                .toList();
//        int firstIndex = Integer.parseInt(pageNumberList.getFirst());
//        int lastIndex = Integer.parseInt(pageNumberList.getLast());
        type(iptPage, page);
        click(btnGo);
    }

    public String getInputPageText() {
        return driver.findElement(iptPage).getAttribute("value");
    }

}

