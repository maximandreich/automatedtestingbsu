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
import service.TestDataCreator;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class CartTest extends CommonConditions{

    private final Logger logger = LogManager.getRootLogger();
    private final String emptyCartMessage = "Your Shopping Cart is Empty";
    private final String promocodeErrorMessage = "Coupon cannot be added to your cart";

    @Test
    public void addItemToCart() {
        logger.info("addItemToCart start " + Thread.currentThread().getId());
        boolean cartCheck = new FenderItemPage(driver)
                .openPage()
                .addItemToCart()
                .checkProductInCart()
                .checkCorrectProductInCart();
        logger.info("addItemToCart finish");
        assertThat(cartCheck)
                .isTrue();
    }

    @Test
    public void deleteItemFromCart() {
        logger.info("deleteItemFromCart start " + Thread.currentThread().getId());
        new FenderItemPage(driver)
                .openPage()
                .addItemToCart();
        new FenderCartPage(driver)
                .openPage()
                .deleteFromCart();
        driver.navigate().refresh();
        int quantityOfItems = new FenderCartPage(driver).getQuantityOfItemsInCart();
        logger.info("deleteItemFromCart finish");
        assertThat(quantityOfItems)
                .as("quantity of elements is 0")
                .isEqualTo(0);
    }

    @Test
    public void checkEmptyCartMessage() {
        logger.info("checkEmptyCartMessage start " + Thread.currentThread().getId());
        String messageText = new FenderCartPage(driver)
                .openPage()
                .getItemsCountMessageBoxText();
        logger.info("checkEmptyCartMessage finish");
        assertThat(messageText)
                .as("Similar text phrase of empty cart")
                .contains(emptyCartMessage);
    }

    @Test
    public void changeQuantity() {
        logger.info("changeQuantity start "  + Thread.currentThread().getId());
        new FenderItemPage(driver)
                .openPage()
                .addItemToCart();
        boolean equalityOfValues = new FenderCartPage(driver)
                .openPage()
                .changeQuantityAndCheckEquality();
        logger.info("changeQuantity finish");
        assertThat(equalityOfValues)
                .isTrue();
    }

    @Test
    public void checkInvalidPromo() {
        logger.info("checkInvalidPromo start " + Thread.currentThread().getId());
        new FenderItemPage(driver)
                .openPage()
                .addItemToCart();
        double priceBeforePromo = new FenderCartPage(driver)
                .openPage()
                .getCurrentPrice();
        String errorMessage = new FenderCartPage(driver)
                .setPromoCode()
                .getErrorPromoCodeMessage();
        double priceAfterPromo = new FenderCartPage(driver)
                .getCurrentPrice();
        logger.info("checkInvalidPromo finish");
        assertThat(errorMessage)
                .as("There invalid promocode message")
                .contains(promocodeErrorMessage);
        assertThat(priceBeforePromo)
                .as("price not changed")
                .isEqualTo(priceAfterPromo);

    }
}
