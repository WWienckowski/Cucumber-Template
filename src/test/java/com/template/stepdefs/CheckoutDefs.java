package com.template.stepdefs;

import com.template.CheckoutState;
import com.template.page_objects.CheckoutPage;
import driver.DriverFactory;
import driver.SharedDriver;
import helpers.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


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

	@Then("there will be a {string} component")
	public void there_will_be_a_component(String elementName) {
	    Verify.findComponentByText(elementName);
	}

	@Given("the Shopping Bag control is {word}")
	public void the_Shopping_Bag_control_is_minimised(String state) {
	    Boolean open = !state.contains("minimised");
	    checkout.bagControlIsOpen(open);
	}

	@Then("the user can see the products in the Shopping Bag")
	public void the_user_can_see_the_products_in_the_Shopping_Bag() {
	    Verify.isDisplayed("//div[@class='checkout-shopping-bag_items']", true);
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
	public void there_is_a_clickable_black_arrow_next_to_the_field(String field) {
	    // TODO there's an arrow but its not clickable. Ask about this. Skip for now.
	}

	@Then("beneath the {string} field there is text that says 'or use my location'")
	public void beneath_the_field_there_is_text_that_says(String field) {
	    Verify.findElementByXpath("//*[text()='or ' ] | //*[text()='use my location']");
	    Verify.isXaboveYbyXpath("//input[@placeholder=\'"+field+"\']",
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
	
	@When("the user clicks on the Edit link in the Picking up summary")
	public void the_user_clicks_on_the_link_in_the_summary() {
		Click.javascriptClickXpath("//a[@class='edit-link']");
	}
	
	@Given("the user is in the Card payment section")
	public void the_user_is_in_the_Card_payment_section() {
	    Navigate.to("checkout");
	    Move.idleForX(300);
	    checkout.enterPaymentSection(); 
	    Move.idleForX(1000);
	}
	
	@Given("the user clicks on the Shopping Bag button")
	public void the_user_clicks_on_the_button() {
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

	@Then("the TAX field value on the checkout order summary will be zero")
	public void theTAXFieldValueOnTheCheckoutOrderSummaryWillBeZero() {
		checkout.startWithZeroTax();
	}

	@And("the checkout summary TAX will be displayed in dollars")
	public void theCheckoutSummaryTAXWillBeDisplayedIn$() {
		checkout.displayTaxInDollars();
	}

	@And("the user has entered a valid ZIP code in the {string} field")
	public void theUserHasEnteredAValidZIPCodeInTheZIPCodeField(String field, String entry) {
		Input.inputTextByPlaceholder(field, entry);
	}

	@Then("the system will calculate the tax to be applied to the order")
	public void theSystemWillCalculateTheTaxToBeAppliedToTheOrder() {
		String taxNumber = Cart.getLineItemAttribute(0, "USSLSTXB2C");
		DriverFactory.getScenario().write(taxNumber);
		// TODO calculate tax
	}

	@And("the TAX field in the Order Summary will display the tax to be applied to the order")
	public void theTAXFieldInTheOrderSummaryWillDisplayTheTaxToBeAppliedToTheOrder() {
		// TODO display tax
	}

	@And("the Contact for Order Component will contain the correct fields")
	public void theContactForOrderComponentWillContainTheCorrectFields(String stepData) {
		List<String> fields = Arrays.asList(stepData.split(","));
		checkout.checkContactFields(fields);
	}

	@Given("the user has provided valid entries for all required fields in the {string} section")
	public void theUserHasProvidedValidEntriesForAllRequiredFieldsInTheDelivery_typeSection(String deliveryType) {
		if (deliveryType.equals("Ship to Address")){
			checkout.enterShippingAddress();
		} else {
			checkout.collectInStoreIsActive();
			checkout.enterCollectInStoreInfo();
		}
	}

    @Then("the user\\'s contact details are captured")
    public void theUserSContact_detailsAreCaptured(String entries) {

    }

    @Then("the user\\'s cart will reflect their choice to subscribe to updates")
    public void theUserSCartWillReflectTheirChoiceToSubscribeToUpdates() {
	    // TODO check the cart object value that indicates a user wants updates
    }

    @Given("the user has already completed the Delivery section")
    public void theUserHasAlreadyCompletedTheDeliverySection() {
	    Move.idleForX(2000);
	    checkout.enterPaymentSection();
    }

    @When("the user clicks 'Edit' on the {word} section")
    public void theUserClicksEditOnTheDeliverySection(String section) {
	    checkout.clickToEdit(section);
    }

    @Then("the Contact for Order component is active again")
    public void theContactForOrderComponentIsActiveAgain() {
        Verify.findComponentByText("Contact for order");
    }

    @And("the user can change their Contact for Order entries")
    public void theUserCanChangeTheirContactForOrderEntries() {
        checkout.changeContactEntries();
    }
}
