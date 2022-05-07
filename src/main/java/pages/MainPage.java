package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private static final String MAIN_PAGE_LOCATOR = "//div[@class='main-content-wrapper']";

    private final ILink currencyRates = getElementFactory().getLink(
            By.xpath("//a[contains(@href,'currency') and contains(@class,'main-nav_link')]"),"Currency rates");

    public MainPage() {
        super(By.xpath(MAIN_PAGE_LOCATOR), "Main page");
    }

    public void clickCurrencyRates() {
        currencyRates.click();
    }
}
