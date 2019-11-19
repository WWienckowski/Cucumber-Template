@PINK-335 @Checkout @Sprint8 @incomplete
Feature: Contact for Order Summary
  As a system
  I want to present a summary of the contact information provided by the user in their delivery address or pick up location
  So that I can display it in the summary during checkout and the user can feel assured
  that their contact information has been captured correctly

  Scenario:
    GIVEN the user is in Deliver to Address
    AND the user has entered their contact details
    WHEN the user clicks 'Continue'
    THEN the Delivery Summary will include the contact information they provided

  Scenario:
    GIVEN the user is in Collect in a Pink Store
    AND the user has entered their contact details
    WHEN the user clicks 'Continue'
    THEN the Delivery Summary will include the contact information they provided