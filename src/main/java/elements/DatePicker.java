package elements;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import elements.interfaces.IDatePicker;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class DatePicker extends Element implements IDatePicker {

    private final String NEXT_LOCATOR = "//th[@class='next']";
    private final String PREV_LOCATOR = "//th[@class='prev']";
    private final String DAY_LOCATOR = "//td[@class='day']";
    private final String MONTH_LOCATOR = "//span[@class='month']";
    private final String YEAR_LOCATOR = "//span[@class='year']";
    private final String CURRENT_DAY_LOCATOR = "//td[contains(@class,'day') and contains(@class,'active')]";
    private final String CURRENT_MONTH_LOCATOR = "//span[contains(@class,'month') and contains(@class,'active')]";
    private final String CURRENT_YEAR_LOCATOR = "//span[contains(@class,'year') and contains(@class,'active')]";
    private final String SELECTED_MONTH_AND_YEAR_LOCATOR = "//div[@class='datepicker-days']//th[@class='picker-switch']";
    private final String SELECTED_YEAR_LOCATOR = "//div[@class='datepicker-months']//th[@class='picker-switch']";

    private IButton nextBtn;
    private IButton prevBtn;
    private IButton dayBtn;
    private IButton monthBtn;
    private IButton yearBtn;
    private IButton selectedYearBtn;
    private IButton selectedMonthAndYearBtn;

    private ILabel currentDay;
    private ILabel currentMonth;
    private ILabel currentYear;

    protected DatePicker(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("Date picker", new Object[0]);
    }

    public void clickNext() {
        this.getNextBtn().click();
    }

    public void clickPrev() {
        this.getPrevBtn().click();
    }

    public void clickDay() {
        this.getDayBtn().click();
    }

    public void clickMonth() {
        this.getMonthBtn().click();
    }

    public void clickYear() {
        this.getYearBtn().click();
    }

    public void clickSelectedYear() {
        this.getSelectedYearBtn().click();
    }

    public void clickSelectedMonthAndYear() {
        this.getSelectedMonthAndYearBtn().click();
    }

    public void selectDay(int day) {
        List<ILink> days = this.findChildElements(By.xpath(DAY_LOCATOR), "Day in data picker", ILink.class);
        days.get(--day).click();
    }

    public void selectMonth(int month) {
        List<ILink> months = this.findChildElements(By.xpath(MONTH_LOCATOR), "Month in data picker", ILink.class);
        months.get(--month).click();
    }

    public void selectYear(int year) {
        List<ILink> years = this.findChildElements(By.xpath(YEAR_LOCATOR), "Year in data picker", ILink.class);
        for (ILink severalYear : years) {
            if (year == Integer.parseInt(severalYear.getText())) {
                severalYear.click();
                return;
            }
        }
        this.clickPrev();
        this.selectYear(year);
    }

    public void setDate(int day, int month, int year) {
        this.clickSelectedMonthAndYear();
        this.clickSelectedYear();
        this.selectYear(year);
        this.selectMonth(month);
        this.selectDay(day);
    }

    public IButton getNextBtn() {
        if (nextBtn == null)
            nextBtn = this.findChildElement(By.xpath(NEXT_LOCATOR), "Next button", IButton.class);

        return nextBtn;
    }

    public IButton getPrevBtn() {
        if (prevBtn == null)
            prevBtn = this.findChildElement(By.xpath(PREV_LOCATOR), "Prev button", IButton.class);

        return prevBtn;
    }

    public IButton getDayBtn() {
        if (dayBtn == null)
            dayBtn = this.findChildElement(By.xpath(DAY_LOCATOR), "Day button", IButton.class);

        return dayBtn;
    }

    public IButton getMonthBtn() {
        if (monthBtn == null)
            monthBtn = this.findChildElement(By.xpath(MONTH_LOCATOR), "Month button", IButton.class);

        return monthBtn;
    }

    public IButton getYearBtn() {
        if (yearBtn == null)
            yearBtn = this.findChildElement(By.xpath(YEAR_LOCATOR), "Year button", IButton.class);

        return yearBtn;
    }

    public IButton getSelectedYearBtn() {
        if (selectedYearBtn == null)
            selectedYearBtn = this.findChildElement(By.xpath(SELECTED_YEAR_LOCATOR),
                    "Selected year button", IButton.class);

        return selectedYearBtn;
    }

    public IButton getSelectedMonthAndYearBtn() {
        if (selectedMonthAndYearBtn == null)
            selectedMonthAndYearBtn = this.findChildElement(By.xpath(SELECTED_MONTH_AND_YEAR_LOCATOR),
                    "Selected month and year button", IButton.class);

        return selectedMonthAndYearBtn;
    }

    public ILabel getCurrentDayLbl() {
        if (currentDay == null)
            currentDay = this.findChildElement(By.xpath(CURRENT_DAY_LOCATOR), "Current day", ILabel.class);

        return currentDay;
    }

    public ILabel getCurrentMonthLbl() {
        if (currentMonth == null)
            currentMonth = this.findChildElement(By.xpath(CURRENT_MONTH_LOCATOR), "Current month", ILabel.class);

        return currentMonth;
    }

    public ILabel getCurrentYearLbl() {
        if (currentYear == null)
            currentYear = this.findChildElement(By.xpath(CURRENT_YEAR_LOCATOR), "Current year", ILabel.class);

        return currentYear;
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

    public List<String> getDate() {
        List<String> date = new ArrayList<>();

        date.add(this.getCurrentDayLbl().getText());
        this.clickSelectedMonthAndYear();
        date.add(String.valueOf(this.getMonthNumber(this.getCurrentMonthLbl().getText())));
        this.clickSelectedYear();
        date.add(this.getCurrentYearLbl().getText());

        AqualityServices.getBrowser().getDriver().navigate().refresh();

        return date;
    }
}
