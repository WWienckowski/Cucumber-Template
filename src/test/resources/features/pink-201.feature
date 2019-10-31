@PINK-201 @Checkout @Sprint7
Feature: Create Loqate Service for UK Predictive Address Lookup
  Set up Loqate service to enable users to search and choose their preferred delivery address.

  Scenario: The predictive address will not suggest an address if less than 4 characters have been entered
    Given the user is in the 'Enter postcode or address' field
    When the user enters 3 or fewer characters
      | 123 |
    Then no address suggestions will display

  Scenario: The predictive address suggests an address after entering 4 characters
    Given the user is in the 'Enter postcode or address' field
    When the user enters 4 or more characters
      | 1234 |
    Then the site will suggest addresses based on the user's entry

  Scenario: Selecting a suggested address expands and populates the address fields
    Given the user has entered text into the 'Enter postcode or address' field
      | entry |
    When the user selects an address
      | address |
    Then the delivery address fields are expanded
      | Address 1* |
      | City/Town* |
      | County*    |
      | Postcode*  |
    And each field is populated with the selected address value
      | Address 1 value |
      | City value      |
      | County salue    |
      | Postcode value  |
