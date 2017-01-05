
package com.truecorp.pushad.bdd.feature.setting;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Lennon
 */
//@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature/settings.feature", plugin = {
        "html:target/cucumber-html-report-setting", "json:target/cucumber-json-report-setting.json"
})
public class SettingTest {
}
