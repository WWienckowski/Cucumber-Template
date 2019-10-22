package com.template.stepdefs;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import com.template.BagState;
import com.template.page_objects.BagPage;

import driver.DriverFactory;
import driver.SharedDriver;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
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
		Assert.assertEquals("Cart value was not updated", bagState.getLineItems()-1, Cart.getLineItemCount());
		DriverFactory.getScenario().write("Cart quantity is correct.");
	}
}
