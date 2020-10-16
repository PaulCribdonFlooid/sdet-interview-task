package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"json:target/report.json",
                "de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber-html"},
        glue = {"stepdefinitions", "hooks"},
        publish = true
)

public class CucumberRunner
{
}
