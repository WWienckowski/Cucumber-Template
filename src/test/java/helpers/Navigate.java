package helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

import java.sql.Driver;

public class Navigate {
	private final static String baseUrl = checkEnvironment();
	
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
		case "CI":
			baseUrl="http://pink-test.s3-website-us-east-1.amazonaws.com/";
			System.out.println("Running tests on CI environment\n"+baseUrl);
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
		new WebDriverWait(DriverFactory.getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated
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

	public static void toCheckout() {
		String url = DriverFactory.getDriver().getCurrentUrl();
		DriverFactory.getScenario().write("Navigating to the checkout page...");
		if (url.contains("basket/viewbasket")) {
			// navigate to checkout from bag page
			DriverFactory.getScenario().write("... from the shopping bag");
			Click.javascriptClickXpath("//a[contains(text(), 'GUEST CHECKOUT')]");
		} else if (url.contains("checkout")) {
			// you are in checkout
			DriverFactory.getScenario().write("The checkout page is already displayed");
			return;
		} else {
			// navigate to checkout from the mini shopping bag
			DriverFactory.getScenario().write("... from the mini shopping bag");
			Move.hoverOnByXpath("//span[@class='icon-cart']");
			Move.idleForX(1000);
			try {
				Click.javascriptClickXpath("//pink-header-bag-items//button[text()='checkout']");
			} catch (NoSuchElementException e) {
				// This handles getting to checkout on mobile
				Click.javascriptClickXpath("//span[@class='icon-cart']");
				new WebDriverWait(DriverFactory.getDriver(), 15).until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//a[contains(text(),'GUEST CHECKOUT')]"))).click();
			}
		}
		new WebDriverWait(DriverFactory.getDriver(), 15).until(ExpectedConditions.urlContains("checkout"));
		new WebDriverWait(DriverFactory.getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated
				(By.tagName("header")));
		DriverFactory.getScenario().write("Checkout page loaded");
		Move.idleForX(1500);
	}

	public static void toShirt() {
		String url = DriverFactory.getDriver().getCurrentUrl();
		if (url.contains("product")){
			// you're on a pdp, navigate to homepage
			Click.javascriptClickXpath("//a[img[@alt='Thomas Pink Logo']]");
			Move.idleForX(3000);
		}
		// click shirts, then click the first shirt
		Click.javascriptClickXpath("//div[@class='header']/a[contains(text(), 'Shirts')]");
		Move.idleForX(3000);
		Click.javascriptClickXpath("//pink-product-listing-item//a");
		Move.idleForX(3000);
	}

	public static void toTie() {
		String url = DriverFactory.getDriver().getCurrentUrl();
		if (url.contains("product")){
			// you're on a pdp, navigate to homepage
			Click.javascriptClickXpath("//a[img[@alt='Thomas Pink Logo']]");
			Move.idleForX(3000);
		}
		// click shirts, then click the first tie
		Click.javascriptClickXpath("//div[@class='header']/a[contains(text(), 'Accessories')]");
		Move.idleForX(3000);
		try {
			Click.javascriptClickXpath("//pink-product-listing-item//div[span[contains(text(),'Tie')]]/preceding-sibling::a");
		} catch (NoSuchElementException e) {
			Assert.fail("No ties found on the Accessory page");
		}
		Move.idleForX(3000);
	}

	public static void toBag() {
		String url = DriverFactory.getDriver().getCurrentUrl();
		DriverFactory.getScenario().write("Navigating to the Shopping Bag page...");
		if (url.contains("checkout")) {
			// TODO navigate to shopping bag from checkout, handle desktop and mobile
		} else if (url.contains("basket/viewbasket")) {
			// you are in checkout
			DriverFactory.getScenario().write("The Shopping Bag page is already displayed");
		} else {
			// navigate to checkout from the cart icon
			DriverFactory.getScenario().write("... from the cart icon");
			Click.javascriptClickXpath("//a[span[@class='icon-cart']]");
		}
	}
}
