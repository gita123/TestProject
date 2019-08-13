package uk.gov.ho.domain.component.ui.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then        ;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import uk.gov.ho.domain.component.ui.stepLib.MetastormStepLib;

//import uk.gov.ho.domain.component.ui.pages.*;

public class StepDefsMetaStorm {


    @Steps
    MetastormStepLib metastormStepLib;

    @When("^I open Metastorm Homepage$")
    public void i_open_Metastorm_Homepage() { metastormStepLib.navigateToHomePage(); }

    @Then("^I check the todo button$")
    public void i_check_the_todo_button() { metastormStepLib.checkToDoButton(); }

    @Given("^I am on metastorm page$")
    public void i_am_on_metastorm_page()
    {
        metastormStepLib.navigateToMetastorm();
    }


}

