package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import elements.WebTable;
import elements.interfaces.IWebTable;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesPage extends Form {

    private static final String CURRENCY_RATES_PAGE_LOCATOR = "//div[@id='currency-content']";
    private final String BANK_RATES_BODY_ROW_LOCATOR = "//tbody//tr[contains(@class,'tr')]";

    private final IWebTable bestRates = getElementFactory().getCustomElement(WebTable.class,
            By.xpath("//div[contains(@class,'best-rates')]//table"), "Best rates");
    private final IWebTable bankRates = getElementFactory().getCustomElement(WebTable.class,
            By.xpath("//table[contains(@class,'rates')]"), "Bank rates");

    private IButton nextBtn= getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//th[@class='next']"), "Next button");
    private IButton prevBtn= getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//th[@class='prev']"), "Prev button");
    private IButton dayBtn= getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//td[@class='day']"), "Day button");
    private IButton monthBtn= getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//span[@class='month']"), "Month button");
    private IButton yearBtn= getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//span[@class='year']"), "Year button");
    private final IButton selectedYearBtn = getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//div[@class='datepicker-months']//th[@class='picker-switch']"), "Selected year button");
    private final IButton selectedMonthAndYearBtn = getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//div[@class='datepicker-days']//th[@class='picker-switch']"), "Selected month and year button");

    private final ILabel currentDate = getElementFactory().getLabel(By.xpath("//div[contains(@class,'best-rates')]//th//sup"),
            "Current date");
    private final ILabel currentDay= getElementFactory().getLabel(
            By.xpath("//div[@class='datepicker']//td[contains(@class,'day') and contains(@class,'active')]"),
            "Current day");
    private final ILabel currentMonth= getElementFactory().getLabel(
            By.xpath("//div[@class='datepicker']//span[contains(@class,'month') and contains(@class,'active')]"),
            "Current month");
    private final ILabel currentYear= getElementFactory().getLabel(
            By.xpath("//div[@class='datepicker']//span[contains(@class,'year') and contains(@class,'active')]"),
            "Current year");

    private final ILink selectDate = getElementFactory().getLink(By.xpath("//div[@id='datetimepicker']"),
            "Select date");

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
        this.clickSelectedMonthAndYear();
        this.clickSelectedYear();
        this.selectYear(year);
        this.selectMonth(month);
        this.selectDay(day);
    }

    public List<String> getCurrentDate() {
        this.clickSelectDateLnk();

        List<String> date = new ArrayList<>();

        date.add(currentDay.getText());
        this.clickSelectedMonthAndYear();
        date.add(String.valueOf(this.getMonthNumber(currentMonth.getText())));
        this.clickSelectedYear();
        date.add(currentYear.getText());

        AqualityServices.getBrowser().getDriver().navigate().refresh();

        return date;
    }


    public void clickNext() {
        nextBtn.click();
    }

    public void clickPrev() {
        prevBtn.click();
    }

    public void clickDay() {
        dayBtn.click();
    }

    public void clickMonth() {
        monthBtn.click();
    }

    public void clickYear() {
        yearBtn.click();
    }

    public void clickSelectedMonthAndYear() {
        selectedMonthAndYearBtn.click();
    }

    public void clickSelectedYear() {
        selectedYearBtn.click();
    }

    public void selectDay(int day) {
        List<ILink> days = getElementFactory().findElements(
                By.xpath("//div[@class='datepicker']//td[@class='day']"), "Day in data picker", ILink.class);
        days.get(--day).click();
    }

    public void selectMonth(int month) {
        List<ILink> months = getElementFactory().findElements(
                By.xpath( "//div[@class='datepicker']//span[@class='month']"), "Month in data picker", ILink.class);
        months.get(--month).click();
    }

    public void selectYear(int year) {
        List<ILink> years = getElementFactory().findElements(
                By.xpath("//div[@class='datepicker']//span[@class='year']"), "Year in data picker", ILink.class);
        for (ILink severalYear : years) {
            if (year == Integer.parseInt(severalYear.getText())) {
                severalYear.click();
                return;
            }
        }
        this.clickPrev();
        this.selectYear(year);
    }

    public int getMonthNumber(String month) {
        switch (month) {
            case "янв":
                return 1;
            case "фев":
                return 2;
            case "мар":
                return 3;
            case "апр":
                return 4;
            case "май":
                return 5;
            case "июнь":
                return 6;
            case "июль":
                return 7;
            case "авг":
                return 8;
            case "сен":
                return 9;
            case "окт":
                return 10;
            case "ноя":
                return 11;
            case "дек":
                return 12;
        }

        return 0;
    }
}
