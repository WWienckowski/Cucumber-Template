@PINK-315 @PLP @Sprint7 @Confirmed
Feature: Toggle between colours on PLP (Integration)
  The user must be able to see the different colour options available for a product in the PLP.
  The user must be able to select the different colours and have the image change to that colour selection.

	Background: 
		Given the user is on the PLP(shirts) page

  Scenario: The PLP displays color swatches with a color variant name for each product
		Then each product should display the product swatches beneath the product image
		And the name of each selected colour variant is displayed as per designs
		
	Scenario: The PLP updates the master image and color variant name to reflect a selected color swatch
		When the user clicks on a colour swatch beneath a product PDP link
		Then the master image is the selected colour variant
		And the name of the selected colour variant is displayed as per designs
