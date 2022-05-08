package tests;

import browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import services.Url;
import utils.PropertiesManager;

import static services.Paths.MAIN_RESOURCES_PATH;
import static services.ProjectFiles.CONFIG;

public class BaseTest {

    protected final String MYFIN_URL = PropertiesManager.getValue(MAIN_RESOURCES_PATH.getPath(),
            CONFIG.getFile(), Url.MYFIN_URL.getUrl());

    @BeforeMethod
    protected void setup() {
        Browser.setMaximizeWindow();
        Browser.timeouts();
    }

    @AfterMethod
    protected void quitDriver() {
        Browser.quit();
    }
}