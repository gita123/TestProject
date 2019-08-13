package uk.gov.ho.domain.component.api.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = {"@api"},
        plugin = {"pretty"},
        features = "classpath:features/api",
        glue = "uk.gov.ho.domain.component.api.steps"
)
public class TestSuiteApi {


}
