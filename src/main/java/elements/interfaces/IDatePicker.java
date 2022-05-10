package elements.interfaces;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;

import java.util.List;

public interface IDatePicker extends IElement {
    void setDate(int day, int month, int year);

    IButton getNextBtn();

    IButton getPrevBtn();

    ILabel getCurrentDayLbl();

    ILabel getCurrentMonthLbl();

    ILabel getCurrentYearLbl();

    IButton getDayBtn();

    IButton getMonthBtn();

    IButton getYearBtn();

    IButton getSelectedYearBtn();

    IButton getSelectedMonthAndYearBtn();

    void clickNext();

    void clickPrev();

    void clickDay();

    void clickMonth();

    void clickYear();

    void clickSelectedMonthAndYear();

    void clickSelectedYear();

    void selectYear(int year);

    void selectMonth(int month);

    void selectDay(int day);

    int getMonthNumber(String month);

    List<String> getDate();
}
