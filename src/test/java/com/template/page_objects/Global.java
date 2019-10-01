package com.template.page_objects;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.Helpers;

import cucumber.api.Scenario;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Global {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	
	String baseUrl;
	
	public Global(WebDriver driver, WebDriverWait wait, Scenario scenario, String baseUrl) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
		 this.baseUrl = baseUrl;
		 PageFactory.initElements(driver, this);
		 }
	
	public void navigateTo(String urlSuffix) {
		scenario.write("Navigating to: "+baseUrl+urlSuffix);
		driver.get(baseUrl+urlSuffix);
	}

	public void isActiveByText(String element) {
		WebElement isActive = driver.findElement(By.className("is-active"));
		if (isActive.getText().matches(element)) {
			scenario.write(element+" is active.");
		} else {
			scenario.write(element+" is not active");
			Assert.fail();
		}
		
	}
	
	public void isInactiveByText(String element) {
		WebElement isActive = driver.findElement(By.className("is-active"));
		if (!isActive.getText().matches(element)) {
			scenario.write(element+" is inactive.");
		} else {
			scenario.write(element+" is active");
			Assert.fail();
		}
		
	}

	public void clickOnByText(String elementText) {
		scenario.write("Clicking: "+elementText);
		try {
		WebElement element = driver.findElement(By.xpath("//*[contains(text(), \'"+elementText+"\')]"));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		} catch (NoSuchElementException e) {
			Assert.fail("No element found with this text."
					+ " \nThere may be a misspelling or a difference in wording or capitalization.");
		} catch (Exception f) {
			f.printStackTrace();
		}
		scenario.write("Success");
	}

	public void includeScreenshot() {
		scenario.write("Including a screenshot with this step for manual review");
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

	public void checkForElementByText(String element) {
		scenario.write("Looking for "+element);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), \'"+element+"\')]")));
	}

	public void isSelectedByText(String text, boolean expected) {
		try {
			WebElement element = driver.findElement(By.xpath("//input[following-sibling::*[contains(text(), \'"+text+"\')]]"));
			Boolean actual = element.isSelected();
			scenario.write("Expecting Selected to be: "+expected+"\nSelected is: "+actual);
			Assert.assertTrue(actual==expected);
		} catch (NoSuchElementException e) {
			Assert.fail("No element found with label: "+text+"\nThis may be due to a difference in spelling,"
					+ "wording, or capitalization.");
		}
			
	}
	
	public void includeScreenshotOfElement(WebElement element) {
		BufferedImage screenshot = new AShot()
	            .shootingStrategy(ShootingStrategies.viewportPasting(100))
	            .takeScreenshot(driver).getImage();

	File screenshotFile = new File("target/image.png");
	try {
	    ImageIO.write(screenshot, "png", screenshotFile);
	} catch (Exception e) {
	    scenario.write("Screenshot failed");
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
		    
		    scenario.embed(screenByte, "image/png");
		} catch (Exception e) {
			scenario.write("Screenshot failed");
		}
	    
	    
	}

	public void clickOnByAltText(String altText) {
		try {
			scenario.write("Clicking: "+altText);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt=\'"+altText+"\')]"))).click();
		} catch (Exception e) {
			scenario.write("Unable to click "+altText);
			Assert.fail();
		}
		scenario.write("Success");
	}

	public void checkUrl(String page) {
		page = page.equalsIgnoreCase("Homepage") ? baseUrl : baseUrl+page;
		Assert.assertTrue(page+" is the expected URL\n"+driver.getCurrentUrl()+" is the actual URL",
				wait.until(ExpectedConditions.urlToBe(page)));
		scenario.write("Current URL is: "+driver.getCurrentUrl());
	}

	public void clickOnByLinkText(String linkText) {
		try {
			scenario.write("Clicking: "+linkText);
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText))).click();
		} catch (Exception e) {
			scenario.write("Unable to click "+linkText);
			Assert.fail();
		}
		scenario.write("Success");
	}

	public void checkInputFieldPlaceholders(List<String> fieldNames, WebElement fieldset) {
		this.includeScreenshotOfElement(fieldset);
		int notFound = 0;
		for (String fieldName : fieldNames) {
			scenario.write("\nLooking for: "+fieldName);
			try {
				fieldset.findElement(By.xpath(".//input[@placeholder=\'"+fieldName+"\']")); 
				} catch (Exception e) {
					try {
						fieldset.findElement(By.xpath(".//*[contains(text(), \'"+fieldName+"\')]"));
					} catch (Exception f) {
						scenario.write("NOT FOUND");
						notFound++;
						continue;
					}
			} 
			scenario.write("Found");
		}
		if (notFound > 0) {
			scenario.write(notFound+" elements not found. Scenario failed.");
			Assert.fail(notFound+" elements not found. Scenario failed.");
		}
	}

	public void isButtonEnabledByText(String text, Boolean expected) {
		WebElement button = driver.findElement(By.xpath("//button[text()=\'"+text+"\']"));
		Assert.assertEquals(expected, button.isEnabled());
		scenario.write("Expecting button enabled: "+expected+" \nActual button enabled: "+button.isEnabled());
	}

	public void clickCheckboxByText(String checkText) {
		scenario.write("Clicking: "+checkText+" checkbox");
		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(text(), \'"+checkText+"\')]/preceding-sibling::input[@type='checkbox']"));
			this.javascriptClick(element);
		} catch (NoSuchElementException e) {
			Assert.fail("No element found with label: "+checkText+"\nThis may be due to a difference in spelling,"
					+ "wording, or capitalization.");
		}
		
		scenario.write("Success");
	}
	
	public void javascriptClick(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		
	}

	public void verifyToolTipByFieldName(String fieldName) {
		driver.findElement(By.xpath("//input[contains(@placeholder,\'"+fieldName+
				"\')]/following-sibling::a[@class='form-control_tooltip']/*[name()='svg']"));
		this.includeScreenshot();
		scenario.write("Tool tip icon found");
	}

	public void mouseOut() {
		Helpers.MoveCursor(50, 50, driver);
	}

	public void hoverOnByText(String text) {
		WebElement element = driver.findElement(By.xpath("//*[contains(text(), \'"+text+"\')]"));
		Helpers.HoverOn(element, driver);
	}

	public void checkCursor(String expected, WebElement element) {
		String cursor = element.getCssValue("cursor");
		Assert.assertTrue(expected.equals(cursor));
		scenario.write("Cursor is "+cursor);
		
	}

	public void findComponentByText(String componentText) {
		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(text(),\'"+componentText+"\')]"));
			this.scrollToElement(element);
		} catch (Exception e) {
			Assert.fail("No "+componentText+" component found. This may be due to a spelling or capitalization difference.");
		} 
		scenario.write(componentText+" found");
		this.includeScreenshot();
	}
	
	public void scrollToElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}


	public void checkCSS(String xpath, String expectedValue, String cssValue) {
		String actualValue = driver.findElement(By.xpath(xpath)).getCssValue(cssValue);
		scenario.write("Expected "+cssValue+": "+expectedValue+"\nActual "+cssValue+": "+actualValue);
		Assert.assertEquals("Mismatch in "+cssValue+" values...", expectedValue, actualValue);
	}

	public void tapAnywhere() {
		Actions action = new Actions(driver);
		action.moveByOffset(-100, 0).click().perform();
	}
	
	public Boolean isXaboveY(WebElement x, WebElement y) {
		int xBottom = x.getRect().y+x.getRect().height;
		int yTop = y.getRect().y;
		
		Boolean result = xBottom<=yTop ? true : false;
		
		return result;
	}

	public void isDisplayed(String xpath, Boolean expected) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Assert.assertTrue("Element is displayed: "+expected, expected==element.isDisplayed());
		scenario.write("Element is displayed: "+expected);
	}

	public void findLinkByText(String link) {
		try {
			WebElement element = driver.findElement(By.xpath("//*[contains(text(),\'"+link+"\')]"));
			this.scrollToElement(element);
			if (!element.getTagName().contentEquals("a")) {
				Assert.fail("Expected element tag to be 'a', but it was '"+element.getTagName()+"'");
			}
		} catch (Exception e) {
			Assert.fail("No element found with text: "+link);
		} 
		scenario.write(link+" link found");
		this.includeScreenshot();
	}

	public void isXaboveYbyXpath(String xpathX, String xpathY, Boolean expected) {
		WebElement x = driver.findElement(By.xpath(xpathX));
		WebElement y = driver.findElement(By.xpath(xpathY));
		
		int xBottom = x.getRect().y+x.getRect().height;
		int yTop = y.getRect().y;
		
		Boolean result = xBottom<=yTop ? true : false;
		if (result!=expected) {
			Assert.fail("Relative element locations were not as expected");
		}
		scenario.write("Relative element locations were as expected.");
	}

	public void findElementByXpath(String xpath) {
		try {
			driver.findElement(By.xpath(xpath));
		} catch (Exception e) {
			Assert.fail("Element not found. This may be due to a difference in spelling, wording, or capitalization");
		}
		scenario.write("Element found.");
	}

	public void findElementByPlaceholder(String placeholder) {
		scenario.write("Looking for: "+placeholder);
		this.findElementByXpath("//pink-payment-options-form//input[@placeholder=\'"+placeholder+"\']");
	}

	public void getTextByXpath(String xpath) {
		scenario.write(driver.findElement(By.xpath(xpath)).getText());		
	}

	
}
