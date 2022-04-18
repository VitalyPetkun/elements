package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;

import java.util.List;

public interface IWebTable extends IElement {
    String getHeadCell(int column);

    String getCell(int row, int column);

    List<String> getHead();

    List<String> getBodyRow(int row);

    List<List<String>> getBody();

    <T extends IElement> List<List<String>> getBodyList(List<T> list);

    int getColumnSize();

    int getBodyRowSize();

    void clickCell(int row, int column);

    void clickHeadCell(int column);

    <T extends IElement> List<String> getList(List<T> list);

    <T extends IElement> List<IElement> getBodyListElements();

    <T extends IElement> List<IElement> getHeadListElements();
}
