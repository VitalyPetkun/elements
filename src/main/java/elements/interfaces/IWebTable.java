package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILink;

import java.util.List;

public interface IWebTable extends IElement {
    void setBodyRowByXpathLocator(String locator);

    String getHeadCell(int column);

    String getBodyCell(int row, int column);

    String getBodyCell(String rowName, String columnName);

    int getRowIndex(String rowName);

    List<String> getHead();

    List<String> getBodyRow(int row);

    List<String> getBodyRow(String rowName);

    List<String> getBodyColumn(String columnName);

    List<List<String>> getBody();

    <T extends IElement> List<List<String>> getBodyList(List<T> list);

    int getColumnSize();

    int getBodyRowSize();

    void clickCell(int row, int column);

    void clickHeadCell(int column);

    <T extends IElement> List<String> getList(List<T> list);

    List<ILink> getBodyListElements();

    List<ILink> getHeadListElements();
}
