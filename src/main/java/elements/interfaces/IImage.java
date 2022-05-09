package elements.interfaces;

import aquality.selenium.elements.interfaces.IElement;

public interface IImage  extends IElement {
    void save(String imageName);

    void save(String imageName, String path);

    void saveWithoutRepeatImage(String imageName);

    void saveWithoutRepeatImage(String imageName, String path);

    void saveWithoutRepeatImageName(String imageName);

    void saveWithoutRepeatImageName(String imageName, String path);
}
