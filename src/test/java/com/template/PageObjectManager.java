package com.template;

import org.openqa.selenium.WebDriver;

import com.template.page_objects.HomePage;


public class PageObjectManager {
	public HomePage home;

	
	public PageObjectManager(WebDriver driver) {
		home = new HomePage(driver);

	}
}
