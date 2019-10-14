@PINK-135 @CheckoutUI @ignore
Feature: Checkout Header (UI)
  Users will be provided with a simplified header when in the checkout

  Background: 
    Given the user is on the checkout page

  Scenario: The header appears correctly
    Then the header should appear as per designs

  Scenario: The user clicks the Pink Shirtmaker logo in the header
    When the user clicks on Pink Shirtmaker logo in checkout header
    Then the user is re-directed to the Homepage

  Scenario: The user clicks the 'Continue Shopping' link in the header
    When the user clicks on 'Continue Shopping'
    Then the user is re-directed to the Bag
