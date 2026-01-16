package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage {

    private LoginPage loginPage;

    By inputUsername = By.id("username");
    By inputEmail = By.id("email");
    By inputPassword = By.id("password");
    By btnRegister = By.className("register-btn");
    By txtLogin = By.xpath("//*[@id=\"root\"]/div/div/div/div/p/a");
    By msgError = By.className("error-message");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void inputUsername(String username) {
        log.trace("Input username: [{}]", username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername)).clear();
        driver.findElement(inputUsername).sendKeys(username);
    }

    public void inputEmail(String email) {
        log.trace("Input email: [{}]", email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail)).clear();
        driver.findElement(inputEmail).sendKeys(email);
    }

    public void inputPassword(String password) {
        log.trace("Input password: [{}]", password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword)).clear();
        driver.findElement(inputPassword).sendKeys(password);
    }

    public void clickRegisterButton() {
        driver.findElement(btnRegister).click();
    }

    public void clickLoginLinkText() {
        driver.findElement(txtLogin).click();
    }

    public boolean isRegisterSuccess() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(btnRegister));
    }

    public boolean isUsernameValid() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement usernameElement = driver.findElement(inputUsername);
        return (Boolean) js.executeScript("return arguments[0].checkValidity();", usernameElement);
    }

    public boolean isEmailValid() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement emailElement = driver.findElement(inputEmail);
        return (Boolean) js.executeScript("return arguments[0].checkValidity();", emailElement);
    }

    public boolean isPasswordValid() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement passwordElement = driver.findElement(inputPassword);
        return (Boolean) js.executeScript("return arguments[0].checkValidity();", passwordElement);
    }

    public boolean isExistingDataErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(msgError)).isDisplayed();
    }

    public String getExistingDataErrorMessage() {
        return driver.findElement(msgError).getText();
    }

    public boolean isBackToLoginSuccess() {
        return wait.until(ExpectedConditions.urlContains("login"));
    }
}
