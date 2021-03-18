package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "html:target/default-cucumber-reports",
                "json:target/cucumber.json"//will generate json report but needs to be run from command-line
        },
        features = {"classpath:features"},
        glue = {"step_defs"},
        //tags = {"@googleTest"},
        dryRun = false
)
public class Runner {
}
