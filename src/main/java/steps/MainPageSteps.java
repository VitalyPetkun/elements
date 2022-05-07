package steps;

import org.testng.Assert;
import pages.MainPage;

public class MainPageSteps {

    private static final MainPage mainPage = new MainPage();

    private MainPageSteps() {}

    public static void assertIsOpen() {
        Assert.assertTrue(mainPage.state().isDisplayed(), "Main page isn't opened.");
    }
}
