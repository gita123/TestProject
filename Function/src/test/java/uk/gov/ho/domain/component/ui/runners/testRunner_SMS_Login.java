package uk.gov.ho.domain.component.ui.runnersAWh;
 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features/ui/SMS_Login.feature", glue="uk.gov.ho.domain.component.ui.steps")


public class testRunner_SMS_Login {

}

