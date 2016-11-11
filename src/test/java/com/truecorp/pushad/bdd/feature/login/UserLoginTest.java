
package com.truecorp.pushad.bdd.feature.login;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Lennon
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature/userLogin.feature")
public class UserLoginTest {
}
