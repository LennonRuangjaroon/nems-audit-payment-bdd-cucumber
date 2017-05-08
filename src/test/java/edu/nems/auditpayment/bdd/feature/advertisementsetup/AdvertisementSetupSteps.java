
package edu.nems.auditpayment.bdd.feature.advertisementsetup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import edu.nems.auditpayment.bdd.AbstractTest;
import edu.nems.auditpayment.bdd.TestHelper;
import org.apache.commons.lang.time.DateUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Lennon
 */

@ConfigurationProperties(prefix = "pushad")
public class AdvertisementSetupSteps extends AbstractTest {

    @Autowired
    private TestHelper testHelper;

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementSetupSteps.class);
    private static boolean setUpfinished = false;
    private final static String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";

    private WebDriver webDriver;
    private WebElement userNameEle;
    private WebElement paswordEle;

    private String chromeDriver;
    private String url;

    private String jobName;
    private String jobId;

    private String profile;

    @Before
    public void setUp() throws MalformedURLException {
        if (setUpfinished) {
            return;
        }

        if (profile.equals("local")) {
            System.setProperty(WEB_DRIVER_PROPERTY, chromeDriver);
            logger.info("chromeDriver : {}", chromeDriver);
        }
        setUpfinished = true;
    }

    @Given("^user login pushad with \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_login_pushad_with(String username, String password)
            throws MalformedURLException {

        webDriver = testHelper.getDriver();
        webDriver.manage().window().maximize();

        webDriver.get(url + "/login");
        userNameEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[2]/div/input"));
        userNameEle.sendKeys(username);
        paswordEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[3]/div/input"));
        paswordEle.sendKeys(password);

        webDriver.findElement(By.xpath("html/body/div[1]/div[2]/form/div[4]/div/button")).click();
    }

    @Given("^user on the create advertisement setup page$")
    public void user_on_the_create_advertisement_setup_page() throws InterruptedException {
        logger.info("user_on_the_create_advertisement_setup_page : {} /advertisement/create", url);
        webDriver.get(url + "/advertisement/create");

    }

    @Given("^user input data in form advertisement setup page \"([^\"]*)\"$")
    public void user_input_data_in_form_advertisement_setup_page(String name)
            throws InterruptedException {
        Thread.sleep(100);

        jobName = name;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = format.format(new Date());
        Date date = DateUtils.addDays(new Date(), 7);
        String endDate = format.format(date);

        logger.info("startDate: {}, date: {}, endDate: {}", startDate, date, endDate);

        // job name
        webDriver
                .findElement(
                        By.xpath(".//*[@id='create-form']/div[1]/div/div[2]/div[2]/div[2]/input"))
                .sendKeys(name);
        // Job Description
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[1]/div/div[2]/div[3]/div[2]/textarea"))
                .sendKeys("วันศุกร์ 18:00");
        // SMS Body
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[1]/div/div[2]/div[4]/div[2]/textarea"))
                .sendKeys("มวยมันวันศุกร์  ทาง True4U");
        // Type of SIM
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[1]/div/div[2]/div[5]/div[2]/div/label[1]"))
                .click();
        // Life Style

        new Select(webDriver.findElement(
                By.xpath(".//*[@id='create-form']/div[2]/div/div[2]/div[1]/div[2]/div/select")))
                        .selectByValue("Boxing");

        new Select(webDriver.findElement(
                By.xpath(".//*[@id='create-form']/div[2]/div/div[2]/div[1]/div[4]/div/select")))
                        .selectByValue("0");
        new Select(webDriver.findElement(
                By.xpath(".//*[@id='create-form']/div[2]/div/div[2]/div[1]/div[6]/div/select")))
                        .selectByValue("10");
        // And Life Style
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[2]/div/div[2]/div[2]/div/label[1]/input"))
                .click();
        Thread.sleep(2000);
        // sleep for get selection life style
        // Life Style 2
        new Select(
                webDriver.findElement(By.xpath(".//*[@id='lifestyle_2']/div[1]/div[2]/div/select")))
                        .selectByValue("Funny");
        new Select(
                webDriver.findElement(By.xpath(".//*[@id='lifestyle_2']/div[1]/div[4]/div/select")))
                        .selectByValue("0");
        new Select(
                webDriver.findElement(By.xpath(".//*[@id='lifestyle_2']/div[1]/div[6]/div/select")))
                        .selectByValue("10");
        // True4U SMS Exclude
        webDriver
                .findElement(By
                        .xpath(".//*[@id='CKture4uExclude']"))
                .click();
        webDriver.findElement(By.xpath(".//*[@id='ture4uExclude']")).sendKeys("5");

        // True SMS Exclude
        webDriver
                .findElement(By
                        .xpath(".//*[@id='CKtrueExclude']"))
                .click();
        webDriver.findElement(By.xpath(".//*[@id='trueExclude']")).sendKeys("10");

        // User Time
        webDriver
                .findElement(By
                        .xpath(".//*[@id='time']/tbody/tr[1]/td[1]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='time']/tbody/tr[1]/td[2]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='time']/tbody/tr[1]/td[3]"))
                .click();

        Actions actions = new Actions(webDriver);
        WebElement findElement = webDriver.findElement(By.xpath(".//*[@id='btnSeccion3']"));
        actions.moveToElement(findElement);
        actions.perform();

        // Start date, 11/11/2016
        webDriver.findElement(By.xpath(".//*[@id='dtp_schedule']")).sendKeys(startDate);
        // End date
        webDriver.findElement(By.xpath(".//*[@id='dtp_schedule_until']")).sendKeys(endDate);

        // Select Date
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[3]/div/div[2]/div[5]/div[2]/div/div/div/label[1]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[3]/div/div[2]/div[5]/div[2]/div/div/div/label[2]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[3]/div/div[2]/div[5]/div[2]/div/div/div/label[3]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[3]/div/div[2]/div[5]/div[2]/div/div/div/label[4]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[3]/div/div[2]/div[5]/div[2]/div/div/div/label[5]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[3]/div/div[2]/div[5]/div[2]/div/div/div/label[6]"))
                .click();
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[3]/div/div[2]/div[5]/div[2]/div/div/div/label[7]"))
                .click();

        // Execution Time
        webDriver.findElement(By.xpath(".//*[@id='dtp_exclude_time']/select[1]/option[9]")).click();
        webDriver.findElement(By.xpath(".//*[@id='dtp_exclude_time']/select[2]/option[11]"))
                .click();

    }

    @Given("^user on page advertisement setup$")
    public void user_on_page_advertisement_setup() {
        webDriver.get(url + "/advertisement/setup");
    }

    @Given("^user should be see job name \"([^\"]*)\" in page advertisement setup list$")
    public void user_should_be_see_job_name_in_page_advertisement_setup_list(
            String jobNameUpdtate) {
        logger.info("user_should_be_see_job_name_in_page_advertisement_setup_list : {}",
                jobNameUpdtate);

        int size = webDriver.findElements(By.xpath("html/body/div[1]/div[2]/div[4]/ul/li")).size();

        jobName = jobNameUpdtate;
        logger.info("jobName: {}, size: {}", jobName, size);

        if (size == 0) {
            try {
                WebElement findElement = webDriver.findElement(By.xpath(
                        ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobNameUpdtate
                                + "')]"));
                String attribute = findElement.getAttribute("href");
                jobId = attribute
                        .replace(url + "/advertisement?adsid=", "");
                System.out.println("attribute : " + jobId);
                assertEquals(jobNameUpdtate, findElement.getText());
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        } else {
            try {
                size = (size - 4);
                for (int i = 0; i <= size; i++) {
                    webDriver
                            .get(url + "/advertisement/setup/" + i);

                    if (webDriver.getPageSource().contains(jobNameUpdtate)) {
                        WebElement findElement = webDriver.findElement(
                                By.xpath(".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'"
                                        + jobNameUpdtate
                                        + "')]"));
                        String attribute = findElement.getAttribute("href");
                        jobId = attribute.replace(
                                url + "/advertisement?adsid=", "");
                        System.out.println("attribute : " + jobId);
                        assertEquals(jobNameUpdtate, findElement.getText());
                    }
                }
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        }

    }

    @When("^user click save job$")
    public void user_click_save_job() throws InterruptedException {
        webDriver.findElement(By.xpath(".//*[@id='create-form']/div[4]/div/div[2]/button")).click();
        Thread.sleep(6000);
    }

    @When("^delete data test$")
    public void delete_data_test() {
        webDriver.get(url + "/advertisement/delete/" + jobId);
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[6]/a/span")).click();
        webDriver.close();
    }

    @When("^user click edit$")
    public void user_click_edit() {
        logger.info("user_click_edit : {}", jobId);
        webDriver.get(
                url + "/advertisement/update/" + jobId);

    }

    @When("^user click update$")
    public void user_click_update() {
        logger.info("user_click_edit : {}", jobId);
        webDriver.findElement(By.xpath(".//*[@id='btnSeccion3']")).click();

    }

    @When("^user see notify for lifestyle exclude$")
    public void user_click_no_get_lifestyle() {
        logger.info("user_click_edit : {}", jobId);
        String currentUrl = webDriver.getCurrentUrl();
        
        WebDriverWait waiting = new WebDriverWait(webDriver, 30, 2500);

        WebElement element;
        try {
            element = waiting.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath(".//*[@id='cencelsave']")));
            element.click();
        } catch (Exception e) {
            fail("Modal cannot occur");
        }

        String currentUrl2 = webDriver.getCurrentUrl();
        assertEquals(currentUrl, currentUrl2);

        webDriver.get(url + "/advertisement/settings");

    }

    @When("^user update field name \"([^\"]*)\"$")
    public void user_update_field_name(String name) {
        // job name
        webDriver
                .findElement(
                        By.xpath(".//*[@id='create-form']/div[1]/div/div[2]/div[2]/div[2]/input"))
                .sendKeys(name);
    }

    @When("^user click delete life style$")
    public void user_click_delete_life_style() {
        webDriver
                .findElement(
                        By.xpath(".//*[@id='delete_2']"))
                .click();
    }

    @When("^user click delete job name \"([^\"]*)\"$")
    public void user_click_delete_job_name(String jobName) {
        WebElement findElement = webDriver.findElement(By.xpath(
                ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobName + "')]"));
        String attribute = findElement.getAttribute("href");
        jobId = attribute
                .replace(url + "/advertisement?adsid=", "");
        System.out.println("attribute : " + jobId);

        webDriver.findElement(By
                .xpath(".//*[@id='adsetup']/tbody/tr/td/div/div[2]/button[contains(@data-href,'delete/"
                        + jobId + "')]"))
                .click();

        WebDriverWait waiting = new WebDriverWait(webDriver, 30, 2500);
        WebElement element;
        element = waiting.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(".//*[@id='confirm-delete']/div/div/div[3]/div[1]/a")));

        element.click();
    }

    @When("^user click pause job name \"([^\"]*)\"$")
    public void user_click_pause_job_name(String jobName) throws InterruptedException {

        WebElement findElement = webDriver.findElement(By.xpath(
                ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobName + "')]"));
        String attribute = findElement.getAttribute("href");
        jobId = attribute
                .replace(url + "/advertisement?adsid=", "");

        logger.info("user_click_pause_job_name : {}", jobId);

        webDriver.findElement(By
                .xpath(".//*[@id='pause_" + jobId + "']"))
                .click();

        Thread.sleep(3000);

        webDriver.switchTo().alert().accept();

    }

    @When("^user click resume job name \"([^\"]*)\"$")
    public void user_click_resume_job_name(String jobName) throws InterruptedException {
        WebElement findElement = webDriver.findElement(By.xpath(
                ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobName + "')]"));
        String attribute = findElement.getAttribute("href");
        jobId = attribute
                .replace(url + "/advertisement?adsid=", "");
        System.out.println("attribute : " + jobId);

        webDriver.findElement(By
                .xpath(".//*[@id='resume_" + jobId + "']"))
                .click();

        Thread.sleep(3000);

        webDriver.switchTo().alert().accept();

    }

    @When("^user click setting$")
    public void user_click_setting() throws InterruptedException, AWTException {

        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[4]/a/span")).click();

        Thread.sleep(2000);

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/advertisement/settings", currentUrl);

    }

    @When("^user click advert setup$")
    public void user_click_advert_setup() throws InterruptedException, AWTException {

        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[3]/a/span")).click();

        Thread.sleep(2000);

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/advertisement/setup", currentUrl);

    }

    @When("^user click setting list choose lifestyle exclude$")
    public void user_click_setting_list_choose_lifestyle_exclude() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.xpath(".//*[@id='select1']/button")).click();
        webDriver.findElement(By.xpath(".//*[@id='3']/a")).click();

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/advertisement/settings#", currentUrl);
    }

    @When("^user choose lifestyle name \"([^\"]*)\"$")
    public void user_choose_lifestyle_name(String arg1) {
        webDriver.findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg1 + "']"))
                .click();
    }

    @When("^user click save lifestyle$")
    public void user_click_save_lifestyle() {
        webDriver
                .findElement(
                        By.xpath(".//*[@id='adExclude']"))
                .click();
    }

    @When("^user should be see alert message \"([^\"]*)\"$")
    public void user_should_be_see_alert_message(String message) throws InterruptedException {
        Thread.sleep(6000);

        Alert alert = webDriver.switchTo().alert();
        assertEquals(message, alert.getText());

        alert.accept();

    }

    @Then("^user can not choose lifestyle exclude in setup page \"([^\"]*)\"$")
    public void user_check_lifestyle_exclude(String lifestyle) throws InterruptedException {
        logger.info("user_on_the_create_advertisement_setup_page : {} /advertisement/create", url);
        List<WebElement> allSuggestions = webDriver.findElements(By.xpath(
                ".//*[@id='create-form']/div[2]/div/div[2]/div[1]/div[2]/div/select/option"));
        Boolean checkLifeStyle = false;
        for (int j = 0; j < allSuggestions.size(); j++) {

            if (lifestyle.equals(allSuggestions.get(j).getText())) {
                checkLifeStyle = true;
            }
        }

        assertEquals(false, checkLifeStyle);
    }

    @Then("^user should be see page advertisement setup$")
    public void user_should_be_see_page_advertisement_setup() {
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/advertisement/setup", currentUrl);
    }

    @Then("^user should be see job in page advertisement setup list$")
    public void user_should_be_see_job_in_page_advertisement_setup_list() {
        logger.info("user_should_be_see_job_in_page_advertisement_setup_list");

        int size = webDriver.findElements(By.xpath("html/body/div[1]/div[2]/div[4]/ul/li")).size();

        logger.info("size: {}", size);

        if (size == 0) {
            try {
                WebElement findElement = webDriver.findElement(By.xpath(
                        ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobName + "')]"));
                String attribute = findElement.getAttribute("href");
                jobId = attribute
                        .replace(url + "/advertisement?adsid=", "");
                System.out.println("attribute : " + jobId);
                assertEquals(jobName, findElement.getText());
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        } else {
            try {
                size = (size - 4);
                for (int i = 0; i <= size; i++) {
                    webDriver
                            .get(url + "/advertisement/setup/" + i);

                    if (webDriver.getPageSource().contains(jobName)) {
                        WebElement findElement = webDriver.findElement(
                                By.xpath(".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'"
                                        + jobName
                                        + "')]"));
                        String attribute = findElement.getAttribute("href");
                        jobId = attribute.replace(
                                url + "/advertisement?adsid=", "");
                        System.out.println("attribute : " + jobId);
                        assertEquals(jobName, findElement.getText());
                    }
                }
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        }

    }

    @Then("^user click logout$")
    public void user_click_logout() {
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[6]/a/span")).click();
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/", currentUrl);
        webDriver.close();
        webDriver.quit();
    }

    @Then("^user should be not see job name \"([^\"]*)\" in page advertisement setup list$")
    public void user_should_be_not_see_job_name_in_page_advertisement_setup_list(
            String jobNameUpdtate) {
        int size = webDriver.findElements(By.xpath(
                ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobNameUpdtate + "')]"))
                .size();

        assertEquals(0, size);
    }

    @Then("^user should be see job name \"([^\"]*)\" pause status in page advertisement setup list$")
    public void user_should_be_not_see_job_name_pause_status_in_page_advertisement_setup_list(
            String jobNameUpdtate) {

        int size = webDriver.findElements(By.xpath("html/body/div[1]/div[2]/div[4]/ul/li")).size();

        logger.info("size: {}", size);

        if (size == 0) {
            try {
                WebElement findElement = webDriver.findElement(By.xpath(
                        ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobName + "')]"));
                String attribute = findElement.getAttribute("href");
                jobId = attribute
                        .replace(url + "/advertisement?adsid=", "");
                System.out.println("attribute : " + jobId);
                assertEquals(jobName, findElement.getText());
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        } else {
            try {
                size = (size - 4);
                for (int i = 0; i <= size; i++) {
                    webDriver
                            .get(url + "/advertisement/setup/" + i);

                    if (webDriver.getPageSource().contains(jobName)) {
                        WebElement findElement = webDriver.findElement(
                                By.xpath(".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'"
                                        + jobName
                                        + "')]"));
                        String attribute = findElement.getAttribute("href");
                        jobId = attribute.replace(
                                url + "/advertisement?adsid=", "");
                        System.out.println("attribute : " + jobId);
                        assertEquals(jobName, findElement.getText());
                    }
                }
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        }

        int resumeSize = webDriver.findElements(By
                .xpath(".//*[@id='resume_" + jobId + "']")).size();

        assertEquals(1, resumeSize);
    }

    @Then("^user should be see job name \"([^\"]*)\" resume status in page advertisement setup list$")
    public void user_should_be_not_see_job_name_resume_status_in_page_advertisement_setup_list(
            String jobNameUpdtate) {

        int size = webDriver.findElements(By.xpath("html/body/div[1]/div[2]/div[4]/ul/li")).size();

        logger.info("size: {}", size);

        if (size == 0) {
            try {
                WebElement findElement = webDriver.findElement(By.xpath(
                        ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobName + "')]"));
                String attribute = findElement.getAttribute("href");
                jobId = attribute
                        .replace(url + "/advertisement?adsid=", "");
                System.out.println("attribute : " + jobId);
                assertEquals(jobName, findElement.getText());
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        } else {
            try {
                size = (size - 4);
                for (int i = 0; i <= size; i++) {
                    webDriver
                            .get(url + "/advertisement/setup/" + i);
  
                    if (webDriver.getPageSource().contains(jobName)) {
                        WebElement findElement = webDriver.findElement(
                                By.xpath(".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'"
                                        + jobName
                                        + "')]"));
                        String attribute = findElement.getAttribute("href");
                        jobId = attribute.replace(
                                url + "/advertisement?adsid=", "");
                        System.out.println("attribute : " + jobId);
                        assertEquals(jobName, findElement.getText());
                    }
                }
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        }

        int pauseSize = webDriver.findElements(By
                .xpath(".//*[@id='pause_" + jobId + "']")).size();

        assertEquals(1, pauseSize);
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

}// end class
