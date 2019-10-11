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
		manager.global.idleForX(300);
	}
	
	@Given("there are products in the Shopping Bag")
	public void there_are_products_in_the_Shopping_Bag() {
		String item = "{\"accessToken\":\"pMIPSPvLjZ9n_eiKBxy8sdvmAN8vCUhK\",\"expiresIn\":172800,\"anonymous\":true,\"scope\":\"manage_project:pink-shirtmaker-dev\",\"tokenType\":\"Bearer\",\"tokenGen\":1570568152802,\"bag\":{\"type\":\"Cart\",\"id\":\"74226e53-d661-4aea-baf2-32c830257038\",\"version\":13,\"createdAt\":\"2019-10-08T21:07:33.211Z\",\"lastModifiedAt\":\"2019-10-09T13:01:29.597Z\",\"lastModifiedBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"createdBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"lineItems\":[{\"id\":\"744fabbe-282f-4e48-a263-1fada7906738\",\"productId\":\"01826bbb-4e08-4e87-81cd-011544f02ab2\",\"name\":{\"en-US\":\"Spot Woven Silk Tie\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"spot-woven-silk-tie-70111150\"},\"variant\":{\"id\":1,\"sku\":\"70111150P2L0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":11500,\"fractionDigits\":2},\"id\":\"6a37b701-52d0-4831-91d8-201833663785\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"d75c4ace-01d4-474e-a0ab-fa02189dc775\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111150\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Pink\"},{\"name\":\"SEASON\",\"value\":\"AW18\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111150P2L\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"ties-woven\",\"ties\",\"all-accessories\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Spot Woven Silk Tie\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111150P3W\",\"70111150P2L\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Pink/Navy\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[],\"availability\":{\"channels\":{\"072f015f-cbad-4042-ba3b-52b4a863238b\":{\"isOnStock\":true,\"availableQuantity\":26},\"89de900c-1cac-4d66-913c-31acb3a0ae6d\":{\"isOnStock\":true,\"availableQuantity\":11},\"9e41116c-4636-46fd-924e-4092e60d3915\":{\"isOnStock\":true,\"availableQuantity\":11}}}},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":1,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":1,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"},{\"id\":\"ac50b4fd-2c75-41fa-af87-71f714666224\",\"productId\":\"496af939-8234-4920-b411-127c694d5a4c\",\"name\":{\"en-US\":\"Roberts Stripe Skinny Woven\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"roberts-stripe-skinny-woven-70111125\"},\"variant\":{\"id\":1,\"sku\":\"70111125B2S0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"927e10bc-3105-4fcb-ba97-dc742d00476e\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":7000,\"fractionDigits\":2},\"id\":\"b6141a82-f537-4956-8993-8f224047c534\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111125\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Blue\"},{\"name\":\"SEASON\",\"value\":\"CLAS\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111125B2S\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"business\",\"all-accessories\",\"ties-woven\",\"ties\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Roberts Stripe Skinny Woven\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111125L3R\",\"70111125B2S\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Blue/Sky\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[]},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":5,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":5,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":67500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"}],\"cartState\":\"Active\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":84000,\"fractionDigits\":2},\"country\":\"US\",\"customLineItems\":[],\"discountCodes\":[],\"inventoryMode\":\"None\",\"taxMode\":\"External\",\"taxRoundingMode\":\"HalfEven\",\"taxCalculationMode\":\"LineItemLevel\",\"refusedGifts\":[],\"origin\":\"Customer\",\"itemShippingAddresses\":[]}}";
		manager.global.setCart(item);
	}
	
	@Given("the cart has 1 item with of quantity 2")
	public void the_cart_has_item_with_of_quantity_and_item_with_quantity_of() {
		String item = "{\"accessToken\":\"E1Ms7io3op1CkNxIIEsaL-qnzyehUFOO\",\"expiresIn\":172619,\"anonymous\":true,\"scope\":\"manage_project:pink-shirtmaker-dev\",\"tokenType\":\"Bearer\",\"tokenGen\":1570729307313,\"bag\":{\"type\":\"Cart\",\"id\":\"0013c6ec-245b-4031-92ea-d199bc5a728b\",\"version\":33,\"createdAt\":\"2019-10-10T17:45:24.741Z\",\"lastModifiedAt\":\"2019-10-11T14:08:27.111Z\",\"lastModifiedBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"createdBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"lineItems\":[{\"id\":\"6da7aab1-738a-4321-9816-3d2822f388ad\",\"productId\":\"d8e357b3-a40f-4168-bcd0-9fd836a622a3\",\"name\":{\"en-US\":\"Grenadine Club Stripe Woven\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"grenadine-club-stripe-woven-70111189\"},\"variant\":{\"id\":1,\"sku\":\"70111189D2N0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":16000,\"fractionDigits\":2},\"id\":\"5fc9886a-a69c-4bef-9035-9b929bcf9a31\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"e8f610b4-2b7c-44ee-ad6c-c919cf858834\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":21500,\"fractionDigits\":2},\"id\":\"430054e6-8a80-46f5-841a-4a1b3f6ffc33\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111189\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Brown\"},{\"name\":\"SEASON\",\"value\":\"AW18\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111189D2N\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"ties-woven\",\"all-accessories\",\"ties\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Grenadine Club Stripe Woven\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111189R2O\",\"70111189L3H\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Brown/Neutral\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1},{\"name\":\"TAX_PRODUCT_CODE\",\"value\":\"PC040134\"}],\"assets\":[],\"availability\":{\"channels\":{\"072f015f-cbad-4042-ba3b-52b4a863238b\":{\"isOnStock\":true,\"availableQuantity\":13},\"89de900c-1cac-4d66-913c-31acb3a0ae6d\":{\"isOnStock\":true,\"availableQuantity\":26},\"9e41116c-4636-46fd-924e-4092e60d3915\":{\"isOnStock\":true,\"availableQuantity\":26}}}},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":21500,\"fractionDigits\":2},\"id\":\"430054e6-8a80-46f5-841a-4a1b3f6ffc33\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":2,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":2,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":43000,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"}],\"cartState\":\"Active\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":43000,\"fractionDigits\":2},\"country\":\"US\",\"customLineItems\":[],\"discountCodes\":[],\"inventoryMode\":\"None\",\"taxMode\":\"External\",\"taxRoundingMode\":\"HalfEven\",\"taxCalculationMode\":\"LineItemLevel\",\"refusedGifts\":[],\"origin\":\"Customer\",\"itemShippingAddresses\":[]}}";
		manager.global.setCart(item);
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
	
	@Given("the user has entered a valid value in the field")
	public void the_user_has_entered_a_valid_value_in_the_field(List<String> info) {
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
	
	@Then("the error_messages display as per designs")
	public void the_error_messages_display_as_per_designs(List<String> messages) {
	    for (String message : messages) {
	    	manager.global.checkForElementByText(message);
	    }
	}
	
	@Then("user's entry is displayed in the field")
	public void user_s_entry_is_displayed_in_the_field(List<String> info) {
	    String value = manager.global.getValueFromPlaceHolder(info.get(0));
	    manager.global.inputMatchesValue(info.get(1), value);
	}
	
	@Then("the field is underlined in red")
	public void the_field_is_underlined_in_red(String placeholder) {
	    manager.global.checkCSS("//input[@placeholder=\'"+placeholder+"\']", "rgb(196, 11, 20)", "border-color");
	}
	
	@Then("the fields are underlined in red")
	public void the_fields_are_underlined_in_red(List<String> fields) {
	    for (String field : fields) {
	    	manager.global.checkCSS("//input[@placeholder=\'"+field+"\']", "rgb(196, 11, 20)", "border-color");
	    }
	}

	
}
