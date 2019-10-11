package com.template.stepdefs;

import java.util.Arrays;
import java.util.List;

import com.template.PageObjectManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ShoppingBagDefs {
	PageObjectManager manager = Hooks.manager;
	
	@When("the user {word} the quantity using the quantity selector")
	public void the_user_changes_the_quantity_using_the_quantity_selector(String quantity) {
		manager.bag.clickQuantitySelect(quantity);
	}

	@Then("the user's cart is updated to include the {word} quantity")
	public void the_user_s_cart_is_updated_to_include_the_selected_quantity(String quantity) {
		manager.bag.cartUpdatesQuantity(quantity);
	}
	
	@Then("the Order Summary fields are updated to reflect the {word} in cart quantity")
	public void the_Order_Summary_fields_are_updated_to_reflect_the_change_in_cart_quantity(String change, String fieldsCSV) {
		List<String> fields = Arrays.asList(fieldsCSV.split("\\s*,\\s*"));
	    manager.bag.summaryUpdateQuantity(fields);
	}
	
	@Given("the user has a quantity of 1 selected for a product")
	public void the_user_has_a_quantity_of_selected_for_a_product() {
		manager.bag.clickQuantitySelect("decreases");
	}

	@Then("the product quantity will remain 1")
	public void the_product_quantity_will_remain() {
		manager.bag.cartUpdatesQuantity("decreased");
	}
}
