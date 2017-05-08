
package edu.nems.auditpayment.bdd.feature.auditpayment;

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

    @Given("^User is on the Payment page$")
    public void user_is_on_the_Payment_page() {
        webDriver.manage().window().maximize();
        webDriver.get(url + "/payment/Payment");
    }

    @When("^User input data field Reference Number Payment with \"([^\"]*)\"$")
    public void user_input_data_field_Reference_Number_Payment_with(String input) {
        webDriver.findElement(By.name("pay_bill")).sendKeys(input);
    }

    @When("^User input data field Date of payment with \"([^\"]*)\"$")
    public void user_input_data_field_Date_of_payment_with(String input) {
        webDriver.findElement(By.name("pay_date")).sendKeys(input);
    }

    @When("^User input data field Payment status with \"([^\"]*)\"$")
    public void user_input_data_field_Payment_status_with(String input) {
        switch (input) {
            case "ชำระเรียบร้อย":
                input = "1";
                break;
            case "ไม่พบข้อมูล/ไม่มีข้อมูลชำระเงิน":
                input = "2";
                break;
            case "ชำระไม่ครบตามจำนวน":
                input = "3";
                break;
        }
        webDriver.findElement(By.name("pay_bs_id")).sendKeys(input);
    }

    @When("^User input data field Amount with \"([^\"]*)\"$")
    public void user_input_data_field_Amount_with(String input) {
        webDriver.findElement(By.name("pay_amount")).sendKeys(input);
    }

    @When("^User input data field Payee with \"([^\"]*)\"$")
    public void user_input_data_field_Payee_with(String input) {
        webDriver.findElement(By.name("pay_receiver")).sendKeys(input);
    }

    @When("^User click save button to insert data$")
    public void user_click_save_button_to_insert_data() {
        webDriver.findElement(By.xpath("/html/body/div/section/section[1]/div/div/div[2]/form/fieldset[2]/div/div/button[1]")).click();

    }

    @Then("^User should see record the data successfully and then display the data in the table\\.$")
    public void user_should_see_record_the_data_successfully_and_then_display_the_data_in_the_table() throws InterruptedException {
        webDriver.get(url + "/payment/Payment");
        Thread.sleep(1000);

        WebElement payReceiver = webDriver.findElement(By.xpath(".//*[@id='datatable3']/tbody/tr/td[7]/center"));
        assertNotNull(payReceiver);
        assertEquals("Admin Test", payReceiver.getText());
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
