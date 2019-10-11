package com.template;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait( ) {
		return wait;
	}
	
	public void startDriver() {
		// Determines if the drivers run headless
		try{
			String selenium = System.getProperty("selenium");
			selenium = selenium==null ? "http://localhost:4444/wd/hub" : selenium; 
			driver = new RemoteWebDriver(new URL(selenium), new ChromeOptions());
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 15);
		} catch(MalformedURLException e){
			System.out.println("Error"+e);
		}
	}
	
	public static String checkEnvironment() {
		String baseUrl;
		String location = System.getProperty("location");
		if (location  == null) {
			baseUrl="http://pink-develop.s3-website.us-east-2.amazonaws.com/";
			System.out.println("No location argument, running tests on DEV environment\n"+baseUrl);
			return baseUrl;
		}
		switch(location) {
		case "DEV":
			baseUrl="http://pink-develop.s3-website.us-east-2.amazonaws.com/";
			System.out.println("Running tests on DEV environment\n"+baseUrl);
			break;
		case "QA":	
			baseUrl="http://pink-qa.s3-website-us-east-1.amazonaws.com/";
			System.out.println("Running tests on QA environment\n"+baseUrl);
			break;
		case "LOCAL":
			baseUrl="http://localhost:4200/";
			System.out.println("Running tests on LOCAL environment\n"+baseUrl);
			break;
		default:
			baseUrl="http://pink-develop.s3-website.us-east-2.amazonaws.com/";
			System.out.println("Unknown location argument, running tests on DEV environment\n"+baseUrl);
			break;
		}
		return baseUrl;
	}

	public void teardownDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void startMobileDriver() {
		try {
		String selenium = System.getProperty("selenium");
		selenium = selenium==null ? "http://localhost:4444/wd/hub" : selenium; 
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone X");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

		driver = new RemoteWebDriver(new URL(selenium), chromeOptions);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    wait = new WebDriverWait(driver, 15);
		} catch(MalformedURLException e){
			System.out.println("Error"+e);
		}
	    
	}

	
}
