
package com.truecorp.pushad.bdd.feature.uploadMSISDN;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Lennon
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature/uploadMSISDN.feature", plugin = {
        "html:target/cucumber-html-report-uploadmsisdn", "json:target/cucumber-json-report-uploadmsisdn.json"
})
public class UploadMSISDNTest {
}
