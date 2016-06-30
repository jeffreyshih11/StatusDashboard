package com.iworkscorp.dashboard.hudson;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


/**
 * This class is used to initialize webdriver and event firing properties of web driver
 * and will open a browser and maximize it.
 *
 * @author vpandey
 */
public class TestBase {
    public static final String CONFIG_PATH = "//src//main//resources//";

    // READ THE PROPERTIES FILE

    public static Properties CONFIG = null;
    public static Properties OR = null;
    public static Properties AutoOR = null;
    public static Properties PreconditionVariables = null;
    public static WebDriver dr = null;
    public static EventFiringWebDriver driver = null;

    public static void initialize() throws Exception {

        if (driver == null) {

            // initialize and LOAD config.properties file
            CONFIG = new Properties();
            FileInputStream fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "config.properties");
            CONFIG.load(fn);

            // initialize and LOAD OR.properties file
            OR = new Properties();
            fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "OR.properties");
            OR.load(fn);

            //Initialize and load AutoOR.properties file
            AutoOR = new Properties();
            fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "AutoOR.properties");
            AutoOR.load(fn);

            //Initialize and load PreconditionVariables.properties file
            PreconditionVariables = new Properties();
            fn = new FileInputStream(System.getProperty("user.dir") + CONFIG_PATH + "PreconditionVariables.properties");
            PreconditionVariables.load(fn);


            // initialize the WebDriver and EventFiringWebDriver
            if (CONFIG.getProperty("browser").equalsIgnoreCase("Firefox")) {
                FirefoxProfile profile = new FirefoxProfile();

                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.download.dir", "C:\\Users\\Public\\Documents\\RFAs\\RFA PDFs from app");
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
                profile.setPreference("pdfjs.disabled", true);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("plugin.scan.plid.all", false);
                profile.setPreference("plugin.scan.Acrobat", "99.0");

                dr = new FirefoxDriver(profile);

            } else if (CONFIG.getProperty("browser").equalsIgnoreCase("IE")) {
//				System.setProperty("webdriver.ie.driver", "C://IEDriverServer.exe");
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//IEDriverServer.exe");
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability("ignoreZoomSetting", true);
                caps.setCapability("ignoreProtectedModeSettings", true);
                caps.setCapability("enablePersistentHover", true);
                caps.setCapability("ie.ensureCleanSession", true);
                // caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);

                // Setting attribute nativeEvents to false enable click button in IE
                caps.setCapability("nativeEvents", false);
                System.out.println("The test is being performed in IE 11 Windows 7 OS.");

                dr = new InternetExplorerDriver(caps);

            } else if (CONFIG.getProperty("browser").equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//chromedriver.exe");
                dr = new ChromeDriver();

            }
            driver = new EventFiringWebDriver(dr);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize(); // maximize the browser window
            System.out.println("Initialization Complete");
        }
    }


    /**
     * webdriver will wait until the requested time to perform the next action
     *
     * @param sec - No of seconds
     */
    public static void pause(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        System.out.println("Logging out of JVS \n");
        driver.findElement(By.partialLinkText("off")).click();
        WaitingToLoad();
    }

    public static void catsLogout() {
        WaitingToLoad();

        //System.out.println("Logging out of CATS");
        //getObject("catsLogout").click();
        //com.iworks.DISS.test.common.functions.ReusableFunctions.waitUntilElementExistsAndClick(By.linkText("Logoff"));

        ReusableFunctions.getObjectByLinktext("Logoff").isEnabled();
        driver.navigate().to(CONFIG.getProperty("CATS_Url1"));
        WaitingToLoad();
        driver.navigate().to(CONFIG.getProperty("CATS_Url1"));
        pause(3);

    }

    public static void reportsLogout() {
        ReusableFunctions.getObjectByLinktext("Logoff").isEnabled();
        driver.navigate().to(CONFIG.getProperty("REPORTS_Url1"));
        WaitingToLoad();
        driver.navigate().to(CONFIG.getProperty("REPORTS_Url1"));
        pause(3);
    }

    public static void quitBrowser() {
        driver.close();
        driver.quit();
    }

    public static void driverClose() {

        if (driver != null) {
            try {
                driver.close();
                driver.quit();

            } catch (Exception e) {
                //swallow
            } finally {
                //driver = null;
            }
        }
    }


    /**
     * webdriver will wait implicitly until the requested time to perform the next action
     */
    public static void WaitingToLoad() {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebElement getObject(String xpathKey) {
        try {
            return driver.findElement(By.xpath(OR.getProperty(xpathKey)));
        } catch (Throwable t) {
            //report error
            return null;
        }
    }

}