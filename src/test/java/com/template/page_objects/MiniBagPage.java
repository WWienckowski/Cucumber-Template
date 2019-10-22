package com.template.page_objects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverFactory;
import helpers.Move;
import helpers.Screenshot;
import helpers.Verify;
import io.cucumber.core.api.Scenario;

public class MiniBagPage {
	Scenario scenario = DriverFactory.getScenario();
	
	@FindBy(xpath = "//*[@class='header-bag is-open']") private WebElement miniBag;
	
	@FindAll({
		@FindBy(tagName = ("pink-bag-item"))
	}) private List<WebElement> bagItems;
	
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
	}) private List<WebElement> bagElements;
	
	@FindBy(xpath = "//button[text()='checkout']") private WebElement checkoutButton;
	
	@FindBy(xpath = "//button[text()='view bag']") private WebElement viewBagButton;
	
	@FindBy(xpath = "//a[span[text()='Shopping Cart']]") private WebElement bagIcon;
	
	public MiniBagPage() {
		 PageFactory.initElements(DriverFactory.getDriver(), this);
		 }
	
	public void hoverOnBagIcon() {
		Move.HoverOn(bagIcon);
	}
	
	public void expandBag() {
		Move.HoverOn(bagIcon);
		Assert.assertTrue(miniBag.isDisplayed());
		scenario.write("Bag is expanded.");
	}

	public void miniBagElements(List<String> elements) {
		int items = bagItems.size();
		scenario.write(items+" items in cart.");
		Assert.assertTrue("One or more bag elements are missing.", bagElements.size()/items==9);
		scenario.write("Each bag item has an appropriate element");
	}

	public void bagIsMinimized() {
		Move.idleForX(1000);
		try {
			boolean displayed = !miniBag.isDisplayed();
			Assert.assertTrue("Mini bag is not minimized.", displayed);
		} catch(NoSuchElementException e) {
			scenario.write("Mini bag is minimized.");
		}
	}

	public void checkMiniBagButtons(String button) {
		switch (button) {
		case "checkout":
			Assert.assertTrue("Checkout button not found", checkoutButton.isDisplayed());
			Assert.assertEquals("Background is not black,",
					"rgba(0, 0, 0, 1)", checkoutButton.getCssValue("background-color"));
			Assert.assertEquals("Text is not white,", 
					"rgba(255, 255, 255, 1)", checkoutButton.getCssValue("color"));
			scenario.write("Checkout button is present, the background is black, and the text is white.");
		case "view bag":
			Assert.assertTrue("View Bag button not found", viewBagButton.isDisplayed());
			Assert.assertEquals("Background is not white,",
					"rgba(255, 255, 255, 1)", viewBagButton.getCssValue("background-color"));
			Assert.assertEquals("Text is not black,", 
					"rgba(0, 0, 0, 1)", viewBagButton.getCssValue("color"));
			Assert.assertEquals("Outline is not black,", 
					"rgb(0, 0, 0)", viewBagButton.getCssValue("border-color"));
			scenario.write("Checkout button is present, the background is white, the outline is black, and the text is black.");
		}
		
	}

	public void scrollInMiniBag() {
		WebElement lastItem = bagItems.get(bagItems.size()-1);
		WebElement firstItem = bagItems.get(0);
		Move.scrollToElement(lastItem);
		Move.idleForX(1000);
		Assert.assertTrue("Mini bag window did not scroll down properly, y location = "+lastItem.getLocation().getY(),
				lastItem.getLocation().getY()<=101);
		scenario.write("Mini bag window has scrolled down");
		Screenshot.includeScreenshot();
		Move.scrollToElement(firstItem);
		Move.idleForX(1000);
		Assert.assertTrue("Mini bag window did not scroll up properly, y location = "+firstItem.getLocation().getY(),
				firstItem.getLocation().getY()<=101);
		scenario.write("Mini bag window has scrolled up");
		Screenshot.includeScreenshot();
	}

	public void scrollMainPage() {
		WebElement lastItem = bagItems.get(2);
		Move.scrollToBottom();
		Move.idleForX(500);
		Assert.assertTrue("Mini bag scrolled with the main page while scrolling down",
				Verify.isXaboveY(checkoutButton, lastItem));
		Move.scrollToTop();
		Move.idleForX(500);
		Assert.assertTrue("Mini bag scrolled with the main page while scrolling up",
				Verify.isXaboveY(checkoutButton, lastItem));
		scenario.write("Main page scrolls independently of Mini bag");
	}

}


