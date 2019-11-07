@PINK-170 @Checkout @Sprint7
Feature: Contact for Order - Field Validation (Services)
  The user must be able to enter the required contact details and 
  be alerted if their entries do not meet the validation criteria.

	Background: 
    Given there are products in the Shopping Bag
    And the user is on the checkout page

  Scenario Outline: Invalid Contact for Order entries display an error message correctly (Ship to Address)
    Given the user is in the Ship to Address section
    And the user has entered an invalid entry to the Contact for Order '<field>' field
      | <entry> |
    When the user clicks out of the field
      | <field> |
    Then the error message displays as per designs
      | <error_message> |
    And the field is underlined in red
      | <field> |

    Examples: 
      | field          | entry        | error_message                      |
      | Email Address* | email        | Please enter a valid email address |
      | Mobile Number* | phone number | Please enter a valid mobile number |

  Scenario Outline: Invalid Contact for Order entries display an error message correctly (Collect in Store)
    Given the user is in the Collect in a Pink Store section
    And the user has entered an invalid entry to the Contact for Order '<field>' field
      | <entry> |
    When the user clicks out of the field
      | <field> |
    Then the error message displays as per designs
      | <error_message> |
    And the field is underlined in red
      | <field> |

    Examples: 
      | field          | entry        | error_message                      |
      | Full name*     |        12345 | Please enter your full name        |
      | Email Address* | email        | Please enter a valid email address |
      | Mobile Number  | phone number | Please enter a valid mobile number |

  Scenario: Empty Contact for Order entries display an error message correctly (Ship to Address)
    Given the user is in the Ship to Address section
    And the user has not entered any contact details
    When the user clicks the 'continue' button
    Then the error messages display as per designs
      | Please enter a valid email address |
      | Please enter a valid mobile number |
    And the field is underlined in red
      | Email Address* |
      | Mobile Number* |

  Scenario: Empty Contact for Order entries display an error message correctly (Collect in Store)
    Given the user is in the Collect in a Pink Store section
    And the user has not entered any contact details
    When the user clicks the 'continue' button
    Then the error messages display as per designs
      | Please select a title              |
      | Please enter your full name        |
      | Please enter a valid email address |
      | Please enter a valid mobile number |
    And the fields are underlined in red
      | Select a Title* |
      | Full name*      |
      | Email Address*  |
      | Mobile Number*  |
