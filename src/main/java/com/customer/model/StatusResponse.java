package com.customer.model;

public class StatusResponse {
	
	/** Status for SUCCESS and ERROR **/
	private String status;
	
	/** Sending a discriptive Message for the client **/
	private String message;
	
	/** Returning customer id for the response **/
	private String customerId;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
}
