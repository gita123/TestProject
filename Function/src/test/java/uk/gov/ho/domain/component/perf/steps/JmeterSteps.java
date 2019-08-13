package uk.gov.ho.domain.component.perf.steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.ho.domain.component.perf.stepLib.JmeterReport;
import uk.gov.ho.cts.utils.Configuration;
import uk.gov.ho.domain.component.perf.stepLib.JmeterProxy;
import uk.gov.ho.domain.component.perf.stepLib.JmeterExecute;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class JmeterSteps {

    private JmeterReport jreports = new JmeterReport();

    private static final int NINETY_FIFTH_INDEX = 5;
    private static final int NINETIETH_INDEX = 4;
    private static final int AVERAGE_INDEX = 2;
    private static final int PERCENT_INDEX = 9;
    private static final int THROUGHPUT_INDEX = 10;
    private static String jmxFileLocation = System.getProperty("user.dir") + File.separator + Configuration.get("jmeter.jmx.location");
    private static String reportLocation = System.getProperty("user.dir") + File.separator + Configuration.get("jmeter.report.location");
    private static String jmxDomain = Configuration.get("jmeter.jmx.endpoint");
    private String reportPrefix;
    private final Logger logger = LoggerFactory.getLogger(JmeterSteps.class);
    private final String jmeterHome = System.getProperty("user.dir") + File.separator + Configuration.get("jmeter.home");


    @Given("^I have the \"([^\"]*)\" test in my script directory$")
    public void checkScriptExistInDirectory(String scriptName) {
        // Check if script file exists
        String jmxFile = jmxFileLocation + scriptName;
        File f = new File(jmxFile);
        assertThat(f).exists().isFile();
    }

    @When("^the test script \"([^\"]*)\" is executed$")
    public void theTestIsExecuted(String scriptName) {
        reportPrefix = StringUtils.substringBefore(scriptName, ".");
        try {

            //Initialize JMeter
            JMeterUtils.setJMeterHome(jmeterHome);
            JMeterUtils.loadJMeterProperties(jmeterHome + File.separator + "bin" + File.separator + "jmeter.properties");
            JMeterUtils.loadProperties(jmeterHome + File.separator + "bin" + File.separator + "user.properties");
            JMeterUtils.setProperty("jmeter.domain", jmxDomain);

            String proxyDetails = JmeterProxy.getJmeterProxy();
            if (proxyDetails.isEmpty()) {
                JMeterUtils.setProperty("jmeter.proxy.host", "");
                JMeterUtils.setProperty("jmeter.proxy.port", "");
            } else {
                String[] proxyList = proxyDetails.split(":");
                JMeterUtils.setProperty("jmeter.proxy.host", proxyList[0]);
                JMeterUtils.setProperty("jmeter.proxy.port", proxyList[1]);
            }

            JMeterUtils.initLocale();
            SaveService.loadProperties();

            //Load existing .jmx Test Plan
            String scriptLocation = jmxFileLocation + scriptName;
            File f = new File(scriptLocation);
            assertThat(f).exists().isFile();

            JmeterExecute.jmeterExecution(scriptLocation, reportLocation, reportPrefix);

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Then("^the report is generated correctly with following average,error rate,90th percentile and 95th percentile$")
    public void theReportIsGeneratedCorrectly(DataTable resultValues) {

        try {
            jreports.generateJmeterReports(reportLocation, reportPrefix);

            String aggregateReportPath = reportLocation + reportPrefix + "AggReport.csv";
            File file = new File(aggregateReportPath);
            assertThat(file).exists().isFile();

            List<List<String>> data = resultValues.raw();
            data.stream().skip(1).forEach(list -> {

                String transName = list.get(0);
                String[] testNumber = transName.split("_");
                String avg = list.get(1);
                double error = Double.valueOf(list.get(2));
                String agg90th = list.get(3);
                String agg95th = list.get(4);
                double throughput = Double.valueOf(list.get(5));

                try {
                    String currentLine = Files.readAllLines(Paths.get(aggregateReportPath)).get(Integer.valueOf(testNumber[0]));
                    String[] aggRep = currentLine.split(",");
                    String errPercent = StringUtils.substringBefore(aggRep[PERCENT_INDEX], ".");
                    String average = aggRep[AVERAGE_INDEX];
                    String aggNinetieth = aggRep[NINETIETH_INDEX];
                    String aggNinetyFifth = aggRep[NINETY_FIFTH_INDEX];
                    String aggThroughput = aggRep[THROUGHPUT_INDEX];

                    assertThat(Double.parseDouble(errPercent)).isLessThanOrEqualTo(error);
                    assertThat(Integer.parseInt(average)).isLessThanOrEqualTo(Integer.parseInt(avg));
                    assertThat(Integer.parseInt(aggNinetieth)).isLessThanOrEqualTo(Integer.parseInt(agg90th));
                    assertThat(Integer.parseInt(aggNinetyFifth)).isLessThanOrEqualTo(Integer.parseInt(agg95th));
                    assertThat(Double.parseDouble(aggThroughput)).isGreaterThanOrEqualTo(throughput);
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            });

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

}