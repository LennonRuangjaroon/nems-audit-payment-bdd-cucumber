package com.truecorp.pushad.bdd;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Lennon
 *
 */
@Component
@ConfigurationProperties(prefix = "pushad")
public class TestHelper {

    private static final Logger logger = LoggerFactory.getLogger(TestHelper.class);
    private static final String SELENIUM_PORT_4444_TCP_ADD = "SELENIUM_PORT_4444_TCP_ADD";
    private static final String DSELENIUM_PORT_4444_TCP_POR = "DSELENIUM_PORT_4444_TCP_POR";
    
    private String profile;

    public WebDriver getDriver() throws MalformedURLException {
        logger.info("profile : {}", profile);

        if (profile.equals("development")) {
            String chromeRemoteDriver = "http://" + System.getenv(SELENIUM_PORT_4444_TCP_ADD)
                    + ':' + System.getenv(DSELENIUM_PORT_4444_TCP_POR) + "/wd/hub";
            logger.info("chromeRemoteDriver : {}", chromeRemoteDriver);

            return new RemoteWebDriver(new URL(chromeRemoteDriver),
                    DesiredCapabilities.chrome());
        } else {
            return new ChromeDriver();
        }
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
