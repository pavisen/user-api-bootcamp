@userData
Feature: User CRUD Operations
    Background: Set Basic authorization with Username and Password and enter the base URL

    @get_request_get_all_users
    Scenario Outline: Verify Admin can retrieve all users with valid or invalid data
        Given Admin constructs a GET request for the User Data
        When Admin sends an HTTP request to "<endpoint>" with "<authorization>"
        Then Admin receives a response status code "<statuscode>" with message "<statustext>" for "<endpoint>" and "<authorization>"

        Examples:
            | endpoint | statuscode | statusMessage   | authorization |
            | valid    | 401        | Unauthorized | noauth        |
            | valid    | 200        | OK           | auth          |
            | invalid  | 404        | Not Found    | auth          |

    @post_request_to_create_user
    Scenario Outline: Verify Admin can create a new user with valid or invalid inputs
        Given Admin constructs a POST request to create a User with request body from "<sheet>" and <row>
        When Admin sends an HTTP request to "<endpoint>" with request body from "<sheet>" and <row>
        Then Admin receives a response code

        Examples:
            | sheet      | row | endpoint |
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
            | sheet      | row | endpoint |
            | UserData | 1   | valid    |

    @get_request_get_user_by_userid
    Scenario Outline: Verify Admin can retrieve user details by userID with valid and invalid inputs
        Given Admin constructs a GET request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to retrieve User details by UserID "<userid>"
        Then Admin receives status <statuscode> and message "<statustext>" with response body for the User Data

        Examples:
            | userid  | endpoint | statuscode | statustext   | authorization |
            | valid   | valid    | 401        | Unauthorized | noauth        |
            | valid   | valid    | 200        | OK           | auth          |
            | valid   | invalid  | 404        | Not Found    | auth          |
            | invalid | valid    | 404        | Not Found    | auth          |

    @put_request_to_update_user
    Scenario Outline: Verify Admin can update user details with valid or invalid data
        Given Admin constructs a PUT request to update User details with request body from "<sheet>" and <row>
        When Admin sends an HTTP PUT request with "<authorization>" to "<endpoint>" to update User by "<userid>" with request body from "<sheet>" and <row>
        Then Admin receives response code <statuscode> and message "<respMsg>"

        Examples:
            | sheet      | row | endpoint | userid  | authorization | statuscode | respMsg      |
            | UserData | 48  | valid    | valid   | noauth        | 401        | HTTP/1.1 401 |
            | UserData | 48  | invalid  | valid   | auth          | 404        | HTTP/1.1 404 |
            | UserData | 48  | valid    | invalid | auth          | 404        | HTTP/1.1 404 |
            | UserData | 48  | valid    | valid   | auth          | 200        | HTTP/1.1 200 |

    @get_request_get_user_by_userfirstname
    Scenario Outline: Verify Admin can retrieve user details by userFirstName with valid and invalid inputs
        Given Admin constructs a GET request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to retrieve User details by User First Name "<userfirstname>"
        Then Admin receives status <statuscode> and message "<statustext>" with response body for the User Data

        Examples:
            | userfirstname | endpoint | statuscode | statustext   | authorization |
            | valid         | valid    | 401        | Unauthorized | noauth        |
            | valid         | valid    | 200        | OK           | auth          |
            | valid         | invalid  | 404        | Not Found    | auth          |
            | invalid       | valid    | 404        | Not Found    | auth          |

    @delete_request_delete_user_by_userid
    Scenario Outline: Verify Admin can delete user by userID with valid and invalid inputs
        Given Admin constructs a DELETE request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to delete User by UserID "<userid>"
        Then Admin receives status <statuscode> and message "<statustext>" and "<respMsg>" with response body for the User Data

        Examples:
            | userid  | endpoint | statuscode | statustext   | authorization | respMsg      |
            | valid   | valid    | 401        | Unauthorized | noauth        | HTTP/1.1 401 |
            | valid   | valid    | 200        | OK           | auth          | HTTP/1.1 200 |
            | valid   | invalid  | 404        | Not Found    | auth          | HTTP/1.1 404 |
            | invalid | valid    | 404        | Not Found    | auth          | HTTP/1.1 404 |

    @post_request_to_create_user_with_mandatory_fields
    Scenario Outline: Verify Admin can create a new user with mandatory fields only
        Given Admin constructs a POST request to create a User with request body from "<sheet>" and <row>
        When Admin sends an HTTP request to "<endpoint>" with request body from "<sheet>" and <row> to create a User with mandatory fields only
        Then Admin receives a response code

        Examples:
            | sheet      | row | endpoint |
            | UserData | 44  | valid    |

    @delete_request_delete_user_by_userfirstname
    Scenario Outline: Verify Admin can delete user by userFirstName with valid and invalid inputs
        Given Admin constructs a DELETE request for the User Data
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to delete User by User First Name "<userfirstname>"
        Then Admin receives status <statuscode> and message "<statustext>" and "<respMsg>" with response body for the User Data

        Examples:
            | userfirstname | endpoint | statuscode | statustext   | authorization | respMsg      |
            | valid         | valid    | 401        | Unauthorized | noauth        |
            | valid         | valid    | 200        | OK           | auth          |
            | valid         | invalid  | 404        | Not Found    | auth          |
            | invalid       | valid    | 404        | Not Found    | auth          |
