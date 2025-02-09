package com.self.utilities;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.self.utilities.payloads.AddressPayload;
import com.self.utilities.payloads.UserPayload;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestUtils {

	public static ResourceBundle config = ResourceBundle.getBundle("config");
	public static CsvIO xlutils = new CsvIO(config.getString("XLpath"));

	// Schema Path
	public static File userPostWithAllFieldsJson = new File(
			RestUtils.class.getClassLoader().getResource(config.getString("programPostSchemajson")).getFile());

	public static Logger log = LogManager.getLogger();

	public static String baseURL = config.getString("BaseUrl");
	public static String userName = config.getString("userName");
	public static String passwd = config.getString("passwd");

	// PayLoad Objects
	public static UserPayload objUserPayload = new UserPayload();
	public static AddressPayload objAddressPayload = new AddressPayload();
	public static RequestSpecification request;
	public static Response userResponse;

	public static String extractResponse;
	public static int statusCode;
	public static int responseBody;
	public static int userId1;
	public static String userFirstName1;
	public static int userIdMandatoryFields;
	public static String userFirstNameMandatoryFields;
	public static int InvalidUserId = 5000;
	public static String InvalidUserFirstName = "zz";

}
