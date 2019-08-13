package uk.gov.ho.domain.component.ui.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = {"@ui"},
        plugin = {"pretty"},
        features = "classpath:features/ui",
        glue = "uk.gov.ho.domain.component.ui.steps"
)
public class TestSuiteUi {


}
