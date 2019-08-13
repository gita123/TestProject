package uk.gov.ho.domain.component.ui.pages;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;



//@DefaultUrl("https://www3.points.homeoffice.gov.uk/gui-sms-jsf/SMS-001-Landing.faces")


public class PageMetastorm extends PageObject {


    @FindBy(xpath = "//*[@id=\"TodoCell\"]/a/img")
    WebElement toDoButton;



    public void checkToDoButton() {


        if (toDoButton.isDisplayed()) {
            System.out.println("Web Server Is Up - To Do Button Displayed");
        }


    }


}
