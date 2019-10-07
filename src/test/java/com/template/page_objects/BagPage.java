package com.template.page_objects;

import org.openqa.selenium.support.PageFactory;
import driver.DriverFactory;
//import io.cucumber.core.api.Scenario;


public class BagPage {
	//private Scenario scenario = DriverFactory.getScenario();
	
	public BagPage() {
		
		 PageFactory.initElements(DriverFactory.getDriver(), this);
		 }
}
