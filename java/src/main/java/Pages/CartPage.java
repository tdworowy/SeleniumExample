package Pages;

import Utils.TestLogger;
import WebDriverPackage.WebDriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    private WebDriverWrapper webDriverWrapper;
    private TestLogger testLogger;


    public CartPage(WebDriverWrapper webDriverWrapper, TestLogger testLogger) {
        this.webDriverWrapper = webDriverWrapper;
        PageFactory.initElements(webDriverWrapper.getDriver(), this);
        this.testLogger = testLogger;
    }
    public boolean checkIfProductIsInCart(String itemId){
        try {
            webDriverWrapper.takeScreenshot("cart");
            webDriverWrapper.findElementByCss("a[href=\"/jpetstore/actions/Catalog.action?viewItem=&itemId="+itemId+"\"]");
            return true;
        }
        catch (Exception ex) {
            testLogger.getLog().error(ex.getMessage());
            return false;
        }
    }
}
