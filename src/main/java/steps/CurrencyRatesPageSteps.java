package steps;

import org.testng.Assert;
import pages.CurrencyRatesPage;

public class CurrencyRatesPageSteps {

    private static CurrencyRatesPage currencyRatesPage = new CurrencyRatesPage();

    private CurrencyRatesPageSteps() {}

    public static String getCurrencyRate(String rowName, String columnName) {
        return currencyRatesPage.getCurrencyRate(rowName, columnName);
    }

    public static void assertIsOpen() {
        Assert.assertTrue(currencyRatesPage.state().isDisplayed());
    }
}
