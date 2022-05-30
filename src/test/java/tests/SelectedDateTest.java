package tests;

import browser.Browser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.TestDataVariables;
import steps.CurrencyRatesPageSteps;
import steps.MainPageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

import static services.Paths.TEST_RESOURCES_PATH;
import static services.ProjectFiles.TEST_DATA;

public class SelectedDateTest extends BaseTest {

    private final String FIRST_DATE = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.FIRST_DATE.getVariable());
    private final String SECOND_DATE = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.SECOND_DATE.getVariable());
    private final String THIRD_DATE = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.THIRD_DATE.getVariable());

    private String currentDate;

    @DataProvider
    public Object[][] getDate() {
        return new Object[][]{
                {FIRST_DATE},
                {SECOND_DATE},
                {THIRD_DATE}
        };
    }

    @Test(dataProvider = "getDate")
    private void selectDate(String date) {
        SmartLogger.logStep(1, "Open main page.");
        Browser.goTo(MYFIN_URL);
        MainPageSteps.assertIsOpen();

        SmartLogger.logStep(2, "Open currency rates page.");
        MainPageSteps.clickCurrencyRates();
        CurrencyRatesPageSteps.assertIsOpen();

        SmartLogger.logStep(3, "Select date.");
        currentDate = CurrencyRatesPageSteps.getCurrentDate();
        CurrencyRatesPageSteps.clickSelectDateLnk();
        CurrencyRatesPageSteps.setDate(date);
        CurrencyRatesPageSteps.assertIsCorrectDate(date);

        SmartLogger.logStep(4, "Compare date.");
        CurrencyRatesPageSteps.assertCompareDate(currentDate);

        Browser.takeScreenshotTest(this.getClass().getName());
    }
}
