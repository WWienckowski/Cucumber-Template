package com.template.page_objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverFactory;
import helpers.Cart;
import helpers.Click;
import helpers.Move;
import helpers.Verify;
import io.cucumber.core.api.Scenario;

public class PLPage {
	Scenario scenario = DriverFactory.getScenario();
	
	// xpath variables for individual listing components
	String masterImageXpath = ".//a[not(contains(@class, 'color-option'))]//img";
	String colorSwatchXpath = ".//a[contains(@class, 'color-option')]//img";
	String selectedColorSwatchXpath = ".//a[@class='color-option is-active']//img";
	String colorVariantNameXpath = ".//span[@class='product-color']";
	
	
	// xpath variables for product facets
	String filterOptionXpath = ".//pink-listing-facet-item";
	String upArrowXpath = ".//img[@src='/assets/samples/up-arrow.svg']";
	
	public PLPage() {
		 PageFactory.initElements(DriverFactory.getDriver(), this);
		 }
	
	@FindAll ({
		@FindBy(tagName = ("pink-product-listing-item"))
	}) private List<WebElement> products;
	
	@FindAll ({
		@FindBy(xpath = "//span[@class='product-color']")
	}) private List<WebElement> colorNames;
	
	@FindAll ({
		@FindBy(xpath = "//pink-listing-facet[div]")
	}) private List<WebElement> facets;
	
	@FindAll ({
		@FindBy(xpath = "//pink-listing-facet//div[@class='container open']")
	}) private List<WebElement> openFacets;
	
	@FindAll ({
		@FindBy(xpath = "//pink-listing-facet//div[@class='container']")
	}) private List<WebElement> closedFacets;
	
	@FindAll ({
		@FindBy(xpath = "//pink-listing-facet//img[@src='/assets/samples/down-arrow.svg']")
	}) private List<WebElement> downArrows;
	
	@FindAll ({
		@FindBy(xpath = "//pink-listing-facet-item//div[span[@class='choice']]")
	}) private List<WebElement> filterOptions;
	
	@FindAll ({
		@FindBy(xpath = "//pink-listing-facet-item//div[span[@class='choice selected']]")
	}) private List<WebElement> activeOptions;
	
	@FindAll ({
		@FindBy(className = "product-name")
	}) private List<WebElement> productNames;

	public void checkProductsHaveImagesAndSwatches() {
		int listed = products.size();
		scenario.write(Integer.toString(listed)+" products listed");
		for (WebElement product : products) {
			List<WebElement> masterImage = product.findElements(By.xpath(masterImageXpath));
			Assert.assertNotEquals("Each product does not have a master image", 0, masterImage.size());
			List<WebElement> swatches = product.findElements(By.xpath(colorSwatchXpath));
			Assert.assertNotEquals("Each product does not have at least one color swatch", 0, swatches.size());
			scenario.write(Integer.toString(swatches.size())+" swatches found for item "+(products.indexOf(product)+1));
			Assert.assertTrue("Swatches were not beneath the master image", Verify.isXaboveY(masterImage.get(0), swatches.get(0)));
			scenario.write("Swatches display beneath the master image");
		}
	}

	public void checkProductsHaveColorNames() {
		Assert.assertEquals("Each product does not have a color variant name", products.size(), colorNames.size()); 
		scenario.write("Each product has a color variant name");
	}

	public Map<String,Object> selectColorSwatch() {
		Map<String,Object> data = new HashMap<String,Object>();
		// look through each product cell to find a product with multiple swatches
		for (WebElement product : products) {
			List<WebElement> swatches = product.findElements(By.xpath(".//a[contains(@class, 'color-option')]"));
			if (swatches.size() > 1) {
				//pick a swatch that isn't active
				scenario.write("Found a product with more than one swatch");
				String colorName = product.findElement(By.xpath(colorVariantNameXpath)).getText();
				for (WebElement swatch : swatches) {
					if (swatch.getAttribute("class").matches("color-option")) {
						Click.javascriptClick(swatch);
						scenario.write("Selected inactive swatch");
						//return the index of the product and swatch
						data.put("product", products.indexOf(product));
						data.put("swatch", swatches.indexOf(swatch));
						data.put("color", colorName);
						break;
					}
				}
				if (data !=null) {
					break;
				}
			}
		}
		return data;
	}

	public void checkSwatchMatchesImage(int productIndex, int swatchIndex) {
		WebElement product = products.get(productIndex);
		String masterImage[] = product.findElement(By.xpath(masterImageXpath)).getAttribute("src").split("/");
		String masterId = Arrays.asList(masterImage).get(8);
		String[] swatch = product.findElement(By.xpath(selectedColorSwatchXpath)).getAttribute("src").split("/");
		String swatchId = Arrays.asList(swatch).get(8);
		Assert.assertEquals("Master image and color swatch do not match",
				masterId, swatchId);
		scenario.write("Master image and color swatch have same product ID: "+masterId);
	}

	public void checkColorName(int productIndex, String originalColor) {
		String newColor = products.get(productIndex).findElement(By.xpath(colorVariantNameXpath)).getText();
		Assert.assertNotEquals("Color Name did not change", originalColor, newColor);
		scenario.write("Original color was: "+originalColor+", New color is: "+newColor);
	}
	
	

	public void clickAttribute(int i) {
		Click.javascriptClick(facets.get(i).findElement(By.xpath(".//div//div")));
		Move.idleForX(1000);
	}

	public void checkForFilterOptions(int i) {
		scenario.write(Integer.toString(openFacets.size())+" open accordians");
		for (WebElement facet : openFacets) {
			int filterOptions = facet.findElements(By.xpath(filterOptionXpath)).size();
			Assert.assertNotEquals("No options shown", 0, filterOptions);
			scenario.write
			(Integer.toString(filterOptions)+" filter options found in accordian "+(openFacets.indexOf(facet)+1));	
		}
		
	}

	public void checkForUpArrow(int i) {
		Assert.assertTrue(facets.get(i).findElement(By.xpath(upArrowXpath)).isDisplayed());
		scenario.write("Up arrow is displayed for the open attribute accordian");
	}

	public void checkAllAccordiansMinimised() {
		Assert.assertEquals("Not all accordians are minimised",
	    		facets.size(), closedFacets.size());
	    scenario.write("All accordians are minimised");
	}

	public void checkAllArrowsPointDown() {
		Assert.assertEquals("Not all arrows are pointing down",
	    		facets.size(), downArrows.size());
	    scenario.write("All arrows are pointing down");
	}

	public void selectAttribute(int i) {
		openFacets.get(i).findElement(By.xpath(".//pink-listing-facet-item//div//span")).click();
		Move.idleForX(500);
	}

	private String[] getPLPFilters() {
		String[] queries = StringUtils.substringsBetween(Cart.getNetworkLog(), "filter.query", ",");
		String query = queries[queries.length-1];
		String[] filters = StringUtils.substringsBetween(query, ":%22", "%22");
		return filters;
	}

	public void checkActiveAttributeText() {
		for (WebElement option : activeOptions) {
			String font = option.findElement(By.xpath(".//span")).getCssValue("font-family").toLowerCase();
			Assert.assertTrue("Active attribute is not bold", font.contains("heavy"));
			scenario.write("Active attribute is bold");
		}
	}

	public void checkActiveAttributeBullets() {
		for (WebElement option : activeOptions) {
			String border = option.findElement(By.xpath(".//div")).getCssValue("borderWidth").toLowerCase();
			Assert.assertEquals("Active attribute bullet border is not bold", border, "2px");
			scenario.write("Active attribute bullet border is bold");
		}
	}

	public void checkInactiveFilterOptionSyle() {
		WebElement option = openFacets.get(0).findElement(By.xpath(filterOptionXpath));
		
		String font = option.findElement(By.xpath(".//div//span")).getCssValue("font-family").toLowerCase();
		Assert.assertFalse("Text is still bold", font.contains("heavy"));
		scenario.write("Text has returned to normal");
		
		String border = option.findElement(By.xpath(".//div//div")).getCssValue("borderWidth").toLowerCase();
		Assert.assertEquals("Bullet border is still heavy", border, "1px");
		scenario.write("Bullet border has returned to normal");
	}

	public void updatesForFilters(List<String> originalNames) {
		String[] filters = getPLPFilters();
		scenario.write("Filtering by:");
		for (String filter: filters) {
			scenario.write(filter);
		}
		Assert.assertEquals("Incorrect number of filters are active", filters.length-1, activeOptions.size());
		Assert.assertFalse("Products did not update", originalNames.equals(getDisplayedProducts()));
		scenario.write("Products have been updated.");
	}

	public void checkNoActiveFilters() {
		Move.idleForX(1000);
		Assert.assertEquals("There are still active filter options", 0, activeOptions.size());
		Assert.assertEquals("The page is still filtering results", 1, getPLPFilters().length);
		scenario.write("No active filters");
	}

	public List<String> getDisplayedProducts() {
		List<String> originalProductNames = new ArrayList<String>();
		for (WebElement productName : productNames) {
			originalProductNames.add(productName.getText());
		}
		return originalProductNames;
	}

	public void displayOriginalProducts(List<String> originalNames) {
		Assert.assertTrue("The unfiltered products are not displayed", originalNames.equals(getDisplayedProducts()));
		scenario.write("The unfiltered products are displayed");
	}

}
