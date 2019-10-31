package com.template.stepdefs;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.template.BagState;
import com.template.page_objects.BagPage;

import driver.DriverFactory;
import driver.SharedDriver;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
import helpers.Verify;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ShoppingBagDefs {
	
private BagPage bag;
private BagState bagState;
	
	public ShoppingBagDefs(SharedDriver driver, BagPage bag, BagState bagState) {
		this.bag = bag;
		this.bagState = bagState;
	}
	
	@When("the user {word} the quantity using the quantity selector")
	public void the_user_changes_the_quantity_using_the_quantity_selector(String quantity) {
		bag.clickQuantitySelect(quantity);
	}

	@Then("the user's cart is updated to include the {word} quantity")
	public void the_user_s_cart_is_updated_to_include_the_selected_quantity(String quantity) {
		bag.cartUpdatesQuantity(quantity);
	}
	
	@Then("the Order Summary fields are updated to reflect the {word} in cart quantity")
	public void the_Order_Summary_fields_are_updated_to_reflect_the_change_in_cart_quantity(String change, String fieldsCSV) {
		List<String> fields = Arrays.asList(fieldsCSV.split("\\s*,\\s*"));
	    bag.summaryUpdateQuantity(fields);
	}
	
	@Then("the Shopping Bag Order Summary values are updated")
	public void the_shopping_bag_order_summary_values_are_updated() {
		String total = Integer.toString(Cart.getTotalPrice()).replace("00", "");
		bag.verifyTotal(total);
	}
	
	@Given("the user has a quantity of 1 selected for a product")
	public void the_user_has_a_quantity_of_selected_for_a_product() {
		bag.clickQuantitySelect("decreases");
	}

	@Then("the product quantity will remain 1")
	public void the_product_quantity_will_remain() {
		bag.cartUpdatesQuantity("decreased");
	}
	
	@Then("the product cell will contain the appropriate elements as per designs")
	public void the_product_cell_will_contain_the_appropriate_elements_as_per_designs(List<String> elements) {
	    bag.checkProductElements(elements);
	}
	
	@When("the user clicks on the {string} link in the bag")
	public void the_user_clicks_on_the_link_in_the_bag(String link) {
	    bagState.setLineItems(Cart.getLineItemCount());
		bagState.setProductsDisplayed(bag.getItemNumber());
	    Click.byLinkText("Remove Item");
	}
	
	@Then("the entire selected quantity of that product is removed from the cart")
	public void the_entire_selected_quantity_of_that_product_is_removed_from_the_cart() {
		Move.idleForX(1000);
		Assert.assertEquals("Unexpected amount of products in cart", 1, Cart.getLineItemCount());
		DriverFactory.getScenario().write("Cart quantity is correct.");
		System.out.println(bagState.getLineItems());
	}
	
	@Then("the product is no longer displayed in the shopping bag")
	public void the_product_is_no_longer_displayed_in_the_shopping_bag() {
		Assert.assertEquals("Incorrect number of products displayed", bagState.getProductsDisplayed()-1, bag.getItemNumber());
		DriverFactory.getScenario().write("Correct number of products displayed");
	}
	
	@Then("the empty shopping bag page is displayed")
	public void the_empty_shopping_bag_page_is_displayed() {
		Verify.checkForElementByText("Your Shopping Bag is empty.");
	}
	
	@Then("each product will display the following attributes if it has that attribute.")
	public void each_product_will_display_the_following_attributes_if_it_has_that_attribute(List<String> attributes) {
	    List<String> attNames = Arrays.asList
	    		("PRODUCT_ID", "INT_NAME", "COLOUR", "SIZE", "SKU_COLLAR_SIZE", "WEB_FIT", "SKU_SLEEVE_LENGTH", "price");
	    Map<String,String> attributeMap = new LinkedHashMap<String,String>();
	    for (int i=0; i<attributes.size(); i++) {
	        attributeMap.put(attributes.get(i), attNames.get(i));
	    }
	    bag.verifyProductAttributes(attributeMap);
	}
	
	@Then("no product cells will display")
	public void no_product_cells_will_display() {
	    Assert.assertEquals("Product cells are displayed", 0, bag.getItemNumber());
	    DriverFactory.getScenario().write("No product cells found");
	}

	@Then("the {string} button is displayed beneath the message")
	public void continue_Shopping_button_is_displayed_beneath(String button) {
	    Verify.checkForElementByText(button);
	}

	@Then("the Order Summary does not display")
	public void the_Order_Summary_does_not_display() {
	    Verify.isDisplayed("//pink-order-summary", false);
	}

	@Then("the Checkout buttons do not display")
	public void the_Checkout_buttons_do_not_display() {
		Verify.isDisplayed("//pink-shopping-bag-checkout-buttons", false);
	}

	@Then("the Helpline component does not display")
	public void the_Helpline_component_does_not_display() {
		Verify.isDisplayed("//pink-shopping-bag-checkout-help", false);
	}
	
	@Given("the user clicks on the gift wrap checkbox")
	public void the_user_clicks_on_the_gift_wrap_checkbox() {
		Move.idleForX(500);
	    Click.javascriptClickXpath("//div[@class='actions']//input[@type='checkbox']");
	}
	
	@Then("the Optional Gift Message field opens")
	public void the_Optional_Gift_Message_field_opens() {
		bag.verifyGiftMessageOpen();
	}
	
	@Then("the field will include ghost text of {string} \\(differs to designs)")
	public void the_field_will_include_ghost_text_of_differs_to_designs(String placeholder) {
	    Verify.checkCSS("//textarea", placeholder, "placeholder");
	}

	@Then("there is a character counter that displays {string}")
	public void there_is_a_character_counter_that_displays(String counter) {
	    Verify.checkForElementByText(counter);
	}
	
	@Then("the user is not able to resize the Optional Gift Message field")
	public void the_user_is_not_able_to_resize_the_Optional_Gift_Message_field() {
	    Move.idleForX(1000);
		Verify.checkCSS("//textarea", "none", "resize");
	}
	
	@Then("the gift wrap checkbox displays the cursor as a pointing finger icon")
	public void the_gift_wrap_checkbox_displays_the_cursor_as_a_pointing_finger_icon() {
	    bag.verifyGiftWrapCheckboxCursor();
	}
	
	@When("the user starts typing in the Optional Gift Message field")
	public void the_user_starts_typing_in_the_Optional_Gift_Message_field() {
	    bag.enterGiftMessage("Test");
	}
	
	@Then("the character counter displays the number of characters entered out of the 200 allowed")
	public void the_character_counter_displays_the_number_of_characters_entered_out_of_the_allowed() {
	    bag.checkCharacterCount();
	}
	
	@Then("the Optional Gift Message field closes")
	public void the_Optional_Gift_Message_field_closes() {
	    bag.verifyGiftMessageClosed();
	}
	
	@Given("the user has added a gift message")
	public void the_user_has_added_a_gift_message(String message) {
		bag.enterGiftMessage(message);
	}
	
	@Then("the gift message they added previously will be displayed")
	public void the_gift_message_they_added_previously_will_be_displayed(String message) {
	    bag.checkGiftMessagePersists(message);
	}
	
	@Then("the Gift Wrap checkbox is unchecked")
	public void the_checkbox_is_unchecked() {
		Move.idleForX(1000);
	    Verify.checkCSS("//div[@class='actions']//input[@type='checkbox']", "", "checked");
	}
	
	@Given("the user has typed 200 characters in the Optional Gift Message field")
	public void the_user_has_typed_characters_in_the_Optional_Gift_Message_field() {
	    String message = bagState.getMaxGiftMessage();
	    DriverFactory.getScenario().write(Integer.toString(message.length())+" characters entered");
		bag.enterGiftMessage(message);
	}

	@When("the user enters an additional character to the gift message")
	public void the_user_enters_an_additional_character_to_the_gift_message() {
		String message = bag.enterCharToGiftMessage();
		bagState.setGiftMessage(message);
	}

	@Then("no character is added to the gift message field")
	public void no_character_is_added_to_the_field() {
	    Assert.assertEquals("Gift message changed", bagState.getMaxGiftMessage(), bagState.getGiftMessage());
	    DriverFactory.getScenario().write("Gift message was unchanged");
	}

}
