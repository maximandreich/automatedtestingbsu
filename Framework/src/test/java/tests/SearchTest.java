package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import page.FenderHomePage;
import service.TestDataCreator;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest extends CommonConditions{

    private final Logger logger = LogManager.getRootLogger();

    private final String errorSearchMessage = "Sorry, nothing found for:";

    @Test
    public void validSearch() {
        logger.info("start valid search text");
        String searchNameOfItem = TestDataCreator.withCredentialsFromPropertyForValidSearch().toLowerCase();
        List<String> itemsNames = new FenderHomePage(driver)
                .openPage()
                .sendSearchText(searchNameOfItem)
                .getSearchItemsName();
        String[] refactorSearchName = searchNameOfItem.split(" ");
        logger.info((itemsNames.toString()));
        assertThat(itemsNames)
                .allMatch(it -> Arrays.stream(refactorSearchName).allMatch(i -> it.contains(i)));
    }

    @Test
    public void invalidSearch() {
        logger.info("start invalid search text");
        String searchNameOfItem = TestDataCreator.withCredentialsFromPropertyForInvalidSearch().toLowerCase();
        int itemsSearchSize = new FenderHomePage(driver)
                .openPage()
                .sendSearchText(searchNameOfItem)
                .getSizeOfSearchingItems();
        logger.info("items search size = " + itemsSearchSize);
        assertThat(itemsSearchSize)
                .as("size of searching elements")
                .isEqualTo(0);
    }

    @Test
    public void invalidSearchMessage() {
        logger.info("start invalid search text 2");
        String searchNameOfItem = TestDataCreator.withCredentialsFromPropertyForInvalidSearch().toLowerCase();
        String searchMessage = new FenderHomePage(driver)
                .openPage()
                .sendSearchText(searchNameOfItem)
                .getErrorMessage();
        logger.info("items search size = " + searchMessage);
        assertThat(searchMessage)
                .as("error message check")
                .contains(errorSearchMessage)
                .as("error text searching check")
                .contains(searchNameOfItem);
    }
}
