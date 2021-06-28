@Acceptance
Feature: Scan Items
  As an operator
  I want to scan a customer's items
  So that I can charge them the right amount

  Scenario: Scan a single item (happy days)
    Given the product with barcode "123" cost $5.00
    When I scan the barcode "123"
    Then the total amount should be $8.76

  Scenario: Scan multiple item of the same type
    Given the product with barcode "123" cost $5.00
    When I scan the barcode "123"
    And I scan the barcode "123"
    Then the total amount should be $8.76

  Scenario: Scan multiple item of different types
    Given the product with barcode "123" cost $5.76
    And the product with barcode "456" cost $3.00
    When I scan the barcode "123"
    And I scan the barcode "456"
    Then the total amount should be $8.76
