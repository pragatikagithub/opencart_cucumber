Feature: Add product on the cart

  Scenario: Add to cart
    Given the user is on the opencart Home page
    And enter product in the search box
    When clicks on the Search button
    Then the user should be redirected to the Product Page
    And select the product,quantity and add to cart
    And validate the conformation massage
