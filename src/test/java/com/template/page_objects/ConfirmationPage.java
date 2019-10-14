package com.template.page_objects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import helpers.Click;
import helpers.Move;
import helpers.Screenshot;
import helpers.Verify;
import io.cucumber.core.api.Scenario;

public class ConfirmationPage {
	WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 15);;
	Scenario scenario = DriverFactory.getScenario();
	
	public ConfirmationPage() {
		 PageFactory.initElements(DriverFactory.getDriver(), this);
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
			Click.javascriptClick(link);
			
			List<String> browserTabs = new ArrayList<String> (DriverFactory.getDriver().getWindowHandles());
			if (browserTabs.size()>1) {
				DriverFactory.getDriver().switchTo().window(browserTabs.get(1));
				scenario.write("Expecting: "+linkHref+" \n Found: "+DriverFactory.getDriver().getCurrentUrl());
				if (!DriverFactory.getDriver().getCurrentUrl().matches(linkHref)); {
					noMatch++;
				}
				DriverFactory.getDriver().close();
				DriverFactory.getDriver().switchTo().window(browserTabs.get(0));
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
		WebElement fieldset = DriverFactory.getDriver().findElement(By.xpath("//fieldset[@class='form-fieldset']"));
		Verify.checkInputFieldPlaceholders(fieldNames, fieldset);
	}

	public void checkLoginHeader() {
		String legend = DriverFactory.getDriver().findElement(By.xpath("//div[@class='login-header']/legend")).getText();
		String email = DriverFactory.getDriver().findElement(By.xpath("//div[@class='login-header']/div[@class='login-email']")).getText();
		scenario.write("Name: "+legend+"\nEmail: "+email);
	}

	public void enterPassword() {
		WebElement fieldset = DriverFactory.getDriver().findElement(By.xpath("//fieldset[@class='form-fieldset']"));
		password.sendKeys("Test1234!");
		passwordConfirm.sendKeys("Test1234!");
		Screenshot.includeScreenshotOfElement(fieldset);
	}

	public void hoverOnTooltip() {
		Move.HoverOn(toolTipIcon);
		
	}

	public void tooltipMessageDisplayed(String message) {
		scenario.write("Tool tip text: "+tooltip.getText());
		Assert.assertTrue(message.equals(tooltip.getText()));
		Assert.assertTrue(tooltip.isDisplayed());
		scenario.write("Tool tip is visible");
		Screenshot.includeScreenshot();
	}

	public void tooltipMessageDismissed() {
		Assert.assertFalse(tooltip.isDisplayed());
		scenario.write("Tool tip is not visible");
		Screenshot.includeScreenshot();
	}

	public void tapToolTip() {
		WebElement element = DriverFactory.getDriver().findElement(By.tagName("pink-confirmation-create-account"));
		Move.scrollToElement(element);
		Actions action = new Actions(DriverFactory.getDriver());
		action.pause(2000);
		action.click(toolTipIcon);
		action.pause(2000);
		action.perform();
		Screenshot.includeScreenshot();
	}

	public void enterPaymentDetails() {
		WebElement button = DriverFactory.getDriver().findElement(By.xpath("//button[text()='continue']"));
		Click.javascriptClick(button);
	}
}
