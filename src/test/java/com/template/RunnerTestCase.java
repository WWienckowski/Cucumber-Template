package com.template;

import org.junit.AfterClass;
import org.junit.runner.RunWith;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = "com.template.stepdefs",
		plugin = {"html:target/basic-html-report", "json:target/cucumber-report/cucumber.json"},
		monochrome = true)

public class RunnerTestCase {
	
	@AfterClass
	public static void teardown() {
		System.out.println("Tests over");
	}

}
