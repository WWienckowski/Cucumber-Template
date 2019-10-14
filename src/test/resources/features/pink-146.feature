@PINK-146 @CheckoutUI
Feature: Checkout Button Behavior (UI)
  CTA buttons throughout checkout must all behave in the same way

  Background: 
    Given there are products in the Shopping Bag
    And the user is on the checkout page

  Scenario: Checkout CTA buttons display correctly
    Then an active CTA button will be displayed with a black outline, black fill and white text
    And an inactive CTA button will be displayed with a grey outline, grey fill and white text

  Scenario: The active CTA button will display a black arrow when hovered over
    Then the cursor hovering over an active CTA button will be a pointing finger icon

  Scenario: The cursor reverts to default when moving off of an active CTA button
    And the user hovers over an active CTA button
    When the user moves the cursor off the active CTA button
    Then the mouse cursor will revert to default

  Scenario: The inactive CTA button will display a black arrow when hovered over
    Then the cursor hovering over an inactive CTA button will be the default mouse cursor
