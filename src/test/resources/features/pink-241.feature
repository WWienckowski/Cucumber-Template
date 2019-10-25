@PINK-241 @ShoppingBag @Sprint6 @incomplete
Feature: Gift Wrap & Gift Message (UI & Integration)
  Users can add complimentary gift wrap or gift message to their products 
  (done at line level not order level).
  The user must not be able to type more than 200 characters in the gift message field.

  Scenario: 
		Given the user is on the shopping bag page
		When the user checks the 'Add Complimentary Gift Wrap' checkbox
		Then the 'Optional Gift Message' field opens up below (as per wireframes - accordion animation)
		And the field will include ghost text of 'Type your personal message' (differs to designs)
		And there is a character counter that displays '0/200'
		
	Scenario:	
		Given the user is on the shopping bag page
		And the user is on desktop
		When the user hovers over the 'Add Complimentary Gift Wrap' checkbox
		Then the cursor is a pointing finger icon
		
	Scenario:	
		Given the user has checked the 'Add Complimentary Gift Wrap' checkbox
		And the 'Optional Gift Message' field has opened up below
		When the user starts typing in the 'Optional Gift Message' field
		Then the character counter displays the number of characters entered out of the 200 allowed
		
	Scenario:	
		Given the user has checked the ‘Add Complimentary Gift Wrap' checkbox
		When the 'Optional Gift Message' field opens up below
		Then the user is not able to resize the 'Optional Gift Message’ field
		
	Scenario: 
		Given the user has typed 200 characters in the 'Optional Gift Message' field
		When the user enters a character
		Then no character is added to the field
		
	Scenario:	
		Given the user has checked the 'Add Complimentary Gift Wrap' checkbox
		And the 'Optional Gift Message' field has opened up below (accordion animation)
		When the user checks the 'Add Complimentary Gift Wrap' checkbox again
		Then the accordion field closes
		And the 'Add Complimentary Gift Wrap' checkbox is unchecked
		
	Scenario:	
		Given the user has added a gift message
		And the user has unchecked the 'Add Complimentary Gift Wrap' checkbox
		And the gift message field is no longer displayed
		And the user has not left the page
		When the user checks the 'Add Complimentary Gift Wrap' checkbox
		Then the gift message they added previously will be displayed