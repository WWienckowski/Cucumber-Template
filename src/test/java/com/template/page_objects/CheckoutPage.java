package com.template.page_objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.Helpers;
import com.template.stepdefs.Hooks;

import cucumber.api.Scenario;

public class CheckoutPage {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	
	@FindAll( {  // A list of all fieldsets on the checkout page
		@FindBy(xpath = "//fieldset")
	} ) private List<WebElement> checkoutFieldsets;
	
	@FindBy(className = "form-control_flag-text") private WebElement flagText;
	
	@FindBy(className = "form-control_flag-image") private WebElement flagImage;
	
	@FindAll( { // A list of delivery speed radio buttons
		@FindBy(xpath = "//input[@name=\"deliverySpeed\"]")
	}) private List<WebElement> deliverySpeedRadios;
	
	@FindAll( { // A list of the delivery option selectors
		@FindBy(className = "checkout_delivery-option")
	}) private List<WebElement> deliveryOptions;
	
	@FindBy(className = "simple-header_logo") private WebElement headerLogo;
	
	@FindBy(xpath = "//div[@class='wrapper background-to-white']") private WebElement header;
	
	@FindBy(xpath= "//pink-guest-checkout-bar") private WebElement loginBanner;
	
	@FindBy(className = "checkout-shopping-bag") private WebElement shoppingBag;
	
	@FindBy(linkText = "login") private WebElement loginLink;
	
	@FindBy(xpath = "//div[@class='wrapper alternate-layout']") private WebElement checkoutFooter;
	
	@FindBy(className = "checkout-shopping-bag_button") private WebElement bagButton;
	
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
	
	@FindBy(tagName = "pink-checkout-shopping-bag") private WebElement bag;
	
	public CheckoutPage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
		 PageFactory.initElements(driver, this);
		 }
	
	public void checkFields(List<String> fieldNames, int fieldsetIndex) {
		WebElement fieldset = checkoutFieldsets.get(fieldsetIndex);
		Boolean failed = false;
		Hooks.manager.global.includeScreenshotOfElement(fieldset);
		if (fieldsetIndex==3) {
			List<WebElement> fields = fieldset.findElements(By.xpath(".//input[not(@type='checkbox')] | "
					+ ".//select"));
			if (fields.size() != fieldNames.size()) {
				scenario.write(fieldNames.size()+" fields expected, "+fields.size()+" fields found");
				failed = true;
			}
		} 
		
		Hooks.manager.global.checkInputFieldPlaceholders(fieldNames, fieldset);
		if (failed==true) { 
			Assert.fail("Too many fields found for this section");
		}
	}

	public void locationIs(String expectedLocation) {
		String actualLocation = flagText.getText();
		scenario.write(expectedLocation+" is expected");
		if (expectedLocation.matches(actualLocation)) {
			scenario.write("Location Confirmed");
		} else {
			scenario.write(actualLocation+" was found");
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
		WebElement titleSelect = driver.findElement(By.xpath("//select"));
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
					Hooks.manager.global.includeScreenshotOfElement(radio);
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
		Hooks.manager.global.includeScreenshotOfElement(headerLogo);
		wait.until(ExpectedConditions.elementToBeClickable(headerLogo)).click();
		scenario.write("Logo Successfully Clicked");
		
	}

	public void verifyHeader() {
		Hooks.manager.global.includeScreenshotOfElement(header);	
	}

	public void fillOutRequiredFields(String deliveryType) {
		// TODO fill out the form when delivery type changes the available fields and the form requires info
		scenario.write("This part cannot currently be completed");
	}

	public void verifySummary() {
		WebElement summary = driver.findElement(By.className("checkout-preview_details"));
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
		Hooks.manager.global.includeScreenshot();
	}

	public void checkSummaryFields(List<String> fieldNames) {
		WebElement summary = driver.findElement(By.className("checkout-preview_details"));
		Hooks.manager.global.includeScreenshotOfElement(summary);
		
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
		WebElement deliverySection = driver.findElement(By.className("checkout-fulfilment_section"));
		try {
			WebElement textInput = deliverySection.findElement(By.xpath(".//input[@type='text']"));
			textInput.sendKeys("Test");
			Hooks.manager.global.includeScreenshotOfElement(checkoutFieldsets.get(0));
			scenario.write("Successfully amended delivery info");
		} catch (Exception e) {
			Assert.fail("Unable to amend delivery fields");
		}
	}

	public void bagControlIsOpen(Boolean open) {
		String state = open==false ? "closed" : "open";
		scenario.write("Expecting Shopping Bag Summary to be "+state);
		Hooks.manager.global.idleForX(500);
		Dimension bagSize = bag.getSize();
		//(280, 483)(280, 83)
		System.out.print(bagSize);
		boolean actual = bagSize.getHeight() == 483 ? true : false ;
		if (actual!=open) {
			Assert.fail("Shopping Bag Summary was not "+state);
		}
		scenario.write("Shopping Bag Summary was "+state);
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
		Hooks.manager.global.includeScreenshot();
	}

	public void loginAboveShoppingBag() {
		int loginLocation = loginBanner.getLocation().getY();
		int bagLocation = shoppingBag.getLocation().getY();
		Assert.assertTrue(loginLocation<bagLocation);
		scenario.write("Login bar (Y: "+loginLocation+") is above the Shopping bag (Y: "+bagLocation+")");
		Hooks.manager.global.includeScreenshot();
	}

	public void cursorVerifyLogin() {
		Hooks.manager.global.checkCursor("pointer", loginLink);
		
	}
	
	public void cursorVerifyHelpLine() {
		WebElement helpLine = helpLines.get(1);
		WebElement helpLineNumber = helpLine.findElement(By.xpath("./*[2]"));
		Hooks.manager.global.checkCursor("pointer", helpLineNumber);
	}

	public void checkReviewFields(List<String> fieldNames, String xpath) {
		scenario.write("Expected fields: "+fieldNames);
		List<String> headerTexts = new ArrayList<String>();
		List<WebElement> headers = driver.findElements(By.xpath(xpath));
		for (WebElement header : headers) {
			headerTexts.add(header.getText());
		}
		scenario.write("Actual fields: "+headerTexts);
		Assert.assertEquals(fieldNames, headerTexts);
	}

	public void footerConfirm() {
		wait.until(ExpectedConditions.visibilityOf(checkoutFooter));
		scenario.write("Alternate footer is visible");
		Hooks.manager.global.scrollToElement(checkoutFooter);
		Hooks.manager.global.includeScreenshot();
	}

	public void footerIconConfirm() {
		List<WebElement> icons = driver.findElements(By.xpath("//pink-footer//a"));
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
		
		WebElement total = driver.findElement(By.xpath("//div[*[contains(text(), \'"+section+"\')]]//div[@class='detail total']"));

		Assert.assertEquals(message, helpMessage.getText());
		scenario.write("Message matches acceptance criteria");
		Assert.assertEquals(phone, helpNumber.getText());
		scenario.write("Phone number matches acceptance criteria");
		
		if (Hooks.manager.global.isXaboveY(total, helpLine)==false) {
			Assert.fail("Helpline was not below the "+section);
		}
		scenario.write("Helpline was below "+section);
	}
	
	public void checkHelpLineLocation () {
		WebElement helpLine = helpLines.get(1);
		WebElement total = driver.findElement(By.xpath("//div[*[contains(text(), 'Order Summary')]]//div[@class='detail total']"));
		if (Hooks.manager.global.isXaboveY(total, helpLine)==false) {
			Assert.fail("Helpline was not below the Order Summary");
		}
		scenario.write("Helpline is still below the Order Summary");
		Hooks.manager.global.includeScreenshot();
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
		WebElement rightRail = driver.findElement(By.className("checkout_right-rail"));
		WebElement shoppingBag = rightRail.findElement(By.xpath(".//pink-checkout-shopping-bag"));
		WebElement orderSummary = rightRail.findElement(By.xpath(".//div[@class='checkout_order-summary']"));
		scenario.write("Order Summary found on the right side.");
		if (Hooks.manager.global.isXaboveY(shoppingBag, orderSummary)==false) {
			Assert.fail("Shopping Bag is not above Order Summary");
		}
		scenario.write("Shopping Bag is above the Order Summary");
		Hooks.manager.global.includeScreenshot();
	}

	public void mobileOrderSummaryLocation() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("checkout-shopping-bag_order-summary")));
		WebElement orderSummary = driver.findElement(By.className("checkout-shopping-bag_order-summary"));
		WebElement editLink = driver.findElement(By.className("checkout-shopping-bag_edit-button"));
		if (Hooks.manager.global.isXaboveY(orderSummary, editLink)==false) {
			Assert.fail("Order Summary is not above the Edit Link");
		}
		scenario.write("Order Summary is above the Edit Link");
		Hooks.manager.global.includeScreenshot();
	}

	public void enterPaymentSection() {
		WebElement edit = driver.findElement(By.xpath("//*[contains(text(), 'Payment')]/a[text()='Edit']"));
		Hooks.manager.global.idleForX(100);
		Hooks.manager.global.scrollToElement(edit);
		Hooks.manager.global.javascriptClick(edit);
		Hooks.manager.global.scrollToElement(edit);
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
		Hooks.manager.global.checkInputFieldPlaceholders(fieldNames, fieldset);
		
	}

	public void billingAddressFieldsDissapear() {
		List<WebElement> inputFields = driver.findElements(By.xpath("//pink-payment-options-form//input"));
		if (inputFields.size()>5) {
			Assert.fail("Billing Address fields are still visible");
		}
		scenario.write("Billing Address fields are no longer visible");
		Hooks.manager.global.includeScreenshotOfElement(checkoutFieldsets.get(1));
	}

	public void noPayPalFields() {
		if (checkoutFieldsets.size()>1) {
			Assert.fail("Too many fieldsets found");
		}
		scenario.write("No payment fields displayed");
	}

	public void noPayPalReview() {
		List<WebElement> previews = driver.findElements(By.xpath("//pink-checkout-preview"));
		if (previews.size()>1) {
			Assert.fail("Review section is still visible, or a new preview element has been added.");
		}
		scenario.write("Review section is not displayed");
	}

	public void buttonCheck(boolean active) {
		List<WebElement> buttons = driver.findElements(By.tagName("button"));
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
				? driver.findElement(By.xpath("//button[not(@disabled)]")) :
					driver.findElement(By.xpath("//button[@disabled]"));
		Helpers.HoverOn(CTA, driver);
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
		WebElement editLink = driver.findElement(By.xpath("//pink-collect-in-store-pickup//*[text()='Edit']"));
		Hooks.manager.global.javascriptClick(editLink);
	}

	public void clickBagButton() {
		wait.until(ExpectedConditions.elementToBeClickable(bagButton));
		Hooks.manager.global.javascriptClick(bagButton);
	}
	
}
