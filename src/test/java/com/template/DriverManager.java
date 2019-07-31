package com.template;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
	public static WebDriver driver;
	
	public static void startDriver(String browser) {
		if (browser.contentEquals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
		    FirefoxOptions firefoxOptions = new FirefoxOptions();
		    firefoxOptions.setHeadless(false);
		    driver = new FirefoxDriver(firefoxOptions);
		    
		} else if (browser.contentEquals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setHeadless(false);
			driver = new ChromeDriver(chromeOptions);
		} else if (browser.contentEquals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
	}
	
}
