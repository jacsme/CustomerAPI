package com.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.customer.model.CustomerModel;
import com.customer.model.StatusResponse;

@Service
public interface CustomerService {
	public StatusResponse addCustomerRecords(List<CustomerModel> customerModel);
	public StatusResponse updateCustomerRecords(List<CustomerModel> customerModel);
	public StatusResponse deleteCustomerRecords(String customerId);
	
	public List<CustomerModel> getCustomerRecords(String customerId);
	public List<CustomerModel> getCustomerRecordsAll();
}
