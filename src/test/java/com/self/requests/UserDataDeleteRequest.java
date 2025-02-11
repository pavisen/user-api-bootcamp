package com.self.requests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.self.endPoints.UserDataEndPoints;
import com.self.utilities.RestSupport;

import io.restassured.response.Response;

public class UserDataDeleteRequest  extends RestSupport {

	public Response deleteUserById(String isvalidUserId, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint = "";
		if ( isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = getBaseURL() + UserDataEndPoints.DELETEBYUSERID + userId1;
		} else if (  isvalidUserId.equalsIgnoreCase("invalid")) {
			endpoint = getBaseURL() + UserDataEndPoints.DELETEBYUSERID + InvalidUserId;
		}
		Response response = given().spec(getCommonRequestSpec(hasAuthorization)).when().delete(endpoint).then()
				.spec(getCommonResponseSpec(false)).extract().response();
		return response;
	}

	public Response deleteUserByFirstname(String isvalidUserName, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint = "";
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserName.equalsIgnoreCase("valid")) {
			endpoint = getBaseURL() + UserDataEndPoints.DELETEBYUSERFIRSTNAME + userFirstNameMandatoryFields;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserName.equalsIgnoreCase("invalid")) {
			endpoint = getBaseURL() + UserDataEndPoints.DELETEBYUSERFIRSTNAME + InvalidUserFirstName;
		}
		Response response;
		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().delete(endpoint).then()
				.spec(getCommonResponseSpec(false)).extract().response();
		return response;
	}


}
