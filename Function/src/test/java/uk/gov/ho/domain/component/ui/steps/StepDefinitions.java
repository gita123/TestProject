package uk.gov.ho.domain.component.ui.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.gov.ho.domain.component.ui.pages.*;
import uk.gov.ho.domain.component.ui.stepLib.GoogleUiStepLib;
import net.thucydides.core.annotations.Steps;

public class StepDefinitions {
	
	Page_GoogleHomepage googleHomepage = new Page_GoogleHomepage();

	
	/*@Given("^I launch Chrome browser$")
	public void i_launch_Chrome_browser() throws Exception {
		googleHomepage.launchBrowser();
	
	}*/

	@Steps
	GoogleUiStepLib googleUiStepLib;

	@When("^I open Google Homepage$")
	public void iOpenGoogle() {
		googleUiStepLib.navigateToGoogleHomePage();
	}



/*	@When("^I open Google Homepage$")
	public void i_open_Google_Homepage() throws Exception {
		googleHomepage.openGoogleURL();
		
	} */
	
	@Then("^I verify that the page displays search text box$")
	public void i_verify_that_the_page_displays_search_text_box() { googleUiStepLib.checkSearchBoxIsDisplayed(); }

	
	@Then("^the page displays Google Search button$")
	public void the_page_displays_Google_Search_button() { googleUiStepLib.checkSearchButtonIsDisplayed(); }

	/*
	@Then("^the page displays Im Feeling Lucky button$")
	public void the_page_displays_Im_Feeling_Lucky_button() throws Exception {
		googleHomepage.checkImFeelingLuckyButtonIsDisplayed();
	
	}
	
	@Then("^I click the gmail link$")
	public void i_click_the_gmail_link() throws Exception{
		googleHomepage.clickGMaillink();
	} */
}
