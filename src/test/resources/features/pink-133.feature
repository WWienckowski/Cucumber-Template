@PINK-133 @CheckoutUI
Feature: Review Order Component (UI)
  Users must be able to review the sub-total, shipping and total fees they are paying prior to
  placing their order in the checkout, as well as agreeing to the Terms and Conditions of Sale.

  Background:
  Given the user is on the checkout page
  
  Scenario: The checkout page has an inactive Review component
		Then there is a 'Review' component as per designs
		And the Review component is greyed out and not clickable

	Scenario: The user enters payment info and clicks 'Continue' in checkout
		When the user enters valid entries into the Payment section and clicks Continue
		Then the Review section is active
		And there is a 'Terms and Conditions' checkbox
		And the checkbox for 'Terms and Conditions' is not checked
		And there is a 'Place order' button that is grey with white text
		And the Review section has the appropriate fields
			| SUBTOTAL |
			| SHIPPING |
			| VOUCHER |
			| GIFT CARD |
			| TAX |
			| TOTAL |

	Scenario: The user clicks on the unchecked Terms and Conditions Checkbox in the Order Review
		Given the user is on the Review section of the Checkout page
		And the checkbox for 'Terms and Conditions' is not checked
		When the user clicks the checkbox for 'Terms and Conditions'
		Then the checkbox for 'Terms and Conditions' is checked

	Scenario: The user clicks on the checked Terms and Conditions Checkbox in the Order Review
		Given the user is on the Review section of the Checkout page
		And the user clicks the checkbox for 'Terms and Conditions'
		And the checkbox for 'Terms and Conditions' is checked
		When the user clicks the checkbox for 'Terms and Conditions'
		Then the checkbox for 'Terms and Conditions' is not checked

 
