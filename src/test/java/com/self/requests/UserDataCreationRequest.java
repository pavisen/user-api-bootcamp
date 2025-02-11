package com.self.requests;

import java.io.IOException;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.Assert;
import com.self.endPoints.UserDataEndPoints;
import com.self.payloads.UserPayload;
import com.self.utilities.RestSupport;

import static io.restassured.RestAssured.given;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserDataCreationRequest extends RestSupport {

	public Response postRequest(UserPayload userPayload, int rowNo) throws IOException {
		String endpoint = "";
		endpoint = getBaseURL() + UserDataEndPoints.CREATEUSER;
		Response response = given().spec(getCommonRequestSpec("auth")).body(userPayload).when().post(endpoint).then()
				.spec(getCommonResponseSpec(true)).extract().response();
		if (statusCode == 201) {
			userId1 = response.getBody().jsonPath().getInt("user_id");
			userFirstName1 = response.getBody().jsonPath().getString("user_first_name");
			response.then().body(JsonSchemaValidator.matchesJsonSchema(getUserPostWithAllFieldsJson()));
			response.then().body("user_id", notNullValue(), "user_first_name", notNullValue(), "user_last_name",
					notNullValue(), "user_contact_number", notNullValue(), "user_email_id", notNullValue());
			response.then().body("user_first_name", equalTo(userPayload.getUser_first_name()))
					.body("user_last_name", equalTo(userPayload.getUser_last_name()))
					.body("user_contact_number", equalTo(Long.parseLong(userPayload.getUser_contact_number())))
					.body("user_email_id", equalTo(userPayload.getUser_email_id()))
					.body("userAddress.plotNumber", equalTo(userPayload.getUserAddress().getPlotNumber()))
					.body("userAddress.street", equalToCompressingWhiteSpace(userPayload.getUserAddress().getStreet()))
					.body("userAddress.state", equalTo(userPayload.getUserAddress().getState()))
					.body("userAddress.country", equalTo(userPayload.getUserAddress().getCountry()))
					.body("userAddress.zipCode", equalTo(Integer.parseInt(userPayload.getUserAddress().getZipCode())));
		}
		System.out.println("User Phone #"+userPayload.getUser_contact_number());
		Assert.assertEquals(response.getStatusCode(), userPayload.getExpectedStatusCode());
		if (statusCode == 200) {
			Integer userId = Integer.parseInt(response.getBody().jsonPath().get("user_id"));
			createdUsers.put(userId, userPayload);
			userPayload.setUserId(userId);
		}
		return response;
	}

	public Response postRequestNoAuth(UserPayload objUserPayload)
			throws IOException {
		Response response = given().spec(getCommonRequestSpec("noauth")).body(objUserPayload).when()
				.post(getBaseURL() + UserDataEndPoints.CREATEUSER).then().spec(getCommonResponseSpec(false)).extract()
				.response();
		Assert.assertEquals(response.getStatusCode(), 401);
		Assert.assertTrue(response.getContentType().equalsIgnoreCase("application/json"));
		return response;
	}

}
