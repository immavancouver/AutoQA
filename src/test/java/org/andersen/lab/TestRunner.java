package org.andersen.lab;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {
		"src/test/resources/feature/LoginPage.feature",
		"src/test/resources/feature/RegistrationPage.feature"},
		glue = "org.andersen.lab.steps",
		plugin = {"pretty", "html:target/cucumber-reports.html"},
		monochrome = true)
public class TestRunner extends AbstractTestNGCucumberTests {

}
