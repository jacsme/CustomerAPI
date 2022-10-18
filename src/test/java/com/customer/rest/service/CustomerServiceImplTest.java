package com.customer.rest.service;

import static com.customer.constants.CustomerConstantsTest.ERROR_DELETE_MESSAGE;
import static com.customer.constants.CustomerConstantsTest.ERROR_SAVE_MESSAGE;
import static com.customer.constants.CustomerConstantsTest.ERROR_STATUS;
import static com.customer.constants.CustomerConstantsTest.SUCCESS_DELETE_MESSAGE;
import static com.customer.constants.CustomerConstantsTest.SUCCESS_SAVE_MESSAGE;
import static com.customer.constants.CustomerConstantsTest.SUCCESS_STATUS;
import static com.customer.constants.CustomerConstantsTest.SUCCESS_UPDATE_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.customer.model.CustomerModel;
import com.customer.model.StatusResponse;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;
import com.customer.service.CustomerServiceImpl;
import com.customer.util.GeneratorUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {
	
	@MockBean
    private CustomerRepository customerRepository;
	
	@MockBean
	List<CustomerModel> customerInitialRecordsRequest;
	
	@MockBean
	List<CustomerModel> customerAllRecordsRequest;
	
	@InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();
	
    @Before
    public void setUp() throws ParseException {
    	MockitoAnnotations.initMocks(this);
        customerInitialRecordsRequest = GeneratorUtil.setInitialCustomerRequest();
        doReturn(customerInitialRecordsRequest).when(customerRepository).save(customerInitialRecordsRequest);
    }
	
	@Test
	public void addCustomerRecordsSuccess() throws ParseException {
		List<CustomerModel> customerSecondRecordsRequest = GeneratorUtil.setSecondCustomerRequest();
		StatusResponse statusReponse = new StatusResponse();
		statusReponse = customerService.addCustomerRecords(customerSecondRecordsRequest);
		assertEquals(SUCCESS_SAVE_MESSAGE, statusReponse.getMessage());
		assertEquals(SUCCESS_STATUS, statusReponse.getStatus());
	}
	
	@Test
	public void addCustomerRecordsError() throws ParseException {
		StatusResponse statusReponse = new StatusResponse();
		List<CustomerModel> customerSecondRecordsRequest = null;
		statusReponse = customerService.addCustomerRecords(customerSecondRecordsRequest);
		String[] message = statusReponse.getMessage().split("->");
		
		assertEquals(ERROR_SAVE_MESSAGE, message[0]);
		assertEquals(ERROR_STATUS, statusReponse.getStatus());
	}
	
	@Test
	public void updateCustomerRecordsSuccess() throws ParseException {
	    List<CustomerModel> customerUpdateRecordsRequest = GeneratorUtil.setUpdateCustomerRequest();
		
		StatusResponse statusReponse = new StatusResponse();
		statusReponse = customerService.updateCustomerRecords(customerUpdateRecordsRequest);
		
		assertEquals(SUCCESS_UPDATE_MESSAGE, statusReponse.getMessage());
		assertEquals(SUCCESS_STATUS, statusReponse.getStatus());
	}
	
	@Test
	public void deleteCustomerRecordsSuccess() throws ParseException {
		List<Object[]> customerInitialRecordsObject = GeneratorUtil.setInitialCustomerObject();
		doReturn(customerInitialRecordsObject).when(customerRepository).findCustomerID(anyString());
		
        StatusResponse statusReponse = new StatusResponse();
        Object customerObject[] = customerInitialRecordsObject.get(0);
        statusReponse = customerService.deleteCustomerRecords(customerObject[1].toString());
		
		assertEquals(SUCCESS_DELETE_MESSAGE, statusReponse.getMessage());
		assertEquals(SUCCESS_STATUS, statusReponse.getStatus());
	}
	
	@Test
	public void deleteCustomerRecordsError() throws ParseException {
        StatusResponse statusReponse = new StatusResponse();
        statusReponse = customerService.deleteCustomerRecords(null);
        String[] message = statusReponse.getMessage().split("->");
		
		assertEquals(ERROR_DELETE_MESSAGE, message[0]);
		assertEquals(ERROR_STATUS, statusReponse.getStatus());
	}
	
	@Test
	public void getCustomerRecordsAll() throws ParseException {
		customerAllRecordsRequest = GeneratorUtil.getCustomerResponseAll();
		doReturn(customerAllRecordsRequest).when(customerRepository).findAll();
        List<CustomerModel> customerModelResponse = customerService.getCustomerRecordsAll();
	    assertEquals(customerRepository.findAll(), customerModelResponse);
	}
	
	@Test
	public void getCustomerRecordsCustomerId() throws ParseException {
		customerAllRecordsRequest = GeneratorUtil.getCustomerResponseAll();
        doReturn(customerAllRecordsRequest).when(customerRepository).findAll();
        
        List<CustomerModel> customerModelResponse = customerService.getCustomerRecords(customerAllRecordsRequest.get(0).getCustomerId());
        assertNotNull(customerModelResponse);
        assertEquals(customerAllRecordsRequest.get(0), customerModelResponse.get(0));
	}
}

