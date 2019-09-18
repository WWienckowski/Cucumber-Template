package com.template.stepdefs;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.DriverManager;
import com.template.Helpers;
import com.template.PageObjectManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	DriverManager driverManager;		
	public static PageObjectManager manager;
	
	public Hooks(DriverManager driverManager)  {
		this.driverManager = driverManager;
	}
	
	  @Before
	/*
	 * Before any scenario runs this hook takes the scenario's name, passes it to a
	 * helper method that scans it for a browser name, the browser name is sent
	 * to the DriverManager which opens the WebDriver.
	 */
	  public void initialize(Scenario scenario) throws MalformedURLException {
		  String scenarioName = scenario.getName();
		  String browser = Helpers.browserCheck(scenarioName);
		  driverManager.startDriver(browser);
		  // Create all page objects in the PageObjectManager
		  WebDriver driver = driverManager.getDriver();
		  WebDriverWait wait = driverManager.getWait();
	      manager = new PageObjectManager(driver, wait, scenario);
	  }
	  
	  @After(order=1)
		// If a test fails, document it with a screen shot and then quit the driver
		public void captureScreenOnFail(Scenario scenario){
			if (scenario.isFailed()) {
				byte[] screenshot = ((TakesScreenshot) driverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png"); 
			} else {
				scenario.write("Scenario passed, no screenshots taken.");
			}
	  }
	  @After(order=0)
	  // Quit the driver after a test
	  public void quitDriver(Scenario scenario) {
		  driverManager.teardownDriver();;
	  }
	  
}
