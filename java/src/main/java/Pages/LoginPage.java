package Pages;

import Utils.TestLogger;
import WebDriverPackage.WebDriverWrapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage {

    private WebDriverWrapper webDriverWrapper;
    private TestLogger testLogger;


    private static final String loginFiledName = "username";
    @FindBy(name = loginFiledName)
    private WebElement loginField;

    private static final String passwordFiledName = "password";
    @FindBy(name = passwordFiledName)
    private WebElement passwordField;

    private static final String loginButtonName = "signon";
    @FindBy(name = loginButtonName)
    private WebElement loginButton;

    public LoginPage(WebDriverWrapper webDriverWrapper,TestLogger testLogger) {
        this.webDriverWrapper = webDriverWrapper;
        PageFactory.initElements(webDriverWrapper.getDriver(), this);
        this.testLogger = testLogger;

    }
    public MainPage LogIn(String login, String password){
        testLogger.getLog().info("Log as: "+login+"password: "+password );
        webDriverWrapper.enterText(loginField,login);
        webDriverWrapper.enterText(passwordField,password);
        webDriverWrapper.clickOnElement(loginButton);
        try {
            webDriverWrapper.takeScreenshot("LogIn");
        } catch (IOException e) {
            testLogger.getLog().error(e.getMessage());
        }
        return new MainPage(webDriverWrapper,testLogger);
    }

}
