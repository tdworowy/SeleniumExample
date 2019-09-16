import org.junit.jupiter.api.*;

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
@DisplayName("ShouldDisplayEmptySearch")
public void ShouldDisplayEmptySearch(TestInfo testInfo) {
    mainPage.getLogger().info("Start Test: ".concat(testInfo.getDisplayName()));

    mainPage.ClickEnterStoreLink();
    mainPage.ClickSearchButton();
    assert !mainPage.checkIfProductTableIsDisplay();
}
@Test
@DisplayName("ShouldFindFish")
public void ShouldFindFish(TestInfo testInfo) {
        String productName = "Angelfish";
        String linkText = "FI-SW-01";
        mainPage.getLogger().info("Start Test: ".concat(testInfo.getDisplayName()));

        mainPage.ClickEnterStoreLink();
        mainPage.enterSearchText(productName);
        mainPage.ClickSearchButton();
        assert  mainPage.waitForProductLink(linkText);
        assert mainPage.checkIfProductTableIsDisplay();
    }
}
