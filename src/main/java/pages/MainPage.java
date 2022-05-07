package pages;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MainPage extends Form {

    private static final String MAIN_PAGE_LOCATOR = "//div[@class='main-content-wrapper']";

    public MainPage() {
        super(By.xpath(MAIN_PAGE_LOCATOR), "Main page");
    }

}
