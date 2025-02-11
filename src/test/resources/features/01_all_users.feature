Feature: Retrieve all users

 @get_all_users
    Scenario Outline: Verify Admin can retrieve all users with valid or invalid data
        Given Admin constructs a GET request for the User Data
        When Admin sends an HTTP request to "<endpoint>" with "<authorization>"
        Then Admin receives a response status code <statuscode> with message "<statusMsg>" for "<endpoint>" and "<authorization>"

        Examples:
            | endpoint | statuscode | statusMsg    | authorization |
            | valid    | 200        | OK           | auth          |
            | invalid  | 404        | Not Found    | auth          |
            | valid    | 401        | Unauthorized | noauth        |
