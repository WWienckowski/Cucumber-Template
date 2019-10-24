package com.template.page_objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverFactory;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
import helpers.Screenshot;
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
	
	@FindBy(className = "detail subtotal") private WebElement subtotal;
	
	@FindBy(className = "detail tax") private WebElement tax;
	
	@FindBy(className = "detail total") private WebElement total;
	
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

}
