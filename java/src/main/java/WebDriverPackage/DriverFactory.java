package WebDriverPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static String url = "http://192.168.0.104:4444/wd/hub";
    private static  String ChromeDriverPath = "resources/chromedriver/chromedriver.exe";

    public static WebDriver getDriver(DriversEnum driverEnum) throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
        WebDriver driver;

        if (driverEnum.equals(DriversEnum.ChromeRemote)) {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setBrowserName("chrome");
            driver = new RemoteWebDriver(new URL(url),capability);
        }
        if (driverEnum.equals(DriversEnum.Chrome)) {
            driver = new ChromeDriver();
        }
        return driver
    }

    public static void setUrl(String url) {
        url = url;
    }
}
