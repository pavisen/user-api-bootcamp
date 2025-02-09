@UserData
Feature: User CRUD Operations
    Background: Set Basic authorization with Username and Password and enter the base URL

    @Get_request_get_all_Users
    Scenario Outline: Verify Admin can retrieve all users with valid or invalid data
        Given Admin creates a GET request for the User Module
        When Admin sends an HTTP request to "<endpoint>" with "<authorization>"
        Then Admin receives a response status code "<statuscode>" with message "<statustext>" for "<endpoint>" and "<authorization>"

        Examples:
            | endpoint | statuscode | statusMessage   | authorization |
            | valid    | 401        | Unauthorized    | noauth        |
            | valid    | 200        | OK              | auth          |
            | invalid  | 404        | Not Found       | auth          |

    @POST_Request_to_Create_User
    Scenario Outline: Verify Admin can create a new user with valid or invalid inputs
        Given Admin sets a POST request to create a user with request body from "<sheet>" and <row>
        When Admin sends an HTTP request to "<endpoint>" with request body from "<sheet>" and <row>
        Then Admin receives a response code

        Examples:
            | sheet      | row | endpoint |
            | UserData   | 1   | invalid  |
            | UserData   | 1   | valid    |
            | UserData   | 3   | valid    |
            | UserData   | 4   | valid    |
            | UserData   | 5   | valid    |
            | UserData   | 6   | valid    |
            | UserData   | 7   | valid    |
            | UserData   | 8   | valid    |
            | UserData   | 9   | valid    |
            | UserData   | 10  | valid    |
            | UserData   | 11  | valid    |
            | UserData   | 12  | valid    |
            | UserData   | 13  | valid    |
            | UserData   | 14  | valid    |
            | UserData   | 15  | valid    |
            | UserData   | 16  | valid    |
            | UserData   | 17  | valid    |
            | UserData   | 18  | valid    |
            | UserData   | 19  | valid    |
            | UserData   | 20  | valid    |
            | UserData   | 21  | valid    |
            | UserData   | 22  | valid    |
            | UserData   | 23  | valid    |
            | UserData   | 24  | valid    |
            | UserData   | 25  | valid    |
            | UserData   | 26  | valid    |
            | UserData   | 27  | valid    |
            | UserData   | 28  | valid    |
            | UserData   | 29  | valid    |
            | UserData   | 30  | valid    |
            | UserData   | 31  | valid    |
            | UserData   | 32  | valid    |
            | UserData   | 33  | valid    |
            | UserData   | 34  | valid    |
            | UserData   | 35  | valid    |
            | UserData   | 36  | valid    |
            | UserData   | 37  | valid    |
            | UserData   | 38  | valid    |
            | UserData   | 39  | valid    |
            | UserData   | 40  | valid    |

    @POST_Request_to_Create_User_without_authorization
    Scenario Outline: Verify Admin can create a new user without authorization
        Given Admin sets a POST request to create a user without authorization
        When Admin sends an HTTP request without authorization to "<endpoint>" with request body from "<sheet>" and <row>
        Then Admin receives a response code

        Examples:
            | sheet      | row | endpoint |
            | UserData   | 1   | valid    |

    @GetRequest(Get_User_By_UserID)
    Scenario Outline: Verify Admin can retrieve user details by UserID with valid and invalid inputs
        Given Admin creates a GET request for the User Module
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to retrieve user details by UserID "<userid>"
        Then Admin receives status <statuscode> and message "<statustext>" with response body for the User Module

        Examples:
            | userid  | endpoint | statuscode | statustext   | authorization |
            | valid   | valid    | 401        | Unauthorized | noauth        |
            | valid   | valid    | 200        | OK           | auth          |
            | valid   | invalid  | 404        | Not Found    | auth          |
            | invalid | valid    | 404        | Not Found    | auth          |

    @PUT_Request_to_Update_User
    Scenario Outline: Verify Admin can update user details with valid or invalid data
        Given Admin sets a PUT request to update user details with request body from "<sheet>" and <row>
        When Admin sends an HTTP PUT request with "<authorization>" to "<endpoint>" to update user by "<userid>" with request body from "<sheet>" and <row>
        Then Admin receives response code <statuscode> and message "<respMsg>"

        Examples:
            | sheet      | row | endpoint | userid  | authorization | statuscode | respMsg      |
            | UserData   | 48  | valid    | valid   | noauth        | 401        | HTTP/1.1 401 |
            | UserData   | 48  | invalid  | valid   | auth          | 404        | HTTP/1.1 404 |
            | UserData   | 48  | valid    | invalid | auth          | 404        | HTTP/1.1 404 |
            | UserData   | 48  | valid    | valid   | auth          | 200        | HTTP/1.1 200 |

    @GetRequest(Get_User_By_UserFirstName)
    Scenario Outline: Verify Admin can retrieve user details by UserFirstName with valid and invalid inputs
        Given Admin creates a GET request for the User Module
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to retrieve user details by User First Name "<userfirstname>"
        Then Admin receives status <statuscode> and message "<statustext>" with response body for the User Module

        Examples:
            | userfirstname | endpoint | statuscode | statustext   | authorization |
            | valid         | valid    | 401        | Unauthorized | noauth        |
            | valid         | valid    | 200        | OK           | auth          |
            | valid         | invalid  | 404        | Not Found    | auth          |
            | invalid       | valid    | 404        | Not Found    | auth          |

    @DeleteRequest(Delete_User_By_UserID)
    Scenario Outline: Verify Admin can delete user by UserID with valid and invalid inputs
        Given Admin creates a DELETE request for the User Module
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to delete user by UserID "<userid>"
        Then Admin receives status <statuscode> and message "<statustext>" and "<respMsg>" with response body for the User Module

        Examples:
            | userid  | endpoint | statuscode | statustext   | authorization | respMsg      |
            | valid   | valid    | 401        | Unauthorized | noauth        | HTTP/1.1 401 |
            | valid   | valid    | 200        | OK           | auth          | HTTP/1.1 200 |
            | valid   | invalid  | 404        | Not Found    | auth          | HTTP/1.1 404 |
            | invalid | valid    | 404        | Not Found    | auth          | HTTP/1.1 404 |

    @POST_Request_to_Create_User_With_Mandatory_Fields
    Scenario Outline: Verify Admin can create a new user with mandatory fields only
        Given Admin sets a POST request to create a user with request body from "<sheet>" and <row>
        When Admin sends an HTTP request to "<endpoint>" with request body from "<sheet>" and <row> to create a user with mandatory fields only
        Then Admin receives a response code

        Examples:
            | sheet      | row | endpoint |
            | UserData   | 44  | valid    |

    @DeleteRequest(Delete_User_By_UserFirstName)
    Scenario Outline: Verify Admin can delete user by UserFirstName with valid and invalid inputs
        Given Admin creates a DELETE request for the User Module
        When Admin sends an HTTPS request with authorization "<authorization>" to "<endpoint>" to delete user by User First Name "<userfirstname>"
        Then Admin receives status <statuscode> and message "<statustext>" and "<respMsg>" with response body for the User Module

        Examples:
            | userfirstname | endpoint | statuscode | statustext   | authorization | respMsg      |
            | valid         | valid    | 401        | Unauthorized | noauth        |
            | valid         | valid    | 200        | OK           | auth          |
            | valid         | invalid  | 404        | Not Found    | auth          |
            | invalid       | valid    | 404        | Not Found    | auth          |
