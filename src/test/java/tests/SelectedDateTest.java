package tests;

import browser.Browser;
import org.testng.annotations.Test;
import services.TestDataVariables;
import steps.CurrencyRatesPageSteps;
import steps.MainPageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

import static services.Paths.TEST_RESOURCES_PATH;
import static services.ProjectFiles.TEST_DATA;

public class SelectedDateTest extends BaseTest {

    private final String DATE = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.DATE.getVariable());

    private String currentDate;

    @Test
    private void selectDate() {
        SmartLogger.logStep(1, "Open main page.");
        Browser.goTo(MYFIN_URL);
        MainPageSteps.assertIsOpen();

        SmartLogger.logStep(2, "Open currency rates page.");
        MainPageSteps.clickCurrencyRates();
        CurrencyRatesPageSteps.assertIsOpen();

        SmartLogger.logStep(3, "Select date.");
        currentDate = CurrencyRatesPageSteps.getCurrentDate();
        CurrencyRatesPageSteps.clickSelectDateLnk();
        CurrencyRatesPageSteps.setDate(DATE);
        CurrencyRatesPageSteps.assertIsCorrectDate(DATE);

        SmartLogger.logStep(4, "Compare date.");
        CurrencyRatesPageSteps.assertCompareDate(currentDate);

        Browser.takeScreenshotTest(this.getClass().getName());
    }
}
