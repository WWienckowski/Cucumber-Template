package helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class Navigate {
	final static String baseUrl = checkEnvironment();
	
	private static String checkEnvironment() {
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
	
	public static void to(String urlSuffix) {
		DriverFactory.getScenario().write("Navigating to: "+baseUrl+urlSuffix);
		DriverFactory.getDriver().get(baseUrl+urlSuffix);
		new WebDriverWait(DriverFactory.getDriver(), 15).until(ExpectedConditions.presenceOfElementLocated
				(By.tagName("header")));
	}
	
	public static void checkUrl(String page) {
		page = page.equalsIgnoreCase("Homepage") ? baseUrl : page;
		page = page.contains("bag") ? "basket/viewbasket" : page ;
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
		Assert.assertTrue(page+" is the expected URL\n"+DriverFactory.getDriver().getCurrentUrl()+" is the actual URL",
				wait.until(ExpectedConditions.urlContains(page)));
		DriverFactory.getScenario().write("Current URL is: "+DriverFactory.getDriver().getCurrentUrl());
	}

	public static void start() {
		DriverFactory.getDriver().get(baseUrl);
		Move.idleForX(5000);
	}
}
