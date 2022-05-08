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

    private final String HEAD_ROW_LOCATOR = "//thead//tr";
    private final String HEAD_CELL_LOCATOR = "//th";
    private final String BODY_CELL_LOCATOR = "//td";

    private String BODY_ROW_LOCATOR = "//tbody//tr";
    private List<List<String>> body;
    private List<List<String>> head;

    protected WebTable(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("Table", new Object[0]);
    }

    public void setBodyRowByXpathLocator(String locator) {
        BODY_ROW_LOCATOR = locator;
    }

    public String getHeadCell(int row, int column) {
        return this.getHead().get(row).get(column);
    }

    public String getBodyCell(int row, int column) {
        return this.getBody().get(row).get(column);
    }

    public String getBodyCell(String rowName, String columnName) {
        return this.getBody()
                .get(this.getRowIndex(rowName, this.getBody()))
                .get(this.getHead().get(0).indexOf(columnName));
    }

    public String getBodyRowName(String valueCell, int columnIndex) {
        int rowIndex = this.getRowNameIndex(valueCell, columnIndex, this.getBody());
        return this.getBody().get(rowIndex).get(0);
    }

    public int getRowNameIndex(String valueCell, int columnIndex, List<List<String>> list) {
        int row = -1;
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).indexOf(valueCell) == columnIndex){
                row = i;
                break;
            }
        }

        return row;
    }

    public int getRowIndex(String rowName, List<List<String>> list) {
        int row = -1;
        for (List<String> rowList : list) {
            if (row < 0)
                row = rowList.indexOf(rowName);
        }

        return row;
    }

    public List<List<String>> getHead() {
        if (head == null)
            head = this.getHeadList(this.getHeadListElements());

        return head;
    }

    public List<List<String>> getBody() {
        if (body == null)
            body = this.getBodyList(this.getBodyListElements());

        return body;
    }

    public List<String> getBodyRow(int row) {
        return this.getBody().get(row);
    }

    public List<String> getBodyRow(String rowName) {
        return this.getBody().get(this.getRowIndex(rowName, this.getBody()));
    }

    public List<String> getBodyColumn(int columnIndex) {
        List<String> columnList = new ArrayList<>();
        for (List<String> rowList :
                this.getBody()) {
            columnList.add(rowList.get(columnIndex));
        }

        return columnList;
    }

    public List<String> getBodyColumn(String columnName) {
        int column = this.getHead().get(0).indexOf(columnName);
        List<String> columnList = new ArrayList<>();
        for (List<String> rowList :
                this.getBody()) {
            columnList.add(rowList.get(column));
        }

        return columnList;
    }

    public <T extends IElement> List<List<String>> getHeadList(List<T> list) {
        List<List<String>> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<ILink> rowList = new ArrayList<>();
            rowList.addAll(list.get(i).findChildElements(By.xpath(HEAD_CELL_LOCATOR), ILink.class));

            newList.add(this.getList(rowList));
        }

        return newList;
    }

    public <T extends IElement> List<List<String>> getBodyList(List<T> list) {
        List<List<String>> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<ILink> rowList = new ArrayList<>();
            rowList.addAll(list.get(i).findChildElements(By.xpath(BODY_CELL_LOCATOR), ILink.class));

            newList.add(this.getList(rowList));
        }

        return newList;
    }

    public void clickCell(int row, int column) {
        List<ILink> rowList = new ArrayList<>();
        rowList.addAll(this.getBodyListElements().get(row).findChildElements(By.xpath(BODY_CELL_LOCATOR), ILink.class));
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
        return this.findChildElements(By.xpath(BODY_ROW_LOCATOR), "Table body", ILink.class);
    }

    public List<ILink> getHeadListElements() {
        return this.findChildElements(By.xpath(HEAD_ROW_LOCATOR), "Table head", ILink.class);
    }
}
