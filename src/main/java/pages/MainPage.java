package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
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

public class MainPage extends Form {

    private static final String MAIN_PAGE_LOCATOR = "//div[@class='main-content-wrapper']";

    private final ILink currencyRates = getElementFactory().getLink(
            By.xpath("//a[contains(@href,'currency') and contains(@class,'main-nav_link')]"), "Currency rates");

    private final ILabel walletNews = getElementFactory().getLabel(
            By.xpath("//section[@class='section bg-blue section--m'][2]//div[@class='nc nc--5 ']//img"), "Wallet news");
    private final ILabel mainNews = getElementFactory().getLabel(
            By.xpath("//div[contains(@class,'nc--3')]//img"), "Main news");

    private final String SAVE_IMAGES_PATH = Paths.IMAGES_PATH.getPath();
    private final String FORMAT_NAME = "png";

    private String srcAttribute = "src";
    private String mainNewsSrc = null;
    private String walletNewsSrc = null;
    private String mainNewsFullFileName = null;
    private String walletNewsFullFileName = null;

    private URL url = null;

    public MainPage() {
        super(By.xpath(MAIN_PAGE_LOCATOR), "Main page");
    }

    public void clickCurrencyRates() {
        currencyRates.click();
    }

    public void saveWalletNewsImage(String nameWalletNewsImage) {
        try {
            this.setWalletNewsFullFileName(nameWalletNewsImage, SAVE_IMAGES_PATH);

            ((JavascriptExecutor) AqualityServices.getBrowser().getDriver())
                    .executeScript("arguments[0].scrollIntoView();",
                            AqualityServices.getBrowser().getDriver()
                                    .findElement(walletNews.getLocator()));

            Thread.sleep(2000);

            AqualityServices.getBrowser().goTo(this.getUrlWalletNews().toString());

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_S);

            Thread.sleep(5000);

            StringSelection stringSelection = new StringSelection(
                    this.getWalletNewsFullFileName());

            Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(stringSelection, null);

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

    public void saveMainNewsImage(String nameMainNewsImage) {
        try {
            this.setMainNewsFullFileName(nameMainNewsImage, SAVE_IMAGES_PATH);

            ((JavascriptExecutor) AqualityServices.getBrowser().getDriver())
                    .executeScript("arguments[0].scrollIntoView();",
                            AqualityServices.getBrowser().getDriver()
                                    .findElement(mainNews.getLocator()));

            Thread.sleep(2000);

            AqualityServices.getBrowser().goTo(this.getUrlMainNews().toString());

            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_S);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_S);

            Thread.sleep(5000);

            StringSelection stringSelection = new StringSelection(
                    this.getMainNewsFullFileName());

            Toolkit.getDefaultToolkit().getSystemClipboard()
                    .setContents(stringSelection, null);

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

    public Boolean compareMainNewsImageWithWalletNewsImage() {
        try {
            BufferedImage image1 = ImageIO.read(new File(this.getMainNewsFullFileName()));
            BufferedImage image2 = ImageIO.read(new File(this.getWalletNewsFullFileName()));

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

    private String getAttributeSrcMainNews() {
        mainNewsSrc = AqualityServices.getBrowser().getDriver()
                .findElement(mainNews.getLocator())
                .getAttribute(srcAttribute);

        return mainNewsSrc;
    }

    private String getAttributeSrcWalletNews() {
        walletNewsSrc = AqualityServices.getBrowser().getDriver()
                .findElement(walletNews.getLocator())
                .getAttribute(srcAttribute);

        return walletNewsSrc;
    }

    private URL getUrlMainNews() {
        try {
            url = new URL(this.getAttributeSrcMainNews());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private URL getUrlWalletNews() {
        try {
            url = new URL(this.getAttributeSrcWalletNews());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    private String getMainNewsFullFileName() {
        return mainNewsFullFileName;
    }

    private String getWalletNewsFullFileName() {
        return walletNewsFullFileName;
    }

    private void setMainNewsFullFileName(String mainNewsName, String path) {
        if (mainNewsFullFileName == null)
            mainNewsFullFileName = System.getProperty("user.dir").concat("\\")
                    .concat(path).concat(mainNewsName).concat(".").concat(FORMAT_NAME);
    }

    private void setWalletNewsFullFileName(String walletNewsName, String path) {
        if (walletNewsFullFileName == null)
            walletNewsFullFileName = System.getProperty("user.dir").concat("\\")
                    .concat(path).concat(walletNewsName).concat(".").concat(FORMAT_NAME);
    }
}
