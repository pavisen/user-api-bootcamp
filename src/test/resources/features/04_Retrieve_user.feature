Feature: Retrieve user information by name and id
Background: User sets the Basic authorization with Username and Password and enters the base URL 
    @get_user_by_userid
    Scenario Outline: Verify Admin can retrieve user details by userID with valid and invalid inputs
        Given Admin constructs a GET request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to retrieve User details by UserID "<userid>"
        Then Admin receives status <statuscode> and message "<statusMsg>" with response body for the User Data

        Examples:
            | userid  | endpoint | statuscode | statusMsg    | authorization |
            | valid   | valid    | 401        | Unauthorized | noauth        |
            | valid   | valid    | 200        | OK           | auth          |
            | valid   | invalid  | 404        | Not Found    | auth          |
            | invalid | valid    | 404        | Not Found    | auth          |
   @get_user_by_userfirstname
    Scenario Outline: Verify Admin can retrieve user details by userFirstName with valid and invalid inputs
        Given Admin constructs a GET request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to retrieve User details by User First Name "<userfirstname>"
        Then Admin receives status <statuscode> and message "<statusMsg>" with response body for the User Data

        Examples:
            | userfirstname | endpoint | statuscode | statusMsg    | authorization |
            | valid         | valid    | 401        | Unauthorized | noauth        |
            | valid         | valid    | 200        | OK           | auth          |
            | valid         | invalid  | 404        | Not Found    | auth          |
            | invalid       | valid    | 404        | Not Found    | auth          |
            