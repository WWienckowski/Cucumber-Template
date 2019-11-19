package com.template.page_objects;

import driver.DriverFactory;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
import io.cucumber.core.api.Scenario;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.util.List;
import java.util.Map;


public class CheckoutPage {
	private WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
	private Scenario scenario = DriverFactory.getScenario();

	public CheckoutPage() {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

	@FindAll( { // A list of the delivery option selectors
		@FindBy(xpath = "//pink-delivery-options-selector//div[@class='checkout_delivery-option']")
	}) private List<WebElement> deliveryOptions;

	@FindBy(xpath = "//button[text()='continue']") private WebElement continueButton;
	
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

	@FindBy(className = "checkout-shopping-bag_button") private WebElement bagButton;

	@FindBy(xpath = "//pink-order-summary//span[@class='subtotal-detail']") private WebElement deskSub;

	@FindBy(xpath = "//div[@class='checkout-shopping-bag_list is-open']//span[@class='subtotal-detail']")
	private WebElement mobileSub;

	@FindBy(xpath = "//input[@name='userLoqateFinder']") private WebElement addressSearch;
	
	@FindAll ({
		@FindBy(xpath = "//div[@class='address-finder_addresses']//button")
	}) private List<WebElement> addressSuggestions;

	@FindBy(xpath = "//a[text()=' enter address manually ']") private WebElement manualAddress;
	
	@FindAll ({
		@FindBy(xpath = "//pink-ship-to-address-form//fieldset")
	})  private List<WebElement> shipToAddressFieldsets;

	@FindAll ({
			@FindBy(xpath = "//div[contains(@class, 'summary')]//span[@class='tax-detail']")
	}) private List<WebElement> taxSummaries;

	@FindBy (xpath = "//pink-contact-form//fieldset") private WebElement contactComponent;

	@FindBy (xpath = "//h2[contains(text(),'Delivery')]//a]") private WebElement deliveryEditLink;

	@FindBy (xpath = "//h2[contains(text(),'Payment')]//a]") private WebElement paymentEditLink;

	@FindBy (xpath = "//h2[contains(text(),'Review')]//a]") private WebElement reviewEditLink;

	public void bagControlIsOpen(Boolean open) {
		Boolean actual;
		String state = !open ? "closed" : "open";
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

	public void enterPaymentSection() {
		enterShippingAddress();
		Click.javascriptClick(continueButton);
		Move.idleForX(1000);
		Move.scrollToTop();
		Move.idleForX(1000);
	}

	public void enterShippingAddress() {
		scenario.write("Entering Ship to Address info");
		// enter SHIPPING ADDRESS
		expandManualAddressEntry();
		Move.idleForX(1500);
		WebElement shippingAddress = shipToAddressFieldsets.get(0);
		List<WebElement> inputs = shippingAddress.findElements(By.xpath(".//input"));
		// select a title
		Select shipTitle = new Select (shippingAddress.findElement(By.xpath(".//select[@id='shippingTitle']")));
		shipTitle.selectByVisibleText("Ms.");
		// enter first name
		inputs.get(0).sendKeys("test");
		// enter last name
		inputs.get(1).sendKeys("test");
		// enter address
		inputs.get(2).sendKeys("123 Test Street");
		// enter city
		inputs.get(4).sendKeys("Testville");
		// select a state
		Select shipState = new Select (shippingAddress.findElement(By.xpath(".//select[@name='userState']")));
		shipState.selectByVisibleText("VA");
		// enter zipcode
		inputs.get(5).sendKeys("23225");
		// enter CONTACT FOR ORDER
		List<WebElement> contactInputs = contactComponent.findElements(By.xpath(".//input"));
		// enter email address
		contactInputs.get(0).sendKeys("Test@test.com");
		// enter mobile number
		contactInputs.get(2).sendKeys("123-456-7890");
	}

	public void enterCollectInStoreInfo () {
		scenario.write("Entering Collect in Pink Store info");
		// select a store
		DriverFactory.getDriver().findElement(By.xpath("//button[text()='Pick-up here']")).click();
		Move.idleForX(1500);
		// enter CONTACT FOR ORDER
		List<WebElement> contactInputs = contactComponent.findElements(By.xpath(".//input"));
		// select a title
		Select userTitle = new Select (contactComponent.findElement(By.xpath(".//select[@id='userTitle']")));
		userTitle.selectByVisibleText("Mr.");
		// enter full name
		contactInputs.get(0).sendKeys("Test Test");
		// enter email address
		contactInputs.get(1).sendKeys("Test@test.com");
		// enter mobile number
		contactInputs.get(3).sendKeys("123-456-7890");
	}

	public void eachStoreHasMap() {
		scenario.write("Stores: "+storeList.size()+" Map links: "+mapLinks.size());
		Assert.assertEquals("Incorrect amount of map links:", storeList.size(), mapLinks.size());
		
	}

	public void eachStoreHasDetails() {
		scenario.write("Stores: "+storeList.size()+" Store details links: "+detailLinks.size());
		Assert.assertEquals("Incorrect amount of detail links:", storeList.size(), detailLinks.size());
		
	}

	public void clickBagButton() {
		wait.until(ExpectedConditions.elementToBeClickable(bagButton));
		Click.javascriptClick(bagButton);
		Move.idleForX(1000);
	}

	public void verifyGiftWrapCheckbox(boolean expected) {
		if (giftWrapCheckbox.size() == 0) {
			scenario.write("Gift wrap checkbox is not displayed");
			if (!expected) {
				scenario.write("Passed");
			} else {
				Assert.fail("Gift wrap checkbox should be displayed");
			}
		} else {
			scenario.write("Gift wrap checkbox is displayed");
			if (expected) {
				scenario.write("Passed");
			} else {
				Assert.fail("Gift wrap checkbox should not be displayed");
			}
		}
		
	}

	public void verifyGiftMessageDisplayed(boolean expected) {
		if (expected) {
			Assert.assertEquals("Unexpected number of textboxes", 1, giftMessageTextbox.size());
			Assert.assertEquals("Incorrect message displayed", "test message", giftMessageTextbox.get(0).getText());
			scenario.write("Gift message is displayed correctly");
		} else {
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
		Assert.assertEquals(errors + " item properties displayed incorrectly", 0, errors);
	}

	public void verifySubtotal(Boolean mobile) {
		String displayedSubtotal = mobile
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
		if (!displayed) {
			Assert.assertEquals("There are address suggestions", 0, suggestions);
		} else {
			Assert.assertNotEquals("There are not address suggestions", 0, suggestions);
		}
		scenario.write(suggestions +" address suggestions displayed");
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
			scenario.write("Ship to Address is not active, clicking Ship to Address");
			Click.javascriptClick(shipToAddress);
		}
	}
	
	public void collectInStoreIsActive() {
		WebElement collectInStore = deliveryOptions.get(1).findElement(By.xpath(".//label"));
		if (collectInStore.getAttribute("class").contains("is-active")) {
			scenario.write("Collect in a Pink Store is active.");
		} else {
			scenario.write("Collect in a Pink Store is not active, clicking Collect in a Pink Store");
			Click.javascriptClick(collectInStore);
		}
	}

	public void expandManualAddressEntry() {
		Click.javascriptClick(manualAddress);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan
				(By.xpath("//pink-ship-to-address-form//fieldset[legend[text()=' Shipping address ']]//input"), 4));
		scenario.write("Ship to address fields expanded");
		Move.idleForX(3000);
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

	public void billingAddressEntry(String field, String entry) {
		Move.idleForX(500);
		DriverFactory.getDriver()
		.findElement(By.xpath("//input[@placeholder=\'"+field+"\']")).sendKeys(entry);
		scenario.write("Entered: "+entry);
	}

	public void billingAddressEntryPersists(String field, String entry) {
		String displayed = DriverFactory.getDriver()
				.findElement(By.xpath("//input[@placeholder=\'"+field+"\']")).getAttribute("value");
		Assert.assertEquals("Entry is incorrect", entry, displayed);
		scenario.write("Entry remains and is correct.");
	}

	public void billingAddressChooseState(String entry) {
		Select state = new Select (DriverFactory.getDriver().findElement(By.xpath
				("//select[@name='userState']")));
		state.selectByVisibleText(entry);
		scenario.write("Selected: "+entry);
	}

	public void billingAddressChooseCounty(String entry) {
		Select county = new Select (DriverFactory.getDriver().findElement(By.xpath
				("//select[@name='userCounty']")));
		county.selectByVisibleText(entry);
		scenario.write("Selected: "+entry);
	}


	public void startWithZeroTax() {
		boolean displayed = false;
		Assert.assertEquals("No tax summary displayed", 2, taxSummaries.size());
		for (WebElement taxSummary : taxSummaries) {
			if (taxSummary.isDisplayed()) {
				displayed = true;
				Assert.assertEquals("Tax summary was not displayed as 0",
						"0", taxSummary.getText().replace("$",""));
				scenario.write("Tax summary initially displays as 0");
			}
		}
		Assert.assertTrue("No tax summary displayed", displayed);
	}

	public void displayTaxInDollars() {
		for (WebElement taxSummary : taxSummaries) {
			if (taxSummary.isDisplayed()) {
				Assert.assertTrue("$ not present on tax summary", taxSummary.getText().contains("$"));
			}
		}
		scenario.write("Tax is displayed in dollars");
	}

	public void checkContactFields(List<String> fields) {
		int errors =0;
		for (String field : fields) {
			scenario.write("Looking for "+field);
			int found = contactComponent.findElements(By.xpath(".//input[@placeholder=\'"+field.trim()+"\']")).size();
			found += contactComponent.findElements(By.xpath(".//select//option[1][text()=\'"+field.trim()+"\']")).size();
			if (found != 1) {
				scenario.write(found+" found... FAIL");
				errors++;
			}
			else scenario.write("Field was found");
		}
		if (errors>0) Assert.fail(errors+" fields displayed incorrectly");
		scenario.write("All fields displayed correctly");
	}

	public void clickToEdit(String section) {
		scenario.write("Clicking the edit link for the "+section+" section");
		switch (section) {
			case "Delivery":
				Click.javascriptClick(deliveryEditLink);
			case "Payment":
				Click.javascriptClick(paymentEditLink);
			case "Review":
				Click.javascriptClick(reviewEditLink);
		}
	}

	public void changeContactEntries() {
		// TODO edit the contact info and make sure it is changed
	}
}
