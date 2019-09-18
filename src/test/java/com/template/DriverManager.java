package com.template;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebDriverWait getWait( ) {
		return wait;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void startDriver(String browser) {
		// Determines if the drivers run headless
		boolean headless = true;
		if (browser.contentEquals("Firefox") && !(driver instanceof org.openqa.selenium.firefox.FirefoxDriver))
		{
			WebDriverManager.firefoxdriver().setup();
		    FirefoxOptions firefoxOptions = new FirefoxOptions();
		    firefoxOptions.setHeadless(headless);
		    driver = new FirefoxDriver(firefoxOptions);
		    
		} else if (browser.contentEquals("Chrome") && !(driver instanceof org.openqa.selenium.chrome.ChromeDriver)) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(headless);
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.contentEquals("Edge") && !(driver instanceof org.openqa.selenium.edge.EdgeDriver)) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.contentEquals("Mobile")) {
			
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    wait = new WebDriverWait(driver, 5);	    
	}
	
	public void teardownDriver() {
		if (driver != null) {
			driver.quit();
		}
	}
	
}
