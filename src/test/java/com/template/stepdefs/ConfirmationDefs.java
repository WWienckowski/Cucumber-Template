package com.template.stepdefs;

import java.util.List;

import com.template.page_objects.ConfirmationPage;

import driver.SharedDriver;
import helpers.Move;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ConfirmationDefs {
	private ConfirmationPage confirmation;
	
	public ConfirmationDefs(SharedDriver driver, ConfirmationPage confirmation) {
		this.confirmation = confirmation;
	}

}
