package com.template.stepdefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.template.PageObjectManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckoutDefs {
	PageObjectManager manager = Hooks.manager;
	
	@When("the Ship to Address section of the UK checkout page loads")
	public void the_Ship_to_Address_section_of_the_UK_checkout_page_loads() {
		manager.global.isActiveByText("Ship to Address");
		manager.checkout.locationIs("UK");
	}

	@Then("the page will display all fields")
	public void the_page_will_display_all_fields(List<String> fields) {
	    int fieldsetIndex = 0;	
		manager.checkout.checkFields(fields, fieldsetIndex);
	}

	@Given("the user is in the Ship to Address section of the checkout page")
	public void the_user_is_in_the_Ship_to_Address_section_of_the_checkout_page() {
		manager.global.isActiveByText("Ship to Address");
	}

	@When("the user clicks the {string}")
	public void the_user_clicks_the_title_field(String element) {
		manager.global.clickOnByText(element);
	}

	@Then("a drop down list of title options will appear")
	public void a_drop_down_list_of_title_options_will_appear() {
		manager.checkout.checkTitles();
	}
	
	@Then("the country will be {word}")
	public void the_country_will_be_UK(String country) {
	    manager.checkout.locationIs(country);
	}

	@Then("the icon will be the {word} flag")
	public void the_icon_will_be_the_country_flag(String country) {
		manager.checkout.checkFlag(country);
	}

	@When("the user selects the {string} link underneath the address lookup field")
	public void the_user_selects_the_link_underneath_the_address_lookup_field(String link) {
	    manager.global.clickOnByText(link);
	}

	@Then("a {string} link should appear below the last field")
	public void a_link_should_appear_below_the_last_field(String link) {
	    manager.global.clickOnByText(link);
	}

	@Then("the {string} radio button should be selected")
	public void the_radio_button_should_be_selected_by_default(String selection) {
	    manager.checkout.selectedRadioCheck(selection);
	}

	@Then("the selected button will be a black ring surrounding a filled-in black circle")
	public void the_button_will_be_a_color_ring_surrounding_a_filled_in_color_circle() {
		manager.global.includeScreenshot();
	}
	
	@Then("the user is presented with delivery options")
	public void the_user_is_presented_with_delivery_options(List<String> optionNames) {
	    manager.checkout.checkDeliveryOptions(optionNames);
	}
	
	@When("the user selects a {string} that is not already selected")
	public void the_user_selects_a_delivery_option_that_is_not_already_selected(String element) {
	    // Write code here that turns the phrase above into concrete actions
	    manager.global.isInactiveByText(element);
		manager.global.clickOnByText(element);
	}
	
	@Then("that {string} should be highlighted in black as per designs")
	public void that_delivery_option_should_be_highlighted_in_black_as_per_designs(String element) {
	    manager.global.isActiveByText(element);
	}
	
	@When("the user selects a {string} that is already selected")
	public void the_user_selects_a_that_is_already_selected(String element) {
	    manager.global.clickOnByText(element);
	    manager.global.isActiveByText(element);
	    manager.global.clickOnByText(element);
	}

	@Then("that {string} should remain active")
	public void that_should_remain_active(String element) {
	    manager.global.isActiveByText(element);
	}
	
	@When("the user clicks on the {string} section")
	public void the_user_clicks_on_the_section(String element) {
	    manager.global.clickOnByText(element);
	}
	
	@Then("there will be a {string} component")
	public void there_will_be_a_component(String elementName) {
	    manager.global.checkForElementByText(elementName);
	}
	
	@Then("the page will display all fields for {string}")
	public void the_page_will_display_all_fields_for(String deliveryOption) {
		List<String> fieldNames = new ArrayList<String>();
		int fieldsetIndex = 2;
		if (deliveryOption.matches("Ship to Address")) {
			fieldNames = Arrays.asList( "Email Address*", "Mobile Number*");
	    } else if (deliveryOption.matches("Collection Point Pick Up") 
	    		|| deliveryOption.matches("Collect in a Pink Store")) {
	    	fieldNames = Arrays.asList( "Select a Title*", "Full name*","Email Address*", "Mobile Number*");
	    } else {
	    	// fail the scenario
	    }
		manager.checkout.checkFields(fieldNames, fieldsetIndex);
	}
	
	@Then("{string} will contain an interactive checkbox that the user can check")
	public void it_will_contain_an_interactive_checkbox_that_the_user_can_check(String section) {
	    // look for a checkbox in the 'section' section
	}
	
}
