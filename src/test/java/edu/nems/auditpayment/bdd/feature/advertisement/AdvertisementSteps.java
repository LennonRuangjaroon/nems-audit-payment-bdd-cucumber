
package edu.nems.auditpayment.bdd.feature.advertisement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import edu.nems.auditpayment.bdd.AbstractTest;
import org.apache.commons.lang.time.DateUtils;
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

import edu.nems.auditpayment.bdd.TestHelper;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Lennon
 */

@ConfigurationProperties(prefix = "pushad")
public class AdvertisementSteps extends AbstractTest {

    @Autowired
    private TestHelper testHelper;

    private static final Logger logger = LoggerFactory.getLogger(AdvertisementSteps.class);
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
    public void user_on_the_create_advertisement_setup_page() throws Throwable {
        webDriver.get(url + "/advertisement/create");
    }

    @Given("^user input data in form advertisement setup page \"([^\"]*)\"$")
    public void user_input_data_in_form_advertisement_setup_page(String name)
            throws InterruptedException {
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
        Thread.sleep(4000);
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
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String[] time = sdf.format(cal.getTime()).split(":");

        WebElement hh = webDriver.findElement(By.xpath(".//*[@id='dtp_exclude_time']/select[1]"));
        new Select(hh).selectByValue(time[0]);

        WebElement mm = webDriver.findElement(By.xpath(".//*[@id='dtp_exclude_time']/select[2]"));

        String[] arr = {
                "00", "01", "02", "03", "04", "05", "06", "07", "08", "09"
        };

        logger.info("time[1] : {}", time[1]);

        if (time[1] == "60") {
            logger.info("case time : 60");
            new Select(mm).selectByValue("0" + String.valueOf(0 + 1));
        } else if (contains(arr, time[1])) {
            logger.info("case contains time arr");
            new Select(mm).selectByValue("0" + String.valueOf(Integer.parseInt(time[1]) + 1));
        } else if(time[1] == "59") {
            logger.info("case contains time: 59");
            new Select(mm).selectByValue("00");
        }else {
            logger.info("else case time");
            new Select(mm).selectByValue(String.valueOf(Integer.parseInt(time[1]) + 1));
        }

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

                logger.info("attribute: {}", jobId);
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

                        logger.info("attribute: {}", jobId);
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
        Thread.sleep(3000);
    }

    @When("^user click advertisement list$")
    public void user_click_advertisement_list() throws InterruptedException {
        Thread.sleep(100);
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[2]/a/span")).click();
    }

    @When("^user click delete job name \"([^\"]*)\"$")
    public void user_click_delete_job_name(String jobName) {
        WebElement findElement = webDriver.findElement(By.xpath(
                ".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'" + jobName + "')]"));
        String attribute = findElement.getAttribute("href");
        jobId = attribute
                .replace(url + "/advertisement?adsid=", "");
        logger.info("attribute: {}", jobId);

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

    @When("^user click update config job name \"([^\"]*)\"$")
    public void user_click_update_config_job_name(String arg1) throws InterruptedException {
        logger.info("user_click_update_config, jobId: {}", jobId);
        try {
            webDriver.findElement(
                    By.xpath(".//*[@id='adsetup']/tbody/tr/td[1][contains(text(), '" + arg1
                            + "')]/parent::tr/td[5]/a[contains(@href, 'savererun')]"))
                    .click();
        } catch (Exception e) {
            fail("Can't update config");
        }

    }

    @When("^user update data$")
    public void user_update_data() throws Throwable {

        ArrayList<String> tabs2 = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(1));

        Thread.sleep(3000);
        // Type of SIM
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[1]/div/div[2]/div[5]/div[2]/div/label[2]"))
                .click();

        // Life Style
        new Select(
                webDriver.findElement(By.xpath(
                        ".//*[@id='create-form']/div[2]/div/div[2]/div[1]/div[2]/div/select")))
                                .selectByValue("Boxing");
        new Select(
                webDriver.findElement(By.xpath(
                        ".//*[@id='create-form']/div[2]/div/div[2]/div[1]/div[4]/div/select")))
                                .selectByValue("0");
        new Select(
                webDriver.findElement(By.xpath(
                        ".//*[@id='create-form']/div[2]/div/div[2]/div[1]/div[6]/div/select")))
                                .selectByValue("50");

        // And Life Style
        webDriver
                .findElement(By
                        .xpath(".//*[@id='create-form']/div[2]/div/div[2]/div[2]/div/label[1]/input"))
                .click();
        Thread.sleep(4000);
        // sleep for get selection life style
        // Life Style 2
        new Select(
                webDriver.findElement(By.xpath(".//*[@id='lifestyle_2']/div[1]/div[2]/div/select")))
                        .selectByValue("Sports");
        new Select(
                webDriver.findElement(By.xpath(".//*[@id='lifestyle_2']/div[1]/div[4]/div/select")))
                        .selectByValue("0");
        new Select(
                webDriver.findElement(By.xpath(".//*[@id='lifestyle_2']/div[1]/div[6]/div/select")))
                        .selectByValue("50");

        // True4U SMS Exclude
        webDriver.findElement(By.xpath(".//*[@id='ture4uExclude']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='ture4uExclude']")).sendKeys("7");

        // True SMS Exclude
        webDriver.findElement(By.xpath(".//*[@id='trueExclude']")).clear();
        webDriver.findElement(By.xpath(".//*[@id='trueExclude']")).sendKeys("12");

        // User Time
        webDriver.findElement(By.xpath(".//*[@id='time']/tbody/tr[1]/td[4]")).click();

    }

    @When("^user click update job$")
    public void user_click_update_job() throws InterruptedException {
        webDriver.findElement(By.xpath(".//*[@id='btnSeccion5']")).click();
        Thread.sleep(6000);
    }

    @Then("^user on the advertisement setup page$")
    public void user_on_the_dvertisement_setup_page() {
        webDriver.get(url + "/advertisement/setup");
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

                logger.info("attribute: {}", jobId);
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
                    // .//*[@id='adsetup']/tbody/tr/td[contains(text(),'Expired')]
                    if (webDriver.getPageSource().contains(jobName)) {
                        WebElement findElement = webDriver.findElement(
                                By.xpath(".//*[@id='adsetup']/tbody/tr/td/a[contains(text(),'"
                                        + jobName
                                        + "')]"));
                        String attribute = findElement.getAttribute("href");
                        jobId = attribute.replace(
                                url + "/advertisement?adsid=", "");

                        logger.info("attribute: {}", jobId);
                        assertEquals(jobName, findElement.getText());
                    }
                }
            } catch (Exception e) {
                fail("Can't found element advertisement setup list");
            }
        }

    }

    @Then("^user click export job name \"([^\"]*)\"$")
    public void user_click_export(String arg1) throws InterruptedException {
        logger.info("user_click_export, jobId: {}", jobId);
        try {
            webDriver
                    .findElement(By
                            .xpath(".//*[@id='adsetup']/tbody/tr/td[1][contains(text(), '" + arg1
                                    + "')]/parent::tr/td[5]/a[contains(@href, 'export')]"))
                    .click();
        } catch (Exception e) {
            fail("Can't found element export");
        }
    }

    @Then("^user click re run job name \"([^\"]*)\"$")
    public void user_click_re_run_job_name(String arg1) throws InterruptedException {
        logger.info("user_click_export, jobId: {}", jobId);
        try {
            webDriver
                    .findElement(By
                            .xpath(".//*[@id='adsetup']/tbody/tr/td[1][contains(text(), '" + arg1
                                    + "')]/parent::tr/td[5]/a[contains(@href, 'rerun')]"))
                    .click();
        } catch (Exception e) {
            fail("Can't found element export");
        }
    }

    @Then("^user click execute Job$")
    public void user_click_execute_Job() throws Throwable {
        Thread.sleep(6000);

        ArrayList<String> tabs2 = new ArrayList<String>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs2.get(1));

        webDriver
                .findElement(By
                        .xpath(".//*[@id='btnSeccion4']"))
                .click();
    }

    @Then("^user should be see job name \"([^\"]*)\" in \"([^\"]*)\" page advertisement list$")
    public void user_should_be_see_job_name_in_page_advertisement_list(String jobNameRun,
            String action)
            throws InterruptedException {
        logger.info(
                "user_should_be_see_job_name_in_page_advertisement_list, jobNameRun: {}, action: {}",
                jobNameRun, action);

        if (!action.equals("export") && !action.equals("rerun")) {
            logger.info("arg2: {}", action);
            TimeUnit.MINUTES.sleep(4);
        }

        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[2]/a/span")).click();

        int size = webDriver.findElements(By.xpath("html/body/div[1]/div[2]/div[3]/ul")).size();
        logger.info("jobName: {}, size: {}", jobNameRun, size);

        final String xpathFindJobName = ".//*[@id='adsetup']/tbody/tr[1]/td[1][contains(text(),'"
                + jobNameRun + "')]";
        logger.info("xpathFindJobName: {}", xpathFindJobName);

        if (size == 0) {
            try {
                String findElementJobName = webDriver.findElement(
                        By.xpath(xpathFindJobName))
                        .getText();

                logger.info("findElementJobName: {}", findElementJobName);
                assertEquals(jobNameRun, findElementJobName);

            } catch (Exception e) {
                logger.error(e.getMessage());
                fail("Can't found element advertisement list");
            }
        } else {
            try {
                size = (size - 4);
                for (int i = 0; i <= size; i++) {
                    webDriver
                            .get(url + "/advertisement/" + i);
                    if (webDriver.getPageSource().contains(jobNameRun)) {

                        String findElementJobName = webDriver
                                .findElement(By.xpath(xpathFindJobName)).getText();

                        logger.info("findElementJobName: {}", findElementJobName);
                        assertEquals(jobNameRun, findElementJobName);
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                fail("Can't found element advertisement list");
            }
        }
    }

    @Then("^user on page advertisement setup$")
    public void user_on_page_advertisement_setup() {
        webDriver.get(url + "/advertisement/setup");
    }

    @Then("^user click edit job name \"([^\"]*)\"$")
    public void user_click_edit_job_name(String arg1) throws Throwable {
        Thread.sleep(6000);
        logger.info("user_click_edit : {}", jobId);
        webDriver.get(url + "/advertisement/update/" + jobId);

        // Type of SIM
        WebElement selectedTypeofSIM = webDriver
                .findElement(By.xpath(
                        ".//*[@id='create-form']/div[1]/div/div[2]/div[5]/div[2]/div/label[2]"));
        assertEquals("Pre Paid", selectedTypeofSIM.getText());

        // Lifestyle 1
        WebElement selectedLifestyle = webDriver
                .findElement(By.xpath(".//*[@id='lifestyle_1']/div[2]/div/select/option[1]"));
        assertEquals("Boxing", selectedLifestyle.getText());

        WebElement selectedPercentile0 = webDriver
                .findElement(By.xpath(".//*[@id='lifestyle_2']/div[4]/div/select/option[2]"));
        assertEquals("0%", selectedPercentile0.getText());

        WebElement selectedPercentile50 = webDriver
                .findElement(By.xpath(".//*[@id='lifestyle_2']/div[4]/div/select/option[7]"));
        assertEquals("50%", selectedPercentile50.getText());

        WebElement selectedAnd = webDriver
                .findElement(By.xpath(".//*[@id='lifestyleE_1']/div/label[1]"));
        assertEquals("And", selectedAnd.getText());

        // Lifestyle 2
        WebElement selectedLifestyle2 = webDriver
                .findElement(By.xpath(".//*[@id='lifestyle_2']/div[2]/div/select/option[1]"));
        assertEquals("Sports", selectedLifestyle2.getText().trim());

        WebElement selectedP0 = webDriver
                .findElement(By.xpath(".//*[@id='lifestyle_2']/div[4]/div/select/option[2]"));
        assertEquals("0%", selectedP0.getText());

        WebElement selectedP50 = webDriver
                .findElement(By.xpath(".//*[@id='lifestyle_2']/div[6]/div/select/option[7]"));
        assertEquals("50%", selectedP50.getText());

        // True4U SMS Exclude
        String ture4uExclude = webDriver.findElement(By.id("ture4uExclude")).getAttribute("value");
        assertEquals("7", ture4uExclude);

        // True SMS Exclude
        String trueExclude = webDriver.findElement(By.id("trueExclude")).getAttribute("value");
        assertEquals("12", trueExclude);

        // User Time
        WebElement UserTime = webDriver.findElement(By.xpath(".//*[@id='time']/tbody/tr[1]/td[4]"));
        assertEquals("4-0", UserTime.getText());

    }

    @Then("^user click logout$")
    public void user_click_logout() {
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[6]/a/span")).click();
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/", currentUrl);
        webDriver.close();
        webDriver.quit();
    }

    private boolean contains(String[] arr, String test) {
        for (String n : arr) {
            if (test.equals(n)) {
                return true;
            }
        }
        return false;
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
