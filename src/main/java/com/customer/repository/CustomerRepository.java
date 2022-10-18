package com.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.customer.model.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String>{
	@Query(" select " + 
		   " a.id, a.customerId, a.dateOfBirth, " + 
		   " a.firstName, a.lastName " + 
		   " from CustomerModel a " + 
		   " where a.customerId =:customerId")
	public List<Object[]> findCustomerID(@Param("customerId") String customerId);
		
	@Query(" select " + 
		   " max(a.id) as maximumid " + 
		   " from CustomerModel a ")
	public int findMaxId();
}
