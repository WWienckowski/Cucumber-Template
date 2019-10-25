@PINK-154 @Checkout @Sprint5 @parallel
Feature: Card Payment - Field Validation (Services)
  The system must alert the customer if any of their entries in the card payment 
  fields are not in a valid format.

	NOTE: in-line validation should wait until field is not in focus to show error messages.
	Current website shows error messages while typing.
	
	Background: 
		Given there are products in the Shopping Bag
		And the user is in the Card payment section
	
  Scenario Outline: The user inputs invalid Card payment information for: <field>
		Given the user has entered an invalid value in the field
		| <field> |
		| <entry> |
		When the user clicks out of the field
		| <field> |
		Then the error message displays as per designs
		| <error_message> |
		And the field is underlined in red
		| <field> |
	Examples:
	| field | entry | error_message |
	| Enter Card Number* | test | Please enter a valid payment card number |
	| Name on Card* | 123 | Please enter the cardholder's name |
	| Expiration (MM/YY)* | 999 | Please enter a valid expiry date |
	| CVV* | test | Please enter a valid security number |	
		
	Scenario Outline:	The user inputs valid Card payment information for: <field>
		Given the user has entered a valid value in the field
		| <field> |
		| <entry> |
		When the user clicks out of the field
		| <field> |
		Then user's entry is displayed in the field
		| <field> |
		| <entry> |
	Examples:
	| field | entry | 
	| Enter Card Number* | 12345678901234 | 
	| Name on Card* | Test Name |
	| Expiration (MM/YY)* | 10/20 | 
	| CVV* | 123 | 
	
	Scenario:	The user attempts to continue without entering Card payment information
  	Given the user clicks Continue without entering any information
		Then the error_messages display as per designs
		| Please enter a valid payment card number |
		| Please enter the cardholder's name |
		| Please enter a valid expiry date |
		| Please a valid security number |
		And the fields are underlined in red
		| Enter Card Number* |
		| Name on Card* |
		| Expiration (MM/YY)* |
		| CVV* |

	
                                                            
