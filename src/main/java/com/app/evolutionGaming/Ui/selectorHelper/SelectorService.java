package com.app.evolutionGaming.Ui.selectorHelper;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static com.app.evolutionGaming.Ui.types.Timeouts.INCREASED_TIMEOUT_SECS;
import static org.testng.Assert.fail;

@Log4j
public class SelectorService {

    protected WebDriver driver;
    private WebDriverWait wait;

    public SelectorService(WebDriver driver) {
        this.driver = driver;
    }

    public String getWebsiteTitle() {
        return driver.getTitle();
    }

    /**
     * this method will wait until completely load the page
     * @param units time in seconds
     */
    public void pageLoad_Timeout(int units) {
        driver.manage().timeouts().pageLoadTimeout(units, TimeUnit.SECONDS);
    }

    /**
     * this method will wait until the element to be visible by the locator
     * @param units time in seconds
     */
    private WebElement visibilityOfElementLocatedByLocator(By locator, int units) //Visibility Of Element Located By Xpath
    {
        wait = new WebDriverWait(driver, units);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * this method will wait until the element to be clickable
     * @param element this could be an attribute of an element
     */
    private void waitElementToBeClickable(WebElement element) {
        wait = new WebDriverWait(driver, INCREASED_TIMEOUT_SECS);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.info("Wait element to be clickable");
    }

    protected void waitLocatorToBeClickable(By locator) {
        wait = new WebDriverWait(driver, INCREASED_TIMEOUT_SECS);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        log.info("Wait element to be clickable: (" + locator + ")");
    }

    /**
     * this method will wait until the element to be visible by the locator
     * @param locator this could be an attribute of an element
     * @param text    this field is for
     */
    protected void click(By locator, String text) {
        visibilityOfElementLocatedByLocator(locator, INCREASED_TIMEOUT_SECS);
        waitLocatorToBeClickable(locator);
        if (isElementPresent(locator)) {
            driver.findElement(locator).click();
        }
        log.info("Clicked '" + text + "' using element: (" + locator + ")");
    }

    protected void clickElement(WebElement element, String text) {
        waitElementToBeClickable(element);
        if (element.isDisplayed()) {
            element.click();
        }
        log.info("Clicked " + text);
    }

    protected void scrollIntoLocatorAndClick(By locator, String text) {
        WebElement locator1 = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", locator1);
        locator1.click();
        log.info("Clicked " + text + " using element: (" + locator + ")");
    }

    protected void type(By locator, String text) {
        String inputFieldText = visibilityOfElementLocatedByLocator(locator, INCREASED_TIMEOUT_SECS).getText();
        driver.findElement(locator).click();
        if (inputFieldText != null) {
            driver.findElement(locator).clear();
        }
        driver.findElement(locator).sendKeys(text);
        log.info("Typed: '" + text + "' in the field: (" + locator + ")");
    }

    protected void selectAnOptionFromDropdown(By locator, String text) {
        Select select = new Select(driver.findElement(locator));
        select.getOptions().stream().filter(option -> option.getText().toLowerCase()
                .contains(text.toLowerCase()))
                .findFirst().ifPresent(WebElement::click);
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            fail(ex.getMessage());
            return false;
        }
    }
}
