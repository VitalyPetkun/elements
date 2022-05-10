package elements;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Element;
import elements.interfaces.IImage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import services.Paths;
import utils.SmartLogger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Image extends Element implements IImage {

    private final String SAVE_IMAGES_PATH = Paths.IMAGES_PATH.getPath();
    private final String FORMAT_NAME = "png";

    private String srcAttribute = "src";
    private String src = null;
    private String fullFileName = null;

    private URL url = null;

    protected Image(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    protected String getElementType() {
        return this.getLocalizationManager().getLocalizedMessage("Image", new Object[0]);
    }

    public void save(String imageName) {
        this.save(imageName, SAVE_IMAGES_PATH);
    }

    public void save(String imageName, String path) {
        try {
            ((JavascriptExecutor) AqualityServices.getBrowser().getDriver())
                    .executeScript("arguments[0].scrollIntoView();",
                            AqualityServices.getBrowser().getDriver().findElement(this.getLocator()));

            Thread.sleep(2000);

            AqualityServices.getBrowser().goTo(this.getUrl().toString());

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_S);

            Thread.sleep(5000);

            StringSelection stringSelection = new StringSelection(this.setFullFileName(imageName, path));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(2000);
        } catch (InterruptedException | AWTException e) {
            SmartLogger.logError("Robot not create");
        }

        AqualityServices.getBrowser().goBack();
    }

    @Override
    public Boolean compare(String imageName) {
        try {
            BufferedImage image1 = ImageIO.read(new File(this.getFullFileName()));
            BufferedImage image2 = ImageIO.read(new File(imageName));

            int columns = image1.getWidth();
            int rows = image1.getHeight();

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    int rgb = image1.getRGB(col, row);
                    int rgb2 = image2.getRGB(col, row);

                    if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight()) {
                        return false;
                    } else if (rgb != rgb2) {
                        return false;
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public String getAttributeSrc() {
        if (src == null)
            src = AqualityServices.getBrowser().getDriver().findElement(this.getLocator()).getAttribute(srcAttribute);

        return src;
    }

    public String getFullFileName() {
        return fullFileName;
    }

    public String setFullFileName(String imageName, String path) {
        if (fullFileName == null)
            fullFileName = System.getProperty("user.dir").concat("\\")
                    .concat(path).concat(imageName).concat(".").concat(FORMAT_NAME);

        return fullFileName;
    }

    private URL getUrl() {
        if (url == null) {
            try {
                url = new URL(this.getAttributeSrc());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return url;
    }
}
