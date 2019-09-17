import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private String url = "http://192.168.0.104:4444/wd/hub";
    public static WebDriver getDriver(DriversEnum driver) throws MalformedURLException {

        if (driver.equals(DriversEnum.Chrome_Remote)) {
            DesiredCapabilities capability = new DesiredCapabilities();
            capability.setBrowserName("chrome");
            return new RemoteWebDriver(new URL("http://192.168.0.104:4444/wd/hub"),capability);
        }
        if (driver.equals(DriversEnum.Chrome))
        {
            return new ChromeDriver();
        }
        return new ChromeDriver();
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
