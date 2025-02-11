package com.self.requests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import com.self.endPoints.UserDataEndPoints;
import com.self.utilities.RestSupport;

import io.restassured.response.Response;

public class UserDataGetRequest extends RestSupport {
	public Response getUserById(String isvalidUserId, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint = "";
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = getBaseURL() + UserDataEndPoints.GETUSERBYID + userId1;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("invalid")) {
			endpoint = getBaseURL() + UserDataEndPoints.GETUSERBYID + InvalidUserId;
		}
		Response response;
		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get(endpoint).then()
				.spec(getCommonResponseSpec(false)).assertThat().time(lessThan(2000L)).extract().response();
		if (response.getStatusCode() == 200) {
			response.then().body("user_id", notNullValue(), "user_first_name", notNullValue(), "user_last_name",
					notNullValue(), "user_contact_number", notNullValue(), "user_email_id", notNullValue());
			response.then().body("user_id", equalTo(userId1));
		}
		return response;
	}

	public Response getUserByFirstName(String isvalidUserFirstName, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint = "";
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserFirstName.equalsIgnoreCase("valid")) {
			endpoint = getBaseURL() + UserDataEndPoints.GETUSERBYFIRSTNAME + userFirstName1;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserFirstName.equalsIgnoreCase("invalid")) {
			endpoint = getBaseURL() + UserDataEndPoints.GETUSERBYFIRSTNAME + InvalidUserFirstName;
		}
		Response response;
		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get(endpoint).then()
				.spec(getCommonResponseSpec(false)).extract().response();
		return response;
	}

}
