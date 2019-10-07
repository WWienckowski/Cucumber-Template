package com.template;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "not @ignore",
		features = "src/test/resources/features",
		glue = "com.template.stepdefs",
		plugin = {"html:target/cucumber-reports","json:target/cucumber-json/cucumber.json"},
		monochrome = true)

public class RunnerTestCase {
	
	

}
