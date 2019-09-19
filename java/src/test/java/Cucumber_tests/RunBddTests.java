package Cucumber_tests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/java/Features"},
        glue={"Steps"},
        plugin = { "pretty","html:target/cucumber-reports" })
public class RunBddTests {

}
