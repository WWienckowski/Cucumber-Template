@PINK-155 @Checkout @Sprint8 @incomplete
Feature: Delivery Address - Field Validation (Services)
  The system must alert the customer if any of their entries in the delivery address fields are invalid.
  NOTE: in-line validation should wait until field is not in focus to show error messages. 
  Current website shows error messages while typing.

  Background: 
    Given there are products in the Shopping Bag
    And the user is on the checkout page
    And the user is in the Ship to Address section
    And the user expands the manual address entry

  #Scenario Outline: Valid entries show in the <field> field (UK)
  #Given the user is on the UK site
  #When the user has entered a valid entry into the <field> field
  #| <entry> |
  #Then user's entry is displayed in the field after leaving that field
  #| <entry> |
  #Examples:
  #| field | entry |
  #| First name* | Test |
  #| Last name* | Test |
  #| Address 1* | 123 Test Lane |
  #| Town/City* | Testshire |
  #| Postcode* | 123456 |
  Scenario Outline: Valid entries show in the <field> field (US)
    When the user has entered a valid entry into the Ship to Address '<field>' field
      | <entry> |
    And the user clicks out of the field
      | <field> |
    Then user's entry is displayed in the Ship to Address '<field>' field after exiting that field
      | <entry> |

    Examples: 
      | field       | entry           |
      | First name* | Test            |
      | Last name*  | Test            |
      | Address 1*  | 123 Test Street |
      | Town/City*  | Testville       |
      | ZIP Code*   |           12345 |

  Scenario Outline: Invalid <field> values result in an appropriate error message (US)
    Given the user has entered an invalid entry in the Ship to Address '<field>' field.
      | <entry> |
    When the user clicks out of the field
      | <field> |
    Then the error message displays as per designs
      | <error_message> |
    And the field is underlined in red

    Examples: 
      | field       | error_message                               | entry      |
      | First name* | Please enter your first name                | 123$%,.+-@ |
      | Last name*  | Please enter your last name                 | 123$%,.+-@ |
      | Address 1*  | Please enter the first line of your address | ^@$%       |
      | Town/City*  | Please enter your Town/City                 | 123$%,.+-@ |
      | ZIP Code*   | Please enter a valid ZIP code               | ZIPCODE    |

  #Scenario Outline:	Invalid <field> values result in an appropriate error message (UK)
  #Given the user is on the UK site
  #And the user is in the <field> field
  #And the user has entered an invalid entry
  #| <entry> |
  #When the user clicks out of the field
  #Then the error_message displays as per designs
  #| <error_message> |
  #And the field is underlined in red
  #Examples:
  #| field | error_message | entry |
  #| First name* | Please enter your first name | "123$%,.+-@" |
  #| Last name* | Please enter your last name | "123$%,.+-@" |
  #| Address 1* | Please enter the first line of your address | "@#$%" |
  #| Town/City* | Please enter your Town/City | "123$%,.+-@" |
  #| Postcode* |  Please enter a valid postcode | post code |
  Scenario: Empty Ship to Address fields display error messages (US)
    And the user has not entered anything in the Shipping Address fields
    When the user clicks the 'continue' button
    Then the error messages display as per designs
      | Please enter your first name                |
      | Please enter your last name                 |
      | Please enter the first line of your address |
      | Please enter your Town/City                 |
      | Please enter a valid ZIP code               |
      | Please select a Title                       |
      | Please select your State                    |
    And the fields are underlined in red
      | Select a Title* |
      | First name*     |
      | Last name*      |
      | Address 1*      |
      | Town/City*      |
      | ZIP code*       |
      | State*          |
    And the user remains in the Ship to Address section
    And the user is anchored back up to the first error message
      | Please select a Title |

  #Scenario: Empty Ship to Address fields display error messages (UK)
  #And the user has not entered anything in the Shipping Address fields
  #When the user clicks the 'Continue' button
  #Then the error messages display as per designs
  #And the user remains in the Ship to Address section
  #Scenario Outline: Dropdown error messages work (UK)
  #Given the user is on the UK site
  #And the user has not selected <drop_down>
  #When the user clicks 'Continue'
  #Then the <error_message> is displayed
  #And the field is underlined in red
  #And the user is in the Ship to Address section
  #And the user is anchored back up to the first error message
  #Examples:
  #| drop_down | error_message |
  #| Select a Title* | Please select a Title |
  #| County | Please select your County |

  Scenario: Clicking 'Continue' after completing the Ship to Address fields leads to the Payment Section
    And the user has provided valid entries and selections for all Ship to Address fields
    When the user clicks the 'continue' button
    Then the user is on the Payment section
    And the user's Delivery Address entries are saved

  Scenario: Valid address values are saved in the review section
    And the user has provided valid entries and selections for all fields
    And the user has completed the Payment section
    When the user clicks the 'continue' button
    Then the user is on the Review section
    And the user's Delivery Address entries are saved
