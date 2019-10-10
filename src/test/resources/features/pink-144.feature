@PINK-144 @CheckoutUI
Feature: Billing Address (UI)
  Users must be able to edit their billing address in the Payment section of checkout
  if it differs to the shipping address they have already inputted

  Background: 
  	Given there are items in the cart
    And the user is on the checkout page
    And the user is in the payment section of checkout

  Scenario: There is a checkbox is in the Credit/Debit payment section
    When the user selects the 'Credit or Debit Card' payment option
    Then the checkbox for 'Billing address same as shipping address' is checked
    And the checkbox should display under the card details fields as per designs

  Scenario: The billing address fields appear when the checkbox is un-checked
    Given the user selects the 'Credit or Debit Card' payment option
    When the user clicks the checkbox for 'Billing address same as shipping address'
    Then the address fields appear underneath the checkbox for users input

  Scenario: The billing address fields dissapear when the checkbox is re-checked
    Given the user selects the 'Credit or Debit Card' payment option
    And the user clicks the checkbox for 'Billing address same as shipping address'
    And the address fields appear underneath the checkbox for users input
    When the user clicks the checkbox for 'Billing address same as shipping address'
    Then the billing address fields disappear
