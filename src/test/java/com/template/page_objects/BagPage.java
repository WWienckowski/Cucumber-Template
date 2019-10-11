package com.template.page_objects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.stepdefs.Hooks;

import cucumber.api.Scenario;


public class BagPage {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	
	@FindBy(tagName = "pink-quantity-selector") private WebElement quantitySelect;
	
	@FindBy(className = "detail subtotal") private WebElement subtotal;
	
	@FindBy(className = "detail tax") private WebElement tax;
	
	@FindBy(className = "detail total") private WebElement total;
	
	public BagPage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
		 PageFactory.initElements(driver, this);
		 }

	public void clickQuantitySelect(String quantity) {
		switch (quantity) {
    	case "increases":
    		Hooks.manager.global.javascriptClick
    		(quantitySelect.findElement(By.xpath(".//span[text()='Add one more']")));
    		break;
    	case "decreases":
    		Hooks.manager.global.javascriptClick
    		(quantitySelect.findElement(By.xpath(".//span[text()='Remove one more']")));
    		break;
		}
		System.out.println(quantitySelect.findElement
				(By.xpath(".//div[@class='quantity_counter']/span")).getText()+" quantity");
	}

	public void cartUpdatesQuantity(String quantity) {
		String number = quantitySelect.findElement
	    		(By.xpath(".//div[@class='quantity_counter']/span")).getText();
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
