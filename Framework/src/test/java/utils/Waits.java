package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    private final static long WAIT_TIMEOUT_SECONDS = 1000;
    public static WebElement getWebElementUntilWait(WebDriver driver, By elementPath){
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions
                        .presenceOfElementLocated(elementPath));
    }

    public static WebElement getWebElementUntilClickableWait(WebDriver driver, By elementPath) {
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions
                        .elementToBeClickable(elementPath));
    }

    public static ExpectedCondition<Boolean> jQueryAJAXsCompleted() {
        return new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver){
                return (Boolean) ((JavascriptExecutor)driver).executeScript("return ((window.jQuery != 'undefined') && (jQuery.active == 0) && (document.readyState == 'complete')); ");
            }
        };
    }
}
