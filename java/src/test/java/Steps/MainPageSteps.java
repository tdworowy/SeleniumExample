package Steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInfo;
import WebDriverPackage.MainPage;

public class MainPageSteps {

    private MainPage mainPage;
    private  void  searchForAnimal(String animalName) {
        mainPage.enterSearchText(animalName);
        mainPage.ClickSearchButton();
    }
    @Before
    public void beforeScenario(Scenario scenario){
        mainPage = new MainPage();
        mainPage.getWebDriverWrapper().setTestName(scenario.getName());
        mainPage.getWebDriverWrapper().getLogger().info("Start scenario: ".concat(scenario.getName()));
    }

    @After
    public void afterScenario(){
        mainPage.getWebDriverWrapper().quitDriver();
    }

    @Given("^Entry store link is clicked$")
    public void ShouldDisplayEmptySearch()  throws Throwable {
         mainPage.ClickEnterStoreLink();
    }

    @When("^Search for cat '(.*?)'$")
    public void SearchForCat(String catName)  throws Throwable {
        searchForAnimal(catName);
    }

    @When("^Search for dog '(.*?)'$")
    public void SearchForDog(String dogName)  throws Throwable {
        searchForAnimal(dogName);
    }
    @Then("^Cat link Text is found '(.*?)'$")
    public void CatLinkTextIsFount(String linkText)  throws Throwable {
        Assertions.assertTrue(mainPage.waitForProductLink(linkText));
        Assertions.assertTrue(mainPage.checkIfProductTableIsDisplay());
    }
}
