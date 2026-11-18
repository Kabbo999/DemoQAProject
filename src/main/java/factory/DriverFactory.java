// java
package factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.io.Files;

public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public OptionsManager optionsManager;
    public static String highlight;
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver initDrivers(Properties prop) {

        this.prop = prop;
        highlight = prop.getProperty("highlight", "false").trim();
        String browserName = prop.getProperty("browser", "chrome").toLowerCase().trim();

        // Initialize OptionsManager with properties (same package)
        optionsManager = new OptionsManager(prop);

        System.out.println("browser name is : " + browserName);

        if (browserName.equalsIgnoreCase("chrome")) {
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            tlDriver.set(new FirefoxDriver(optionsManager.getFireFoxOptions()));
        } else if (browserName.equalsIgnoreCase("edge")) {
            tlDriver.set(new EdgeDriver(optionsManager.getEdgebrowserOptions()));
        } else if (browserName.equalsIgnoreCase("safari")) {
            tlDriver.set(new SafariDriver(optionsManager.getSafariOptions()));
        } else {
            System.out.println("Please pass the right browser: " + browserName);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().get(prop.getProperty("url"));
        return getDriver();
    }

    public synchronized static WebDriver getDriver() {
        return tlDriver.get();
    }

    public Properties initProp() {
        prop = new Properties();
        String path = System.getProperty("user.dir") + "/src/main/resources/Config/config.properties";
        try (FileInputStream ip = new FileInputStream(path)) {
            prop.load(ip);
        } catch (FileNotFoundException e) {
            System.err.println("Config file not found: " + path);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String dir = System.getProperty("user.dir") + "/screenshots/";
        new File(dir).mkdirs();
        String path = dir + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            Files.copy(srcFile, destination);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return path;
    }
}