package com.customer.service;

import static com.customer.constant.CustomerConstant.ERROR_DELETE_MESSAGE;
import static com.customer.constant.CustomerConstant.ERROR_SAVE_MESSAGE;
import static com.customer.constant.CustomerConstant.ERROR_STATUS;
import static com.customer.constant.CustomerConstant.ERROR_UPDATE_MESSAGE;
import static com.customer.constant.CustomerConstant.SUCCESS_DELETE_MESSAGE;
import static com.customer.constant.CustomerConstant.SUCCESS_SAVE_MESSAGE;
import static com.customer.constant.CustomerConstant.SUCCESS_STATUS;
import static com.customer.constant.CustomerConstant.SUCCESS_UPDATE_MESSAGE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.model.CustomerModel;
import com.customer.model.StatusResponse;
import com.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	public CustomerServiceImpl() {}
	
	@Autowired
    private CustomerRepository customerRepository;
	
	/**
	 * This method will handle the service that is pointing to a repository using jpaRepository
	 * Will add the Customer Records
	 * @return StatusResponse
	 * @author Jack Hermoso
	 */
	@Override
	public StatusResponse addCustomerRecords(List<CustomerModel> customerModel) {
		StatusResponse statusReponse = new StatusResponse();
		int maximumid = customerRepository.findMaxId();
		String customerIdValue = null;
		
		try {
			for(CustomerModel customer: customerModel) {
				
				maximumid +=1;
				customerIdValue = formatCustomerId("CUST-", maximumid);
				customer.setCustomerId(customerIdValue);
				customerRepository.save(customer);
			}
			statusReponse.setCustomerId(customerIdValue);
			statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
			statusReponse.setStatus(SUCCESS_STATUS);
		} catch (Exception e) {
			statusReponse.setMessage(ERROR_SAVE_MESSAGE + "->" + e.getMessage() );
			statusReponse.setStatus(ERROR_STATUS);
		}
		
		return statusReponse;
	}
	
	private String formatCustomerId(String prefix, int code) {
		return prefix +  StringUtils.leftPad("" + code, 6, '0');
	}
	
	/**
	 * This method will handle the service that is pointing to a repository using jpaRepository
	 * Will update the Customer Records
	 * @return StatusResponse
	 * @author Jack Hermoso
	 */
	@Override
	public StatusResponse updateCustomerRecords(List<CustomerModel> customerModel) {
		StatusResponse statusReponse = new StatusResponse();
		try {
			customerRepository.save(customerModel);
			statusReponse.setMessage(SUCCESS_UPDATE_MESSAGE);
			statusReponse.setStatus(SUCCESS_STATUS);
		}catch(Exception e) {
			statusReponse.setMessage(ERROR_UPDATE_MESSAGE + "->" + e.getMessage());
			statusReponse.setStatus(ERROR_STATUS);
		}
		
		return statusReponse;
	}
	
	/**
	 * This method will handle the service that is pointing to a repository using jpaRepository
	 * Will delete the Customer Using CustomerID
	 * @return StatusResponse
	 * @author Jack Hermoso
	 */
	@Override
	public StatusResponse deleteCustomerRecords(String customerId) {
		StatusResponse statusReponse = new StatusResponse();
		try {
			List<Object[]> customerModel = customerRepository.findCustomerID(customerId);
			Object customerObject[] = customerModel.get(0);
			
			CustomerModel customerModelItem = new CustomerModel();
			customerModelItem.setId(Integer.parseInt(customerObject[0].toString()));
			customerModelItem.setCustomerId(customerObject[1].toString());
			
			String dateString = customerObject[2].toString();
			Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			customerModelItem.setDateOfBirth(dateOfBirth);
			
			customerModelItem.setFirstName(customerObject[3].toString());
			customerModelItem.setLastName(customerObject[4].toString());
			
			customerRepository.delete(customerModelItem);
			
			statusReponse.setMessage(SUCCESS_DELETE_MESSAGE);
			statusReponse.setStatus(SUCCESS_STATUS);
			
		} catch (Exception e) {
			statusReponse.setMessage(ERROR_DELETE_MESSAGE + "->" + e.getMessage());
			statusReponse.setStatus(ERROR_STATUS);
			
		}
		
		return statusReponse;
	}
	
	/**
	 * This method will handle the service that is pointing to a repository using jpaRepository
	 * Will get the List of Customer Using CustomerID
	 * @return List<CustomerModel>
	 * @author Jack Hermoso
	 */
	@Override
	public List<CustomerModel> getCustomerRecords(String customerId) {
		List<CustomerModel> customerModelList = customerRepository.findAll();
		List<CustomerModel> customerModelFiltered = customerModelList.stream()
			.filter(customer -> customer.getCustomerId().equals(customerId))
			.collect(Collectors.toList());
		
		return customerModelFiltered;
	}

	/**
	 * This method will handle the service that is pointing to a repository using jpaRepository
	 * Will get all the Customer Records
	 * @return List<CustomerModel>
	 * @author Jack Hermoso
	 */
	@Override
	public List<CustomerModel> getCustomerRecordsAll() {
		List<CustomerModel> customerModelList = customerRepository.findAll();
		return customerModelList;
	}
	
}