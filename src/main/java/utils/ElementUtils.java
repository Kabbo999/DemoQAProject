package utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

    private WebDriver driver;

    public ElementUtils(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By locator, int timeOut) {
        return waitForElementVisible(locator, timeOut);
    }

    public List<WebElement> getElements(By locator) {
        return driver.findElements(locator);
    }

    public void doSendKeys(By locator, String value, int timeout) {
        WebElement element = getElement(locator, timeout);
        element.clear();
        element.sendKeys(value);
    }

    public void doActionsSendKeys(By locator, String value, int timeout) {
        Actions act = new Actions(driver);
        act.sendKeys(getElement(locator, timeout), value).build().perform();
    }

    public void doClick(By locator, int timeout) {
        getElement(locator, timeout).click();
    }

    public void doActionsClick(By locator, int timeout) {
        Actions act = new Actions(driver);
        act.click(getElement(locator, timeout)).build().perform();
    }

    public String doElementGetText(By locator, int timeout) {
        return getElement(locator, timeout).getText();
    }

    public boolean doElementIsDisplayed(By locator, int timeout) {
        return getElement(locator, timeout).isDisplayed();
    }

    public String getElementAttribute(By locator, String attrName, int timeout) {
        return getElement(locator, timeout).getAttribute(attrName);
    }

    public void getElementAttributes(By locator, String attrName) {
        List<WebElement> eleList = getElements(locator);

        for (WebElement e : eleList) {
            System.out.println(e.getAttribute(attrName));
        }
    }

    public int getTotalElementsCount(By locator) {
        return getElements(locator).size();
    }

    public List<String> getElementsTextList(By locator) {

        List<String> eleTextList = new ArrayList<>();

        for (WebElement e : getElements(locator)) {
            eleTextList.add(e.getText());
        }

        return eleTextList;
    }

    // ================= Dropdown Utils =================

    public void doSelectDropDownByIndex(By locator, int index, int timeout) {

        Select select = new Select(getElement(locator, timeout));
        select.selectByIndex(index);
    }

    public void doSelectDropDownByValue(By locator, String value, int timeout) {

        Select select = new Select(getElement(locator, timeout));
        select.selectByValue(value);
    }

    public void doSelectDropDownByVisibleText(By locator, String text, int timeout) {

        Select select = new Select(getElement(locator, timeout));
        select.selectByVisibleText(text);
    }

    public List<WebElement> getDropDownOptionsList(By locator, int timeout) {

        Select select = new Select(getElement(locator, timeout));
        return select.getOptions();
    }

    public List<String> getDropDownOptionsTextList(By locator, int timeout) {

        List<String> optionsTextList = new ArrayList<>();

        for (WebElement e : getDropDownOptionsList(locator, timeout)) {
            optionsTextList.add(e.getText());
        }

        return optionsTextList;
    }

    public void selectDropDownValue(By locator, String expValue, int timeout) {

        List<WebElement> optionsList =
                getDropDownOptionsList(locator, timeout);

        for (WebElement e : optionsList) {

            if (e.getText().equals(expValue)) {
                e.click();
                break;
            }
        }
    }

    // ================= Wait Utils =================

    public WebElement waitForElementPresence(By locator, int timeOut) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement waitForElementVisible(By locator, int timeOut) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForElementsVisible(By locator, int timeOut) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        return wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public Alert waitForAlertPresence(int timeOut) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String getAlertText(int timeOut) {
        return waitForAlertPresence(timeOut).getText();
    }

    public void acceptAlert(int timeOut) {
        waitForAlertPresence(timeOut).accept();
    }

    public void dismissAlert(int timeOut) {
        waitForAlertPresence(timeOut).dismiss();
    }

    public void clickWhenReady(int timeOut, By locator) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        wait.until(
                ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public WebElement waitForElementToBeClickable(int timeOut,
                                                  By locator) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeOut));

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementPresenceWithFluentWait(
            int timeOut,
            int pollingTime,
            By locator) {

        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(timeOut))
                        .pollingEvery(Duration.ofSeconds(pollingTime))
                        .ignoring(NoSuchElementException.class)
                        .ignoring(StaleElementReferenceException.class);

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForAlertWithFluentWait(
            int timeOut,
            int pollingTime) {

        Wait<WebDriver> wait =
                new FluentWait<>(driver)
                        .withTimeout(Duration.ofSeconds(timeOut))
                        .pollingEvery(Duration.ofSeconds(pollingTime))
                        .ignoring(NoAlertPresentException.class);

        wait.until(ExpectedConditions.alertIsPresent());
    }
}