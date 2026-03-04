package org.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.HashMap;

public class Driver {
    static public WebDriver getAutoLocalDriver() {
        WebDriverManager.chromedriver().setup(); // sets up ChromeDriver automatically
//        WebDriverManager.firefoxdriver().setup();
        return new ChromeDriver();
//        return new FirefoxDriver();
    }

    static public WebDriver getLocalDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64");
//        System.setProperty("webdriver.gecko.driver", "C:\\path\\geckodriver.exe");
        ChromeOptions options = new ChromeOptions();
//        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(options);
//        return new FirefoxDriver(options);
    }

    public static RemoteWebDriver getRemoteDriver() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
//        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("browserVersion", "128.0");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
            put("enableVNC", true);
            put("enableLog", true);
            put("noSandbox", true);
            put("headless", true);
        }});
        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        return driver;
    }
}
