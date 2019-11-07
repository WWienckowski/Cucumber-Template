@PINK-241 @ShoppingBag @Sprint6
Feature: Gift Wrap & Gift Message (UI & Integration)
  Users can add complimentary gift wrap or gift message to their products 
  (done at line level not order level).
  The user must not be able to type more than 200 characters in the gift message field.

	Background:
		Given the user has a shirt in the bag
		And the user is on the bag page
		And the user clicks on the gift wrap checkbox
	
  Scenario: The gift message textfield appears when 'add gift wrap' is selected
		Then the Optional Gift Message field opens
		And the field will include ghost text of "Type your personal message" (differs to designs)
		And there is a character counter that displays '0 / 200'
		
	Scenario:	The mouse cursor becomes a pointing finger over the gift wrap checkbox
		Then the gift wrap checkbox displays the cursor as a pointing finger icon
		
	Scenario:	The gift message character counter tracks the character count
		When  the Optional Gift Message field opens
		And the user starts typing in the Optional Gift Message field
		Then the character counter displays the number of characters entered out of the 200 allowed
		
	Scenario:	The gift message textfield is not resizeable
		When the Optional Gift Message field opens
		Then the user is not able to resize the Optional Gift Message field
		
	Scenario: The user can not enter more than 200 characters in the gift message textfield
		Given the user has typed 200 characters in the Optional Gift Message field
		When the user enters an additional character to the gift message
		Then no character is added to the gift message field
		
	Scenario:	The gift message textfield closes when the checkbox is un-checked
		And the Optional Gift Message field opens
		When the user clicks on the gift wrap checkbox
		Then the Optional Gift Message field closes
		And the Gift Wrap checkbox is unchecked
		
	Scenario:	The gift message textfield saves the user's message after closing
		Given the Optional Gift Message field opens
		And the user has added a gift message
		And the user clicks on the gift wrap checkbox
		And the Optional Gift Message field closes
		And the user has not left the page
		When the user clicks on the gift wrap checkbox
		And the Optional Gift Message field opens
		Then the gift message they added previously will be displayed