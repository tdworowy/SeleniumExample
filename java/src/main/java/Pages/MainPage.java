package Pages;

import Utils.TestLogger;
import WebDriverPackage.WebDriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class MainPage {

    private String url= "http://przyklady.javastart.pl/jpetstore/";
    private WebDriverWrapper webDriverWrapper;
    private TestLogger testLogger;

    private static final String enterStoreLink = "Enter the Store";
    @FindBy(linkText = enterStoreLink)
    private WebElement enterStore;

    private static final String fishLinkCss = "img[src=\"../images/sm_fish.gif\"";
    @FindBy(css = fishLinkCss)
    private WebElement fishLink;

    private static final String singInLink = "Sign In";
    @FindBy(linkText = singInLink)
    private WebElement singIn;

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
    public MainPage(WebDriverWrapper webDriverWrapper, TestLogger testLogger) {
        this.webDriverWrapper = webDriverWrapper;
        PageFactory.initElements(webDriverWrapper.getDriver(), this);
        this.testLogger = testLogger;
    }
    public MainPage openMainPage() {
        webDriverWrapper.OpenPage(url);
        return this;
    }
    public WebDriverWrapper getWebDriverWrapper() {
        return webDriverWrapper;
    }
    public MainPage ClickEnterStoreLink() {
        webDriverWrapper.clickOnElement(enterStore);
        return  this;
    }
    public MainPage ClickSearchButton() {
        webDriverWrapper.clickOnElement(searchButton);
        return this;
    }
    public MainPage enterSearchText(String productName){
        webDriverWrapper.enterText(searchFiled, productName);
        return this;
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
                testLogger.getLog().error(e.getMessage());
            }
            return false;
        }
    }
    public LoginPage openLoginPage(){
        webDriverWrapper.clickOnElement(singIn);
        return new LoginPage(webDriverWrapper,testLogger);
    }
    public FishPage openFishPage(){
        webDriverWrapper.clickOnElement(fishLink);
        return new FishPage(webDriverWrapper,testLogger);
    }

}
