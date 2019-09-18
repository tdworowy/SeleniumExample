package WebDriverPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static String url = "http://192.168.0.104:4444/wd/hub";
    private static  String ChromeDriverPath = "chromedriver/chromedriver.exe";
    public static WebDriver getDriver(DriversEnum driver) throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", ChromeDriverPath);
        if (driver.equals(DriversEnum.ChromeRemote)) {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setBrowserName("chrome");
            return new RemoteWebDriver(new URL(url),capability);
        }
        if (driver.equals(DriversEnum.Chrome))
        {
            return new ChromeDriver();
        }
        return new ChromeDriver();
    }

    public static void setUrl(String url) {
        url = url;
    }
}
