package com.template.page_objects;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.core.api.Scenario;

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
	
	public void verifyTitle() {
		wait.until(ExpectedConditions.visibilityOf(title));
		String expectedText = "THE PINK SHIRT";
		String actualText = title.getText();
		scenario.write("Comparing "+expectedText+" to "+actualText+"...");
		Assert.assertTrue("Title missing or incorrect",expectedText.equals(actualText));
		scenario.write("They match!");
	}

	public void clickShirtsLink() {
		scenario.write("Waiting for Shirts link...");
		wait.until(ExpectedConditions.elementToBeClickable(shirtsLink));
		scenario.write("Clicking Shirts link...");
		shirtsLink.click();
		scenario.write("Shirts link clicked!");
	}
}
