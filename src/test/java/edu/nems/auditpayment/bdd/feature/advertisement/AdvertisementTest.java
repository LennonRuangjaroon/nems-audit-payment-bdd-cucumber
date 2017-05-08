
package edu.nems.auditpayment.bdd.feature.advertisement;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Lennon
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature/advertisement.feature", plugin = {
        "html:target/cucumber-html-report-advertisement",
        "json:target/cucumber-json-report-advertisement.json"
})
public class AdvertisementTest {
}
