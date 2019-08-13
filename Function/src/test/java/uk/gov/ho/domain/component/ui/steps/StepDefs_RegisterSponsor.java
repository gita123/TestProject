package uk.gov.ho.domain.component.ui.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.gov.ho.domain.component.ui.pages.*;
import uk.gov.ho.domain.component.ui.stepLib.RegisterUiStepLib;
import uk.gov.ho.domain.component.ui.stepLib.ApplySponsorLicenceStepLib;
import net.thucydides.core.annotations.Steps;

public class StepDefs_RegisterSponsor {
	
	Page_RegisterSponsor sponsorRegistration = new Page_RegisterSponsor();

	@Steps
	RegisterUiStepLib registerUiStepLib;


	@When("^I open Sponsor App Homepage$")
	public void  i_open_Sponsor_App_Homepage() { registerUiStepLib.navigateToHomePage(); }

 	
	@Then("^I click on the registration link$")
        public void i_click_on_the_registration_link() { registerUiStepLib.selectLogRegistrationLink(); }	

	
	@When("^Enter registration name and email details$")
        public void enter_registration_name_and_email_details() { registerUiStepLib.enterRegistrationCredentials(); }


	@When("^Confirm confirmation of registration$")
        public void confirm_confirmation_of_registration() { registerUiStepLib.confirmRegistration(); }


//        *****************************************************


    @And("^read userID value$")
	public void read_userID_value()
	{
		registerUiStepLib.getUserID();
	}

	@And("^read password from registered email and login to sponsor portal$")
	public void read_data_from_email()
	{
		registerUiStepLib.readEmailData();
	}

	@And("^login to Sponsor app$")
	public void login_to_sponsor_app()
	{
		registerUiStepLib.loginSponsor();
	}


	

}

