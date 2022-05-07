package tests;

import browser.Browser;
import org.testng.annotations.Test;
import services.Paths;
import services.ProjectFiles;
import services.Url;
import utils.PropertiesManager;
import utils.SmartLogger;

public class CurrencyRates {

    private final String MYFIN_URL = PropertiesManager.getValue(Paths.MAIN_RESOURCES_PATH.getPath(),
            ProjectFiles.CONFIG.getFile(), Url.MYFIN_URL.getUrl());

    @Test
    private void currentUsDollarRate() {
        SmartLogger.logStep(1, "Open web site.");
        Browser.goTo(MYFIN_URL);

    }
}
