package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FenderHomePage extends AbstractPage{

    public static String FENDER_HOME_PAGE_URL = "https://www.fender.com/en-US/start";

    @FindBy(id = "hs-eu-decline-button")
    private WebElement closeCookieWindowButton;

    public FenderHomePage(WebDriver driver){
        super(driver);
    }

    public int searchByNameResult(String itemName){
        WebElement searchBox;
        searchBox = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search' and @placeholder='Search here']")));
        searchBox.sendKeys("F-91W-1XY");
        List<WebElement> searchResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//li/a/div[@class='klevu-name-desc-l2']/div[@class='klevu-name-l2' and contains(.,'"+itemName+"')]")));
        return searchResult.size();
    }

    @Override
    public FenderHomePage openPage(){
        driver.get(FENDER_HOME_PAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("hs-eu-decline-button")));
        closeCookieWindowButton.click();
        return this;
    }
}
