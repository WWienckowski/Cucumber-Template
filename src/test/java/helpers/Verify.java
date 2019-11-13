package helpers;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;

public class Verify {
	
	public static void inputMatchesValue(String input, String value) {
		Assert.assertEquals("Input: "+input+"\nValue"+value,input, value);
		DriverFactory.getScenario().write("Input: "+input+"\nValue"+value);
	}

	public static void isButtonEnabledByText(String text, Boolean expected) {
		WebElement button = DriverFactory.getDriver().findElement(By.xpath("//button[text()=\'"+text+"\']"));
		Assert.assertEquals(expected, button.isEnabled());
		DriverFactory.getScenario().write("Expecting button enabled: "+expected+" \nActual button enabled: "+button.isEnabled());
	}
	
	public static void isActiveByText(String element) {
		Move.idleForX(1000);
		WebElement isActive = DriverFactory.getDriver().findElement(By.className("is-active"));
		if (isActive.getText().matches(element)) {
			DriverFactory.getScenario().write(element+" is active.");
		} else {
			Assert.fail(element+" is not active");
		}
		
	}

	public static void checkForElementByText(String element) {
		DriverFactory.getScenario().write("Looking for "+element);
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), \""+element+"\")]")));
		} catch (NoSuchElementException e) {
			Assert.fail(element+" not found");
		}
		DriverFactory.getScenario().write(element+" found");
	}
	
	public static void findComponentByText(String componentText) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(),\""+componentText+"\")]"));
			Move.scrollToElement(element);
		} catch (NoSuchElementException e) {
			Assert.fail("No "+componentText+" component found. This may be due to a spelling or capitalization difference.");
		} 
		DriverFactory.getScenario().write(componentText+" found");
		Screenshot.includeScreenshot();
	}
	
	public static void checkForValidationText(String text) {
		int error = DriverFactory.getDriver().findElements
				(By.xpath(".//div[contains(text(), \'"+text+"\')]")).size();
		Assert.assertTrue("Error message not displayed", error>0);
		DriverFactory.getScenario().write("Error message displayed.");
	}
	
	public static void isXaboveYbyXpath(String xpathX, String xpathY, Boolean expected) {
		WebElement x = DriverFactory.getDriver().findElement(By.xpath(xpathX));
		WebElement y = DriverFactory.getDriver().findElement(By.xpath(xpathY));
		
		int xBottom = x.getRect().y+x.getRect().height;
		int yTop = y.getRect().y;
		
		Boolean result = xBottom <= yTop;
		if (result!=expected) {
			Assert.fail("Relative element locations were not as expected");
		}
		DriverFactory.getScenario().write("Relative element locations were as expected.");
	}
	
	public static void findElementByXpath(String xpath) {
		try {
			DriverFactory.getDriver().findElement(By.xpath(xpath));
		} catch (Exception e) {
			Assert.fail("Element not found. This may be due to a difference in spelling, wording, or capitalization");
		}
		DriverFactory.getScenario().write("Element found.");
	}

	public static Boolean isXaboveY(WebElement x, WebElement y) {
		int xBottom = x.getRect().y+x.getRect().height;
		int yTop = y.getRect().y;

		return xBottom <= yTop;
	}

	public static void isDisplayed(String xpath, Boolean expected) {
		List<WebElement> elements = DriverFactory.getDriver().findElements(By.xpath(xpath));
		if (elements.size()==0) {
			if (expected) {
				Assert.fail("Element not found");
			} else {
				DriverFactory.getScenario().write("Element not found");
				return;
			}
		}
		String message = elements.get(0).isDisplayed() 
				? "Element is displayed" : "Element is present, but not displayed";
		Assert.assertEquals(message, expected, elements.get(0).isDisplayed());
		DriverFactory.getScenario().write(message);
	}
	
	public static void checkCSS(String xpath, String expectedValue, String cssValue) {
		String actualValue = DriverFactory.getDriver().findElement(By.xpath(xpath)).getCssValue(cssValue);
		DriverFactory.getScenario().write("Expected "+cssValue+": "+expectedValue+"\nActual "+cssValue+": "+actualValue);
		Assert.assertEquals("Mismatch in "+cssValue+" values...", expectedValue, actualValue);
	}
	
	public static void checkCursor(String expected, WebElement element) {
		String cursor = element.getCssValue("cursor");
		Assert.assertEquals("Unexpected cursor value:", expected, cursor);
		DriverFactory.getScenario().write("Cursor is "+cursor);
		
	}

	public static void getTextByXpath(String xpath) {
		DriverFactory.getScenario().write(DriverFactory.getDriver().findElement(By.xpath(xpath)).getText());		
	}

	public static void screenAnchorsToErrorMessage(String errorMessage) {
		System.out.println(DriverFactory.getDriver()
				.findElement(By.xpath("//*[contains(text(), \'"+errorMessage+"\')]")).getLocation());
	}

	public static void linkIsPresent(String linkText) {
		int link = DriverFactory.getDriver().findElements(By.linkText(linkText)).size();
		Assert.assertNotEquals("No link found with this text: "+linkText, 0, link);
		DriverFactory.getScenario().write("Link found.");
	}

	public static void linkIsAbsent(String linkText) {
		int link = DriverFactory.getDriver().findElements(By.linkText(linkText)).size();
		Assert.assertEquals(link+" Link(s) found with this text: "+linkText, 0, link);
		DriverFactory.getScenario().write("Link is absent.");
	}
}
