package helpers;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class Click {
	
	public static void byText(String elementText) {
		DriverFactory.getScenario().write("Clicking: "+elementText);
		try {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(), \""+elementText+"\")]"));
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (NoSuchElementException e) {
			Assert.fail("No element found with this text."
					+ " \nThere may be a misspelling or a difference in wording or capitalization.");
		} catch (Exception f) {
			f.printStackTrace();
		}
		DriverFactory.getScenario().write("Success");
	}
	
	public static void byAltText(String altText) {
		try {
			DriverFactory.getScenario().write("Clicking: "+altText);
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt=\'"+altText+"\')]"))).click();
		} catch (Exception e) {
			DriverFactory.getScenario().write("Unable to click "+altText);
			Assert.fail();
		}
		DriverFactory.getScenario().write("Success");
	}
	
	public static void byLinkText(String linkText) {
		try {
			DriverFactory.getScenario().write("Clicking: "+linkText);
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
		} catch (Exception e) {
			DriverFactory.getScenario().write("Unable to click "+linkText);
			Assert.fail();
		}
		DriverFactory.getScenario().write("Success");
	}
	
	public static void byCheckboxText(String checkText) {
		DriverFactory.getScenario().write("Clicking: "+checkText+" checkbox");
		try {
			WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(), \'"+checkText+"\')]/preceding-sibling::input[@type='checkbox']"));
			javascriptClick(element);
		} catch (NoSuchElementException e) {
			Assert.fail("No element found with label: "+checkText+"\nThis may be due to a difference in spelling,"
					+ "wording, or capitalization.");
		}
		
		DriverFactory.getScenario().write("Success");
	}
	
	public static void javascriptClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		executor.executeScript("arguments[0].click();", element);	
	}
	
	public static void javascriptClickXpath(String xpath) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}
	
	public static void clickOnByXpath(String xpath) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
			WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			} catch (NoSuchElementException e) {
				Assert.fail("No element found with this text."
						+ " \nThere may be a misspelling or a difference in wording or capitalization.");
			} catch (Exception f) {
				f.printStackTrace();
			}
			DriverFactory.getScenario().write("Success");
	}
	
	public static void tapAnywhere() {
		Actions action = new Actions(DriverFactory.getDriver());
		action.moveByOffset(-100, 0).click().perform();
	}
}
