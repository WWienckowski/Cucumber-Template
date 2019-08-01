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
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static void startDriver(String browser) {
		// Determines if the tests run headless; true = yes, false = no
		boolean headless = false;
		if (browser.contentEquals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
		    FirefoxOptions firefoxOptions = new FirefoxOptions();
		    firefoxOptions.setHeadless(headless);
		    driver = new FirefoxDriver(firefoxOptions);
		    
		} else if (browser.contentEquals("Chrome")) {
			WebDriverManager.chromedriver().version("75.0.3770.90").setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(headless);
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.contentEquals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	    wait = new WebDriverWait(driver, 10);
	}
	
}
