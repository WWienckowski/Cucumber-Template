package helpers;

import java.sql.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class Input {
	public static void inputTextByPlaceholder(String placeholder, String text) {
		new WebDriverWait(DriverFactory.getDriver(), 15).until
		(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder=\'"+placeholder+"\']")));
		DriverFactory.getDriver().findElement(By.xpath("//input[@placeholder=\'"+placeholder+"\']")).sendKeys(text);		
	}

	public static void exitFieldByPlaceholder(String placeholder) {
		DriverFactory.getDriver().findElement(By.xpath("//input[@placeholder=\'"+placeholder+"\']")).sendKeys(Keys.TAB);
	}

	public static String getValueFromPlaceHolder(String placeholder) {
		return DriverFactory.getDriver().findElement(By.xpath("//input[@placeholder=\'"+placeholder+"\']")).getAttribute("value");
	}
	
	public static void setCart(String item) {
		Navigate.to("");
		new WebDriverWait(DriverFactory.getDriver(), 15).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//picture[@class='background-image']")));
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		executor.executeScript(String.format("window.localStorage.setItem('pink-shopper','%s');", item));
		Move.idleForX(300);
	}
	
	public static void clearLocal() {
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		executor.executeScript("window.localStorage.clear()");
	}
	
	public static void getConsole() {
		LogEntries logEntries = DriverFactory.getDriver().manage().logs().get(LogType.BROWSER);
	    for (LogEntry entry : logEntries) {
	        System.out.println
	        (new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
	    }
	}
}
