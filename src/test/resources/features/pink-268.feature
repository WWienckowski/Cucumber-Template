@PINK-268 @ShoppingBag @Sprint6 @incomplete
Feature: Quantity/price update on bag (Integration)
  Users will see updated price on the product cells and summary on the
  shopping bag when changing the quantity of products

  Scenario: #I think this is redundant
    Given the user is on the shopping bag page
		When the user clicks the '+' or '-' buttons on the quantity selector
		Then the price will update appropriately on the product line 
		And the Subtotal will update on the Order Summary section
