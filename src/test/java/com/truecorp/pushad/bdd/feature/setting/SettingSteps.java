
package com.truecorp.pushad.bdd.feature.setting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class SettingSteps extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(SettingSteps.class);
    private static boolean setUpfinished = false;
    private final static String WEB_DRIVER_PROPERTY = "webdriver.chrome.driver";

    private WebDriver webDriver;
    private WebElement userNameEle;
    private WebElement paswordEle;

    private String chromeDriver;
    private String url;

    private String name;

    @Before
    public void setUp() {
        if (setUpfinished) {
            return;
        }
        System.setProperty(WEB_DRIVER_PROPERTY, chromeDriver);
        logger.info("chromeDriver : {}", chromeDriver);
        setUpfinished = true;
    }

    @Given("^user login pushad with \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_login_pushad_with(String username, String password) throws MalformedURLException {

        webDriver = getDriver();
        
        webDriver.get(url + "/login");
        userNameEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[2]/div/input"));
        userNameEle.sendKeys(username);
        paswordEle = webDriver
                .findElement(By.xpath("html/body/div[1]/div[2]/form/div[3]/div/input"));
        paswordEle.sendKeys(password);

        webDriver.findElement(By.xpath("html/body/div[1]/div[2]/form/div[4]/div/button")).click();
    }

    @When("^user click setting$")
    public void user_click_setting() {
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[4]/a/span")).click();
    }

    @When("^user click edit user and save$")
    public void user_click_edit_user_and_save() {

        String userrName = getUserName();

        logger.info("user_click_edit_user userName: {}", userrName);

        webDriver.findElement(By
                .xpath(".//*[@id='editUser_" + userrName + "']"))
                .click();

        webDriver.findElement(By
                .xpath(".//*[@id='role_select_" + userrName + "']"))
                .click();

        webDriver.findElement(By
                .xpath(".//*[@id='role_select_" + userrName + "']/option[1]"))
                .click();

        webDriver.findElement(By
                .xpath(".//*[@id='saveUser_" + userrName + "']"))
                .click();

    }

    @When("^user click add user$")
    public void user_click_add_user() {
        webDriver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/div[2]/div/div/a")).click();
    }

    @When("^user provide text for search user \"([^\"]*)\"$")
    public void user_provide_text_for_search_user(String search) {
        webDriver.findElement(By.xpath(".//*[@id='keyword']")).sendKeys(search);

    }

    @When("^user click search user$")
    public void user_click_search_user() {
        webDriver.findElement(By.xpath(".//*[@id='search-user']")).click();
    }

    @When("^user click first list user$")
    public void user_click_first_list_user() throws InterruptedException {
        Thread.sleep(10000);
        webDriver.findElement(By.xpath(".//*[@id='rowsResult']/label[1]/a")).click();
    }

    @When("^user click list user$")
    public void user_click_list_user() throws InterruptedException {
        Thread.sleep(20000);

        webDriver.findElement(By.xpath(".//*[@id='rowsResult']/label/a")).click();
    }

    @When("^user click select user type \"([^\"]*)\"$")
    public void user_click_select_user_type(String userType) throws InterruptedException {
        Thread.sleep(6000);

        name = webDriver
                .findElement(By
                        .xpath(".//*[@id='userForAddRole']/div[2]/div/div/div[1]/div/h3"))
                .getText().replace("\n", "");

        String[] split = name.split("\\(");

        name = split[0];

        logger.info("user_click_select_user_type, userName : {}", name);

        switch (userType) {
            case "admin":
                webDriver
                        .findElement(By
                                .xpath(".//*[@id='userForAddRole']/div[2]/div/div/div[2]/div/label[2]"))
                        .click();
                break;
            case "user":
                webDriver
                        .findElement(By
                                .xpath(".//*[@id='userForAddRole']/div[2]/div/div/div[2]/div/label[1]"))
                        .click();
                break;
        }
    }

    @When("^user click save user$")
    public void user_click_save_user() {
        webDriver
                .findElement(
                        By.xpath(".//*[@id='userForAddRole']/div[2]/div/div/div[3]/div/button"))
                .click();
    }

    @When("^user click save lifestyle$")
    public void user_click_save_lifestyle() {
        webDriver
                .findElement(
                        By.xpath(".//*[@id='adExclude']"))
                .click();
    }

    @When("^user click save change password datasource$")
    public void user_click_save_change_password_datasource() {
        webDriver
                .findElement(
                        By.xpath(".//*[@id='save-datasource-password']"))
                .click();
    }

    @Then("^user should be see alert message \"([^\"]*)\"$")
    public void user_should_be_see_alert_message(String message) throws InterruptedException {
        Thread.sleep(6000);

        Alert alert = webDriver.switchTo().alert();
        assertEquals(message, alert.getText());

        alert.accept();

    }

    @Then("^user should be see new user in page user management list$")
    public void user_should_be_see_new_user_in_page_user_management_list() {
        int size = webDriver
                .findElements(By.xpath("html/body/div[1]/div[2]/div[2]/div[2]/div[2]/ul/li"))
                .size();

        logger.info("userName: {}, size: {}", name, size);

        if (size == 0) {
            try {
                WebElement findElement = webDriver.findElement(By.xpath(
                        ".//*[@id='adsetup']/tbody/tr/td[contains(text(),'" + name
                                + "')]"));
                assertEquals(name, findElement.getText());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Can't found element User management list");
            }
        } else {
            try {
                size = (size - 4);
                for (int i = 0; i <= size; i++) {
                    webDriver
                            .get(url + "/advertisement/setup/" + i);
                    if (webDriver.getPageSource().contains(name)) {
                        WebElement findElement = webDriver.findElement(
                                By.xpath(".//*[@id='adsetup']/tbody/tr/td[contains(text(),'"
                                        + name
                                        + "')]"));
                        assertEquals(name, findElement.getText());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                fail("Can't found element User management list");
            }
        }

    }

    @Then("^user should be see new role \"([^\"]*)\"$")
    public void user_should_be_see_new_role(String roleName) {
        String userrName = getUserName();

        String newRoleName = new Select(
                webDriver.findElement(By.xpath(".//*[@id='role_select_" + userrName + "']")))
                        .getFirstSelectedOption().getText();

        assertEquals(roleName, newRoleName);
    }

    @Then("^user click delete user data test$")
    public void user_click_delete_test() throws InterruptedException {

        String userrName = getUserName();

        logger.info("user_click_delete_test userName: {}", userrName);

        Thread.sleep(5000);

        webDriver.findElement(By
                .xpath(".//*[@id='confirm-delete-user_" + userrName + "']"))
                .click();

        WebDriverWait waiting = new WebDriverWait(webDriver, 30, 2500);
        WebElement element;
        element = waiting.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(".//*[@id='confirm-delete-user']/div/div/div[3]/div[1]/a")));
        element.click();

    }

    @Then("^user click logout$")
    public void user_click_logout() {
        webDriver.findElement(By.xpath(".//*[@id='sidenav01']/li[6]/a/span")).click();
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(url + "/", currentUrl);
        webDriver.close();
        webDriver.quit();
    }

    @When("^user click setting list choose lifestyle exclude$")
    public void user_click_setting_list_choose_lifestyle_exclude() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.xpath(".//*[@id='select1']/button")).click();
        webDriver.findElement(By.xpath(".//*[@id='3']/a")).click();
    }

    @When("^user click setting list choose change password datasource$")
    public void user_click_setting_list_choose_change_password_datasource()
            throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.xpath(".//*[@id='select1']/button")).click();
        webDriver.findElement(By.xpath(".//*[@id='2']/a")).click();
    }

    @When("^user set data source password \"([^\"]*)\"$")
    public void user_set_data_source_password(String arg1) throws InterruptedException {
        Thread.sleep(3000);
        webDriver.findElement(By.xpath(".//*[@id='datasource-password']")).sendKeys(arg1);
    }

    @When("^user choose lifestyle name \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_choose_lifestyle_name(String arg1, String arg2,
            String arg3) {
        webDriver.findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg1 + "']"))
                .click();
        webDriver.findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg2 + "']"))
                .click();
        webDriver.findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg3 + "']"))
                .click();
    }

    @Then("^user should be see lifestyle name \"([^\"]*)\", \"([^\"]*)\" selected$")
    public void user_should_be_see_lifestyle_name_selected(String arg1, String arg2) {
        boolean selected = webDriver
                .findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg1 + "']"))
                .isSelected();
        boolean selected2 = webDriver
                .findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg2 + "']"))
                .isSelected();

        assertTrue(selected);
        assertTrue(selected2);
    }

    @Then("^user should be not see lifestyle name \"([^\"]*)\", \"([^\"]*)\" selected$")
    public void user_should_be_not_see_lifestyle_name_selected(String arg1, String arg2) {
        boolean selected = webDriver
                .findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg1 + "']"))
                .isSelected();
        boolean selected2 = webDriver
                .findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg2 + "']"))
                .isSelected();

        assertFalse(selected);
        assertFalse(selected2);
    }

    @When("^user choose lifestyle name \"([^\"]*)\"$")
    public void user_choose_lifestyle_name(String arg1) {
        webDriver.findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg1 + "']"))
                .click();
    }

    @When("^user choose lifestyle name \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_choose_lifestyle_name(String arg1, String arg2) {
        webDriver.findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg1 + "']"))
                .click();
        webDriver.findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg2 + "']"))
                .click();
    }

    @Then("^user should be see lifestyle name \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" selected$")
    public void user_should_be_see_lifestyle_name_selected(String arg1, String arg2, String arg3) {
        boolean selected = webDriver
                .findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg1 + "']"))
                .isSelected();
        boolean selected2 = webDriver
                .findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg2 + "']"))
                .isSelected();
        boolean selected3 = webDriver
                .findElement(By.xpath(".//*[@id='checkExclude'][@value='" + arg3 + "']"))
                .isSelected();

        assertTrue(selected);
        assertTrue(selected2);
        assertTrue(selected3);
    }

    private String getUserName() {
        String userrName = webDriver.findElement(By
                .xpath(".//*[@id='adsetup']/tbody/tr/td[contains(text(), '" + name
                        + "')]/parent::tr/td[1]"))
                .getText();
        return userrName;
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
