package uk.gov.ho.domain.component.ui.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.And;

//import uk.gov.ho.domain.component.ui.pages.*;
import uk.gov.ho.domain.component.ui.stepLib.CosCasCheckerStepLib;
import net.thucydides.core.annotations.Steps;

public class StepDefsCosCasChecker {


    @Steps
    CosCasCheckerStepLib cosCasCheckerStepLib;


    @When("^I open CosCas Check Homepage$")
    public void i_open_CosCas_Check_Homepage() { cosCasCheckerStepLib.navigateToHomePage(); }


    @And("^Enter the UserID and Password$")
    public void enter_the_UserID_and_Password() { cosCasCheckerStepLib.enterTheUserIDAndPassword(); }

    @And("^Click on the LogIn Button$")
    public void click_on_the_LogIn_Button() { cosCasCheckerStepLib.clickOnTheLogInButton(); }

    @And("^Search for a Cos Number$")
    public void search_for_a_Cos_Number() { cosCasCheckerStepLib.searchForACosNumber(); }

    @And("^Check correct Cos Result Returned$")
    public void check_correct_Cos_Result_Returned() { cosCasCheckerStepLib.checkCorrectCosResultReturned(); }

    @And("^I Click on the Back Button$")
    public void i_Click_on_the_Back_Button() { cosCasCheckerStepLib.clickOnTheBackButton(); }

    @And("^I Click on the Cas Link$")
    public void i_Click_on_the_Cas_Link() { cosCasCheckerStepLib.clickOnTheCasLink(); }

    @And("^Retrieve a Cas Number$")
    public void retrieve_a_Cas_Number() { cosCasCheckerStepLib.retrieveACasNumber(); }

    @And("^Check Correct Cas Result Returned$")
    public void check_Correct_Cas_Result_Returned() { cosCasCheckerStepLib.checkCorrectCasResultReturned(); }

    @And("^Click on Logout Link$")
    public void click_on_Logout_Link() { cosCasCheckerStepLib.clickOnLogoutLink(); }


}