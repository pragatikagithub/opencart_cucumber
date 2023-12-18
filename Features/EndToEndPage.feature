Feature: End to End testPage

  Scenario: EndtoEnd testpage
    Given on homepage click on Register Account page
    When enters the details and selects Privacy Policy
    And clicks on Continue button
    Then check account should get created successfully
    And click on logout button
    Given go to login page
    When enters  username as email and password as "test@123"
    And clicks on the Login button
    Then validate the MyAccount Page
    When enter productname and click search button
    Then add product to cart
