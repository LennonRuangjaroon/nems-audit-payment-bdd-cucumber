
package com.truecorp.pushad.bdd;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Lennon
 */

@SpringApplicationConfiguration(classes = TestApplication.class)
@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
@ActiveProfiles("development,local")
public abstract class AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTest.class);

    private String profile;

    public WebDriver getDriver() throws MalformedURLException {
        logger.info("profile : {}", profile);

        if (profile.equals("development")) {
            String chromeRemoteDriver = "http://" + System.getenv("SELENIUM_PORT_4444_TCP_ADD")
                    + ':' + System.getenv("DSELENIUM_PORT_4444_TCP_POR") + "/wd/hub";
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
