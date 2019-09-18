package com.template.page_objects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.Scenario;

public class Global {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	
	public String baseUrl = "http://pink-develop.s3-website.us-east-2.amazonaws.com/";
	
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

	public void clickOnByText(String element) {
		try {
			scenario.write("Clicking: "+element);
			driver.findElement(By.xpath("//*[contains(text(), \'"+element+"\')]")).click();
		} catch (Exception e) {
			scenario.write("Unable to click "+element);
			Assert.fail();
		}
		scenario.write("Success");
	}

	public void includeScreenshot() {
		scenario.write("Including a screenshot with this step for manual review");
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

	public void checkForElementByText(String element) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), \'"+element+"\')]")));
	}
}
