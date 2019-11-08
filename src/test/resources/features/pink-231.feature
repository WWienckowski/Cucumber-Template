@PINK-231 @ShoppingBag @Sprint7 @incomplete
Feature: Edit size in Shopping Bag (UI & Integration)
  As a user, 
  I want to be able to change the size of a particular item in my bag, 
  so that I can have the correct size before I purchase

  Scenario: Title of your scenario
    Given the user is navigating to the shopping bag page
    When the shopping bag page loads
    Then there is a drop down menu in the product cell for <attribute>

  Scenario: 
    Given the user is on the shopping bag page
    When the user clicks on the <attribute> drop down
    Then the page will display the list of the in stock <attribute>
    And the user can toggle to change the <attribute> of that product

  Scenario: 
    Given the user is on the shopping bag page
    And the selected product comes in more than one <attribute>
    And only one <attribute> is in stock
    When the user clicks on the drop down
    Then only that <attribute> will display

  Scenario: 
    Given the user is navigating to the shopping bag page
    And the selected product comes in only one <attribute>
    When the product cell for the selected product loads
    When the user clicks on the drop down
    Then only that <attribute> will display

  Scenario: 
    Given the user is on the Shopping Bag page
    And the selected product has more than one <attribute> in stock
    And the user has a quantity of more than one in their shopping bag
    When the user changes the <attribute> value
    Then the quantity is the maximum available up to the quantity selected by the user

  Scenario: 
    Given the user is on the Shopping Bag page
    And the shopping bag contains a product with a selected quantity of more than 1
    When the user selects a size that has a stock value that is less than the previously selected quantity
    Then the new size is selected
    And the quantity is the maximum available for that size
    And the message 'Maximum quantity available' is displayed underneath the quantity selector
    And the message is red as per other error messages on the site

  Scenario: 
    Given the user is on the shopping bag page
    And the selected product has more than one size in stock
    When the user selects a different size
    Then the cart is updated with the new size selection
      | attribute   |
      | size        |
      | collar size |

  Scenario: 
    Given the user is in the shopping bag
    When the user changes the size of a product
    Then the page will display a white overlay as per design below
    And the page will display a loading icon as per design below

  Scenario: 
    Given the page is in the loading state (described immediately above)
    When the new product size has been added to cart
    Then the white overlay and loading icon are no longer displayed
