package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import elements.DatePicker;
import elements.WebTable;
import elements.interfaces.IDatePicker;
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

    private final ILink selectDate = getElementFactory().getLink(By.xpath("//div[@id='datetimepicker']"),
            "Select date");

    private final IDatePicker datePicker = getElementFactory().getCustomElement(DatePicker.class,
            By.xpath("//div[@class='datepicker']"), "Date picker");

    private final ILabel currentDate = getElementFactory().getLabel(By.xpath("//div[contains(@class,'best-rates')]//th//sup"),
            "Current date");

    public CurrencyRatesPage() {
        super(By.xpath(CURRENCY_RATES_PAGE_LOCATOR), "Currency rates page");
        bankRates.setBodyRowByXpathLocator(BANK_RATES_BODY_ROW_LOCATOR);
    }

    public String getCurrencyRate(String rowName, String columnName) {
        return bestRates.getBodyCell(rowName, columnName);
    }

    public List<String> getBankRates(int columnIndex) {
        return bankRates.getBodyColumn(columnIndex);
    }

    public String getBankName(String valueCurrency, int columnIndex) {
        return bankRates.getBodyRowName(valueCurrency, columnIndex);
    }

    public void clickSelectDateLnk() {
        selectDate.click();
    }

    public String getSelectedDateLnk() {
        return currentDate.getText();
    }

    public void setDate(int day, int month, int year) {
        datePicker.setDate(day, month, year);
    }

    public List<String> getCurrentDate() {
        clickSelectDateLnk();
        return datePicker.getDate();
    }
}
