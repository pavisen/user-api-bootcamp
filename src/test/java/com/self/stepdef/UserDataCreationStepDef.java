package com.self.stepdef;

import java.io.IOException;

import com.self.payloads.UserPayload;
import com.self.requests.UserDataCreationRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserDataCreationStepDef {
	UserDataCreationRequest userDataCreationRequest = new UserDataCreationRequest();

	@Given("Admin constructs a POST request to create a User with request body from the data sheet")
	public void admin_constructs_a_post_request_to_create_a_user_with_request_body_from_the_data_sheet()
			throws IOException {

	}

	@When("Admin sends an HTTP request to endpoint with request body from data sheet")
	public void admin_sends_an_http_request_to_endpoint_with_request_body_from_data_sheet() throws IOException {
		for (int i = 0; i < 3; i++) {
			UserPayload body = UserDataCreationRequest.getUserPayloads().get(i);
			userDataCreationRequest.postRequest(body, i);
		}
	}

	@Then("Admin receives a response code defined in the data sheet")
	public void admin_receives_a_response_code_defined_in_the_data_sheet() {

	}

	@Given("Admin constructs a POST request to create a User without Authorization")
	public void admin_constructs_a_post_request_to_create_a_user_without_authorization() {

	}

	@When("Admin sends an HTTP request without Authorization to endpoint with request body from row <{int}>")
	public void admin_sends_an_http_request_without_authorization_to_endpoint_with_request_body_from_row(Integer rowNo)
			throws IOException {
		UserPayload body = UserDataCreationRequest.getUserPayloads().get(rowNo);
		userDataCreationRequest.postRequestNoAuth(body);
	}

	@Then("Admin receives a response code")
	public void admin_receives_a_response_code() {

	}

}
