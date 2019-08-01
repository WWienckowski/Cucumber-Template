package com.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.page_objects.Details;
import com.template.page_objects.HomePage;


public class PageObjectManager {
	public HomePage home;
	public Details details;

	// add each page object here and they will be instantiated when the scenario starts
	public PageObjectManager(WebDriver driver, WebDriverWait wait) {
		home = new HomePage(driver, wait);
		details = new Details(driver, wait);

	}
}
