
package edu.nems.auditpayment.bdd.feature.auditpaymentImport;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.nems.auditpayment.bdd.AbstractTest;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.IOException;
import java.net.URL;

/**
 * @author Lennon
 */

@ConfigurationProperties(prefix = "nems")
public class AuditPaymentImportSteps extends AbstractTest {

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


    @Given("^User is on the Payment Import page$")
    public void user_is_on_the_Payment_Import_page() {
        webDriver.manage().window().maximize();
        webDriver.get(url + "/payment/Import/import_file");
    }

    @When("^User input data field Year \"([^\"]*)\"$")
    public void user_input_data_field_Year(String input) {
        webDriver.findElement(By.name("im_edu_bgy")).sendKeys(input);
    }

    @When("^User input data field exam type \"([^\"]*)\"$")
    public void user_input_data_field_ประเภทการสอบ(String input) {
        webDriver.findElement(By.name("im_exam")).sendKeys(input);
    }

    @When("^User Choose file for attach$")
    public void user_Choose_file_for_attach() {
        webDriver.findElement(By.xpath(".//*[@id='datatable3']/tbody/tr/td[7]/center")).click();
    }

    @When("^User click import button$")
    public void user_click_import_button() {
        webDriver.findElement(By.className("btn btn-primary")).click();
    }

    @Then("^User should be import data successfully$")
    public void user_should_be_import_data_successfully() throws InterruptedException {
        webDriver.get(url + "/payment/Import/import_file");
        Thread.sleep(1000);

        WebElement payReceiver = webDriver.findElement(By.xpath("html/body/div[1]/section/section/div/div/div[3]/div/div/div/button[1]"));
        assertNotNull(payReceiver);
    }

    @Then("^User should be import data not be successfully$")
    public void user_should_be_import_data_not_be_successfully() throws InterruptedException {
        webDriver.get(url + "/payment/Import/import_file");
        Thread.sleep(1000);

        WebElement payReceiver = webDriver.findElement(By.xpath("html/body/div[1]/section/section/div/div/div[3]/div/div/div/button[1]"));
        assertNull(payReceiver);
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
