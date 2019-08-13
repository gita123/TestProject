package uk.gov.ho.domain.component.ui.runnersAWh;
 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:features/ui/Single_CoS.feature", glue="uk.gov.ho.domain.component.ui.steps")

public class testRunner_Single_CoS {

}
