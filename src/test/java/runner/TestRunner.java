package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = {"step_definitions"},
        plugin = {"pretty", "summary", "html:target/CucumberReports/reports.html",
                 "me.jvt.cucumber.report.PrettyReports:target/cucumber-reports"},
        publish = true,
        tags = "@negative_test"
)

public class TestRunner {
}
