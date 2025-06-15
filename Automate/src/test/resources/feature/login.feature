Feature: User Login

  Scenario Outline: Successful login
    When the user is on the login page
    Then the user should be redirected to the Homepage after giving valid login credentials "<username>" and "<password>"
    
      Examples:
    | username | password  |
    | standard_user    | secret_sauce  |
    | locked_out_user     | secret_sauce |
    | problem_user    | secret_sauce  |
    | visual_user   | secret_sauce       |  