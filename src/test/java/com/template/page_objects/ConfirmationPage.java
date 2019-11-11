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
	

}
