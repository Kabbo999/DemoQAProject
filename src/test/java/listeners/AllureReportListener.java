package listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.DriverFactory;

public class AllureReportListener implements ITestListener {

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("===== Test Suite Started: " + context.getName() + " =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("===== Test Suite Finished: " + context.getName() + " =====");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("STARTED: " + getTestMethodName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("PASSED: " + getTestMethodName(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("FAILED: " + getTestMethodName(result));

        try {

            WebDriver driver = DriverFactory.getDriver();

            if (driver != null) {

                System.out.println("Capturing screenshot for: "
                        + getTestMethodName(result));

                saveScreenshotPNG(driver);
            }

        } catch (Exception e) {

            System.out.println("Unable to capture screenshot: "
                    + e.getMessage());
        }

        saveTextLog(
                getTestMethodName(result)
                        + " failed and screenshot was captured."
        );
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("SKIPPED: "
                + getTestMethodName(result));

        saveTextLog(
                getTestMethodName(result)
                        + " was skipped."
        );
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(
            ITestResult result) {

        System.out.println(
                "FAILED WITHIN SUCCESS PERCENTAGE: "
                        + getTestMethodName(result));
    }
}