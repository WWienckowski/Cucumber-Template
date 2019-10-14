package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import driver.DriverFactory;

public class Move {
	public static void mouseOut() {
		MoveCursor(50, 50);
	}

	public static void hoverOnByText(String text) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(), \'"+text+"\')]"));
		HoverOn(element);
	}

	public static void scrollToElement(WebElement element) {
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void scrollToXpath(String xpath) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void scrollToTop() {
		((JavascriptExecutor) DriverFactory.getDriver())
	    .executeScript("window.scrollTo(0, 0)");
	}
	
	public static void MoveCursor(int x, int y) {
		Actions pointer = new Actions(DriverFactory.getDriver());
		pointer.moveByOffset(x, y).perform();
	}
	
	public static void HoverOn(WebElement element) {
		Actions pointer = new Actions(DriverFactory.getDriver());
		pointer.moveToElement(element).perform();
	}
	
	public static void idleForX(Integer seconds) {
		Actions idle = new Actions(DriverFactory.getDriver());
		idle.pause(seconds);
		idle.perform();
	}
}
