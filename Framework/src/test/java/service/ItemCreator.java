package service;

import model.Item;

public class ItemCreator {

    public static final String TESTDATA_ITEM_NAME = "testdata.item.name";
    public static final String TESTDATA_ITEM_URL = "testdata.item.url";

    public static Item withCredentialsFromProperty() {
        return new Item(TestDataReader.getTestData(TESTDATA_ITEM_NAME),
                TestDataReader.getTestData(TESTDATA_ITEM_URL));
    }

    public static Item withEmptyUsername() {
        return new Item("", TestDataReader.getTestData(TESTDATA_ITEM_URL));
    }

    public static Item withEmptyPassword() {
        return new Item(TestDataReader.getTestData(TESTDATA_ITEM_NAME), "");
    }
}
