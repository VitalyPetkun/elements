package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CurrencyRatesPage extends Form {

    private static final String CURRENCY_RATES_PAGE_LOCATOR = "//div[@id='currency-content']";

    public CurrencyRatesPage() {
        super(By.xpath(CURRENCY_RATES_PAGE_LOCATOR), "Currency rates page");
    }
}
