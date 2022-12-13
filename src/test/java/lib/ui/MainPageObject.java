package lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

import static lib.TestData.TIMEOUT_IN_SEC;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

abstract public class MainPageObject
{
    protected RemoteWebDriver driver;

    public MainPageObject(RemoteWebDriver driver){
        this.driver = driver;
    }

    public WebElement waitForElementPresent(String locator, String errorMessage){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SEC));
        wait.withMessage(errorMessage + "\n");
        return wait.until(presenceOfElementLocated(by));
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage){
        WebElement element = waitForElementPresent(locator, errorMessage);
        element.click();
        return element;
    }

    public Boolean waitForElementClickAndWaitForNotPresent(String locator, String errorMessage){
        WebElement element = waitForElementPresent(locator, errorMessage);
        element.click();
        return waitForElementNotPresent(locator, errorMessage);
    }

    public WebElement waitForElementAndSendKeys(String locator, String value, String errorMessage){
        WebElement element = waitForElementPresent(locator, errorMessage);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndSetValue(String locator, String value, String errorMessage){
        WebElement element = waitForElementPresent(locator, errorMessage);
        driver.executeScript("arguments[0].innerHTML = '" + value + "'", element);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage){
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SEC));
        wait.withMessage(errorMessage + "\n");
        return wait.until(invisibilityOfElementLocated(by));
    }

    public void assertElementPresent(String locator, String errorMessage){
        try {
            By by = this.getLocatorByString(locator);
            driver.findElement(by);
        }
        catch(NoSuchElementException exception){
            throw new AssertionError(errorMessage + ". " + exception.getMessage());
        }
    }

    private By getLocatorByString(String locatorWithType){
        String[] explodedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = explodedLocator[0];
        String locator = explodedLocator[1];

        switch (byType){
            case "xpath" : return By.xpath(locator);
            case "id" : return By.id(locator);
            case "css" : return By.cssSelector(locator);
            default: throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locatorWithType);
        }
    }
}
