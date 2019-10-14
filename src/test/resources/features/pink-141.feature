@PINK-141 @CheckoutUI
Feature: Payment Type Selection (UI)
  Users must be able to select their preferred Payment method.
  Each option will present different values underneath when selected.

  Background: 
  	Given there are products in the Shopping Bag
    And the user is on the checkout page

  Scenario: Payment options are initially disabled during checkout
    Then the 'Credit or Debit Card', 'Paypal' and 'Gift Card or e-voucher' payment options should display with radio buttons as per designs
    And the payment section should be greyed out as per designs

  Scenario: An active Payment section shows Credit/Debit by default
    When the user is in the payment section of checkout
    Then the 'Credit or Debit Card' radio button is selected
    And the relevant payment fields will be displayed
      | Enter Card Number*  |
      | Name on Card*       |
      | Expiration (MM/YY)* |
      | CVV*                |
    And the 'Billing address same as shipping address' checkbox will appear checked below the fields

  Scenario: The PayPal payment option is selected
    Given the user is in the payment section of checkout
    And the user clicks on 'Paypal'
    Then no payment fields will display underneath
    And a paragraph of text will appear beneath the radio buttons
    And an active 'Proceed With Paypal' button will display
    And the Review section of the Checkout page will not display

  Scenario: The Gift Card payment option is selected
    Given the user is in the payment section of checkout
    And the user clicks on 'Gift Card or e-voucher'
    Then the gift card fields will be displayed
      | Gift Card Number* |
      | Amount to Redeem* |
    And the Captcha security check will be displayed beneath the fields
