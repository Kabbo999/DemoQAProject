// java
package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static void waitForElementVisible(By locator, int timeoutSeconds) {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void clickWhenReady(By locator, int timeoutSeconds) {
        WebDriver driver = DriverFactory.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}