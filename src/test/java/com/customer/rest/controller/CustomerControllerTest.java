package com.customer.rest.controller;

import static com.customer.constant.CustomerConstant.SUCCESS_SAVE_MESSAGE;
import static com.customer.constant.CustomerConstant.SUCCESS_STATUS;
import static com.customer.constants.CustomerConstantsTest.CUSTOMER_REQUEST_JSON;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.customer.controller.CustomerController;
import com.customer.model.Authority;
import com.customer.model.AuthorityName;
import com.customer.model.CustomerModel;
import com.customer.model.StatusResponse;
import com.customer.model.User;
import com.customer.security.JwtTokenUtil;
import com.customer.security.JwtUser;
import com.customer.security.JwtUserFactory;
import com.customer.security.service.JwtUserDetailsService;
import com.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTest {

	@MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;
    
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    
    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    @WithMockUser(roles = "USER")
    public void addCustomer_Success() throws Exception {
    	setUserSecurity();
    	
    	List<CustomerModel> customerRecordsRequest = setCustomerRequest();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
    	doReturn(statusReponse).when(customerService).addCustomerRecords(customerRecordsRequest);
        mvc.perform(post("/v1/customer/saveRecords")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(CUSTOMER_REQUEST_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(roles = "USER")
    public void updateCustomer_Success() throws Exception {
    	setUserSecurity();
    	
    	List<CustomerModel> customerRecordsRequest = setCustomerRequest();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
    	doReturn(statusReponse).when(customerService).updateCustomerRecords(customerRecordsRequest);
        mvc.perform(put("/v1/customer/updateRecords")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(CUSTOMER_REQUEST_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(roles = "USER")
    public void deleteCustomer_Success() throws Exception {
    	setUserSecurity();
    	
    	List<CustomerModel> customerRecordsRequest = setCustomerRequest();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
    	doReturn(statusReponse).when(customerService).deleteCustomerRecords(customerRecordsRequest.get(0).getCustomerId().toString());
        mvc.perform(delete("/v1/customer/deleteRecord/" + customerRecordsRequest.get(0).getCustomerId())
        		.contentType(MediaType.APPLICATION_JSON)
        		.param("customerId", customerRecordsRequest.get(0).getCustomerId()))
                .andExpect(status().isOk());
    }
    
    @WithMockUser(roles = "USER")
    public void getCustomerCustomerID_Success() throws Exception {
    	setUserSecurity();
    	
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
		List<CustomerModel> customerModelList = getCustomerResponseCustomerID();
    	doReturn(customerModelList).when(customerService).getCustomerRecords(customerModelList.get(0).getCustomerId().toString());
        mvc.perform(get("/v1/customer/getRecord/" + customerModelList.get(0).getCustomerId())
        		.contentType(MediaType.APPLICATION_JSON)
        		.param("customerId", customerModelList.get(0).getCustomerId()))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(roles = "USER")
    public void getCustomerAll_Success() throws Exception {
    	setUserSecurity();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		List<CustomerModel> customerModelList = getCustomerResponseAll();
    	doReturn(customerModelList).when(customerService).getCustomerRecordsAll();
        mvc.perform(get("/v1/customer/getAllRecords/")
        		.contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void addCustomer_Unauthorized() throws Exception {
        List<CustomerModel> customerRecordsRequest = setCustomerRequest();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
    	doReturn(statusReponse).when(customerService).addCustomerRecords(customerRecordsRequest);
        mvc.perform(post("/v1/customer/saverecords")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(CUSTOMER_REQUEST_JSON))
        		.andExpect(status().isUnauthorized());
    }

    @Test
    public void updateCustomer_Unauthorized() throws Exception {
    	List<CustomerModel> customerRecordsRequest = setCustomerRequest();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
    	doReturn(statusReponse).when(customerService).updateCustomerRecords(customerRecordsRequest);
        mvc.perform(put("/v1/customer/updaterecords")
        		.contentType(MediaType.APPLICATION_JSON)
        		.content(CUSTOMER_REQUEST_JSON))
        		.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void deleteCustomer_Unauthorized() throws Exception {
    	List<CustomerModel> customerRecordsRequest = setCustomerRequest();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
    	doReturn(statusReponse).when(customerService).deleteCustomerRecords(customerRecordsRequest.get(0).getCustomerId().toString());
        mvc.perform(delete("/v1/customer/deleteRecord/" + customerRecordsRequest.get(0).getCustomerId())
        		.contentType(MediaType.APPLICATION_JSON)
        		.param("customerId", customerRecordsRequest.get(0).getCustomerId()))
        		.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void getCustomerAll_Unauthorized() throws Exception {
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		List<CustomerModel> customerModelList = getCustomerResponseAll();
    	doReturn(customerModelList).when(customerService).getCustomerRecordsAll();
        mvc.perform(get("/v1/customer/getallrecords/")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isUnauthorized());
    }
    
    @Test
    public void getCustomerCustomerID_Unauthorized() throws Exception {
    	List<CustomerModel> customerRecordsRequest = setCustomerRequest();
    	StatusResponse statusReponse = new StatusResponse();
    	statusReponse.setMessage(SUCCESS_SAVE_MESSAGE);
		statusReponse.setStatus(SUCCESS_STATUS);
		
		List<CustomerModel> customerModelList = getCustomerResponseCustomerID();
    	doReturn(customerModelList).when(customerService).getCustomerRecords(customerRecordsRequest.get(0).getCustomerId().toString());
        mvc.perform(get("/v1/customer/getrecord/" + customerRecordsRequest.get(0).getCustomerId())
        		.contentType(MediaType.APPLICATION_JSON)
        		.param("customerId", customerRecordsRequest.get(0).getCustomerId()))
        		.andExpect(status().isUnauthorized());
    }
    
	private void setUserSecurity() {
		Authority authority = new Authority();
        authority.setId(1L);
        authority.setName(AuthorityName.ROLE_ADMIN);
        List<Authority> authorities = Arrays.asList(authority);

        User user = new User();
        user.setUsername("username");
        user.setAuthorities(authorities);
        user.setEnabled(Boolean.TRUE);

        JwtUser jwtUser = JwtUserFactory.create(user);
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn(user.getUsername());
        when(jwtUserDetailsService.loadUserByUsername(eq(user.getUsername()))).thenReturn(jwtUser);
	}
	
	public List<CustomerModel> setCustomerRequest() throws ParseException{
		List<CustomerModel> customerModelRequest = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-00000001");
		String dateString = "1980-11-01";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord");
		customerModel.setLastName("Hermoso");
		
		customerModelRequest.add(customerModel);
		return customerModelRequest;
	}
	
	public List<CustomerModel> getCustomerResponseAll() throws ParseException{
		List<CustomerModel> customerModelResponse = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-00000001");
		String dateString = "1980-11-01";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord");
		customerModel.setLastName("Hermoso");
		
		customerModelResponse.add(customerModel);
		
		CustomerModel customerModel2 = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-00000002");
		String dateString2 = "1990-11-01";
		Date dateOfBirth2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateString2);
		
		customerModel.setDateOfBirth(dateOfBirth2);
		customerModel.setFirstName("John");
		customerModel.setLastName("Hermoso");
		
		customerModelResponse.add(customerModel2);
		return customerModelResponse;
	}
	
	public List<CustomerModel> getCustomerResponseCustomerID() throws ParseException{
		List<CustomerModel> customerModelResponse = new ArrayList<>();
		CustomerModel customerModel = new CustomerModel();
		customerModel.setId(1);
		customerModel.setCustomerId("CUST-00000001");
		String dateString = "1980-11-01";
		Date dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
		
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setFirstName("Jack Lord");
		customerModel.setLastName("Hermoso");
		customerModelResponse.add(customerModel);
		return customerModelResponse;
	}
}
