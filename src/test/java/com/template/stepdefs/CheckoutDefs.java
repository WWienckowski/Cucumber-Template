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
	    	fieldNames = Arrays.asList( "Select a Title", "Full name*","Email Address*", "Mobile Number*");
	    } else {
	    	// TODO fail the step
	    }
		manager.checkout.checkFields(fieldNames, fieldsetIndex);
	}
	
	@Then("Contact for Order will contain an interactive checkbox that the user can check")
	public void it_will_contain_an_interactive_checkbox_that_the_user_can_check() {
	    manager.checkout.checkboxVerify();
	}
	
	@When("the user clicks on Pink Shirtmaker logo in checkout header")
	public void the_user_clicks_on_Pink_Shirtmaker_logo_in_checkout_header() {
		manager.checkout.clickCheckoutHeaderLogo();
	}

	@Then("the header should appear as per designs")
	public void the_header_should_appear_as_per_designs() {
	    manager.checkout.verifyHeader();
	}
	
	@Given("the user has completed all required fields for {string}")
	public void the_user_has_completed_all_required_information_for(String deliveryType) {
	    manager.checkout.fillOutRequiredFields(deliveryType);
	}
	
	@Then("the delivery component will change to a summary component")
	public void the_delivery_component_will_change_to_a_summary_component() {
	    manager.checkout.verifySummary();
	}
	
	@Then("the associated summary fields will display for {string}")
	public void the_associated_summary_fields_will_display_for(String deliveryType) {
		List<String> fieldNames = new ArrayList<String>();
		if (deliveryType.matches("Ship to Address")) {
	    	fieldNames = Arrays.asList( "DELIVERING TO", "DELIVERING IN", "CONTACT");
	    } else if (deliveryType.matches("Collection Point Pick Up") ||
	    		deliveryType.matches("Collect in a Pink Store")) {
	    	fieldNames = Arrays.asList( "PICKING UP", "READY IN", "CONTACT");
	    } else {
	    	// TODO fail step
	    }
	    manager.checkout.checkSummaryFields(fieldNames);
	}
	
	@Given("the site is showing the summary field")
	public void the_site_is_showing_the_summary_field() {
	    // TODO Actually fill out form fields when that becomes necessary
		manager.global.clickOnByText("continue");
		manager.checkout.verifySummary();
	}
	
	@Then("the summary section reverts to the delivery component")
	public void the_summary_section_reverts_to_the_delivery_component() {
	    manager.checkout.verifyDelivery();
	}
	
	@Then("the user can amend delivery information")
	public void the_user_can_amend_delivery_information() {
	    manager.checkout.verifyInputs();
	}
	
	@Given("the Shopping Bag control is {word}")
	public void the_Shopping_Bag_control_is_minimised(String state) {
	    Boolean open = state.contains("minimised") ? false : true;
	    manager.checkout.bagControlIsOpen(open);
	}
	
	@Then("the login bar is displayed beneath the header as per designs")
	public void the_login_bar_is_displayed_beneath_the_header_as_per_designs() {
	    manager.checkout.loginUnderHeader();
	}
	
	@Then("the login bar is displayed above Shopping Bag summary bar")
	public void the_login_bar_is_displayed_above_Shopping_Bag_summary_bar() {
	    manager.checkout.loginAboveShoppingBag();
	}
	
	@Then("the Review component is greyed out and not clickable")
	public void the_Review_component_is_greyed_out_and_not_clickable() {
	    manager.global.isButtonEnabledByText("Place order", false);
	    manager.global.checkCSS("//div[pink-order-summary]", "0.5", "opacity");
	}
	
	@When("the user enters valid entries into the Payment section and clicks Continue")
	public void the_user_enters_valid_entries_into_the_Payment_section_and_clicks() {
	    manager.confirmation.enterPaymentDetails();
	    manager.global.clickOnByText("continue");
	}
	
	@Then("the cursor becomes a finger")
	public void the_cursor_becomes_a_finger() {
	    manager.checkout.cursorVerifyLogin();
	}

	@Then("the Review section is active")
	public void the_Review_section_is_active() {
	   manager.global.checkCSS("//div[pink-order-summary]", "1", "opacity");
	}
	
	@Then("the Review section has the appropriate fields")
	public void the_Review_section_has_the_appropriate_fields(List<String> fieldNames) {
	    manager.checkout.checkReviewFields(fieldNames, "//div[@class='checkout-fulfilment_section']//span[@class='header']");
	}
	
	@Given("the user is on the Review section of the Checkout page")
	public void the_user_is_on_the_Review_section_of_the_Checkout_page() {
		manager.confirmation.enterPaymentDetails();
	    manager.global.clickOnByText("continue");
	}
	
	@Then("the footer should appear as per designs")
	public void the_footer_should_appear_as_per_designs() {
	    manager.checkout.footerConfirm();
	}
	
	@Then("none of the footer icons are clickable")
	public void none_of_the_icons_are_clickable() {
	    manager.checkout.footerIconConfirm();
	}
	
	@Then("the {string} message and {string} are displayed below the {string} as per designs")
	public void the_message_and_are_displayed_below_the_Order_Summary_as_per_designs(String message, String phone, String section) {
	    manager.checkout.confirmHelpline(message, phone, section);
	}
	
	@Then("the helpline link changes the mouse cursor to a pointer")
	public void the_link_changes_the_mouse_cursor_to_a_pointer() {
	    manager.checkout.cursorVerifyHelpLine();
	}
	
	@Then("the phone number is a clickable link")
	public void the_phone_number_is_a_clickable_link(int format) {
		manager.checkout.helplineLink(format);
	}
	
	@Then("the helpline message is pushed down the page immediately beneath the Order Summary component")
	public void the_helpline_message_is_pushed_down_the_page_immediately_beneath_the_Order_Summary_component() {
	    manager.checkout.checkHelpLineLocation();
	}
	
	@Then("there is an Order Summary component on the right hand side of the Checkout components underneath the Shopping Bag summary as per designs")
	public void there_is_an_Order_Summary_component_on_the_right_hand_side_of_the_Checkout_components_underneath_the_Shopping_Bag_summary_as_per_designs() {
	    manager.checkout.orderSummaryLocation();
	}

	@Then("the Order Summary component includes non-interactive fields")
	public void the_Order_Summary_component_includes_non_interactive_fields() {
	    List<String> fieldNames = 
	    		Arrays.asList("SUBTOTAL","SHIPPING","VOUCHER","GIFT CARD","TAX","TOTAL");
	    manager.checkout.checkReviewFields(fieldNames, "//div[@class='checkout_order-summary']//span[@class='header']");
	    
	}
	
	@Then("there is an Order Summary component above the Edit Shopping Bag link")
	public void there_is_an_Order_Summary_component_above_the_Edit_Shopping_Bag_link() {
	    manager.checkout.mobileOrderSummaryLocation();
	}
	
	@Then("the mobile Order Summary component includes non-interactive fields")
	public void the_mobile_Order_Summary_component_includes_non_interactive_fields() {
		List<String> fieldNames = 
	    		Arrays.asList("SUBTOTAL","SHIPPING","VOUCHER","GIFT CARD","TAX","TOTAL");
	    manager.checkout.checkReviewFields(fieldNames, "//div[@class='checkout-shopping-bag_order-summary is-open']//span[@class='header']");
	    
	}
	
	@Then("the Order Summary is no longer displayed")
	public void the_Order_Summary_is_no_longer_displayed() {
	    manager.global.isDisplayed("//div[@class='checkout-shopping-bag_order-summary']", false);
	}
	
	@Then("the Order Summary is pushed down the page immediately beneath the Shopping Bag summary")
	public void the_Order_Summary_is_pushed_down_the_page_immediately_beneath_the_Shopping_Bag_summary() {
		manager.checkout.orderSummaryLocation();
	}

	@Then("the Order Summary returns to its original position immediately beneath the Shopping Bag summary")
	public void the_Order_Summary_returns_to_its_original_position_immediately_beneath_the_Shopping_Bag_summary() {
		manager.checkout.orderSummaryLocation();
	}

}
