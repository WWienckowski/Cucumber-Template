package com.template.page_objects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
WebDriver driver;
	
	public HomePage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver, this);
		 }
	
	@FindBy(xpath = "/html/body/app-root/pink-home-layout/pink-banner-home/div/div/h1")WebElement title;
	
	public void navigateTo_HomePage() {
		driver.get("https://pink-develop.s3.us-east-2.amazonaws.com/index.html");
		}
	
	public void verifyTitle() {
		String expectedText = "THE PINK SHIRT";
		String actualText = title.getText();
		Assert.assertTrue("Title missing or incorrect",expectedText.equals(actualText));	
		
		
	}

}
