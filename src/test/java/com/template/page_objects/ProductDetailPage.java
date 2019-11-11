package com.template.page_objects;

import driver.DriverFactory;
import io.cucumber.core.api.Scenario;
import org.openqa.selenium.support.PageFactory;


public class ProductDetailPage {

	public ProductDetailPage(Scenario scenario) {
		PageFactory.initElements(DriverFactory.getDriver(), this);
	}

}
