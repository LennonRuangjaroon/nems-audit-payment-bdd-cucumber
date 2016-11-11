
package com.truecorp.pushad.bdd.feature.setting;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.truecorp.pushad.bdd.AbstractTest;

import cucumber.api.java.Before;

/**
 * @author Lennon
 */

@ConfigurationProperties(prefix = "pushad")
public class SettingSteps extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(SettingSteps.class);
    private static boolean setUpfinished = false;
    private final static String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
    
    private WebDriver webDriver;
    private WebDriver webDriverNewWindows;
    private WebElement userNameEle;
    private WebElement paswordEle;
    
    private String chromeDriver;
    private String url;

    @Before
    public void setUp() {
        if (setUpfinished) {
            return;
        }
        System.setProperty(WEB_DRIVER_PROPERTY, chromeDriver);
        logger.info("chromeDriver : {}", chromeDriver);
        setUpfinished = true;
    }
    
    @AfterClass
    public static void tearDown() {
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver(String chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}// end class
