package uk.gov.ho.domain.component.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//@DefaultUrl("https://www3.points.homeoffice.gov.uk/gui-sms-jsf/SMS-001-Landing.faces")


public class Page_SMSLogin extends PageObject {

	@FindBy(xpath = "//*[@id=\"centralContent\"]/div[1]/a") WebElement loginLink;
	@FindBy(name = "j_username") WebElement userIDField;
	@FindBy(name = "j_password") WebElement passwordField;
	@FindBy(name = "j_id241:Cancel") WebElement cancelButton;
	@FindBy(name = "login") WebElement loginButton;
	@FindBy(name = "smsNoActiveMessages:ackNoActiveMessages") WebElement smsHomePageOKButton;
	@FindBy(name = "j_id11:smsNavigation:logout") WebElement logout;

			
	public void verifyPageDisplaysLogInLink() { 
	// check login link is displayed
		if(loginLink.isDisplayed())
		{
			System.out.println("The Login link is displayed");			
		} else {
			System.out.println("The Login link is NOT displayed");
		}
	}
		
	public void selectLogInLink() {
	// click login link
		loginLink.click();
	System.out.println("Select Login link");
	}

	public void verifySMSLogInPageDisplaysUserIdField() {
	// check user id field is displayed
		if (userIDField.isDisplayed()) {
			System.out.println("UserID field is displayed");
		} else {
			System.out.println("UserID field is NOT displayed");
		}
	}
	
	public void pageDisplaysPasswordField() {
	// check password field is displayed
		if(passwordField.isDisplayed()) {
			System.out.println("Password field is displayed");
		} else {
			System.out.println("Password field is NOT displayed");
		}
	}

	public void pageDisplaysCancelButton() {
	// check cancel button is displayed
		if(cancelButton.isDisplayed()) {
			System.out.println("Cancel button is displayed");
		} else {
			System.out.println("Cancel button is NOT displayed");
		}
	}

	public void pageDisplaysLogInButton() {
	// check login button is displayed
		if(loginButton.isDisplayed()) {
			System.out.println("Login button is displayed");
		} else {
			System.out.println("Login button is NOT displayed");
		}
	}

	public void enterLoginCredentials() {
	// enter login details and select login button
		userIDField.sendKeys("wrLwK2fdx");
		passwordField.sendKeys("Password1");
		loginButton.submit();
		try{Thread.sleep(5000);}catch(InterruptedException ie){}
	System.out.println("Enter login credentials");
	
	String expectedTitle = "SMS message board - no active messages";
    String actualTitle = "";
	
	    
   // get the actual value of the title
    // DGB - actualTitle = driver.getTitle();


	actualTitle = this.getTitle();

    /*
     * compare the actual title of the page with the expected one and print
     * the result
     */
    if (actualTitle.contentEquals(expectedTitle)){
        System.out.println("Login Successful");
    } else {
        System.out.printf("Login Unsuccessful, actual webpage is:" + actualTitle );
     // close the Browser 
	 //	driver.close();
		System.out.println("Test terminated as login failed");
    }
}

	public void verifySMSHomepageDisplaysOkButton() {
	// check ok button is displayed for 
		if(smsHomePageOKButton.isDisplayed()) {
			System.out.println("SMS HomePage OK button is displayed");
		} else {
			System.out.println("SMS HomePage OK button is NOT displayed");
		}
	}

	public void clickOKbutton() {
	    smsHomePageOKButton.click();
        System.out.println("Click OK button verification");   
	}
	
	public void logout() {
		logout.click();
		System.out.println("Logout");   
		//driver.close();
	}

}
