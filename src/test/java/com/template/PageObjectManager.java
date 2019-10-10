package com.template;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.page_objects.BagPage;
import com.template.page_objects.CheckoutPage;
import com.template.page_objects.ConfirmationPage;
import com.template.page_objects.Global;
import com.template.page_objects.HomePage;
import com.template.page_objects.ProductDetailPage;

import cucumber.api.Scenario;


public class PageObjectManager {
	public Global global;
	public HomePage home;
	public ProductDetailPage detail;
	public CheckoutPage checkout;
	public BagPage bag;
	public ConfirmationPage confirmation;

	// add each page object here and they will be instantiated when the scenario starts
	public PageObjectManager(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		if (global == null) {
			global = new Global(driver, wait, scenario);
		}
		if (home == null) {
			home = new HomePage(driver, wait, scenario);
		}
		if (detail == null) {
			detail = new ProductDetailPage(driver, wait, scenario);
		}
		if (checkout == null) {
			checkout = new CheckoutPage(driver, wait, scenario);
		}
		if (bag == null) {
			bag = new BagPage(driver, wait, scenario);
		}
		if (confirmation == null) {
			confirmation = new ConfirmationPage(driver, wait, scenario);
		}
	}
}
