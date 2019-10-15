package com.template.stepdefs;

import java.util.List;

import com.template.page_objects.MiniBagPage;

import driver.SharedDriver;
import helpers.Click;
import helpers.Move;
import helpers.Verify;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MiniShoppingBagDefs {
	private MiniBagPage miniBag;
	
	public MiniShoppingBagDefs(SharedDriver driver, MiniBagPage miniBag) {
		this.miniBag = miniBag;
	}
	
	@When("the user expands the mini shopping bag")
	public void the_user_exapands_the_mini_shopping_bag() {
	    miniBag.expandBag();
	}
	
	@Then("the product cells will contain the correct elements as per designs")
	public void the_product_cells_will_contain_the_correct_elements_as_per_designs(List<String> elements) {
	    miniBag.miniBagElements(elements);
	}
	
	@Then("the mini shopping bag is minimised")
	public void the_mini_shopping_bag_is_minimised() {
	    miniBag.bagIsMinimized();
	}
	
	@When("the user clicks anywhere off the the mini shopping bag")
	public void the_user_clicks_anywhere_off_the_the_mini_shopping_bag() {
	    Move.hoverOnByText("THE PINK SHIRT");
		Click.byText("THE PINK SHIRT");
	    
	}
	
	@When("the user CLICKS on the shopping bag icon in the header")
	public void the_user_CLICKS_on_the_shopping_bag_icon_in_the_header() {
	    Click.clickOnByXpath("//*[@class='icon-cart']");
	}
	
	@When("the user hovers over the Shopping Bag icon")
	public void the_user_hovers_over_the_Shopping_Bag_icon() {
	    // this is intentionally empty
	}
	
	@Then("the mini shopping bag is expanded")
	public void the_mini_shopping_bag_is_expanded() {
	    miniBag.expandBag();
	}
	
	@When("the user interacts with {string}, the behaviour will be identical to that on the Shopping Bag page")
	public void the_user_interacts_with(String control) {
	    switch(control){
	    case "Quantity Selector":
	    	//increment item
	    case "Remove Item Link":
	    	//click the link
	    case "Size Selector":
	    	//choose a size other than what's chosen
	    }
	}
	
	@Then("there is a {string} button in the mini bag as per designs")
	public void there_is_a_button_as_per_designs(String button) {
	    miniBag.checkMiniBagButtons(button);
	}
	
	@Then("the mini bag's {string} button is active")
	public void the_button_is_active(String button) {
	    Verify.isButtonEnabledByText(button, true);
	}

	@When("the user has not clicked\\/tapped on any element within the mini shopping bag for {int} seconds")
	public void the_user_has_not_clicked_tapped_on_any_element_within_the_mini_shopping_bag_for_seconds(Integer time) {
		Move.idleForX(time*1000);
	}

}
