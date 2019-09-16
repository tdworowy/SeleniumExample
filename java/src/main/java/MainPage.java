import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage {

    private String url= "http://przyklady.javastart.pl/jpetstore/";
    private WebDriverWrapper webDriverWrapper;

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


    MainPage() {
        webDriverWrapper = new WebDriverWrapper(MainPage.class.getName());
        PageFactory.initElements(webDriverWrapper.getDriver(), this);
        webDriverWrapper.OpenPage(url);
    }
    WebDriverWrapper getWebDriverWrapper() {
        return webDriverWrapper;
    }
    void ClickEnterStoreLink() {
        webDriverWrapper.clickOnElement(enterStore);
    }
    void ClickSearchButton() {
        webDriverWrapper.clickOnElement(searchButton);
    }
    void enterSearchText(String productName){
        webDriverWrapper.enterText(searchFiled, productName);
    }
    boolean checkIfProductTableIsDisplay() {
        try {
            webDriverWrapper.waitUntilElementIsVisible(productTable);
            return true;
        }
        catch (Exception ex) {
            webDriverWrapper.getLogger().error(ex.getMessage());
            return false;
        }
    }
    boolean waitForProductLink(String productName){
        try {
            WebElement product = webDriverWrapper.findElementByLinkText(productName);
            webDriverWrapper.waitUntilElementIsVisible(product);
            return true;
        }
        catch (Exception ex) {
            webDriverWrapper.getLogger().error(ex.getMessage());
            return false;
        }
    }
}
