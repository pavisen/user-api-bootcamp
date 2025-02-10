Feature: delete the existing user
Background: User sets the Basic authorization with Username and Password and enters the base URL 
 @delete_user_by_userid
    Scenario Outline: Verify Admin can delete user by userID with valid and invalid inputs
        Given Admin constructs a DELETE request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to delete User by UserID "<userid>"
        Then Admin receives status <statuscode> and message "<statusMsg>"

        Examples:
            | userid  | endpoint | statuscode | statusMsg    | authorization |
            | valid   | valid    | 401        | Unauthorized | noauth        |
            | valid   | valid    | 200        | OK           | auth          |
            | valid   | invalid  | 404        | Not Found    | auth          |
            | invalid | valid    | 404        | Not Found    | auth          |

    @delete_user_by_userfirstname
    Scenario Outline: Verify Admin can delete user by userFirstName with valid and invalid inputs
        Given Admin constructs a DELETE request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to delete User by User First Name "<userfirstname>"
        Then Admin receives status <statuscode> and message "<statusMsg>" 

        Examples:
            | userfirstname | endpoint | statuscode | statusMsg    | authorization |
            | valid         | valid    | 401        | Unauthorized | noauth        |
            | valid         | valid    | 200        | OK           | auth          |
            | valid         | invalid  | 404        | Not Found    | auth          |
            | invalid       | valid    | 404        | Not Found    | auth          |
            