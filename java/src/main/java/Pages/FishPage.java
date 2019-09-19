package Pages;

import Utils.TestLogger;
import WebDriverPackage.WebDriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FishPage {
    private WebDriverWrapper webDriverWrapper;
    private TestLogger testLogger;

    public FishPage(WebDriverWrapper webDriverWrapper, TestLogger testLogger) {
        this.webDriverWrapper = webDriverWrapper;
        PageFactory.initElements(webDriverWrapper.getDriver(), this);
        this.testLogger = testLogger;
    }
    public CartPage addToCart(String productId,String itemId) {
        WebElement productIdEle = webDriverWrapper.findElementByLinkText(productId);
        webDriverWrapper.clickOnElement(productIdEle);
        ProductDetails productDetails = new ProductDetails(webDriverWrapper,testLogger);
        return productDetails.addToCart(itemId);
    }
}
