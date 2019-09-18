package com.template.stepdefs;

import com.template.PageObjectManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class GenericDefs {
	PageObjectManager manager = Hooks.manager;
	
	@Given("the user is on the {word} page")
	public void navigate_to_page_url(String urlSuffix) {
		urlSuffix = urlSuffix.equals("PDP") ? "detail" : urlSuffix;
		manager.global.navigateTo(urlSuffix);
	}
	
	@When("the user clicks on {string}")
	public void user_clicks_on(String element) {
		manager.global.clickOnByText(element);
	}
	
	@Given("the checkbox for {string} is not checked")
	public void the_checkbox_for_is_not_checked(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user clicks the checkbox for {string}")
	public void the_user_clicks_the_checkbox_for(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Given("the checkbox for {string} is checked")
	public void the_checkbox_for_is_checked(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
