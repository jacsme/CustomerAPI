package com.customer.controller;

import static com.customer.constant.CustomerConstant.APPLICATION_JSON_API_VALUE;
import static com.customer.constant.CustomerConstant.CUSTOMER_DELETERECORD_URI;
import static com.customer.constant.CustomerConstant.CUSTOMER_GETALLRECORDS_URI;
import static com.customer.constant.CustomerConstant.CUSTOMER_GETRECORD_URI;
import static com.customer.constant.CustomerConstant.CUSTOMER_SAVERECORD_URI;
import static com.customer.constant.CustomerConstant.CUSTOMER_UPDATERECORDS_URI;
import static com.customer.constant.CustomerConstant.NO_RECORD_MESSAGE;
import static com.customer.constant.CustomerConstant.SUCCESS_STATUS;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.customer.model.CustomerModel;
import com.customer.model.StatusResponse;
import com.customer.service.CustomerService;

@RestController
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService CustomerService;

	/**
	 * This is to accept add customer request
	 * @param customerRecordsRequest
	 * @return
	 */
	@RequestMapping(value = CUSTOMER_SAVERECORD_URI, method = RequestMethod.POST, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<StatusResponse> addCustomerRecords(@RequestBody List<CustomerModel> customerRecordsRequest) {
		logger.info("Initiated the add Customer URI");
		
		StatusResponse statusReponse = CustomerService.addCustomerRecords(customerRecordsRequest);
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(statusReponse);
    }
	
	/**
	 * This is to accept update customer request
	 * @param customerRecordsRequest
	 * @return
	 */
	@RequestMapping(value = CUSTOMER_UPDATERECORDS_URI, method = RequestMethod.PUT, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<StatusResponse> updateCustomerRecords(@RequestBody List<CustomerModel> customerRecordsRequest) {
		logger.info("Initiated the update Customer URI");
		
		StatusResponse statusReponse = CustomerService.updateCustomerRecords(customerRecordsRequest);
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(statusReponse);
    }
	
	/**
	 * This is to accept delete request
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = CUSTOMER_DELETERECORD_URI + "/{customerId:.+}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<StatusResponse> deleteCustomerRecords(@PathVariable("customerId") String customerId) {
		logger.info("Initiated the delete Customer URI");
		
		StatusResponse statusReponse = new StatusResponse();
		statusReponse = CustomerService.deleteCustomerRecords(customerId);
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(statusReponse);
    }
	
	/**
	 * This is to accepts retrieval request
	 * @param customerId
	 * @return
	 */
	@RequestMapping(value = CUSTOMER_GETRECORD_URI + "/{customerId:.+}", method = RequestMethod.GET, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getCustomerRecords(@PathVariable("customerId") String customerId) {
		logger.info("Initiated the get Customer using customerId URI");
		
		List<CustomerModel> customerModelList = CustomerService.getCustomerRecords(customerId);
        
        StatusResponse statusReponse = new StatusResponse();
        statusReponse.setMessage(NO_RECORD_MESSAGE);
        statusReponse.setStatus(SUCCESS_STATUS);
        
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(customerModelList !=null ? customerModelList : statusReponse);
    }
	
	/**
	 * This is to accept getting all the records request
	 * @return
	 */
	@RequestMapping(value = CUSTOMER_GETALLRECORDS_URI, method = RequestMethod.GET, produces = APPLICATION_JSON_API_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<?> getCustomerRecordsAll() {
		logger.info("Initiated the get All Customer URI");
		
		List<CustomerModel> customerModelList = CustomerService.getCustomerRecordsAll();
        
        StatusResponse statusReponse = new StatusResponse();
        statusReponse.setMessage(NO_RECORD_MESSAGE);
        statusReponse.setStatus(SUCCESS_STATUS);
        
		return ResponseEntity
	            .status(HttpStatus.OK)                 
	            .body(customerModelList !=null ? customerModelList : statusReponse);
    }
}
