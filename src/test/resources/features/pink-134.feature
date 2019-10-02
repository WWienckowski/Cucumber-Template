@PINK-134 @CheckoutUI
Feature: Order Confirmation Page (UI)
  The UI story for the order confirmation page

  Background: 
  	Given the user is on the confirmation page
  	
  Scenario: The user clicks the edit link in the registration section
		When the user clicks on 'Edit' link
		Then the user is presented with empty fields with the appropriate placeholder text
			| Select a Title |
			| First name* |
			| Last name* |
			| Email Address* |

Scenario: The user registers via the confirmation page
		Given the registration contains the users name and email address
		When the user enters a password in the password field in the registration section
		And the user clicks on the 'Terms and Conditions' checkbox
		Then the 'Create Account' button is not disabled

