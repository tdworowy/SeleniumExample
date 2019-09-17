import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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

@ParameterizedTest(name = "Should Find Cat: {0}, ID= {1}")
@CsvSource({
        "Persian,FL-DLH-02",
        "Manx,FL-DSH-01"
})
public void ShouldFindCat(String catName,String linkText, TestInfo testInfo) {
       mainPage.getLogger().info("Start Test: ".concat(testInfo.getDisplayName()));

        mainPage.ClickEnterStoreLink();
        mainPage.enterSearchText(catName);
        mainPage.ClickSearchButton();
        assertTrue(mainPage.waitForProductLink(linkText));
        assertTrue(mainPage.checkIfProductTableIsDisplay());
    }
}
