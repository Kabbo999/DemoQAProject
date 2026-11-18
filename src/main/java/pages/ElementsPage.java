
package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public ElementsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    private By elementsMenu = By.xpath("//h5[text()='Elements']");
    private By textBoxMenu = By.xpath("//span[text()='Text Box']");

    private By fullNameField = By.id("userName");
    private By emailField = By.id("userEmail");
    private By currentAddressField = By.id("currentAddress");
    private By permanentAddressField = By.id("permanentAddress");
    private By submitButton = By.id("submit");

    // Output locators
    private By outputName = By.id("name");
    private By outputEmail = By.id("email");
    private By outputCurrentAddress =
            By.xpath("//p[@id='currentAddress']");
    private By outputPermanentAddress =
            By.xpath("//p[@id='permanentAddress']");

    // Email validation error
    private By emailError =
            By.cssSelector("#userEmail.field-error");

    // Wait for element visibility
    private WebElement waitForElement(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    // Wait for element clickable
    private WebElement waitForClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    // Navigate to Elements
    public void clickOnElements() {

        WebElement element = waitForElement(elementsMenu);

        // Scroll Elements menu into center of screen
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        // Wait until element is clickable
        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        );

        // JavaScript click
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    // Navigate to Text Box
    public void clickOnTextBox() {

        WebElement element = waitForClickable(textBoxMenu);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    // Enter Full Name
    public void enterFullName(String name) {
        WebElement element = waitForElement(fullNameField);
        element.clear();
        element.sendKeys(name);
    }

    // Enter Email
    public void enterEmail(String email) {
        WebElement element = waitForElement(emailField);
        element.clear();
        element.sendKeys(email);
    }

    // Enter Current Address
    public void enterCurrentAddress(String address) {
        WebElement element = waitForElement(currentAddressField);
        element.clear();
        element.sendKeys(address);
    }

    // Enter Permanent Address
    public void enterPermanentAddress(String address) {
        WebElement element = waitForElement(permanentAddressField);
        element.clear();
        element.sendKeys(address);
    }

    // Click Submit Button
    public void clickSubmit() {
        WebElement element = waitForClickable(submitButton);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    // Get Full Name Output
    public String getOutputName() {
        return waitForElement(outputName).getText();
    }

    // Get Email Output
    public String getOutputEmail() {
        return waitForElement(outputEmail).getText();
    }

    // Get Current Address Output
    public String getOutputCurrentAddress() {
        return waitForElement(outputCurrentAddress).getText();
    }

    // Get Permanent Address Output
    public String getOutputPermanentAddress() {
        return waitForElement(outputPermanentAddress).getText();
    }

    // Compatibility method for original ElementsTest
    public String getOutput() {
        return getOutputName();
    }

    // Compatibility method for original ElementsTest
    public String getCurrentAddressOutput() {
        return getOutputCurrentAddress();
    }

    // Check if Full Name field is visible
    public boolean isFullNameVisible() {
        return waitForElement(fullNameField).isDisplayed();
    }

    // Check if Email field is visible
    public boolean isEmailVisible() {
        return waitForElement(emailField).isDisplayed();
    }

    // Check if Current Address field is visible
    public boolean isCurrentAddressVisible() {
        return waitForElement(currentAddressField).isDisplayed();
    }

    // Check if Permanent Address field is visible
    public boolean isPermanentAddressVisible() {
        return waitForElement(permanentAddressField).isDisplayed();
    }

    // Check if Submit button is visible
    public boolean isSubmitVisible() {
        return waitForElement(submitButton).isDisplayed();
    }

    // Check if output is displayed
    public boolean isOutputDisplayed(By locator) {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Check if Email Error is displayed
    public boolean isEmailErrorDisplayed() {
        try {
            return driver.findElement(emailError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Check if Email field has invalid CSS class
    public boolean hasEmailErrorClass() {
        String classAttribute =
                waitForElement(emailField).getAttribute("class");

        return classAttribute != null &&
                classAttribute.contains("field-error");
    }

    // Get Email field class attribute
    public String getEmailFieldClass() {
        return waitForElement(emailField).getAttribute("class");
    }

    // Clear all fields
    public void clearAllFields() {
        waitForElement(fullNameField).clear();
        waitForElement(emailField).clear();
        waitForElement(currentAddressField).clear();
        waitForElement(permanentAddressField).clear();
    }

    // Scroll to Submit Button
    public void scrollToSubmitButton() {
        WebElement element = waitForElement(submitButton);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", element
        );
    }
}
