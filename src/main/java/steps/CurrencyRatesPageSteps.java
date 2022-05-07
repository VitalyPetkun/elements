package steps;

import org.testng.Assert;
import pages.CurrencyRatesPage;

public class CurrencyRatesPageSteps {

    private static CurrencyRatesPage currencyRatesPage = new CurrencyRatesPage();

    private CurrencyRatesPageSteps() {}

    public static void assertIsOpen() {
        Assert.assertTrue(currencyRatesPage.state().isDisplayed());
    }
}
