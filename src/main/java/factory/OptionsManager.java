// java
package factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class OptionsManager {

    private final Properties prop;

    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless", "false"))) {
            // use new headless flag for modern Chrome or fallback
            options.addArguments("--headless=new");
        }
        // add other options from properties if needed
        return options;
    }

    public FirefoxOptions getFireFoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless", "false"))) {
            options.addArguments("-headless");
        }
        return options;
    }

    public EdgeOptions getEdgebrowserOptions() {
        EdgeOptions options = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        return options;
    }

    public SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        // Safari headless is not generally supported; keep default
        return options;
    }
}