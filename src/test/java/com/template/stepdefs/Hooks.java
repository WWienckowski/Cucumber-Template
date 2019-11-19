package com.template.stepdefs;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import helpers.Navigate;
import org.openqa.selenium.Dimension;

import driver.DriverFactory;
import driver.SharedDriver;
import helpers.Input;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Hooks {		
	public Hooks(SharedDriver driver)  {
		
	}
	
	  @Before("not @mobile")
	  public void initialize(Scenario scenario) {
	      DriverFactory.getDriver().manage().window().maximize();
	      DriverFactory.getDriver().manage().deleteAllCookies();
		  DriverFactory.setScenario(scenario);
		  Navigate.to("");
	  }
	  
	  @Before("@mobile")
	  public void initializeMobile(Scenario scenario) {
		  scenario.write("Simulating mobile browser");
		  Dimension mobile = new Dimension(375,812);
		  DriverFactory.getDriver().manage().window().setSize(mobile);
		  DriverFactory.getDriver().manage().deleteAllCookies();
	      DriverFactory.setScenario(scenario);
		  Navigate.to("");
	  }
	  
	  @After(order=1)
		// If a test fails, document it with a screen shot
		public synchronized void captureScreenOnFail(Scenario scenario){
			if (scenario.isFailed()) {
				BufferedImage screenshot = new AShot()
			            .shootingStrategy(ShootingStrategies.viewportPasting(100))
			            .takeScreenshot(DriverFactory.getDriver()).getImage();

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
	  public void clearCookies() {
		  Input.clearLocal();
	  }
	  
}
