package elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import elements.interfaces.IWebTable;
import org.openqa.selenium.By;

public class WebTable extends Element {
    protected WebTable(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    @Override
    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("Table", new Object[0]);
    }
}
