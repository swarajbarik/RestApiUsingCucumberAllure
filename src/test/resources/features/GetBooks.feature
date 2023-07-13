
Feature: Books Controller

  Scenario: Get Books 
    Given i have the end point "Books"
    When i am executing the "GET" method
    Then i should see the status code as "200"
    And the response should contain the below details
    |isbn|title|subTitle|author|publish_date|publisher|pages|description|website|
