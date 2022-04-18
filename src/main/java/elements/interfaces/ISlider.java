package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;

public interface ISlider extends IElement {
    String getValue();

    String getMin();

    String getMax();

    void setValue(String value);

    void plusOne();

    void minusOne();
}
