package listeners;

import base.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        log.info("START TEST: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("PASS TEST: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("FAIL TEST: {}", result.getMethod().getMethodName());
        log.error("Reason: ", result.getThrowable());
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testClass;
            String screenshotPath = ScreenshotUtils.takeScreenshot(
                    baseTest.getDriver(),
                    result.getMethod().getMethodName()
            );
            log.error("Screenshot saved at: {}", screenshotPath);
            attachScreenshotToAllure(baseTest.getDriver());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("SKIP TEST: {}", result.getMethod().getMethodName());
    }

    @Attachment(value = "Screenshot on test failure", type = "image/png")
    public byte[] attachScreenshotToAllure(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
