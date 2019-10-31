@PINK-161 @Checkout @Sprint7
Feature: View Shopping Bag from Checkout (Services)
  The user must be able to review what products are in their shopping bag, 
  attributes for each product and any gift messaging, all without leaving the Checkout page

  Background: 
    Given the user has a shirt in the bag

  Scenario: The checkout page's bag summary displays correctly
    Given the user is on the checkout page
    And the Shopping Bag control is minimised
    When the user expands the Shopping Bag summary
    Then the user's products are displayed in the Shopping Bag summary
    And the primary image for each product is displayed in the Shopping Bag summary
    And each applicable chosen attribute is displayed for each product
      | Colour | Fit     | Sleeve            | Size | Collar          |
      | COLOUR | WEB_FIT | SKU_SLEEVE_LENGTH | SIZE | SKU_COLLAR_SIZE |

  Scenario: A gift wrapped item shows correctly on checkout
    Given the shirt has been gift wrapped
    And the user is on the checkout page
    When the user expands the Shopping Bag summary
    Then the Gift Wrap box is displayed
    And the Gift Wrap box is checked and greyed out

  Scenario: An item with a gift message shows correctly on checkout
    Given the shirt has a gift message
    Given the user is on the checkout page
    When the user expands the Shopping Bag summary
    Then the gift message field is displayed with the gift message
    And the Bag Summary gift message field is greyed out

  Scenario: The gift message field is not shown for non-gift wrapped items
    Given the user is on the checkout page
    When the user expands the Shopping Bag summary
    Then the Gift Wrap check box is not displayed
    And the Bag Summary gift message field is not displayed

  Scenario: The edit shopping bag link on checkout takes the user to the shopping bag page
    Given the user is on the checkout page
    When the user expands the Shopping Bag summary
    And the user clicks on 'Edit Shopping Bag' link
    Then the user is re-directed to the "Shopping Bag"
