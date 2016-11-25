
package com.truecorp.pushad.bdd.feature.uploadMSISDN;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.truecorp.pushad.bdd.AbstractTest;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Lennon
 */

@ConfigurationProperties(prefix = "pushad")
public class UploadMSISDNSteps extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(UploadMSISDNSteps.class);
    private static boolean setUpfinished = false;
    private final static String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";
    private final static String JAVA_AWT_HEADLESS = "java.awt.headless";

    private WebDriver webDriver;
    private WebElement userNameEle;
    private WebElement paswordEle;

    private String chromeDriver;
    private String url;
    private String fileIncludePath;
    private String fileExcludePath;

    @Before
    public void setUp() {
        if (setUpfinished) {
            return;
        }
        System.setProperty(WEB_DRIVER_PROPERTY, chromeDriver);
        System.setProperty(JAVA_AWT_HEADLESS, "false");
        logger.info("chromeDriver : {}", chromeDriver);
        setUpfinished = true;
    }

    @Given("^user login pushad with \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_login_pushad_with(String username, String password) {

        webDriver = new ChromeDriver();
        webDriver.get(url + "/login");
        userNameEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[2]/div/input"));
        userNameEle.sendKeys(username);
        paswordEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[3]/div/input"));
        paswordEle.sendKeys(password);

        webDriver.findElement(By.xpath("html/body/div[1]/div[2]/form/div[4]/div/button")).click();
    }

    @Given("^user on the upload MSISDN page$")
    public void user_on_the_upload_MSISDN_page() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[5]/a/span")).click();
    }

    @Given("^user click select type \"([^\"]*)\" list$")
    public void user_click_select_type_include_list(String type) throws InterruptedException {

        webDriver.findElement(By.xpath(".//*[@id='frm-upload']/div/div[2]/div[1]/div/div/select"))
                .click();
        Thread.sleep(1000);

        if (type.equals("include")) {
            webDriver
                    .findElement(By
                            .xpath(".//*[@id='frm-upload']/div/div[2]/div[1]/div/div/select/option[1]"))
                    .click();
        } else if (type.equals("exclude")) {
            webDriver
                    .findElement(By
                            .xpath(".//*[@id='frm-upload']/div/div[2]/div[1]/div/div/select/option[2]"))
                    .click();
        }

    }

    @When("^user click upload panel$")
    public void user_click_upload_panel() throws InterruptedException, AWTException {
        logger.info("user_select_file_upload, fileIncludePath: {}", fileIncludePath);

        webDriver.findElement(By.xpath(".//*[@id='dropzonePreview']/label")).click();
    }

    @When("^user upload file type \"([^\"]*)\"$")
    public void user_upload_file(String type) throws InterruptedException, AWTException {
        Thread.sleep(3000);
        
        String filePath = null;
        if(type.equals("include")) {
            filePath = fileIncludePath;
        }else if (type.equals("exclude")) {
            filePath = fileExcludePath;
        }

        try {
            Robot robot = new Robot();

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(filePath);
            clipboard.setContents(strSel, null);

            robot.delay(100);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Can't upload file");
        }
        
        Thread.sleep(3000);
        
        webDriver.switchTo().alert().accept();
    }

    @Then("^user should be see alert message \"([^\"]*)\"$")
    public void user_should_be_see_alert_message(String message) throws InterruptedException {
        Thread.sleep(3000);

        Alert alert = webDriver.switchTo().alert();
        assertEquals(message, alert.getText());

        webDriver.switchTo().alert().accept();
    }

    @Then("^user click logout$")
    public void user_click_logout() throws InterruptedException {
        Thread.sleep(1000);

        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[6]/a/span")).click();
        webDriver.close();
        webDriver.quit();
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

    public String getFileIncludePath() {
        return fileIncludePath;
    }

    public String getFileExcludePath() {
        return fileExcludePath;
    }

    public void setFileIncludePath(String fileIncludePath) {
        this.fileIncludePath = fileIncludePath;
    }

    public void setFileExcludePath(String fileExcludePath) {
        this.fileExcludePath = fileExcludePath;
    }

}// end class
