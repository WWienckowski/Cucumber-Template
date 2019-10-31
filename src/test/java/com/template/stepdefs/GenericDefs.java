package com.template.stepdefs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import driver.DriverFactory;
import driver.SharedDriver;
import helpers.Cart;
import helpers.Click;
import helpers.Input;
import helpers.Move;
import helpers.Navigate;
import helpers.Verify;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class GenericDefs {
	
	
	public GenericDefs(SharedDriver driver) {
		
	}
	@Given("the user does not have an item in their bag")
	public void the_user_does_not_have_an_item_in_their_bag() {
	    Navigate.to("");
	    Move.idleForX(1000);
	}
	
	@Given("the user has a shirt in the bag")
	public void the_user_has_an_item_in_the_bag() {
		Navigate.to("");
		Navigate.to("product/poplin-button-cuff/10500040B1X");
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(500);
	    Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    System.out.println(Cart.getLineItemCount());
	}
	
	@Given("the user has a tie in the bag")
	public void there_is_a_shirt_and_a_tie_in_the_bag() {
		Navigate.to("product/club-stripe-woven-silk-tie/70111185G2P");
		Move.idleForX(1000);
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    System.out.println(Cart.getLineItemCount());
	}
	
	@Given("there are products in the Shopping Bag")
	public void there_are_products_in_the_Shopping_Bag() {
		Navigate.to("product/poplin-button-cuff/10500040B1X");
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    Navigate.to("product/club-stripe-woven-silk-tie/70111185G2P");
	    Move.idleForX(1000);
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	}
	
	@Given("the cart has 1 item with of quantity 2")
	public void the_cart_has_item_with_of_quantity_and_item_with_quantity_of() {
		Navigate.to("product/poplin-button-cuff/10500040B1X");
		Move.idleForX(1000);
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(500);
	    Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(500);
	    Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(500);
	}
	
	@Given("the shirt has been gift wrapped")
	public void the_shirt_has_been_gift_wrapped() {
		Click.javascriptClickXpath("//span[@class='icon-cart']");
		Move.idleForX(1500);
	    Click.javascriptClickXpath("//div[@class='actions']//input[@type='checkbox']");
	    DriverFactory.getScenario().write("The shirt has been gift wrapped");
	    Move.idleForX(1500);
	}

	@Given("the shirt has a gift message")
	public void the_shirt_has_a_gift_message() {
		Click.javascriptClickXpath("//span[@class='icon-cart']");
		Move.idleForX(1500);
	    Click.javascriptClickXpath("//div[@class='actions']//input[@type='checkbox']");
	    Move.idleForX(1000);
	    DriverFactory.getDriver().findElement(By.tagName("textarea")).click();
		Move.idleForX(500);
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("test message");
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys(Keys.TAB);
		DriverFactory.getScenario().write("Entered: \"test message\"");
		Move.idleForX(500);
	}
	
	@Given("the user is on the {word} page")
	public void navigate_to_page_url(String urlSuffix) {
		urlSuffix = urlSuffix.contentEquals("bag") ? "basket/viewbasket" : urlSuffix;
		urlSuffix = urlSuffix.contentEquals("home") ? "" : urlSuffix;
		Navigate.to(urlSuffix);
		Move.idleForX(1000);
	}
	
	@When("the user clicks on {string}")
	public void user_clicks_on(String element) {
		Click.byText(element);
	}
	
	@Given("the checkbox for {string} is not checked")
	public void the_checkbox_for_is_not_checked(String checkbox) {
	    Verify.isSelectedByText(checkbox, false);
	}

	@When("the user clicks the checkbox for {string}")
	public void the_user_clicks_the_checkbox_for(String checkbox) {
	    Click.byCheckboxText(checkbox);
	}

	@Given("the checkbox for {string} is checked")
	public void the_checkbox_for_is_checked(String checkbox) {
		Verify.isSelectedByText(checkbox, true);
	}
	
	@When("the user clicks on {string} image")
	public void the_user_clicks_on_image(String altText) {
	    Click.byAltText(altText);
	}
	
	@When("the user clicks on {string} link")
	public void the_user_clicks_on_link(String linkText) {
	    Click.byLinkText(linkText);
	}
	
	@Then("the user is re-directed to the {string}")
	public void the_user_is_redirected(String page) {
		page = page.contentEquals("Shopping Bag") ? "basket/viewbasket" : page;
		Navigate.checkUrl(page);
	}
	
	@Then("the {string} button is not disabled")
	public void the_button_is_not_disabled(String text) {
	    Verify.isButtonEnabledByText(text, true);
	}
	
	@When("the user clicks on the {string} checkbox")
	public void the_user_clicks_on_checkbox_by_text(String checkText) {
	    Click.byCheckboxText(checkText);
	}
	
	@Then("there is a tool tip icon next to the {string} field")
	public void there_is_a_tool_tip_icon_next_to_the_field(String fieldName) {
	    Verify.verifyToolTipByFieldName(fieldName);
	}
	
	@When("the user hovers over {string}")
	public void the_user_hovers_over(String text) {
	    Move.hoverOnByText(text);
	}
	
	@Then("there is a {string} component as per designs")
	public void there_is_a_component_as_per_designs(String componentText) {
	    Verify.findComponentByText(componentText);
	}
	
	@Then("there is a {string} checkbox")
	public void there_is_a_checkbox(String componentText) {
	    Verify.findComponentByText(componentText);
	}
	
	@Given("the user has not left the page")
	public void the_user_has_not_left_the_page() {
	    Move.idleForX(3000);
	}
	
	@Then("there is a {string} button that is grey with white text")
	public void there_is_a_button_that_is_grey_with_white_text(String componentText) {
		Verify.findComponentByText(componentText);
		String xpath = "//*[contains(text(), \'"+componentText+"\')]";
		Verify.checkCSS(xpath, "rgba(200, 200, 200, 1)", "background-color");
		Verify.checkCSS(xpath, "rgba(255, 255, 255, 1)", "color");
	}
	
	@When("the user taps anywhere on the screen")
	public void the_user_taps_anywhere_on_the_screen() {
	    Click.tapAnywhere();
	}
	
	@Then("the {string} radio button is selected")
	public void the_radio_button_is_selected(String string) {
	    Verify.isSelectedByText(string, true);
	}
	
	@Then("an active {string} button will display")
	public void an_active_button_will_display(String buttonText) {
	    Verify.isButtonEnabledByText(buttonText, true);
	}
	
	@Then("the mouse cursor will revert to default")
	public void the_mouse_cursor_will_revert_to_default() {
	    Verify.checkCSS("//body", "auto", "cursor");
	}
	
	@Then("the Shopping Bag page is loaded")
	public void the_Shopping_Bag_page_is_loaded() {
	    Navigate.checkUrl("bag");
	}
	
	@Then("the {string} button turns black")
	public void the_button_turns_black(String element) {
	    Verify.isActiveByText(element);
	}
	
	@Then("{string} is bold and underlined")
	public void is_bold_and_underlined(String text) {
	    Verify.checkCSS("//*[text()=\'"+text+"\']", "700", "font-weight");
	    Verify.checkCSS("//*[text()=\'"+text+"\']", "underline solid rgb(0, 0, 0)", "text-decoration");
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
	    Input.inputTextByPlaceholder(info.get(0),info.get(1));
	    
	}
	
	@Given("the user has entered a valid value in the field")
	public void the_user_has_entered_a_valid_value_in_the_field(List<String> info) {
		Input.inputTextByPlaceholder(info.get(0),info.get(1));
	}
	
	@When("the user clicks out of the field")
	public void the_user_clicks_out_of_the_field(String placeholder) {
		Input.exitFieldByPlaceholder(placeholder);
	}
	
	@Then("the error message displays as per designs")
	public void the_error_message_displays_as_per_designs(String message) {
	    Verify.findComponentByText(message);
	}
	
	@Then("the error_messages display as per designs")
	public void the_error_messages_display_as_per_designs(List<String> messages) {
	    for (String message : messages) {
	    	Verify.findComponentByText(message);
	    }
	}
	
	@Then("user's entry is displayed in the field")
	public void user_s_entry_is_displayed_in_the_field(List<String> info) {
	    String value = Input.getValueFromPlaceHolder(info.get(0));
	    Verify.inputMatchesValue(info.get(1), value);
	}
	
	@Then("the field is underlined in red")
	public void the_field_is_underlined_in_red(String placeholder) {
	    Verify.checkCSS("//input[@placeholder=\'"+placeholder+"\']", "rgb(196, 11, 20)", "border-color");
	}
	
	@Then("the fields are underlined in red")
	public void the_fields_are_underlined_in_red(List<String> fields) {
	    for (String field : fields) {
	    	Verify.checkCSS("//input[@placeholder=\'"+field+"\']", "rgb(196, 11, 20)", "border-color");
	    }
	}
	
	@When("I make a cart")
	public void i_make_a_cart() {
	    Move.idleForX(3000);
	    String token = Input.getPinkShopper().substring(16, 48);
	    System.out.println(token);
		Cart.makeCart(token);
	}
	

	@Then("The pink-shopper should have a cart")
	public void the_pink_shopper_should_have_a_cart() {
	    System.out.println(Input.getPinkShopper());
	}
	
	@Then("I can add items to the cart")
	public void i_can_add_items_to_the_cart() {
	    Navigate.to("product/poplin-button-cuff/10500040B1X");
	    Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    DriverFactory.getScenario().write(Input.getPinkShopper());
	}
	
	@Given("the user goes to a product page")
	public void the_user_goes_to_a_product_page() {
		Navigate.to("product/poplin-button-cuff/10500040B1X");
		Move.idleForX(1000);
	}

	@Given("adds an item to the card")
	public void adds_an_item_to_the_card() {
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	}

	@Then("there will be an item in the cart")
	public void there_will_be_an_item_in_the_cart() {
		DriverFactory.getScenario().write(Input.getPinkShopper());
	}
	
	@Then("the user is presented with the {string} message")
	public void the_user_is_presented_with_the_Your_Shopping_Bag_is_empty_message(String message) {
	    Verify.checkForElementByText(message);
	}
	
}
