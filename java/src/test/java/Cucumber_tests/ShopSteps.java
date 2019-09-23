package Cucumber_tests;

import Pages.CartPage;
import Pages.FishPage;
import Pages.MainPage;
import Utils.TestLogger;
import WebDriverPackage.WebDriverWrapper;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.net.MalformedURLException;
import java.util.Random;

public class ShopSteps {

    private MainPage mainPage;
    private FishPage fishPage;
    private CartPage cartPage;

    TestLogger testLogger;
    private  void  searchForAnimal(String animalName) {
        mainPage.enterSearchText(animalName);
        mainPage.ClickSearchButton();
    }
    @Before
    public void beforeScenario(Scenario scenario){

        testLogger = new TestLogger(scenario.getName());
        WebDriverWrapper webDriverWrapper = null;
        try {
            webDriverWrapper = new WebDriverWrapper(testLogger,false);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        mainPage = new MainPage(webDriverWrapper, testLogger);
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
    @Given("^user is log in '(.*?)' '(.*?)'$")
    public void LogIn(String login, String password)  throws Throwable {
        mainPage.openLoginPage().LogIn(login, password);
    }
    @Given("^fish page is opened$")
    public void OpenFishPage()  throws Throwable {
        fishPage = mainPage.openFishPage();
    }
    @Given("^Entry store link is clicked$")
    public void EnterStoreLink()  throws Throwable {
           mainPage.ClickEnterStoreLink();
    }

    @When("^Search for cat '(.*?)'$")
    public void SearchForCat(String catName)  throws Throwable {
        searchForAnimal(catName);
    }
    @When("^Add '(.*?)' '(.*?)' to cart$")
    public void AddFishToCart(String fishId, String itemId)  throws Throwable {
        cartPage = fishPage.addToCart(fishId,itemId);
    }

    @When("^Search for dog '(.*?)'$")
    public void SearchForDog(String dogName)  throws Throwable {
        searchForAnimal(dogName);
    }
    @When("^Pass$")
    public void Pass()  throws Throwable {
        int x; // Do nothing
    }
    @Then("^Cat link Text is found '(.*?)'$")
    public void CatLinkTextIsFount(String linkText)  throws Throwable {
        Assert.assertTrue(mainPage.waitForProductLink(linkText));
        Assert.assertTrue(mainPage.checkIfProductTableIsDisplay());
    }
    @Then("^fish '(.*?)' is in the cart$")
    public void FishIsInCar(String itemId)  throws Throwable {
        Assert.assertTrue(cartPage.checkIfProductIsInCart(itemId));
    }
    @Then("^SometimesFail")
    public void SometimesFail()  throws Throwable {
        int randomInteger = new Random().nextInt(2);
        Assert.assertEquals(randomInteger,1);
    }
}
