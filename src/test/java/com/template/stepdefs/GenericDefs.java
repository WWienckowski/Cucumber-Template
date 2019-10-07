package com.template.stepdefs;

import com.template.Helpers;
import driver.SharedDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class GenericDefs {
	
	
	public GenericDefs(SharedDriver driver) {
		
	}
	
	
	@Given("the user is on the {word} page")
	public void navigate_to_page_url(String urlSuffix) {
		urlSuffix = urlSuffix.equals("PDP") ? "detail" : urlSuffix;
		Helpers.navigateTo(urlSuffix);
	}
	
	@When("the user clicks on {string}")
	public void user_clicks_on(String element) {
		Helpers.clickOnByText(element);
	}
	
	@Given("the checkbox for {string} is not checked")
	public void the_checkbox_for_is_not_checked(String checkbox) {
	    Helpers.isSelectedByText(checkbox, false);
	}

	@When("the user clicks the checkbox for {string}")
	public void the_user_clicks_the_checkbox_for(String checkbox) {
	    Helpers.clickCheckboxByText(checkbox);
	}

	@Given("the checkbox for {string} is checked")
	public void the_checkbox_for_is_checked(String checkbox) {
		Helpers.isSelectedByText(checkbox, true);
	}
	
	@When("the user clicks on {string} image")
	public void the_user_clicks_on_image(String altText) {
	    Helpers.clickOnByAltText(altText);
	}
	
	@When("the user clicks on {string} link")
	public void the_user_clicks_on_link(String linkText) {
	    Helpers.clickOnByLinkText(linkText);
	}
	
	@Then("the user is re-directed to the {word}")
	public void the_user_is_redirected(String page) {
		Helpers.checkUrl(page);
	}
	
	@Then("the {string} button is not disabled")
	public void the_button_is_not_disabled(String text) {
	    Helpers.isButtonEnabledByText(text, true);
	}
	
	@When("the user clicks on the {string} checkbox")
	public void the_user_clicks_on_checkbox_by_text(String checkText) {
	    Helpers.clickCheckboxByText(checkText);
	}
	
	@Then("there is a tool tip icon next to the {string} field")
	public void there_is_a_tool_tip_icon_next_to_the_field(String fieldName) {
	    Helpers.verifyToolTipByFieldName(fieldName);
	}
	
	@When("the user hovers over {string}")
	public void the_user_hovers_over(String text) {
	    Helpers.hoverOnByText(text);
	}
	
	@Then("there is a {string} component as per designs")
	public void there_is_a_component_as_per_designs(String componentText) {
	    Helpers.findComponentByText(componentText);
	}
	
	@Then("there is a {string} checkbox")
	public void there_is_a_checkbox(String componentText) {
	    Helpers.findComponentByText(componentText);
	}
	
	@Then("there is a {string} button that is grey with white text")
	public void there_is_a_button_that_is_grey_with_white_text(String componentText) {
		Helpers.findComponentByText(componentText);
		String xpath = "//*[contains(text(), \'"+componentText+"\')]";
		Helpers.checkCSS(xpath, "rgba(200, 200, 200, 1)", "background-color");
		Helpers.checkCSS(xpath, "rgba(255, 255, 255, 1)", "color");
	}
	
	@When("the user taps anywhere on the screen")
	public void the_user_taps_anywhere_on_the_screen() {
	    Helpers.tapAnywhere();
	}
	
	@Then("the {string} radio button is selected")
	public void the_radio_button_is_selected(String string) {
	    Helpers.isSelectedByText(string, true);
	}
	
	@Then("an active {string} button will display")
	public void an_active_button_will_display(String buttonText) {
	    Helpers.isButtonEnabledByText(buttonText, true);
	}
	
	@Then("the mouse cursor will revert to default")
	public void the_mouse_cursor_will_revert_to_default() {
	    Helpers.checkCSS("//body", "auto", "cursor");
	}
	
	@Then("the Shopping Bag page is loaded")
	public void the_Shopping_Bag_page_is_loaded() {
	    Helpers.checkUrl("bag");
	}
	
	@Then("the {string} button turns black")
	public void the_button_turns_black(String element) {
	    Helpers.isActiveByText(element);
	}
	
	@Then("{string} is bold and underlined")
	public void is_bold_and_underlined(String text) {
	    Helpers.checkCSS("//*[text()=\'"+text+"\']", "700", "font-weight");
	    Helpers.checkCSS("//*[text()=\'"+text+"\']", "underline solid rgb(0, 0, 0)", "text-decoration");
	}
}
