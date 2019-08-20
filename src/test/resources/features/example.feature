@example
Feature: This is an example feature
  I will demonstrate that the template works

  @example_1
  Scenario Outline: Load the home page in <browser>
    Given I navigate the web driver to the home page
    Then I should see the title

    Examples: 
      | browser   |
      | "Firefox" |
      | "Chrome"  |

  @example_2
  Scenario: Navigate from the Home Page to the Details page - Chrome
    Given I navigate the web driver to the home page
    When I click on the SHIRTS link
    Then I should be on the "detail" page
