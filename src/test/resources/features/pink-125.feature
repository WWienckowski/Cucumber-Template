@PINK-125 @CheckoutUI
Feature: Ship to Address (UI)
  UI functionality relating to the 'Ship to Address' section of checkout.
  We will build it as per the UK site design for now, 
  this can then be amended later for the US and International sites

	Background:
		Given I put a cart into local storage
		And the user is on the checkout page

  Scenario: UK Ship to Address fields display correctly
    When the Ship to Address section of the UK checkout page loads
    Then the Shipping Address section will display all fields
      | First name*           |
      | Last name*            |
      | Search for address... |

  Scenario: User selects a title
    And the user is in the Ship to Address section of the checkout page
    When the user clicks the 'Select a Title'
    Then a drop down list of title options will appear

  Scenario: 'UK' and British flag display on checkout page
    And the user is in the Ship to Address section of the checkout page
    Then the country will be UK
    And the icon will be the British flag

  Scenario: All fields display for manual address entry
    When the user selects the 'enter address manually' link underneath the address lookup field
    Then the Shipping Address section will display all fields
      | Select a Title |
      | First name*    |
      | Last name*     |
      | Address 1*     |
      | City/Town*     |
      | County*        |
      | Postcode*      |
      | Country        |
    And a 'Search for Address' link should appear below the last field
