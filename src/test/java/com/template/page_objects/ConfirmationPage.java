package com.template.page_objects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.template.Helpers;
import com.template.stepdefs.Hooks;

import cucumber.api.Scenario;

public class ConfirmationPage {
	WebDriver driver;
	WebDriverWait wait;
	Scenario scenario;
	
	public ConfirmationPage(WebDriver driver, WebDriverWait wait, Scenario scenario) {
		 this.driver = driver;
		 this.wait = wait;
		 this.scenario = scenario;
		 PageFactory.initElements(driver, this);
		 }
	
	@FindAll( { // A list of the social media links
		@FindBy(xpath = ("//div[@class='social']/a"))
	}) private List <WebElement> socialLinks;
	
	@FindBy(xpath = ("//input[@placeholder='Password*']")) WebElement password;
	
	@FindBy(xpath = ("//input[@placeholder='Re-enter Password*']")) WebElement passwordConfirm;
	
	@FindBy(xpath = ("//input[contains(@placeholder,'Password')]/following-sibling::"
			+ "a[@class='form-control_tooltip']/*[name()='svg']")) WebElement toolTipIcon;
	
	@FindBy(xpath = ("//div[@class='tooltip']")) WebElement tooltip;
	
	public void clickSocialLinks() {
		int noTab = 0;
		int noMatch = 0;
		for (WebElement link : socialLinks) {
			String linkHref = link.getAttribute("href");
			scenario.write("Expecting a new tab with URL: "+linkHref);
			if (link.getAttribute("target")!="_blank") {
				scenario.write("Link won't open in new tab, skipping link");
				noTab++;
				continue;
			}
			Hooks.manager.global.javascriptClick(link);
			
			List<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
			if (browserTabs.size()>1) {
				driver.switchTo().window(browserTabs.get(1));
				scenario.write("Expecting: "+linkHref+" \n Found: "+driver.getCurrentUrl());
				if (!driver.getCurrentUrl().matches(linkHref)); {
					noMatch++;
				}
				driver.close();
				driver.switchTo().window(browserTabs.get(0));
			}
			
		}
		if (noTab>0 && noMatch>0) {
			Assert.fail(noTab+" links did not open new tabs and "+noMatch+" URLs did not match their links");
		} else if (noTab>0) {
			Assert.fail(noTab+" links did not open new tabs");
		} else if (noMatch>0) {
			Assert.fail(noMatch+" URLs did not match their links");
		}
	}

	public void checkRegistrationFields(List<String> fieldNames) {
		WebElement fieldset = driver.findElement(By.xpath("//fieldset[@class='form-fieldset']"));
		Hooks.manager.global.checkInputFieldPlaceholders(fieldNames, fieldset);
	}

	public void checkLoginHeader() {
		String legend = driver.findElement(By.xpath("//div[@class='login-header']/legend")).getText();
		String email = driver.findElement(By.xpath("//div[@class='login-header']/div[@class='login-email']")).getText();
		scenario.write("Name: "+legend+"\nEmail: "+email);
	}

	public void enterPassword() {
		WebElement fieldset = driver.findElement(By.xpath("//fieldset[@class='form-fieldset']"));
		password.sendKeys("Test1234!");
		passwordConfirm.sendKeys("Test1234!");
		Hooks.manager.global.includeScreenshotOfElement(fieldset);
	}

	public void hoverOnTooltip() {
		Helpers.HoverOn(toolTipIcon, driver);
		
	}

	public void tooltipMessageDisplayed(String message) {
		scenario.write("Tool tip text: "+tooltip.getText());
		Assert.assertTrue(message.equals(tooltip.getText()));
		Assert.assertTrue(tooltip.isDisplayed());
		scenario.write("Tool tip is visible");
		Hooks.manager.global.includeScreenshot();
	}

	public void tooltipMessageDismissed() {
		Assert.assertFalse(tooltip.isDisplayed());
		scenario.write("Tool tip is not visible");
		Hooks.manager.global.includeScreenshot();
	}

	public void tapToolTip() {
		WebElement element = driver.findElement(By.className("right-column"));
		
		Hooks.manager.global.scrollToElement(element);
		Actions action = new Actions(driver);
		action.pause(2000);
		action.click(toolTipIcon);
		action.pause(2000);
		action.perform();
		Hooks.manager.global.includeScreenshot();
	}

	public void enterPaymentDetails() {
		WebElement button = driver.findElement(By.xpath("//button[text()='continue']"));
		Hooks.manager.global.javascriptClick(button);
	}
}
