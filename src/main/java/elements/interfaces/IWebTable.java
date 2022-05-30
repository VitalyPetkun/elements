package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILink;
import java.util.List;

public interface IWebTable extends IElement {
    void setBodyRowByXpathLocator(String locator);
    void clickHeadCell(int column);
    void clickCell(int row, int column);
    int getRowIndex(String rowName, List<List<String>> list);
    int getRowNameIndex(String valueCell, int columnIndex, List<List<String>> list);
    String getHeadCell(int row, int column);
    String getBodyCell(int row, int column);
    String getBodyCell(String rowName, String columnName);
    String getBodyRowName(String valueCell, int columnIndex);
    List<String> getBodyRow(int row);
    List<String> getBodyRow(String rowName);
    List<String> getBodyColumn(int columnIndex);
    List<String> getBodyColumn(String columnName);
    List<ILink> getBodyListElements();
    List<ILink> getHeadListElements();
    List<List<String>> getHead();
    List<List<String>> getBody();
    <T extends IElement> List<String> getList(List<T> list);
    <T extends IElement> List<List<String>> getHeadList(List<T> list);
    <T extends IElement> List<List<String>> getBodyList(List<T> list);
}
