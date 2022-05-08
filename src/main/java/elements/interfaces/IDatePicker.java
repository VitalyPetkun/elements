package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;

public interface IDatePicker  extends IElement {
    void setDate(int day, int month, int year);

    void clickNext();

    void clickPrev();

    void clickMonthAndYear();

    void clickYear();

    void selectYear(int year);

    void selectMonth(int month);

    void selectDay(int day);

    String getDate();
}
