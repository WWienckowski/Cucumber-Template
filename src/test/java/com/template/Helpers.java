package com.template;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Helpers {
	
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
