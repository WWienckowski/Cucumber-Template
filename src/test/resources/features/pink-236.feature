@PINK-236 @ShoppingBag @Sprint6 @parallel
Feature: Mini Shopping Bag (UI)
  The user must be able to view the products in their shopping bag by hovering over 
  or tapping on the Shopping bag icon in the header. The product cells in the mini shopping bag 
  are almost identical to those in the shopping bag page.
  If the user does not interact with the mini shopping bag then it must disappear after 5 seconds.

  Background: 
    Given there are products in the Shopping Bag
    And the user is on the home page

  Scenario: The mini shopping bag contains the correct elements
    When the user expands the mini shopping bag
    Then the product cells will contain the correct elements as per designs
      | Name              |
      | Price             |
      | Image             |
      | Colour            |
      | Fit               |
      | Sleeve Length     |
      | Size Selector     |
      | Quantity Selector |
      | Remove Item Link  |

  Scenario: The mini shopping bag expands when hovered over
    When the user hovers over the Shopping Bag icon
    Then the mini shopping bag is expanded

  Scenario: The mini shopping bag is scrollable
  	Given the user expands the mini shopping bag
  	Then the user can scroll up and down over the mini shopping bag
  
  Scenario: The mini shopping bag scroll bar is independent of the main page's scrollbar
  	Given the user expands the mini shopping bag
  	Then the user can scroll up and down over the main page without scrolling the mini bag.

  Scenario: The mini shopping bag has an active checkout button
    When the user expands the mini shopping bag
    Then there is a 'checkout' button in the mini bag as per designs
    And the mini bag's 'checkout' button is active

  Scenario: The mini shopping bag has an active view bag button
    When the user expands the mini shopping bag
    Then there is a 'view bag' button in the mini bag as per designs
    And the mini bag's 'view bag' button is active

  Scenario: The mini shopping bag is dismissable
    Given the user expands the mini shopping bag
    When the user clicks anywhere off the the mini shopping bag
    Then the mini shopping bag is minimised

  Scenario: The mini shopping bag will automatically dismiss after 5 seconds of inactivity
    Given the user expands the mini shopping bag
    When the user has not clicked/tapped on any element within the mini shopping bag for 5 seconds
    Then the mini shopping bag is minimised

  Scenario: The shopping bag icon takes the user to the Shopping Bag page
    When the user CLICKS on the shopping bag icon in the header
    Then the user is re-directed to the "Shopping Bag"
