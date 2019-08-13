package uk.gov.ho.domain.component.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.junit.Assert;



//@DefaultUrl("https://www3.points.homeoffice.gov.uk/gui-sms-jsf/SMS-001-Landing.faces")


public class PageCosCasChecker extends PageObject {

//    @FindBy(xpath = "//*[@id=\"centralContent\"]/div[1]/a") WebElement logoutLink; ??

    @FindBy(linkText = "Log out")
    WebElement logoutLink;
    @FindBy(linkText = "CAS check")
    WebElement casCheckLink;
    @FindBy(linkText = "CoS check")
    WebElement cosCheckLink;

    @FindBy(name = "j_username")
    WebElement userIDField;
    @FindBy(name = "j_password")
    WebElement passwordField;
    @FindBy(name = "login")
    WebElement loginButton;

    @FindBy(name = "cosSearchForm:cosNo")
    WebElement cosSearchCosNo;
    @FindBy(name = "cosSearchForm:search")
    WebElement cosSearchSearchButton;
    @FindBy(name = "cosSearchForm:back")
    WebElement cosSearchBackButton;

    @FindBy(name = "casSearchForm:casNo")
    WebElement casSearchCasNo;
    @FindBy(name = "casSearchForm:retrieveCAS")
    WebElement casRetrieveButton;

    @FindBy(className = "fixedData")
    List<WebElement> scanElements;


    public void enterTheUserIDAndPassword() {

        // Check Log in button is displayed to see if we actually have the page up

        if (loginButton.isDisplayed()) {

            System.out.println("The Login button is displayed");
            userIDField.sendKeys("ml-caseworker14");
            passwordField.sendKeys("Password101!");

        } else {

            System.out.println("The Login button is NOT displayed");

        }

    }


    public void clickOnTheLogInButton() {
        loginButton.submit();
        System.out.println("Login button clicked");
    }

    public void searchForACosNumber() {

        if (cosCheckLink.isDisplayed()) {
            System.out.println("CoS Link Displayed");
            cosCheckLink.click();
            cosSearchCosNo.sendKeys("C2G2O34575A");
            cosSearchSearchButton.click();
        } else{
            System.out.println("CoS Link NOT Displayed");
        }

    }


    public void checkCorrectCosResultReturned() {


        boolean correctCosResult;

        correctCosResult = false;

        for(WebElement ele:scanElements)
        {
            if (ele.getText().equals("C2G2O34575A")) {
                correctCosResult = true;
                break;
            }
        }

        Assert.assertTrue(correctCosResult);

    }


    public void clickOnTheBackButton() {

        if (cosSearchBackButton.isDisplayed()) {
            System.out.println("CoS Back Button Displayed");
            cosSearchBackButton.click();
        } else{
            System.out.println("CoS Back Button NOT Displayed");
        }


    }

    public void clickOnTheCasLink() {

        if (casCheckLink.isDisplayed()) {
            System.out.println("Cas Link Displayed");
            casCheckLink.click();
        } else {
                System.out.println("Cas Link NOT Displayed");
            }
        }


    public void retrieveACasNumber() {
        casSearchCasNo.sendKeys("E4C0JA8A15J0A9");
        casRetrieveButton.click();

    }

    public void checkCorrectCasResultReturned() {
        boolean correctCasResult;

        correctCasResult = false;

        for(WebElement ele:scanElements)
        {
            System.out.println(ele.getText());
            if (ele.getText().equals("E4C0JA8A15J0A9")) {
                System.out.println("Found it !!!!!!!!!!!!!");
                correctCasResult = true;
            }
        }

        Assert.assertTrue(correctCasResult);
    }

    public void clickOnLogoutLink() {
        if (logoutLink.isDisplayed()) {
            System.out.println("Logout Link is Displayed");
            logoutLink.click();
        } else{
            System.out.println("Log out link NOT Displayed");
        }
    }

}
