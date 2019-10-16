@PINK-230 @ShoppingBagUI @ShoppingBagServices @Sprint6
Feature: Quantity Selector on Shopping Bag (UI & Integration)
  As a user, 
  I want to be able to change the quantity of my items in my shopping bag, 
  so that I can buy more or less of the items I have chosen

  Background: 
    Given the cart has 1 item with of quantity 2
    And the user is on the bag page

  Scenario Outline: The user's cart reflects changes made by increasing the quantity selector
    Given the user is in <country> locale
    When the user increases the quantity using the quantity selector
    Then the user's cart is updated to include the increased quantity
    And the Order Summary fields are updated to reflect the increase in cart quantity
      | <fields> |

    Examples: 
      | country | fields                         |
      | US      | Subtotal, Est. Tax, Est. Total |
      | UK      | Subtotal, Total                |
      | ROW     | Subtotal, Est. Tax, Est. Total |

  Scenario Outline: The user's cart reflects changes made by decreasing the quantity selector
    Given the user is in <country> locale
    When the user decreases the quantity using the quantity selector
    Then the user's cart is updated to include the decreased quantity
    And the Order Summary fields are updated to reflect the decrease in cart quantity
      | <fields> |

    Examples: 
      | country | fields                         |
      | US      | Subtotal, Est. Tax, Est. Total |
      | UK      | Subtotal, Total                |
      | ROW     | Subtotal, Est. Tax, Est. Total |

  Scenario: The quantity selector can't decrement below 1
    Given the user has a quantity of 1 selected for a product
    When the user decreases the quantity using the quantity selector
    Then the product quantity will remain 1
