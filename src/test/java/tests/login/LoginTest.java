package tests.login;

import base.BaseTest;
import jdk.jfr.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import tests.driver.DriverManager;

public class LoginTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class); // Luôn kế thừa class BaseTest

    private LoginPage loginPage; // Nhớ tạo object page

    // Mở trang
    @BeforeMethod
    public void setUpLoginTest() {
        log.info("Open login page");
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
    }

    // Khởi tạo bộ data
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"abc", "123", "Invalid email or password"},
                {"", "xanhlacay1", "Email can't be null"},
                {"trandangduy13@gmail.com", "", "Password can't be null"},
        };
    }

    // Khởi tạo các test cases
    @Test(groups = {"UI"})
    public void testPageUI() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginPage.isLoginFormDisplayed(), "Login form doesn't appear");
        softAssert.assertTrue(loginPage.isEmailInputDisplayed(), "Email input doesn't appear");
        softAssert.assertTrue(loginPage.isPasswordInputDisplayed(), "Password input doesn't appear");
        softAssert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button doesn't work");
        softAssert.assertAll();
    }

    // Test flow logic
    @Test(priority = 1, groups = {"function"})
    @Description("Test login với tài khoản hợp lệ")
    public void shouldLoginSuccessfullyWhenCredentialsAreValid() {
        log.info("Open with valid account");
        loginPage.login("trandangduy13@gmail.com", "xanhlacay1");
        Assert.assertTrue(loginPage.isLoginSuccess(), "Đăng nhập thất bại");
    }

    @Test(priority = 2, groups = {"function"}, dataProvider = "invalidLoginData")
    @Description("Test login với tài khoản không hợp lệ")
    public void shouldFailToLoginWhenCredentialsAreInvalid(String email, String password, String errorMessage) {
        log.info("Login with invalid account: email = [{}], password = [{}]", email, password);
        loginPage.login(email, password);
        Assert.assertFalse(loginPage.isLoginFailed(), errorMessage);
    }

}
