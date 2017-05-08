
package edu.nems.auditpayment.bdd.feature.auditpayment;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.nems.auditpayment.bdd.AbstractTest;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.IOException;
import java.net.URL;

/**
 * @author Lennon
 */

@ConfigurationProperties(prefix = "nems")
public class AuditPaymentSteps extends AbstractTest {

    private String chromeDriverServiceHost = "http://localhost:4500";

    private WebDriver webDriver;
    private String chromeDriver;
    private String url;

    @Before
    public void setUp() throws IOException {
        webDriver = new RemoteWebDriver(new URL(String.format(chromeDriverServiceHost)),
                DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {
        webDriver.quit();
    }

    @Given("^user open nems site$")
    public void user_open_nems_site() throws InterruptedException {
        webDriver.manage().window().maximize();
        webDriver.get(url + "/payment/Payment");
        Thread.sleep(3000);
    }

    @When("^user click menu$")
    public void user_click_menu() {
    }

    @Then("^user click save$")
    public void user_click_save() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getChromeDriver() {
        return chromeDriver;
    }

    public void setChromeDriver(String chromeDriver) {
        this.chromeDriver = chromeDriver;
    }

}// end class
