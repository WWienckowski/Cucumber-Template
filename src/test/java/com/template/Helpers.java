package com.template;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Helpers {
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
	// Call this method to hover the cursor on an element
	public static void HoverOn(WebElement element) {
		Actions pointer = new Actions(DriverFactory.getDriver());
		pointer.moveToElement(element).perform();
	}
	
	public static void MoveCursor(int x, int y) {
		Actions pointer = new Actions(DriverFactory.getDriver());
		pointer.moveByOffset(x, y).perform();
	}
	public static void navigateTo(String urlSuffix) {
		DriverFactory.getScenario().write("Navigating to: "+baseUrl+urlSuffix);
		DriverFactory.getDriver().get(baseUrl+urlSuffix);
	}

	public static void isActiveByText(String element) {
		WebElement isActive = DriverFactory.getDriver().findElement(By.className("is-active"));
		if (isActive.getText().matches(element)) {
			//DriverFactory.getScenario().write(element+" is active.");
		} else {
			//DriverFactory.getScenario().write(element+" is not active");
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

	public static void clickOnByText(String elementText) {
		DriverFactory.getScenario().write("Clicking: "+elementText);
		try {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(), \'"+elementText+"\')]"));
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

	public static void includeScreenshot() {
		DriverFactory.getScenario().write("Including a screenshot with this step for manual review");
		byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
		DriverFactory.getScenario().embed(screenshot, "image/png");
	}

	public static void checkForElementByText(String element) {
		DriverFactory.getScenario().write("Looking for "+element);
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), \'"+element+"\')]")));
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
	
	public static void includeScreenshotOfElement(WebElement element) {
		BufferedImage screenshot = new AShot()
	            .shootingStrategy(ShootingStrategies.viewportPasting(100))
	            .takeScreenshot(DriverFactory.getDriver()).getImage();

	File screenshotFile = new File("target/image.png");
	try {
	    ImageIO.write(screenshot, "png", screenshotFile);
	} catch (Exception e) {
	    DriverFactory.getScenario().write("Screenshot failed");
	}

        try {
        	
			
			BufferedImage  fullScreen = ImageIO.read(screenshotFile);
			Point location= element.getLocation();
		    int width= element.getSize().getWidth();
		    int height= element.getSize().getHeight();
		    
		    BufferedImage elementImage= fullScreen.getSubimage(location.getX(), location.getY(),
		            width, height);
		    
		    ImageIO.write(elementImage, "png", screenshotFile);
		    byte[] screenByte = Files.readAllBytes(screenshotFile.toPath());
		    
		    DriverFactory.getScenario().embed(screenByte, "image/png");
		} catch (Exception e) {
			DriverFactory.getScenario().write("Screenshot failed");
		}
	    
	    
	}

	public static void clickOnByAltText(String altText) {
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

	public static void checkUrl(String page) {
		page = page.equalsIgnoreCase("Homepage") ? baseUrl : baseUrl+page;
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);
		Assert.assertTrue(page+" is the expected URL\n"+DriverFactory.getDriver().getCurrentUrl()+" is the actual URL",
				wait.until(ExpectedConditions.urlToBe(page)));
		DriverFactory.getScenario().write("Current URL is: "+DriverFactory.getDriver().getCurrentUrl());
	}

	public static void clickOnByLinkText(String linkText) {
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

	public static void checkInputFieldPlaceholders(List<String> fieldNames, WebElement fieldset) {
		includeScreenshotOfElement(fieldset);
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

	public static void isButtonEnabledByText(String text, Boolean expected) {
		WebElement button = DriverFactory.getDriver().findElement(By.xpath("//button[text()=\'"+text+"\']"));
		Assert.assertEquals(expected, button.isEnabled());
		DriverFactory.getScenario().write("Expecting button enabled: "+expected+" \nActual button enabled: "+button.isEnabled());
	}

	public static void clickCheckboxByText(String checkText) {
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

	public static void verifyToolTipByFieldName(String fieldName) {
		DriverFactory.getDriver().findElement(By.xpath("//input[contains(@placeholder,\'"+fieldName+
				"\')]/following-sibling::a[@class='form-control_tooltip']/*[name()='svg']"));
		includeScreenshot();
		DriverFactory.getScenario().write("Tool tip icon found");
	}

	public static void mouseOut() {
		Helpers.MoveCursor(50, 50);
	}

	public static void hoverOnByText(String text) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(), \'"+text+"\')]"));
		Helpers.HoverOn(element);
	}

	public static void checkCursor(String expected, WebElement element) {
		String cursor = element.getCssValue("cursor");
		Assert.assertTrue(expected.equals(cursor));
		DriverFactory.getScenario().write("Cursor is "+cursor);
		
	}

	public static void findComponentByText(String componentText) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(),\'"+componentText+"\')]"));
			scrollToElement(element);
		} catch (Exception e) {
			Assert.fail("No "+componentText+" component found. This may be due to a spelling or capitalization difference.");
		} 
		DriverFactory.getScenario().write(componentText+" found");
		includeScreenshot();
	}
	
	public static void scrollToElement(WebElement element) {
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
	}


	public static void checkCSS(String xpath, String expectedValue, String cssValue) {
		String actualValue = DriverFactory.getDriver().findElement(By.xpath(xpath)).getCssValue(cssValue);
		DriverFactory.getScenario().write("Expected "+cssValue+": "+expectedValue+"\nActual "+cssValue+": "+actualValue);
		Assert.assertEquals("Mismatch in "+cssValue+" values...", expectedValue, actualValue);
	}

	public static void tapAnywhere() {
		Actions action = new Actions(DriverFactory.getDriver());
		action.moveByOffset(-100, 0).click().perform();
	}
	
	public static Boolean isXaboveY(WebElement x, WebElement y) {
		int xBottom = x.getRect().y+x.getRect().height;
		int yTop = y.getRect().y;
		
		Boolean result = xBottom<=yTop ? true : false;
		
		return result;
	}

	public static void isDisplayed(String xpath, Boolean expected) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		Assert.assertTrue("Element is displayed: "+expected, expected==element.isDisplayed());
		DriverFactory.getScenario().write("Element is displayed: "+expected);
	}

	public static void findLinkByText(String link) {
		try {
			WebElement element = DriverFactory.getDriver().findElement(By.xpath("//*[contains(text(),\'"+link+"\')]"));
			scrollToElement(element);
			if (!element.getTagName().contentEquals("a")) {
				Assert.fail("Expected element tag to be 'a', but it was '"+element.getTagName()+"'");
			}
		} catch (Exception e) {
			Assert.fail("No element found with text: "+link);
		} 
		DriverFactory.getScenario().write(link+" link found");
		includeScreenshot();
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

	public static void getTextByXpath(String xpath) {
		DriverFactory.getScenario().write(DriverFactory.getDriver().findElement(By.xpath(xpath)).getText());		
	}

	public static void scrollToXpath(String xpath) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
		((JavascriptExecutor) DriverFactory.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		
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

}
