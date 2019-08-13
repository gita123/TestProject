package uk.gov.ho.domain.component.perf.runners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        tags = {"@perf"},
        plugin = {"pretty"},
        features = "classpath:features/perf",
        glue = "uk.gov.ho.domain.component.perf.steps"
)
public class TestSuitePerf {


}
