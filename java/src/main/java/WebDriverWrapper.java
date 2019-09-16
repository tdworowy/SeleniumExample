import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import javax.lang.model.element.Name;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import com.sun.xml.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WebDriverWrapper {
    private Logger Log;
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public WebDriverWrapper(String className) {
        this.driver = new ChromeDriver();
        this.Log = Logger.getLogger(className);

        this.wait = new FluentWait <WebDriver>(this.driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        Log.info("Create WebDriver");
    }
    public WebDriver getDriver() {
        return driver;
    }
    public void OpenPage(String url) {
        driver.get(url);
        Log.info("Open url: ".concat(url));
    }
    public void QuitDriver() {
        driver.quit();
        Log.info("Close browser");
    }

    public void waitForElementById(String elementId) {
        Log.info("Wait for: ".concat(elementId));
        wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id(elementId));
            }
        });
    }

   public WebElement findElementByName(String elementName) {
        return driver.findElement(By.name(elementName));
    }
    public WebElement findElementById(String elementId) {
        return driver.findElement(By.id(elementId));
    }
    public WebElement findElementByCss(String css) {
        return driver.findElement(By.cssSelector(css));
    }
    public void ClickOnElement(WebElement webElement){
        Log.info("Click on: ".concat(webElement.toString()));
        webElement.click();
    }

}
