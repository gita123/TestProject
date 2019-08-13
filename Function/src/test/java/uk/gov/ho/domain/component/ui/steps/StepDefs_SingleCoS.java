package uk.gov.ho.domain.component.ui.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.gov.ho.domain.component.ui.pages.*;
import uk.gov.ho.domain.component.ui.stepLib.SingleCosUiStepLib;
import net.thucydides.core.annotations.Steps;
import uk.gov.ho.domain.component.ui.stepLib.SingleCosUiStepLib;

public class StepDefs_SingleCoS {

	@Steps
	SingleCosUiStepLib singleCosLoginUiStepLib;


	@When("^I open SMS Homepage$")
	public void i_open_SMS_Homepage() {  singleCosLoginUiStepLib.navigateToHomePage(); }



	@Then("^I verify that the page displays Log in link$")
	public void i_verify_that_the_page_displays_Log_in_link() { singleCosLoginUiStepLib.verifyPageDisplaysLogInLink(); }


	@When("^I select the Log in link$")
	public void i_select_the_Log_in_link() { singleCosLoginUiStepLib.selectLogInLink(); }


	@Then("^I verify that the SMS Log in page displays UserID field$")
	public void i_verify_that_the_SMS_Log_in_page_displays_UserID_field() { singleCosLoginUiStepLib.verifySMSLogInPageDisplaysUserIdField(); }


	@Then("^the page displays the Password field$")
	public void the_page_displays_the_Password_field() { singleCosLoginUiStepLib.pageDisplaysPasswordField(); }


	@Then("^the page displays Cancel button$")
	public void the_page_displays_Cancel_button() { singleCosLoginUiStepLib.pageDisplaysCancelButton(); }


	@Then("^the page displays Login button$")
	public void the_page_displays_Login_button() { singleCosLoginUiStepLib.pageDisplaysLogInButton(); }


	@Then("^I enter login credentials$")
	public void i_enter_login_credentials() { singleCosLoginUiStepLib.enterLoginCredentials(); }


	@Then("^verify the SMS Homepage displays Ok button$")
	public void verify_the_SMS_Homepage_displays_Ok_button() { singleCosLoginUiStepLib.verifySMSHomepageDisplaysOkButton(); }


	@Then("^verify the SMS Homepage displays Cancel button$")
	public void verify_the_SMS_Homepage_displays_Cancel_button() { singleCosLoginUiStepLib.verifySMSHomepageDisplaysOkButton(); }


	@Then("^I click the OK button$")
	public void i_click_the_OK_button() { singleCosLoginUiStepLib.clickOKbutton(); }


	@Then("^I log out$")
	public void i_log_out() { singleCosLoginUiStepLib.logout(); }


	
	////////////////////////////////////////////////////////////////////////////////
	
	
	
	

	@Then("^I select the Workers link$")
	public void i_select_the_Workers_link() {
		singleCosLoginUiStepLib.selectWorkersLink();
	}

	@Then("^I select the Create and Assign link$")
	public void i_select_the_Create_and_Assign_link() {
		singleCosLoginUiStepLib.selectCreateAndAssignLink();
	}

	@Then("^I choose European Union accession country answer from List of Values$")
	public void i_choose_European_Union_accession_country_answer_from_List_of_Values() {
		singleCosLoginUiStepLib.selectEuropeanUnionAccession();
	}

	@Then("^I choose the Tier from List of Values$")
	public void i_choose_the_Tier_from_List_of_Values() {
		singleCosLoginUiStepLib.selectTier();
	}

	@Then("^I choose the Category from List of Values$")
	public void i_choose_the_Category_from_List_of_Values() {
		singleCosLoginUiStepLib.selectCategory();
	}

	@Then("^I choose the Sub-Category from List of Values$")
	public void i_choose_the_Sub_Category_from_List_of_Values() {
		singleCosLoginUiStepLib.selectSubCategory();
	}

	@Then("^I select create a new single certificate$")
	public void i_select_create_a_new_single_certificate() {
		singleCosLoginUiStepLib.selectCreateAndAssign();
	}

	@Then("^Enter Personal information$")
	public void enter_Personal_information() {
		singleCosLoginUiStepLib.enterPersonalInformation();
	}

	@Then("^Enter Passport or travel document$")
	public void enter_Passport_or_travel_document() {
		singleCosLoginUiStepLib.enterPassportTraveldoc();
	}

	@Then("^Enter current home address$")
	public void enter_current_home_address() {
		singleCosLoginUiStepLib.enterCurrentAddress();
	}

	@Then("^Enter Work dates$")
	public void enter_Work_dates() {
		singleCosLoginUiStepLib.enterWorkDates();
	}

	@Then("^Enter Main work address in UK$")
	public void enter_Main_work_address_in_UK() {
		singleCosLoginUiStepLib.selectMainWorkAddressUK();
	}

	@Then("^Enter Migrants employment details and Save form$")
	public void enter_Migrants_employment_details_and_Save_form() {
		singleCosLoginUiStepLib.enterMigrantDetailsSaveForm();
	}

	@Then("^check the Assign button is available$")
	public void check_the_Assign_button_is_available() {
		singleCosLoginUiStepLib.checkAssignButtonAvailable();
	}

	@Then("^I click on the Assign button$")
	public void i_click_on_the_Assign_button() {
		singleCosLoginUiStepLib.clickAssignButton();
	}

	@When("^I click on the Assign CoS button$")
	public void i_click_on_the_Assign_CoS_button() {
		singleCosLoginUiStepLib.clickAssignCoSButton();
	}

	@When("^I click on the Ok button$")
	public void i_click_on_the_Ok_button() {
		singleCosLoginUiStepLib.clickOnlinePaymentOkButton();
	}

	@Then("^I check the correct Worldpay URL is displayed$")
	public void i_check_the_correct_Worldpay_URL_is_displayed() {
		singleCosLoginUiStepLib.checkWorldpayURL();
	}

}
