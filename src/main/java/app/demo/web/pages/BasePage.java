package app.demo.web.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

/**
 * Base Page is an abstract class containing the common functionality.
 * All other pages extend this class.
 **/
public abstract class BasePage {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Click on a known element
     *
     * @param - element path
     */
    public void clickElement(By element) {
        waitForElementVisibility(element);
        driver.findElement(element).click();
    }

    /**
     * click on a known element, when the element is inaccessible at first
     *
     * @param element
     * @return
     */
    public boolean retryingFindClick(By element) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitForElementVisibility(element);
                driver.findElement(element).click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
                log.error(String.valueOf(e.fillInStackTrace()));
            }
            attempts++;
        }
        return result;
    }

    /**
     * Insert keys into a known element
     *
     * @param - element path, text to be entered
     */
    public void insertText(By element, String text) {
        waitForElementVisibility(element);
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    /**
     * Get text of a given element
     *
     * @param element
     */
    public String getTextOfElement(By element) {
        waitForElementVisibility(element);
        return driver.findElement(element).getText();
    }

    /**
     * Get text of a given element when the element is inaccessible
     *
     * @param element
     */
    public String retryingFindGetText(By element) {
        int attempts = 0;
        String text = null;
        while (attempts < 2) {
            try {
                waitForElementVisibility(element);
                text = driver.findElement(element).getText();
                break;
            } catch (StaleElementReferenceException e) {
                log.error(String.valueOf(e.fillInStackTrace()));
            }
            attempts++;
        }
        return text;
    }

    /**
     * click on a known element using JavaScript executor
     * @param element - Web element locator
     */
    public void javaScriptExClick(By element){
        WebElement webElement = driver.findElement(element);
        try {
            if (webElement.isEnabled() && webElement.isDisplayed()) {
                log.info("Clicking on element with using java script click");

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
            } else {
                log.error("Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            log.error("Element is not attached to the page document ".concat(String.valueOf(e.getStackTrace())));
        } catch (NoSuchElementException e) {
            log.error("Element was not found in DOM ".concat(String.valueOf(e.getStackTrace())));
        } catch (Exception e) {
            log.error("Unable to click on element ".concat(String.valueOf(e.getStackTrace())));
        }
    }

    /**
     * Find a web element which contains a provided text
     * @param searchText - text to be searched for
     * @return
     */
    public WebElement getWebElementByText(String searchText){
        return driver.findElement(By.xpath("//*[contains(text(),'"+searchText+"')]"));
    }

    /**
     * Find a data-cy web element which contains a provided text
     * @param searchText -text to be searched for
     * @return
     */
    public WebElement getDataCyElementByText(String searchText){
        return driver.findElement(By.xpath("//*[@data-cy='"+searchText+"']"));
    }

    /**
     * Get displayed status of an element
     *
     * @param element
     */
    public Boolean isElementDisplayed(By element) {
        waitForElementVisibility(element);
        return driver.findElement(element).isDisplayed();
    }

    /**
     * Get all the items from a list
     *
     * @param listContainer
     * @param listValues
     * @return itemList
     */
    public List<WebElement> getItemList(By listContainer, By listValues) {
        waitForElementVisibility(listContainer);
        WebElement element = driver.findElement(listContainer);
        return element.findElements(listValues);
    }

    /**
     * Get the count of items in a list
     *
     * @param listContainer
     * @param listValues
     * @return list size
     */
    public int getItemCountOfList(By listContainer, By listValues) {
        return getItemList(listContainer, listValues).size();
    }

    /**
     * Explicitly wait until the element is visible
     *
     * @param element
     */
    public void waitForElementVisibility(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
}
