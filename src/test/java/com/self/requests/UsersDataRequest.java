package com.self.requests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.self.endPoints.UserDataEndPoints;
import com.self.utilities.RestSupport;

import io.restassured.response.Response;

public class UsersDataRequest extends RestSupport {

	public Response getAllUsers(boolean isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint=getBaseURL()+"random";
		if (isvalidEndpoint) {
			endpoint = getBaseURL() +UserDataEndPoints.GETALLUSERS;
		}  
		Response response;
		System.out.println(endpoint +": ---"+hasAuthorization);

		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get( endpoint).then()
				.spec(getCommonResponseSpec(endpoint.contains(UserDataEndPoints.GETALLUSERS) && hasAuthorization.equals("auth"))).extract().response();

		return response;
	}

}
