package steps;

import org.testng.Assert;
import pages.MainPage;

public class MainPageSteps {

    private static final MainPage mainPage = new MainPage();

    private MainPageSteps() {
    }

    public static void clickCurrencyRates() {
        mainPage.clickCurrencyRates();
    }

    public static void saveWalletNewsImage(String nameWalletNewsImage) {
        mainPage.saveWalletNewsImage(nameWalletNewsImage);
    }

    public static void saveMainNewsImage(String nameMainNewsImage) {
        mainPage.saveMainNewsImage(nameMainNewsImage);
    }

    public static void assertIsOpen() {
        Assert.assertTrue(mainPage.state().isDisplayed(), "Main page isn't opened.");
    }

    public static void assertCompareNewsImages() {
        Assert.assertFalse(mainPage.compareMainNewsImageWithWalletNewsImage(), "Images are same.");
    }

}
