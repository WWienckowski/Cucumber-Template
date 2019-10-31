package com.template.page_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
import helpers.Screenshot;
import helpers.Verify;
import io.cucumber.core.api.Scenario;

public class BagPage {
	private Scenario scenario = DriverFactory.getScenario();
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector") 
	private WebElement quantitySelect;
	
	@FindBy(xpath = "//pink-shopping-bag-item//div[@class='quantity_counter']/span") 
	private WebElement quantityNo;
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector//a[span[text()='Add one more']]") 
	private WebElement plusItem;
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector//a[span[text()='Remove one more']]") 
	private WebElement minusItem;
	
	@FindBy(xpath = "//*[contains(@class, 'detail subtotal')]") 
	private WebElement subtotal;
	
	@FindBy(xpath = "//*[contains(@class, 'detail tax')]") 
	private WebElement tax;
	
	@FindBy(xpath = "//*[contains(@class, 'detail total')]") 
	private WebElement total;
	
	@FindAll({
		@FindBy(tagName = "pink-shopping-bag-item") 
	}) private List<WebElement> bagItems;
	
	@FindAll({
		@FindBy(className = "remove-item") 
	}) private List<WebElement> removeItemLinks;
	
	@FindAll({
		@FindBy(className = "add-gift-wrap") 
	}) private List<WebElement> giftWrapCheckboxes;
	
	@FindAll({
		@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector") 
	}) private List<WebElement> quantitySelectors;
	
	@FindBy(xpath = "//pink-shopping-bag-item//textarea") 
	private WebElement giftMessageTextArea;
	
	@FindBy(xpath = "//pink-shopping-bag-item//div[@class='character-count']") 
	private WebElement giftMessageCharCount;
	
	@FindBy(xpath = "//div[@class='actions']//input[@type='checkbox']") 
	private WebElement giftMessageCheckbox;
	
	public BagPage() {
		
		 PageFactory.initElements(DriverFactory.getDriver(), this);
		 }
	
	public void clickQuantitySelect(String quantity) {
		switch (quantity) {
    	case "increases":
    		Click.javascriptClick(plusItem);
    		break;
    	case "decreases":
    		Click.javascriptClick(minusItem);
    		break;
		}
	}

	public void cartUpdatesQuantity(String quantity) {
		Move.idleForX(600);
		Screenshot.includeScreenshotOfElement(quantitySelect);
		String number = quantityNo.getText();
		switch (quantity) {
    	case "increased":
    		Assert.assertEquals("Unexpected number on quantity selector", "3", number);
    		Assert.assertEquals("Unexpected number in cart", 3, Cart.getItemQuantity(0));
    		break;
    	case "decreased":
    		Assert.assertEquals("Unexpected number on quantity selector", "1", number);
    		Assert.assertEquals("Unexpected number in cart", 1, Cart.getItemQuantity(0));
    		break;
    		}
	}

	public void summaryUpdateQuantity(List<String> fields) {
		System.out.println(fields);
		switch (fields.size()) {
		case 2:
			// check uk fields
		case 3:
			// check us and row?
		}
	}

	public void checkProductElements(List<String> elements) {
		scenario.write(bagItems.size()+" item(s) in Shopping Bag. \nChecking "+elements.get(0));
		Assert.assertEquals(bagItems.size(), quantitySelectors.size());
		scenario.write(quantitySelectors.size()+" found. \nChecking "+elements.get(1));
		Assert.assertEquals(bagItems.size(), removeItemLinks.size());
		scenario.write(removeItemLinks.size()+" found. \nChecking "+elements.get(2));
		Assert.assertEquals(bagItems.size(), giftWrapCheckboxes.size());
		scenario.write(giftWrapCheckboxes.size()+" found. \nAll elements found");
	}

	public int getItemNumber() {
		return bagItems.size();
	}

	public void verifyProductAttributes(Map<String, String> attributes) {
		int errors = 0;
		for (int i=0; i<getItemNumber(); i++) {
			scenario.write("Checking Attributes of item #"+i);
			ArrayList<Map<String, Object>> prodAtts = Cart.getLineItemAttributes(i);
			for (Map.Entry<String, String> attribute : attributes.entrySet()) {
				scenario.write("\nChecking "+attribute.getKey());
				if (attribute.getKey().equals("Price")) {
					String price = Integer.toString(Cart.getLineItemTotalPrice(i));
					errors = verifyPrice(price, i) ? errors : errors+1;
					continue;
				} else if (attribute.getKey().equals("Primary Product Image")) {
					String id = Cart.getAttributeValue(prodAtts, attribute.getValue());
					errors = verifyImage(id, i) ? errors : errors+1;
					continue;
				} else {
					String attName = Cart.getAttributeValue(prodAtts, attribute.getValue());
					errors = verifyAttribute(attribute.getKey(), attName, i) ? errors : errors+1;
					continue;
				}
			}
		}
		if (errors > 0) {
			Assert.fail(errors+" attributes not displayed correctly, scenario failed.");
		}
	}
	
	private boolean verifyAttribute(String key, String attName, int i) {
		Boolean result;
		switch (attName) {
		// If the attribute is not present on the item, make sure the field is not displayed for that item
		case "Not Found":
			int elements = bagItems.get(i).findElements
			(By.xpath(".//*[contains(text(), \'"+key+"\')]")).size();
			String message = elements==0 ? key+" not displayed. \nPASS" : key+" displayed. \nFAIL";
			scenario.write("Item has no "+key+" attribute, "+message);
			result = elements==0;
			break;
		// If a product only comes in one size make sure it's displayed appropriately	
		case "One Size":
		case "One":	
			String displayed = bagItems.get(i).findElement
			(By.xpath("//div[span[contains(text(), \'"+key+"\')]]//span[@class='detail-value']")).getText(); 
			scenario.write("Product only comes in one size, Size displayed = "+displayed);
			message = displayed.equals(attName) ? "PASS" : "FAIL";
			scenario.write(message);
			result = displayed.equals(attName);
			break;	
		default:	
			if (key.equals("Name")) {
				// get the displayed name
				displayed = bagItems.get(i).findElement
				(By.xpath(".//span[@class='product-name']")).getText();
				message = displayed.equals(attName) ? "PASS" : "FAIL";
				scenario.write(key+" = "+attName+", Displayed name = "+displayed);
				scenario.write(message);
				result = displayed.equals(attName);
				break;
			} else if (key.equals("Size") || key.equals("Collar Size")) {
				try {
					displayed = bagItems.get(i).findElement
							(By.xpath("//div[span[contains(text(), \'"+key+"\')]]//select")).getAttribute("value");
					message = displayed.equals(attName) ? "PASS" : "FAIL";
					scenario.write(key+" = "+attName+", Displayed = "+displayed);
					scenario.write(message);
					result = displayed.equals(attName);
					break;
				} catch (NoSuchElementException e) {
					scenario.write("Item has "+key+" attribute but it is not displayed");
					scenario.write("FAIL");
					result = false;
					break;
				}
				
			} else {
				// get the displayed text for the key
				
				try {
					displayed = bagItems.get(i).findElement
							(By.xpath(".//span[contains(text(), \'"+key+"\')]/following-sibling::span")).getText();
					message = displayed.equals(attName) ? "PASS" : "FAIL";
					scenario.write(key+" = "+attName+", Displayed = "+displayed);
					scenario.write(message);
					result = displayed.equals(attName);
					break;
				} catch (NoSuchElementException e) {
					scenario.write("Item has "+key+" attribute but it is not displayed.");
					scenario.write("FAIL");
					result = false;
					break;
				}
			}
		}
		return result;
	}

	private boolean verifyImage(String id, int i) {
		String imgSrc = bagItems.get(i).findElement(By.xpath(".//div[@class='image']/img")).getAttribute("src");
		scenario.write("Product ID = "+id+", Product Image Source = "+imgSrc);
		String message = imgSrc.contains(id) ? "PASS" : "FAIL";
		scenario.write(message);
		return imgSrc.contains(id);
	}

	private boolean verifyPrice(String price, int i) {
		String displayPrice = bagItems.get(i).findElement
				(By.xpath(".//span[@class='product-price current']"))
				.getText().substring(1).replace(".", "").trim();
		scenario.write("Price = "+price+", Displayed price = "+displayPrice);
		String message = price.contains(displayPrice) ? "PASS" : "FAIL";
		scenario.write(message);
		return price.contains(displayPrice);
	}

	public void verifyGiftMessageOpen() {
		new WebDriverWait(DriverFactory.getDriver(), 5).until
		(ExpectedConditions.visibilityOfElementLocated(By.tagName("textarea")));
		Assert.assertTrue("Gift Message textarea is not open.", 
				DriverFactory.getDriver().findElement(By.tagName("textarea")).isDisplayed());
		scenario.write("Gift message textarea is open and displayed.");
	}

	public void verifyGiftWrapCheckboxCursor() {
		Verify.checkCursor("pointer", giftMessageCheckbox);
	}

	public void enterGiftMessage(String message) {
		DriverFactory.getDriver().findElement(By.tagName("textarea")).click();
		Move.idleForX(500);
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys(""+message);
		scenario.write("Entered: "+message);
	}

	public void checkCharacterCount() {
		String message = DriverFactory.getDriver().findElement(By.tagName("textarea")).getAttribute("value");
		scenario.write("Gift message: \'"+message+"\' Length: "+message.length());
		int charCount = Integer.parseInt(giftMessageCharCount.getText().replace("/ 200", "").trim());
		scenario.write("Character Count: "+charCount);
		Assert.assertEquals("Character counter did not match text length", message.length(), charCount);
	}

	public void verifyGiftMessageClosed() {
		new WebDriverWait(DriverFactory.getDriver(), 5).until
		(ExpectedConditions.invisibilityOfElementLocated(By.tagName("textarea")));
		try {
			Assert.assertFalse("Gift Message textarea is open.", 
					DriverFactory.getDriver().findElement(By.tagName("textarea")).isDisplayed());
		} catch (StaleElementReferenceException e) {
			
		}
		scenario.write("Gift message textarea is closed and no longer displayed.");
	}

	public void checkGiftMessagePersists(String message) {
		Move.idleForX(1000);
		Assert.assertEquals("Message has not been saved correctly",
				message, DriverFactory.getDriver().findElement(By.tagName("textarea")).getAttribute("value"));
		scenario.write("Message saved correctly: \'"+message+"\'");
	}

	public String enterCharToGiftMessage() {
		DriverFactory.getDriver().findElement(By.tagName("textarea")).sendKeys("Q");
		return DriverFactory.getDriver().findElement(By.tagName("textarea")).getAttribute("value");
	}

	public void verifyTotal(String totalPrice) {
		Move.idleForX(500);
		Assert.assertTrue("Total was not updated", total.getText().contains(totalPrice));
		scenario.write("Total was updated");
	}

}
