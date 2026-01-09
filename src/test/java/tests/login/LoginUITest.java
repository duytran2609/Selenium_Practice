package tests.login;

import base.BaseTest;
import org.openqa.selenium.bidi.log.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginUITest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpLogin() {
        driver.get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(driver);
    }

    @Test(groups = {"UI"})
    public void verifyPageUI() {
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form doesn't appear");
        Assert.assertTrue(loginPage.isEmailInputDisplayed(), "Email input doesn't appear");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password input doesn't appear");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button doesn't work");
    }
}
