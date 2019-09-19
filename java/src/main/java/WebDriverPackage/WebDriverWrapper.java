package WebDriverPackage;

import Utils.TestLogger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public class WebDriverWrapper {

    private TestLogger Logger;
    private WebDriver driver;
    private Wait<WebDriver> wait;

    public WebDriverWrapper(TestLogger Logger, boolean remote) throws MalformedURLException {

       if (remote) { this.driver = DriverFactory.getDriver(DriversEnum.ChromeRemote); }
       else { this.driver =DriverFactory.getDriver(DriversEnum.Chrome);  }

       this.wait = getFluentWait();
       this.Logger = Logger;
       Logger.getLog().info("Create WebDriver");
    }

    public void takeScreenshot(String fileName) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(Logger.getTestLogsDir()+"/"+Logger.getTestName()+"/"+(fileName).concat(".png")));
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
        Logger.getLog().info("Open url: ".concat(url));
    }
    public void quitDriver() {
        driver.quit();
        Logger.getLog().info("Close browser");
    }
    public void waitUntilElementIsVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement findElementByName(String elementName) {
        return driver.findElement(By.name(elementName));
    }
    public WebElement findElementByLinkText(String text) {
        Logger.getLog().info("Find element by text: ".concat(text));
        return driver.findElement(By.linkText(text));
    }
    public WebElement findElementById(String elementId) {
        return driver.findElement(By.id(elementId));
    }
    public WebElement findElementByCss(String css) {
        return driver.findElement(By.cssSelector(css));
    }
    public void clickOnElement(WebElement webElement){
        Logger.getLog().info("Click on: ".concat(webElement.toString()));
        waitUntilElementIsVisible(webElement);
        webElement.click();
        Logger.getLog().info("Browser log: ".concat(driver.manage().logs().get("browser").toJson().toString()));
    }
    public void enterText(WebElement webElement, String text){
        Logger.getLog().info("Enter text: ".concat(text));
        waitUntilElementIsVisible(webElement);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
        Logger.getLog().info("Browser log: ".concat(driver.manage().logs().get("browser").toJson().toString()));
    }


}
