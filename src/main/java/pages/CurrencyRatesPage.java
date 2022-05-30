package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.SmartLogger;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesPage extends Form {

    private static final String CURRENCY_RATES_PAGE_LOCATOR = "//div[@id='currency-content']";
    private final String BANK_RATES_BODY_ROW_LOCATOR = "//tbody//tr[contains(@class,'tr')]";
    private final String BEST_RATES_BODY_ROW_LOCATOR = "//tbody//tr";


    private final ILink bestRates = getElementFactory().getLink(
            By.xpath("//div[contains(@class,'best-rates')]//table"), "Best rates");
    private final ILink bankRates = getElementFactory().getLink(
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
            By.xpath("//div[@class='datepicker']//div[@class='datepicker-months']//th[@class='picker-switch']"),
            "Selected year button");
    private final IButton selectedMonthAndYearBtn = getElementFactory().getButton(
            By.xpath("//div[@class='datepicker']//div[@class='datepicker-days']//th[@class='picker-switch']"),
            "Selected month and year button");

    private final ILabel currentDate = getElementFactory().getLabel(
            By.xpath("//div[contains(@class,'best-rates')]//th//sup"),
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
    }

    private final String HEAD_ROW_LOCATOR = "//thead//tr";
    private final String HEAD_CELL_LOCATOR = "//th";
    private final String BODY_CELL_LOCATOR = "//td";

    private List<List<String>> bestRatesBody;
    private List<List<String>> bestRatesHead;


    public String getCurrencyRate(String rowName, String columnName) {
        return this.getBestRatesBodyCell(rowName, columnName);
    }

    public int getBestRatesRowIndex(String rowName, List<List<String>> list) {
        int row = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(rowName))
                row = i;
        }

        return row;
    }

    public int getBestRatesRowNameIndex(String valueCell, int columnIndex, List<List<String>> list) {
        int row = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf(valueCell) == columnIndex) {
                row = i;
                break;
            }
        }

        return row;
    }

    public String getBestRatesHeadCell(int row, int column) {
        return this.getBestRatesHead().get(row).get(column);
    }

    public String getBestRatesBodyCell(String rowName, String columnName) {
        return this.getBestRatesBody()
                .get(this.getBestRatesRowIndex(rowName, this.getBestRatesBody()))
                .get(this.getBestRatesHead().get(0).indexOf(columnName));
    }

    public String getBestRatesBodyRowName(String valueCell, int columnIndex) {
        int rowIndex = this.getBestRatesRowNameIndex(valueCell, columnIndex, this.getBestRatesBody());
        return this.getBestRatesBody().get(rowIndex).get(0);
    }

    public List<String> getBestRatesBodyRow(int row) {
        return this.getBestRatesBody().get(row);
    }

    public List<String> getBestRatesBodyRow(String rowName) {
        return this.getBestRatesBody().get(this.getBestRatesRowIndex(rowName, this.getBestRatesBody()));
    }

    public List<String> getBestRatesBodyColumn(int columnIndex) {
        List<String> columnList = new ArrayList<>();
        for (List<String> rowList :
                this.getBestRatesBody()) {
            columnList.add(rowList.get(columnIndex));
        }

        return columnList;
    }

    public List<String> getBestRatesBodyColumn(String columnName) {
        int column = this.getBestRatesHead().get(0).indexOf(columnName);
        List<String> columnList = new ArrayList<>();
        for (List<String> rowList :
                this.getBestRatesBody()) {
            columnList.add(rowList.get(column));
        }

        return columnList;
    }

    public List<ILink> getBestRatesBodyListElements() {
        return bestRates.findChildElements(By.xpath(BEST_RATES_BODY_ROW_LOCATOR), "Best rates body", ILink.class);
    }

    public List<ILink> getBestRatesHeadListElements() {
        return bestRates.findChildElements(By.xpath(HEAD_ROW_LOCATOR), "Best rates head", ILink.class);
    }

    public List<List<String>> getBestRatesHead() {
        if (bestRatesHead == null)
            bestRatesHead = this.getHeadList(this.getBestRatesHeadListElements());

        return bestRatesHead;
    }

    public List<List<String>> getBestRatesBody() {
        if (bestRatesBody == null)
            bestRatesBody = this.getBodyList(this.getBestRatesBodyListElements());

        return bestRatesBody;
    }

    public List<String> getBankRates(int columnIndex) {
        return this.getBankRatesBodyColumn(columnIndex);
    }

    public String getBankName(String valueCurrency, int columnIndex) {
        return this.getBankRatesBodyRowName(valueCurrency, columnIndex);
    }

    private List<List<String>> bankRatesBody;
    private List<List<String>> bankRatesHead;

    public int getBankRatesRowIndex(String rowName, List<List<String>> list) {
        int row = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(rowName))
                row = i;
        }

        return row;
    }

    public int getBankRatesRowNameIndex(String valueCell, int columnIndex, List<List<String>> list) {
        int row = -1;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf(valueCell) == columnIndex) {
                row = i;
                break;
            }
        }

        return row;
    }

    public String getBankRatesHeadCell(int row, int column) {
        return this.getBankRatesHead().get(row).get(column);
    }

    public String getBankRatesBodyCell(int row, int column) {
        return this.getBankRatesBody().get(row).get(column);
    }

    public String getBankRatesBodyCell(String rowName, String columnName) {
        return this.getBankRatesBody()
                .get(this.getBankRatesRowIndex(rowName, this.getBankRatesBody()))
                .get(this.getBankRatesHead().get(0).indexOf(columnName));
    }

    public String getBankRatesBodyRowName(String valueCell, int columnIndex) {
        int rowIndex = this.getBankRatesRowNameIndex(valueCell, columnIndex, this.getBankRatesBody());
        return this.getBankRatesBody().get(rowIndex).get(0);
    }

    public List<String> getBankRatesBodyRow(int row) {
        return this.getBankRatesBody().get(row);
    }

    public List<String> getBankRatesBodyRow(String rowName) {
        return this.getBankRatesBody().get(this.getBankRatesRowIndex(rowName, this.getBankRatesBody()));
    }

    public List<String> getBankRatesBodyColumn(int columnIndex) {
        List<String> columnList = new ArrayList<>();
        for (List<String> rowList :
                this.getBankRatesBody()) {
            columnList.add(rowList.get(columnIndex));
        }

        return columnList;
    }

    public List<String> getBankRatesBodyColumn(String columnName) {
        int column = this.getBankRatesHead().get(0).indexOf(columnName);
        List<String> columnList = new ArrayList<>();
        for (List<String> rowList :
                this.getBankRatesBody()) {
            columnList.add(rowList.get(column));
        }

        return columnList;
    }

    public List<ILink> getBodyListElements() {
        return bankRates.findChildElements(By.xpath(BANK_RATES_BODY_ROW_LOCATOR), "Bank rates body", ILink.class);
    }

    public List<ILink> getHeadListElements() {
        return bankRates.findChildElements(By.xpath(HEAD_ROW_LOCATOR), "Bank rates head", ILink.class);
    }

    public List<List<String>> getBankRatesHead() {
        if (bankRatesHead == null)
            bankRatesHead = this.getHeadList(this.getHeadListElements());

        return bankRatesHead;
    }

    public List<List<String>> getBankRatesBody() {
        if (bankRatesBody == null)
            bankRatesBody = this.getBodyList(this.getBodyListElements());

        return bankRatesBody;
    }

    public <T extends IElement> List<String> getList(List<T> list) {
        List<String> newList = new ArrayList<>();
        for (T element : list) {
            newList.add(element.getText());
        }

        return newList;
    }

    public <T extends IElement> List<List<String>> getHeadList(List<T> list) {
        List<List<String>> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<ILink> rowList = new ArrayList<>();
            rowList.addAll(list.get(i).findChildElements(By.xpath(HEAD_CELL_LOCATOR), ILink.class));

            newList.add(this.getList(rowList));
        }

        return newList;
    }

    public <T extends IElement> List<List<String>> getBodyList(List<T> list) {
        List<List<String>> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<ILink> rowList = new ArrayList<>();
            rowList.addAll(list.get(i).findChildElements(By.xpath(BODY_CELL_LOCATOR), ILink.class));

            newList.add(this.getList(rowList));
        }

        return newList;
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
        if(day > days.size())
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException exception) {
                SmartLogger.logError("Uncorrected day");
            }
        days.get(--day).click();
    }

    public void selectMonth(int month) {
        List<ILink> months = getElementFactory().findElements(
                By.xpath( "//div[@class='datepicker']//span[@class='month']"),
                "Month in data picker", ILink.class);
        if(month > months.size())
            try {
                throw new IndexOutOfBoundsException();
            } catch (IndexOutOfBoundsException exception) {
                SmartLogger.logError("Uncorrected month");
            }
        months.get(--month).click();
    }

    public void selectYear(int year) {
        List<ILink> years = getElementFactory().findElements(
                By.xpath("//div[@class='datepicker']//span[@class='year']"),
                "Year in data picker", ILink.class);
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
