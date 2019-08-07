package com.template.stepdefs;

import com.template.PageObjectManager;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class ExampleDefs {
	PageObjectManager manager = Hooks.manager;
	
	
	@Given("I navigate the web driver to the home page")
	public void i_navigate_the_web_driver_to_the_home_page() {
	    manager.home.navigateTo_HomePage();
	}
	
	@Then("I should see the title")
	public void i_should_see_the_title() {
	    manager.home.verifyTitle();
	}
	
	@When("I click on the SHIRTS link")
	public void i_click_on_the_SHIRTS_link() {
	    manager.home.clickShirtsLink();
	}

	@Then("I should be on the {string} page")
	public void i_should_be_on_the_Details_page(String url) {
		manager.detail.confirmUrl(url);

	}

}
