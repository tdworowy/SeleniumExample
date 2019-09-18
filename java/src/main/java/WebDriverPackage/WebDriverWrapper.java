package WebDriverPackage;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public class WebDriverWrapper {
    private Logger Log;
    private FileAppender fileAppender;
    private WebDriver driver;
    private Wait<WebDriver> wait;
    private String ChromeDriverPath = "chromedriver/chromedriver.exe";
    private String className;
    private String testName;

    public WebDriverWrapper(String className, boolean remote) throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
        this.className = className;

       if (remote) { this.driver = DriverFactory.getDriver(DriversEnum.ChromeRemote); }
        else { this.driver =DriverFactory.getDriver(DriversEnum.Chrome);  }
        this.wait = getFluentWait();
        this.Log = createLogger(className);
        Log.info("Create WebDriver");
    }
    private Logger createLogger(String className) {
        Log = Logger.getLogger(className);
        return Log;
    }
    private void addLogAppender(String fileName) {
        String  appenderName = "FileLogger";
        Logger.getRootLogger().removeAppender(appenderName);

        fileAppender = new FileAppender();
        fileAppender.setName(appenderName);
        fileAppender.setFile(fileName);
        fileAppender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.setAppend(true);
        fileAppender.activateOptions();
        Logger.getRootLogger().addAppender(fileAppender);

    }
    public void takeScreenshot(String fileName) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(className +"/"+testName+"/"+(fileName).concat(".png")));
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
        Log.info("Find element by text: ".concat(text));
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
        Log.info("Browser log: ".concat(driver.manage().logs().get("browser").toJson().toString()));
    }
    public void enterText(WebElement webElement, String text){
        Log.info("Enter text: ".concat(text));
        waitUntilElementIsVisible(webElement);
        webElement.click();
        webElement.sendKeys(text);
        Log.info("Browser log: ".concat(driver.manage().logs().get("browser").toJson().toString()));
    }

    public void setTestName(String testName) {
        this.testName = testName;
        addLogAppender(className +"/"+testName+"/"+testName+".log");
    }
}
