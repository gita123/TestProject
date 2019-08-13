package uk.gov.ho.domain.component.ui.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("https://www.gov.uk/")
public class GovHomePage extends PageObject {

    @FindBy(xpath = "//input[contains(@id,\"search-main-\")]")
    WebElement searchMain;

    public void Search(String term) {
        searchMain.sendKeys(term+ Keys.RETURN);
    }

}
