package tests;

import model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.FenderCartPage;
import page.FenderItemPage;
import service.ItemCreator;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends CommonConditions{

    private final Logger logger = LogManager.getRootLogger();
    private final String emptyCartMessage = "You have no items in your shopping basket.";
    private final String secondCartMessage = "Click here to continue shopping.";

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
    public void deleteItemFromCart() {
        new FenderCartPage(driver)
                .openPage()
                .addItemToCart()
                .goToCart()
                .deleteFromCart();
        String quantityOfItems = new FenderCartPage(driver).getQuantityOfItemsInCart();
        assertThat(quantityOfItems)
                .isEqualTo("0 Items");
    }

    /*@Test
    public void changeQuantity() throws InterruptedException {
        boolean equalityOfValues = new FenderCartPage(driver)
                .openPage()
                .addItemToCart()
                .goToCart()
                .changeQuantityAndCheckEquality();
        Assert.assertTrue(equalityOfValues);
    }*/
}
