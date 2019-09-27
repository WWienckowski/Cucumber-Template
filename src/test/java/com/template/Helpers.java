package com.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Helpers {
	// This method determines which browser to use based on the Scenario name and deaults to Firefox
	public static String browserCheck(String scenarioName) {
		// Default to Firefox
		String browser = "Chrome";
		// Check for Chrome
		boolean chrome = scenarioName.matches(".*\\bChrome\\b.*");
		if (chrome == true) {
			browser = "Chrome";
			return browser;
		}
		// Check for Edge
		boolean edge = scenarioName.matches(".*\\bEdge\\b.*");
		if (edge == true) {
			browser = "Edge";
			return browser;
		}
		return browser;
	}
	
	// Call this method to hover the cursor on an element
	public static void HoverOn(WebElement element, WebDriver driver) {
		Actions pointer = new Actions(driver);
		pointer.moveToElement(element).perform();
	}
	
	public static void MoveCursor(int x, int y, WebDriver driver) {
		Actions pointer = new Actions(driver);
		pointer.moveByOffset(x, y).perform();
	}

}
