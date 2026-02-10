package tests.register;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ForgetPasswordPage;
import pages.LoginPage;
import pages.RegisterPage;
import tests.driver.DriverManager;

public class RegisterTest extends BaseTest {

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgetPasswordPage forgetPasswordPage;

    @BeforeMethod
    public void setUpFlow() {
        log.info("Open login page");
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.register();
        registerPage = new RegisterPage(DriverManager.getDriver());
    }

    @DataProvider(name = "invalidUsernameData")
    public Object[][] invalidUsernameData() {
        return new Object[][] {
                {""},
        };
    }

    @DataProvider(name = "invalidEmailData")
    public Object[][] invalidEmailData() {
        return new Object[][] {
                {""},
                {"abc"},
                {"a bc@gmail.com"}
        };
    }

    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() {
        return new Object[][] {
                {""},
        };
    }

    @Test
    public void inputValidData() {
        registerPage.inputUsername("Nguyễn Đăng Duyyyyyy");
        registerPage.inputEmail("trandangduy1444444@gmail.com");
        registerPage.inputPassword("xanhlacay222222");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isRegisterSuccess(), "An error occured while registering");
    }

    @Test(dataProvider = "invalidUsernameData", dependsOnMethods = {"inputValidData"})
    public void inputInvalidUsername(String username) {
        registerPage.inputUsername(username);
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Boolean isValid = registerPage.isUsernameValid();
        Assert.assertFalse(isValid, "Username should be invalid by HTML5 rules");
    }

    @Test(dataProvider = "invalidEmailData")
    public void inputInvalidEmail(String email) {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail(email);
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Boolean isValid = registerPage.isEmailValid();
        Assert.assertFalse(isValid, "Username should be invalid by HTML5 rules");
    }

    @Test(dataProvider = "invalidPasswordData")
    public void inputInvalidPassword(String password) {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword(password);
        registerPage.clickRegisterButton();
        Boolean isValid = registerPage.isPasswordValid();
        Assert.assertFalse(isValid, "Username should be invalid by HTML5 rules");
    }

    @Test
    public void inputExistingUsername() {
        registerPage.inputUsername("Trần Đăng Duy");
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isExistingDataErrorDisplayed(), "Error must be displayed");
        Assert.assertEquals(registerPage.getExistingDataErrorMessage(), "Email hoặc username đã được sử dụng!", "Error message is not correct");
    }

    @Test
    public void inputExistingEmail() {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail("trandangduy13@gmail.com");
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isExistingDataErrorDisplayed(), "Error must be displayed");
        Assert.assertEquals(registerPage.getExistingDataErrorMessage(), "Email hoặc username đã được sử dụng!", "Error message is not correct");
    }

    @Test
    public void inputExistingPassword() {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword("xanhlacay1");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isExistingDataErrorDisplayed(), "Error must be displayed");
        Assert.assertEquals(registerPage.getExistingDataErrorMessage(), "Email hoặc username đã được sử dụng!", "Error message is not correct");
    }

    @Test
    public void navigateToLoginPage() {
        registerPage.clickLoginLinkText();
        String currentURL = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentURL.contains("login"), "An error occured while navigating");
    }

}