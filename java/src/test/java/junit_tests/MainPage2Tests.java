package junit_tests;

import Pages.MainPage;
import Utils.TestLogger;
import WebDriverPackage.WebDriverWrapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage2Tests {

private MainPage mainPage;

public MainPage openMainPge(TestInfo testInfo) {
        TestLogger testLogger = new TestLogger(testInfo.getDisplayName());
    WebDriverWrapper webDriverWrapper= null;
    try {
        webDriverWrapper = new WebDriverWrapper(testLogger,false);
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    return new MainPage(webDriverWrapper,testLogger).openMainPage();
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

@ParameterizedTest(name = "Should Find Cat {0}_{1}")
@CsvSource({
        "Persian,FL-DLH-02",
        "Manx,FL-DSH-01"
})
public void ShouldFindCat(String catName,String linkText, TestInfo testInfo) {
        mainPage = openMainPge(testInfo);
        mainPage.getLogger().getLog().info("Start Test: ".concat(testInfo.getDisplayName()));

        mainPage.ClickEnterStoreLink();
        mainPage.enterSearchText(catName);
        mainPage.ClickSearchButton();
        assertTrue(mainPage.waitForProductLink(linkText));
        assertTrue(mainPage.checkIfProductTableIsDisplay());
    }
}
