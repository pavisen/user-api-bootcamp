package com.self.stepdef;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import com.self.payloads.UserPayload;
import com.self.requests.UserDataCreationRequest;
import com.self.requests.UserDataDeleteRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class UserDataCreationStepDef {
	UserDataCreationRequest userDataCreationRequest = new UserDataCreationRequest();

	@Given("Admin constructs a POST request to create a User with valid request body from the data sheet")
	public void admin_constructs_a_post_request_to_create_a_user_with_valid_request_body_from_the_data_sheet()
			throws IOException {
		for (int i = 0; i < 15; i++) {
			UserPayload body = UserDataCreationRequest.getUserPayloads().get(i);
			Response response = userDataCreationRequest.postRequest(body, i);
			if (response.getStatusCode() == 200) {
				return;
			}
			int userId = response.body().jsonPath().getInt("user_id");
			if (userId > 0) {
				UserDataDeleteRequest userDataDeleteRequest = new UserDataDeleteRequest();
				userDataDeleteRequest.deleteUserById(userId + "", "valid");
				response = userDataCreationRequest.postRequest(body, i);
				if (response.getStatusCode() == 200) {
					return;
				}
			}
		}
		assertTrue("Couldn't able to create a user", false);
	}

	@Given("Admin constructs a POST request to create a User with a invalid request body from the data sheet")
	public void admin_constructs_a_post_request_to_create_a_user_with_a_invalid_request_body_from_the_data_sheet()
			throws IOException {
		for (int i = 0; i < 15; i++) {
			UserPayload body = UserDataCreationRequest.getUserPayloads().get(i);
			Response response = userDataCreationRequest.postRequest(body, i);
			if (response.getStatusCode() > 399) {
				return;
			}
		}
		assertTrue("Couldn't able to test the invalid scenario", false);
	}

	@When("Admin sends an HTTP request to endpoint with request body from data sheet")
	public void admin_sends_an_http_request_to_endpoint_with_request_body_from_data_sheet() throws IOException {

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
