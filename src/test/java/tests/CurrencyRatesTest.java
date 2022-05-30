package tests;

import browser.Browser;
import org.testng.annotations.Test;
import services.TestDataVariables;
import steps.CurrencyRatesPageSteps;
import steps.MainPageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

import static services.Paths.*;
import static services.ProjectFiles.*;

public class CurrencyRatesTest extends BaseTest {

    private final String CURRENCY_BANK_BUYING_USD_COLUMN_INDEX = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.CURRENCY_BANK_BUYING_USD_COLUMN_INDEX.getVariable());
    private final String CURRENCY_BANK_SALE_USD_COLUMN_INDEX = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.CURRENCY_BANK_SALE_USD_COLUMN_INDEX.getVariable());
    private final String CURRENCY_BANK_BUYING_EUR_COLUMN_INDEX = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.CURRENCY_BANK_BUYING_EUR_COLUMN_INDEX.getVariable());
    private final String CURRENCY_BANK_SALE_EUR_COLUMN_INDEX = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.CURRENCY_BANK_SALE_EUR_COLUMN_INDEX.getVariable());
    private final String CURRENCY_USD_NAME = "Доллар США";
    private final String CURRENCY_EUR_NAME = "Евро";
    private final String CURRENCY_STATE_BUYING = "Покупка";
    private final String CURRENCY_STATE_SALE = "Продажа";
    private final String BANK_NAME = "Абсолютбанк";

    @Test
    private void currentUsDollarRate() {
        SmartLogger.logStep(1, "Open main page.");
        Browser.goTo(MYFIN_URL);
        MainPageSteps.assertIsOpen();

        SmartLogger.logStep(2, "Open currency rates page.");
        MainPageSteps.clickCurrencyRates();
        CurrencyRatesPageSteps.assertIsOpen();

        SmartLogger.logStep(3, "Check Euro rate for buying state.");
        CurrencyRatesPageSteps.softAssertIsCorrectCurrencyRatesBuyingState(CURRENCY_EUR_NAME,
                CURRENCY_STATE_BUYING, CURRENCY_BANK_BUYING_EUR_COLUMN_INDEX);

        SmartLogger.logStep(4, "Check Euro rate for sale state.");
        CurrencyRatesPageSteps.softAssertIsCorrectCurrencyRatesSaleState(CURRENCY_EUR_NAME,
                CURRENCY_STATE_SALE, CURRENCY_BANK_SALE_EUR_COLUMN_INDEX);

        SmartLogger.logStep(5, "Check bank which has the best rate usd for sale state.");
        CurrencyRatesPageSteps.softAssertIsCorrectBankWithTheBestCurrencyRatesSaleState(
                CURRENCY_BANK_SALE_USD_COLUMN_INDEX, BANK_NAME);

        Browser.takeScreenshotTest(this.getClass().getName());
        CurrencyRatesPageSteps.softAssertAll("Test isn't success.");
    }
}
