package page;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FenderItemPage extends AbstractPage{

    public static String FENDER_ITEM_PAGE_URL = "https://www.fender.com/en-US/electric-guitars/telecaster/american-vintage-ii-1972-telecaster-thinline/0110392800.html";

    @FindBy(xpath = "//*[@id=\"focus-lock-id\"]/div/div/div[2]/div/div/div[2]/div/button[3]")
    private WebElement closeCookieWindowButton;

    @FindBy(xpath = "//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")
    private WebElement closeLocationWindow;

    @FindBy(xpath = "//*[@id=\"btn-0110392800\"]/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "/html/body/div[1]/header/nav/div/div/div/div[3]/div[3]/div[1]/a")
    private WebElement goToCartButton;


    public FenderItemPage(WebDriver driver){
        super(driver);
    }

    public WebElement addItemToCart(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"btn-0110392800\"]/button")));
        addToCartButton.click();
        WebElement cartWindow = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("mini-cart-content")));
        return cartWindow;
    }

    @Override
    public FenderItemPage openPage(){
        driver.get(FENDER_ITEM_PAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")));
        closeLocationWindow.click();
        WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#focus-lock-id > div > div > div.sc-jJoQJp.dTzACB > div > div > div.sc-bBHxTw.hgPqkm > div > button:nth-child(3)"));
        shadowContent.click();
        return this;

        // /html/body/div[11]//div/div/div[2]/div/div/div/div[2]/div/div/div[2]/div/button[3]
        //*[@id="focus-lock-id"]/div/div/div[2]/div/div/div[2]/div/button[3]
    }
}