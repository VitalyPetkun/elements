package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILink;

import java.util.List;

public interface IWebTable extends IElement {
    void setBodyRowByXpathLocator(String locator);

    String getHeadCell(int row, int column);

    String getBodyCell(int row, int column);

    String getBodyCell(String rowName, String columnName);

    String getBodyRowName(String valueCell, int columnIndex);

    int getRowNameIndex(String valueCell, int columnIndex, List<List<String>> list);

    int getRowIndex(String rowName, List<List<String>> list);

    List<List<String>> getHead();

    List<List<String>> getBody();

    List<String> getBodyRow(int row);

    List<String> getBodyRow(String rowName);

    List<String> getBodyColumn(int columnIndex);

    List<String> getBodyColumn(String columnName);

    <T extends IElement> List<List<String>> getHeadList(List<T> list);

    <T extends IElement> List<List<String>> getBodyList(List<T> list);

    void clickCell(int row, int column);

    void clickHeadCell(int column);

    <T extends IElement> List<String> getList(List<T> list);

    List<ILink> getBodyListElements();

    List<ILink> getHeadListElements();
}
