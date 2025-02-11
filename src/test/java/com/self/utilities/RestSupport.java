package com.self.utilities;

import static io.restassured.RestAssured.preemptive;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.self.payloads.UserPayload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestSupport {

	public final static Map<Integer, UserPayload> createdUsers = new HashMap<>();

	private static ResourceBundle config = ResourceBundle.getBundle("config");
	protected static CsvIO csvUtils = new CsvIO(config.getString("csvpath"));
	protected static List<UserPayload> userPayloads = csvUtils.createUserPayloads();
	// Schema Path
	private static File userPostWithAllFieldsJson = new File(
			RestSupport.class.getClassLoader().getResource(getConfig().getString("programPostSchemajson")).getFile());
	private static Logger log = LogManager.getLogger();
	private static String baseURL = getConfig().getString("BaseUrl");
	private static String userName = getConfig().getString("userName");
	private static String passwd = getConfig().getString("passwd");

	public static List<UserPayload> getUserPayloads() {
		return userPayloads;
	}

	// PayLoad Objects

	public RequestSpecification request;
	public Response userResponse;
	public String extractResponse;
	public int statusCode;
	public int responseBody;
	public int userId1;
	public String userFirstName1;
	public int userIdMandatoryFields;
	public String userFirstNameMandatoryFields;
	public int InvalidUserId = 905000;
	public String InvalidUserFirstName = "zz";

	protected RequestSpecification getCommonRequestSpec(String hasAuth) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		if (hasAuth.equalsIgnoreCase("auth"))
			builder.setAuth(preemptive().basic(getUserName(), getPasswd()));
		RequestSpecification requestSpec = builder.build();
		return requestSpec;
	}

	protected ResponseSpecification getCommonResponseSpec(boolean valid) {
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		if (valid)
			responseSpecBuilder.expectHeader("Content-Type", "application/json");
		ResponseSpecification responseSpec = responseSpecBuilder.build();
		return responseSpec;
	}

	public static String getPasswd() {
		return passwd;
	}

	public static String getUserName() {
		return userName;
	}

	public static String getBaseURL() {
		return baseURL;
	}

	public static Logger getLog() {
		return log;
	}

	public static File getUserPostWithAllFieldsJson() {
		return userPostWithAllFieldsJson;
	}

	public static CsvIO getCsvUtils() {
		return csvUtils;
	}

	public static ResourceBundle getConfig() {

		return config;
	}

}
