@PINK-134 @CheckoutUI
Feature: Order Confirmation Page (UI)
  The UI story for the order confirmation page

  Background: 
  	Given the user is on the confirmation page
  	
  Scenario: The user clicks the edit link in the registration section
When the user clicks the edit button by the email address on the registration section
Then the user is presented with empty fields for 'Select a Title', 'First name', 'Last name' and 'Email Address' (as per delivery section of checkout)
And the fields have ghost text to guide the users input

Scenario: The user registers via the confirmation page
And the registration contains the users name and email address
When the user enters a password in the password field in the registration section
And selects the 'Terms & Conditions' checkbox
Then the 'Create Account' button is black and clickable

Scenario: The user clicks a social media icon
When the user clicks on the social media icons
Then the a link for that icon opens in a new tab
