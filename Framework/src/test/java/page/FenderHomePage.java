package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utils.Waits;

import java.util.ArrayList;
import java.util.List;

public class FenderHomePage extends AbstractPage{

    private final Logger logger = LogManager.getRootLogger();

    public final String FENDER_CART_PAGE_URL = "https://www.fender.com/en-US/start";

    @FindBy(xpath = "//*[@id=\"focus-lock-id\"]/div/div/div[2]/div/div/div[2]/div/button[3]")
    private WebElement closeCookieWindowButton;

    @FindBy(xpath = "//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")
    private WebElement closeLocationWindow;

    @FindBy(xpath = "//*[contains(@class, 'search-icon')]")
    private WebElement searchButtonIcon;

    @FindBy(xpath = "//*[contains(@class, 'form-control search-field ')]")
    private WebElement searchField;

    @FindBy(xpath = "//div[@class='pdp-link']/a[contains(@class, 'link')]")
    private List<WebElement> listSearchingElements;

    @FindBy(xpath = "//div[@class='search-result-title']")
    private WebElement firstPartErrorMessage;

    @FindBy(xpath = "//div[@class='search-result-title']/span")
    private WebElement secondPartErrorMessage;

    private By pathToFirstErrorMessage = By.xpath("//div[@class='search-result-title']");

    private By searchingElements = By.xpath("//div[@class='pdp-link']/a[contains(@class, 'link')]");

    private final By searchFieldPath = By.xpath("//*[contains(@class, 'form-control search-field ')]");

    private final By searchIconPath = By.xpath("//*[contains(@class, 'search-icon')]");

    private final By countryClose = By.xpath("//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img");
    private final By closeCookieWindow = By.cssSelector("#focus-lock-id > div > div > div.sc-cCjUiG.gHlwwJ > div > div > div.sc-lllmON.fjvxqY > div > button:nth-child(3)");

    public FenderHomePage(WebDriver driver) {
        super(driver);
    }

    public FenderHomePage sendSearchText(String textForSearch) {
        Waits.getWebElementUntilClickableWait(driver, searchIconPath);
        searchButtonIcon.click();
        Waits.getWebElementUntilClickableWait(driver,searchFieldPath);
        searchField.sendKeys(textForSearch);
        searchField.sendKeys(Keys.ENTER);
        logger.info("sendSearchText func " + Thread.currentThread().getId());
        return this;
    }

    public List<String> getSearchItemsName(){
        Waits.getWebElementUntilClickableWait(driver,searchingElements);
        List<String> itemsNames = new ArrayList<>();
        for(WebElement item : listSearchingElements){
            itemsNames.add(item.getText().toLowerCase());
        }
        logger.info("getSearchItemsName func " + Thread.currentThread().getId());
        return itemsNames;
    }

    public int getSizeOfSearchingItems() {
        if(driver.findElements(searchingElements).size() != 0) {
            Waits.getWebElementUntilClickableWait(driver,searchingElements);
            return listSearchingElements.size();
        }else return listSearchingElements.size();
    }

    public String getErrorMessage() {
        Waits.getWebElementUntilClickableWait(driver, pathToFirstErrorMessage);
        return firstPartErrorMessage.getText();
    }

    @Override
    public FenderHomePage openPage() {
        driver.get(FENDER_CART_PAGE_URL);
        if (driver.findElements(countryClose).size() != 0) {
            Waits.getWebElementUntilWait(driver, countryClose);
            closeLocationWindow.click();
            WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
            SearchContext shadowRoot = shadowHost.getShadowRoot();
            WebElement shadowContent = shadowRoot.findElement(closeCookieWindow);
            shadowContent.click();
        }
        logger.info("FenderHomePage openPage() " + Thread.currentThread().getId());
        return this;
    }
}
