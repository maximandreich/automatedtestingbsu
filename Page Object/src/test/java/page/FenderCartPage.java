package page;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FenderCartPage extends AbstractPage{

    public static String FENDER_ITEM_PAGE_URL = "https://www.fender.com/en-US/electric-guitars/telecaster/american-vintage-ii-1972-telecaster-thinline/0110392800.html";

    @FindBy(xpath = "//*[@id=\"focus-lock-id\"]/div/div/div[2]/div/div/div[2]/div/button[3]")
    private WebElement closeCookieWindowButton;

    @FindBy(xpath = "//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")
    private WebElement closeLocationWindow;

    @FindBy(xpath = "/html/body/div[1]/header/nav/div/div/div/div[3]/div[3]/div[1]/a")
    private WebElement cartIcon;

    @FindBy(xpath = "//*[@id=\"btn-0110392800\"]/button")
    private WebElement addToCartButton;

    public FenderCartPage(WebDriver driver) {
        super(driver);
    }

    public FenderCartPage addItemToCart() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"btn-0110392800\"]/button")));
        addToCartButton.click();
        return this;
    }

    public FenderCartPage goToCart() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/header/nav/div/div/div/div[3]/div[3]/div[1]/a")));
        cartIcon.click();
        return this;
    }

    public boolean changeQuantityAndCheckEquality() throws InterruptedException {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[4]/div[1]/div/div[1]")));
        String valueString = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[4]/div[1]/div/div[1]")).getText();
        valueString = valueString.replace("$", "");
        valueString = valueString.replace(",", "");
        Double valueBeforeChanging = Double.parseDouble(valueString);
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/header/nav/div/div/div/div[3]/div[3]/div[1]/a")));
        Select quantitySelector = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div[1]/div[4]/div/div/div[4]/div[3]/div/div/select")));
        quantitySelector.selectByVisibleText("2");
        Thread.sleep(1000);
        valueString = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div[1]/div[4]/div/div/div[4]/div[1]/div/div[1]")).getText();
        valueString = valueString.replace("$", "");
        valueString = valueString.replace(",", "");
        Double valueAfterChanging = Double.parseDouble(valueString);
        return valueAfterChanging.equals(valueBeforeChanging*2.0);
    }

    @Override
    public FenderCartPage openPage(){
        driver.get(FENDER_ITEM_PAGE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"countryMisMatch\"]/div/div/div[3]/div[2]/table/tbody/tr/td[1]/div/a/img")));
        closeLocationWindow.click();
        WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#focus-lock-id > div > div > div.sc-jJoQJp.dTzACB > div > div > div.sc-bBHxTw.hgPqkm > div > button:nth-child(3)"));
        shadowContent.click();
        return this;
    }
}
