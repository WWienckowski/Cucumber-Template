@PINK-314 @PLP @Sprint7 @incomplete
Feature: PLP Filters UI & Integration Desktop
  Users must be able to interact with the filters on the PLPs,
  for example by adding or removing filters, opening the filter category accordion, clearing all filters

  Background: 
    Given the user is on the PLP(shirts) page

  Scenario: PLP initially displays with all menus closed
    Then all attribute accordions will be minimised
    And the arrows on the attribute accordions will be pointing down

  Scenario: Attribute menus expand when clicked
    When the customer clicks the expand icon for any attribute
    Then the attribute filter options will be displayed
    And the arrow on the accordion will be pointing up

  Scenario: Expanded attribute menus minimise when clicked
    Given the attribute accordion is expanded
    When the user clicks that attribute accordian
    Then the attribute accordian will minimise

  Scenario: Selecting an attribute updates the products and makes the selection bold
    Given the attribute accordion is expanded
    When the user clicks on an available attribute
    Then the attribute text will be bold
    And the attribute bullet point is bold/circled as per designs
    And the PLP updates to show the products meeting the attribute filter

  Scenario: Unavailable attributes change nothing
    Given the attribute accordion is expanded
    When the user clicks on an unavailable attribute
    Then no action will occur

  Scenario: Clicking a selected attribute removes it from the filter
    Given the attribute accordion is expanded
    And the user clicks on an available attribute
    When the user clicks on that selected attribute again
    Then the attribute reverts back to its normal styling
    And the PLP updates to show the original products

  Scenario: A 'Clear All' button appears when any attributes are selected
    Given the attribute accordion is expanded
    When the user clicks on an available attribute
    Then a 'Clear All' link is present

  Scenario: The 'Clear All' button removes all filters when clicked
    Given the attribute accordion is expanded
    And the user clicks on an available attribute
    And a 'Clear All' link is present
    When the user clicks the 'Clear All' link
    Then all the filters should be removed
    And the PLP updates to show the original products
    And a 'Clear All' link is not present
