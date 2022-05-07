package elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILink;
import elements.interfaces.IWebTable;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class WebTable extends Element implements IWebTable {

    private final String LOCATOR_HEAD = "//thead//tr//th";
    private final String LOCATOR_BODY_ROW = "//tbody//tr";
    private final String LOCATOR_BODY_CELL = "//td";

    private List<List<String>> body;
    private List<String> head;

    protected WebTable(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("Table", new Object[0]);
    }

    public String getHeadCell(int column) {
        return this.getHead().get(column);
    }

    public String getBodyCell(int row, int column) {
        return this.getBody().get(row).get(column);
    }

    public String getBodyCell(String rowName, String columnName) {
        return this.getBody()
                .get(this.getRowIndex(rowName))
                .get(this.getHead().indexOf(columnName));
    }

    public int getRowIndex(String rowName) {
        int row = -1;
        for (List<String> rowList : this.getBody()) {
            if (row < 0)
                row = rowList.indexOf(rowName);
        }

        return row;
    }

    public List<String> getHead() {
        if (head == null)
            head = this.getList(this.getHeadListElements());

        return head;
    }

    public List<String> getBodyRow(int row) {
        return this.getBody().get(row);
    }

    public List<String> getBodyRow(String rowName) {
        return this.getBody().get(this.getRowIndex(rowName));
    }

    public List<String> getBodyColumn(String columnName) {
        int column = this.getHead().indexOf(columnName);
        List<String> columnList = new ArrayList<>();
        for (List<String> rowList:
             this.getBody()) {
            columnList.add(rowList.get(column));
        }

        return columnList;
    }

    public List<List<String>> getBody() {
        if (body == null)
            body = this.getBodyList(this.getBodyListElements());

        return body;
    }

    public <T extends IElement> List<List<String>> getBodyList(List<T> list) {
        List<List<String>> newList = new ArrayList<>();
        for (int i = 0; i < this.getBodyRowSize(); i++) {
            List<ILink> rowList = new ArrayList<>();
            rowList.addAll(list.get(i).findChildElements(By.xpath(LOCATOR_BODY_CELL), ILink.class));

            newList.add(this.getList(rowList));
        }

        return newList;
    }

    public int getColumnSize() {
        return this.getHead().size();
    }

    public int getBodyRowSize() {
        return this.getBody().size();
    }

    public void clickCell(int row, int column) {
        List<ILink> rowList = new ArrayList<>();
        rowList.addAll(this.getBodyListElements().get(row).findChildElements(By.xpath(LOCATOR_BODY_CELL), ILink.class));
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

    public List<ILink> getBodyListElements() {
        return this.findChildElements(By.xpath(LOCATOR_BODY_ROW), "Table body", ILink.class);
    }

    public List<ILink> getHeadListElements() {
        return this.findChildElements(By.xpath(LOCATOR_HEAD), "Table head", ILink.class);
    }
}
