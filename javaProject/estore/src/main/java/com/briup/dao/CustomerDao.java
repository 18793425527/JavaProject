package com.briup.dao;

import com.briup.bean.Customer;

public interface CustomerDao {
	
	Customer findByUsername(String username);
	Customer findByUserPhone(String UserPhone);
	Customer findByUserEmail(String UserEmail);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);

}
