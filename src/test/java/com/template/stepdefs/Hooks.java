package com.template.stepdefs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.DriverManager;
import com.template.Helpers;
import com.template.PageObjectManager;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Hooks {
	DriverManager driverManager;		
	public static PageObjectManager manager;
	String baseUrl;
	
	public Hooks(DriverManager driverManager)  {
		this.driverManager = driverManager;
	}
	
	  @Before("not @mobile")
	/*
	 * Before any scenario runs this hook takes the scenario's name, passes it to a
	 * helper method that scans it for a browser name, the browser name is sent
	 * to the DriverManager which opens the WebDriver.
	 */
	  public void initialize(Scenario scenario) throws MalformedURLException {
		  String scenarioName = scenario.getName();
		  String browser = Helpers.browserCheck(scenarioName);
		  driverManager.startDriver(browser);
		  baseUrl = driverManager.getBaseUrl();
		  // Create all page objects in the PageObjectManager
		  WebDriver driver = driverManager.getDriver();
		  WebDriverWait wait = driverManager.getWait();
	      manager = new PageObjectManager(driver, wait, scenario, baseUrl);
	  }
	  
	  @Before("@mobile")
	  public void initializeMobile(Scenario scenario) {
		  scenario.write("Simulating mobile browser");
		  driverManager.startMobileDriver();
		  baseUrl = driverManager.getBaseUrl();
		  WebDriver driver = driverManager.getDriver();
		  WebDriverWait wait = driverManager.getWait();
	      manager = new PageObjectManager(driver, wait, scenario, baseUrl);
	  }
	  
	  @After(order=1)
		// If a test fails, document it with a screen shot and then quit the driver
		public void captureScreenOnFail(Scenario scenario){
			if (scenario.isFailed()) {
				BufferedImage screenshot = new AShot()
			            .shootingStrategy(ShootingStrategies.viewportPasting(100))
			            .takeScreenshot(driverManager.getDriver()).getImage();

			File screenshotFile = new File("target/image.png");
			try {
			    ImageIO.write(screenshot, "png", screenshotFile);
			} catch (IOException e) {
			    e.printStackTrace();
			}

		        try {
		        	byte[] screenByte = Files.readAllBytes(screenshotFile.toPath());
		            scenario.embed(screenByte, "image/png");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				

			 
				
			}
	  }
	  @After(order=0)
	  // Quit the driver after a test
	  public void quitDriver(Scenario scenario) {
		  driverManager.teardownDriver();;
	  }
	  
}
