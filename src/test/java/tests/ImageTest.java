package tests;

import browser.Browser;
import org.testng.annotations.Test;
import services.TestDataVariables;
import steps.MainPageSteps;
import utils.PropertiesManager;
import utils.SmartLogger;

import static services.Paths.TEST_RESOURCES_PATH;
import static services.ProjectFiles.TEST_DATA;

public class ImageTest extends BaseTest {

    private final String NAME_MAIN_NEWS_IMAGE = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.NAME_MAIN_NEWS_IMAGE.getVariable());
    private final String NAME_WALLET_NEWS_IMAGE = PropertiesManager.getValue(TEST_RESOURCES_PATH.getPath(),
            TEST_DATA.getFile(), TestDataVariables.NAME_WALLET_NEWS_IMAGE.getVariable());

    @Test
    public void saveImages() {
        SmartLogger.logStep(1, "Open main page.");
        Browser.goTo(MYFIN_URL);
        MainPageSteps.assertIsOpen();

        SmartLogger.logStep(2, "Save main news image.");
        MainPageSteps.saveMainNewsImage(NAME_MAIN_NEWS_IMAGE);

        SmartLogger.logStep(3, "Save wallet news image.");
        MainPageSteps.saveWalletNewsImage(NAME_WALLET_NEWS_IMAGE);

        SmartLogger.logStep(4, "Compare news images.");
        MainPageSteps.assertCompareNewsImages();

        Browser.takeScreenshotTest(this.getClass().getName());
    }
}
