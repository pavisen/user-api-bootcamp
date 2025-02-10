package com.self.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(monochrome = true, features = {
		"src/test/resources/features/"

}, dryRun = false, glue = { "com.self.stepDefinitions" }, plugin = { "pretty", "html:target/CucumberReports/CucumberReport.html",
		"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"

})

public class TestRunner extends AbstractTestNGCucumberTests {

}
