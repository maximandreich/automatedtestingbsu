package page;

import model.Item;
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
import utils.StringRefactor;
import utils.Waits;

import javax.swing.plaf.TableHeaderUI;
import java.time.Duration;

public class FenderCartPage extends AbstractPage {

    public static String FENDER_ITEM_PAGE_URL = "https://www.fender.com/en-US/electric-guitars/telecaster/american-vintage-ii-1972-telecaster-thinline/0110392800.html";

    @FindBy(xpath = "//*[@id=\"focus-lock-id\"]/div/div/div[2]/div/div/div[2]/div/button[3]")
    private WebElement closeCookieWindowButton;

    @FindBy(xpath = "//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")
    private WebElement closeLocationWindow;

    @FindBy(xpath = "//*[@id=\"mini-cart-content\"]/div/div[4]/div/a")
    private WebElement cartIconButton;

    @FindBy(xpath = "//*[@id=\"btn-0110392800\"]/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[3]/div/div[3]/div[2]/button")
    private WebElement removeButton;

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

    public FenderCartPage addItemToCart() {
        Waits.getWebElementUntilWait(driver, addToCart);
        addToCartButton.click();
        return this;
    }

    public FenderCartPage goToCart() {
        Waits.getWebElementUntilWait(driver, cartIcon);
        cartIconButton.click();
        return this;
    }

    public FenderCartPage deleteFromCart() {
        Waits.getWebElementUntilWait(driver, remove);
        removeButton.click();
        return this;
    }

    public String getQuantityOfItemsInCart() {
        String valueString = driver.findElement(itemCounter).getText();
        return valueString;
    }

    /*public boolean changeQuantityAndCheckEquality() throws InterruptedException {
        Waits.getWebElementUntilWait(driver, priceString);
        String valueString = driver.findElement(priceString).getText();
        Double valueBeforeChanging = new StringRefactor().makeTextToPrice(valueString);
        Waits.getWebElementUntilWait(driver, cartIcon);
        //Waits.getWebElementUntilClickableWait(driver, select);
        Thread.sleep(1000);
        Select quantitySelector = new Select(driver.findElement(select));
        quantitySelector.selectByVisibleText("2");
        valueString = driver.findElement(priceString).getText();
        Double valueAfterChanging = new StringRefactor().makeTextToPrice(valueString);
        return valueAfterChanging.equals(valueBeforeChanging*2.0);
    }*/

    @Override
    public FenderCartPage openPage() {
        driver.get(FENDER_ITEM_PAGE_URL);
        Waits.getWebElementUntilWait(driver, countryClose);
        closeLocationWindow.click();
        WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowContent = shadowRoot.findElement(closeCookieWindow);
        shadowContent.click();
        return this;
    }
}
