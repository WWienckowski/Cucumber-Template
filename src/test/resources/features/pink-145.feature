@PINK-145 @CheckoutUI
Feature: Collect in Pink Store (UI)
  The UI story for the Collect in Pink store fulfilment option

  Background: 
    Given the user is on the checkout page
    And the user clicks on 'Collect in a Pink Store'

  Scenario: The Collect in a Pink Store delivery option displays options to find a store or use location
    Then the 'Collect in a Pink Store' button turns black
    And the 'Find store by postcode or town' field is displayed beneath it as per wireframe
    And there is a clickable black arrow next to the 'Find store by postcode or town' field
    And beneath the 'Find store by postcode or town' field there is text that says 'or use my location'
    And 'use my location' is bold and underlined

  Scenario: A list of stores is displayed and each has a Show on map link
    And a list of Pink stores is displayed
    And each Pink store cell has a clickable ‘Show on map’ link

  Scenario: Each store has a 'See store details' link and the Contact for order component is displayed
    And each Pink store cell has a clickable ‘See store details' link that is underlined
    And there will be a 'Contact for Order' component

  Scenario: The map link displays a map component
    Given a list of Pink stores is displayed
    When the user clicks on 'See map view'
    Then the system will load the map component

  Scenario: Selecting a store displays the address in a 'Picking up' section
    Given the user clicks on 'Pick-up here'
    Then the 'Picking up' summary and 'Edit' link are displayed

  Scenario: The user can navigate back to the store list after picking a store
    Given the user clicks on 'Pick-up here'
    And the 'Picking up' summary and 'Edit' link are displayed
    When the user clicks on the 'Edit' link in the 'Picking up' summary
    Then a list of Pink stores is displayed
