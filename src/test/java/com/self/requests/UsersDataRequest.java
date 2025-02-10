package com.self.requests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.self.endPoints.UserModuleEndPoints;
import com.self.utilities.RestSupport;

import io.restassured.response.Response;

public class UsersDataRequest extends RestSupport{

	public Response getAllUsers(boolean isvalidEndpoint,  String hasAuthorization)
			throws IOException {
		String endpoint;
		if (isvalidEndpoint) {
			endpoint = UserModuleEndPoints.endpoint.GETALLUSERS.getPath();
		} else {
			endpoint = UserModuleEndPoints.endpoint.GETALLUSERSINVALIDENDPOINT.getPath();
		}
		Response response;
		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		
		return response;
	}
	
}
