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

import com.template.DriverManager;
import com.template.Helpers;

import cucumber.api.Scenario;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Global {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	
	final String baseUrl = DriverManager.checkEnvironment();
	
	public Global(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
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
		page = page.contains("Shopping Bag") ? baseUrl+"basket/viewbasket" : baseUrl+page;
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

	public void isVisible(String xpath, boolean expected) {
		WebElement element = driver.findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(element));
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

	public void scrollToXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
	}

	public void clickOnByXpath(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();
			} catch (NoSuchElementException e) {
				Assert.fail("No element found with this text."
						+ " \nThere may be a misspelling or a difference in wording or capitalization.");
			} catch (Exception f) {
				f.printStackTrace();
			}
			scenario.write("Success");
	}

	public void idleForX(Integer seconds) {
		Actions idle = new Actions(driver);
		idle.pause(5000);
		idle.perform();
	}

	public void setCart() {
		String item = "{\"accessToken\":\"pMIPSPvLjZ9n_eiKBxy8sdvmAN8vCUhK\",\"expiresIn\":172800,\"anonymous\":true,\"scope\":\"manage_project:pink-shirtmaker-dev\",\"tokenType\":\"Bearer\",\"tokenGen\":1570568152802,\"bag\":{\"type\":\"Cart\",\"id\":\"74226e53-d661-4aea-baf2-32c830257038\",\"version\":13,\"createdAt\":\"2019-10-08T21:07:33.211Z\",\"lastModifiedAt\":\"2019-10-09T13:01:29.597Z\",\"lastModifiedBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"createdBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"lineItems\":[{\"id\":\"744fabbe-282f-4e48-a263-1fada7906738\",\"productId\":\"01826bbb-4e08-4e87-81cd-011544f02ab2\",\"name\":{\"en-US\":\"Spot Woven Silk Tie\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"spot-woven-silk-tie-70111150\"},\"variant\":{\"id\":1,\"sku\":\"70111150P2L0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":11500,\"fractionDigits\":2},\"id\":\"6a37b701-52d0-4831-91d8-201833663785\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"d75c4ace-01d4-474e-a0ab-fa02189dc775\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111150\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Pink\"},{\"name\":\"SEASON\",\"value\":\"AW18\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111150P2L\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"ties-woven\",\"ties\",\"all-accessories\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Spot Woven Silk Tie\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111150P3W\",\"70111150P2L\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Pink/Navy\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[],\"availability\":{\"channels\":{\"072f015f-cbad-4042-ba3b-52b4a863238b\":{\"isOnStock\":true,\"availableQuantity\":26},\"89de900c-1cac-4d66-913c-31acb3a0ae6d\":{\"isOnStock\":true,\"availableQuantity\":11},\"9e41116c-4636-46fd-924e-4092e60d3915\":{\"isOnStock\":true,\"availableQuantity\":11}}}},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":1,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":1,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"},{\"id\":\"ac50b4fd-2c75-41fa-af87-71f714666224\",\"productId\":\"496af939-8234-4920-b411-127c694d5a4c\",\"name\":{\"en-US\":\"Roberts Stripe Skinny Woven\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"roberts-stripe-skinny-woven-70111125\"},\"variant\":{\"id\":1,\"sku\":\"70111125B2S0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"927e10bc-3105-4fcb-ba97-dc742d00476e\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":7000,\"fractionDigits\":2},\"id\":\"b6141a82-f537-4956-8993-8f224047c534\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111125\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Blue\"},{\"name\":\"SEASON\",\"value\":\"CLAS\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111125B2S\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"business\",\"all-accessories\",\"ties-woven\",\"ties\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Roberts Stripe Skinny Woven\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111125L3R\",\"70111125B2S\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Blue/Sky\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[]},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":5,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":5,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":67500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"}],\"cartState\":\"Active\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":84000,\"fractionDigits\":2},\"country\":\"US\",\"customLineItems\":[],\"discountCodes\":[],\"inventoryMode\":\"None\",\"taxMode\":\"External\",\"taxRoundingMode\":\"HalfEven\",\"taxCalculationMode\":\"LineItemLevel\",\"refusedGifts\":[],\"origin\":\"Customer\",\"itemShippingAddresses\":[]}}";
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript(String.format("window.localStorage.setItem('pink-shopper','%s');", item));

	}
	public void getCart() {
		//RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
		// RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
		// LocalStorage storage = webStorage.getLocalStorage();
		 //scenario.write(storage.getItem("pink-shopper"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		String item = (String) executor.executeScript("return window.localStorage.getItem('pink-shopper')");
		scenario.write(item);
	}

}