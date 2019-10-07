package com.template.stepdefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.template.Helpers;
import com.template.page_objects.CheckoutPage;
import driver.SharedDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CheckoutDefs {
	private CheckoutPage checkout;
	
	public CheckoutDefs(SharedDriver driver, CheckoutPage checkout) {
		this.checkout = checkout;
	}
	
	@When("the Ship to Address section of the UK checkout page loads")
	public void the_Ship_to_Address_section_of_the_UK_checkout_page_loads() {
		Helpers.isActiveByText("Ship to Address");
		checkout.locationIs("UK");
	}

	@Then("the Shipping Address section will display all fields")
	public void the_page_will_display_all_fields(List<String> fields) {
	    int fieldsetIndex = 1;	
		checkout.checkFields(fields, fieldsetIndex);
	}

	@Given("the user is in the Ship to Address section of the checkout page")
	public void the_user_is_in_the_Ship_to_Address_section_of_the_checkout_page() {
		Helpers.isActiveByText("Ship to Address");
	}

	@When("the user clicks the {string}")
	public void the_user_clicks_the_title_field(String element) {
		Helpers.clickOnByText(element);
	}

	@Then("a drop down list of title options will appear")
	public void a_drop_down_list_of_title_options_will_appear() {
		checkout.checkTitles();
	}
	
	@Then("the country will be {word}")
	public void the_country_will_be_UK(String country) {
	    checkout.locationIs(country);
	}

	@Then("the icon will be the {word} flag")
	public void the_icon_will_be_the_country_flag(String country) {
		checkout.checkFlag(country);
	}

	@When("the user selects the {string} link underneath the address lookup field")
	public void the_user_selects_the_link_underneath_the_address_lookup_field(String link) {
	    Helpers.clickOnByText(link);
	}

	@Then("a {string} link should appear below the last field")
	public void a_link_should_appear_below_the_last_field(String link) {
	    Helpers.findLinkByText(link);
	}

	@Then("the {string} radio button should be selected")
	public void the_radio_button_should_be_selected_by_default(String selection) {
	    checkout.selectedRadioCheck(selection);
	}

	@Then("the selected button will be a black ring surrounding a filled-in black circle")
	public void the_button_will_be_a_color_ring_surrounding_a_filled_in_color_circle() {
		Helpers.includeScreenshot();
	}
	
	@Then("the user is presented with delivery options")
	public void the_user_is_presented_with_delivery_options(List<String> optionNames) {
	    checkout.checkDeliveryOptions(optionNames);
	}
	
	@When("the user selects a {string} that is not already selected")
	public void the_user_selects_a_delivery_option_that_is_not_already_selected(String element) {
	    // Write code here that turns the phrase above into concrete actions
	    Helpers.isInactiveByText(element);
		Helpers.clickOnByText(element);
	}
	
	@Then("that {string} should be highlighted in black as per designs")
	public void that_delivery_option_should_be_highlighted_in_black_as_per_designs(String element) {
	    Helpers.isActiveByText(element);
	}
	
	@When("the user selects a {string} that is already selected")
	public void the_user_selects_a_that_is_already_selected(String element) {
	    Helpers.clickOnByText(element);
	    Helpers.isActiveByText(element);
	    Helpers.clickOnByText(element);
	}

	@Then("that {string} should remain active")
	public void that_should_remain_active(String element) {
	    Helpers.isActiveByText(element);
	}
	
	@When("the user clicks on the {string} section")
	public void the_user_clicks_on_the_section(String element) {
	    Helpers.clickOnByText(element);
	}
	
	@Then("there will be a {string} component")
	public void there_will_be_a_component(String elementName) {
	    Helpers.findComponentByText(elementName);
	}
	
	@Then("the page will display all fields for {string}")
	public void the_page_will_display_all_fields_for(String deliveryOption) {
		List<String> fieldNames = new ArrayList<String>();
		int fieldsetIndex = 3;
		if (deliveryOption.matches("Ship to Address")) {
			fieldNames = Arrays.asList( "Email Address*", "Mobile Number*");
	    } else if (deliveryOption.matches("Collection Point Pick Up") 
	    		|| deliveryOption.matches("Collect in a Pink Store")) {
	    	fieldNames = Arrays.asList( "Select a Title", "Full name*","Email Address*", "Mobile Number*");
	    } else {
	    	// TODO fail the step
	    }
		checkout.checkFields(fieldNames, fieldsetIndex);
	}
	
	@Then("Contact for Order will contain an interactive checkbox that the user can check")
	public void it_will_contain_an_interactive_checkbox_that_the_user_can_check() {
	    checkout.checkboxVerify();
	}
	
	@When("the user clicks on Pink Shirtmaker logo in checkout header")
	public void the_user_clicks_on_Pink_Shirtmaker_logo_in_checkout_header() {
		checkout.clickCheckoutHeaderLogo();
	}

	@Then("the header should appear as per designs")
	public void the_header_should_appear_as_per_designs() {
	    checkout.verifyHeader();
	}
	
	@Given("the user has completed all required fields for {string}")
	public void the_user_has_completed_all_required_information_for(String deliveryType) {
	    checkout.fillOutRequiredFields(deliveryType);
	}
	
	@Then("the delivery component will change to a summary component")
	public void the_delivery_component_will_change_to_a_summary_component() {
	    checkout.verifySummary();
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
	    checkout.checkSummaryFields(fieldNames);
	}
	
	@Given("the site is showing the summary field")
	public void the_site_is_showing_the_summary_field() {
	    // TODO Actually fill out form fields when that becomes necessary
		Helpers.clickOnByText("continue");
		checkout.verifySummary();
	}
	
	@Then("the summary section reverts to the delivery component")
	public void the_summary_section_reverts_to_the_delivery_component() {
	    checkout.verifyDelivery();
	}
	
	@Then("the user can amend delivery information")
	public void the_user_can_amend_delivery_information() {
	    checkout.verifyInputs();
	}
	
	@Given("the Shopping Bag control is {word}")
	public void the_Shopping_Bag_control_is_minimised(String state) {
	    Boolean open = state.contains("minimised") ? false : true;
	    checkout.bagControlIsOpen(open);
	}
	
	@Then("the login bar is displayed beneath the header as per designs")
	public void the_login_bar_is_displayed_beneath_the_header_as_per_designs() {
	    checkout.loginUnderHeader();
	}
	
	@Then("the login bar is displayed above Shopping Bag summary bar")
	public void the_login_bar_is_displayed_above_Shopping_Bag_summary_bar() {
	    checkout.loginAboveShoppingBag();
	}
	
	@Then("the Review component is greyed out and not clickable")
	public void the_Review_component_is_greyed_out_and_not_clickable() {
	    Helpers.isButtonEnabledByText("Place order", false);
	    Helpers.checkCSS("//div[pink-order-summary]", "0.5", "opacity");
	}
	
	@Then("the cursor becomes a finger")
	public void the_cursor_becomes_a_finger() {
	    checkout.cursorVerifyLogin();
	}

	@Then("the Review section is active")
	public void the_Review_section_is_active() {
	   Helpers.checkCSS("//div[pink-order-summary]", "1", "opacity");
	}
	
	@Then("the Review section has the appropriate fields")
	public void the_Review_section_has_the_appropriate_fields(List<String> fieldNames) {
	    checkout.checkReviewFields(fieldNames, "//div[@class='checkout-fulfilment_section']//span[@class='header']");
	}
	
	@Then("the footer should appear as per designs")
	public void the_footer_should_appear_as_per_designs() {
	    checkout.footerConfirm();
	}
	
	@Then("none of the footer icons are clickable")
	public void none_of_the_icons_are_clickable() {
	    checkout.footerIconConfirm();
	}
	
	@Then("the {string} message and {string} are displayed below the {string} as per designs")
	public void the_message_and_are_displayed_below_the_Order_Summary_as_per_designs(String message, String phone, String section) {
	    checkout.confirmHelpline(message, phone, section);
	}
	
	@Then("the helpline link changes the mouse cursor to a pointer")
	public void the_link_changes_the_mouse_cursor_to_a_pointer() {
	    checkout.cursorVerifyHelpLine();
	}
	
	@Then("the phone number is a clickable link")
	public void the_phone_number_is_a_clickable_link(int format) {
		checkout.helplineLink(format);
	}
	
	@Then("the helpline message is pushed down the page immediately beneath the Order Summary component")
	public void the_helpline_message_is_pushed_down_the_page_immediately_beneath_the_Order_Summary_component() {
	    checkout.checkHelpLineLocation();
	}
	
	@Then("there is an Order Summary component on the right hand side of the Checkout components underneath the Shopping Bag summary as per designs")
	public void there_is_an_Order_Summary_component_on_the_right_hand_side_of_the_Checkout_components_underneath_the_Shopping_Bag_summary_as_per_designs() {
	    checkout.orderSummaryLocation();
	}

	@Then("the Order Summary component includes non-interactive fields")
	public void the_Order_Summary_component_includes_non_interactive_fields() {
	    List<String> fieldNames = 
	    		Arrays.asList("SUBTOTAL","SHIPPING","VOUCHER","GIFT CARD","TAX","TOTAL");
	    checkout.checkReviewFields(fieldNames, "//div[@class='checkout_order-summary']//span[@class='header']");
	    
	}
	
	@Then("there is an Order Summary component above the Edit Shopping Bag link")
	public void there_is_an_Order_Summary_component_above_the_Edit_Shopping_Bag_link() {
	    checkout.mobileOrderSummaryLocation();
	}
	
	@Then("the mobile Order Summary component includes non-interactive fields")
	public void the_mobile_Order_Summary_component_includes_non_interactive_fields() {
		List<String> fieldNames = 
	    		Arrays.asList("SUBTOTAL","SHIPPING","VOUCHER","GIFT CARD","TAX","TOTAL");
	    checkout.checkReviewFields(fieldNames, "//div[@class='checkout-shopping-bag_order-summary is-open']//span[@class='header']");
	    
	}
	
	@Then("the Order Summary is no longer displayed")
	public void the_Order_Summary_is_no_longer_displayed() {
	    Helpers.isDisplayed("//div[@class='checkout-shopping-bag_order-summary']", false);
	}
	
	@Then("the Order Summary is pushed down the page immediately beneath the Shopping Bag summary")
	public void the_Order_Summary_is_pushed_down_the_page_immediately_beneath_the_Shopping_Bag_summary() {
		checkout.orderSummaryLocation();
	}

	@Then("the Order Summary returns to its original position immediately beneath the Shopping Bag summary")
	public void the_Order_Summary_returns_to_its_original_position_immediately_beneath_the_Shopping_Bag_summary() {
		checkout.orderSummaryLocation();
	}

	@Given("the user is in the payment section of checkout")
	public void the_user_is_in_the_payment_section_of_checkout() {
	    // REVIEW this may need to be updated as functionality is added to the site
		checkout.enterPaymentSection();
		}
	
	@When("the user selects the {string} payment option")
	public void the_user_selects_the_payment_option(String paymentType) {
	    checkout.choosePaymentType(paymentType);
	}
	
	@Then("the checkbox should display under the card details fields as per designs")
	public void the_checkbox_should_display_under_the_card_details_fields_as_per_designs() {
	    Helpers.isXaboveYbyXpath("//pink-payment-options-form//input[@name='expirationDate']",
	    		"//pink-payment-options-form//input[@type='checkbox']", true);
	}
	
	@Given("the address fields appear underneath the checkbox for users input")
	public void the_address_fields_appear_underneath_for_users_input() {
		List<String> fieldNames = Arrays.asList("Address 1*", "Address 2", "City/Town*",
				"County*", "Postcode*", "Country");
	    checkout.verifyPaymentAddressFields(fieldNames);
	}
	
	@Then("the billing address fields disappear")
	public void the_billing_address_fields_disappear() {
	    checkout.billingAddressFieldsDissapear();
	}
	
	@Then("the {string}, {string} and {string} payment options should display with radio buttons as per designs")
	public void the_and_payment_options_should_display_with_radio_buttons_as_per_designs(String creditCard, String paypal, String giftCard) {
	    Helpers.findComponentByText(creditCard);
	    Helpers.findComponentByText(paypal);
	    Helpers.findComponentByText(giftCard);
	}
	
	@Then("the payment section should be greyed out as per designs")
	public void the_payment_section_should_be_greyed_out_as_per_designs() {
	    Helpers.checkCSS("//pink-payment-options-selector//fieldset", "", "disabled");
	}
	
	@Then("the relevant payment fields will be displayed")
	public void the_relevant_payment_fields_will_be_displayed(List<String> fieldNames) {
	    checkout.checkFields(fieldNames, 1);
	}
	
	@Then("the {string} checkbox will appear checked below the fields")
	public void the_checkbox_will_appear_checked_below_the_fields(String string) {
	    Helpers.isSelectedByText(string, true);
	    Helpers.isXaboveYbyXpath("//pink-payment-options-form//input[@name='expirationDate']", 
	    		"//pink-payment-options-form//input[@type='checkbox']", true);
	}
	
	@Then("the gift card fields will be displayed")
	public void the_gift_card_fields_will_be_displayed(List<String> fieldNames) {
	    for (String fieldName : fieldNames) {
	    	Helpers.findElementByPlaceholder(fieldName);
	    }
	}
	
	@Then("the Captcha security check will be displayed beneath the fields")
	public void the_Captcha_security_check_will_be_displayed_beneath_the_fields() {
	    	Helpers.findElementByXpath("//*[contains(@class, 'captcha')]");
	}
	
	@Then("no payment fields will display underneath")
	public void no_payment_fields_will_display_underneath() {
	    checkout.noPayPalFields();
	}
	
	@Then("a paragraph of text will appear beneath the radio buttons")
	public void a_paragraph_of_text_will_appear_beneath_the_radio_buttons() {
	    Helpers.findElementByXpath("//p[contains(@class,'paypal-copy')]");
	    Helpers.getTextByXpath("//p[contains(@class,'paypal-copy')]");
	}
	
	@Then("the Review section of the Checkout page will not display")
	public void the_Review_section_of_the_Checkout_page_will_not_display() {
	    checkout.noPayPalReview();
	}
	@Then("an active CTA button will be displayed with a black outline, black fill and white text")
	public void an_active_CTA_button_will_be_displayed_with_a_black_outline_black_fill_and_white_text() {
	    checkout.buttonCheck(true);
	}
	
	@Then("an inactive CTA button will be displayed with a grey outline, grey fill and white text")
	public void an_inactive_CTA_button_will_be_displayed_with_a_grey_outline_grey_fill_and_white_text() {
	    checkout.buttonCheck(false);
	}
	
	@When("the user hovers over an active CTA button")
	public void the_user_hovers_over_an_active_CTA_button() {
	    checkout.hoverCTA(true);
	}
	
	@Then("the cursor hovering over an active CTA button will be a pointing finger icon")
	public void the_cursor_hovering_over_an_active_CTA_button_will_be_a_pointing_finger_icon() {
	    checkout.hoverCTA(true);
	    Helpers.checkCSS("//button[not(@disabled)]", "pointer", "cursor");
	}
	
	@Then("the cursor hovering over an inactive CTA button will be the default mouse cursor")
	public void the_cursor_hovering_over_an_inactive_CTA_button_will_be_the_default_mouse_cursor() {
		checkout.hoverCTA(false);
	    Helpers.checkCSS("//button[@disabled]", "auto", "cursor");
	}
	
	@When("the user moves the cursor off the active CTA button")
	public void the_user_moves_the_cursor_off_the_active_CTA_button() {
	    Helpers.hoverOnByText("Country");
	}
	
	@Then("the user can see the products in the Shopping Bag")
	public void the_user_can_see_the_products_in_the_Shopping_Bag() {
	    Helpers.isDisplayed("//div[@class='checkout-shopping-bag_items']", true);
	}
	
	@Then("the gift message field is displayed underneath the associated product")
	public void the_gift_message_field_is_displayed_underneath_the_associated_product() {
	    Helpers.isDisplayed("//textarea", true);
	    Helpers.isXaboveYbyXpath("//img[@class='checkout-shopping-bag-item_image']",
	    		"//textarea", true);
	}

	@Then("the text is greyed out and not clickable")
	public void the_text_is_greyed_out_and_not_clickable() {
	    Helpers.checkCSS("//textarea", "", "disabled");
	}
	
	@When("the user scrolls down past the Shopping Bag header")
	public void the_user_scrolls_down_past_the_Shopping_Bag_header() {
	    Helpers.scrollToXpath("//pink-footer");
	}
	
	@Then("the Shopping Bag header is stuck to the top of the screen")
	public void the_Shopping_Bag_header_is_stuck_to_the_top_of_the_screen() {
	    Helpers.isDisplayed("//button[@class='checkout-shopping-bag_button']", true);
	    
	}
	
	@When("the user scrolls up past the original placement of the Shopping Bag header")
	public void the_user_scrolls_up_past_the_original_placement_of_the_Shopping_Bag_header() {
	    Helpers.scrollToXpath("//div[@class='container simple-header']");
	}

	@Then("the Shopping Bag header is unstuck and in its original placement on the Checkout page")
	public void the_Shopping_Bag_header_is_unstuck_and_in_its_original_placement_on_the_Checkout_page() {
	    Helpers.findElementByXpath("//div[@class='checkout_shopping-bag']");
	}
	
	@Then("the {string} field is displayed beneath it as per wireframe")
	public void the_field_is_displayed_beneath_it_as_per_wireframe(String placeholder) {
	    Helpers.findElementByXpath("//input[@placeholder=\'"+placeholder+"\']");
	    Helpers.isXaboveYbyXpath("//div[@class='form-button-selector']",
	    		"//input[@placeholder=\'"+placeholder+"\']", true);
	}
	
	@Then("there is a clickable black arrow next to the {string} field")
	public void there_is_a_clickable_black_arrow_next_to_the_field(String string) {
	    // TODO there's an arrow but its not clickable. Ask about this. Skip for now.
	}

	@Then("beneath the {string} field there is text that says {string}")
	public void beneath_the_field_there_is_text_that_says(String string1, String string2) {
	    Helpers.findElementByXpath("//*[text()='or ' ] | //*[text()='use my location']");
	    Helpers.isXaboveYbyXpath("//input[@placeholder=\'"+string1+"\']",
	    		"//*[text()='or ' ] | //*[text()='use my location']", true);
	}
	
	@Given("a list of Pink stores is displayed")
	public void a_list_of_Pink_stores_is_displayed() {
	    Helpers.isDisplayed("//pink-collect-in-store-list", true);
	}
	
	@Given("each Pink store cell has a clickable ‘Show on map’ link")
	public void each_Pink_store_cell_has_a_clickable_Show_on_map_link() {
	    checkout.eachStoreHasMap();
	}
	
	@Given("each Pink store cell has a clickable ‘See store details' link that is underlined")
	public void each_Pink_store_cell_has_a_clickable_See_store_details_link_that_is_underlined() {
	    checkout.eachStoreHasDetails();
	}
	
	
	@Then("the system will load the map component")
	public void the_system_will_load_the_map_component() {
	    Helpers.isDisplayed("//pink-gmap", true);
	}
	
	@Then("the {string} summary and {string} link are displayed")
	public void the_summary_and_link_are_displayed(String summary, String link) {
	    Helpers.findComponentByText(summary);
	    Helpers.findElementByXpath("//pink-collect-in-store-pickup//*[text()=\'"+link+"\']");
	    Helpers.getTextByXpath("//pink-collect-in-store-pickup//span[@class='checkout-preview_detail-description']");
	}
	
	@When("the user clicks on the {string} link in the {string} summary")
	public void the_user_clicks_on_the_link_in_the_summary(String link, String string2) {
	    Helpers.clickOnByXpath("//pink-collect-in-store-pickup//*[text()=\'"+link+"\']");
	}
}
