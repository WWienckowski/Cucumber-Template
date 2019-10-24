@PINK-229 @Sprint6 @ShoppingBag
Feature: Empty Shopping Bag Page (UI & Integration)
  Behaviour when user is in shopping bag page and there are no products in the bag. 
  Please note that this ticket includes the integration and requires that the user is 
  directed to the Home Page when they click on the ‘Continue Shopping’ button.

  Scenario: The empty shopping bag appears correctly
    Given the user does not have an item in their bag
    And the user is on the home page
    When the user CLICKS on the shopping bag icon in the header
    Then no product cells will display
    And the user is presented with the 'Your Shopping Bag is empty.' message
    And the 'Continue Shopping' button is displayed beneath the message
    And the Order Summary does not display
    And the Checkout buttons do not display
    And the Helpline component does not display

  Scenario: The empty shopping bag page appears when the last item is removed from the cart
    Given the user has a shirt in the bag
    And the user is on the bag page
    When the user clicks on the 'Remove Item' link in the bag
    Then the user is presented with the 'Your Shopping Bag is empty.' message
    And the 'Continue Shopping' button is displayed beneath the message
    And the Order Summary does not display
    And the Checkout buttons do not display
    And the Helpline component does not display

  Scenario: The 'Continue Shopping' button works correctly
    Given the user does not have an item in their bag
    And the user is on the home page
    When the user CLICKS on the shopping bag icon in the header
    And the user clicks on 'Continue Shopping'
    Then the user is re-directed to the 'Homepage'
