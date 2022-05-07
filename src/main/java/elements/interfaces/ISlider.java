package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;

public interface ISlider extends IElement {
    String getValue();

    String getMin();

    String getMax();

    void setValue(int value);

    void plusOne();

    void minusOne();
}
