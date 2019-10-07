package com.template.stepdefs;

import java.util.List;

import com.template.Helpers;
import com.template.page_objects.ConfirmationPage;
import driver.SharedDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ConfirmationDefs {
	private ConfirmationPage confirmation;
	
	public ConfirmationDefs(SharedDriver driver, ConfirmationPage confirmation) {
		this.confirmation = confirmation;
	}
	
	@When("the user clicks on the social media icons they open a new tab for the link")
	public void the_user_clicks_on_the_social_media_icons_they_open_a_new_tab_for_the_link() {
	    confirmation.clickSocialLinks();
	}
	
	@Then("the user is presented with empty fields with the appropriate placeholder text")
	public void the_user_is_presented_with_empty_filds_with_the_appropriate_placeholder_text(List<String> fieldNames) {
	    confirmation.checkRegistrationFields(fieldNames);
	}
	
	@Given("the registration contains the users name and email address")
	public void the_registration_contains_the_users_name_and_email_address() {
		confirmation.checkLoginHeader();
	}
	
	@When("the user enters a password in the password field in the registration section")
	public void the_user_enters_a_password_in_the_password_field_in_the_registration_section() {
	    confirmation.enterPassword();
	}

	@Given("the user is on the Review section of the Checkout page")
	public void the_user_is_on_the_Review_section_of_the_Checkout_page() {
		confirmation.enterPaymentDetails();
	}
	
	@When("the user enters valid entries into the Payment section and clicks Continue")
	public void the_user_enters_valid_entries_into_the_Payment_section_and_clicks() {
	    confirmation.enterPaymentDetails();
	}
	
	@Given("the user hovers over the tool tip icon")
	public void the_user_hovers_over_the_tool_tip_icon() {
	    confirmation.hoverOnTooltip();
	}
	
	@Then("the password message is displayed")
	public void the_password_message_is_displayed() {
	    String message = "Password must be a minimum of 8 characters";
	    confirmation.tooltipMessageDisplayed(message);
	}
	
	@Then("the password message is not displayed")
	public void the_password_message_is_not_displayed() {
	    confirmation.tooltipMessageDismissed();
	}
	
	@When("the user moves the cursor off the tool tip icon")
	public void the_user_moves_the_cursor_off_the_tool_tip_icon() {
		Helpers.mouseOut();
	    
	}
	
	@When("the user taps on the tool tip icon")
	public void the_user_taps_on_the_tool_tip_icon() {
	    confirmation.tapToolTip();
	}

}
