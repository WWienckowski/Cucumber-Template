package com.template;

import org.openqa.selenium.WebDriver;

import com.template.page_objects.HomePage;


public class PageObjectManager {
	public HomePage home;

	// add each page object here and they will be instantiated when the scenario starts
	public PageObjectManager(WebDriver driver) {
		home = new HomePage(driver);

	}
}
