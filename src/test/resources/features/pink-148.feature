@PINK-148 @CheckoutUI @Sprint4
Feature: Tool Tip on password field (UI)
  In the password field on the Confirmation page,
  there must be a tool tip icon which when hovered over or clicked on states the minimum password requirements

	Background:
		Given the user is on the confirmation page

  Scenario: Tool tip icon is visible on the confirmation page
    Then there is a tool tip icon next to the 'Password*' field
    
  Scenario: The tool tip icon displays a message when hovered upon (Desktop)
  	Given the user hovers over the tool tip icon
  	Then the password message is displayed 
  	
  Scenario: The tool tip message dismisses when the user's mouse leaves the icon (Desktop)
  	Given the user hovers over the tool tip icon
  	And the password message is displayed
  	When the user moves the cursor off the tool tip icon
  	Then the password message is not displayed
  
  @mobile	
  Scenario: The tool tip icon displays a message when tapped (Mobile)
  	When the user taps on the tool tip icon
  	Then the password message is displayed
  		
  @mobile
  Scenario: The tool tip message dismisses when the user taps the screen (Mobile)	
  	Given the user taps on the tool tip icon
  	And the password message is displayed
  	When the user taps anywhere on the screen
  	Then the password message is not displayed
