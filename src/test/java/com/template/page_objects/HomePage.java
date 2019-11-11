package com.template.page_objects;


import org.openqa.selenium.support.PageFactory;
import driver.DriverFactory;
import io.cucumber.core.api.Scenario;


public class HomePage {
private Scenario scenario;

	public HomePage(Scenario scenario) {
		 this.scenario = DriverFactory.getScenario();
		 PageFactory.initElements(DriverFactory.getDriver(), this);
		 }
	

}
