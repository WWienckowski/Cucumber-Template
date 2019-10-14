package com.template.page_objects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import helpers.Move;
import io.cucumber.core.api.Scenario;

public class MiniBagPage {
	Scenario scenario = DriverFactory.getScenario();
	
	@FindBy(className = "icon-cart")WebElement bagIcon;
	
	@FindBy(xpath = "//*[@class='header-bag']")WebElement miniBag;
	
	@FindAll({
		@FindBy(tagName = ("pink-bag-item"))
	}) List<WebElement> bagItems;
	
	@FindAll({
		@FindBy(className =("bag-item_name")),
		@FindBy(className =("bag-item_price")),
		@FindBy(className =("bag-item_image")),
		@FindBy(xpath =("//li[dt[text()='Colour:']]")),
		@FindBy(xpath =("//li[dt[text()='Fit:']]")),
		@FindBy(xpath =("//li[dt[text()='Sleeve:']]")),
		@FindBy(xpath =("//li[dt[text()='Collar:']]")),
		@FindBy(tagName =("pink-quantity-selector")),
		@FindBy(className =("bag-item_remove-link"))
	}) List<WebElement> bagElements;
	
	public MiniBagPage() {
		 PageFactory.initElements(DriverFactory.getDriver(), this);
		 }
	
	public void expandBag() {
		Move.HoverOn(bagIcon);
		new WebDriverWait(DriverFactory.getDriver(), 15).until(ExpectedConditions.visibilityOf(miniBag));
		scenario.write("Bag is expanded.");
	}

	public void miniBagElements(List<String> elements) {
		int items = bagItems.size();
		scenario.write(items+" items in cart.");
		Assert.assertTrue("One or more bag elements are missing.", bagElements.size()/items==9);
		scenario.write("Each bag item has an appropriate element");
	}

	public void bagIsMinimized() {
		if (miniBag.isDisplayed()==true) {
			Assert.fail("Mini bag is not minimized.");
		} else {
			scenario.write("Mini bag is minimized.");
		}
	}
}
