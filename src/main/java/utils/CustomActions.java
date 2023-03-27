package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.exceptions.ActionsException;

import java.time.Duration;

public class CustomActions {

    private static final Logger LOGGER = LogManager.getLogger(CustomActions.class);
    private static final int DEFAULT_TIMEOUT = 10;
    private WebDriver driver;

    public CustomActions(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollIntoView(WebElement element) {
        LOGGER.info("Scrolling to web element");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()", element);
    }

    public boolean isElementVisible(WebElement element) {
        return isElementVisible(element, DEFAULT_TIMEOUT);
    }

    public boolean isElementVisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (NoSuchElementException ex) {
            LOGGER.info(ex.getMessage());
            return false;
        }
    }

    public void click(WebElement element) throws ActionsException{
        this.click(element, DEFAULT_TIMEOUT);
    }

    public void click(WebElement element, int seconds) throws ActionsException{
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            throw new ActionsException("The element is not clickable", e);
        }
    }

    public void sendText(WebElement element, String text) throws ActionsException {
        this.sendText(element, text, DEFAULT_TIMEOUT);
    }

    public void sendText(WebElement element, String text, int seconds) throws  ActionsException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
        } catch (Exception e) {
            throw new ActionsException("Could not send text to the web element", e);
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
