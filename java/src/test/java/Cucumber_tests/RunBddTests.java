package Cucumber_tests;

import Steps.ShopSteps;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features={ "src/test/resources/features" },
        glue= { "Steps" },
        plugin = { "pretty","html:target/cucumber-reports" })
public class RunBddTests {
}
