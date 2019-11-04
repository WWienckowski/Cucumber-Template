@PINK-168 @Checkout @Sprint7 @incomplete
Feature: Billing Address - Field Validation (Services)
  This is the services ticket for the Billing Address behaviour. 
  The user must be able to use the same billing address as the 
  delivery address they have already entered for their order. 
  The user must also be able to provide a different billing address that is 
  associated with the credit card of their choice.
  
  NOTE: in-line validation should wait until field is not in focus to show error messages. 
  Current website shows error messages while typing.
  
  Field Validation & Error Messaging
  https://authxconsulting.atlassian.net/wiki/spaces/PINK/pages/1013645849/Field+Validation+Error+Messaging

  Background: 
    Given the user is in the card payment section
    And the user has unchecked the 'Billing Address is same as Shipping Address' checkbox

  Scenario Outline: Valid entries remain in the input field (UK)
    And the user is on the UK site
    And the user is in the field
      | <field> |
    And the user has entered a valid entry
      | <entry> |
    When the user clicks out of the field
    Then user's entry is displayed in the field
      | <entry> |
      | <field> |

    Examples: 
      | field      | entry           |
      | Address 1* | "123 Test Lane" |
      | Town/City* | "Testingham"    |
      | Postcode*  | "SW15 5PU"      |

  Scenario Outline: Valid entries remain in the input field (US)
    And the user is on the US site
    And the user is in the field
      | <field> |
    And the user has entered a valid entry
      | <entry> |
    When the user clicks out of the field
    Then user's entry is displayed in the field
      | <entry> |
      | <field> |

    Examples: 
      | field      | entry            |
      | Address 1* | "123 Test Drive" |
      | Town/City* | "Testburg"       |
      | ZIP code*  | "23220"          |

  Scenario Outline: Invalid field entries result in an error message (UK)
    And the user is on the UK site
    And the user is in the field
      | <field> |
    And the user has entered an invalid entry
      | <entry> |
    When the user clicks out of the field
    Then the error message displays as per designs
      | <error_message> |
    And the field is underlined in red
      | <field> |
    And the user is anchored back up to the first error message
      | <error_message> |

    Examples: 
      | field      | entry     | error_message                                 |
      | Address 1* | "%$@&&//" | "Please enter the first line of your address" |
      | Town/City* | "1234"    | "Please enter your Town/City"                 |
      | Postcode*  | "%$@&&//" | "Please enter a valid postcode"               |

  Scenario Outline: Invalid field entries result in an error message (US)
    And the user is on the US site
    And the user is in the field
      | <field> |
    And the user has entered an invalid entry
      | <entry> |
    When the user clicks out of the field
    Then the error message displays as per designs
      | <error_message> |
    And the field is underlined in red
      | <field> |
    And the user is anchored back up to the first error message
      | <error_message> |

    Examples: 
      | field      | entry      | error_message                                 |
      | Address 1* | "%$@&&//"  | "Please enter the first line of your address" |
      | Town/City* | "1234"     | "Please enter your Town/City"                 |
      | Postcode*  | "abc defg" | "Please enter a valid ZIP code"               |

  Scenario: Error messages display for empty Billing Address fields (UK)
    And the user is on the UK site
    And the user has not entered anything in the Billing Address fields
    When the user clicks 'Continue'
    Then the error messages are displayed
      | "Please enter the first line of your address" |
      | "Please enter your Town/City"                 |
      | "Please enter a valid postcode"               |
      | "Please select your County"                   |
    And the user is in the card payment section
    And the fields are underlined in red
      | Address 1* |
      | Town/City* |
      | Postcode*  |
      | County*    |
    And the user is anchored back up to the first error message

  Scenario: Error messages display for empty Billing Address fields (US)
    And the user is on the US site
    And the user has not entered anything in the Billing Address fields
    When the user clicks 'Continue'
    Then the error messages are displayed
      | "Please enter the first line of your address" |
      | "Please enter your Town/City"                 |
      | "Please enter a valid ZIP code"               |
      | "Please select your State"                    |
    And the user is in the card payment section
    And the fields are underlined in red
      | Address 1* |
      | Town/City* |
      | Postcode*  |
      | State*     |
    And the user is anchored back up to the first error message

  Scenario: Valid Billing Address entries are saved and displayed in the Review section
    Given the user is on the card payment section
    And the user has provided valid entries and selections for all fields
      | Address 1* | "123 Test Drive" |
      | Town/City* | "Testburg"       |
      | ZIP code*  | "23220"          |
      | State*     | "Alabama"        |
    When the user clicks 'Continue'
    Then the user is on the Review section
    And the user's card payment entries are saved
      | Address 1* | "123 Test Drive" |
      | Town/City* | "Testburg"       |
      | ZIP code*  | "23220"          |
      | State*     | "Alabama"        |
