package service;

public class TestDataCreator {

    public static final String TESTDATA_VALID_SEARCH = "testdata.valid.search";
    public static final String TESTDATA_INVALID_SEARCH = "testdata.invalid.search";
    public static final String TESTDATA_INVALID_PROMO = "testdata.invalid.promo";

    public static String withCredentialsFromPropertyForInvalidPromo() {
        return TestDataReader.getTestData(TESTDATA_INVALID_PROMO);
    }

    public static String withCredentialsFromPropertyForInvalidSearch() {
        return TestDataReader.getTestData(TESTDATA_INVALID_SEARCH);
    }

    public static String withCredentialsFromPropertyForValidSearch() {
        return TestDataReader.getTestData(TESTDATA_VALID_SEARCH);
    }
}
