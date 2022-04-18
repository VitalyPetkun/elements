package elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import elements.interfaces.ISlider;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Slider extends Element implements ISlider {

    private final String ATTRIBUTE_MIN = "min";
    private final String ATTRIBUTE_MAX = "max";
    private final String ATTRIBUTE_VALUE = "value";

    protected Slider(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("Slider", new Object[0]);
    }

    public String getValue() {
        return this.getAttribute(ATTRIBUTE_VALUE);
    }

    public String getMin() {
        return this.getAttribute(ATTRIBUTE_MIN);
    }

    public String getMax() {
        return this.getAttribute(ATTRIBUTE_MAX);
    }

    public void setValue(String value) {
        int setValue = Integer.parseInt(value);
        int currentValue = Integer.parseInt(this.getValue());

        if (currentValue < setValue)
            for (int i = currentValue; i < setValue; i++)
                this.plusOne();

        if (currentValue > setValue)
            for (int i = currentValue; i > setValue; i--)
                this.minusOne();
    }

    public void plusOne() {
        this.sendKeys(Keys.ARROW_RIGHT);
    }

    public void minusOne() {
        this.sendKeys(Keys.ARROW_LEFT);
    }
}
