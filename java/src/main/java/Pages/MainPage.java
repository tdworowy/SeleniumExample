package WebDriverPackage;

import Utils.TestLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.MalformedURLException;

public class MainPage {

    private String url= "http://przyklady.javastart.pl/jpetstore/";
    private WebDriverWrapper webDriverWrapper;
    private TestLogger testLogger;

    private static final String enterStoreLink = "Enter the Store";
    @FindBy(linkText = enterStoreLink)
    private WebElement enterStore;

    private static final String searchButtonName = "searchProducts";
    @FindBy(name = searchButtonName)
    private WebElement searchButton;

    private static final String searchFiledName = "keyword";
    @FindBy(name = searchFiledName)
    private WebElement searchFiled;

    private static final String table = "table";
    @FindBy(css =table)
    private WebElement productTable;

    public TestLogger getLogger() {
       return testLogger;
    }
    public MainPage(TestLogger testLogger) {
        try {
            webDriverWrapper = new WebDriverWrapper(testLogger,false);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public MainPage openMainPage() {
        PageFactory.initElements(webDriverWrapper.getDriver(), this);
        webDriverWrapper.OpenPage(url);
        return this;
    }
    public WebDriverWrapper getWebDriverWrapper() {
        return webDriverWrapper;
    }
    public void ClickEnterStoreLink() {
        webDriverWrapper.clickOnElement(enterStore);
    }
    public void ClickSearchButton() {
        webDriverWrapper.clickOnElement(searchButton);
    }
    public void enterSearchText(String productName){
        webDriverWrapper.enterText(searchFiled, productName);
    }
    public boolean checkIfProductTableIsDisplay() {

        try {
            webDriverWrapper.waitUntilElementIsVisible(productTable);
            return true;
        }
        catch (Exception ex) {
            testLogger.getLog().error(ex.getMessage());
            return false;
        }
    }
    public boolean waitForProductLink(String productName){
        try {
            webDriverWrapper.takeScreenshot(productName);
            WebElement product = webDriverWrapper.findElementByLinkText(productName);
            webDriverWrapper.waitUntilElementIsVisible(product);
            return true;
        }
        catch (Exception ex) {
            testLogger.getLog().error(ex.getMessage());
            try {
                webDriverWrapper.takeScreenshot("Exeption");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
