package pages;

import aquality.selenium.forms.Form;
import elements.WebTable;
import elements.interfaces.IWebTable;
import org.openqa.selenium.By;

public class CurrencyRatesPage extends Form {

    private static final String CURRENCY_RATES_PAGE_LOCATOR = "//div[@id='currency-content']";

    private final IWebTable bestRates = getElementFactory().getCustomElement(WebTable.class,
            By.xpath("//div[contains(@class,'best-rates')]//table"), "Best rates");

    public CurrencyRatesPage() {
        super(By.xpath(CURRENCY_RATES_PAGE_LOCATOR), "Currency rates page");
    }

    public String getCurrencyRate(String rowName, String columnName) {
        return bestRates.getBodyCell(rowName, columnName);
    }
}
