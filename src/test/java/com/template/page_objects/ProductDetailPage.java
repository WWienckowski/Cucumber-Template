package com.template.page_objects;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

public class ProductDetailPage {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
		
		public ProductDetailPage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
			 this.driver = driver;
			 this.wait = wait;
			 this.scenario = scenario;
			 PageFactory.initElements(driver, this);
			 }
		
		public void confirmUrl(String url) {
		    try {
		    	wait.until(ExpectedConditions.urlContains(url));
		    } catch (Exception e) {
		    	Assert.fail("URL does not match \""+url+"\".");
		    }
		}
}
