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
	
	public synchronized static void setShopper() {
		String shopper = "\"{\"accessToken\":\"pMIPSPvLjZ9n_eiKBxy8sdvmAN8vCUhK\",\"expiresIn\":172800,\"anonymous\":true,\"scope\":\"manage_project:pink-shirtmaker-dev\",\"tokenType\":\"Bearer\",\"tokenGen\":1570568152802,\"bag\":{\"type\":\"Cart\",\"id\":\"74226e53-d661-4aea-baf2-32c830257038\",\"version\":13,\"createdAt\":\"2019-10-08T21:07:33.211Z\",\"lastModifiedAt\":\"2019-10-09T13:01:29.597Z\",\"lastModifiedBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"createdBy\":{\"clientId\":\"Ey8F16RwTXKwxCh9568CRzlQ\",\"isPlatformClient\":false},\"lineItems\":[{\"id\":\"744fabbe-282f-4e48-a263-1fada7906738\",\"productId\":\"01826bbb-4e08-4e87-81cd-011544f02ab2\",\"name\":{\"en-US\":\"Spot Woven Silk Tie\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"spot-woven-silk-tie-70111150\"},\"variant\":{\"id\":1,\"sku\":\"70111150P2L0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":11500,\"fractionDigits\":2},\"id\":\"6a37b701-52d0-4831-91d8-201833663785\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"d75c4ace-01d4-474e-a0ab-fa02189dc775\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111150\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Pink\"},{\"name\":\"SEASON\",\"value\":\"AW18\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111150P2L\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"ties-woven\",\"ties\",\"all-accessories\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Spot Woven Silk Tie\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111150P3W\",\"70111150P2L\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Pink/Navy\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[],\"availability\":{\"channels\":{\"072f015f-cbad-4042-ba3b-52b4a863238b\":{\"isOnStock\":true,\"availableQuantity\":26},\"89de900c-1cac-4d66-913c-31acb3a0ae6d\":{\"isOnStock\":true,\"availableQuantity\":11},\"9e41116c-4636-46fd-924e-4092e60d3915\":{\"isOnStock\":true,\"availableQuantity\":11}}}},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"id\":\"8690a3dd-5cc2-4975-b454-7a41dd27c479\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":2,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":1,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":16500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"},{\"id\":\"ac50b4fd-2c75-41fa-af87-71f714666224\",\"productId\":\"496af939-8234-4920-b411-127c694d5a4c\",\"name\":{\"en-US\":\"Roberts Stripe Skinny Woven\"},\"productType\":{\"typeId\":\"product-type\",\"id\":\"93f256a9-aef1-450e-8f01-03430afb2061\",\"version\":139},\"productSlug\":{\"en-US\":\"roberts-stripe-skinny-woven-70111125\"},\"variant\":{\"id\":1,\"sku\":\"70111125B2S0000\",\"prices\":[{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"EUR\",\"centAmount\":9500,\"fractionDigits\":2},\"id\":\"927e10bc-3105-4fcb-ba97-dc742d00476e\",\"country\":\"FR\",\"channel\":{\"typeId\":\"channel\",\"id\":\"89de900c-1cac-4d66-913c-31acb3a0ae6d\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"GBP\",\"centAmount\":7000,\"fractionDigits\":2},\"id\":\"b6141a82-f537-4956-8993-8f224047c534\",\"country\":\"GB\",\"channel\":{\"typeId\":\"channel\",\"id\":\"9e41116c-4636-46fd-924e-4092e60d3915\"}},{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}}],\"images\":[],\"attributes\":[{\"name\":\"ARTICLE_CODE\",\"value\":\"70111125\"},{\"name\":\"MASTER_COLOUR\",\"value\":\"Blue\"},{\"name\":\"SEASON\",\"value\":\"CLAS\"},{\"name\":\"AVAILABLE_COLLECTION\",\"value\":true},{\"name\":\"AVAILABLE_HD\",\"value\":true},{\"name\":\"WEB_FABRIC\",\"value\":\"Silk\"},{\"name\":\"WEB_STYLE\",\"value\":\"woven silk tie\"},{\"name\":\"PRODUCT_ID\",\"value\":\"70111125B2S\"},{\"name\":\"WEB_CATEGORY\",\"value\":[\"business\",\"all-accessories\",\"ties-woven\",\"ties\",\"baynote\"]},{\"name\":\"GIFT_WRAP1\",\"value\":true},{\"name\":\"INT_NAME\",\"value\":\"Roberts Stripe Skinny Woven\"},{\"name\":\"USSLSTXB2C\",\"value\":\"4\"},{\"name\":\"RELATED_COLOR_VARIANTS\",\"value\":[\"70111125L3R\",\"70111125B2S\"]},{\"name\":\"WEB_PUBLISHTOWEB\",\"value\":true},{\"name\":\"COLOUR\",\"value\":\"Blue/Sky\"},{\"name\":\"SIZE\",\"value\":\"One Size\"},{\"name\":\"WEB_POSITION\",\"value\":1}],\"assets\":[]},\"price\":{\"value\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":13500,\"fractionDigits\":2},\"id\":\"d5c68bf0-1dde-4ac4-8471-46a07ac28a39\",\"country\":\"US\",\"channel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"}},\"quantity\":2,\"discountedPricePerQuantity\":[],\"distributionChannel\":{\"typeId\":\"channel\",\"id\":\"072f015f-cbad-4042-ba3b-52b4a863238b\"},\"state\":[{\"quantity\":5,\"state\":{\"typeId\":\"state\",\"id\":\"8a268083-d42b-490c-9068-4f562490d3d2\"}}],\"priceMode\":\"Platform\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":67500,\"fractionDigits\":2},\"lineItemMode\":\"Standard\"}],\"cartState\":\"Active\",\"totalPrice\":{\"type\":\"centPrecision\",\"currencyCode\":\"USD\",\"centAmount\":84000,\"fractionDigits\":2},\"country\":\"US\",\"customLineItems\":[],\"discountCodes\":[],\"inventoryMode\":\"None\",\"taxMode\":\"External\",\"taxRoundingMode\":\"HalfEven\",\"taxCalculationMode\":\"LineItemLevel\",\"refusedGifts\":[],\"origin\":\"Customer\",\"itemShippingAddresses\":[]}}\"";
		JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getDriver();
		executor.executeScript(String.format("localStorage.setItem('pink-shopper','%s')", shopper));
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
	
	public static void javascriptClickXpath(String xpath) {
		WebElement element = DriverFactory.getDriver().findElement(By.xpath(xpath));
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
		WebElement element = (new WebDriverWait(DriverFactory.getDriver(), 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
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
