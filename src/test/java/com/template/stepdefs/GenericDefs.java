package com.template.stepdefs;

import java.util.List;

import driver.SharedDriver;
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
	
	@Given("there are products in the Shopping Bag")
	public void there_are_products_in_the_Shopping_Bag() {
		String item = "{\"accessToken\":\"pMIPSPvLjZ9n_eiKBxy8sdvmAN8vCUhK\",\"expiresIn\":172800,\"anonymous\":true,\"scope\":\"manage_project:pink-shirtmaker-dev\",\"tokenType\":\"Bearer\",\"tokenGen\":1570568152802,\"bag\":{\"type\":\"Cart\",\"id\":\"74226e53-d661-4aea-baf2-32c830257038\",\"version\":13,\"createdAt\":\"2019-10-08T21:07:33.211Z\",\"lastModifiedAt\":\"2019-10-09T13:01:29.597Z\",\"lastModifiedBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"createdBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"lineItems\":[{\"id\":\"744fabbe-282f-4e48-a263-1fada7906738\",\"productId\":\"01826bbb-4e08-4e87-81cd-011544f02ab2\",\"name\":{\"en-US\":\"Spot Woven Silk Tie\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"spot-woven-silk-tie-70111150\"},\"variant\":{\"id\":1,\"sku\":\"70111150P2L0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":11500,\"fractionDigits\":2},\"id\":\"6a37b701-52d0-4831-91d8-201833663785\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"d75c4ace-01d4-474e-a0ab-fa02189dc775\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111150\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Pink\"},{\"name\":\"SEASON\",\"value\":\"AW18\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111150P2L\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"ties-woven\",\"ties\",\"all-accessories\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Spot Woven Silk Tie\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111150P3W\",\"70111150P2L\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Pink/Navy\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[],\"availability\":{\"channels\":{\"072f015f-cbad-4042-ba3b-52b4a863238b\":{\"isOnStock\":true,\"availableQuantity\":26},\"89de900c-1cac-4d66-913c-31acb3a0ae6d\":{\"isOnStock\":true,\"availableQuantity\":11},\"9e41116c-4636-46fd-924e-4092e60d3915\":{\"isOnStock\":true,\"availableQuantity\":11}}}},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":1,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":1,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"},{\"id\":\"ac50b4fd-2c75-41fa-af87-71f714666224\",\"productId\":\"496af939-8234-4920-b411-127c694d5a4c\",\"name\":{\"en-US\":\"Roberts Stripe Skinny Woven\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"roberts-stripe-skinny-woven-70111125\"},\"variant\":{\"id\":1,\"sku\":\"70111125B2S0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"927e10bc-3105-4fcb-ba97-dc742d00476e\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":7000,\"fractionDigits\":2},\"id\":\"b6141a82-f537-4956-8993-8f224047c534\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111125\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Blue\"},{\"name\":\"SEASON\",\"value\":\"CLAS\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111125B2S\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"business\",\"all-accessories\",\"ties-woven\",\"ties\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Roberts Stripe Skinny Woven\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111125L3R\",\"70111125B2S\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Blue/Sky\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[]},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":5,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":5,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":67500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"}],\"cartState\":\"Active\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":84000,\"fractionDigits\":2},\"country\":\"US\",\"customLineItems\":[],\"discountCodes\":[],\"inventoryMode\":\"None\",\"taxMode\":\"External\",\"taxRoundingMode\":\"HalfEven\",\"taxCalculationMode\":\"LineItemLevel\",\"refusedGifts\":[],\"origin\":\"Customer\",\"itemShippingAddresses\":[]}}";
		Input.setCart(item);
	}
	
	@Given("the cart has 1 item with of quantity 2")
	public void the_cart_has_item_with_of_quantity_and_item_with_quantity_of() {
		String item = "{\"accessToken\":\"QvjkbSTEm3eFjfLIDLVtbZFcNdE6aSCI\",\"expiresIn\":172800,\"anonymous\":true,\"scope\":\"manage_project:pink-shirtmaker-dev\",\"tokenType\":\"Bearer\",\"tokenGen\":1571067337065,\"bag\":{\"type\":\"Cart\",\"id\":\"7057216e-91b5-4b6d-a576-e0b52e9ddbce\",\"version\":53,\"createdAt\":\"2019-10-14T15:37:24.407Z\",\"lastModifiedAt\":\"2019-10-14T19:54:47.095Z\",\"lastModifiedBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"createdBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"lineItems\":[{\"id\":\"7d754059-789c-4532-8b28-fd1233f95d1f\",\"productId\":\"cbf7a119-0215-4af7-a127-d6fcc8e57992\",\"name\":{\"en-US\":\"Block Stripe Woven Tie\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"block-stripe-woven-tie-70111202\"},\"variant\":{\"id\":1,\"sku\":\"70111202L3N0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":14000,\"fractionDigits\":2},\"id\":\"5741bbd7-4f32-4091-83e5-24cd04600512\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":12000,\"fractionDigits\":2},\"id\":\"3dac41d5-948e-44dc-b369-9f0f9be312c5\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":19000,\"fractionDigits\":2},\"id\":\"1f0d71be-3c7c-484a-9af0-a02c90858771\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111202\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Navy\"},{\"name\":\"SEASON\",\"value\":\"AW18\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111202L3N\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"ties-woven\",\"ties\",\"all-accessories\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Block Stripe Woven Tie\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111202L3N\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Navy/Neutral\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1},{\"name\":\"TAX_PRODUCT_CODE\",\"value\":\"PC040134\"}],\"assets\":[],\"availability\":{\"channels\":{\"072f015f-cbad-4042-ba3b-52b4a863238b\":{\"isOnStock\":true,\"availableQuantity\":13},\"89de900c-1cac-4d66-913c-31acb3a0ae6d\":{\"isOnStock\":true,\"availableQuantity\":10},\"9e41116c-4636-46fd-924e-4092e60d3915\":{\"isOnStock\":true,\"availableQuantity\":10}}}},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":19000,\"fractionDigits\":2},\"id\":\"1f0d71be-3c7c-484a-9af0-a02c90858771\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":2,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":2,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":38000,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"}],\"cartState\":\"Active\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":38000,\"fractionDigits\":2},\"country\":\"US\",\"customLineItems\":[],\"discountCodes\":[],\"inventoryMode\":\"None\",\"taxMode\":\"External\",\"taxRoundingMode\":\"HalfEven\",\"taxCalculationMode\":\"LineItemLevel\",\"refusedGifts\":[],\"origin\":\"Customer\",\"itemShippingAddresses\":[]}}";
		Input.setCart(item);
	}
	
	@Given("the user is on the {word} page")
	public void navigate_to_page_url(String urlSuffix) {
		urlSuffix = urlSuffix.equals("PDP") ? "detail" : urlSuffix;
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
	
	
}
