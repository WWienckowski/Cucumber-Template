@PINK-155 @CheckoutServices
Feature: Delivery Address - Field Validation (Services)
  The system must alert the customer if any of their entries in the delivery address fields are invalid.
	NOTE: in-line validation should wait until field is not in focus to show error messages. 
	Current website shows error messages while typing.
	
	Background:
		Given there are products in the Shopping Bag
    And the user is on the checkout page
     
  Scenario: Title of your scenario
    GIVEN the user is in the Ship to Address section
		AND the user is on the <country> site
		AND the user is in the <field>
		AND the user has entered a valid entry
		WHEN the user clicks out of the <field>
		THEN user's entry is displayed in the <field>
		
		GIVEN the user is in the Ship to Address section
		AND the user is on the <country> site
		AND the user is in the <field>
		AND the user has entered an invalid <entry>
		WHEN the user clicks out of the <field>
		THEN the <error_message> displays as per designs
		AND the field is underlined in red
		
		GIVEN the user is in the Ship to Address section
		AND the user is on the <country> site
		AND the user has not entered anything in the <field>
		WHEN the user clicks 'Continue'
		THEN the <error_message> is displayed
		AND the user is in the Ship to Address section

country     

field                                

error_message                                  

UK            

First name*                     

Please enter the first line of your address        

UK            

Last name*                    

Please enter your Town/City         

UK            

Address 1*                     

Please enter the first line of your address        

UK            

Town/City*                    

Please enter your Town/City         

UK            

Postcode*                    

Please enter a valid postcode      

US           

First name*                     

Please enter the first line of your address        

US           

Last name*                    

Please enter your Town/City         

US            

Address 1*                    

Please enter the first line of your address        

US            

Town/City*                    

Please enter your Town/City         

US            

ZIP code*                    

Please enter a valid ZIP code      

GIVEN the user is in the Ship to Address section
AND the user is on the <country> site
AND the user has not selected a <drop_down>
WHEN the user clicks 'Continue'
THEN the <error_message> is displayed
AND the field is underlined in red
AND the user is in the Ship to Address section
AND the user is anchored back up to the first error message

country     

drop_down           

error_message                  

UK            

Select a Title*       

Please select a Title          

UK            

County*                

Please select your County 

US            

Select a Title*       

Please select a Title          

US            

State*                    

Please select your State   

GIVEN the user is on the Ship to Address section
AND the user has provided valid entries and selections for all fields
AND the user has not yet completed the Payment section
WHEN the user clicks 'Continue'
THEN the user is on the Payment section
AND the user's Delivery Address entries are saved

GIVEN the user is on the Ship to Address section
AND the user has provided valid entries and selections for all fields
AND the user has completed the Payment section
WHEN the user clicks 'Continue'
THEN the user is on the Review section
AND the user's Delivery Address entries are saved

