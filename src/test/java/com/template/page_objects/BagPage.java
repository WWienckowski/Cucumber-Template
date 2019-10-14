package com.template.page_objects;

import java.sql.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverFactory;
import helpers.Click;
import helpers.Input;
import helpers.Move;
import helpers.Screenshot;

public class BagPage {
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector") private WebElement quantitySelect;
	
	@FindBy(xpath = "//pink-shopping-bag-item//div[@class='quantity_counter']/span") private WebElement quantityNo;
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector//a[span[text()='Add one more']]") private WebElement plusItem;
	
	@FindBy(xpath = "//pink-shopping-bag-item//pink-quantity-selector//a[span[text()='Remove one more']]") private WebElement minusItem;
	
	@FindBy(className = "detail subtotal") private WebElement subtotal;
	
	@FindBy(className = "detail tax") private WebElement tax;
	
	@FindBy(className = "detail total") private WebElement total;
	
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
		Input.getConsole();
		String number = quantityNo.getText();
		switch (quantity) {
    	case "increased":
    		Assert.assertEquals("3", number);
    		break;
    	case "decreased":
    		Assert.assertEquals("1", number);
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
}
