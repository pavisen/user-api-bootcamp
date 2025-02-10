Feature: update the existing user
Background: User sets the Basic authorization with Username and Password and enters the base URL 
 @update_user
    Scenario Outline: Verify Admin can update user details with valid or invalid data
        Given Admin constructs a PUT request to update User details with request body from "<sheet>" and <row>
        When Admin sends an HTTP PUT request with "<authorization>" to "<endpoint>" to update User by "<userid>" with request body from "<sheet>" and <row>
        Then Admin receives response code "<statuscode>"

        Examples:
            | sheet    | row | endpoint | userid  | authorization | statuscode |
            | UserData | 48  | valid    | valid   | noauth        | 401        |
            | UserData | 48  | invalid  | valid   | auth          | 404        |
            | UserData | 48  | valid    | invalid | auth          | 404        |
            | UserData | 48  | valid    | valid   | auth          | 200        |
