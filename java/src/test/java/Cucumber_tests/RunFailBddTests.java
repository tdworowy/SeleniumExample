//package Cucumber_tests;
//
//import io.cucumber.junit.CucumberOptions;
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//
//@CucumberOptions(plugin = { "pretty","html:target/cucumber-reports" })
//public class RunBddTests extends AbstractTestNGCucumberTests {
//}
package Cucumber_tests;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = {"@rerun/failed_scenarios.txt"},
//        plugin = { "pretty","html:target/cucumber-reports","io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
//                "json:target/cucumber-html-reports/cucumber.json",
//        })
//public class RunFailBddTests {
//}
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"@rerun/failed_scenarios.txt"},
        plugin = { "pretty","html:target/cucumber-reports","io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
                "json:target/cucumber-html-reports/cucumber.json",
        })
public class RunFailBddTests {
}