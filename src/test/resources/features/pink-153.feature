@PINK-153 @CheckoutUI
Feature: Guest Checkout Login Bar (UI)
  This is the UI story for the login bar that appears below the header on the Checkout page.
  PLEASE NOTE that the login bar appears ABOVE the Shopping Bag header when the user is on mobile.
  This is because the Shopping Bag header is sticky and will need to move down the page.

	Background:
		Given there are products in the Shopping Bag
    And the user is on the checkout page
  
  Scenario: The login bar appears on the desktop site
  	Then the login bar is displayed beneath the header as per designs
  
  Scenario: The cursor becomes a pointing finger over 'login'
  	When the user hovers over 'login'
  	Then the cursor becomes a finger	 
 
  @mobile	
  Scenario: The login bar appears on the mobile site
  	Then the login bar is displayed above Shopping Bag summary bar
  	