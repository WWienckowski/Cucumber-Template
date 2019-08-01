package com.template.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Details {
	WebDriver driver;
	WebDriverWait wait;
		
		public Details(WebDriver driver, WebDriverWait wait) {
			 this.driver = driver;
			 this.wait = wait;
			 PageFactory.initElements(driver, this);
			 }
}
