package com.template.stepdefs;

import java.util.List;

import org.junit.Assert;

import com.template.BagState;
import com.template.page_objects.MiniBagPage;

import driver.DriverFactory;
import driver.SharedDriver;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
import helpers.Verify;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MiniShoppingBagDefs {
	private MiniBagPage miniBag;
	private BagState bagState;
	
	public MiniShoppingBagDefs(SharedDriver driver, MiniBagPage miniBag, BagState bagState) {
		this.miniBag = miniBag;
		this.bagState = bagState;
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
	    miniBag.hoverOnBagIcon();
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
	
	@Then("the user can scroll up and down over the mini shopping bag")
	public void the_user_scrolls_up_and_down_over_the_mini_shopping_bag() {
	    miniBag.scrollInMiniBag();
	}
	
	@Then("the user can scroll up and down over the main page without scrolling the mini bag.")
	public void the_user_can_scroll_up_and_down_over_the_main_page_without_scrolling_the_mini_bag() {
	    miniBag.scrollMainPage();
	}
	
	@When("the user clicks on the {string} link in the mini bag")
	public void the_user_clicks_on_the_link_in_the_mini_bag(String string) {
		bagState.setLineItems(Cart.getLineItemCount());
		bagState.setProductsDisplayed(miniBag.getItemNumber());
		Click.byLinkText("Remove Item");
	}
	
	@Then("the product is no longer displayed in the mini shopping bag")
	public void the_product_is_no_longer_displayed_in_the_mini_shopping_bag() {
	    Assert.assertEquals("Incorrect number of products displayed", 1, miniBag.getItemNumber());
		DriverFactory.getScenario().write("Correct number of products displayed");
	}
	
	@Then("the mini bag will display the item\\(s) in their bag")
	public void the_mini_bag_will_display_the_item_s_in_their_bag() {
		Assert.assertEquals("Incorrect number of products displayed", Cart.getLineItemCount(), miniBag.getItemNumber());
		DriverFactory.getScenario().write("Correct number of products displayed");
	}
}
