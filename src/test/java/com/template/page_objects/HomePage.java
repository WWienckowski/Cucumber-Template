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

public class HomePage {
WebDriver driver;
WebDriverWait wait;
	
	public HomePage(WebDriver driver, WebDriverWait wait) {
		 this.driver = driver;
		 this.wait = wait;
		 PageFactory.initElements(driver, this);
		 }
	
	@FindBy(xpath = "/html/body/app-root/pink-home-layout/pink-banner-home/div/div/h1")WebElement title;
	
	@FindBys( {
		@FindBy(className = "category-link"),
		@FindBy(xpath = "//a[text()=\"SHIRTS\"]")
	} )
	List<WebElement> categoryLinks;
	
	public void navigateTo_HomePage() {
		driver.get("https://pink-develop.s3.us-east-2.amazonaws.com/index.html");
		}
	
	public void verifyTitle() {
		wait.until(ExpectedConditions.visibilityOf(title));
		String expectedText = "THE PINK SHIRT";
		String actualText = title.getText();
		Assert.assertTrue("Title missing or incorrect",expectedText.equals(actualText));		
	}

	public void clickShirtsLink() {
		wait.until(ExpectedConditions.visibilityOfAllElements(categoryLinks));
		categoryLinks.get(0).click();
	}
}
