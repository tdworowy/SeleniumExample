package Steps;

import Utils.TestLogger;
import WebDriverPackage.MainPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;

public class MainPageSteps {

    private MainPage mainPage;
    TestLogger testLogger;
    private  void  searchForAnimal(String animalName) {
        mainPage.enterSearchText(animalName);
        mainPage.ClickSearchButton();
    }
    @Before
    public void beforeScenario(Scenario scenario){
        testLogger = new TestLogger(scenario.getName());
        mainPage = new MainPage(testLogger);
        testLogger.getLog().info("Start scenario: ".concat(scenario.getName()));
    }

    @After
    public void afterScenario(){
        mainPage.getWebDriverWrapper().quitDriver();
    }

    @Given("^Main page is opened$")
    public void OpenMainPage()  throws Throwable {
        mainPage.openMainPage();
    }

    @Given("^Entry store link is clicked$")
    public void EnterStoreLink()  throws Throwable {
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
