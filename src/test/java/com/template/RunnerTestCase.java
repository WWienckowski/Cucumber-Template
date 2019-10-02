package com.template;

import org.junit.runner.RunWith;


import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "not @ignore",
		features = "src/test/resources/features",
		glue = "com.template.stepdefs",
		plugin = {"html:target/basic-html-report", "json:target/cucumber-report/cucumber.json"},
		monochrome = true)

public class RunnerTestCase {
	
	

}
