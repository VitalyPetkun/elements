package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;

public interface IWebTable extends IElement {
    String getHeadCell(int column);

    String getCell(int row, int column);

    String[] getHead();

    String[] getBodyRow(int row);

    String[][] getBody();

    int getColumnSize();

    int getBodyRowSize();

    void clickCell(int row, int column);

    void clickHeadCell(int column);
}
