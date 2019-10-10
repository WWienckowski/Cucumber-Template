package com.template.stepdefs;

import java.util.List;

import com.template.PageObjectManager;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GenericDefs {
	PageObjectManager manager = Hooks.manager;
	
	@Given("the user is on the {word} page")
	public void navigate_to_page_url(String urlSuffix) {
		urlSuffix = urlSuffix.equals("PDP") ? "detail" : urlSuffix;
		urlSuffix = urlSuffix.equals("Bag") ? "basket/viewbasket" : urlSuffix;
		manager.global.navigateTo(urlSuffix);
	}
	
	@Given("there are products in the Shopping Bag")
	public void there_are_products_in_the_Shopping_Bag() {
		manager.global.navigateTo("homepage");
		manager.global.setCart();
	}
	
	@When("the user clicks on {string}")
	public void user_clicks_on(String element) {
		manager.global.clickOnByText(element);
	}
	
	@Given("the checkbox for {string} is not checked")
	public void the_checkbox_for_is_not_checked(String checkbox) {
	    manager.global.isSelectedByText(checkbox, false);
	}

	@When("the user clicks the checkbox for {string}")
	public void the_user_clicks_the_checkbox_for(String checkbox) {
	    manager.global.clickCheckboxByText(checkbox);
	}

	@Given("the checkbox for {string} is checked")
	public void the_checkbox_for_is_checked(String checkbox) {
		manager.global.isSelectedByText(checkbox, true);
	}
	
	@When("the user clicks on {string} image")
	public void the_user_clicks_on_image(String altText) {
	    manager.global.clickOnByAltText(altText);
	}
	
	@When("the user clicks on {string} link")
	public void the_user_clicks_on_link(String linkText) {
	    manager.global.clickOnByLinkText(linkText);
	}
	
	@Then("the user is re-directed to the {string} page")
	public void the_user_is_redirected(String page) {
		manager.global.checkUrl(page);
	}
	
	@Then("the {string} button is not disabled")
	public void the_button_is_not_disabled(String text) {
	    manager.global.isButtonEnabledByText(text, true);
	}
	
	@When("the user clicks on the {string} checkbox")
	public void the_user_clicks_on_checkbox_by_text(String checkText) {
	    manager.global.clickCheckboxByText(checkText);
	}
	
	@Then("there is a tool tip icon next to the {string} field")
	public void there_is_a_tool_tip_icon_next_to_the_field(String fieldName) {
	    manager.global.verifyToolTipByFieldName(fieldName);
	}
	
	@When("the user hovers over {string}")
	public void the_user_hovers_over(String text) {
	    manager.global.hoverOnByText(text);
	}
	
	@Then("there is a {string} component as per designs")
	public void there_is_a_component_as_per_designs(String componentText) {
	    manager.global.findComponentByText(componentText);
	}
	
	@Then("there is a {string} checkbox")
	public void there_is_a_checkbox(String componentText) {
	    manager.global.findComponentByText(componentText);
	}
	
	@Then("there is a {string} button that is grey with white text")
	public void there_is_a_button_that_is_grey_with_white_text(String componentText) {
		manager.global.findComponentByText(componentText);
		String xpath = "//*[contains(text(), \'"+componentText+"\')]";
		manager.global.checkCSS(xpath, "rgba(200, 200, 200, 1)", "background-color");
		manager.global.checkCSS(xpath, "rgba(255, 255, 255, 1)", "color");
	}
	
	@When("the user taps anywhere on the screen")
	public void the_user_taps_anywhere_on_the_screen() {
	    manager.global.tapAnywhere();
	}
	
	@Then("the {string} radio button is selected")
	public void the_radio_button_is_selected(String string) {
	    manager.global.isSelectedByText(string, true);
	}
	
	@Then("an active {string} button will display")
	public void an_active_button_will_display(String buttonText) {
	    manager.global.isButtonEnabledByText(buttonText, true);
	}
	
	@Then("the mouse cursor will revert to default")
	public void the_mouse_cursor_will_revert_to_default() {
	    manager.global.checkCSS("//body", "auto", "cursor");
	}
	
	@Then("the Shopping Bag page is loaded")
	public void the_Shopping_Bag_page_is_loaded() {
	    manager.global.checkUrl("Shopping Bag");
	}
	
	@Then("the {string} button turns black")
	public void the_button_turns_black(String element) {
	    manager.global.isActiveByText(element);
	}
	
	@Then("{string} is bold and underlined")
	public void is_bold_and_underlined(String text) {
	    manager.global.checkCSS("//*[text()=\'"+text+"\']", "700", "font-weight");
	    manager.global.checkCSS("//*[text()=\'"+text+"\']", "underline solid rgb(0, 0, 0)", "text-decoration");
	}
	
	@When("the user has not clicked\\/tapped on any element within the mini shopping bag for {int} seconds")
	public void the_user_has_not_clicked_tapped_on_any_element_within_the_mini_shopping_bag_for_seconds(Integer seconds) {
	    manager.global.idleForX(seconds);
	}
	
	@Then("there is a {string} button as per designs")
	public void there_is_a_button_as_per_designs(String string) {
	    manager.global.isDisplayed("//button[text()=\'"+string+"\']", true);
	}
	
	@Then("the {string} button is black with white text")
	public void the_button_is_black_with_white_text(String string) {
	    manager.global.checkCSS("//button[text()=\'"+string+"\']", "rgba(0, 0, 0, 1)", "background-color");
	    manager.global.checkCSS("//button[text()=\'"+string+"\']", "rgba(255, 255, 255, 1)", "color");
	}
	
	@Then("the {string} button is white with black text and black outline")
	public void the_button_is_white_with_black_text_and_black_outline(String string) {
		manager.global.checkCSS("//button[text()=\'"+string+"\']", "rgba(255, 255, 255, 1)", "background-color");
	    manager.global.checkCSS("//button[text()=\'"+string+"\']", "rgba(0, 0, 0, 1)", "color");
	    manager.global.checkCSS("//button[text()=\'"+string+"\']", "rgb(0, 0, 0)", "border-color");
	}
	
	@Then("the {string} button is active")
	public void the_button_is_active(String string) {
	    manager.global.isButtonEnabledByText(string, true);
	}
	
	@Given("I put a cart into local storage")
	public void i_put_a_cart_into_local_storage() {
	    manager.global.navigateTo("homepage");
		manager.global.setCart();
	}
	
	@Given("the user is in {word} locale")
	public void the_user_is_in_US_locale(String locale) {
	    switch(locale) {
	    case "US":
	    	// TODO set to USA
	    	break;
	    case "UK":
	    	// TODO set to UK
	    	break;
	    case "ROW":
	    	// TODO set to ROW
	    	break;
	    }
	}
	
	@Given("the user has entered an invalid value in the field")
	public void the_user_is_in_the_string_field(List<String> info) {
	    manager.global.inputTextByPlaceholder(info.get(0),info.get(1));
	    
	}
	
	@When("the user clicks out of the field")
	public void the_user_clicks_out_of_the_field(String placeholder) {
		manager.global.exitFieldByPlaceholder(placeholder);
	}
	
	@Then("the error message displays as per designs")
	public void the_error_message_displays_as_per_designs(String message) {
	    manager.global.checkForElementByText(message);
	}
	
	@Then("the field is underlined in red")
	public void the_field_is_underlined_in_red(String placeholder) {
	    manager.global.checkCSS("//input[@placeholder=\'"+placeholder+"\']", "rgb(196, 11, 20)", "border-color");
	}
	
}
