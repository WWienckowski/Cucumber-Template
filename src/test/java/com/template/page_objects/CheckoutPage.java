package com.template.page_objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
import helpers.Screenshot;
import helpers.Verify;
import io.cucumber.core.api.Scenario;


public class CheckoutPage {
	WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
	Scenario scenario = DriverFactory.getScenario();
	
	@FindAll( {  // A list of all fieldsets on the checkout page
		@FindBy(xpath = "//fieldset")
	} ) private List<WebElement> checkoutFieldsets;
	
	@FindBy(className = "form-control_flag-text") private WebElement flagText;
	
	@FindBy(className = "form-control_flag-image") private WebElement flagImage;
	
	@FindAll( { // A list of delivery speed radio buttons
		@FindBy(xpath = "//input[@name=\"deliverySpeed\"]")
	}) private List<WebElement> deliverySpeedRadios;
	
	@FindAll( { // A list of the delivery option selectors
		@FindBy(xpath = "//pink-delivery-options-selector//div[@class='checkout_delivery-option']")
	}) private List<WebElement> deliveryOptions;
	
	@FindBy(className = "simple-header_logo") private WebElement headerLogo;
	
	@FindBy(xpath = "//header[@class='opaque simple']") private WebElement header;
	
	@FindBy(xpath= "//pink-guest-checkout-bar") private WebElement loginBanner;
	
	@FindBy(className = "checkout-shopping-bag") private WebElement shoppingBag;
	
	@FindBy(linkText = "login") private WebElement loginLink;
	
	@FindBy(xpath = "//button[text()='continue']") private WebElement continueButton;
	
	@FindBy(xpath = "//div[@class='wrapper alternate-layout']") private WebElement checkoutFooter;
	
	@FindAll ( {
		@FindBy(className = "help") 
	}) private List<WebElement> helpLines;
	
	@FindAll ( {
		@FindBy(tagName = "pink-collect-in-store-list-item")
	}) private List<WebElement> storeList;
	
	@FindAll ( {
		@FindBy(xpath = "//a[contains(text(), 'See map view ')]")
	}) private List<WebElement> mapLinks;
	
	@FindAll ( {
		@FindBy(xpath = "//a[text()='See store details']")
	}) private List<WebElement> detailLinks;
	
	@FindAll ( {
		@FindBy(xpath = "//input[contains(@name, 'CheckoutItemMessage')]")
	}) private List<WebElement> giftWrapCheckbox;
	
	@FindAll ( {
		@FindBy(xpath = "//div[@class='bag-item_text-area form-control']")
	}) private List<WebElement> giftMessageTextbox;
	
	@FindAll ( {
		@FindBy(tagName = "pink-bag-item")
	}) private List<WebElement> bagItems;
	
	@FindBy(tagName = "pink-checkout-shopping-bag") private WebElement bag;
	
	@FindBy(className = "checkout-shopping-bag_button") private WebElement bagButton;
	
	@FindBy(xpath = "//pink-order-summary//span[@class='subtotal-detail']") private WebElement deskSub;
	
	@FindBy(xpath = "//div[@class='checkout-shopping-bag_list is-open']//span[@class='subtotal-detail']") private WebElement mobileSub;
	
	@FindBy(xpath = "//input[@name='userLoqateFinder']") private WebElement addressSearch;
	
	@FindAll ({
		@FindBy(xpath = "//div[@class='address-finder_addresses']//button")
	}) private List<WebElement> addressSuggestions;
	
	@FindBy(xpath = "//a[text()=' enter address manually ']") private WebElement manualAddress;
	
	@FindAll ({
		@FindBy(xpath = "//pink-ship-to-address-form//fieldset")
	})  private List<WebElement> shipToAddressFieldsets;
	
	public CheckoutPage() {
		 PageFactory.initElements(DriverFactory.getDriver(), this);
		 }
	
	public void checkFields(List<String> fieldNames, int fieldsetIndex) {
		WebElement fieldset = checkoutFieldsets.get(fieldsetIndex);
		Boolean failed = false;
		Screenshot.includeScreenshotOfElement(fieldset);
		if (fieldsetIndex==3) {
			List<WebElement> fields = fieldset.findElements(By.xpath(".//input[not(@type='checkbox')] | "
					+ ".//select"));
			if (fields.size() != fieldNames.size()) {
				scenario.write(fieldNames.size()+" fields expected, "+fields.size()+" fields found");
				failed = true;
			}
		} 
		
		Verify.checkInputFieldPlaceholders(fieldNames, fieldset);
		if (failed==true) { 
			Assert.fail("Too many fields found for this section");
		}
	}

	public void locationIs(String expectedLocation) {
		String actualLocation = flagText.getText();
		//scenario.write(expectedLocation+" is expected");
		if (expectedLocation.matches(actualLocation)) {
			//scenario.write("Location Confirmed");
		} else {
			//scenario.write(actualLocation+" was found");
			Assert.fail("Incorrect Location");
		}
	}

	public void checkFlag(String flag) {
		String ukFlag = "/assets/samples/flag-uk.png";
		String usFlag = "/assets/samples/flag-us.png";
		
		flag = flag.matches("British") ? ukFlag : usFlag;
		
		if (flagImage.getAttribute("src").contains(flag)) {
			scenario.write("Correct Icon");
		} else {
			Assert.fail("Incorrect Icon");
		}
	}
	
	public void checkTitles() {
		WebElement titleSelect = DriverFactory.getDriver().findElement(By.xpath("//select"));
		List<WebElement> userTitles = titleSelect.findElements(By.xpath("./option")); 
		if (userTitles.size()==0) {
			Assert.fail("No title options found");
		} else {
			scenario.write("Displayed titles:");
			for (WebElement title : userTitles) {
				if (title.isDisplayed()==true) {
					scenario.write(title.getText());
				} else {
					scenario.write(title.getText()+" is not displayed.");
				}
			}
		}
	}

	public void selectedRadioCheck(String selection) {
		for (WebElement radio : deliverySpeedRadios) {
			if (radio.isSelected()) {
				String radioText = radio.findElement(By.xpath("following-sibling::span")).getText();
				if (radioText.contains(selection)) {
					scenario.write(radioText+" contains "+selection);
					Screenshot.includeScreenshotOfElement(radio);
				} else {
					scenario.write(radioText+" does not contain "+selection);
					Assert.fail(radioText+" does not contain "+selection);
				}
			}
			
		}
		
		
	}

	public void checkDeliveryOptions(List<String> optionNames) {
		ArrayList<String> displayedNames = new ArrayList<String>();
		for (WebElement option : deliveryOptions) {
			displayedNames.add(option.getText());
		}
		int i;
		int noMatch = 0;
		for (i=0; i<optionNames.size(); i++) {
			scenario.write("\nExpected: "+optionNames.get(i)+"\nDisplaying: "+displayedNames.get(i));
			if (displayedNames.get(i).matches(optionNames.get(i))) {
				scenario.write("MATCH");
			} else {
				scenario.write("DO NOT MATCH");
				noMatch++;
			}
		}
		if (noMatch > 0) {
			Assert.fail("Expected names do not match displayed names");
		}
	}

	public void checkboxVerify() {
		WebElement fieldset = checkoutFieldsets.get(2);
		if (fieldset.findElement(By.xpath(".//input[@type='checkbox']"))!=null); {
			scenario.write("Checkbox found");
		}
		
	}
	
	public void clickCheckoutHeaderLogo() {
		scenario.write("Clicking Checkout Page Header Logo");
		Screenshot.includeScreenshotOfElement(headerLogo);
		wait.until(ExpectedConditions.elementToBeClickable(headerLogo)).click();
		scenario.write("Logo Successfully Clicked");
		
	}

	public void verifyHeader() {
		Screenshot.includeScreenshotOfElement(header);	
	}

	public void fillOutRequiredFields(String deliveryType) {
		// TODO fill out the form when delivery type changes the available fields and the form requires info
		scenario.write("This part cannot currently be completed");
	}

	public void verifySummary() {
		WebElement summary = DriverFactory.getDriver().findElement(By.className("checkout-preview_details"));
		List <WebElement> terms = summary.findElements(By.xpath(".//li/span[@class='checkout-preview_detail-term']"));
		
		scenario.write(Integer.toString(terms.size()));
		for (WebElement term : terms) {
			scenario.write(term.getText());
		}
		
	}
	
	public void verifyDelivery() {
		for (WebElement option : deliveryOptions) {
			Assert.assertTrue(option.isDisplayed());
		}
		scenario.write("Delivery component is displayed");
		Screenshot.includeScreenshot();
	}

	public void checkSummaryFields(List<String> fieldNames) {
		WebElement summary = DriverFactory.getDriver().findElement(By.className("checkout-preview_details"));
		Screenshot.includeScreenshotOfElement(summary);
		
		List <WebElement> terms = summary.findElements(By.xpath(".//li/span[@class='checkout-preview_detail-term']"));
		List<String> termsText = new ArrayList<String>();
		terms.forEach((n) -> termsText.add(n.getText()));
		scenario.write("Expected field names:");
		for (String fieldName : fieldNames) {
			scenario.write(fieldName);
		}
		
		scenario.write("Actual field names:");
		for (WebElement term : terms) {
			scenario.write(term.getText());
		}
		
		Assert.assertTrue(terms.size()==fieldNames.size());
		Assert.assertEquals(fieldNames, termsText);;
	}

	public void verifyInputs() {
		WebElement deliverySection = DriverFactory.getDriver().findElement(By.className("checkout-fulfilment_section"));
		try {
			WebElement textInput = deliverySection.findElement(By.xpath(".//input[@type='text']"));
			textInput.sendKeys("Test");
			Screenshot.includeScreenshotOfElement(checkoutFieldsets.get(0));
			scenario.write("Successfully amended delivery info");
		} catch (Exception e) {
			Assert.fail("Unable to amend delivery fields");
		}
	}

	public void bagControlIsOpen(Boolean open) {
		Boolean actual;
		String state = open==false ? "closed" : "open";
		scenario.write("Expecting Shopping Bag Summary to be "+state);
		try {
			DriverFactory.getDriver().findElement(By.xpath("//*[contains(@class,'is-open')]"));
			actual = true;
		} catch (Exception e) {
			actual = false;
		}
		if (open!=actual) {
			Assert.fail("Shopping Bag Summary was not "+state);
		} else {
			scenario.write("Shopping Bag Summary was "+state);
		}
	}
	
	public void loginLocations() {
		
		scenario.write(Integer.toString(shoppingBag.getLocation().getY()));
		scenario.write(Integer.toString(header.getLocation().getY()));
		scenario.write(Integer.toString(loginBanner.getLocation().getY()));
	}

	public void loginUnderHeader() {
		int loginLocation = loginBanner.getLocation().getY();
		int headerLocation = header.getLocation().getY();
		Assert.assertTrue(loginLocation>headerLocation);
		scenario.write("Login bar (Y: "+loginLocation+") is below the header (Y: "+headerLocation+")");
		Screenshot.includeScreenshot();
	}

	public void loginAboveShoppingBag() {
		int loginLocation = loginBanner.getLocation().getY();
		int bagLocation = shoppingBag.getLocation().getY();
		Assert.assertTrue(loginLocation<bagLocation);
		scenario.write("Login bar (Y: "+loginLocation+") is above the Shopping bag (Y: "+bagLocation+")");
		Screenshot.includeScreenshot();
	}

	public void cursorVerifyLogin() {
		Verify.checkCursor("pointer", loginLink);
		
	}
	
	public void cursorVerifyHelpLine() {
		WebElement helpLine = helpLines.get(1);
		WebElement helpLineNumber = helpLine.findElement(By.xpath("./*[2]"));
		Verify.checkCursor("pointer", helpLineNumber);
	}

	public void checkReviewFields(List<String> fieldNames, String xpath) {
		scenario.write("Expected fields: "+fieldNames);
		List<String> headerTexts = new ArrayList<String>();
		List<WebElement> headers = DriverFactory.getDriver().findElements(By.xpath(xpath));
		for (WebElement header : headers) {
			headerTexts.add(header.getText());
		}
		scenario.write("Actual fields: "+headerTexts);
		Assert.assertEquals(fieldNames, headerTexts);
	}

	public void footerConfirm() {
		wait.until(ExpectedConditions.visibilityOf(checkoutFooter));
		scenario.write("Alternate footer is visible");
		Move.scrollToElement(checkoutFooter);
		Screenshot.includeScreenshot();
	}

	public void footerIconConfirm() {
		List<WebElement> icons = DriverFactory.getDriver().findElements(By.xpath("//pink-footer//a"));
		if (icons.size()!=0) {
			Assert.fail("Clickable icons found.");
		}
		scenario.write("No clickable icons found.");
	}

	public void confirmHelpline(String message, String phone, String section) {
		WebElement helpLine;
		if (section.contentEquals("Review")) {
			helpLine = helpLines.get(0);
		} else {
			helpLine = helpLines.get(1);
		}
		WebElement helpMessage = helpLine.findElement(By.xpath("./*[1]"));
		WebElement helpNumber = helpLine.findElement(By.xpath("./*[2]"));
		
		WebElement total = DriverFactory.getDriver().findElement(By.xpath("//div[*[contains(text(), \'"+section+"\')]]//div[@class='detail total']"));

		Assert.assertEquals(message, helpMessage.getText());
		scenario.write("Message matches acceptance criteria");
		Assert.assertEquals(phone, helpNumber.getText());
		scenario.write("Phone number matches acceptance criteria");
		
		if (Verify.isXaboveY(total, helpLine)==false) {
			Assert.fail("Helpline was not below the "+section);
		}
		scenario.write("Helpline was below "+section);
	}
	
	public void checkHelpLineLocation () {
		WebElement helpLine = helpLines.get(1);
		WebElement total = DriverFactory.getDriver().findElement(By.xpath("//div[*[contains(text(), 'Order Summary')]]//div[@class='detail total']"));
		if (Verify.isXaboveY(total, helpLine)==false) {
			Assert.fail("Helpline was not below the Order Summary");
		}
		scenario.write("Helpline is still below the Order Summary");
		Screenshot.includeScreenshot();
	}

	public void helplineLink(int format) {
		WebElement helpLine = helpLines.get(format);
		WebElement helpNumber = helpLine.findElement(By.xpath("./*[2]"));
		Assert.assertTrue("Helpline number is not a link",helpNumber.getTagName().equals("a"));
		scenario.write("Helpline number is a link");
		Assert.assertTrue("Link is not clickable", helpNumber.getAttribute("href").contains("tel:"));
		scenario.write("Helpline number link is clickable");
	}

	public void orderSummaryLocation() {
		WebElement rightRail = DriverFactory.getDriver().findElement(By.className("checkout_right-rail"));
		WebElement shoppingBag = rightRail.findElement(By.xpath(".//pink-checkout-shopping-bag"));
		WebElement orderSummary = rightRail.findElement(By.xpath(".//div[@class='checkout_order-summary']"));
		scenario.write("Order Summary found on the right side.");
		if (Verify.isXaboveY(shoppingBag, orderSummary)==false) {
			Assert.fail("Shopping Bag is not above Order Summary");
		}
		scenario.write("Shopping Bag is above the Order Summary");
		Screenshot.includeScreenshot();
	}

	public void mobileOrderSummaryLocation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout-shopping-bag_order-summary")));
		WebElement orderSummary = DriverFactory.getDriver().findElement(By.className("checkout-shopping-bag_order-summary"));
		WebElement editLink = DriverFactory.getDriver().findElement(By.className("checkout-shopping-bag_edit-button"));
		if (Verify.isXaboveY(orderSummary, editLink)==false) {
			Assert.fail("Order Summary is not above the Edit Link");
		}
		scenario.write("Order Summary is above the Edit Link");
		Screenshot.includeScreenshot();
	}

	public void enterPaymentSection() {
		enterShippingAddress();
		Click.javascriptClick(continueButton);
		Move.idleForX(1000);
		Move.scrollToTop();
	}

	private void enterShippingAddress() {
		// enter SHIPPING ADDRESS
		expandManualAddressEntry();
		WebElement shippingAddress = shipToAddressFieldsets.get(0);
		List<WebElement> inputs = shippingAddress.findElements(By.xpath(".//input"));
		// select a title
		Select shipTitle = new Select (shippingAddress.findElement(By.xpath(".//select[@id='shippingTitle']")));
		shipTitle.selectByValue("Ms.");
		// select a state
		Select shipState = new Select (shippingAddress.findElement(By.xpath(".//select[@name='userState']")));
		shipState.selectByValue("VA");
		// enter first name
		inputs.get(0).sendKeys("test");
		// enter last name
		inputs.get(1).sendKeys("test");
		// enter address
		inputs.get(2).sendKeys("123 Test Street");
		// enter city
		inputs.get(4).sendKeys("Testville");
		// enter zipcode
		inputs.get(5).sendKeys("23225");
		// enter CONTACT FOR ORDER
		WebElement contactInfo = shipToAddressFieldsets.get(2);
		List<WebElement> contactInputs = contactInfo.findElements(By.xpath(".//input"));
		// select a title
		Select userTitle = new Select (contactInfo.findElement(By.xpath(".//select[@id='userTitle']")));
		userTitle.selectByVisibleText("Ms.");
		// enter full name
		contactInputs.get(0).sendKeys("Test Test");
		// enter email address
		contactInputs.get(1).sendKeys("Test@test.com");
		// enter mobile number
		contactInputs.get(3).sendKeys("123-456-7890");
	}

	public void choosePaymentType(String paymentType) {
		int noMatch = 0;
		WebElement paymentFieldset = checkoutFieldsets.get(0);
		List<WebElement> paymentOptions = paymentFieldset.findElements
				(By.xpath("//fieldset//input[@type='radio']/following-sibling::span[1]"));
		for (WebElement option : paymentOptions) {
			if (option.getText().contentEquals(paymentType)) {
				scenario.write(option.getText()+" selected.");
				option.click();
			} else {
				noMatch++;
				if (noMatch>=3) {
					Assert.fail("No matching payment options found for: \'"+paymentType+"\' "
							+ "\nThis may be due to a difference in text on the site");
					
				}
			}
		}
	}

	public void verifyPaymentAddressFields(List<String> fieldNames) {
		WebElement fieldset = checkoutFieldsets.get(1);
		Verify.checkInputFieldPlaceholders(fieldNames, fieldset);
		
	}

	public void billingAddressFieldsDissapear() {
		List<WebElement> inputFields = DriverFactory.getDriver().findElements(By.xpath("//pink-payment-options-form//input"));
		if (inputFields.size()>5) {
			Assert.fail("Billing Address fields are still visible");
		}
		scenario.write("Billing Address fields are no longer visible");
		Screenshot.includeScreenshotOfElement(checkoutFieldsets.get(1));
	}

	public void noPayPalFields() {
		if (checkoutFieldsets.size()>1) {
			Assert.fail("Too many fieldsets found");
		}
		scenario.write("No payment fields displayed");
	}

	public void noPayPalReview() {
		List<WebElement> previews = DriverFactory.getDriver().findElements(By.xpath("//pink-checkout-preview"));
		if (previews.size()>1) {
			Assert.fail("Review section is still visible, or a new preview element has been added.");
		}
		scenario.write("Review section is not displayed");
	}

	public void buttonCheck(boolean active) {
		List<WebElement> buttons = DriverFactory.getDriver().findElements(By.tagName("button"));
		int index = active==true ? 0 : 1;
		WebElement button = buttons.get(index);
		
		String outline = active==true ? "rgb(0, 0, 0)" : "rgb(216, 216, 216)";
		String background = active==true ? "rgba(0, 0, 0, 1)" : "rgba(200, 200, 200, 1)";
		String color = "rgba(255, 255, 255, 1)";
		List<String> expected = Arrays.asList(outline, background, color);

		List<String> actual = Arrays.asList(button.getCssValue("border-color"),
				button.getCssValue("background-color"),
				button.getCssValue("color"));
		
		Assert.assertEquals("Button colors do not match, the following values are for border-color, background-color, and color \n", expected, actual);
		scenario.write("Button colors match");
		
		
	}

	public void hoverCTA(boolean active) {
		WebElement CTA = active==true 
				? DriverFactory.getDriver().findElement(By.xpath("//button[not(@disabled)]")) :
					DriverFactory.getDriver().findElement(By.xpath("//button[@disabled]"));
		Move.HoverOn(CTA);
	}

	public void eachStoreHasMap() {
		scenario.write("Stores: "+storeList.size()+" Map links: "+mapLinks.size());
		Assert.assertEquals("Incorrect amount of map links:", storeList.size(), mapLinks.size());
		
	}

	public void eachStoreHasDetails() {
		scenario.write("Stores: "+storeList.size()+" Store details links: "+detailLinks.size());
		Assert.assertEquals("Incorrect amount of detail links:", storeList.size(), detailLinks.size());
		
	}
	
	public void PickUpEditClick() {
		WebElement editLink = DriverFactory.getDriver().findElement(By.xpath("//pink-collect-in-store-pickup//*[text()='Edit']"));
		Click.javascriptClick(editLink);
	}

	public void clickBagButton() {
		wait.until(ExpectedConditions.elementToBeClickable(bagButton));
		Click.javascriptClick(bagButton);
		Move.idleForX(1000);
	}

	public void verifyGiftWrapCheckbox(boolean expected) {
		if (giftWrapCheckbox.size() == 0) {
			scenario.write("Gift wrap checkbox is not displayed");
			if (expected == false) {
				return;
			} else {
				Assert.fail("Gift wrap checkbox should be displayed");
			}
		} else if (giftWrapCheckbox.size() > 0) {
			scenario.write("Gift wrap checkbox is displayed");
			if (expected == true) {
				return;
			} else {
				Assert.fail("Gift wrap checkbox should not be displayed");
			}
		}
		
	}

	public void verifyGiftMessageDisplayed(boolean expected) {
		if (expected == true) {
			Assert.assertEquals("Unexpected number of textboxes", 1, giftMessageTextbox.size());
			Assert.assertEquals("Incorrect message displayed", "test message", giftMessageTextbox.get(0).getText());
			scenario.write("Gift message is displayed correctly");
		} else if (expected == false) {
			Assert.assertEquals("Unexpected number of textboxes", 0, giftMessageTextbox.size());
			scenario.write("Gift message is not displayed");
		}
	}

	public void verifySummaryImages() {
		for (int i=0; i<Cart.getLineItemCount(); i++) {
			String id = Cart.getLineItemAttribute(i, "PRODUCT_ID");
			String imgSrc = bagItems.get(i).findElement(By.xpath(".//img[@class='bag-item_image']")).getAttribute("src");
			scenario.write("Product ID = "+id+", Product Image Source = "+imgSrc);
			Assert.assertTrue(imgSrc.contains(id));
			scenario.write("Image is correct");
		}
	}

	public void checkDisplayedAttributes(List<Map<String, String>> attributes) {
		int errors = 0;
		for (int i=0; i<Cart.getLineItemCount(); i++) {
			for (Map<String, String> map : attributes) {
				for (Map.Entry<String, String> attribute : map.entrySet()) {
					String productValue = Cart.getLineItemAttribute(i, attribute.getValue());
					scenario.write(attribute.getKey()+" = "+productValue);
					try {
						String text = bagItems.get(i).findElement
							(By.xpath("//dt[contains(text(), \'"+attribute.getKey()+"\')]/following-sibling::dd")).getText();
						scenario.write("Displayed = "+text);
						if (!text.equals(productValue)) {
							scenario.write("FAILED");
							errors+=1;
						}
					} catch (NoSuchElementException e) {
						scenario.write("Not Displayed");
						if (productValue != null) {
							scenario.write("FAILED");
							errors+=1;
						}
					}
				}
			}
		}
		Assert.assertTrue(errors+" item properties displayed incorrectly", errors==0);
	}

	public void verifySubtotal(Boolean mobile) {
		String displayedSubtotal = mobile==true 
				? mobileSub.getText().replace("$", "") : deskSub.getText().replace("$", "");
		String actualSubtotal = Cart.getSubtotal();
		Assert.assertEquals("Subtotal did not match cart", actualSubtotal, displayedSubtotal);
		scenario.write("Subtotal was correct");
	}

	public void addressSearchEntry(Integer chars) {
		int i = 1;
		while (i<=chars) {
			addressSearch.sendKeys(Integer.toString(i));
			i++;
			Move.idleForX(500);
		}
		scenario.write("Entered: "+addressSearch.getAttribute("value"));
	}

	public void addressSuggestionDisplayed(boolean displayed) {
		Move.idleForX(1000);
		int suggestions = addressSuggestions.size();
		if (displayed==false) {
			Assert.assertEquals("There are address suggestions", 0, suggestions);
		} else if (displayed==true) {
			Assert.assertNotEquals("There are not address suggestions", 0, suggestions);
		}
		scenario.write(Integer.toString(suggestions)+" address suggestions displayed");
	}

	public String selectSuggestedAddress() {
		String addressInfo = addressSuggestions.get(0).getText();
		scenario.write(addressInfo);
		Click.javascriptClick(addressSuggestions.get(0));
		return addressInfo;
	}

	public void selectAddressExpandsFields(List<String> fields) {
		for (String field : fields) {
			scenario.write("looking for "+field);
		}
	}

	public void selectAddressFillsFields(List<String> fields, String address) {
		scenario.write("Selected Address: "+address);
		for (String field : fields) {
			String value =
					DriverFactory.getDriver().findElement
					(By.xpath("//*[@placeholder=\'"+field+"\']")).getAttribute("value");
			scenario.write(value);
			Assert.assertTrue("Displayed address does not match selected address", address.contains(value.toUpperCase()));
			scenario.write(field+" displayed correctly");
		}
	}

	public void shipToAddressIsActive() {
		WebElement shipToAddress = deliveryOptions.get(0).findElement(By.xpath(".//label"));
		if (shipToAddress.getAttribute("class").contains("is-active")) {
			scenario.write("Ship to Address is active.");
		} else {
			scenario.write("Ship to Address is not active");
			Click.javascriptClick(shipToAddress);
		}
	}

	public void expandManualAddressEntry() {
		Click.javascriptClick(manualAddress);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan
				(By.xpath("//pink-ship-to-address-form//fieldset[legend[text()=' Shipping address ']]//input"), 4));
		scenario.write("Ship to address fields expanded");
	}

	public void enterAddressValue(String field, String entry) {
		WebElement addressField = shipToAddressFieldsets.get(0).findElement(By.xpath(".//input[@placeholder=\'"+field+"\']"));
		addressField.sendKeys(entry);
		Assert.assertEquals(entry, addressField.getAttribute("value"));
		scenario.write("Entered: "+entry);
	}

	public void validEntryRemains(String field, String entry) {
		WebElement addressField = shipToAddressFieldsets.get(0).findElement(By.xpath(".//input[@placeholder=\'"+field+"\']"));
		Assert.assertEquals("Entry was incorrect", entry, addressField.getAttribute("value"));
		scenario.write(field+" value: "+entry);
	}

	public void invalidEntryShowsError(String errorMessage) {
		int error = shipToAddressFieldsets.get(0).findElements
				(By.xpath(".//div[contains(text(), \'"+errorMessage+"\' ]")).size();
		Assert.assertTrue("Error message not displayed", error>0);
		scenario.write("Error message displayed.");
	}
	
}
