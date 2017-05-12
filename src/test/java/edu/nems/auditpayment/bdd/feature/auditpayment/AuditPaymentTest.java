
package edu.nems.auditpayment.bdd.feature.auditpayment;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Lennon
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature/auditPayment.feature", plugin = {
        "html:target/cucumber-html-report",
        "json:target/cucumber-json-report.json"
})
@ConfigurationProperties(prefix = "nems")
public class AuditPaymentTest {

    private static ChromeDriverService service;
    private static String chromeDriver = "src/test/resources/driver/chromedriver";

    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromeDriver))
                .usingPort(4500)
                .build();
        service.start();
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver(String chromeDriver) {
        this.chromeDriver = chromeDriver;
    }
}
