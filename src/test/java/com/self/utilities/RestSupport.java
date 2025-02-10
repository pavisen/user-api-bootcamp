package com.self.utilities;

import static io.restassured.RestAssured.preemptive;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.self.utilities.payloads.AddressPayload;
import com.self.utilities.payloads.UserPayload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestSupport {

	private static ResourceBundle config ;
	private static CsvIO xlutils;
	// Schema Path
	private static File userPostWithAllFieldsJson = new File(
			RestSupport.class.getClassLoader().getResource(getConfig().getString("programPostSchemajson")).getFile());
	private static Logger log = LogManager.getLogger();
	private static String baseURL = getConfig().getString("BaseUrl");
	private static String userName = getConfig().getString("userName");
	private static String passwd = getConfig().getString("passwd");
	
	
	// PayLoad Objects
	public  UserPayload objUserPayload = new UserPayload();
	public  AddressPayload objAddressPayload = new AddressPayload();
	public  RequestSpecification request;
	public  Response userResponse;
	public  String extractResponse;
	public  int statusCode;
	public  int responseBody;
	public  int userId1;
	public  String userFirstName1;
	public  int userIdMandatoryFields;
	public  String userFirstNameMandatoryFields;
	public  int InvalidUserId = 5000;
	public  String InvalidUserFirstName = "zz";
	
	protected RequestSpecification getCommonRequestSpec(String hasAuth) {
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		if (hasAuth.equalsIgnoreCase("auth"))
			builder.setAuth(preemptive().basic(getUserName(), getPasswd()));
		RequestSpecification requestSpec = builder.build();
		return requestSpec;
	}

	protected ResponseSpecification getCommonResponseSpec() {
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
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

	public static CsvIO getXlutils() {
		
		return xlutils;
	}

	public static ResourceBundle getConfig() {
		if(config==null) {
			config= ResourceBundle.getBundle(RestSupport.class.getClassLoader().getResource("config.properties").getFile());
		}
		return config;
	}

}
