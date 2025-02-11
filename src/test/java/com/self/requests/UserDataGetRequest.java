
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

	public Response getUserById(String userId, String hasAuthorization)
			throws IOException {
		String endpoint = getBaseURL() + UserDataEndPoints.GETUSERBYID + userId;
		Response response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get(endpoint).then()
				.spec(getCommonResponseSpec()).assertThat().time(lessThan(2000L)).extract().response();
		if (response.getStatusCode() == 200) {
			response.then().body("user_id", notNullValue(), "user_first_name", notNullValue(), "user_last_name",
					notNullValue(), "user_contact_number", notNullValue(), "user_email_id", notNullValue());
			response.then().body("user_id", equalTo(userId));
		}
		return response;
	}

	public Response getUserByFirstName(String userFirstName, String hasAuthorization)
			throws IOException {
		String endpoint = getBaseURL() + UserDataEndPoints.GETUSERBYFIRSTNAME + userFirstName;
		Response response = given().spec(getCommonRequestSpec(hasAuthorization)).when().get(endpoint).then()
				.spec(getCommonResponseSpec()).extract().response();
		return response;
	}
}
