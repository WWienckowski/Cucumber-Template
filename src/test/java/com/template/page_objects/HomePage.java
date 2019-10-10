package com.template.page_objects;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.Helpers;

import cucumber.api.Scenario;

public class HomePage {
WebDriver driver;
WebDriverWait wait;
Scenario scenario;

	public HomePage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
		 PageFactory.initElements(driver, this);
		 }
	
	@FindBy(xpath = "/html/body/app-root/pink-home-layout/pink-banner-home/div/div/h1")WebElement title;
	
	@FindBy(xpath = "/html/body/app-root/pink-header/div/nav/pink-header-navigation/div/pink-header-navigation-tray[1]/div/div[1]/a[1]")WebElement shirtsLink;
	
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
	

	public void expandBag() {
		Helpers.HoverOn(bagIcon, driver);
		wait.until(ExpectedConditions.visibilityOf(miniBag));
		scenario.write("Bag is expanded.");
	}

	public void miniBagElements(List<String> elements) {
		int items = bagItems.size();
		scenario.write(items+" items in cart.");
		assertTrue("One or more bag elements are missing.", bagElements.size()/items==9);
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
