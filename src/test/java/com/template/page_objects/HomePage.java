package com.template.page_objects;


import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

public class HomePage {
WebDriver driver;
WebDriverWait wait;
Scenario scenario;
public String homePage = "https://pink-develop.s3.us-east-2.amazonaws.com/index.html";


	
	public HomePage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
		 PageFactory.initElements(driver, this);
		 }
	
	@FindBy(xpath = "/html/body/app-root/pink-home-layout/pink-banner-home/div/div/h1")WebElement title;
	
	@FindBys( {
		@FindBy(className = "category-link"),
		@FindBy(xpath = "//a[text()=\"SHIRTS\"]")
	} )
	List<WebElement> categoryLinks;
	
	public void navigateTo_HomePage() {
		scenario.write("Navigating to: "+homePage+"...");
		driver.get(homePage);
		}
	
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
		wait.until(ExpectedConditions.visibilityOfAllElements(categoryLinks));
		scenario.write("Clicking Shirts link...");
		categoryLinks.get(0).click();
		scenario.write("Shirts link clicked!");
	}
}
