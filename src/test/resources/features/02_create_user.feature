Feature: create new user
Background: User sets the Basic authorization with Username and Password and enters the base URL 
 @create_user
    Scenario Outline: Verify Admin can create a new user with valid or invalid inputs
        Given Admin constructs a POST request to create a User with request body from "<sheet>" and <row>
        When Admin sends an HTTP request to "<endpoint>" with request body from "<sheet>" and <row>
        Then Admin receives a response code

        Examples:
            | sheet    | row | endpoint |
            | UserData | 1   | invalid  |
            | UserData | 1   | valid    |
            | UserData | 3   | valid    |
            | UserData | 4   | valid    |
            | UserData | 5   | valid    |
            | UserData | 6   | valid    |
            | UserData | 7   | valid    |
            | UserData | 8   | valid    |
            | UserData | 9   | valid    |
            | UserData | 10  | valid    |
            | UserData | 11  | valid    |
            | UserData | 12  | valid    |
            | UserData | 13  | valid    |
            | UserData | 14  | valid    |
            | UserData | 15  | valid    |
            | UserData | 16  | valid    |
            | UserData | 17  | valid    |
            | UserData | 18  | valid    |
            | UserData | 19  | valid    |
            | UserData | 20  | valid    |
            | UserData | 21  | valid    |
            | UserData | 22  | valid    |
            | UserData | 23  | valid    |
            | UserData | 24  | valid    |
            | UserData | 25  | valid    |
            | UserData | 26  | valid    |
            | UserData | 27  | valid    |
            | UserData | 28  | valid    |
            | UserData | 29  | valid    |
            | UserData | 30  | valid    |
            | UserData | 31  | valid    |
            | UserData | 32  | valid    |
            | UserData | 33  | valid    |
            | UserData | 34  | valid    |
            | UserData | 35  | valid    |
            | UserData | 36  | valid    |
            | UserData | 37  | valid    |
            | UserData | 38  | valid    |
            | UserData | 39  | valid    |
            | UserData | 40  | valid    |

    @post_request_to_create_user_without_authorization
    Scenario Outline: Verify Admin can create a new user without authorization
        Given Admin constructs a POST request to create a User without Authorization
        When Admin sends an HTTP request without Authorization to "<endpoint>" with request body from "<sheet>" and <row>
        Then Admin receives a response code

        Examples:
            | sheet    | row | endpoint |
            | UserData | 1   | valid    |
 @post_request_to_create_user_with_mandatory_fields
    Scenario Outline: Verify Admin can create a new user with mandatory fields only
        Given Admin constructs a POST request to create a User with request body from "<sheet>" and <row>
        When Admin sends an HTTP request to "<endpoint>" with request body from "<sheet>" and <row> to create a User with mandatory fields only
        Then Admin receives a response code

        Examples:
            | sheet    | row | endpoint |
            | UserData | 44  | valid    |

            