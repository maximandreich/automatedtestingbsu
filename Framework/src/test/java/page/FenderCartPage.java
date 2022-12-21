package page;

import model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.ItemCreator;
import service.TestDataCreator;
import utils.StringRefactor;
import utils.Waits;

import java.time.Duration;
import java.util.List;


public class FenderCartPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    public static String FENDER_ITEM_PAGE_URL = "https://www.fender.com/en-US/electric-guitars/telecaster/american-vintage-ii-1972-telecaster-thinline/0110392800.html";

    public final String FENDER_CART_PAGE_URL = "https://www.fender.com/en-US/cart";

    @FindBy(xpath = "//*[@id=\"focus-lock-id\"]/div/div/div[2]/div/div/div[2]/div/button[3]")
    private WebElement closeCookieWindowButton;

    @FindBy(xpath = "//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")
    private WebElement closeLocationWindow;

    @FindBy(xpath = "//*[contains(@class, 'empty-cart')]")
    private WebElement itemsCountMessageBox;

    @FindBy(xpath = "//*[@id=\"mini-cart-content\"]/div/div[4]/div/a")
    private WebElement cartIconButton;

    @FindBy(xpath = "//*[@id=\"btn-0110392800\"]/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[3]/div/div[3]/div[2]/button")
    private WebElement removeButton;

    @FindBy(xpath = "//*[contains(@class, 'product-info')]")
    private List<WebElement> productInfoBlock;

    @FindBy(xpath = "//*[contains(@class, 'text-right grand-total')]")
    private WebElement totalPriceText;

    @FindBy(xpath = "//*[contains(@class, 'm-0 promo-code-show promo-code add-promo-code-button')]")
    private WebElement addPromoCodeButton;

    @FindBy(xpath = "//*[contains(@class, 'coupon-code-field')]")
    private WebElement textFieldForPromoCode;

    @FindBy(xpath = "//*[contains(@class, 'coupon-error-message invalid-feedback')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[contains(@class, 'promo-code-btn')]")
    private WebElement applyPromoButton;

    private final By errorMessagePath = By.xpath("//*[contains(@class, 'coupon-error-message invalid-feedback')]");

    private final By textFieldForPromoCodePath = By.xpath("//*[contains(@class, 'coupon-code-field')]");

    /*@FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[4]/div[1]/div/div[1]")
    private WebElement priceString;*/

    /*@FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[1]/span")
    private WebElement itemCounter;*/

    private final By countryClose = By.xpath("//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img");
    private final By closeCookieWindow = By.cssSelector("#focus-lock-id > div > div > div.sc-cCjUiG.gHlwwJ > div > div > div.sc-lllmON.fjvxqY > div > button:nth-child(3)");
    private final By addToCart = By.xpath("//*[@id=\"btn-0110392800\"]/button");
    private final By cartIcon = By.xpath("//*[@id=\"mini-cart-content\"]/div/div[4]/div/a");
    private final By priceString = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[4]/div[1]/div/div[1]");
    private final By select = By.xpath("//*[@id=\"quantity\"]");
    private final By productInCart = By.xpath("//*[@id=\"mini-cart-content\"]/div/div[1]/div[2]/div/div/div/div[1]/div[2]/div[1]/div[1]/a");
    private final By remove = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[3]/div/div[3]/div[2]/button");
    private final By itemInCart = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]");
    private final By itemCounter = By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[1]/span");



    public FenderCartPage(WebDriver driver) {
        super(driver);
    }

    public FenderCartPage deleteFromCart() {
        Waits.getWebElementUntilWait(driver, remove);
        removeButton.click();
        return this;
    }

    public int getQuantityOfItemsInCart() {
        return productInfoBlock.size();
    }

    public String getItemsCountMessageBoxText() {
        return itemsCountMessageBox.getText();
    }

    public boolean changeQuantityAndCheckEquality() {
        Waits.getWebElementUntilWait(driver, priceString);
        String valueString = driver.findElement(priceString).getText();
        Double valueBeforeChanging = new StringRefactor().makeTextToPrice(valueString);
        Waits.getWebElementUntilClickableWait(driver, select);
        Select quantitySelector = new Select(driver.findElement(select));
        quantitySelector.selectByVisibleText("2");
        driver.navigate().refresh();
        valueString = driver.findElement(priceString).getText();
        Double valueAfterChanging = new StringRefactor().makeTextToPrice(valueString);
        return valueAfterChanging.equals(valueBeforeChanging*2.0);
    }

    public double getCurrentPrice() {
        logger.info("getCurrentPrice " + Thread.currentThread().getId());
        return  new StringRefactor().makeTextToPrice(totalPriceText.getText());
    }

    public FenderCartPage setPromoCode() {
        String testPromoCode = TestDataCreator.withCredentialsFromPropertyForInvalidPromo();
        addPromoCodeButton.click();
        Waits.getWebElementUntilClickableWait(driver, textFieldForPromoCodePath);
        textFieldForPromoCode.sendKeys(testPromoCode);
        applyPromoButton.click();
        logger.info("applyPromoButton click " + Thread.currentThread().getId());
        return this;
    }

    public String getErrorPromoCodeMessage() {
        Waits.getWebElementUntilClickableWait(driver, errorMessagePath);
        return errorMessage.getText();
    }

    @Override
    public FenderCartPage openPage() {
        driver.get(FENDER_CART_PAGE_URL);
        if (driver.findElements(countryClose).size() != 0) {
            Waits.getWebElementUntilWait(driver, countryClose);
            closeLocationWindow.click();
            WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
            SearchContext shadowRoot = shadowHost.getShadowRoot();
            WebElement shadowContent = shadowRoot.findElement(closeCookieWindow);
            shadowContent.click();
        }
        logger.info("openPage FenderCartPage func " + Thread.currentThread().getId());
        return this;
    }
}
