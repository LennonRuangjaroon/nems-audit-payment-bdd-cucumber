
package com.truecorp.pushad.bdd.feature.login;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Lennon
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature/userLogin.feature", plugin = {
        "html:target/cucumber-html-report-login", "json:target/cucumber-json-report-login.json"
})
public class UserLoginTest {
}
