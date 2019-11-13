package com.template;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "not @incomplete",
		features = "src/test/resources/features",
		glue = "com.template.stepdefs",
		plugin = {"pretty", "json:target/cucumber-json/cucumber.json", "html:target/cucumber-html"},
		monochrome = true)

public class RunCucumberIT {
}
