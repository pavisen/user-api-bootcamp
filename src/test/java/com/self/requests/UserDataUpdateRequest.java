package com.self.requests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.Assert;

import com.self.endPoints.UserDataEndPoints;
import com.self.payloads.UserPayload;
import com.self.utilities.RestSupport;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserDataUpdateRequest extends RestSupport {
	public Response updateUser(UserPayload objUserPayload, String hasAuthorization)
			throws IOException {
		String endpoint = getBaseURL() + UserDataEndPoints.UPDATEUSER + objUserPayload.getUserId();
		Response response;
		response = RestAssured.given().spec(getCommonRequestSpec(hasAuthorization)).body(objUserPayload).when()
				.put(endpoint).then().spec(getCommonResponseSpec(false)).assertThat().time(lessThan(2000L)).extract()
				.response();
		if (statusCode == 200) {
			userId1 = response.getBody().jsonPath().getInt("user_id");
			userFirstName1 = response.getBody().jsonPath().getString("user_first_name");
			response.then().body(JsonSchemaValidator.matchesJsonSchema(getUserPostWithAllFieldsJson()));
			response.then().body("user_id", notNullValue(), "user_first_name", notNullValue(), "user_last_name",
					notNullValue(), "user_contact_number", notNullValue(), "user_email_id", notNullValue());
			response.then().body("user_id", equalTo(userId1))
					.body("user_first_name", equalTo(objUserPayload.getUser_first_name()))
					.body("user_last_name", equalTo(objUserPayload.getUser_last_name()))
					.body("user_contact_number", equalTo(Long.parseLong(objUserPayload.getUser_contact_number())))
					.body("user_email_id", equalTo(objUserPayload.getUser_email_id()));
		}
		Assert.assertTrue(response.getContentType().equalsIgnoreCase("application/json"));
		return response;
	}

}
