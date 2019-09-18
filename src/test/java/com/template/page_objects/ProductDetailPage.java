package com.template.page_objects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

public class ProductDetailPage {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	private String productUrl;
	private String productColour;
	
	@FindAll( {
		@FindBy(className = "color-selector_item")
	} )
	private List<WebElement> colourSwatches;
		
		public ProductDetailPage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
			 this.driver = driver;
			 this.wait = wait;
			 this.scenario = scenario;
			 PageFactory.initElements(driver, this);
			 }
		
		public void confirmUrl() {
		    try {
		    	wait.until(ExpectedConditions.urlContains(productUrl));
		    } catch (Exception e) {
		    	Assert.fail("URL does not match \""+productUrl+"\".");
		    }
		}
		
		// Count the color swatches on the PDP page and compare them to the expected number
		public void swatchCount(Integer expectedSwatches) {
			int actualSwatches = colourSwatches.size();
			
			scenario.write(expectedSwatches+" swatches expected. "+actualSwatches+" swatches found.");
			if (expectedSwatches != actualSwatches) {
				Assert.fail("Incorrect number of swatches.");
			} else {
				scenario.write("Correct number of swatches.");
			}
			
		}
		
		// Click on the colour swatch image given by the scenario
		public void clickColour(String colour) {
			scenario.write("Clicking on "+colour+" colour swatch");
			try {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt=\'"+colour+"\']"))).click();
			} catch (Exception e ) {
				scenario.write("Unable to click");
				Assert.fail();
			}
			this.productColour = colour;
		}
		
		//Confirm that a colour label matches the selected colour
		public void confirmLabel(String labelText) {
			scenario.write("Confirming that colour label has changed to "+productColour);
			try {
				wait.until(ExpectedConditions.textToBe(By.xpath
						("/html/body/app-root/pink-detail-layout/div/div[2]/pink-product-options/div/div/div[2]/pink-product-color/div[1]/span"),
						labelText));
			} catch (Exception e) {
				Assert.fail("Incorrect label or timeout on page.");
			}
			scenario.write("Label confrimed.");
			
		}
		
		//Confirm that the currently displayed colour's swatch is outlined
		public void confirmOutline() {
			wait.until(ExpectedConditions.attributeContains(By.xpath
					("//*[@alt=\'"+productColour+"\']/.."), "class", "is-active"));
			scenario.write("Outline confirmed.");
		}
}
