Feature: update the existing user

    @update_user
    Scenario Outline: Verify Admin can update user details with valid data
        Given Admin constructs a PUT request to update User details with request body
        When Admin sends an HTTP PUT request to update User by "<userid>" with request body
        Then Admin receives response code "<statuscode>"

        Examples:
            | userid  | authorization | statuscode |
            | valid   | noauth        | 401        |
            | valid   | auth          | 404        |
            | invalid | auth          | 404        |
            | valid   | auth          | 200        |
