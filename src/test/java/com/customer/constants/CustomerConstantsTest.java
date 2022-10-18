package com.customer.constants;

public class CustomerConstantsTest {
	public static final String CUSTOMER_REQUEST_JSON = "[{\"id\": 3, \"customerId\": \"CUST-000003\", \"dateOfBirth\": \"2001-01-01T14:56:22Z\", \"firstName\": \"Johnny\", \"lastName\": \"Herm\"}]";
	public static final String CUSTOMER_RECORD_URI = "${customer.records.uri}";
	public static final String CUSTOMER_RECORD_LIST_URI = "${customer.records.list.uri}";
	
	public static final String APPLICATION_JSON_API_VALUE = "application/json";
	
	public static final String ERROR_SAVE_MESSAGE = "Record save is not successful";
	public static final String SUCCESS_SAVE_MESSAGE = "Record save is successful";
	public static final String ERROR_DELETE_MESSAGE = "Record delete is not successful";
	public static final String SUCCESS_DELETE_MESSAGE = "Record delete is successful";
	public static final String ERROR_UPDATE_MESSAGE = "Record update is not successful";
	public static final String SUCCESS_UPDATE_MESSAGE = "Record update is successful";
	public static final String NO_RECORD_MESSAGE = "No record found";
	
	public static final String ERROR_STATUS = "ERROR";
	public static final String SUCCESS_STATUS = "SUCCESS";
	
	public static final String REGEX_TO_ESCAPE_SQUARE_BRACKETS = "[\\[\\]]";

}
