package com.template.stepdefs;

import java.util.Map;

import com.template.PLPState;
import com.template.page_objects.PLPage;

import driver.SharedDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PLPDefs {
	PLPage plp;
	PLPState plpState;
	
	public PLPDefs(SharedDriver driver, PLPage plp, PLPState plpState){
		this.plp = plp; 
		this.plpState = plpState;
	}
	
	@Then("each product should display the product swatches beneath the product image")
	public void each_product_should_display_the_product_swatches_beneath_the_product_image() {
	    plp.checkProductsHaveImagesAndSwatches();
	}

	@Then("the name of each selected colour variant is displayed as per designs")
	public void the_name_of_each_selected_colour_variant_is_displayed_as_per_designs() {
	    plp.checkProductsHaveColorNames();
	}

	@When("the user clicks on a colour swatch beneath a product PDP link")
	public void the_user_clicks_on_a_colour_swatch_beneath_a_product_PDP_link() {
	    Map<String,Object> data = plp.selectColorSwatch();
	    plpState.setProductIndex((Integer)data.get("product"));
	    plpState.setSwatchIndex((Integer)data.get("swatch"));
	    plpState.setOriginalColor((String)data.get("color"));
	}

	@Then("the master image is the selected colour variant")
	public void the_master_image_is_the_selected_colour_variant() {
	    int productIndex = plpState.getProductIndex();
		int swatchIndex = plpState.getSwatchIndex();
		plp.checkSwatchMatchesImage(productIndex, swatchIndex);
	}

	@Then("the name of the selected colour variant is displayed as per designs")
	public void the_name_of_the_selected_colour_variant_is_displayed_as_per_designs() {
		int productIndex = plpState.getProductIndex();
		String originalColor = plpState.getOriginalColor();
		plp.checkColorName(productIndex, originalColor);
	}
	
	@Then("all attribute accordions will be minimised")
	public void all_attribute_accordions_will_be_minimised() {
		plp.checkAllAccordiansMinimised();
	}

	@Then("the arrows on the attribute accordions will be pointing down")
	public void the_arrows_on_the_attribute_accordions_will_be_pointing_down() {
		plp.checkAllArrowsPointDown();
	}
	
	@When("the customer clicks the expand icon for any attribute")
	public void the_customer_clicks_the_expand_icon_for_any_attribute() {
	    plp.clickAttribute(0);
	}

	@Then("the attribute filter options will be displayed")
	public void the_attribute_filter_options_will_be_displayed() {
	    plp.checkForFilterOptions(0);
	}

	@Then("the arrow on the accordion will be pointing up")
	public void the_arrow_on_the_accordion_will_be_pointing_up() {
	    plp.checkForUpArrow(0);
	}
	
	@Given("the attribute accordion is expanded")
	public void the_attribute_accordion_is_expanded() {
		plp.clickAttribute(0);
	}

	@When("the user clicks that attribute accordian")
	public void the_user_clicks_that_attribute_accordian() {
		plp.clickAttribute(0);
	}

	@Then("the attribute accordian will minimise")
	public void the_attribute_accordian_will_minimise() {
	    plp.checkAllAccordiansMinimised();
	}
	
	@When("the user clicks on an available attribute")
	public void the_user_clicks_on_an_available_attribute() {
	    plp.selectAttribute(0);
	}

	@Then("the attribute text will be bold")
	public void the_attribute_text_will_be_bold() {
	    plp.checkActiveAttributeText();
	}

	@Then("the attribute bullet point is bold\\/circled as per designs")
	public void the_attribute_bullet_point_is_bold_circled_as_per_designs() {
	    plp.checkActiveAttributeBullets();
	}
	
	@When("the user clicks on that selected attribute again")
	public void the_user_clicks_on_that_selected_attribute_again() {
		plp.selectAttribute(0);
	}
	
	@Then("the attribute reverts back to its normal styling")
	public void the_attribute_reverts_back_to_its_normal_styling() {
	    plp.checkInactiveFilterOptionSyle();
	}
}
