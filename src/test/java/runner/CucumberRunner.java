package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "classpath:features",
        plugin = {"html:target/cucumber-html",
                "json:target/report.json"},
        glue = {"stepdefinitions", "hooks"}
)

public class CucumberRunner
{
}
