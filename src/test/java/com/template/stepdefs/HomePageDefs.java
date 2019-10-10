package com.template.stepdefs;

import java.util.List;

import com.template.PageObjectManager;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageDefs {
	PageObjectManager manager = Hooks.manager;
	
	@When("the user expands the mini shopping bag")
	public void the_user_exapands_the_mini_shopping_bag() {
	    manager.home.expandBag();
	}
	
	@Then("the product cells will contain the correct elements as per designs")
	public void the_product_cells_will_contain_the_correct_elements_as_per_designs(List<String> elements) {
	    manager.home.miniBagElements(elements);
	}
	
	@Then("the mini shopping bag is minimised")
	public void the_mini_shopping_bag_is_minimised() {
	    manager.home.bagIsMinimized();
	}
	
	@When("the user clicks anywhere off the the mini shopping bag")
	public void the_user_clicks_anywhere_off_the_the_mini_shopping_bag() {
	    
	}
	
	@When("the user CLICKS on the shopping bag icon in the header")
	public void the_user_CLICKS_on_the_shopping_bag_icon_in_the_header() {
	    manager.global.clickOnByXpath("//*[@class='icon-cart']");
	}
	
	@When("the user hovers over the Shopping Bag icon")
	public void the_user_hovers_over_the_Shopping_Bag_icon() {
	    
	}
	
	@Then("the mini shopping bag is expanded")
	public void the_mini_shopping_bag_is_expanded() {
	    manager.home.expandBag();
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
}

	