@ReqRes

Feature: Get All User and Create Post and check Negative Scenario

Scenario: Get All User
  Given User prepares "Get All User" Request
  When  "GET" Request is Submitted
  Then Response Code is "200"
  And user response data is matched as expected

Scenario: Create new User
    Given User prepares "Create new User" Request with Below Data
     |title                                                                     | body                                                                                                                                                            |
     |sunt aut facere repellat provident occaecati excepturi optio reprehenderit|quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto|
    When  "POST" Request is Submitted
    Then Response Code is "201"

Scenario: Missing Details
    Given User prepares "Missing Details" Request
    When  "POST" Request is Submitted
    Then  Response Code is "400"



