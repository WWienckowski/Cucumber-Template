package com.template.stepdefs;

import org.openqa.selenium.WebDriver;

import com.template.DriverManager;
import com.template.PageObjectManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ExampleDefs {
	WebDriver driver = DriverManager.driver;
	PageObjectManager manager = Hooks.manager;

	@Given("I navigate the web driver to the home page")
	public void i_navigate_the_web_driver_to_the_home_page() {
	    manager.home.navigateTo_HomePage();
	}
	
	@Then("I should see the title")
	public void i_should_see_the_title() {
	    manager.home.verifyTitle();
	}

}
