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
	    switch (quantity) {
	    	case "increases":
	    		//TODO click the + 
	    		break;
	    	case "decreases":
	    		//TODO click the -
	    		break;
	    }
	}

	@Then("the user's cart is updated to include the {word} quantity")
	public void the_user_s_cart_is_updated_to_include_the_selected_quantity(String quantity) {
		switch (quantity) {
    	case "increased":
    		//TODO the number should increase by one
    		break;
    	case "decreased":
    		//TODO the number should decrease by one
    		break;
    }
	}
	
	@Then("the Order Summary fields are updated to reflect the change in cart quantity")
	public void the_Order_Summary_fields_are_updated_to_reflect_the_change_in_cart_quantity(String fieldsCSV) {
		List<String> fields = Arrays.asList(fieldsCSV.split("\\s*,\\s*"));
	    System.out.println(fields);
	    System.out.println(fields.size());
	    System.out.println(fields.get(1));
	    //TODO 
	}
	
	@Given("the user has a quantity of {int} selected for a product")
	public void the_user_has_a_quantity_of_selected_for_a_product(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user clicks {string} on the quantity selector")
	public void the_user_clicks_on_the_quantity_selector(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the product quantity will remain {int}")
	public void the_product_quantity_will_remain(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}


}
