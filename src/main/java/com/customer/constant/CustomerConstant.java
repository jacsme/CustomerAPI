package com.customer.constant;

public class CustomerConstant {
	public static final String CUSTOMER_GETRECORD_URI = "${customer.getrecord.uri}";
	public static final String CUSTOMER_SAVERECORD_URI = "${customer.saverecords.uri}";
	public static final String CUSTOMER_UPDATERECORDS_URI = "${customer.updaterecords.uri}";
	public static final String CUSTOMER_DELETERECORD_URI = "${customer.deleterecord.uri}";
	public static final String CUSTOMER_GETALLRECORDS_URI = "${customer.getallrecords.uri}";
	
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
