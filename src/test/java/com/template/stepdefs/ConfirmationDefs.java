package com.template.stepdefs;

import java.util.List;

import com.template.PageObjectManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ConfirmationDefs {
	PageObjectManager manager = Hooks.manager;
	
	@When("the user clicks on the social media icons they open a new tab for the link")
	public void the_user_clicks_on_the_social_media_icons_they_open_a_new_tab_for_the_link() {
	    manager.confirmation.clickSocialLinks();
	}
	
	@Then("the user is presented with empty fields with the appropriate placeholder text")
	public void the_user_is_presented_with_empty_filds_with_the_appropriate_placeholder_text(List<String> fieldNames) {
	    manager.confirmation.checkRegistrationFields(fieldNames);
	}
	
	@Given("the registration contains the users name and email address")
	public void the_registration_contains_the_users_name_and_email_address() {
		manager.confirmation.checkLoginHeader();
	}
	
	@When("the user enters a password in the password field in the registration section")
	public void the_user_enters_a_password_in_the_password_field_in_the_registration_section() {
	    manager.confirmation.enterPassword();
	}
	
	@Given("the user hovers over the tool tip icon")
	public void the_user_hovers_over_the_tool_tip_icon() {
	    manager.confirmation.hoverOnTooltip();
	}
	
	@Then("the password message is displayed")
	public void the_password_message_is_displayed() {
	    String message = "Password must be a minimum of 8 characters";
	    manager.confirmation.tooltipMessageDisplayed(message);
	}
	
	@Then("the password message is not displayed")
	public void the_password_message_is_not_displayed() {
	    manager.confirmation.tooltipMessageDismissed();
	}
	
	@When("the user moves the cursor off the tool tip icon")
	public void the_user_moves_the_cursor_off_the_tool_tip_icon() {
		manager.global.mouseOut();
	    
	}
	
	@When("the user taps on the tool tip icon")
	public void the_user_taps_on_the_tool_tip_icon() {
	    manager.confirmation.tapToolTip();
	}

}
