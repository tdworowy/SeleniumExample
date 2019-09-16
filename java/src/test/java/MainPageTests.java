import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainPageTests {

private MainPage mainPage;

@BeforeEach
public void beforeEach() {
    mainPage = new MainPage();
}
@AfterEach
public void afterEach () {
    mainPage.getWebDriverWrapper().quitDriver();
}


@Test
public void ShouldDisplayEmptySearch() {
    String testName = Thread.currentThread().getStackTrace()[2].getMethodName();
    mainPage.getLogger().info("Start Test: ".concat(testName));

    mainPage.ClickEnterStoreLink();
    mainPage.ClickSearchButton();
    assert !mainPage.checkIfProductTableIsDisplay();
}
@Test
public void ShouldFindFish() {
        String productName = "Angelfish";
        String linkText = "FI-SW-01";

        String testName = Thread.currentThread().getStackTrace()[2].getMethodName();
        mainPage.getLogger().info("Start Test: ".concat(testName));

        mainPage.ClickEnterStoreLink();
        mainPage.enterSearchText(productName);
        mainPage.ClickSearchButton();
        assert  mainPage.waitForProductLink(linkText);
        assert mainPage.checkIfProductTableIsDisplay();
    }
}
