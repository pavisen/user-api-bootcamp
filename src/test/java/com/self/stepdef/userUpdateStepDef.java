package com.self.stepdef;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import com.self.payloads.UserPayload;
import com.self.requests.UserDataUpdateRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class userUpdateStepDef {
	UserDataUpdateRequest userDataUpdateRequest = new UserDataUpdateRequest();
	private Response response;

	@Given("Admin constructs a PUT request to update User details with request body")
	public void admin_constructs_a_put_request_to_update_user_details_with_request_body() {

	}

	@When("Admin sends an HTTP PUT request to update User by {string} with request body")
	public void admin_sends_an_http_put_request_to_update_user_by_with_request_body(String isValidUser)
			throws IOException {
		
		if (isValidUser.equalsIgnoreCase("valid") ) {
			assertTrue(UserDataUpdateRequest.createdUsers.size()>0);
			UserPayload objUserPayload = UserDataUpdateRequest.createdUsers.get(0);
			response = userDataUpdateRequest.updateUser(objUserPayload, "auth");
		}
		if (isValidUser.equalsIgnoreCase("invalid")) {
			for (UserPayload user : UserDataUpdateRequest.getUserPayloads()) {
				if (user.getExpectedStatusCode() == (400) && user.getUserId() < 0) {
					response = userDataUpdateRequest.updateUser(user, "auth");
					response.then().assertThat().statusCode(400);
					break;
				}
			}
		}
	}
	@Then("Admin receives response code {string}")
	public void admin_receives_response_code(String string) {
	    
	}

}
