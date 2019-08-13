package uk.gov.ho.domain.component.ui.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.ho.cts.utils.Driver;

import java.util.concurrent.TimeUnit;


public class Page_RegisterSponsor extends PageObject {

	   public String sponserPassword;
	   public String userID_value;

       @FindBy(xpath = "//*[@id=\"formSponsorHome:register\"]") WebElement registerLink;
       @FindBy(name = "sponsorregister:salutation") WebElement salutation;
       @FindBy(name = "sponsorregister:emailAddress") WebElement emailAddress;
       @FindBy(name = "sponsorregister:emailAddressRep") WebElement sponsorEmailAddress;
       @FindBy(name = "sponsorregister:register") WebElement sponsorRegister;
       @FindBy(name = "sponsorregisterconf:ok") WebElement sponsorConfirmOK;


//       ******************************************

	   @FindBy(xpath = "//*[@id=\"centralContent\"]/table/tbody/tr/td[2]") WebElement sponsorUserID;
	   @FindBy(name = "j_username") WebElement sponsorUserLoginID;
	   @FindBy(name = "j_password") WebElement sponsorUserLoginPwd;
	   @FindBy(name = "login") WebElement sponserLogIn;
	   @FindBy(name = "sponsorpwdchange:password") WebElement sponserCurrentPwd;
	   @FindBy(name = "sponsorpwdchange:newpassword") WebElement sponserNewPwd;
	   @FindBy(name = "sponsorpwdchange:confirmnewpassword") WebElement sponserConfirmNewPwd;
	   @FindBy(name = "sponsorpwdchange:submit") WebElement sponserNewPwdSubmit;
	   @FindBy(name = "sponsorpwdchangeconf:ok") WebElement sponserPwdChangeConfirm;



	public void selectLogRegistrationLink() {

		registerLink.click();

		System.out.println("Register link Select");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
		}

		String expectedTitle = "Sponsor Register";
		String actualTitle ;


		actualTitle = this.getTitle();
	    
		Assert.assertEquals(expectedTitle,actualTitle);

	}


	public void enterRegistrationCredentials() {


		salutation.sendKeys("Test Name");
//		emailAddress.sendKeys("v8f7q9k7b7b7q4q6@ukim-tech.slack.com");
//		sponsorEmailAddress.sendKeys("v8f7q9k7b7b7q4q6@ukim-tech.slack.com");
		emailAddress.sendKeys("pbs.inttesting@gmail.com");
		sponsorEmailAddress.sendKeys("pbs.inttesting@gmail.com");
		sponsorRegister.click();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException ie) {
		}
		System.out.println("Enter login credentials");

		String expectedTitle = "Sponsor Register Confirmation";
		String actualTitle;


	    actualTitle = this.getTitle();

	    System.out.println(actualTitle);

		Assert.assertEquals(expectedTitle,actualTitle);

	}

	public void confirmRegistration() {
		sponsorConfirmOK.click();
                System.out.println("Registration confirmed");   
	}


//	*******************************************************************************
	public String retrieveUserID()
	{
		userID_value = sponsorUserID.getText();
		System.out.println("User ID is :: " + userID_value);
		return userID_value;
	}

	public void retrieveEmailData()
	{
		String host = "iimap.gmail.com";
		String mailStoreType = "imap";
		String username = "pbs.inttesting@gmail.com";
		String password = "P1ssword!";
		try {
			sponserPassword = ReadEmailData.check(host, mailStoreType, username, password);
			System.out.println("Password from email is :: " + sponserPassword);
			System.out.println("email USERID is :: " + userID_value);
			System.out.println("email USER PWD is ::" + sponserPassword+"I'm writing password");
			sponsorUserLoginID.clear();
			sponsorUserLoginID.sendKeys(userID_value);
			sponsorUserLoginPwd.clear();
			sponsorUserLoginPwd.sendKeys(sponserPassword);
			sponserLogIn.click();
			Thread.sleep(3000);
			sponserCurrentPwd.sendKeys(sponserPassword);
			sponserNewPwd.sendKeys("Password1!");
			sponserConfirmNewPwd.sendKeys("Password1!");
			sponserNewPwdSubmit.click();
			Thread.sleep(3000);
			sponserPwdChangeConfirm.click();
			Thread.sleep(3000);

		}catch(Exception e)
		{
			System.err.println(e.getStackTrace());
		}

	}

	public void logInSponser()
	{
		try {
			sponsorUserLoginID.sendKeys("dkQyH1K6uf");
			sponsorUserLoginPwd.sendKeys("Password1!");
			sponserLogIn.click();
			Thread.sleep(3000);
		}catch (Exception e)
		{
			System.err.println(e.toString());
		}
	}
}
