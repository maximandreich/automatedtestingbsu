package page;

import model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.ItemCreator;
import utils.Waits;

import java.time.Duration;

public class FenderItemPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    public static String FENDER_ITEM_PAGE_URL = "https://www.fender.com/en-US/electric-guitars/telecaster/american-vintage-ii-1972-telecaster-thinline/0110392800.html";

    @FindBy(xpath = "//*[@id=\"focus-lock-id\"]/div/div/div[2]/div/div/div[2]/div/button[3]")
    private WebElement closeCookieWindowButton;

    @FindBy(xpath = "//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")
    private WebElement closeLocationWindow;

    @FindBy(xpath = "//*[@id=\"btn-0110392800\"]/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "/html/body/div[1]/header/nav/div/div/div/div[3]/div[3]/div[1]/a")
    private WebElement cartIconButton;

    @FindBy(xpath = "//*[@id=\"mini-cart-content\"]/div/div[1]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[1]/a")
    private WebElement productInCartButton;

    private final By countryClose = By.xpath("//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img");
    private final By closeCookieWindow = By.cssSelector("#focus-lock-id > div > div > div.sc-cCjUiG.gHlwwJ > div > div > div.sc-lllmON.fjvxqY > div > button:nth-child(3)");
    private final By addToCart = By.xpath("//*[@id=\"btn-0110392800\"]/button");
    private final By cartIcon = By.xpath("/html/body/div[1]/header/nav/div/div/div/div[3]/div[3]/div[1]/a");
    private final By productInCart = By.xpath("//*[@id=\"mini-cart-content\"]/div/div[1]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[1]/a");

    public FenderItemPage(WebDriver driver) {
        super(driver);
    }

    public FenderItemPage addItemToCart() {
        Waits.getWebElementUntilWait(driver, addToCart);
        addToCartButton.click();
        logger.info("addItemToCart func " + Thread.currentThread().getId());
        return this;
    }

    public FenderItemPage checkProductInCart() {
        Actions actions = new Actions(driver);
        Waits.getWebElementUntilWait(driver, cartIcon);
        actions.moveToElement(cartIconButton);
        Waits.getWebElementUntilWait(driver, productInCart);
        productInCartButton.click();
        logger.info("productInCartButton click " + Thread.currentThread().getId());
        return this;
    }

    public boolean checkCorrectProductInCart() {
        Item testItem = ItemCreator.withCredentialsFromProperty();
        logger.info("checkCorrectProductInCart func " + Thread.currentThread().getId());
        return driver.getCurrentUrl().equals(testItem.getItemURL());
    }

    @Override
    public FenderItemPage openPage() {
        Item testItem = ItemCreator.withCredentialsFromProperty();
        driver.get(testItem.getItemURL());
        Waits.getWebElementUntilWait(driver, countryClose);
        closeLocationWindow.click();
        WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowContent = shadowRoot.findElement(closeCookieWindow);
        shadowContent.click();
        logger.info("openPage itemPage func " + Thread.currentThread().getId());
        return this;
    }
}