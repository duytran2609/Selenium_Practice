package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class LoginPage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private HomePage homePage;

    // Khai báo locators
    private By inputEmail = By.id("email");
    private By inputPassword = By.id("password");
    private By btnLogin = By.cssSelector(".login-btn");
    private By formLogin = By.cssSelector(".login-box");
    private By txtForgetPass = By.linkText("Quên mật khẩu?");


    // Khởi tạo constructor có tham số
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Khai báo các hành động
    public HomePage login(String email, String password) {
        try {
            type(inputEmail, email);
            type(inputPassword, password);
            click(btnLogin);
        } catch (Exception e) {
            log.error("Login fail because: ", e);
        }
        return new HomePage(driver);
    }

    // Khai báo logic
    public boolean isLoginSuccess() {
        return wait.until(ExpectedConditions.urlMatches("https://movie-project-front-end.vercel.app/"));
    }

    public boolean isLoginFailed() {
        return wait.until(ExpectedConditions.urlMatches("https://movie-project-front-end.vercel.app/login"));
    }

    // Khai báo UI component
    public boolean isLoginFormDisplayed() {
        return isDisplayed(formLogin);
    }

    public boolean isEmailInputDisplayed() {
        return isDisplayed(inputEmail);
    }

    public boolean isPasswordInputDisplayed() {
        return isDisplayed(inputPassword);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(btnLogin);
    }
}
