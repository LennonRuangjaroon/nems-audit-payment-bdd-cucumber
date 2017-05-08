
package edu.nems.auditpayment.bdd.feature.advertisementsetup;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Lennon
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:feature/advertisementSetup.feature", plugin = {
        "html:target/cucumber-html-report-advertisementsetup", "json:target/cucumber-json-report-advertisementsetup.json"
})
public class AdvertisementSetupTest {
}
