package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import browser.Browser;
import elements.Image;
import elements.interfaces.IImage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class MainPage extends Form {

    private static final String MAIN_PAGE_LOCATOR = "//div[@class='main-content-wrapper']";

    private final ILink currencyRates = getElementFactory().getLink(
            By.xpath("//a[contains(@href,'currency') and contains(@class,'main-nav_link')]"), "Currency rates");

    private final IImage walletNews = getElementFactory().getCustomElement(Image.class,
            By.xpath("//section[@class='section bg-blue section--m'][2]//div[@class='nc nc--5 ']//img"), "Wallet news");
    private final IImage mainNews = getElementFactory().getCustomElement(Image.class,
            By.xpath("//div[contains(@class,'nc--3')]//img"), "Main news");

    public MainPage() {
        super(By.xpath(MAIN_PAGE_LOCATOR), "Main page");
    }

    public void clickCurrencyRates() {
        currencyRates.click();
    }

    public void saveWalletNewsImage(String nameWalletNewsImage) {
        walletNews.save(nameWalletNewsImage);
    }

    public void saveMainNewsImage(String nameMainNewsImage) {
        mainNews.save(nameMainNewsImage);
    }

    public Boolean compareMainNewsImageWithWalletNewsImage() {
        return mainNews.compare(walletNews.getFullFileName());
    }
}
