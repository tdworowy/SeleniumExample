import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTests2 {

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
    assertFalse(mainPage.checkIfProductTableIsDisplay());
}
@Test
@DisplayName("ShouldFindCat")
public void ShouldFindCat(TestInfo testInfo) {
        String productName = "Persian";
        String linkText = "FL-DLH-02";
        mainPage.getLogger().info("Start Test: ".concat(testInfo.getDisplayName()));

        mainPage.ClickEnterStoreLink();
        mainPage.enterSearchText(productName);
        mainPage.ClickSearchButton();
        assertTrue(mainPage.waitForProductLink(linkText));
        assertTrue(mainPage.checkIfProductTableIsDisplay());
    }
}
