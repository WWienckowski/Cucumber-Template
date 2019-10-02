package com.template;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait( ) {
		return wait;
	}
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public void startDriver(String browser) {
		// Determines if the drivers run headless
		try{
			String selenium = System.getProperty("selenium");
			if (browser.contentEquals("Firefox")) {
				driver = new RemoteWebDriver(new URL(selenium),DesiredCapabilities.firefox());

			} else if (browser.contentEquals("Chrome")) {
				//ChromeOptions options = new ChromeOptions();
				//options.addArguments("--headless");
				//options.addArguments("--whitelisted-ips");
				//options.addArguments("--no-sandbox");
				//options.addArguments("--disable-extensions");
				driver = new RemoteWebDriver(new URL(selenium),DesiredCapabilities.chrome());

			} else if (browser.contentEquals("Edge")) {
				driver = new RemoteWebDriver(new URL(selenium),DesiredCapabilities.edge());
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, 10);
		} catch(MalformedURLException e){
			System.out.println("Error"+e);
		}
	}
	
	private void checkEnvironment() {
		try {
			String location = System.getProperty("location");
			if (location.contentEquals("DEV")) {
				this.setBaseUrl("http://pink-develop.s3-website.us-east-2.amazonaws.com/");
				System.out.println("Running tests on DEV environment\n"+baseUrl);
			} else if (location.contentEquals("QA")) {
				this.setBaseUrl("http://pink-qa.s3-website-us-east-1.amazonaws.com/");
				System.out.println("Running tests on QA environment\n"+baseUrl);;
			} else if (location.contentEquals("LOCAL")) {
				this.setBaseUrl("http://localhost:4200/");
				System.out.println("Running tests on LOCAL environment\n"+baseUrl);
			}
		} catch (NullPointerException e) {
			this.setBaseUrl("http://pink-develop.s3-website.us-east-2.amazonaws.com/");
			System.out.println("No location, running tests on DEV environment\n"+baseUrl);
		}
	}

	public void teardownDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public void startMobileDriver() {
		this.checkEnvironment();
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone 6/7/8");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setHeadless(false);
		chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    wait = new WebDriverWait(driver, 5);
	    
	}

	
}
