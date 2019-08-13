package uk.gov.ho.domain.component.api.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import uk.gov.ho.domain.component.api.stepLib.ApiActions;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ApiStepDefinitions {

    @Steps
    private static ApiActions apiActions;

    @Given("^the \"([^\"]*)\" endpoint is available$")
    public void theEndpointIsAvailable(String name) {
        apiActions.restEndpointIsAvailable(name);
    }

    @When("^I send SOAP request$")
    public void iSendSoapRequest() {
        //System.out.print(System.getProperty("os.name"));


      /*  String soapString = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:new=\"http://pbs.pidp.fujitsu.bia.homeoffice.gov.uk/notification/newsponsorapplication/\">"
                          + "<soapenv:Header/>"
                          + "<soapenv:Body>"
                          + "<new:NewSponsorApplication>"
                          + "<!--Optional:-->"
                          + "<new:SponsorApplicationURN>SPL6666600087</new:SponsorApplicationURN>"
                          + "</new:NewSponsorApplication>"
                          + "</soapenv:Body>"
                          + "</soapenv:Envelope>";   */




        apiActions.sendSoapRequest();



    }

    @Then("^Then I get a valid response back$")
    public void iShouldGetBack() {
//        assertThat(apiActions.getResponseBody(), apiActions.getResponseStatusCode(), is(responseCode));

    }

/*   @When("^I set the endpoint$")
    public void iSetTheEndpoint() {
        apiActions.setEndpoint();
    }

    @When("^I get \"([^\"]*)\" resource$")
    public void iGetResource(String resourceName) {
        apiActions.getResource(resourceName);
    }  */


}