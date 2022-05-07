package elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.IElement;
import elements.interfaces.IWebTable;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class WebTable extends Element implements IWebTable {

    private final String LOCATOR_BODY = "//tbody//tr";
    private final String LOCATOR_HEAD = "//tr//th";

    protected WebTable(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("Table", new Object[0]);
    }

    public String getHeadCell(int column) {
        return this.getList(this.getHeadListElements()).get(column);
    }

    public String getCell(int row, int column) {
        return this.getBodyList(this.getBodyListElements()).get(row).get(column);
    }

    public List<String> getHead() {
        return this.getList(this.getHeadListElements());
    }

    public List<String> getBodyRow(int row) {
        return this.getBodyList(this.getBodyListElements()).get(row);
    }

    public List<List<String>> getBody() {
        return this.getBodyList(this.getBodyListElements());
    }

    public <T extends IElement> List<List<String>> getBodyList(List<T> list) {
        List<List<String>> newList = new ArrayList<>();
        for (int i = 0; i < getBodyRowSize(); i++) {
            List<IElement> row = new ArrayList<>();
            row.add(list.get(i));

            newList.add(this.getList(row));
        }

        return newList;
    }

    public int getColumnSize() {
        return this.getHeadListElements().size();
    }

    public int getBodyRowSize() {
        return this.getBodyListElements().size();
    }

    public void clickCell(int row, int column) {
        List<IElement> rowList = new ArrayList<>();
        rowList.add(this.getBodyListElements().get(row));
        rowList.get(column).clickAndWait();
    }

    public void clickHeadCell(int column) {
        this.getHeadListElements().get(column).clickAndWait();
    }

    public <T extends IElement> List<String> getList(List<T> list) {
        List<String> newList = new ArrayList<>();
        for (T element : list) {
            newList.add(element.getText());
        }

        return newList;
    }

    public <T extends IElement> List<IElement> getBodyListElements() {
        return this.findChildElements(By.xpath(LOCATOR_BODY), "Table body", IElement.class);
    }

    public <T extends IElement> List<IElement> getHeadListElements() {
        return this.findChildElements(By.xpath(LOCATOR_HEAD), "Table head", IElement.class);
    }
}
