package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;

public interface IImage extends IElement {
    void save(String imageName);
    void save(String imageName, String path);
    Boolean compare(String imageName);
    String getAttributeSrc();
    String getFullFileName();
    String setFullFileName(String imageName, String path);
}
