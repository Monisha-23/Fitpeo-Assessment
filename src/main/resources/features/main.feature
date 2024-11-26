Feature: Open Example Website

  Background: Open the Web browser
    Given User navigates to "https://www.fitpeo.com/"
    Then User verifies the page title as "Remote Patient Monitoring (RPM) - fitpeo.com"

  Scenario Outline: Update the text field
    Given User navigates to <url>
    Then User verifies the url of the page as <url>
    And User scrolls down to the <section>
    When User enters <value> in the text field
    Then User validates the slider value as <value>

    Examples:
      |  | url                                     | section  | value |
      |  | "https://fitpeo.com/revenue-calculator" | "slider" | 530   |

  Scenario Outline: Adjust the Slider
      Given User navigates to <url>
      Then User verifies the url of the page as <url>
      And User scrolls down to the <section>
      And User sets the slider value to <value>
      Then User validates the text field with value as <value>


      Examples:
        | url                                     | value | section  |  |
        | "https://fitpeo.com/revenue-calculator" | 820   | "slider" |  |


  Scenario Outline: Validate CTP Codes
    Given User navigates to <url>
    Then User verifies the url of the page as <url>
    And User scrolls down to the <section>
    And User sets the slider value to <value>
    And User checks the checkbox for "CPT-99091"
    And User checks the checkbox for "CPT-99453"
    And User checks the checkbox for "CPT-99454"
    And User checks the checkbox for "CPT-99474"
    Then User validates the reimbursement as "$110700"

  Examples:
    | section | url                                     | value |
    | "ctp"   | "https://fitpeo.com/revenue-calculator" | 820   |
