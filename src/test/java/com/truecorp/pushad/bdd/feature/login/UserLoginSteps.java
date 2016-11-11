
package com.truecorp.pushad.bdd.feature.login;

import static org.junit.Assert.assertEquals;

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
public class UserLoginSteps extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(UserLoginSteps.class);
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
    
    @Given("^user on the login page$")
    public void user_on_the_login_page() throws Throwable {
        webDriver = new ChromeDriver();
        webDriver.get(url + "/login");
    }

    @When("^user provide the username \"([^\"]*)\"$")
    public void user_provide_the_username(String username) throws Throwable {
        userNameEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[2]/div/input"));
        userNameEle.sendKeys(username);
    }

    @When("^user provide the password \"([^\"]*)\"$")
    public void user_provide_the_password(String password) throws Throwable {
        paswordEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[3]/div/input"));
        paswordEle.sendKeys(password);
    }

    @When("^user click login$")
    public void user_click_login() throws Throwable {
        webDriver.findElement(By.xpath("html/body/div[1]/div[2]/form/div[4]/div/button")).click();
    }

    @Then("^user should be see page advertisement setup$")
    public void user_should_be_see_page_advertisement_setup() throws Throwable {
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/advertisement/setup", currentUrl);
    }

    @Then("^user should be see message \"([^\"]*)\"$")
    public void user_should_be_see_message(String message) throws Throwable {
        String actualMessage = webDriverNewWindows
                .findElement(By.xpath("html/body/div[1]/div[1]/div/div")).getText();
        assertEquals(message, actualMessage);
        webDriverNewWindows.close();
        webDriverNewWindows.quit();
    }

    @Then("^user click logout$")
    public void user_click_logout() throws Throwable {
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[6]/a/span")).click();
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/", currentUrl);
        webDriver.close();
        webDriver.quit();
    }

    @Then("^user should be see page advertisement login$")
    public void user_should_be_see_page_advertisement_login() throws Throwable {
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/login", currentUrl);
        webDriver.close();
        webDriver.quit();
    }

    @Then("^user open new the login page$")
    public void user_open_new_the_login_page() throws Throwable {
        webDriverNewWindows = new ChromeDriver();
        webDriverNewWindows.get(url + "/login");
    }

    @Then("^user provide the username \"([^\"]*)\" in the new login page$")
    public void user_provide_the_username_in_the_new_login_page(String username) throws Throwable {
        userNameEle = webDriverNewWindows
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[2]/div/input"));
        userNameEle.sendKeys(username);
    }

    @Then("^user provide the password \"([^\"]*)\" in the new login page$")
    public void user_provide_the_password_in_the_new_login_page(String password) throws Throwable {
        paswordEle = webDriverNewWindows
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[3]/div/input"));
        paswordEle.sendKeys(password);
    }

    @Then("^user click login at new the login page$")
    public void user_click_login_at_new_the_login_page() throws Throwable {
        webDriverNewWindows.findElement(By.xpath("html/body/div[1]/div[2]/form/div[4]/div/button"))
                .click();
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
