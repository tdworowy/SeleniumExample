package Pages;

import Utils.TestLogger;
import WebDriverPackage.WebDriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ProductDetails {

    private WebDriverWrapper webDriverWrapper;
    private TestLogger testLogger;

    public ProductDetails(WebDriverWrapper webDriverWrapper, TestLogger testLogger) {
        this.webDriverWrapper = webDriverWrapper;
        PageFactory.initElements(webDriverWrapper.getDriver(), this);
        this.testLogger = testLogger;
    }
    public CartPage addToCart(String productId) {
        WebElement cartLink = webDriverWrapper.findElementByCss("a[href=\"/jpetstore/actions/Cart.action?addItemToCart=&workingItemId="+productId+"\"]");
        webDriverWrapper.clickOnElement(cartLink);
        try {
            webDriverWrapper.takeScreenshot("AddToCart");
        } catch (IOException e) {
            testLogger.getLog().error(e.getMessage());
        }
        return new CartPage(webDriverWrapper,testLogger);
    }
}
