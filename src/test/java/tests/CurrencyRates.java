package tests;

import browser.Browser;
import org.testng.annotations.Test;
import services.Paths;
import services.ProjectFiles;
import services.Url;
import steps.MainPageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

public class CurrencyRates {

    private final String MYFIN_URL = PropertiesManager.getValue(Paths.MAIN_RESOURCES_PATH.getPath(),
            ProjectFiles.CONFIG.getFile(), Url.MYFIN_URL.getUrl());

    @Test
    private void currentUsDollarRate() {
        SmartLogger.logStep(1, "Open main page.");
        Browser.goTo(MYFIN_URL);
        MainPageSteps.assertIsOpen();

        SmartLogger.logStep(2, "Open currency rates page.");
        MainPageSteps.clickCurrencyRates();

    }
}
