package com.self.requests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.self.endPoints.UserDataEndPoints;
import com.self.utilities.RestSupport;

import io.restassured.response.Response;

public class UserDataDeleteRequest extends RestSupport {

	public Response deleteUserById(String userId1, String hasAuthorization)
			throws IOException {
		String endpoint = getBaseURL() + UserDataEndPoints.DELETEBYUSERID + userId1;
		Response response = given().spec(getCommonRequestSpec(hasAuthorization)).when().delete(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		return response;
	}

	public Response deleteUserByFirstname(String username, String hasAuthorization)
			throws IOException {
		String endpoint = getBaseURL() + UserDataEndPoints.DELETEBYUSERFIRSTNAME + username;

		Response response = given().spec(getCommonRequestSpec(hasAuthorization)).when().delete(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		return response;
	}

}
