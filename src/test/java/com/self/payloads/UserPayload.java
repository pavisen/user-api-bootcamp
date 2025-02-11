package com.self.payloads;

public class UserPayload {

	private transient int expectedStatusCode;
	private transient String statusMessage;
	private transient int userId = -1;

	private String user_first_name;
	private String user_last_name;
	private String user_contact_number;
	private String user_email_id;
	private AddressPayload userAddress;

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}

	public String getUser_contact_number() {
		return user_contact_number;
	}

	public void setUser_contact_number(String user_contact_number) {
		this.user_contact_number = user_contact_number;
	}

	public String getUser_email_id() {
		return user_email_id;
	}

	public void setUser_email_id(String user_email_id) {
		this.user_email_id = user_email_id;
	}

	public AddressPayload getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(AddressPayload userAddress) {
		this.userAddress = userAddress;
	}

	public int getExpectedStatusCode() {
		return expectedStatusCode;
	}

	public void setExpectedStatusCode(int expectedStatusCode) {
		this.expectedStatusCode = expectedStatusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
