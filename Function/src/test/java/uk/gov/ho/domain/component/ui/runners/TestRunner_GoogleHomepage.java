package uk.gov.ho.domain.component.ui.runnersAW;
 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)


@CucumberOptions(
        tags = {"@test"},
        plugin = {"pretty"},
        features = "classpath:features/ui/GoogleHomepage.feature",
        glue = "uk.gov.ho.domain.component.ui.steps"
)

//@CucumberOptions(features="resources/features/GoogleHomepage.feature", glue="stepDefinitions")


public class TestRunner_GoogleHomepage {
 
}
