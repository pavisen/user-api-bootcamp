package com.self.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserApi_stepdef {

 

@Given("Admin constructs a POST request to create a User with request body from {string} and {int}")
public void admin_constructs_a_post_request_to_create_a_user_with_request_body_from_and(String string, Integer int1) {
   
    
}

@When("Admin sends an HTTP request to {string} with request body from {string} and {int}")
public void admin_sends_an_http_request_to_with_request_body_from_and(String string, String string2, Integer int1) {
   
    
}

@Then("Admin receives a response code")
public void admin_receives_a_response_code() {
   
    
}

@Given("Admin constructs a POST request to create a User without Authorization")
public void admin_constructs_a_post_request_to_create_a_user_without_authorization() {
   
    
}

@When("Admin sends an HTTP request without Authorization to {string} with request body from {string} and {int}")
public void admin_sends_an_http_request_without_authorization_to_with_request_body_from_and(String string, String string2, Integer int1) {
   
    
}

@When("Admin sends an HTTPS request with authorization {string} to {string} to retrieve User details by UserID {string}")
public void admin_sends_an_https_request_with_authorization_to_to_retrieve_user_details_by_user_id(String string, String string2, String string3) {
   
    
}

@Then("Admin receives status {int} and message {string} with response body for the User Data")
public void admin_receives_status_and_message_with_response_body_for_the_user_data(Integer int1, String string) {
   
    
}

@Given("Admin constructs a PUT request to update User details with request body from {string} and {int}")
public void admin_constructs_a_put_request_to_update_user_details_with_request_body_from_and(String string, Integer int1) {
   
    
}

@When("Admin sends an HTTP PUT request with {string} to {string} to update User by {string} with request body from {string} and {int}")
public void admin_sends_an_http_put_request_with_to_to_update_user_by_with_request_body_from_and(String string, String string2, String string3, String string4, Integer int1) {
   
    
}

@Then("Admin receives response code {string}")
public void admin_receives_response_code(String string) {
   
    
}

@When("Admin sends an HTTPS request with authorization {string} to {string} to retrieve User details by User First Name {string}")
public void admin_sends_an_https_request_with_authorization_to_to_retrieve_user_details_by_user_first_name(String string, String string2, String string3) {
   
    
}

@Given("Admin constructs a DELETE request for the User Data")
public void admin_constructs_a_delete_request_for_the_user_data() {
   
    
}

@When("Admin sends an HTTPS request with authorization {string} to {string} to delete User by UserID {string}")
public void admin_sends_an_https_request_with_authorization_to_to_delete_user_by_user_id(String string, String string2, String string3) {
   
    
}

@Then("Admin receives status {int} and message {string}")
public void admin_receives_status_and_message(Integer int1, String string) {
   
    
}

@When("Admin sends an HTTP request to {string} with request body from {string} and {int} to create a User with mandatory fields only")
public void admin_sends_an_http_request_to_with_request_body_from_and_to_create_a_user_with_mandatory_fields_only(String string, String string2, Integer int1) {
   
    
}

@When("Admin sends an HTTPS request with authorization {string} to {string} to delete User by User First Name {string}")
public void admin_sends_an_https_request_with_authorization_to_to_delete_user_by_user_first_name(String string, String string2, String string3) {
   
    
}




}
