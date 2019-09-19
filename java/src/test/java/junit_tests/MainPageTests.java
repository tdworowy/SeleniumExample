package junit_tests;

import Utils.TestLogger;
import WebDriverPackage.MainPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTests {
    private MainPage mainPage;

public MainPage openMainPge(TestInfo testInfo) {
    TestLogger testLogger = new TestLogger(testInfo.getDisplayName());
    return new MainPage(testLogger).openMainPage();
}
@AfterEach
public void afterEach () {
    mainPage.getWebDriverWrapper().quitDriver();
}


@Test
@DisplayName("ShouldDisplayEmptySearch")
public void ShouldDisplayEmptySearch(TestInfo testInfo) {
    mainPage = openMainPge(testInfo);
    mainPage.getLogger().getLog().info("Start Test: ".concat(testInfo.getDisplayName()));

    mainPage.ClickEnterStoreLink();
    mainPage.ClickSearchButton();
    assertFalse(mainPage.checkIfProductTableIsDisplay());

}
@Test
@DisplayName("ShouldFindFish")
public void ShouldFindFish(TestInfo testInfo) {
        mainPage = openMainPge(testInfo);
        String productName = "Angelfish";
        String linkText = "FI-SW-01";
        mainPage.getLogger().getLog().info("Start Test: ".concat(testInfo.getDisplayName()));

        mainPage.ClickEnterStoreLink();
        mainPage.enterSearchText(productName);
        mainPage.ClickSearchButton();
        assertTrue(mainPage.waitForProductLink(linkText));
        assertTrue(mainPage.checkIfProductTableIsDisplay());
    }
}
