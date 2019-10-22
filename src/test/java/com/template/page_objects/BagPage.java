package com.template.page_objects;

import java.util.List;

import org.junit.Assert;
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
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector") private WebElement quantitySelect;
	
	@FindBy(xpath = "//pink-shopping-bag-item//div[@class='quantity_counter']/span") private WebElement quantityNo;
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector//a[span[text()='Add one more']]") private WebElement plusItem;
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector//a[span[text()='Remove one more']]") private WebElement minusItem;
	
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
}
