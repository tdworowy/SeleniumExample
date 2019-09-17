import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;
import java.time.Duration;

public class WebDriverWrapper {
    private Logger Log;
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private String ChromeDriverPath = "chromedriver/chromedriver.exe";

    public WebDriverWrapper(String className, boolean remote) throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);

       if (remote) {
             this.driver = DriverFactory.getDriver("remote");
        }
        else
        {
            this.driver =DriverFactory.getDriver("chrome");
        }
        this.Log = Logger.getLogger(className);
        PropertyConfigurator.configure("log4j.properties");

        this.wait = getFluentWait();

        Log.info("Create WebDriver");
    }
    private Wait<WebDriver> getFluentWait(){
      return new FluentWait <WebDriver>(this.driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
    public WebDriver getDriver() {
        return driver;
    }
    public void OpenPage(String url) {
        driver.get(url);
        Log.info("Open url: ".concat(url));
    }
    public void quitDriver() {
        driver.quit();
        Log.info("Close browser");
    }
    public Logger getLogger(){
        return Log;
    }
    public void waitUntilElementIsVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement findElementByName(String elementName) {
        return driver.findElement(By.name(elementName));
    }
    public WebElement findElementByLinkText(String text) {
        return driver.findElement(By.linkText(text));
    }
    public WebElement findElementById(String elementId) {
        return driver.findElement(By.id(elementId));
    }
    public WebElement findElementByCss(String css) {
        return driver.findElement(By.cssSelector(css));
    }
    public void clickOnElement(WebElement webElement){
        Log.info("Click on: ".concat(webElement.toString()));
        waitUntilElementIsVisible(webElement);
        webElement.click();
    }
    public void enterText(WebElement webElement, String text){
        Log.info("Enter text: ".concat(text));
        waitUntilElementIsVisible(webElement);
        webElement.click();
        webElement.sendKeys(text);
    }

}
