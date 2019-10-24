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
	
	public static void isSelectedByText(String text, boolean expected) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(By.xpath("//input[following-sibling::*[contains(text(), \'"+text+"\')]]"));
			Boolean actual = element.isSelected();
			DriverFactory.getScenario().write("Expecting Selected to be: "+expected+"\nSelected is: "+actual);
			Assert.assertTrue(actual==expected);
		} catch (NoSuchElementException e) {
			Assert.fail("No element found with label: "+text+"\nThis may be due to a difference in spelling,"
					+ "wording, or capitalization.");
		}
			
	}
	
	public static void isButtonEnabledByText(String text, Boolean expected) {
		WebElement button = DriverFactory.getDriver().findElement(By.xpath("//button[text()=\'"+text+"\']"));
		Assert.assertEquals(expected, button.isEnabled());
		DriverFactory.getScenario().write("Expecting button enabled: "+expected+" \nActual button enabled: "+button.isEnabled());
	}
	
	public static void isActiveByText(String element) {
		WebElement isActive = DriverFactory.getDriver().findElement(By.className("is-active"));
		if (isActive.getText().matches(element)) {
			DriverFactory.getScenario().write(element+" is active.");
		} else {
			DriverFactory.getScenario().write(element+" is not active");
			Assert.fail();
		}
		
	}
	
	public static void isInactiveByText(String element) {
		WebElement isActive = DriverFactory.getDriver().findElement(By.className("is-active"));
		if (!isActive.getText().matches(element)) {
			DriverFactory.getScenario().write(element+" is inactive.");
		} else {
			DriverFactory.getScenario().write(element+" is active");
			Assert.fail();
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
	
	public static void isXaboveYbyXpath(String xpathX, String xpathY, Boolean expected) {
		WebElement x = DriverFactory.getDriver().findElement(By.xpath(xpathX));
		WebElement y = DriverFactory.getDriver().findElement(By.xpath(xpathY));
		
		int xBottom = x.getRect().y+x.getRect().height;
		int yTop = y.getRect().y;
		
		Boolean result = xBottom<=yTop ? true : false;
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
	
	public static void findElementByPlaceholder(String placeholder) {
		DriverFactory.getScenario().write("Looking for: "+placeholder);
		findElementByXpath("//pink-payment-options-form//input[@placeholder=\'"+placeholder+"\']");
	}
	
	public static void findLinkByText(String link) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(),\'"+link+"\')]"));
			Move.scrollToElement(element);
			if (!element.getTagName().contentEquals("a")) {
				Assert.fail("Expected element tag to be 'a', but it was '"+element.getTagName()+"'");
			}
		} catch (Exception e) {
			Assert.fail("No element found with text: "+link);
		} 
		DriverFactory.getScenario().write(link+" link found");
		Screenshot.includeScreenshot();
	}
	
	public static Boolean isXaboveY(WebElement x, WebElement y) {
		int xBottom = x.getRect().y+x.getRect().height;
		int yTop = y.getRect().y;
		
		Boolean result = xBottom<=yTop ? true : false;
		
		return result;
	}

	public static void isDisplayed(String xpath, Boolean expected) {
		List<WebElement> elements = DriverFactory.getDriver().findElements(By.xpath(xpath));
		if (elements.size()==0) {
			if (expected==true) {
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
		Assert.assertTrue(expected.equals(cursor));
		DriverFactory.getScenario().write("Cursor is "+cursor);
		
	}
	
	public static void verifyToolTipByFieldName(String fieldName) {
		DriverFactory.getDriver().findElement(By.xpath("//input[contains(@placeholder,\'"+fieldName+
				"\')]/following-sibling::a[@class='form-control_tooltip']/*[name()='svg']"));
		Screenshot.includeScreenshot();
		DriverFactory.getScenario().write("Tool tip icon found");
	}
	
	public static void checkInputFieldPlaceholders(List<String> fieldNames, WebElement fieldset) {
		Screenshot.includeScreenshotOfElement(fieldset);
		int notFound = 0;
		for (String fieldName : fieldNames) {
			DriverFactory.getScenario().write("\nLooking for: "+fieldName);
			try {
				fieldset.findElement(By.xpath(".//input[@placeholder=\'"+fieldName+"\']")); 
				} catch (Exception e) {
					try {
						fieldset.findElement(By.xpath(".//*[contains(text(), \'"+fieldName+"\')]"));
					} catch (Exception f) {
						DriverFactory.getScenario().write("NOT FOUND");
						notFound++;
						continue;
					}
			} 
			DriverFactory.getScenario().write("Found");
		}
		if (notFound > 0) {
			DriverFactory.getScenario().write(notFound+" elements not found. Scenario failed.");
			Assert.fail(notFound+" elements not found. Scenario failed.");
		}
	}
	
	public static void getTextByXpath(String xpath) {
		DriverFactory.getScenario().write(DriverFactory.getDriver().findElement(By.xpath(xpath)).getText());		
	}
}
