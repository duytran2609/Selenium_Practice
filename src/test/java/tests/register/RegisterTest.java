package tests.register;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ForgetPasswordPage;
import pages.LoginPage;
import pages.RegisterPage;
import driver.DriverManager;

public class RegisterTest extends BaseTest {

    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgetPasswordPage forgetPasswordPage;

    @BeforeMethod
    public void setUpRegisterTest() {
        log.info("Open login page");
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage();
        loginPage.register();
        registerPage = new RegisterPage();
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
    public void shouldRegisterSuccessfullyWhenInputIsValid() {
        registerPage.inputUsername("Nguyễn Đăng Duyyyyyy");
        registerPage.inputEmail("trandangduy1444444@gmail.com");
        registerPage.inputPassword("xanhlacay222222");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isRegisterSuccess(), "An error occured while registering");
    }

    @Test(dataProvider = "invalidUsernameData", dependsOnMethods = {"inputValidData"})
    public void shouldFailToRegisterWhenUsernameIsInvalid(String username) {
        registerPage.inputUsername(username);
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Boolean isValid = registerPage.isUsernameValid();
        Assert.assertFalse(isValid, "Username should be invalid by HTML5 rules");
    }

    @Test(dataProvider = "invalidEmailData")
    public void shouldFailToRegisterWhenEmailIsInvalid(String email) {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail(email);
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Boolean isValid = registerPage.isEmailValid();
        Assert.assertFalse(isValid, "Username should be invalid by HTML5 rules");
    }

    @Test(dataProvider = "invalidPasswordData")
    public void shouldFailToRegisterWhenPasswordIsInvalid(String password) {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword(password);
        registerPage.clickRegisterButton();
        Boolean isValid = registerPage.isPasswordValid();
        Assert.assertFalse(isValid, "Username should be invalid by HTML5 rules");
    }

    @Test
    public void shouldFailToRegisterWhenInputExistingUsername() {
        registerPage.inputUsername("Trần Đăng Duy");
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isExistingDataErrorDisplayed(), "Error must be displayed");
        Assert.assertEquals(registerPage.getExistingDataErrorMessage(), "Email hoặc username đã được sử dụng!", "Error message is not correct");
    }

    @Test
    public void shouldFailToRegisterWhenInputExistingEmail() {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail("trandangduy13@gmail.com");
        registerPage.inputPassword("xanhlacay3");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isExistingDataErrorDisplayed(), "Error must be displayed");
        Assert.assertEquals(registerPage.getExistingDataErrorMessage(), "Email hoặc username đã được sử dụng!", "Error message is not correct");
    }

    @Test
    public void shouldFailToRegisterWhenInputExistingPassword() {
        registerPage.inputUsername("Trần Minh Duy");
        registerPage.inputEmail("trandangduy15@gmail.com");
        registerPage.inputPassword("xanhlacay1");
        registerPage.clickRegisterButton();
        Assert.assertTrue(registerPage.isExistingDataErrorDisplayed(), "Error must be displayed");
        Assert.assertEquals(registerPage.getExistingDataErrorMessage(), "Email hoặc username đã được sử dụng!", "Error message is not correct");
    }

    @Test
    public void shouldNavigateToLoginPageSuccessfully() {
        registerPage.clickLoginLinkText();
        String currentURL = DriverManager.getDriver().getCurrentUrl();
        Assert.assertTrue(currentURL.contains("login"), "An error occured while navigating");
    }

}