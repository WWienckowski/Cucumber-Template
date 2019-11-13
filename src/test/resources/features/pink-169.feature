@PINK-169 @Checkout @Sprint8 @incomplete
Feature: Contact for Order Component (Integration)
  As a user,
  I want to provide my contact details,
  So that Pink or Pink's fulfilment partners can contact me about my delivery or pick up

  Background:
    Given the user has a shirt in the bag
    And the user is on the checkout page

  Scenario Outline: The Contact for Order component has the correct fields for <delivery_type>
    And the user is in the <delivery_type> section
    Then there will be a 'Contact for order' component
    And the Contact for Order Component will contain the correct fields
    | <fields> |
   Examples:
    | delivery_type | fields |
    | Ship to Address | Email Address* ,  Mobile Number* |
    | Collect in a Pink Store | Select a Title* , Full name* , Email Address* , Mobile Number* |

  Scenario: A user who wants updates will be added to the mailing list
    Given the user has provided valid entries for all required fields in the 'Ship to Address' section
    And the user has checked the 'Send me updates from Pink Shirtmaker' checkbox
    When the user clicks the 'continue' button
    Then the user\'s cart will reflect their choice to subscribe to updates

  Scenario Outline: The user's 'Contact for Order' entries are captured (<delivery_type>)
    Given the user has provided valid entries for all required fields in the '<delivery_type>' section
    When the user clicks the 'continue' button
    Then the user\'s contact details are captured
      | <entries> |
    Examples:
      | delivery_type | entries |
      | Ship to Address | Test@test.com , 123-456-7890 |
      | Collect in a Pink Store | Mr. , Test Test , Test@test.com , 123-456-7890 |
    
  Scenario: The user can return and edit their 'Contact for Order' information
    Given the user has already completed the Delivery section
    When the user clicks 'Edit' on the Delivery section
    Then the Contact for Order component is active again
    And the user can change their Contact for Order entries
