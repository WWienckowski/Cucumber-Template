@PINK-227 @ShoppingBag @Sprint8
Feature: Shopping Bag Product Display (UI & Integration)
  This story defines what product attributes must display upon page load of the shopping bag. 
	It also covers the behaviours around removing products from the shopping bag.
	It also covers the styling for quantity selector, remove item and gift wrap checkbox.
	The integration for these elements are covered in other stories, as is the integration for Size/Collar Size selection in Shopping Bag.
	
	Background:
		Given the user has a shirt in the bag
		And the user has a tie in the bag
		And the user is on the bag page
		
  Scenario: The appropriate product attributes are displayed for each item
		Then each product will display the following attributes if it has that attribute. 
			| Primary Product Image |
			| Name |
			| Colour |
			| Size |
			| Collar Size |
			| Fit |
			| Sleeve |
			| Price |

	Scenario: Each item in the bag has the appropriate elements
		Then the product cell will contain the appropriate elements as per designs
			| Quantity Selector | 
			| Remove Item Link |
			| Gift Wrap Check Box |
