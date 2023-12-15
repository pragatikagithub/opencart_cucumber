Feature: Searching Products

  Scenario: Search the Product
    Given the user is on the opencart Home page
    And the user enter valid product in the search input box
    When the user clicks on the Search button
    Then the user should be redirected to the Product Page
    And validate display of the product
