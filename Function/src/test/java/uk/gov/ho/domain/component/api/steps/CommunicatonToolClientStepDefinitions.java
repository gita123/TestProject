package uk.gov.ho.domain.component.api.steps;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import uk.gov.ho.domain.component.api.stepLib.CommunicationToolClient;

public class CommunicatonToolClientStepDefinitions {
    @Steps
    private CommunicationToolClient communicationToolClient;

    private String mobile, email;

    @Before
    public void before(Scenario scenario) {
        Serenity.setSessionVariable(communicationToolClient.SCENARIO)
                .to(scenario.getName());
    }

    @Given("^I borrow a mobile$")
    public void i_borrow_a_mobile() {
        mobile = communicationToolClient.borrowMobile();
    }

    @Then("^I borrow an email$")
    public void i_borrow_an_email() {
        email = communicationToolClient.borrowAnEmail();
    }

    @Then("^I return a mobile$")
    public void i_return_a_mobile() {
        communicationToolClient.returnMobile(mobile);
    }

    @Then("^I return an email$")
    public void i_return_an_email() {
        communicationToolClient.returnAnEmail(email);
    }
}
