package steps;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import pages.CurrencyRatesPage;
import utils.ListUtils;

import java.util.List;

public class CurrencyRatesPageSteps {

    private static CurrencyRatesPage currencyRatesPage = new CurrencyRatesPage();
    private static SoftAssert softAssert = new SoftAssert();

    private CurrencyRatesPageSteps() {
    }

    public static String getCurrencyRate(String rowName, String columnName) {
        return currencyRatesPage.getCurrencyRate(rowName, columnName);
    }

    public static List<String> getBankRates(String columnIndex) {
        return currencyRatesPage.getBankRates(Integer.parseInt(columnIndex));
    }

    public static String getBankName(String valueCurrency, String columnIndex) {
        return currencyRatesPage.getBankName(valueCurrency, Integer.parseInt(columnIndex));
    }

    public static void softAssertIsCorrectCurrencyRatesBuyingState(String currencyName, String state, String columnIndex) {
        softAssert.assertEquals(ListUtils.biggestValue(CurrencyRatesPageSteps.getBankRates(columnIndex)),
                getCurrencyRate(currencyName, state), "Currency rates wasn't correct.");
    }

    public static void softAssertIsCorrectCurrencyRatesSaleState(String currencyName, String state, String columnIndex) {
        softAssert.assertEquals(ListUtils.smallestValue(CurrencyRatesPageSteps.getBankRates(columnIndex)),
                getCurrencyRate(currencyName, state), "Currency rates wasn't correct.");
    }

    public static void softAssertIsCorrectBankWithTheBestCurrencyRatesSaleState(String columnIndex, String bankName) {
        String valueCurrency = ListUtils.smallestValue(CurrencyRatesPageSteps.getBankRates(columnIndex));
        softAssert.assertEquals(getBankName(valueCurrency, columnIndex), bankName,
                "Bank wasn't correct witch has the best currency rates sale state.");
    }

    public static void assertIsOpen() {
        Assert.assertTrue(currencyRatesPage.state().isDisplayed());
    }

    public static void softAssertAll(String message) {
        softAssert.assertAll(message);
    }
}
