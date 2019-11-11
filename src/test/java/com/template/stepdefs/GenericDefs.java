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
		Move.idleForX(1000);
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    DriverFactory.getScenario().write(Integer.toString(Cart.getLineItemCount()));
	}
	
	@Given("the user has a tie in the bag")
	public void there_is_a_shirt_and_a_tie_in_the_bag() {
		Navigate.to("product/club-stripe-woven-silk-tie/70111185G2P");
		Move.idleForX(1000);
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(1000);
	    DriverFactory.getScenario().write(Integer.toString(Cart.getLineItemCount()));
	}
	
	@Given("there are products in the Shopping Bag")
	public void there_are_products_in_the_Shopping_Bag() {
		Navigate.to("product/poplin-button-cuff/10500040B1X");
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(2000);
	    DriverFactory.getScenario().write("Line item 0 quantity: "+ Cart.getItemQuantity(0));
	    Navigate.to("product/club-stripe-woven-silk-tie/70111185G2P");
	    Move.idleForX(1000);
		Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(2000);
	    DriverFactory.getScenario().write("Line item 1 quantity: "+ Cart.getItemQuantity(1));
	    DriverFactory.getScenario().write(Cart.getLineItemCount() +" line items in bag");
	}
	
	@Given("the cart has 1 item with of quantity 2")
	public void the_cart_has_item_with_of_quantity_and_item_with_quantity_of() {
		Navigate.to("product/poplin-button-cuff/10500040B1X");
		Move.idleForX(1000);
	    Click.javascriptClickXpath("//*[text()=' Add to shopping Bag ']");
	    Move.idleForX(500);
	    Click.javascriptClickXpath("//a[text()='+']");
	    Move.idleForX(500);
	    DriverFactory.getScenario().write(Cart.getItemQuantity(0) +" of selected item in cart");
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
		urlSuffix = urlSuffix.contentEquals("PLP(shirts)") ? "shirts" : urlSuffix;
		urlSuffix = urlSuffix.contentEquals("PLP(clothing)") ? "clothing" : urlSuffix;
		urlSuffix = urlSuffix.contentEquals("PLP(accessories)") ? "accessories" : urlSuffix;
		urlSuffix = urlSuffix.contentEquals("bag") ? "basket/viewbasket" : urlSuffix;
		urlSuffix = urlSuffix.contentEquals("home") ? "" : urlSuffix;
		Navigate.to(urlSuffix);
		Move.idleForX(1000);
	}
	
	@When("the user clicks on {string}")
	public void user_clicks_on(String element) {
		Click.byText(element);
	}
	
	@When("the user clicks the {string} button")
	public void the_user_clicks_the_button(String text) {
	    Click.javascriptClickXpath("//button[contains(text(),\'"+text+"\')]");
	}

	@When("the user clicks the checkbox for {string}")
	public void the_user_clicks_the_checkbox_for(String checkbox) {
	    Click.byCheckboxText(checkbox);
	}

	@When("the user clicks on/the {string} link")
	public void the_user_clicks_on_link(String linkText) {
	    Click.byLinkText(linkText);
	}
	
	@Then("the user is re-directed to the {string}")
	public void the_user_is_redirected(String page) {
		page = page.contentEquals("Shopping Bag") ? "basket/viewbasket" : page;
		Navigate.checkUrl(page);
	}

	@Given("the user has not left the page")
	public void the_user_has_not_left_the_page() {
	    Move.idleForX(1000);
	}

	@Then("the Shopping Bag page is loaded")
	public void the_Shopping_Bag_page_is_loaded() {
	    Navigate.checkUrl("bag");
	}
	
	@Then("the {string} button turns black")
	public void the_button_turns_black(String element) {
	    Verify.isActiveByText(element);
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
	public void the_error_message_displays_as_per_designs(String text) {
	    Verify.checkForValidationText(text);
	}
	
	@Then("the error messages display as per designs")
	public void the_error_messages_display_as_per_designs(List<String> messages) {
	    Move.idleForX(1000);
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

	@Then("the user is presented with the {string} message")
	public void the_user_is_presented_with_the_message(String message) {
	    Verify.checkForElementByText(message);
	}
	
	@Then("the user is anchored back up to the first error message")
	public void the_user_is_anchored_back_up_to_the_first_error_message(String errorMessage) {
	    Verify.screenAnchorsToErrorMessage(errorMessage);
	}
	
	@Then("a {string} link is present")
	public void a_link_is_present(String linkText) {
	    Verify.linkIsPresent(linkText);
	}
	
	@Then("a {string} link is not present")
	public void a_link_is_not_present(String linkText) {
	    Verify.linkIsAbsent(linkText);
	}
	
}
