package pages;

import aquality.selenium.forms.Form;
import elements.WebTable;
import elements.interfaces.IWebTable;
import org.openqa.selenium.By;

import java.util.List;

public class CurrencyRatesPage extends Form {

    private static final String CURRENCY_RATES_PAGE_LOCATOR = "//div[@id='currency-content']";
    private final String BANK_RATES_BODY_ROW_LOCATOR = "//tbody//tr[contains(@class,'tr')]";

    private final IWebTable bestRates = getElementFactory().getCustomElement(WebTable.class,
            By.xpath("//div[contains(@class,'best-rates')]//table"), "Best rates");

    private final IWebTable bankRates = getElementFactory().getCustomElement(WebTable.class,
            By.xpath("//table[contains(@class,'rates')]"), "Bank rates");

    public CurrencyRatesPage() {
        super(By.xpath(CURRENCY_RATES_PAGE_LOCATOR), "Currency rates page");
        bankRates.setBodyRowByXpathLocator(BANK_RATES_BODY_ROW_LOCATOR);
    }

    public String getCurrencyRate(String rowName, String columnName) {
        return bestRates.getBodyCell(rowName, columnName);
    }

    public List<String> getBankRates(String columnName) {
        return bankRates.getBodyColumn(columnName);
    }

}
