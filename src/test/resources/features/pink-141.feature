@PINK-141 @CheckoutUI
Feature: Payment Type Selection (UI)
  Users must be able to select their preferred Payment method.
  Each option will present different values underneath when selected.

  Scenario: Title of your scenario
    GIVEN the user is on the Checkout page
		AND the user has not reached the Payment section of checkout
		WHEN the user views the Payment section on the checkout page
		THEN the 'credit/debit card', 'paypal' and 'gift card' payment options should display with radio buttons as per designs
		AND the section should be greyed out as per designs

	Scenario:
		GIVEN the user is on the Checkout page
		WHEN the user progresses to the Payment section
		THEN the credit or debit card radio button will be selected by default
		AND the relevant <fields> will be displayed as per designs
		AND the 'Billing address same as shipping address' checkbox will appear checked below the fields
			fields 
			card number* 
			name on card * 
			expiry date (mm/yy)* 
			CVV* 

	Scenario:
		GIVEN the user is in the payment section of checkout 
		WHEN the user selects the Paypal radio button 
		THEN no fields will display underneath 
		AND a paragraph of text will appear beneath the button 
		AND an active 'Proceed with Paypal' button will display as per designs 
		AND the 'Review' section of the Checkout page will not display

	Scenario:
		GIVEN the user is in the payment section of checkout
		WHEN the user selects the Gift Card radio button
		THEN the <field> will display underneath 
		AND the Captcha security check will be displayed beneath the fields
			fields 
			Gift card number* 
			Amount to redeem* 
