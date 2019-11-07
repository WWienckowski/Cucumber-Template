package com.template.stepdefs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;

import com.template.CheckoutState;
import com.template.page_objects.CheckoutPage;

import driver.DriverFactory;
import driver.SharedDriver;
import helpers.Cart;
import helpers.Click;
import helpers.Input;
import helpers.Move;
import helpers.Navigate;
import helpers.Screenshot;
import helpers.Verify;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CheckoutDefs {
	private CheckoutPage checkout;
	private CheckoutState checkoutState;
	
	public CheckoutDefs(SharedDriver driver, CheckoutPage checkout, CheckoutState checkoutState) {
		this.checkout = checkout;
		this.checkoutState = checkoutState;
	}
	
	@Given("the user is in the Ship to Address section")
	public void the_user_is_in_the_Ship_to_Address_section() {
	    checkout.shipToAddressIsActive();
	}
	
	@Given("the user is in the Collect in a Pink Store section")
	public void the_user_is_in_the_Collect_in_a_Pink_Store_section() {
	    checkout.collectInStoreIsActive();
	}
	
	@When("the Ship to Address section of the UK checkout page loads")
	public void the_Ship_to_Address_section_of_the_UK_checkout_page_loads() {
		Verify.isActiveByText("Ship to Address");
		checkout.locationIs("UK");
	}

	@Then("the Shipping Address section will display all fields")
	public void the_page_will_display_all_fields(List<String> fields) {
	    int fieldsetIndex = 1;	
		checkout.checkFields(fields, fieldsetIndex);
	}

	@Given("the user is in the Ship to Address section of the checkout page")
	public void the_user_is_in_the_Ship_to_Address_section_of_the_checkout_page() {
		Verify.isActiveByText("Ship to Address");
	}

	@When("the user clicks the {string}")
	public void the_user_clicks_the_title_field(String element) {
		Click.byText(element);
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
	    Click.byText(link);
	}

	@Then("a {string} link should appear below the last field")
	public void a_link_should_appear_below_the_last_field(String link) {
	    Verify.findLinkByText(link);
	}

	@Then("the {string} radio button should be selected")
	public void the_radio_button_should_be_selected_by_default(String selection) {
	    checkout.selectedRadioCheck(selection);
	}

	@Then("the selected button will be a black ring surrounding a filled-in black circle")
	public void the_button_will_be_a_color_ring_surrounding_a_filled_in_color_circle() {
		Screenshot.includeScreenshot();
	}
	
	@Then("the user is presented with delivery options")
	public void the_user_is_presented_with_delivery_options(List<String> optionNames) {
	    checkout.checkDeliveryOptions(optionNames);
	}
	
	@When("the user selects a {string} that is not already selected")
	public void the_user_selects_a_delivery_option_that_is_not_already_selected(String element) {
	    // Write code here that turns the phrase above into concrete actions
	    Verify.isInactiveByText(element);
		Click.byText(element);
	}
	
	@Then("that {string} should be highlighted in black as per designs")
	public void that_delivery_option_should_be_highlighted_in_black_as_per_designs(String element) {
	    Verify.isActiveByText(element);
	}
	
	@When("the user selects a {string} that is already selected")
	public void the_user_selects_a_that_is_already_selected(String element) {
	    Click.byText(element);
	    Verify.isActiveByText(element);
	    Click.byText(element);
	}

	@Then("that {string} should remain active")
	public void that_should_remain_active(String element) {
	    Verify.isActiveByText(element);
	}
	
	@When("the user clicks on the {string} section")
	public void the_user_clicks_on_the_section(String element) {
	    Click.byText(element);
	}
	
	@Then("there will be a {string} component")
	public void there_will_be_a_component(String elementName) {
	    Verify.findComponentByText(elementName);
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
		Click.byText("continue");
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
	    Verify.isButtonEnabledByText("Place order", false);
	    Verify.checkCSS("//div[pink-order-summary]", "0.5", "opacity");
	}
	
	@Then("the cursor becomes a finger")
	public void the_cursor_becomes_a_finger() {
	    checkout.cursorVerifyLogin();
	}

	@Then("the Review section is active")
	public void the_Review_section_is_active() {
	   Verify.checkCSS("//div[pink-order-summary]", "1", "opacity");
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
	    Verify.isDisplayed("//div[@class='checkout-shopping-bag_order-summary']", false);
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
		checkout.enterPaymentSection();
		}
	
	@When("the user selects the {string} payment option")
	public void the_user_selects_the_payment_option(String paymentType) {
	    checkout.choosePaymentType(paymentType);
	}
	
	@Then("the checkbox should display under the card details fields as per designs")
	public void the_checkbox_should_display_under_the_card_details_fields_as_per_designs() {
	    Verify.isXaboveYbyXpath("//pink-payment-options-form//input[@name='expirationDate']",
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
	    Verify.findComponentByText(creditCard);
	    Verify.findComponentByText(paypal);
	    Verify.findComponentByText(giftCard);
	}
	
	@Then("the payment section should be greyed out as per designs")
	public void the_payment_section_should_be_greyed_out_as_per_designs() {
	    Verify.checkCSS("//pink-payment-options-selector//fieldset", "", "disabled");
	}
	
	@Then("the relevant payment fields will be displayed")
	public void the_relevant_payment_fields_will_be_displayed(List<String> fieldNames) {
	    checkout.checkFields(fieldNames, 1);
	}
	
	@Then("the {string} checkbox will appear checked below the fields")
	public void the_checkbox_will_appear_checked_below_the_fields(String string) {
	    Verify.isSelectedByText(string, true);
	    Verify.isXaboveYbyXpath("//pink-payment-options-form//input[@name='expirationDate']", 
	    		"//pink-payment-options-form//input[@type='checkbox']", true);
	}
	
	@Then("the gift card fields will be displayed")
	public void the_gift_card_fields_will_be_displayed(List<String> fieldNames) {
	    for (String fieldName : fieldNames) {
	    	Verify.findElementByPlaceholder(fieldName);
	    }
	}
	
	@Then("the Captcha security check will be displayed beneath the fields")
	public void the_Captcha_security_check_will_be_displayed_beneath_the_fields() {
	    	Verify.findElementByXpath("//*[contains(@class, 'captcha')]");
	}
	
	@Then("no payment fields will display underneath")
	public void no_payment_fields_will_display_underneath() {
	    checkout.noPayPalFields();
	}
	
	@Then("a paragraph of text will appear beneath the radio buttons")
	public void a_paragraph_of_text_will_appear_beneath_the_radio_buttons() {
	    Verify.findElementByXpath("//p[contains(@class,'paypal-copy')]");
	    Verify.getTextByXpath("//p[contains(@class,'paypal-copy')]");
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
	    Verify.checkCSS("//button[not(@disabled)]", "pointer", "cursor");
	}
	
	@Then("the cursor hovering over an inactive CTA button will be the default mouse cursor")
	public void the_cursor_hovering_over_an_inactive_CTA_button_will_be_the_default_mouse_cursor() {
		checkout.hoverCTA(false);
	    Verify.checkCSS("//button[@disabled]", "auto", "cursor");
	}
	
	@When("the user moves the cursor off the active CTA button")
	public void the_user_moves_the_cursor_off_the_active_CTA_button() {
	    Move.hoverOnByText("Country");
	}
	
	@Then("the user can see the products in the Shopping Bag")
	public void the_user_can_see_the_products_in_the_Shopping_Bag() {
	    Verify.isDisplayed("//div[@class='checkout-shopping-bag_items']", true);
	}
	
	@Then("the gift message field is displayed underneath the associated product")
	public void the_gift_message_field_is_displayed_underneath_the_associated_product() {
	    Verify.isDisplayed("//textarea", true);
	    Verify.isXaboveYbyXpath("//img[@class='checkout-shopping-bag-item_image']",
	    		"//textarea", true);
	}

	@Then("the text is greyed out and not clickable")
	public void the_text_is_greyed_out_and_not_clickable() {
	    Verify.checkCSS("//textarea", "", "disabled");
	}
	
	@When("the user scrolls down past the Shopping Bag header")
	public void the_user_scrolls_down_past_the_Shopping_Bag_header() {
	    Move.scrollToXpath("//pink-footer");
	}
	
	@Then("the Shopping Bag header is stuck to the top of the screen")
	public void the_Shopping_Bag_header_is_stuck_to_the_top_of_the_screen() {
	    Verify.isDisplayed("//button[@class='checkout-shopping-bag_button']", true);
	    
	}
	
	@When("the user scrolls up past the original placement of the Shopping Bag header")
	public void the_user_scrolls_up_past_the_original_placement_of_the_Shopping_Bag_header() {
	    Move.scrollToTop();
	}

	@Then("the Shopping Bag header is unstuck and in its original placement on the Checkout page")
	public void the_Shopping_Bag_header_is_unstuck_and_in_its_original_placement_on_the_Checkout_page() {
	    Verify.findElementByXpath("//div[@class='checkout_shopping-bag']");
	}
	
	@Then("the {string} field is displayed beneath it as per wireframe")
	public void the_field_is_displayed_beneath_it_as_per_wireframe(String placeholder) {
	    Verify.findElementByXpath("//input[@placeholder=\'"+placeholder+"\']");
	    Verify.isXaboveYbyXpath("//div[@class='form-button-selector']",
	    		"//input[@placeholder=\'"+placeholder+"\']", true);
	}
	
	@Then("there is a clickable black arrow next to the {string} field")
	public void there_is_a_clickable_black_arrow_next_to_the_field(String string) {
	    // TODO there's an arrow but its not clickable. Ask about this. Skip for now.
	}

	@Then("beneath the {string} field there is text that says {string}")
	public void beneath_the_field_there_is_text_that_says(String string1, String string2) {
	    Verify.findElementByXpath("//*[text()='or ' ] | //*[text()='use my location']");
	    Verify.isXaboveYbyXpath("//input[@placeholder=\'"+string1+"\']",
	    		"//*[text()='or ' ] | //*[text()='use my location']", true);
	}
	
	@Given("a list of Pink stores is displayed")
	public void a_list_of_Pink_stores_is_displayed() {
	    Verify.isDisplayed("//pink-collect-in-store-list", true);
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
	    Verify.isDisplayed("//pink-gmap", true);
	}
	
	@Then("the {string} summary and {string} link are displayed")
	public void the_summary_and_link_are_displayed(String summary, String link) {
	    Verify.findComponentByText(summary);
	    Verify.findElementByXpath("//pink-collect-in-store-pickup//*[text()=\'"+link+"\']");
	    Verify.getTextByXpath("//pink-collect-in-store-pickup//span[@class='checkout-preview_detail-description']");
	}
	
	@When("the user clicks on the {string} link in the Picking up summary")
	public void the_user_clicks_on_the_link_in_the_summary(String link) {
		Click.javascriptClickXpath("//a[@class='edit-link']");
	}
	
	@Given("the user is in the Card payment section")
	public void the_user_is_in_the_Card_payment_section() {
	    Navigate.to("checkout");
	    Move.idleForX(300);
	    checkout.enterPaymentSection(); 
	    Move.idleForX(1000);
	}
	
	@Given("the user clicks on the {string} button")
	public void the_user_clicks_on_the_button(String string) {
	    checkout.clickBagButton();
	}
	
	@Given("the user clicks Continue without entering any information")
	public void the_user_clicks_Continue_without_entering_any_information() {
	    Click.clickOnByXpath("//pink-payment-options-form//button");
	}
	
	@When("the user expands the Shopping Bag summary")
	public void the_user_expands_the_Shopping_Bag_summary() {
	    checkout.clickBagButton();
	}
	
	@Then("the Gift Wrap check box is not displayed")
	public void the_Gift_Wrap_check_box_is_not_displayed() {
	    checkout.verifyGiftWrapCheckbox(false);
	}
	
	@Then("the Gift Wrap box is displayed")
	public void the_Gift_Wrap_box_is_displayed() {
		checkout.verifyGiftWrapCheckbox(true);
	}
	
	@Then("the Bag Summary gift message field is not displayed")
	public void the_Bag_Summary_gift_message_field_is_not_displayed() {
		checkout.verifyGiftMessageDisplayed(false);
	}
	
	@Then("the gift message field is displayed with the gift message")
	public void the_gift_message_field_is_displayed_with_the_gift_message() {
		checkout.verifyGiftMessageDisplayed(true);
	}
	
	@Then("the Bag Summary gift message field is greyed out")
	public void the_Bag_Summary_gift_message_field_is_greyed_out() {
	    Verify.checkCSS("//div[@class= 'bag-item_text-area form-control']", "rgba(151, 151, 151, 1)", "color");
	}

	@Then("the Gift Wrap box is checked and greyed out")
	public void the_Gift_Wrap_box_is_checked_and_greyed_out() {
	    Verify.checkCSS("//input[contains(@name, 'CheckoutItemMessage')]", "true", "checked");
	    Verify.checkCSS("//input[contains(@name, 'CheckoutItemMessage')]", "true", "disabled");
	}
	
	@Then("the user's products are displayed in the Shopping Bag summary")
	public void the_user_s_products_are_displayed_in_the_Shopping_Bag_summary() {
	    Assert.assertEquals("Incorrect number of products in summary",
	    		DriverFactory.getDriver().findElements(By.tagName("pink-bag-item")).size(),
	    		Cart.getLineItemCount());
	    DriverFactory.getScenario().write("All items shown");
	}

	@Then("the primary image for each product is displayed in the Shopping Bag summary")
	public void the_primary_image_for_each_product_is_displayed_in_the_Shopping_Bag_summary() {
	    checkout.verifySummaryImages();
	}
	
	@Then("each applicable chosen attribute is displayed for each product")
	public void each_applicable_chosen_attribute_is_displayed_for_each_product(List<Map<String, String>> attributes) {
	    checkout.checkDisplayedAttributes(attributes); 
	}
	
	@Then("the SUBTOTAL field in the Order Summary will be the value of all the products in the user's Shopping Bag")
	public void the_SUBTOTAL_field_in_the_Order_Summary_will_be_the_value_of_all_the_products_in_the_user_s_Shopping_Bag(String device) {
	    boolean mobile = device.equals("mobile");
		checkout.verifySubtotal(mobile);
	}
	
	@Given("the user enters {int} characters in the checkout address search field")
	public void the_user_enters_characters_in_the_checkout_address_search_field(Integer chars) {
	    checkout.addressSearchEntry(chars);
	}

	@Then("no address suggestions will display")
	public void no_address_suggestions_will_display() {
	    checkout.addressSuggestionDisplayed(false);
	}

	@Then("the site will suggest addresses based on the user's entry")
	public void the_site_will_suggest_addresses_based_on_the_user_s_entry() {
		checkout.addressSuggestionDisplayed(true);
	}
	
	@When("the user selects an address")
	public void the_user_selects_an_address() {
	    String info = checkout.selectSuggestedAddress();
	    checkoutState.setSelectedAddress(info);
	}
	
	@Then("the delivery address fields are expanded")
	public void the_delivery_address_fields_are_expanded(List<String> fields) {
	    Move.idleForX(1000);
		checkout.selectAddressExpandsFields(fields);
	}

	@Then("each field is populated with the selected address value")
	public void each_field_is_populated_with_the_selected_address_value(List<String> fields) {
	    checkout.selectAddressFillsFields(fields, checkoutState.getSelectedAddress());
	}

	@Given("the user expands the manual address entry")
	public void the_user_expands_the_manual_address_entry() {
	    checkout.expandManualAddressEntry();
	}
	
	@When("the user has entered a valid entry into the Ship to Address {string} field")
	public void the_user_has_entered_a_valid_entry_into_the_Last_name_field(String field, String entry) {
	    checkout.enterAddressValue(field, entry);
	}
	
	@Then("user's entry is displayed in the Ship to Address {string} field after exiting that field")
	public void user_s_entry_is_displayed_in_the_Last_name_field_after_exiting_that_field(String field, String entry) {
	    checkout.validEntryRemains(field, entry);
	}
	
	@Given("the user has entered an invalid entry in the Ship to Address {string} field.")
	public void the_user_has_entered_an_invalid_entry_in_the_field(String field, String entry) {
	    checkout.enterAddressValue(field, entry);
	}
	
	//@Then("the error message displays as per designs")
	//public void the_error_message_displays_as_per_designs(String errorMessage) {
	//    checkout.invalidEntryShowsError(errorMessage);
	//}
	
	@Given("the user has not entered anything in the Billing/Shipping Address fields")
	public void the_user_has_not_entered_anything_in_the_Billing_Address_fields() {
	    Move.idleForX(1000);
	}
	
	@Given("the user has entered a valid/invalid entry in the Billing Address {string} field")
	public void the_user_has_entered_a_valid_entry_in_the_Billing_Address_Address_field(String field, String entry) {
	    checkout.billingAddressEntry(field, entry);
	}
	
	@When("the user clicks out of the Billing Address field")
	public void the_user_clicks_out_of_the_Billing_Address_field(String field) {
	    Input.exitFieldByPlaceholder(field);
	}
	
	@Then("user's entry is displayed in the Billing Address field")
	public void user_s_entry_is_displayed_in_the_Billing_Address_field(List<String> data) {
		String field = data.get(0);
		String entry = data.get(1);
		checkout.billingAddressEntryPersists(field, entry);
	}
	
	@Given("the user has provided valid entries and selections for all Billing Address fields")
	public void the_user_has_provided_valid_entries_and_selections_for_all_Billing_Address_fields(Map<String,String> data) {
	    for (Map.Entry<String,String> pair : data.entrySet()) {
	    	switch (pair.getKey()) {
	    	case "State*":
	    		checkout.billingAddressChooseState(pair.getValue());
	    		break;
	    	case "County*":
	    		checkout.billingAddressChooseCounty(pair.getValue());
	    		break;
	    	default:
	    		checkout.billingAddressEntry(pair.getKey(), pair.getValue());
	    	}
	    }
	}
	
	@Given("the user has entered an invalid entry to the Contact for Order {string} field")
	public void the_user_has_entered_an_invalid_entry_to_the_Contact_for_Order_Full_name_field(String field, String entry) {
	    Input.inputTextByPlaceholder(field, entry);
	}
	
	@Given("the user has not entered any contact details")
	public void the_user_has_not_entered_any_contact_details() {
	    Move.idleForX(500);
	}
	
	@Then("the user remains in the Ship to Address section")
	public void the_user_remains_in_the_Ship_to_Address_section() {
	    Verify.findComponentByText("Shipping addess");
	}
	
	@Given("the user has provided valid entries and selections for all Ship to Address fields")
	public void the_user_has_provided_valid_entries_and_selections_for_all_Ship_to_Address_fields() {
	    checkout.enterShippingAddress();
	}
}
