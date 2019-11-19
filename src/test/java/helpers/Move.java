package helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import driver.DriverFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class Move {

	public static void hoverOnByText(String text) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(), \""+text+"\")]"));
		hoverOnByElement(element);
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
	
	public static void scrollToBottom() {
		((JavascriptExecutor) DriverFactory.getDriver())
	    .executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void moveToXpath(String xpath) {
		WebElement target = DriverFactory.getDriver().findElement(By.xpath(xpath));
		Actions pointer = new Actions(DriverFactory.getDriver());
		pointer.moveToElement(target).perform();
	}
	
	public static void hoverOnByElement(WebElement element) {
		Actions pointer = new Actions(DriverFactory.getDriver());
		pointer.moveToElement(element).perform();
	}

	public static void hoverOnByXpath(String xpath) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		Actions pointer = new Actions(DriverFactory.getDriver());
		pointer.moveToElement(element).perform();
	}
	
	public static void idleForX(Integer milliseconds) {
		Actions idle = new Actions(DriverFactory.getDriver());
		idle.pause(milliseconds);
		idle.perform();
	}

    public static void waitUntilSelectPopulates(Select select) {
		int tries = 0;
		int maxTries = 10;

		while(true){
			if (select.getOptions().size()>1) {
				DriverFactory.getScenario().write("Select has populated");
				break;
			} else {
				if (++tries > maxTries) Assert.fail("Select did not populate after "+maxTries+" seconds");
				DriverFactory.getScenario().write("Attempt: "+tries+", Select has not populated.");
				idleForX(1000);
			}
		}
    }
}
