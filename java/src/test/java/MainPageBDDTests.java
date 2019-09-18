import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(features={"src/test/java/Features"},
                 glue={"Steps"},
                 plugin = { "pretty","html:target/cucumber-reports" })
@RunWith(Cucumber.class)
public class MainPageBDDTests {
}
