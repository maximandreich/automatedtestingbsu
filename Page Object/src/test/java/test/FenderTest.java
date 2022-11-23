package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.FenderCartPage;
import page.FenderItemPage;

import java.time.Duration;

public class FenderTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments(/*"--headless",*/ "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1920,1080",
                "--disable-extensions", "--proxy-server='direct://'", "--proxy-bypass-list=*", "--start-maximized",
                "--disable-gpu", "--ignore-certificate-errors","user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    public void addItemToCart() throws InterruptedException {
        boolean cartCheck = new FenderItemPage(driver)
                .openPage()
                .addItemToCart()
                .checkProductInCart()
                .checkCorrectProductInCart();
        Assert.assertTrue(cartCheck);
    }

    @Test
    public void changeQuantity() throws InterruptedException {
        boolean equalityOfValues = new FenderCartPage(driver)
                .openPage()
                .addItemToCart()
                .goToCart()
                .changeQuantityAndCheckEquality();
        Assert.assertTrue(equalityOfValues);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown(){
        driver.quit();
        driver = null;
    }
}