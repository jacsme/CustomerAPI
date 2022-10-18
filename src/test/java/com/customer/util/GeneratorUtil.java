package com.customer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.customer.model.CustomerModel;

public class GeneratorUtil {
	
	public static List<Object[]> setInitialCustomerObject() throws ParseException{
		List<Object[]> customerModel = new ArrayList<>();
		Object[] customerObject = new Object[10];
		customerObject[0] = 1;
		customerObject[1] = "CUST-0000001";
		customerObject[2] = "2018-11-01";
		customerObject[3] = "Hermoso, Jack Lord";
		customerObject[4] = "JLH";
		customerObject[5] = "Mr";
		customerObject[6] = "Male";
		customerObject[7] = "Single";
		customerObject[8] = 25;
		customerObject[9] = "YES";
		
		customerModel.add(customerObject);
		return customerModel;
	}
	
	public static List<Object[]> setInitialAddressObject() throws ParseException{
		List<Object[]> addressRecords = new ArrayList<>();
		Object[] addressObject = new Object[9];
		addressObject[0] = 1;
		addressObject[1] = "CUST-0000001";
		addressObject[2] = 4;
		addressObject[3] = "Trampulin Street";
		addressObject[4] = "Truganina";
		addressObject[5] = "Melbourne";
		addressObject[6] = "VIC";
		addressObject[7] = "AUSTRALIA";
		addressObject[8] = "1234";
		
		addressRecords.add(addressObject);
		return addressRecords;
	}
	
	public static List<CustomerModel> setInitialCustomerRequest() throws ParseException{
		List<CustomerModel> customerModelRequest = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-0000001");
		String dateString = "1980-01-05";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord");
		customerModel.setLastName("Hermoso");
		customerModelRequest.add(customerModel);
		return customerModelRequest;
	}
	
	public static List<CustomerModel> setSecondCustomerRequest() throws ParseException{
		List<CustomerModel> customerModelRequest = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(2);
		customerModel.setCustomerId("CUST-0000002");
		String dateString = "1990-11-01";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord");
		customerModel.setLastName("Hermoso");
		customerModelRequest.add(customerModel);
		return customerModelRequest;
	}
	
	public static List<CustomerModel> setUpdateCustomerRequest() throws ParseException{
		List<CustomerModel> customerModelRequest = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-0000001");
		String dateString = "1980-01-05";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord AA"); //the new name
		customerModel.setLastName("Hermoso");
		customerModelRequest.add(customerModel);
		return customerModelRequest;
	}
	
	public static List<CustomerModel> setUpdateCustomerErrorRequest() throws ParseException{
		List<CustomerModel> customerModelRequest = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(-1);
		customerModel.setCustomerId(null);
		String dateString = "1980-01-05";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord AA"); //the new name
		customerModel.setLastName("Hermoso");
		customerModelRequest.add(customerModel);
		return customerModelRequest;
	}
	
	public static List<CustomerModel> getCustomerResponseAll() throws ParseException{
		List<CustomerModel> customerModelResponse = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-0000001");
		String dateString = "1980-01-05";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord");
		customerModel.setLastName("Hermoso");
		customerModelResponse.add(customerModel);
		
		CustomerModel customerModel2 = new CustomerModel();
		customerModel2.setId(2);
		customerModel2.setCustomerId("CUST-0000002");
		String dateString2 = "1990-11-01";
		Date dateOfBirth2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateString2);
		
		customerModel2.setDateOfBirth(dateOfBirth2);
		customerModel2.setFirstName("Jack Lord 2");
		customerModel2.setLastName("Hermoso2");
		customerModelResponse.add(customerModel2);
		return customerModelResponse;
	}
	
	public static List<CustomerModel> getCustomerResponseCustomerID() throws ParseException{
		List<CustomerModel> customerModelResponse = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-0000001");
		String dateString = "1980-11-01";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord");
		customerModel.setLastName("Hermoso");
		customerModelResponse.add(customerModel);
		return customerModelResponse;
	}
}
