package com.template.page_objects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

public class CheckoutPage {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	
	@FindAll( {  // A list of all fieldsets on the checkout page
		@FindBy(className = "checkout_form-fieldset")
	} ) private List<WebElement> checkoutFieldsets;
	
	@FindBy(className = "form-control_flag-text") private WebElement flagText;
	
	@FindBy(className = "form-control_flag-image") private WebElement flagImage;
	
	@FindAll( {  // A list of titles available from the userTitle dropdown list
		@FindBy(xpath = "//*[@id=\"userTitle\"]/option")
	} ) private List<WebElement> userTitles;
	
	@FindAll( {
		@FindBy(xpath = "//input[@name=\"deliverySpeed\"]")
	}) private List<WebElement> deliverySpeedRadios;
	
	@FindAll( {
		@FindBy(className = "checkout_delivery-option")
	}) private List<WebElement> deliveryOptions;
	
	
	public CheckoutPage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
		 PageFactory.initElements(driver, this);
		 }
	
	public void checkFields(List<String> fieldNames, int fieldsetIndex) {
		WebElement fieldset = checkoutFieldsets.get(fieldsetIndex);
		List<WebElement> fields = fieldset.findElements(By.xpath("//input"));
		if (fields.size() != fieldNames.size()) {
			Assert.fail(fieldNames.size()+" fields expected, "+fields.size()+" fields found");
		}
		int notFound = 0;
		for (String fieldName : fieldNames) {
			scenario.write("\nLooking for: "+fieldName);
			try {
				driver.findElement(By.cssSelector("input[placeholder=\'"+fieldName+"\']")); 
				} catch (Exception e) {
					try {
						driver.findElement(By.xpath("//*[contains(text(), \'"+fieldName+"\')]"));
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

	public void locationIs(String expectedLocation) {
		String actualLocation = flagText.getText();
		scenario.write(expectedLocation+" is expected");
		if (expectedLocation.matches(actualLocation)) {
			scenario.write("Location Confirmed");
		} else {
			scenario.write(actualLocation+" was found");
			Assert.fail("Incorrect Location");
		}
	}

	public void checkFlag(String flag) {
		String ukFlag = "/assets/samples/flag-uk.png";
		String usFlag = "/assets/samples/flag-us.png";
		
		flag = flag.matches("British") ? ukFlag : usFlag;
		boolean passed = flagImage.getAttribute("src").contains(flag)
				? true : false;
		
		if (passed == true) {
			scenario.write("Correct Icon");
		} else {
			Assert.fail("Incorrect Icon");
		}
	}
	
	public void checkTitles() {
		if (userTitles.size()==0) {
			Assert.fail("No title options found");
		} else {
			scenario.write("Available titles:");
			for (WebElement title : userTitles) {
				scenario.write(title.getText());
			}
		}
	}

	public void selectedRadioCheck(String selection) {
		for (WebElement radio : deliverySpeedRadios) {
			if (radio.isSelected()) {
				String radioText = radio.findElement(By.xpath("following-sibling::span")).getText();
				if (radioText.contains(selection)) {
					scenario.write(radioText+" contains "+selection);
				} else {
					scenario.write(radioText+" does not contain "+selection);
					Assert.fail(radioText+" does not contain "+selection);
				}
			}
			
		}
		
		
	}

	public void checkDeliveryOptions(List<String> optionNames) {
		ArrayList<String> displayedNames = new ArrayList<String>();
		for (WebElement option : deliveryOptions) {
			displayedNames.add(option.getText());
		}
		int i;
		int noMatch = 0;
		for (i=0; i<optionNames.size(); i++) {
			scenario.write("\nExpected: "+optionNames.get(i)+"\nDisplaying: "+displayedNames.get(i));
			if (displayedNames.get(i).matches(optionNames.get(i))) {
				scenario.write("MATCH");
			} else {
				scenario.write("DO NOT MATCH");
				noMatch++;
			}
		}
		if (noMatch > 0) {
			Assert.fail("Expected names do not match displayed names");
		}
	}

}
