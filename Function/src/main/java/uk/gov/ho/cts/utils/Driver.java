package uk.gov.ho.cts.utils;

import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Driver implements DriverSource {

    String os = System.getProperty("os.name");
    String browser = Configuration.get("webdriver.browser");

    @Override
    public WebDriver newDriver() {

        switch (browser) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "/Users/geethaganabathula/Documents/geckodriver");
                System.setProperty("dom.webdriver.enabled", "false");
                return new FirefoxDriver();

            case "edge":
                return new EdgeDriver();

            case "phantomjs":
                return new PhantomJSDriver();

            case "chrome":
                ChromeOptions options = new ChromeOptions();
                System.setProperty("webdriver.chrome.driver","/Users/geethaganabathula/Downloads/chromedriver");
                System.setProperty("Site key", "6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI");
                System.setProperty("Secret key", "6LeIxAcTAAAAAGG-vFI1TnRWxMZNFuojJ4WifJWe");
                options.addArguments("--disable-extensions");
//                options.addArguments("--incognito");
                options.addArguments("start-maximized");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--no-first-run");
                if (os.contains("Linux")) {
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-gpu");
                }
                return new ChromeDriver(options);

            case "ie":
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setVersion("3.14.0");
                capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, "accept");
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                capabilities.setCapability("nativeEvents", false);
                capabilities.setCapability("disable-popup-blocking", false);
                capabilities.setCapability("enablePersistentHover", true);
                capabilities.setJavascriptEnabled(true);
                capabilities.setCapability("browser", "IE");
                capabilities.setCapability("browser_version", "11.0");
                capabilities.setCapability("os", "Windows");
                capabilities.setCapability("os_version", "10");
                capabilities.setCapability("resolution", "1024x768");
                capabilities.setCapability("name", "Bstack-[Java] Sample Test");
                capabilities.setCapability("browserstack.local", "true");
                capabilities.setCapability("browserstack.localIdentifier", "Test123");
                capabilities.setCapability("acceptSslCerts", "true");
                capabilities.setCapability("browserstack.ie.noFlash", "true");
                capabilities.setCapability("browserstack.ie.enablePopups", "true");
                capabilities.setCapability("browserstack.debug", "true");
                capabilities.setCapability("browserstack.console", "warnings");
                return new InternetExplorerDriver(capabilities);

            default:
                return null;
        }
    }


    @Override
    public boolean takesScreenshots() {
        return true;
    }
}
