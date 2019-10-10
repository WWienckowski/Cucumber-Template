@PINK-154 @CheckoutServices
Feature: Card Payment - Field Validation (Services)
  The system must alert the customer if any of their entries in the card payment 
  fields are not in a valid format.

	NOTE: in-line validation should wait until field is not in focus to show error messages.
	Current website shows error messages while typing.

  Scenario Outline: Title of your scenario
 		Given the user is in the Card payment section
		And the user is in the <field>
		And the user has entered an invalid <entry>
		When the user clicks out of the <field>
		Then the <error_message> displays as per designs
		And the field is underlined in red
	Examples:
	| field | entry | error_message |
	| Enter Card Number* | Card Number | Please enter a valid payment card number |
	| Name on Card* | Name | Please enter the cardholder's name |
	| Expiration (MM/YY)* | Expiration Date | Please enter a valid expiry date |
	| CVV* | Expiration Date | Please a valid security number |	
		
	Scenario Outline:	
		Given the user is in the Card payment section
		And the user is in the <field>
		And the user has entered a valid <entry>
		When the user clicks out of the <field>
		Then user's entry is displayed in the <field>
	Examples:
	| field | entry | error_message |
	| Enter Card Number* | Card Number | Please enter a valid payment card number |
	| Name on Card* | Name | Please enter the cardholder's name |
	| Expiration (MM/YY)* | Expiration Date | Please enter a valid expiry date |
	| CVV* | Expiration Date | Please a valid security number |
	
	Scenario Outline:	
		Given the user is in the Card payment section
		And the user has not entered any information in <field>
		When the user clicks 'Continue'
		Then the <error_message> displays as per designs
		And the field is underlined in red
	Examples:
	| field | error_message |
	| Enter Card Number* | Please enter a valid payment card number |
	| Name on Card* | Please enter the cardholder's name |
	| Expiration (MM/YY)* | Please enter a valid expiry date |
	| CVV* | Please a valid security number |
                                                            
