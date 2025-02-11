package com.self.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import com.self.requests.UsersDataRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class Users_stepdef {
	UsersDataRequest usersDataRequest;
	Response response;
	@Given("Admin constructs a GET request for the User Data")
	public void admin_constructs_a_get_request_for_the_user_data() {
		usersDataRequest = new UsersDataRequest(); 
	}

	@When("Admin sends an HTTP request to {string} with {string}")
	public void admin_sends_an_http_request_to_with(String endpoint, String authorization) throws IOException {
		boolean isvalidEndpoint = endpoint.equals("valid") ? true : false;
		response = usersDataRequest.getAllUsers(isvalidEndpoint, authorization);
	}

	@Then("Admin receives a response status code {int} with message {string} for {string} and {string}")
	public void admin_receives_a_response_status_code_with_message_for_and(Integer expectedStatuscode, String string, String string2,
			String string3) {
		Assert.assertEquals(response.getStatusCode(), expectedStatuscode);
	}

}
