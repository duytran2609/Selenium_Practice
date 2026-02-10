package tests.login;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ForgetPasswordPage;
import pages.LoginPage;
import tests.driver.DriverManager;

public class ForgetPasswordTest extends BaseTest {

    private LoginPage loginPage;
    private ForgetPasswordPage forgetPasswordPage;

    @BeforeMethod
    public void setUpForgetPasswordTest() {
        log.info("Open login page");
        DriverManager.getDriver().get("https://movie-project-front-end.vercel.app/login");
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.forgetPassword();
        forgetPasswordPage = new ForgetPasswordPage(DriverManager.getDriver());
    }

    @DataProvider(name = "emailData")
    public Object[][] emailData() {
        return new Object[][] {
                {"trandangduy13@gmail.com"},
                {"abc@gmail.com"},
                {"abc"},
                {""},
        };
    }

    @Test
    public void shouldResetPasswordSuccessfullyWhenEmailIsValid() {
        forgetPasswordPage.inputEmail("trandangduy13@gmail.com");
        forgetPasswordPage.clickSendButton();
        Assert.assertTrue(forgetPasswordPage.isSendSuccess(), "Submit button vẫn còn hiển thị, request có thể chưa gửi thành công");
    }

    @Test(dataProvider = "emailData", dependsOnMethods = {"testInputValidEmail"})
    public void shouldFailToResetPasswordWhenEmailIsInvalid(String email) {
        forgetPasswordPage.inputEmail(email);
        forgetPasswordPage.clickSendButton();
        Assert.assertTrue(forgetPasswordPage.isSendSuccess(), "Submit button vẫn còn hiển thị, request có thể chưa gửi thành công");
    }
}
