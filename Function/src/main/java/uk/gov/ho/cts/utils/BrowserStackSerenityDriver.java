package uk.gov.ho.cts.utils;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.browserstack.local.Local;

import javax.sound.midi.SysexMessage;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class BrowserStackSerenityDriver implements DriverSource {
    static Local bsLocal;
    public WebDriver newDriver() {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String propertyFile = "credentials/browserstackauth.properties";
        String username = Configuration.readCredentials(propertyFile, "user");
        String accessKey = Configuration.readCredentials(propertyFile, "key");
        String environment = System.getProperty("environment");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        Iterator it = environmentVariables.getKeys().iterator();
        while (it.hasNext()) {
            System.out.println("Inside Browser Stack iteration loop");

            String key = (String) it.next();
            System.out.println("keys are :: " + key);

            if (key.contains("proxy")) {
                System.getProperties().put(key, environmentVariables.getProperty(key));
            } else if (key.equals("browserstack.user") || key.equals("browserstack.key") || key.equals("browserstack.server")) {
                continue;
            } else if (key.startsWith("browserstack.")) {
                if (key.contains("debug")) {
                    capabilities.setCapability(key, environmentVariables.getProperty(key));
                } else {
                    capabilities.setCapability(key.replace("browserstack.", ""), environmentVariables.getProperty(key));
                    if (key.equals("browserstack.local")) {
                        System.setProperty("browserstack.local", "true");
                        System.out.println("browser stack local value :: " + System.getProperty("browserstack.local"));

                    }
                }
            }
            //Environment specific capabilities.
            else if (environment != null && key.startsWith("environment." + environment)) {
                capabilities.setCapability(key.replace("environment." + environment + ".", ""), environmentVariables.getProperty(key));
                if (key.equals("environment." + environment + ".browserstack.local")) {
                    System.setProperty("browserstack.local", "true");
                    System.out.println("browser stack local value :: " + System.getProperty("browserstack.local"));
                }
            }
        }

        try {
            System.out.println("************************* I'm here *******************************************");
            System.out.println("https://" + username + ":" + accessKey + "@" + environmentVariables.getProperty("browserstack.server") + "/wd/hub");
            System.out.println("capabilities are :: " + capabilities);
            return new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + environmentVariables.getProperty("browserstack.server") + "/wd/hub"), capabilities);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

//        @BeforeClass
//        public static void setUp() throws Exception {
//            EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
//
//            String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
//            if(accessKey == null) {
//                accessKey = (String) environmentVariables.getProperty("browserstack.key");
//            }
//
//            String environment = System.getProperty("environment");
//            String key = "browserstack.local";
//            boolean is_local = environmentVariables.getProperty(key) != null && environmentVariables.getProperty(key).equals("true");
//
//            if(environment != null && !is_local){
//                key = "environment."+environment+".browserstack.local";
//                is_local = environmentVariables.getProperty(key) != null && environmentVariables.getProperty(key).equals("true");
//            }
//
//            if(is_local){
//                bsLocal = new Local();
//                Map<String, String> bsLocalArgs = new HashMap<>();
//                bsLocalArgs.put("key", accessKey);
//                bsLocal.start(bsLocalArgs);
//            }
//        }
//
//        @AfterClass
//        public static void tearDown() throws Exception {
//            if(bsLocal != null) {
//                bsLocal.stop();
//            }
//        }

    public boolean takesScreenshots() {
        return true;
    }
}
