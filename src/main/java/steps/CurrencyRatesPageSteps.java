package steps;

import org.testng.Assert;
import pages.CurrencyRatesPage;
import utils.ListUtils;

import java.util.List;

public class CurrencyRatesPageSteps {

    private static CurrencyRatesPage currencyRatesPage = new CurrencyRatesPage();

    private CurrencyRatesPageSteps() {}

    public static String getCurrencyRate(String rowName, String columnName) {
        return currencyRatesPage.getCurrencyRate(rowName, columnName);
    }

    public static List<String> getBankRates(String columnName) {
        return currencyRatesPage.getBankRates(columnName);
    }

    public static void assertIsCorrectCurrencyRatesBuyingState(String currencyName, String state, String curName) {
        Assert.assertEquals(ListUtils.biggestValue(CurrencyRatesPageSteps.getBankRates(curName)),
                CurrencyRatesPageSteps.getCurrencyRate(currencyName, state),
                "Currency rates wasn't correct.");
    }

    public static void assertIsCorrectCurrencyRatesSaleState(String currencyName, String state, String curName) {
        Assert.assertEquals(ListUtils.smallestValue(CurrencyRatesPageSteps.getBankRates(curName)),
                CurrencyRatesPageSteps.getCurrencyRate(currencyName, state),

                "Currency rates wasn't correct.");
    }

    public static void assertIsOpen() {
        Assert.assertTrue(currencyRatesPage.state().isDisplayed());
    }
}
