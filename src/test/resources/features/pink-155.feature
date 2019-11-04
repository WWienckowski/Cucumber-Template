@PINK-155 @Checkout @Sprint7 @incomplete
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
    When the user has entered a valid entry into the <field> field
      | <entry> |
    And the user clicks out of the field
      | <field> |
    Then user's entry is displayed in the <field> field
      | <entry> |

    Examples: 
      | field       | entry           |
      | First name* | Test            |
      | Last name*  | Test            |
      | Address 1*  | 123 Test Street |
      | City/Town*  | Testville       |
      | Zip Code*   |           12345 |

  Scenario Outline: Invalid <field> values result in an appropriate error message (US)
    Given the user has entered an invalid entry in the <field> field.
      | <entry> |
    When the user clicks out of the field
      | <field> |
    Then the error_message displays as per designs
      | <error_message> |
    And the field is underlined in red

    Examples: 
      | field         | error_message                                 | entry        |
      | "First name*" | "Please enter your first name"                | "123$%,.+-@" |
      | "Last name*"  | "Please enter your last name"                 | "123$%,.+-@" |
      | "Address 1*"  | "Please enter the first line of your address" | "@#$%"       |
      | "City/Town*"  | "Please enter your Town/City"                 | "123$%,.+-@" |
      | "Zip Code*"    | "Please enter a valid ZIP code"               | "ABCDEF*&^"  |

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
  Scenario Outline: 
    Given the user is on the <country> site
    And the user has not entered anything in the the fields
    When the user clicks 'Continue'
    Then the error messages are displayed
    And the user remains in the Ship to Address section

    Examples: 
      | country |
      #| UK |
      | US      |

  #Scenario Outline: Dropdown error messages work on UK site
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
  Scenario Outline: Dropdown error messages work on US site
    Given the user is on the US site
    And the user has not selected a <drop_down>
    When the user clicks 'Continue'
    Then the error message is displayed
      | <error_message> |
    And the field is underlined in red
    And the user is in the Ship to Address section
    And the user is anchored back up to the first error message

    Examples: 
      | drop_down       | error_message            |
      | Select a Title* | Please select a Title    |
      | State           | Please select your State |

  Scenario: Empty fields produce an error message when the user clicks 'Continue'
    Given the user is on the Ship to Address section
    And the user has provided valid entries and selections for all fields
    And the user has not yet completed the Payment section
    When the user clicks 'Continue'
    Then the user is on the Payment section
    And the user's Delivery Address entries are saved

  Scenario: Valid address values are saved in the review section
    Given the user is on the Ship to Address section
    And the user has provided valid entries and selections for all fields
    And the user has completed the Payment section
    When the user clicks 'Continue'
    Then the user is on the Review section
    And the user's Delivery Address entries are saved
