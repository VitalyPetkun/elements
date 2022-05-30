package elements.interfaces;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import java.util.List;

public interface IDatePicker extends IElement {
    void clickNext();
    void clickPrev();
    void clickDay();
    void clickMonth();
    void clickYear();
    void clickSelectedYear();
    void clickSelectedMonthAndYear();
    void selectDay(int day);
    void selectMonth(int month);
    void selectYear(int year);
    void setDate(int day, int month, int year);
    IButton getNextBtn();
    IButton getPrevBtn();
    IButton getDayBtn();
    IButton getMonthBtn();
    IButton getYearBtn();
    IButton getSelectedYearBtn();
    IButton getSelectedMonthAndYearBtn();
    ILabel getCurrentDayLbl();
    ILabel getCurrentMonthLbl();
    ILabel getCurrentYearLbl();
    int getMonthNumber(String month);
    List<String> getDate();
}
