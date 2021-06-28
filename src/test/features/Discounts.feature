@Acceptance
Feature: Discounts
  In order to pay less
  As a course attendee
  I want to apply a discount code

  Scenario: 10% discount
    Given the course "TDD" costs $1000
    And the code "SAVE" gives a 10% discount
    When the cost of "TDD" with the code "SAVE" is calculated
    Then the cost should be $900

  Scenario: No discount
    Given the course "TDD" costs $1000
    When the cost of "TDD" with the code "ZERO" is calculated
    Then the cost should be $1000

  Scenario Outline: Discount codes
    Given the course "<courseCode>" costs $<courseCost>
    And the code "<discountCode>" gives a <discountAmount>% discount
    When the cost of "<courseCode>" with the code "<discountCode>" is calculated
    Then the cost should be $<expectedCost>

    Examples:
      |courseCode|courseCost|discountCode|discountAmount|expectedCost|
      |    TDD   |   1000   |    SAVE    |      10      |     900    |
      |    TDD   |   1000   |    ZERO    |       0      |    1000    |
