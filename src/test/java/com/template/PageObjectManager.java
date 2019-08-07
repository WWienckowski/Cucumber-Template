package com.template;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.page_objects.HomePage;
import com.template.page_objects.ProductDetailPage;

import cucumber.api.Scenario;


public class PageObjectManager {
	public HomePage home;
	public ProductDetailPage detail;

	// add each page object here and they will be instantiated when the scenario starts
	public PageObjectManager(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		if (home == null) {
			home = new HomePage(driver, wait, scenario);
		}
		if (detail == null) {
			detail = new ProductDetailPage(driver, wait, scenario);
		}
	}
}
