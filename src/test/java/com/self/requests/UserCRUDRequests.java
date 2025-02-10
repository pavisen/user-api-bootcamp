package com.self.requests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.Assert;

import com.self.endPoints.UserModuleEndPoints;
import com.self.utilities.RestSupport;
import com.self.utilities.payloads.UserPayload;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserCRUDRequests extends RestSupport {



	public Response postRequest(UserPayload objUserPayload, String sheetName, int rowNo, String isvalidEndpoint)
			throws IOException {
		String endpoint;
		if (isvalidEndpoint.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.CREATEUSER.getPath();
		} else {
			endpoint = UserModuleEndPoints.endpoint.CREATEUSERINVALIDENDPOINT.getPath();
		}
		Response response = given().spec(getCommonRequestSpec("auth")).body(objUserPayload).when().post(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		if (statusCode == 201) {
			userId1 = response.getBody().jsonPath().getInt("user_id");
			userFirstName1 = response.getBody().jsonPath().getString("user_first_name");
			response.then().body(JsonSchemaValidator.matchesJsonSchema(getUserPostWithAllFieldsJson()));
			response.then().body("user_id", notNullValue(), "user_first_name", notNullValue(), "user_last_name",
					notNullValue(), "user_contact_number", notNullValue(), "user_email_id", notNullValue());
			response.then().body("user_first_name", equalTo(objUserPayload.getUser_first_name()))
					.body("user_last_name", equalTo(objUserPayload.getUser_last_name()))
					.body("user_contact_number", equalTo(Long.parseLong(objUserPayload.getUser_contact_number())))
					.body("user_email_id", equalTo(objUserPayload.getUser_email_id()))
					.body("userAddress.plotNumber", equalTo(objUserPayload.getUserAddress().getPlotNumber()))
					.body("userAddress.street",
							equalToCompressingWhiteSpace(objUserPayload.getUserAddress().getStreet()))
					.body("userAddress.state", equalTo(objUserPayload.getUserAddress().getState()))
					.body("userAddress.country", equalTo(objUserPayload.getUserAddress().getCountry()))
					.body("userAddress.zipCode",
							equalTo(Integer.parseInt(objUserPayload.getUserAddress().getZipCode())));
		}
		if (isvalidEndpoint.equalsIgnoreCase("valid"))
			Assert.assertEquals(response.getStatusCode(), Integer.parseInt(getXlutils().getCellData(sheetName, rowNo, 10)));
		else
			Assert.assertEquals(response.getStatusCode(), 404);
		return response;
	}

	public Response postRequestNoAuth(UserPayload objUserPayload, String sheetName, int rowNo, String isvalidEndpoint)
			throws IOException {
		Response response = given().spec(getCommonRequestSpec("noauth")).body(objUserPayload).when()
				.post(UserModuleEndPoints.endpoint.CREATEUSER.getPath()).then().spec(getCommonResponseSpec()).extract()
				.response();
		Assert.assertEquals(response.getStatusCode(), 401);
		Assert.assertTrue(response.getContentType().equalsIgnoreCase("application/json"));
		return response;
	}

	public Response postRequestMandatoryField(UserPayload objUserPayload, String sheetName, int rowNo,
			String isvalidEndpoint) throws IOException {
		String endpoint;
		if (isvalidEndpoint.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.CREATEUSER.getPath();
		} else {
			endpoint = UserModuleEndPoints.endpoint.CREATEUSERINVALIDENDPOINT.getPath();
		}
		Response response = given().spec(getCommonRequestSpec("auth")).body(objUserPayload).when().post(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		if (statusCode == 201) {
			userIdMandatoryFields = response.getBody().jsonPath().getInt("user_id");
			userFirstNameMandatoryFields = response.getBody().jsonPath().getString("user_first_name");
			response.then().body("user_id", notNullValue(), "user_first_name", notNullValue(), "user_last_name",
					notNullValue(), "user_contact_number", notNullValue(), "user_email_id", notNullValue());
			response.then().body("user_first_name", equalTo(objUserPayload.getUser_first_name()))
					.body("user_last_name", equalTo(objUserPayload.getUser_last_name()))
					.body("user_contact_number", equalTo(Long.parseLong(objUserPayload.getUser_contact_number())))
					.body("user_email_id", equalTo(objUserPayload.getUser_email_id()));
		}
		if (isvalidEndpoint.equalsIgnoreCase("valid"))
			Assert.assertEquals(response.getStatusCode(), Integer.parseInt(getXlutils().getCellData(sheetName, rowNo, 10)));
		else
			Assert.assertEquals(response.getStatusCode(), 404);
		return response;
	}



	public Response getUserById(String isvalidUserId, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint;
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYID.getPath() + userId1;
		} else if (isvalidEndpoint.equalsIgnoreCase("invalid") && isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYIDINVALIDENDPOINT.getPath() + userId1;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("invalid")) {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYID.getPath() + InvalidUserId;
		} else {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYIDINVALIDENDPOINT.getPath();
		}
		Response response;
		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get(endpoint).then()
				.spec(getCommonResponseSpec()).assertThat().time(lessThan(2000L)).extract().response();
		if (response.getStatusCode() == 200) {
			response.then().body("user_id", notNullValue(), "user_first_name", notNullValue(), "user_last_name",
					notNullValue(), "user_contact_number", notNullValue(), "user_email_id", notNullValue());
			response.then().body("user_id", equalTo(userId1));
		}
		return response;
	}

	public Response getUserByFirstName(String isvalidUserFirstName, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint;
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserFirstName.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYFIRSTNAME.getPath() + userFirstName1;
		} else if (isvalidEndpoint.equalsIgnoreCase("invalid") && isvalidUserFirstName.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYFIRSTNAMEINVALIDENDPOINT.getPath() + userFirstName1;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserFirstName.equalsIgnoreCase("invalid")) {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYFIRSTNAME.getPath() + InvalidUserFirstName;
		} else {
			endpoint = UserModuleEndPoints.endpoint.GETUSERBYFIRSTNAMEINVALIDENDPOINT.getPath();
		}
		Response response;
		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		return response;
	}

	public Response deleteUserById(String isvalidUserId, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint;
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERID.getPath() + userId1;
		} else if (isvalidEndpoint.equalsIgnoreCase("invalid") && isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERIDINVALID.getPath() + userId1;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("invalid")) {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERID.getPath() + InvalidUserId;
		} else {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERIDINVALID.getPath();
		}
		Response response = given().spec(getCommonRequestSpec(hasAuthorization)).when().delete(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		return response;
	}

	public Response deleteUserByFirstname(String isvalidUserName, String isvalidEndpoint, String hasAuthorization)
			throws IOException {
		String endpoint;
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserName.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERFIRSTNAME.getPath() + userFirstNameMandatoryFields;
		} else if (isvalidEndpoint.equalsIgnoreCase("invalid") && isvalidUserName.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERFIRSTNAMEINVALID.getPath()
					+ userFirstNameMandatoryFields;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserName.equalsIgnoreCase("invalid")) {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERFIRSTNAME.getPath() + InvalidUserFirstName;
		} else {
			endpoint = UserModuleEndPoints.endpoint.DELETEBYUSERFIRSTNAMEINVALID.getPath();
		}
		Response response;
		response = given().spec(getCommonRequestSpec(hasAuthorization)).when().delete(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		return response;
	}

	public Response updateUser(UserPayload objUserPayload, String hasAuthorization, String isvalidEndpoint,
			String isvalidUserId) throws IOException {
		String endpoint;
		if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.UPDATEUSER.getPath() + userId1;
		} else if (isvalidEndpoint.equalsIgnoreCase("invalid") && isvalidUserId.equalsIgnoreCase("valid")) {
			endpoint = UserModuleEndPoints.endpoint.UPDATEUSERINVALIDENDPOINT.getPath() + userId1;
		} else if (isvalidEndpoint.equalsIgnoreCase("valid") && isvalidUserId.equalsIgnoreCase("invalid")) {
			endpoint = UserModuleEndPoints.endpoint.UPDATEUSER.getPath() + InvalidUserId;
		} else {
			endpoint = UserModuleEndPoints.endpoint.UPDATEUSERINVALIDENDPOINT.getPath();
		}
		Response response;
		response = RestAssured.given().spec(getCommonRequestSpec(hasAuthorization)).body(objUserPayload).when()
				.put(endpoint).then().spec(getCommonResponseSpec()).assertThat().time(lessThan(2000L)).extract()
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
