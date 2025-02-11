Feature: create new user
    @create_user_with_valid_inputs
    Scenario: Verify Admin can create a new user with valid inputs
        Given Admin constructs a POST request to create a User with valid request body from the data sheet
        When Admin sends an HTTP request to endpoint with request body from data sheet
        Then Admin receives a response code defined in the data sheet
    @create_user_with_invalid_inputs
    Scenario: Verify Admin can create a new user with invalid inputs
        Given Admin constructs a POST request to create a User with a invalid request body from the data sheet
        When Admin sends an HTTP request to endpoint with request body from data sheet
        Then Admin receives a response code defined in the data sheet
    @post_request_to_create_user_without_authorization
    Scenario: Verify Admin can create a new user without authorization
        Given Admin constructs a POST request to create a User without Authorization
        When Admin sends an HTTP request without Authorization to endpoint with request body from row <20>
        Then Admin receives a response code


