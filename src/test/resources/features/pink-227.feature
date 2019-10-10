@PINK-227 @ShoppingBagUI @ShoppingBagServices
Feature: Shopping Bag Product Display (UI & Integration)
  This story defines what product attributes must display upon page load of the shopping bag. 
	It also covers the behaviours around removing products from the shopping bag.
	It also covers the styling for quantity selector, remove item and gift wrap checkbox.
	The integration for these elements are covered in other stories, as is the integration for Size/Collar Size selection in Shopping Bag.
	
	Background:
		# The user has specific items with certain attributes in the bag and goes to the bag
		
  Scenario: Shopping Bag Product Display (UI & Integration)
    Given the user is navigating to the Shopping Bag page
		And there is one or more product in the Shopping Bag
		When the Shopping Bag page loads
		Then the product cell will contain the user's selected <attribute> where each product has a value for the <attribute> in Commercetools
		And where the product does not contain a value for <attribute> in Commercetools that <attribute> field is not displayed
			| attribute |
			| Primary Product Image |
			| Name |
			| Colour |
			| Size | 
			| Collar Size |
			| Fit |
			| Sleeve Length | 
			| Price |

	Scenario:
		Given the user is navigating to the Shopping Bag page
		And there is one or more product in the Shopping Bag
		When the Shopping Bag page loads
		Then the product cell will contain the following <elements> as per designs
		| element |
		| Quantity Selector | 
		| Remove Item Link |
		| Gift Wrap Check Box |
